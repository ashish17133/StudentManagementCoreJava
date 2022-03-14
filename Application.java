import java.text.SimpleDateFormat;
import java.text.NumberFormat.Style;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import service.*;

public class Application {
    public static void main(String[] args) {
        Scanner _input = new Scanner(System.in);
        System.out.println("STUDENT MANAGEMENT SYSTEM");
        System.out.println("Welcome to admin panel");
        System.out.println("Please enter username and password");
        System.out.print("Username:");
        String username = _input.nextLine();
        System.out.print("Password:");
        String password = _input.nextLine();
        auth(username, password);

    }

    public static void auth(String username, String password) {

        if (username.equals("ashish")) {
            if (password.equals("changeme")) {
                managementScrn();
            } else {
                System.out.println("Password incorrect");
            }
        } else {
            System.out.println("Username incorrect");
        }
    }

    public static void managementScrn() {
        Scanner _input = new Scanner(System.in);
        boolean _loopTest = true;
        System.out.println("Student Management Screen");

        System.out.println("1.Manage Student");
        System.out.println("2.Manage Subject");
        System.out.println("3.Manage Exam Grade");
        System.out.println("4.Quit");

        while (_loopTest) {
            System.out.println("Enter your choice: ");
            int _value = _input.nextInt();
            _loopTest = false;
            switch (_value) {
                case 1:
                    mgmtStudent();
                    break;
                case 2:
                    System.out.println("Goto manage Subject");
                    break;
                case 3:
                    System.out.println("Goto manage exam grade");
                    break;
                case 4:
                    System.out.println("Hope to see you soon.....exit");
                    break;

                default:
                    System.out.println("Enter valid Choice");
                    _loopTest = true;
                    break;
            }
        }
    }

    public static void mgmtStudent() {
        boolean _isLoop = true;
        Scanner _input = new Scanner(System.in);
        System.out.println("Welcome to Student Management Screen");
        System.out.println("Please Select your Choice from below menu");
        System.out.println("1.Create Student");
        System.out.println("2.Read Single Student data");
        System.out.println("3.Read All Student Data");
        System.out.println("4.Update Student Info");
        System.out.println("5.Delete Student Data");
        System.out.println("6.Exit");
        while (_isLoop) {
            System.out.print("Enter Choice here: ");
            int _value = _input.nextInt();
            _isLoop = false;
            switch (_value) {
                case 1:
                    createStudent();
                    break;
                case 2:
                    singleData();
                    break;
                case 3:
                    readData();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    System.out.println("Delete Student Data");
                    break;
                case 6:
                    System.out.println("Hope to hear from you soon...exit");
                    break;

                default:
                    System.out.println("please enter a valid choice");
                    _isLoop = true;
                    break;
            }
        }
    }

    public static void createStudent() {
        Scanner _input = new Scanner(System.in);
        Date dobDate = null;

        System.out.print("Name:");
        String name = _input.nextLine();
        System.out.print("age:");
        String age = _input.nextLine();
        System.out.print("dob(dd/MM/yyyy):");
        String dob = _input.nextLine();
        try {
            dobDate = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
            Student _newstudent = new Student(name, age, dob);
            dataFile.createFile(_newstudent);
        } catch (Exception e) {
            System.out.println("Error in parsing date");

            // TODO: handle exception
        }
        System.out.println("name:" + name + ",age:" + age + ",dob:" + dobDate);
    }

    public static void readData() {

        ArrayList<String> data = dataFile.readFile();
        System.out.println(data);

    }

    public static void singleData() {
        Scanner _input = new Scanner(System.in);
        System.out.print("Enter User id: ");
        String id = _input.nextLine();

        ArrayList<String> data = dataFile.readSingleStudent(id);
        System.out.println(data);
    }

    public static void updateStudent() {
        Date date;
        Scanner _input = new Scanner(System.in);
        System.out.print("Student id:");
        String id = _input.nextLine();
        System.out.print("Student Name:");
        String name = _input.nextLine();
        System.out.print("Student Age:");
        String age = _input.nextLine();
        System.out.print("Student Dob:(dd/MM/yyyy) ");
        try {
            String dob = _input.nextLine();
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
            Student s1 = new Student(name, age, date);
            dataFile.update(id, s1);
        } catch (Exception e) {
            System.out.println("Dob conversion Error..Please check date format");
            // TODO: handle exception
        }

        System.out.println("SuccessFull updated");
    }
}
