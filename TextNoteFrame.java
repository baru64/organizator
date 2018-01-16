package organizator;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
/**
 *
 * @author baru
 */
public class TextNoteFrame extends NoteFrame {
    private JLabel text;
    private String textstr;
    public TextNoteFrame(Organizator org, String title, String textstr, int bgcolor, JDesktopPane desktopPane, int x, int y, int sizeX, int sizeY)
    {
        super(org, bgcolor, title,x, y, sizeX, sizeY);
        type = 1;
        text = new JLabel("<HTML>"+textstr,JLabel.CENTER);
        this.add(text);
        desktop = desktopPane;
        this.textstr = textstr;
        this.title = title;
        this.bgcolor = bgcolor;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("edit".equals(e.getActionCommand()))
        {
            NoteCreator frame = new TextNoteCreator(org, desktop,title,textstr,bgcolor);
            frame.setVisible(true); //necessary as of 1.3
            desktop.add(frame);
            try {
               frame.setSelected(true);
               setClosed(true);
               org.removeNote(id);
            } catch (java.beans.PropertyVetoException ex) {}
        }
    }
    
    public String getText()
    {
        return textstr;
    }
    
}
