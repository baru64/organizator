package organizator;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
/**
 *
 * @author baru
 */
public class ImageNoteFrame extends NoteFrame{
    private String path;
    public ImageNoteFrame(Organizator org, String title, String path, int bgcolor, JDesktopPane desktop, int x, int y, int sizeX, int sizeY)
    {
        super(org, bgcolor, title, x, y, sizeX, sizeY);
        type = 0;
        JLabel imglabel;

        ImageIcon img = new ImageIcon(path);
        if(img.getImageLoadStatus() == MediaTracker.COMPLETE)
        {
            imglabel = new JLabel("", img, JLabel.CENTER);
            this.setSize(img.getIconWidth(),img.getIconHeight()+45);
        } else
            imglabel = new JLabel("File not found", JLabel.CENTER);
        
        this.add(imglabel);
        this.desktop = desktop;
        this.path = path;
        this.title = title;
        this.bgcolor = bgcolor;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("edit".equals(e.getActionCommand()))
        {
            NoteCreator frame = new ImageNoteCreator(org, desktop,title,path, bgcolor);
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
        return path;
    }
}
