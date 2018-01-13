/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizator;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
/**
 *
 * @author baru
 */
public abstract class NoteCreator extends JInternalFrame{
    
    static final int xOffset = 30, yOffset = 30;
    protected JTextField titleField;
    protected JButton submitButton;
    protected int x=0, y=0;
    protected Organizator org;
    public NoteCreator(String header) {
        super(header, 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable


        setSize(300,300);
        setLocation(xOffset, yOffset);
    }
    
}
