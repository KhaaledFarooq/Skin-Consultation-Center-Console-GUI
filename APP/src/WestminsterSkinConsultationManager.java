import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * WestminsterSkinConsultationManager Class
 *
 * This class implements a console menu which allows users to ;
 * Add doctors, View Doctors, Delete doctors, Save and load doctors from and to a file,
 * and Open the GUI
 *
 * @author Khaaled Farooq
 * IIT ID - 20210370
 * UOW ID - 18671159
 */
public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    /**
     * Main method
     * @param args an array of command-line arguments
     */
    public static void main(String[] args) {
        ArrayList<Doctor> doctors = new ArrayList<>();
        WestminsterSkinConsultationManager program = new WestminsterSkinConsultationManager();

        Scanner input = new Scanner(System.in);

        boolean quit = false;

        //menu
        while (!quit) {
            printMenu();//print menu
            String choice = input.next();

            //Add a doctor
            if (choice.equalsIgnoreCase("A")) {
                program.addDoctor(doctors);

                //Add another doctor
                while (true) {
                    System.out.println("Add another Doctor ? Enter Y or N");
                    String addAnother = input.next();
                    if (addAnother.equalsIgnoreCase("Y")) {
                        program.addDoctor(doctors);
                    } else if (addAnother.equalsIgnoreCase("N")) {
                        break;
                    } else {
                        System.out.println("Please enter a valid option");
                    }
                }
            }

             //Remove a Doctor
             if (choice.equalsIgnoreCase("D")){
                program.removeDoctor(doctors);

                //Remove another doctor
                while (true){
                    System.out.println("Delete another Doctor ? Enter Y or N");
                    String deleteAnother = input.next();
                    if (deleteAnother.equalsIgnoreCase("Y")) {
                        program.removeDoctor(doctors);
                    }
                    else if (deleteAnother.equalsIgnoreCase("N")){
                        break;
                    }
                    else{
                        System.out.println("Please enter a valid option");
                    }
                }
            }

            //View Doctors
            else if (choice.equalsIgnoreCase("V")){
                program.viewDoctorTable(doctors);
            }

            //Write to File
             else if (choice.equalsIgnoreCase("W")){
                 program.writeToFile(doctors);
             }

             //Load to File
             else if (choice.equalsIgnoreCase("L")){
                 program.loadFromFile(doctors);
             }

             //Load GUI
             else if (choice.equalsIgnoreCase("G")){
                 program.loadGUI(doctors);
             }

             //Quit
             else if (choice.equalsIgnoreCase("Q"))
             {
                quit = true;
             }

             //Invalid Choice
             else
             {
                System.out.println("Please enter a valid menu option ");
             }
        }
        System.out.println("Thank you for using the program!");

    }

    /**
     * Prints menu to console
     */
    public static void printMenu() {
        System.out.println();
        System.out.println("Select a menu option");
        System.out.println();
        System.out.println("A : Add New Doctor");
        System.out.println("D : Delete Doctor");
        System.out.println("V : View List of Doctors");
        System.out.println("W : Write to file");
        System.out.println("L : Load from file");
        System.out.println("G : Open GUI");
        System.out.println("Q : Quit program");
        System.out.println();
    }


    /**
     * Method to add doctor to ArrayList
     * @param doctors An ArrayList of Doctor Objects
     */
    @Override
    public void addDoctor(ArrayList<Doctor> doctors) {
        Scanner input = new Scanner(System.in);

        //Max 10 Doctors
        if (doctors.size()<10) {

            //Doctor Name
            System.out.println("Enter Doctor Name : ");
            String name = input.next();

            //Doctor Surname
            System.out.println("Enter Doctor surname : ");
            String surname = input.next();

            //Doctor DOB
            LocalDate dob;
            while (true) {
                try {
                    System.out.println("Enter date of birth of Doctor : ");
                    System.out.println("Please use the following format Year-Month-Day ");
                    System.out.println("Eg: 1975-02-25");
                    String date = input.next();
                    dob = LocalDate.parse(date);
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid Date ! ");
                    System.out.println("Please enter a valid date according to the given format");
                    input.nextLine();
                }
            }

            //Doctor Mobile Number
            System.out.println("Enter Doctor Mobile Number : ");
            String mNum = input.next();

            //Doctor licence
            System.out.println("Enter Doctor Medical Licence Number : ");
            String medicalLicenceNumber = input.next();

            //Doctor Specialisation
            System.out.println("Enter Doctor specialization : ");
            String specialisation = input.next();

            //7 days of the week
            String[] day = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};

            //Doctor available days
            ArrayList<String> dates = new ArrayList<>();
            while (true){
                while (true) {
                    int check = -1;
                    System.out.println("Enter Doctor Available days : ");
                    System.out.println("Enter one day at a time");
                    System.out.println("Eg: MONDAY");
                    String date = input.next();
                    date = date.toUpperCase();

                    for (String s : day) {
                        if (s.equalsIgnoreCase(date)) {
                            check = 1;
                            break;
                        }
                    }
                    if (check == 1) {
                        if (dates.isEmpty()) {
                            dates.add(date);
                            System.out.println("Day Added !");
                            break;
                        } else {
                            int present = dates.indexOf(date);
                            if (present == -1) {
                                dates.add(date.toUpperCase());
                                System.out.println("Day Added");
                                System.out.println();
                                break;
                            } else {
                                System.out.println("Day Already Exists");
                                System.out.println();
                            }
                        }
                    } else {
                        System.out.println("Incorrect Day !");
                        System.out.println();
                    }
                }

                //Add another day
                System.out.println("Add another Day ? Enter Y or N");
                String addAnother = input.next();
                if (addAnother.equalsIgnoreCase("Y")) {
                    System.out.println();
                } else if (addAnother.equalsIgnoreCase("N")) {
                    break;
                } else {
                    System.out.println("Please enter a valid option");
                    System.out.println();
                }
            }

            //Doctor available time
            LocalTime startTime;
            LocalTime endTime;
            while(true){
                System.out.println("Doctor time slot");
                //Start time
                while (true) {
                    try {
                        System.out.println("Enter start time : ");
                        System.out.println("Please use 24-Hour Time Format");
                        System.out.println("Eg: 08:30 ");
                        String sTime = input.next();
                        startTime = LocalTime.parse(sTime);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid Time ! ");
                        System.out.println("Please enter a valid time according to the given format");
                        input.nextLine();
                    }
                }

                //end time
                while (true) {
                    try {
                        System.out.println("Enter end time : ");
                        System.out.println("Please use 24-Hour Time Format");
                        System.out.println("Eg: 08:30 ");
                        String eTime = input.next();
                        endTime = LocalTime.parse(eTime);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid Time ! ");
                        System.out.println("Please enter a valid time according to the given format");
                        input.nextLine();
                    }
                }
                if(endTime.isBefore(startTime)){
                    System.out.println("Starting time cannot be before end time !");
                }
                else{
                    break;
                }
            }

            //Adding doctor
            Doctor newDoctor = new Doctor(name, surname, dob, mNum, medicalLicenceNumber, specialisation, dates, startTime, endTime);
            doctors.add(newDoctor);

            System.out.println("Doctor Successfully Added");
        }

        else{
            System.out.println("The center has reached it's maximum capacity of 10 Doctors!");
            System.out.println("Delete an existing Doctor to add a new Doctor");
        }

    }

    /**
     * Method to remove a doctor from ArrayList
     * @param doctors An ArrayList of Doctor Objects
     */
    @Override
    public void removeDoctor(ArrayList<Doctor> doctors) {
        Scanner input = new Scanner(System.in);

        if(doctors.size()>0){
            System.out.println("Please enter the Medical Licence Number of the Doctor that you wish to delete ");
            String licenceNum = input.next();
            int index = -1;

            for(int i = 0; i<doctors.size(); i++ ){
                Doctor doctor = doctors.get(i);
                if(licenceNum.equals(doctor.getMedicalLicenceNumber())){
                    index = i;
                    break;
                }
            }

            if(index == -1){
                System.out.println("Doctor with the licence number : "+licenceNum+" was not found !");
                System.out.println("Total Doctors in the center : "+doctors.size());
            }
            else{
                Doctor doctor = doctors.get(index);
                System.out.println("Total Doctors in the center : "+doctors.size());
                System.out.println("Doctor Found : ");
                doctor.printDetails();
                System.out.println();
                System.out.println("Delete Doctor? Enter Y or N ");
                String confirm = input.next();
                if (confirm.equalsIgnoreCase("Y")){
                    doctors.remove(index);
                    System.out.println("Delete Successful !");
                    System.out.println();
                    doctor.printDetails();
                    System.out.println();
                    System.out.println("The above doctor has been Deleted");
                    System.out.println("Total Doctors in the center : "+doctors.size());
                }
                else if (confirm.equalsIgnoreCase("N")){
                    System.out.println("Doctor Not Deleted !");
                    System.out.println("Total Doctors in the center : "+doctors.size());
                }
                else {
                    System.out.println("Please enter a valid option");
                }
            }
        }
        //If doctor list empty
        else{
            System.out.println("No Doctors Found");
            System.out.println("Please add a Doctor to be able to a delete Doctor");
        }
    }

    /**
     * Method to view added doctors and doctor details
     * @param doctors An ArrayList of Doctor Objects
     */
    @Override
    public void viewDoctorTable(ArrayList<Doctor> doctors) {
        if (doctors.size()==0){
            System.out.println("Doctor List empty !");
            System.out.println("Please add doctors to view details");
        }
        else{
            Collections.sort(doctors,new DoctorListComparator());
            System.out.println();
            System.out.println("----------------------------------------------------------------------------------------------------------");
            System.out.printf("%60s","DOCTOR DETAILS \n");
            System.out.println("----------------------------------------------------------------------------------------------------------");
            System.out.printf(" %10s %10s %18s %20s %15s %23s","Firstname","Surname","Date-Of-Birth","Mobile Number","Licence","Specialisation\n");
            System.out.println("----------------------------------------------------------------------------------------------------------");
            for(Doctor d : doctors){
                System.out.printf("%1s %-12s %-12s %-20s %-21s %-15s %-15s"," ",d.getName(),d.getSurname(),d.getDateOfBirth(),d.getMobileNumber(),d.getMedicalLicenceNumber(),d.getSpecialisation());
                System.out.println();
            }
            System.out.println("----------------------------------------------------------------------------------------------------------");
        }
    }

    /**
     * Method to write doctor and doctor details to a file
     * @param doctors An ArrayList of Doctor Objects
     */
    @Override
    public void writeToFile(ArrayList<Doctor> doctors) {
        try {
            FileWriter writer = new FileWriter("Doctor-Details.txt");

            for(Doctor d : doctors){
                writer.write(d.getName()+"\n");
                writer.write(d.getSurname()+"\n");
                writer.write(d.getDateOfBirth()+"\n");
                writer.write(d.getMobileNumber()+"\n");
                writer.write(d.getMedicalLicenceNumber()+"\n");
                writer.write(d.getSpecialisation()+"\n");
                writer.write(d.getStartTime()+"\n");
                writer.write(d.getEndTime()+"\n");
                for(String s : d.getDays()){
                    writer.write(s+"\n");
                }
                writer.write("end\n");//to know the end of one doctor object during reading
            }
            writer.close();
            System.out.println("Data Successfully Stored in to file !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to load doctor and doctor details from a file
     * @param doctors An ArrayList of Doctor Objects
     */
    @Override
    public void loadFromFile(ArrayList<Doctor> doctors) {

        int line = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Doctor-Details.txt"));
            while (reader.readLine() != null) line++;
            reader.close();
        } catch (IOException error) {
            System.out.println("Error : " + error);
        }

        if (line == 0) {
            System.out.println("No Stored Data Found");
        }
        else {
            doctors.removeAll(doctors);
            try {
                File dataFile = new File("Doctor-Details.txt");
                Scanner scanner = new Scanner(dataFile);
                while (scanner.hasNext()){
                    String name = scanner.nextLine();
                    String surname = scanner.nextLine();
                    String date = scanner.nextLine();
                    String num = scanner.nextLine();
                    String licence = scanner.nextLine();
                    String sp = scanner.nextLine();
                    String startTime = scanner.nextLine();
                    String endTime = scanner.nextLine();
                    ArrayList<String> day = new ArrayList<>();
                    while (true){
                        String test = scanner.nextLine();
                        if(test.equals("end")){
                            break;
                        }
                        else{
                            day.add(test);
                        }
                    }
                    Doctor d = new Doctor(name, surname, LocalDate.parse(date), num, licence, sp,day,LocalTime.parse(startTime),LocalTime.parse(endTime));
                    doctors.add(d);
                }
                System.out.println("File Loaded Successfully !");
            } catch (IOException error)//if file not found
            {
                System.out.println("Error : " + error);
            }
        }
    }

    /**
     * Method to load the GUI
     * @param doctors An ArrayList of Doctor Objects
     */
    @Override
    public void loadGUI(ArrayList<Doctor> doctors) {
        if (doctors.size()>=1){
            new LoadScreen(doctors);
        }
        else{
            System.out.println("No doctors found !");
            System.out.println("Please add doctors before running the GUI");
        }
    }
}

