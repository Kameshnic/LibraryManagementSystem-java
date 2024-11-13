

package org.example.miniproject1.Model;

import org.example.miniproject1.Interfaces.EmailProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends User implements EmailProvider {
    private List<Book> personalLibrary;
    private List<Book> readingHistory;
    private List<Review> reviews;
    private int points;


    public Student(String firstName, String lastName, int age, LocalDate dob, String gender, String emailId, long mobileNumber, String username, String password, int points,String location) {
        super(firstName, lastName,"STUDENT",username, password, mobileNumber, emailId, gender, dob, age,location);
        this.personalLibrary = new ArrayList<>();
        this.readingHistory = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.points = points;
    }


    String studentName;
    String address;

    public Student(String studentName, String address) {
        this.studentName=studentName;
        this.address=address;
    }

    public void setPoints(int points) {
        this.points+= points;
    }

    public int getPoints() {
        return this.points;
    }

    @Override
    public String getEmail() {
        return this.getEmailId();
    }

    public int getId() {
        return 0;
    }
}