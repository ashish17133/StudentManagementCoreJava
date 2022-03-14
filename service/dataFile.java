package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

public class dataFile {

    public static boolean createFile(Student student) {
        try {
            final File fs = new File("student.txt");
            if (fs.createNewFile()) {
                System.out.println("File created with name:");

            } else {
                System.out.println("File already exist");

            }
            ;
            FileWriter fw = new FileWriter(fs, true);
            BufferedWriter fbw = new BufferedWriter(fw);
            FileReader frw = new FileReader(fs);
            BufferedReader fbrw = new BufferedReader(frw);
            String data = "";
            String tempData = " ";
            while (tempData != null && !tempData.isEmpty()) {
                if (((tempData = fbrw.readLine()) == null)) {
                    break;

                }
                data = tempData;
            }
            String[] _tempData = data.split(",");
            System.out.println(data);
            int studentId = (Integer.parseInt(_tempData[0]) == 0) ? 0 : Integer.parseInt(_tempData[0]) + 1;
            ;
            fbw.write(studentId + "," + student.name + "," + student.age + "," + student.dob + "\n");
            fbw.close();

            System.out.println("File write successful");
            return true;
        } catch (Exception e) {
            System.out.println("Error occured:" + e);
            // TODO: handle exception
            return false;
        }
    }

    public static ArrayList readFile() {
        final File fs = new File("student.txt");
        ArrayList error = new ArrayList<String>(Arrays.asList("Error"));
        try {
            FileReader fwr = new FileReader(fs);
            BufferedReader fbwr = new BufferedReader(fwr);
            String tempData = " ";
            ArrayList<String> data = new ArrayList<String>();

            while (tempData != null && !tempData.isEmpty()) {
                if (((tempData = fbwr.readLine()) == null)) {
                    break;
                }
                ArrayList<String> _value = new ArrayList<String>();
                _value = toMap(tempData);
                data.add(_value.toString());
            }
            return data;

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error in reading file");
        }
        ;
        return error;

    }

    public static ArrayList<String> readSingleStudent(String id) {
        File fs = new File("student.txt");
        ArrayList<String> error = new ArrayList<String>();
        System.out.println("Checking student id of" + id);
        try {
            FileReader fwr = new FileReader(fs);
            BufferedReader fbwr = new BufferedReader(fwr);
            ArrayList<String> data = new ArrayList<String>();
            String tempData = " ";
            while ((tempData = fbwr.readLine()) != null && !tempData.isEmpty()) {
                if (tempData == null) {
                    break;
                }
                String[] _tempvalue = tempData.split(",");
                if (_tempvalue[0].equals(id)) {
                    data.add(toMap(tempData).toString());
                    break;
                }
            }
            return data;
        } catch (Exception e) {
            System.out.println("Error in reading single id");
            // TODO: handle exception
        }
        return error;
    }

    public static void update(String id, Student s1) {
        String[] data;
        String tempData = " ";
        File fs = new File("student.txt");

        try {
            FileReader fsr = new FileReader(fs);
            BufferedReader fbsr = new BufferedReader(fsr);
            FileWriter fsw = new FileWriter(fs, true);
            BufferedWriter fbsw = new BufferedWriter(fsw);

            while ((tempData = fbsr.readLine()) != null && !tempData.isEmpty()) {
                if (tempData == null) {
                    break;
                }
                ;
                String[] _tempValu = tempData.split(",");
                if (_tempValu[0].equals(id)) {

                    data = _tempValu;
                    s1.name = (s1.name == null || s1.name.isEmpty()) ? data[1] : s1.name;
                    s1.age = (s1.age == null || s1.age.isEmpty()) ? data[2] : s1.age;
                    String date = (s1.dob == null || s1.dob.toString().isEmpty()) ? data[3] : s1.dob.toString();

                    fbsw.write(id + "," + s1.name + "," + s1.age + "," + date + "\n");
                    fbsw.close();
                    break;
                }
            }
            ;

        } catch (IOException e) {
            System.out.println("Writing error" + e);
        } catch (Exception e) {
            System.out.println("Error in updating file" + e);
            // TODO: handle exception
        }

    }

    public static ArrayList<String> toMap(String value) {
        ArrayList<String> _tempData = new ArrayList<String>();
        String[] _listValue = value.split(",");
        _tempData.add(_listValue[0] + "," + "Student name:" + _listValue[1] + "," + "Student Age:" + _listValue[2] + ","
                + "Student dob" + _listValue[3]);
        return _tempData;
    }

}
