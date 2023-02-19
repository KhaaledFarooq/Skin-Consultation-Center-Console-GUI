import javax.swing.*;
import java.awt.*;

/**
 * Summary Screen
 *
 * Generates and Displays a summary of the consultation in GUI
 *
 * @author Khaaled Farooq
 * IIT ID - 20210370
 * UOW ID - 18671159
 */
public class SummaryScreen {
    Consultation c; // Consultation Object
    JTextArea textArea; // Text area to add the summary information

    /**
     * Constructor
     * @param c Consultation Object
     */
    public SummaryScreen(Consultation c){
        this.c=c;

        //Gradient Panel
        GradientPanel gradientPanel = new GradientPanel(new Color(0x00c3ff),
                new Color(0xffff1c));
        gradientPanel.setBorder(BorderFactory.createEtchedBorder(5, Color.BLACK, Color.BLACK));

        //Main Frame
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(500,400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(gradientPanel);

        //Frame Icon
        ImageIcon icon = new ImageIcon("iconLogo.png");
        frame.setIconImage(icon.getImage());

        textArea = new JTextArea();
        textArea.setEnabled(false);
        textArea.setFont(new Font("Consolas", Font.BOLD, 14));
        textArea.setDisabledTextColor(new Color(0x000000));
        textArea.setOpaque(false);
        showDetails();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        frame.add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Method to print consultation details to the text area
     */
    public void showDetails(){
        textArea.append("------------------------------------------------------------------\n");
        textArea.append("-----------------------Consultation Details-----------------------\n");
        textArea.append("------------------------------------------------------------------\n");
        textArea.append("Patient ID             : "+c.getPatient().getPatientId()+"\n");

        textArea.append("Patient Name           : "+c.getPatient().getName()+" "+c.getPatient().getSurname()+"\n");
        textArea.append("Patient Date of Birth  : "+c.getPatient().getDateOfBirth()+"\n");
        textArea.append("Patient Mobile Number  : "+c.getPatient().getMobileNumber()+"\n");
        textArea.append("Patient ID             : "+c.getPatient().getPatientId()+"\n"+"\n");

        textArea.append("Doctor Name            : "+c.getDoctor().getName()+" "+c.getDoctor().getSurname()+"\n");
        textArea.append("Doctor Medical Licence : "+c.getDoctor().getMedicalLicenceNumber()+"\n");
        textArea.append("Doctor Specialisation  : "+c.getDoctor().getSpecialisation()+"\n"+"\n");

        textArea.append("Consultation Date      : "+c.getDate().toString() +"\n");
        textArea.append("Consultation Time      : "+c.getTime().toString()+"\n");
        textArea.append("Consultation Cost      : "+c.getCost()+"\n"+"\n");

        textArea.append("Patient Notes          : "+c.getNotes()+"\n");
        textArea.append("Notes have been encrypted to ensure privacy\n\n");

        textArea.append("------------------------------------------------------------------\n");
        textArea.append("----------------------------Thank You!----------------------------\n");
        textArea.append("------------------------------------------------------------------\n");
    }
}
