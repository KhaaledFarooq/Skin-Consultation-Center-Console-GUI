import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/**
 * ConsultationDetailsScreen Class
 *
 * The class is used to schedule a consultation.
 *
 * @author Khaaled Farooq
 * IIT ID - 20210370
 * UOW ID - 18671159
 */
public class ConsultationDetailsScreen implements ActionListener {

    ArrayList<Doctor> doctors; // Arraylist of added doctors
    GradientPanel gradientPanel; // Gradient panel to give background color
    String pName; // Patient name
    String pSurname; // Patient surname
    int pYear; // Year of Patient DOB
    int pMonth; // Month of Patient DOB
    int pDay; // Day of Patient DOB
    String pMobile; // Patient mobile number
    Patient p; // Patient object
    String pID; // Patient ID
    String path; // Path of the patient uploaded image
    LocalDate cDate; // Date of consultation
    LocalTime cTime; // Time of consultation
    Doctor selectedDoctor; // Selected doctor
    JComboBox doctorBox; // Combo box containing all doctors
    JComboBox hourBox; // Combo box containing the hours of the day
    JComboBox minuteBox; // Combo box containing the minutes
    JComboBox yearsField; // Combo box containing the years
    JComboBox monthsField; // Combo box containing the months
    JComboBox daysField; // Combo box containing the days
    JComboBox durationField; // Combo box to select the duration of consultation
    JButton checkAvailability; // Button to check availability of a doctor
    JTextArea textArea; // Text area to show user messages about consultation details
    String addedDoctorID; // Medical licence number of the scheduled doctor
    String pound = "\u20AC"; // the pound symbol
    Integer cDuration; // Duration of the consultation
    String extension; // Extension of the user uploaded image file
    int cost; // Cost for the consultation
    JTextArea noteArea; // Text area for user to enter notes
    String notes; // Notes entered by the user
    JPanel panel3; // Panel containing the user notes, upload image ,view image and confirm consultation buttons
    JButton uploadImage; // Button to chose an image
    JButton viewImage; // Button to view uploaded image
    JButton complete; // Button to complete consultation and view summary screen
    JFrame newFrame;

    // All the months in the year
    List months = List.of("January","February","March","April","May","June","July","August","September","October","November","December");

    // List of months with 30 days
    List<String> monthsList = List.of("April","June","September","November");

    // Possible durations for a consultation
    Integer [] duration = {1,2,3,4,5};

