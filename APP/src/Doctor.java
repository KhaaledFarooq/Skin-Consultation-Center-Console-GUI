import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


/**
 * Doctor Class
 *
 * Doctor class represents a doctor
 * Contains all the fields related to a doctor
 *
 * @author Khaaled Farooq
 * IIT ID - 20210370
 * UOW ID - 18671159
 */
public class Doctor extends Person{
    private String medicalLicenceNumber; // A String containing doctor medical licence number
    private String specialisation; // A String containing doctor specialization
    private ArrayList<String> days; // A String ArrayList containing doctor available days
    private LocalTime startTime; // A LocalTime containing the start time of a doctors shift
    private LocalTime endTime; // A  LocalTime containing the end time of a doctors shift

    /**
     * Constructor
     *
     * @param name A String containing doctors name
     * @param surname A String containing doctors surname
     * @param dateOfBirth A LocalDate containing doctors date of birth
     * @param mobileNumber A String containing doctors mobile number
     * @param medicalLicenceNumber A String containing doctors medical licence number
     * @param specialisation A String containing doctors specialisation
     * @param days A String ArrayList containing doctor available days
     * @param startTime A LocalTime containing doctor shift start time
     * @param endTime A LocalTime containing doctor shift end time
     */
    public Doctor(String name, String surname, LocalDate dateOfBirth, String mobileNumber, String medicalLicenceNumber, String specialisation,ArrayList<String> days,LocalTime startTime,LocalTime endTime) {
        super(name, surname, dateOfBirth, mobileNumber); //Calling parent class constructor
        this.medicalLicenceNumber = medicalLicenceNumber;
        this.specialisation = specialisation;
        this.days=days;
        this.startTime=startTime;
        this.endTime=endTime;
    }

    /**
     * Returns the doctors licence number
     * @return A string containing the doctors licence number
     */
    public String getMedicalLicenceNumber() {
        return medicalLicenceNumber;
    }

    /**
     * Sets the licence number of a doctor
     * @param medicalLicenceNumber A string containing the doctors licence number
     */
    public void setMedicalLicenceNumber(String medicalLicenceNumber) {
        this.medicalLicenceNumber = medicalLicenceNumber;
    }

    /**
     * Returns the doctors specialization
     * @return A string containing the doctors specialisation
     */
    public String getSpecialisation() {
        return specialisation;
    }

    /**
     * Sets a doctors specialisation
     * @param specialisation A string containing the doctors specialisation
     */
    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    /**
     * Returns doctor available days in as a String ArrayList
     * @return a String ArrayList containing doctor available days
     */
    public ArrayList<String> getDays() {
        return days;
    }

    /**
     * Sets doctor available days as a String ArrayList
     * @param days a String ArrayList containing doctor available days
     */
    public void setDays(ArrayList<String> days) {
        this.days = days;
    }

    /**
     * Returns doctor shift start time
     * @return A LocalTime containing doctor shift start time
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Sets doctor shift start time
     * @param startTime A LocalTime containing doctor shift start time
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Returns doctor shift end time
     * @return A LocalTime containing doctor shift end time
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Sets doctor shift start time
     * @param endTime  LocalTime containing doctor shift end time
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Prints doctor details
     */
    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Licence Number :"+medicalLicenceNumber);
        System.out.println("Specialisation :"+specialisation);
    }
}
