/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizator;
import java.awt.Color;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.Border;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author baru
 */
public abstract class NoteFrame extends JInternalFrame implements ActionListener {
    private int frameX = 0, frameY = 0;
    protected JDesktopPane desktop;
    protected int bgcolor;
    protected String title;
    private int color;
    protected static int number = 0;
    protected int id;
    protected Organizator org;
    protected int type;
    public NoteFrame(Organizator org, int color, String title, int x, int y) {
        super(title, 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable
        
        this.color = color; this.frameX = x; this.frameY = y; this.org = org;
        this.id = number;
        number++;
        JMenuBar menuBar = new JMenuBar();
        //menu
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);
        
        JMenuItem menuItem = new JMenuItem("Edit");
        
        menuItem.setActionCommand("edit");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        setJMenuBar(menuBar);
        //...Then set the window size or call pack...
        Color bgcolor = Color.WHITE;
        switch(color)
        {
            case 0: bgcolor = Color.WHITE;
                break;
            case 1: bgcolor = new Color(252,112,103);
                break;
            case 2: bgcolor = new Color(136,252,116);
                break;
            case 3: bgcolor = new Color(116,141,252);
                break;
        }
        setSize(300,300);
        setBackground(bgcolor);
        this.setBorder(BorderFactory.createLineBorder(new Color(122,65,25), 2));
        //Set the window's location.
        setLocation(frameX, frameY);
        
        addInternalFrameListener(new InternalFrameAdapter(){
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                // gdy zamkniemy notke usuwamy z mapy
                org.removeNote(id);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    public int getBgColor()
    {
        return color;
    }
    public int getId()
    {
        return id;
    }
    public int getType()
    {
        return type;
    }
    public abstract String getText();
}
