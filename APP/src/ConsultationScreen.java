import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


/**
 * ConsultationScreen class
 *
 * This class is used to get patient details from user
 *
 * @author Khaaled Farooq
 * IIT ID - 20210370
 * UOW ID - 18671159
 */
public class ConsultationScreen implements ActionListener {

    ArrayList<Doctor> doctors; // ArrayList of Added doctors
    JFrame frame; // Main JFrame
    GradientPanel gradientPanel; // Gradient panel for background color
    JComboBox yearField; // Combo box to select year of patient DOB
    JComboBox monthField; // Combo box to select Month of patient DOB
    JComboBox dayField; // Combo box to select Day of patient DOB
    JButton submitButton; // Button to submit patient details
    JTextField nameField; // Text field to enter patient name
    JTextField surnameField; // Text field to enter patient surname
    JTextField mobileField; // Text field to enter patient mobile number
    JTextField idField; // Text field to enter patient ID

    // All the months in the year
    List months = List.of("January","February","March","April","May","June","July","August","September","October","November","December");

    // List of months with 30 days
    List<String> monthsList = List.of("April","June","September","November");


    /**
     * Constructor
     *
     * Constructor will intialize the doctors array and create and display the JFrame
     * along with GUI components for the user to enter patient details
     *
     * @param doctors An ArrayLIst containing added doctors
     */
    public ConsultationScreen(ArrayList<Doctor> doctors){
        this.doctors=doctors;

        //Gradient panel to set background color
        gradientPanel = new GradientPanel(new Color(0x83a4d4),
                new Color(0xb6fbff));
        gradientPanel.setBorder(BorderFactory.createEtchedBorder(5,Color.BLACK,Color.BLACK));

        //main Frame
        frame = new JFrame("Schedule Consultation");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000,550);
        frame.setResizable(false);
        frame.setContentPane(gradientPanel);
        frame.setLayout(null);

        //Frame icon
        ImageIcon icon = new ImageIcon("iconLogo.png");
        frame.setIconImage(icon.getImage());

        JLabel titleLabel = new JLabel("Patient Details");
        titleLabel.setHorizontalTextPosition(JLabel.RIGHT);
        titleLabel.setVerticalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Lucida Calligraphy",Font.BOLD,28));
        titleLabel.setBounds(0,15,1000,50);

        JPanel panel = new JPanel();
        panel.setBounds(150,75,700,400);
        panel.setLayout(new GridLayout(6,2,10,10));
        panel.setOpaque(false);


        JLabel name = new JLabel("Name : ");
        JLabel surname = new JLabel("Surname : ");
        JLabel dob = new JLabel("Date of Birth :");
        JLabel mobile = new JLabel("Mobile Number : ");
        JLabel id = new JLabel("Patient ID : ");

        nameField = new JTextField();
        nameField.setOpaque(false);
        nameField.setBorder(new EtchedBorder(Color.BLACK,Color.BLACK));

        surnameField = new JTextField();
        surnameField.setOpaque(false);
        surnameField.setBorder(new EtchedBorder(Color.BLACK,Color.BLACK));

        JPanel dobField = new JPanel();
        dobField.setOpaque(false);
        dobField.setLayout(new GridLayout(3,2));

        mobileField = new JTextField();
        mobileField.setOpaque(false);
        mobileField.setBorder(new EtchedBorder(Color.BLACK,Color.BLACK));

        idField = new JTextField();
        idField.setOpaque(false);
        idField.setBorder(new EtchedBorder(Color.BLACK,Color.BLACK));

        JLabel year = new JLabel("Year : ");
        ArrayList<Integer> years = addYears();
        yearField = new JComboBox(years.toArray());
        JLabel month = new JLabel("Month : ");
        monthField = new JComboBox(months.toArray());
        JLabel day = new JLabel("Day : ");
        ArrayList<Integer> days =addDays();
        dayField = new JComboBox(days.toArray());

        dobField.add(year);
        dobField.add(yearField);
        dobField.add(month);
        dobField.add(monthField);
        dobField.add(day);
        dobField.add(dayField);

        JLabel blankLabel = new JLabel();
        blankLabel.setOpaque(false);