    /**
     * Constructor
     *
     * Constructor will initialize the patient details variables and
     * create and display the consultation details JFrame with all the added GUI components
     *
     * @param doctors ArrayList of added doctors
     * @param pName A String containing patient name
     * @param pSurname A string containing patient surname
     * @param pMobile A String containing patient mobile number
     * @param pDay An integer containing the day of patient DOB
     * @param pMonth An integer containing the month number of patient DOB
     * @param pYear An integer containing the year of patient DOB
     * @param pID A String containing patient ID
     */
    public ConsultationDetailsScreen(ArrayList<Doctor> doctors,String pName, String pSurname,String pMobile,int pDay, int pMonth, int pYear, String pID) {
        this.doctors = doctors;
        this.pName =pName;
        this.pSurname =pSurname;
        this.pMobile = pMobile;
        this.pMonth=pMonth;
        this.pYear=pYear;
        this.pDay=pDay;
        this.pID = pID;

        //Gradient panel to set background color
        gradientPanel = new GradientPanel(new Color(0x83a4d4),
                new Color(0xb6fbff));
        gradientPanel.setBorder(BorderFactory.createEtchedBorder(5, Color.BLACK, Color.BLACK));

        newFrame = new JFrame("Consultation Details");// Main Frame
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setSize(1000, 550);
        newFrame.setResizable(false);
        newFrame.setLayout(null);
        newFrame.setContentPane(gradientPanel);

        //frame icon
        ImageIcon icon = new ImageIcon("iconLogo.png");
        newFrame.setIconImage(icon.getImage());

        JPanel panel = new JPanel();
        panel.setBounds(10, 20, 980, 100);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

        JLabel availability = new JLabel("Select Date time slot and Doctor to view availability : ");
        JLabel year = new JLabel("Year : ");
        ArrayList<Integer> years = addYears();
        yearsField = new JComboBox(years.toArray());
        JLabel month = new JLabel("Month : ");
        monthsField = new JComboBox(months.toArray());
        JLabel day = new JLabel("Day : ");
        ArrayList<Integer> days = addDays();
        daysField = new JComboBox(days.toArray());
        JLabel durationLabel = new JLabel("Duration (hrs) : ");
        durationField = new JComboBox(duration);

        panel.add(availability);
        panel.add(year);
        panel.add(yearsField);
        panel.add(month);
        panel.add(monthsField);
        panel.add(day);
        panel.add(daysField);
        panel.add(durationLabel);
        panel.add(durationField);


        ArrayList<String> doctorDetails = doctorDetails(doctors);// Doctor details to add to the combo box
        JLabel doctorLabel = new JLabel("Select a Doctor from the given list");
        doctorBox = new JComboBox(doctorDetails.toArray());

        JPanel panel2 = new JPanel();
        panel2.setBounds(10, 210, 980, 100);
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 28, 15));
        panel2.add(doctorLabel);
        panel2.add(doctorBox);

        JLabel timeLabel = new JLabel("Consultation time : ");
        String[] hours = addHours();
        String[] minutes = addMinutes();
        hourBox = new JComboBox(hours);
        minuteBox = new JComboBox(minutes);

        panel2.add(timeLabel);
        panel2.add(hourBox);
        panel2.add(minuteBox);

        //Button to check doctor availability
        checkAvailability = new JButton(" Check Availability ");
        checkAvailability.setPreferredSize(new Dimension(130, 60));
        checkAvailability.addActionListener(this);
        checkAvailability.setFocusable(false);
        checkAvailability.setBackground(new Color(0xE1C699));
        checkAvailability.setBorder(new LineBorder(new Color(0x41B3A3), 2, true));
        panel2.add(checkAvailability);

        //Text area to display messages to user
        textArea = new JTextArea(9, 100);
        textArea.setOpaque(true);
        textArea.setEnabled(false);
        textArea.setFont(new Font("Consolas", Font.BOLD, 14));
        textArea.setDisabledTextColor(new Color(0x000000));
        JScrollPane scrollPane = new JScrollPane(textArea);

        panel3 = new JPanel(new FlowLayout());
        JLabel noteLabel = new JLabel("Notes : ");
        panel3.add(noteLabel);

        //Text area to add user additional notes
        noteArea = new JTextArea(7,50);
        noteArea.setEnabled(true);
        noteArea.setFont(new Font("Consolas", Font.BOLD, 14));
        noteArea.setDisabledTextColor(new Color(0x000000));

        JScrollPane scrollPane2 = new JScrollPane(noteArea);
        panel3.add(scrollPane2);

        //Upload image button
        uploadImage = new JButton("Upload Image");
        uploadImage.addActionListener(this);
        panel3.add(uploadImage);

        //View image button
        viewImage = new JButton("View Uploaded image");
        viewImage.setEnabled(false);
        viewImage.addActionListener(this);
        panel3.add(viewImage);

        //Button to complete consultation
        complete = new JButton("Save&View consultation details");
        complete.setEnabled(false);
        complete.addActionListener(this);
        panel3.add(complete);

        panel.setOpaque(false);
        newFrame.add(panel);
        panel2.setOpaque(false);
        newFrame.add(panel2);
        newFrame.add(scrollPane);
        panel3.setOpaque(false);
        newFrame.add(panel3);
        newFrame.setVisible(true);
    }

    /**
     * Returns an ArrayList containing years from 2023 to 2040, These
     * years will later be added to the year combo box
     * @return An ArrayList containing years as Integers
     */
    public ArrayList<Integer> addYears() {
        ArrayList<Integer> yearList = new ArrayList<>();
        //Adding years from 2023 to 2040
        for (int year = 2023; year <= 2040; year++) {
            yearList.add(year);
        }
        return yearList;
    }

    /**
     * Returns an ArrayList containing days of the month in integers (1-31),
     * These days will later be added to the day combo box
     * @return An ArrayList containing days of the month as Integers
     */
    public ArrayList<Integer> addDays() {
        ArrayList<Integer> dayList = new ArrayList<>();
        //Adding days of a month to the array
        for (int day = 1; day <= 31; day++) {
            dayList.add(day);
        }
        return dayList;
    }

    /**
     * Returns a String ArrayList of doctor details
     * The doctor details will be in the format; Licence - DoctorFullName - Specialization
     * These doctor details will be later added to the doctor combo box
     * @param doctors ArrayList of doctors
     * @return String ArrayList containing doctor details
     */
    public ArrayList<String> doctorDetails(ArrayList<Doctor> doctors) {
        ArrayList<String> dDetails = new ArrayList<>();
        for (Doctor d : doctors) {
            String name = d.getName();
            String surname = d.getSurname();
            String sp = d.getSpecialisation();
            String licence = d.getMedicalLicenceNumber();
            String detail = (licence + " - " + name + " " + surname + " - " + sp);
            dDetails.add(detail);
        }
        return dDetails;
    }

    /**
     * Returns a String array containing the hours in a day (00 to 23)
     * Each hour should contain two characters to later be converted into LocalTime
     * Hence a "0" will be added in front of hours with one character eg: "03"
     * These hours will be later added to the hours - combo box
     * @return A String array containing hours in a day
     */
    public String[] addHours() {
        String[] hour = new String[24];
        String num;
        for (int i = 0; i < hour.length; i++) {
            if (i < 10) {
                num = ("0" + i);
            } else {
                num = (Integer.toString(i));
            }
            hour[i] = num;
        }
        return hour;
    }

    /**
     * Returns a String array containing minutes in an hour (00 to 59)
     * Each minute should contain two characters to be later converted into LocalTime
     * Hence a "0" will be added in front of minutes with one character eg: "03"
     * These minutes will be later added to the minutes - combo box
     * @return A String array containing minutes in an hour
     */
    public String[] addMinutes() {
        String[] minute = new String[60];
        String num;
        for (int i = 0; i < minute.length; i++) {
            if (i < 10) {
                num = ("0" + i);
            } else {
                num = (Integer.toString(i));
            }
            minute[i] = num;
        }
        return minute;
    }

    /**
     * Action listener
     * @param e Action Event
     */
    public void actionPerformed(ActionEvent e) {

        // If check availability button is clicked
        if (e.getSource() == checkAvailability) {

            Integer date = (Integer) daysField.getSelectedItem();
            String month = (String) monthsField.getSelectedItem();
            Integer year = (Integer) yearsField.getSelectedItem();

            //Month number of the month
            int monthNumber = months.indexOf(month);
            monthNumber += 1;

            //If the entered date is greater than 29th in February of a leap year
            if ((month.equalsIgnoreCase("February")) && (date > 29) && (year % 4 == 0)) {
                JOptionPane.showMessageDialog(null, "February of " + year + " Can have a maximum of 29 days !", "Date Error", JOptionPane.ERROR_MESSAGE);
            }

            //If the entered date is greater than 28th in February of a non-leap year
            else if ((month.equalsIgnoreCase("February")) && (date > 28) && (year % 4 != 0)) {
                JOptionPane.showMessageDialog(null, "February of " + year + " Can have a maximum of 28 days !", "Date Error", JOptionPane.ERROR_MESSAGE);
            }

            //If the entered date is greater than 30th for a month with only 30 days
            else if (monthsList.contains(month) && (date > 30)) {
                JOptionPane.showMessageDialog(null, month + " Can have a maximum of 30 days !", "Date Error", JOptionPane.ERROR_MESSAGE);
            }


            else {
                //Creating consultation date in LocalDate format
                cDate = LocalDate.of(year, monthNumber, date);

                //Creating consultation time in LocalTime format
                String cHour = (String) hourBox.getSelectedItem();
                String cMinute = (String) minuteBox.getSelectedItem();
                cTime = LocalTime.parse(cHour + ":" + cMinute);

                //Getting the selected doctor
                String cDoctor = (String) doctorBox.getSelectedItem();

                //Getting consultation day of the week
                String cDay = String.valueOf(cDate.getDayOfWeek());

                //Getting Duration of the Consultation
                cDuration = (Integer) durationField.getSelectedItem();

                //Getting the doctor id of the selected doctor
                cDoctor = cDoctor.trim();
                int index = cDoctor.indexOf(" ");
                String cDoctorID = cDoctor.substring(0, index);

                int indexNum = -1;
                //Getting the selected doctors index in the doctors ArrayList
                for (int i = 0; i < doctors.size(); i++) {
                    Doctor d = doctors.get(i);
                    if (d.getMedicalLicenceNumber().equalsIgnoreCase(cDoctorID)) {
                        indexNum = i;
                        break;
                    }
                }
                Doctor d = doctors.get(indexNum);

                //Adding Consultation date and time to the text area
                textArea.append("Consultation Date And Time : " + cDate + "   " + cDay + "   " + cTime + "\n");

                //Checking if the selected doctor is available in that day of the week
                int checkDateAvailable = -1;
                for (int i = 0; i < d.getDays().size(); i++) {
                    if (d.getDays().get(i).equalsIgnoreCase(cDay)) {
                        checkDateAvailable = 1;
                        break;
                    }
                }

                //Checking if the selected doctor is available in the time of the consultation
                int checkTimeAvailable = -1;
                if (d.getStartTime().isBefore(cTime) && (d.getEndTime().isAfter(cTime))) {
                    checkTimeAvailable = 1;
                }

                //If doctor is not available on that day of the week
                if (checkDateAvailable != 1) {
                    textArea.append("Dr." + d.getName() + " " + d.getSurname() + " is not available on " + cDay + "\n");
                    setRandomDoctor(cDay, cTime);
                }

                //If doctor is available on that day of the week but not at the given time
                else if (checkTimeAvailable != 1) {
                    textArea.append("Dr." + d.getName() + " " + d.getSurname() + " is not available at " + cTime + "\n");
                    setRandomDoctor(cDay, cTime);
                }

                //If the doctor is available on that day of the week at the given time
                else {
                    textArea.append("Dr." + d.getName() + " " + d.getSurname() + " is available on the selected time slot of the selected date" + "\n");
                    textArea.append("Consultation scheduled !\n\n");
                    selectedDoctor = d;
                    setPrice(cDuration);//Setting the price of the consultation
                    checkAvailability.setEnabled(false);//Disabling the Check availability button
                    complete.setEnabled(true);//Enabling the complete consultation button
                }
            }
        }

        //If upload image button is clicked
        if (e.getSource()==uploadImage){

            JFileChooser fileChooser = new JFileChooser();

            //Setting file chooser to get files with image extensions as default
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png");
            fileChooser.setFileFilter(filter);


            fileChooser.setDialogTitle("Select a image to upload");

            int userSelection = fileChooser.showSaveDialog(null);

            //If a file was chosen by the user
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                //Getting the path of the image
                path = fileToSave.getAbsolutePath();

                //Getting the extension of the image file
                int i = path.lastIndexOf('.');
                if (i > 0) {
                    extension = path.substring(i+1);
                }

                JOptionPane.showMessageDialog(null,"Image Uploaded","Success !",JOptionPane.INFORMATION_MESSAGE);
                viewImage.setEnabled(true);//Enabling viewImage button
            }
        }

        //If view image button is clicked
        if (e.getSource()==viewImage){
            new ViewImg(path);//Displaying the selected image in a new window
        }

        //If complete consultation button is clicked
        if (e.getSource()==complete){
            //Getting user notes
            notes = noteArea.getText();

            //Consultation placed successfully
            JOptionPane.showMessageDialog(null,"Consultation Placed Successfully","Consultation Placed !",JOptionPane.INFORMATION_MESSAGE);
            //Creating new consultation object
            Consultation consultation = new Consultation(cDate,cTime,cost,notes,selectedDoctor,p,path,extension);

            //Encrypting notes
            if(!notes.equals("")){
                consultation.encryptNotes();
            }

            //Encrypting images
            if(path!=(null)) {
                consultation.encryptImage();
            }

            new SummaryScreen(consultation);//Opening summary screen
            newFrame.dispose();//Closing this frame
        }
    }

    /**
     * Sets a random doctor that is available at the given date and time
     * Outputs, no available doctor if there are no doctors available doctors for the
     * given day and time
     * @param day A string value representing the day of the week of the consultation
     * @param time A LocalTime value representing the time of the consultation
     */
    public void setRandomDoctor(String day, LocalTime time) {

        ArrayList<Doctor> randomArray;
        randomArray=doctors;

        Collections.shuffle(randomArray);//Shuffling array to ensure randomness
        int doctorFound = -1;
        for (Doctor d : randomArray){
            int checkDateAvailable =-1;
            //Checking if the doctor is available on the given day
            for (int i = 0; i < d.getDays().size(); i++) {
                if (d.getDays().get(i).equalsIgnoreCase(day)) {
                    checkDateAvailable = 1;
                    break;
                }
            }

            //Checking if the doctor who is available on the given day is available ont the given time
            if(checkDateAvailable==1) {
                if(d.getStartTime().isBefore(time) && (d.getEndTime().isAfter(time))) {
                    textArea.append("New random doctor has been set to match consultation date and time\n");
                    textArea.append("Doctor : "+d.getName()+" "+d.getSurname()+"\n");
                    textArea.append("Consultation scheduled !\n\n");
                    selectedDoctor =d;
                    setPrice(cDuration);//Setting the price of the consultation
                    doctorFound = 1;
                    addedDoctorID=d.getMedicalLicenceNumber();
                    checkAvailability.setEnabled(false);//Disabling check availability button
                    complete.setEnabled(true);//Enabling complete consultation button
                    break;
                }
            }
        }

        //If there are no available doctors for the given day and time
        if(doctorFound==-1){
            textArea.append("No doctor available for the given date and time !\n");
            textArea.append("Please try another date and time !\n\n");
        }
    }

    /**
     * Creates a new patient object with the patient details
     */
    public void addPatient(){
        p = new Patient(pName,pSurname,pYear,pMonth,pDay,pMobile,pID);
        if (!checkPatient(pID)) {
            try {
                FileWriter writer = new FileWriter("Patient-Details.txt",true);
                writer.write(p.getPatientId() + "\n");
                writer.close();
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
    }


    /**
     *Checks Patient-Details.txt to see if the patient is an existing patient or a new patient
     * @param pID A String representing the patient ID
     * @return true if the patient exists or false if the patient doesn't exist
     */
    public boolean checkPatient(String pID){
        boolean present = false;

        try{
            File detailsFile = new File("Patient-Details.txt");
            Scanner scanner = new Scanner(detailsFile);
            while(scanner.hasNext()){
                String id = scanner.nextLine();
                if (pID.equals(id)){
                    present=true;
                    break;
                }
            }
        }
        catch (Exception e){
            System.out.println("Error : "+e);
        }
        return present;
    }


    /**
     * Sets the price of the consultation based on the duration and whether it's an existing patient or not
     * @param duration an integer representing the duration of the consultation
     */
    public void setPrice(int duration){

        //For existing patient
        if(checkPatient(pID)){
            cost =(25*duration);
            textArea.append(pID+" existing patient; Consultation Fee : "+pound+"25/hr\n");
            textArea.append("Consultation cost = "+pound+cost);
            addPatient();
        }

        //For new patient
        else{
            cost =(15*duration);
            textArea.append(pID+" new patient; Consultation Fee : "+pound+"15/hr\n");
            textArea.append("Consultation cost = "+pound+cost);
            addPatient();//Adding the new patient
        }
    }
}
