import java.util.Comparator;


/**
 * DoctorListComparator Class
 * Used to sort the Doctors by surname
 *
 * @author Khaaled Farooq
 * IIT ID - 20210370
 * UOW ID - 18671159
 */
public class DoctorListComparator implements Comparator<Doctor> {
    /**
     * Compares the surname of two doctor objects
     *
     * @param d1 First Doctor Object
     * @param d2 Second Doctor Object
     * @return An int value: 0 if the string is equal to the other string.
     *                       < 0 if the string1 comes before string2 in alphabetical order
     *                       > 0 if the string1 comes after string2 in alphabetical order
     */
    @Override
    public int compare(Doctor d1, Doctor d2) {
        return d1.getSurname().compareTo(d2.getSurname());
    }
}
