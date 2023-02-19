import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


/**
 * Consultation class represents a consultation
 * Contains all the fields related to a consultation
 *
 * @author Khaaled Farooq
 * IIT ID - 20210370
 * UOW ID - 18671159
 */
public class Consultation {

    private LocalDate date; // Consultation Date
    private LocalTime time; // Consultation Time
    private LocalDateTime dateTime; // Combination of Consultation date and time
    private double cost; // Cost of the consultation
    private String notes; // Additional notes entered by the user
    private Doctor doctor; // A doctor object to represent the consultation doctor
    private Patient patient; // A patient object to represent the patient of the consultation
    private Encryptor e = new Encryptor(); // new Encryptor object to encrypt user notes
    private ImageEncryptor i = new ImageEncryptor(); // new Image encryptor object to encrypt user uploaded image
    private String path; // Path of the user uploaded image in String
    String extension; //Extension of the user uploaded image in String Eg: PNG, JPG ...


    /**
     * Constructor
     *
     * @param date Consultation Date
     * @param time Consultation Time
     * @param cost A double containing the cost of the consultation
     * @param notes A string containing additional notes entered by the user
     * @param doctor Doctor object allocated for the consultation
     * @param patient Patient object for the consultation
     * @param path A String containing the Path of the user uploaded image
     * @param extension A String containing the extension of the user uploaded image in String
     */
    public Consultation(LocalDate date, LocalTime time,double cost, String notes, Doctor doctor, Patient patient, String path, String extension){
        this.date = date;
        this.time = time;
        this.cost = cost;
        this.notes = notes;
        this.doctor = doctor;
        this.patient = patient;
        this.path = path;
        this.extension = extension;
    }


    /**
     * Returns the consultation date
     * @return LocalDate containing the consultation date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the consultation date
     * @param date A valid date in LocalDate format
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns the consultation time
     * @return LocalTime containing the consultation time
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Sets the consultation time
     * @param time A valid time in LocalTime format
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Returns the Date and Time of the consultation
     * @return LocalDateTime containing the consultation date and time
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets the date and time of the consultation
     * @param dateTime A valid date and time in LocalDateTime format
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns the consultation cost
     * @return A double value containing the cost of the consultation
     */
    public double getCost() {
        return cost;
    }

    /**
     * Sets the cost of the consultation
     * @param cost A double value containing the consultation cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Returns patient notes
     * @return A string containing the additional notes entered by the patient
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets notes
     * @param notes A string containing patient notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Returns the doctor allocated for the consultation
     * @return A doctor object containing the doctor allocated for the consultation
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Sets the doctor for the consultation
     * @param doctor A doctor object containing the doctor allocated for the consultation
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Returns the patient that made the consultation
     * @return A patient object containing the patient that made the consultation
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets the patient
     * @param patient A patient object containing the patient who made the consultation
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Returns the path of the user uploaded image
     * @return A string containing the path of the image uploaded by the user
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the path of the image uploaded by the user
     * @param path A string containing the path of the image uploaded by the user
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Returns the extension of the user uploaded image
     * @return A string containing the path of the image uploaded by the user
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Sets the extension of the user uploaded image
     * @param extension A string containing the path of the image uploaded by the user
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Encrypts the user entered additional notes and stores it in to the notes variable
     */
    void encryptNotes(){
        this.notes=e.encrypt(notes);
    }

    /**
     * Decrypted the already encrypted user notes
     */
    void decryptNotes(){
        this.notes=e.decrypt();
    }

    /**
     * Encrypts the user uploaded image
     */
    void encryptImage(){
        i.EncryptImage(path,extension,patient.getPatientId());
    }


    /**
     * Decrypts the already encrypted user uploaded image
     */
    void decryptImage(){
        i.decryptImage();
    }
}
