/**
 * Patient Class
 *
 * Represents a Patient Object
 * Contains all the fields related to patient
 *
 * @author Khaaled Farooq
 * IIT ID - 20210370
 * UOW ID - 18671159
 */
public class Patient extends Person{
    private String patientId; //Patient ID

    /**
     * Constructor
     *
     * @param name A String containing patient name
     * @param surname A String containing patient surname
     * @param year An int containing patient DOB year
     * @param month An int containing patient DOB month
     * @param day An int containing patient DOB day
     * @param mobileNumber A String containing patient mobile number
     * @param patientId A String containing patient ID
     */
    public Patient(String name, String surname, int year, int month, int day, String mobileNumber, String patientId) {
        super(name, surname, year, month, day, mobileNumber);
        this.patientId = patientId;
    }

    /**
     * Returns patient ID as a String
     * @return A string containing Patient ID
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * Sets the patient ID
     * @param patientId A string containing Patient ID
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    
}
