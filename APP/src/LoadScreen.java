import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * LoadScreen Class
 *
 * Generates and displays the LoadScreen GUI
 *
 * @author Khaaled Farooq
 * IIT ID - 20210370
 * UOW ID - 18671159
 */
public class LoadScreen implements ActionListener {

    JButton viewDoctors; // Button to Proceed to VIew Doctors Screen
    JButton consultDoctor; // Button to Proceed to Consultation Screen
    ArrayList<Doctor> doctors; // A Doctor ArrayList containing all the added doctors

    /**
     * Constructor
     *
     * initialises the doctors ArrayList and
     * Generates and displays the Load Screen
     *
     * @param doctors A Doctor ArrayList containing all the added doctors
     */
    public LoadScreen(ArrayList<Doctor> doctors){
        this.doctors=doctors;
        Screen();
    }

    /**
     * Creates and Displays the Load Screen
     */
    public void Screen(){

        //Gradient panel
        GradientPanel gradientPanel = new GradientPanel(new Color(0x0CBABA),
                new Color(0x380036));
        gradientPanel.setBorder(BorderFactory.createEtchedBorder(5,Color.BLACK,Color.BLACK));

        //Main Frame
        JFrame frame = new JFrame("Westminster Skin Consultation Centre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,500);
        frame.setResizable(false);
        frame.setContentPane(gradientPanel);
        frame.setLayout(new BorderLayout());

        //Frame icon
        ImageIcon icon = new ImageIcon("iconLogo.png");
        frame.setIconImage(icon.getImage());

        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(150,125));
        titlePanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Westminster Skin Consultation Centre");
        titleLabel.setIcon(icon);
        titleLabel.setHorizontalTextPosition(JLabel.RIGHT);
        titleLabel.setVerticalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Lucida Calligraphy",Font.BOLD,28));
        titlePanel.add(titleLabel);

        JPanel buttonPanel = new JPanel(new GridLayout(3,1,10,20));
        buttonPanel.setPreferredSize(new Dimension(100,150));
        buttonPanel.setOpaque(false);

        //viewDoctors Button
        viewDoctors = new JButton("View Doctors Available");
        viewDoctors.setFocusable(false);
        viewDoctors.addActionListener(this);

        //consultDoctors Button
        consultDoctor = new JButton("Schedule Consultation");
        consultDoctor.setFocusable(false);
        consultDoctor.addActionListener(this);
        JPanel panel1 = new JPanel();
        panel1.setOpaque(false);

        buttonPanel.add(panel1);
        buttonPanel.add(viewDoctors);
        buttonPanel.add(consultDoctor);

        JPanel fillerPanel1 = new JPanel();
        JPanel fillerPanel2 = new JPanel();
        JPanel fillerPanel3 = new JPanel();

        fillerPanel1.setPreferredSize(new Dimension(200,300));
        fillerPanel2.setPreferredSize(new Dimension(200,300));
        fillerPanel3.setPreferredSize(new Dimension(300,160));

        fillerPanel1.setOpaque(false);
        fillerPanel2.setOpaque(false);
        fillerPanel3.setOpaque(false);

        frame.add(titlePanel,BorderLayout.NORTH);
        frame.add(buttonPanel,BorderLayout.CENTER);
        frame.add(fillerPanel1,BorderLayout.WEST);
        frame.add(fillerPanel2,BorderLayout.EAST);
        frame.add(fillerPanel3,BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    /**
     * Action Listener
     * @param e Action Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==viewDoctors){
            new ViewDoctorsScreen(doctors);
        }
        if (e.getSource()==consultDoctor){
            new ConsultationScreen(doctors);
        }
    }
}
