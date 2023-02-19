import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import static javax.swing.BorderFactory.createEmptyBorder;

/**
 * ViewDoctorsScreen class
 *
 * Generates and Displays GUI that shows available doctor details
 *
 * @author Khaaled Farooq
 * IIT ID - 20210370
 * UOW ID - 18671159
 */
public class ViewDoctorsScreen{

    JTable table; //Table containing doctor details
    ArrayList<Doctor> doctors; //Doctor ArrayList containing added doctors

    /**
     * Constructor
     *
     * @param doctors Doctor ArrayList containing added doctors
     */
    public ViewDoctorsScreen(ArrayList<Doctor> doctors){
        this.doctors = doctors;

        //Gradient panel
        GradientPanel gradientPanel = new GradientPanel(new Color(0x0CBABA),
                new Color(0x380036));
        gradientPanel.setBorder(BorderFactory.createEtchedBorder(5,Color.BLACK,Color.BLACK));

        //Main Frame
        JFrame frame = new JFrame("View Doctors");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000,500);
        frame.setResizable(false);
        frame.setContentPane(gradientPanel);
        frame.setLayout(null);

        //Frame image icon
        ImageIcon icon = new ImageIcon("iconLogo.png");
        frame.setIconImage(icon.getImage());

        ImageIcon icon2 = new ImageIcon("doctorLogo.png");

        JLabel titleLabel = new JLabel("Doctor Details");
        titleLabel.setIcon(icon2);
        titleLabel.setHorizontalTextPosition(JLabel.RIGHT);
        titleLabel.setVerticalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Lucida Calligraphy",Font.BOLD,28));
        titleLabel.setBounds(0,15,1000,100);
        titleLabel.setIconTextGap(50);

        String[] columns = {"Name","Surname","DOB","Mobile Number","Licence","Specialisation","Day","Time slot"};
        int row = doctors.size();
        String[][] rows = rowData(row);
        table = new JTable(rows,columns);

        table.setRowHeight(table.getRowHeight() + 25);
        table.setOpaque(false);
        table.setBorder(createEmptyBorder());
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(70);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(90);
        table.getColumnModel().getColumn(5).setPreferredWidth(110);
        table.getColumnModel().getColumn(6).setPreferredWidth(370);
        table.getColumnModel().getColumn(7).setPreferredWidth(90);


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10,150,960,300);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(createEmptyBorder());
        table.setAutoCreateRowSorter(true);


        frame.add(titleLabel);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    /**
     * Returns a 2D string array containing doctor details
     * @param row integer containing number of rows to match number of doctors
     * @return A 2D string array containing doctor details
     */
    public String[][] rowData(int row){
        String[][] rows = new String[row][8];
        for(int i=0; i<row; i++) {
            Doctor d = doctors.get(i);
            rows[i][0] = d.getName();
            rows[i][1] = d.getSurname();
            rows[i][2] = d.getDateOfBirth().toString();
            rows[i][3] = d.getMobileNumber();
            rows[i][4] = d.getMedicalLicenceNumber();
            rows[i][5] = d.getSpecialisation();
            rows[i][6] = d.getDays().toString();
            rows[i][7] = (d.getStartTime().toString() + "-" + d.getEndTime().toString());
        }
        return rows;
    }
}