        //Button to submit patient details
        submitButton = new JButton("Submit");
        submitButton.setFocusable(false);
        submitButton.addActionListener(this);
        submitButton.setBackground(new Color(0xE1C699));
        submitButton.setBorder(new LineBorder(new Color(0x41B3A3),2,true));

        panel.add(name);
        panel.add(nameField);
        panel.add(surname);
        panel.add(surnameField);
        panel.add(dob);
        panel.add(dobField);
        panel.add(mobile);
        panel.add(mobileField);
        panel.add(id);
        panel.add(idField);
        panel.add(blankLabel);
        panel.add(submitButton);

        frame.add(titleLabel);
        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * Action listener
     * @param e Action Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //If submit Button is clicked
        if (e.getSource()==submitButton){

            Integer date = (Integer) dayField.getSelectedItem();
            String month = (String)monthField.getSelectedItem();

            //Month number of the month
            int monthNumber = months.indexOf(month);
            monthNumber+=1;

            Integer year = (Integer)yearField.getSelectedItem();

            String name = nameField.getText();
            String surname = surnameField.getText();
            String mobile = mobileField.getText();
            String id = idField.getText();

            //If the entered date is greater than 29th in February of a leap year
            if ((month.equalsIgnoreCase("February"))&&(date>29)&&(year%4==0)){
                JOptionPane.showMessageDialog(null,"February of "+year+" Can have a maximum of 29 days !","Date Error",JOptionPane.ERROR_MESSAGE);
            }

            //If the entered date is greater than 28th in February of a non-leap year
            else if ((month.equalsIgnoreCase("February"))&&(date>28)&&(year%4!=0)){
                JOptionPane.showMessageDialog(null,"February of "+year+" Can have a maximum of 28 days !","Date Error",JOptionPane.ERROR_MESSAGE);
            }

            //If the entered date is greater than 30th for a month with only 30 days
            else if(monthsList.contains(month)&&(date>30)){
                JOptionPane.showMessageDialog(null,month+" Can have a maximum of 30 days !","Date Error",JOptionPane.ERROR_MESSAGE);
            }

            //If name field is empty
            else if (name.equals("")){
                JOptionPane.showMessageDialog(null,"Name field cannot be empty","Name Error",JOptionPane.ERROR_MESSAGE);
            }

            //If surname field is empty
            else if (surname.equals("")){
                JOptionPane.showMessageDialog(null,"Surname field cannot be empty","Surname Error",JOptionPane.ERROR_MESSAGE);
            }

            //If mobile number field is empty
            else if (mobile.equals("")){
                JOptionPane.showMessageDialog(null,"Mobile Number field cannot be empty","Mobile Number Error",JOptionPane.ERROR_MESSAGE);
            }

            //If ID field is empty
            else if (id.equals("")){
                JOptionPane.showMessageDialog(null,"ID field cannot be empty","ID Error",JOptionPane.ERROR_MESSAGE);
            }

            //If all the fields are filled properly
            else{
                JOptionPane.showMessageDialog(null,"Patient Details Recorded ! \nProceed to Consultation Details Page","Success !",JOptionPane.INFORMATION_MESSAGE);

                //Opening ConsultationDetailsScreen
                new ConsultationDetailsScreen(doctors, name, surname, mobile, date, monthNumber, year, id);
                frame.dispose();//Closing this frame
            }
        }
    }

    /**
     * Returns an ArrayList containing years from 1870 to 2023, These
     * years will later be added to the DOB year combo box
     * @return An ArrayList containing years as Integers
     */
    public ArrayList<Integer> addYears(){
        ArrayList<Integer> yearList = new ArrayList<>();
        for(int year = 1870;year<=2023;year++){
            yearList.add(year);
        }
        return yearList;
    }

    /**
     * Returns an ArrayList containing days of the month in integers (1-31),
     * These days will later be added to the DOB day combo box
     * @return An ArrayList containing days of the month as Integers
     */
    public ArrayList<Integer> addDays(){
        ArrayList<Integer> dayList = new ArrayList<>();
        for(int day = 1;day<=31;day++){
            dayList.add(day);
        }
        return dayList;
    }
}
