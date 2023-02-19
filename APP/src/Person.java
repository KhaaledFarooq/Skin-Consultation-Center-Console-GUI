import java.time.LocalDate;

/**
 * Person class
 *
 * Represents a person
 * Contains all the fields related to a person
 *
 * @author Khaaled Farooq
 * IIT ID - 20210370
 * UOW ID - 18671159
 */
public class Person {
    private String name; //A string containing person name
    private String surname; //A string containing person surname
    private LocalDate dateOfBirth; //A LocalDate containing person DOB
    private String mobileNumber; //A string containing person mobile number


    /**
     * Constructor
     *
     * @param name A string containing person name
     * @param surname A string containing person surname
     * @param year An integer containing year of person DOB
     * @param month An integer containing month of person DOB
     * @param day An integer containing day of person DOB
     * @param mobileNumber A string containing person mobile number
     */
    public Person(String name, String surname, int year, int month, int day, String mobileNumber){
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = LocalDate.of(year,month,day);
        this.mobileNumber = mobileNumber;
    }


    /**
     * Alternative constructor
     *
     * @param name A string containing person name
     * @param surname A string containing person surname
     * @param date A LocalDate containing person DOB
     * @param mobileNumber A string containing person mobile number
     */
    public Person(String name, String surname, LocalDate date, String mobileNumber){
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = date;
        this.mobileNumber = mobileNumber;
    }


    /**
     * Return person name
     * @return A string containing person name
     */
    public String getName() {
        return name;
    }

    /**
     * Set person name
     * @param name A string containing person name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return person name
     * @return A string containing person surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set person surname
     * @param surname A string containing person surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Return person DOB
     * @return LocalDate containing person DOB
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Set person DOB
     * @param dateOfBirth LocalDate containing person DOB
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Return person mobile number
     * @return A string containing person mobile number
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Set person mobile number
     * @param mobileNumber A string containing person mobile number
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * Prints person details
     */
    public void printDetails(){
        System.out.println("Name           :"+name+" "+surname);
        System.out.println("Date Of Birth  :"+dateOfBirth);
        System.out.println("Mobile Number  :"+mobileNumber);
    }
}
