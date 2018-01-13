/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizator;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;

/**
 *
 * @author baru
 */
public class TextNoteCreator extends NoteCreator {
    
    private JTextArea textArea;
    private JComboBox colorCombo;
    public TextNoteCreator(Organizator org, JDesktopPane desktop, String title, String text, int bgcolor)
    {
        super("Note Creator");
        this.x = this.getLocation().x;this.y = this.getLocation().y; this.org = org;
        setLayout(new GridLayout(4, 1));
        titleField = new JTextField(title);
        textArea = new JTextArea(text,15,60);
        submitButton = new JButton("Submit");
        
        final DefaultComboBoxModel color = new DefaultComboBoxModel();
        color.addElement("White");
        color.addElement("Red");
        color.addElement("Green");
        color.addElement("Blue");
        colorCombo = new JComboBox(color);    
        colorCombo.setSelectedIndex(bgcolor);
        JScrollPane colorScrollPane = new JScrollPane(colorCombo);
        
        //JDesktopPane desktop = this.getDesktopPane();
        this.add(titleField);
        this.add(textArea);
        this.add(colorScrollPane);
        this.add(submitButton);
        
        submitButton.addActionListener(new ActionListener()
        {
         @Override
         public void actionPerformed(ActionEvent e) 
         {
            NoteFrame frame = createTextNoteFrame();
            frame.setVisible(true);
            org.addNote(frame, frame.getId());
            desktop.add(frame);
        try {
            frame.setSelected(true);
            setClosed(true);
        } catch (java.beans.PropertyVetoException ex) {}
            
         }          
        });
    }
    public NoteFrame createTextNoteFrame()
    {
        return new TextNoteFrame(org, titleField.getText(),textArea.getText(),colorCombo.getSelectedIndex(), this.getDesktopPane(), this.getLocation().x,this.getLocation().y);
    }

    
}
