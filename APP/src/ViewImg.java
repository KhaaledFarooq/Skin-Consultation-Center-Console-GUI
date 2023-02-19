import javax.swing.*;

/**
 * ViewImg Class
 *
 * Creates a GUI window displaying the image selected by the user
 *
 * @author Khaaled Farooq
 * IIT ID - 20210370
 * UOW ID - 18671159
 */
public class ViewImg {
    String path;//path of the image

    /**
     * method that creates a GUI window that displays the image selected by the user
     * within a scroll pane
     * @param path A string containing the path of the image selected by the user
     */
    public ViewImg(String path){
        this.path = path;
        ImageIcon image = new ImageIcon(path);
        JLabel label = new JLabel(image);

        JFrame frame = new JFrame("Uploaded Image");
        frame.setSize(650,450);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JScrollPane sp = new JScrollPane(label);
        frame.add(sp);
        frame.setVisible(true);
    }
}
