/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizator;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author baru
 */
public class ImageNoteCreator extends NoteCreator {
    
    //private JTextField pathField;
    private JComboBox colorCombo;
    private JButton openButton;
    private String path;
    public ImageNoteCreator(Organizator org, JDesktopPane desktop, String title, String path, int bgcolor)
    {
        super("Image Note Creator");
        this.x = this.getLocation().x;this.y = this.getLocation().y; this.org = org;this.path = path;
        setLayout(new GridLayout(4, 1));
        titleField = new JTextField(title);
        //pathField = new JTextField(path);
        openButton = new JButton("Open file");
        submitButton = new JButton("Submit");
        
        final DefaultComboBoxModel color = new DefaultComboBoxModel();
        color.addElement("White");
        color.addElement("Red");
        color.addElement("Green");
        color.addElement("Blue");
        colorCombo = new JComboBox(color);    
        colorCombo.setSelectedIndex(bgcolor);
        JScrollPane colorScrollPane = new JScrollPane(colorCombo);
        
        this.add(titleField);
        this.add(openButton);
        this.add(colorScrollPane);
        this.add(submitButton);
        //gdy wcisniety zostanie przycisk
        openButton.addActionListener(new ActionListener()
        {
         public void actionPerformed(ActionEvent e) 
         {
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(org);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                setPath(fc.getSelectedFile().getPath());
            }
         }          
        });
        submitButton.addActionListener(new ActionListener()
        {
         public void actionPerformed(ActionEvent e) 
         {
            NoteFrame frame = CreateImageNoteFrame();
            frame.setVisible(true); //necessary as of 1.3
            desktop.add(frame);
            org.addNote(frame, frame.getId());
        try {
            frame.setSelected(true);
            setClosed(true);
        } catch (java.beans.PropertyVetoException ex) {}
            
         }          
        });
    }
    
    public NoteFrame CreateImageNoteFrame()
    {
        return new ImageNoteFrame(org, titleField.getText(),path,colorCombo.getSelectedIndex(), this.getDesktopPane(),this.getLocation().x,this.getLocation().y);
    }
    public void setPath(String path)
    {
        this.path = path;
    }
}
