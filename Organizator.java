package organizator;

import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JFileChooser;
/**
 * 
 * @author baru
 */
public class Organizator extends JFrame implements ActionListener {

    private static JDesktopPane desktop;
    private BufferedImage img;
    private static HashMap<String, NoteFrame> notes;
    public Organizator() {
        super("организатор");
        notes = new HashMap();
        int inset = 20; //ustawiamy rozmiar okna
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset, screenSize.width  - inset*2, screenSize.height - inset*2);
        
        try {
            img = ImageIO.read(new File("bg.jpg"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // A specialized layered pane to be used with JInternalFrames
        desktop = new JDesktopPane() {
            @Override
            protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs);
                grphcs.drawImage(img, 0, 0, null);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(img.getWidth(), img.getHeight());
            }
        };
        
        //desktop = new JDesktopPane();
        setContentPane(desktop);
        setJMenuBar(createMenuBar());

    }
    //tworzenie paska menu
    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        //menu
        JMenu menu = new JMenu("организатор");
        menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu);

        //itemy
        JMenuItem menuItem = new JMenuItem("New Note");
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("newnote");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("New Image");
        menuItem.setMnemonic(KeyEvent.VK_I);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("newimage");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Save Board");
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("save");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Load Board");
        menuItem.setMnemonic(KeyEvent.VK_L);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("load");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Quit");
        menuItem.setMnemonic(KeyEvent.VK_Q);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("quit");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        return menuBar;
    }
    
    public void actionPerformed(ActionEvent e) {
        if ("newimage".equals(e.getActionCommand()))
        {
            createImageFrame();
        }
        else if ("newnote".equals(e.getActionCommand()))
        {
            createNoteFrame();
        }
        else if ("save".equals(e.getActionCommand()))
        {
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                saveBoard(fc.getSelectedFile().getPath());
            }
        }
        else if ("load".equals(e.getActionCommand()))
        {
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                loadBoard(fc.getSelectedFile().getPath());
            }
        }
        else { //quit
            quit();
        }
    }

    protected void createNoteFrame() {
        NoteCreator frame = new TextNoteCreator(this, desktop,"Note title","text",0);
        frame.setVisible(true); //necessary as of 1.3
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }
    protected void createImageFrame() {
        NoteCreator frame = new ImageNoteCreator(this, desktop,"Note title","path",0);
        frame.setVisible(true); //necessary as of 1.3
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }
    protected void loadImageNoteFrame(String title, String text, int bgcolor, int x, int y) {
        NoteFrame frame = new TextNoteFrame(this, title, text, bgcolor, desktop, x, y);
        this.addNote(frame, frame.getId());
        frame.setVisible(true); //necessary as of 1.3
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }
    protected void loadTextNoteFrame(String title, String path, int bgcolor, int x, int y) {
        NoteFrame frame = new ImageNoteFrame(this, title, path, bgcolor, desktop, x, y);
        this.addNote(frame, frame.getId());
        frame.setVisible(true); //necessary as of 1.3
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }
    
    //Quit the application.
    protected void quit() {
        System.exit(0);
    }
    
    private static void createAndShowGUI() {
        //JFrame.setDefaultLookAndFeelDecorated(true);

        //
        Organizator frame = new Organizator();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //wyswietlanie
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    public void addNote(NoteFrame note, int key)
    {
        notes.put(Integer.toString(key), note);
    }
    
    public void removeNote(int key)
    {
        notes.remove(Integer.toString(key));
    }
    
    private void saveBoard(String filename)
    {
        BufferedWriter bw = null;
	FileWriter fw = null;

	try {

            fw = new FileWriter(filename);
            bw = new BufferedWriter(fw);
            
            for(NoteFrame frame : notes.values())
            {
                    bw.write(Integer.toString(frame.getType())+"\n"
                            +frame.getTitle()+"\n"
                            +frame.getText()+"\n"
                            +Integer.toString(frame.getBgColor())+"\n"
                            +Integer.toString(frame.getLocation().x)+"\n"
                            +Integer.toString(frame.getLocation().y)+"\n");
            }

	} catch (IOException e) {

            e.printStackTrace();

	} finally {

            try {

		if (bw != null)
                    bw.close();

		if (fw != null)
                    fw.close();

            } catch (IOException ex) {

		ex.printStackTrace();

            }

	}
    }
    private void loadBoard(String filename)
    {
        BufferedReader br = null;
	FileReader fr = null;

	try 
        {

            fr = new FileReader(filename);
            br = new BufferedReader(fr);

            String firstLine;

            while ((firstLine = br.readLine()) != null) 
            {
                if(Integer.parseInt(firstLine) == 0)
                    loadTextNoteFrame(br.readLine(), br.readLine(), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()),Integer.parseInt(br.readLine()));
                else
                    loadImageNoteFrame(br.readLine(), br.readLine(), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()),Integer.parseInt(br.readLine()));
            }

            } catch (IOException e) {
			e.printStackTrace();
            } finally {
                try 
                {

                     if (br != null)
                        br.close();

                    if (fr != null)
                        fr.close();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
    }
}
