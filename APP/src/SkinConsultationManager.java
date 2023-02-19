import java.util.ArrayList;

/**
 * SkinConsultationManager interface
 *
 * @author Khaaled Farooq
 * IIT ID - 20210370
 * UOW ID - 18671159
 */

public interface SkinConsultationManager {
    /**
     * Method to add doctor to ArrayList
     * @param doctors An ArrayList of Doctor Objects
     */
    void addDoctor(ArrayList<Doctor> doctors);

    /**
     * Method to remove a doctor from ArrayList
     * @param doctors An ArrayList of Doctor Objects
     */
    void removeDoctor(ArrayList<Doctor> doctors);

    /**
     * Method to view added doctors and doctor details
     * @param doctors An ArrayList of Doctor Objects
     */
    void viewDoctorTable(ArrayList<Doctor> doctors);

    /**
     * Method to write doctor and doctor details to a file
     * @param doctors An ArrayList of Doctor Objects
     */
    void writeToFile(ArrayList<Doctor> doctors);

    /**
     * Method to load doctor and doctor details from a file
     * @param doctors An ArrayList of Doctor Objects
     */
    void loadFromFile(ArrayList<Doctor> doctors);

    /**
     * Method to load the GUI
     * @param doctors An ArrayList of Doctor Objects
     */
    void loadGUI(ArrayList<Doctor> doctors);
}
