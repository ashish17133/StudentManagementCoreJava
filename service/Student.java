package service;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Student {
    String name;
    String age;
    Date dob;

    public Student(String name, String age, Date dob) {
        this.name = name;
        this.age = age;
        this.dob = dob;
    }

    public Student(String name, String age, String dob) {
        this.name = name;
        this.age = age;
        Date tempDate;
        try {
            tempDate = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
            this.dob = tempDate;
        } catch (Exception e) {
            System.out.println("Error in date conversion");

            // TODO: handle exception
        }

    };
}
