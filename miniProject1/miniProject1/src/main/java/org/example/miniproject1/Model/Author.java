package org.example.miniproject1.Model;

import org.example.miniproject1.Interfaces.EmailProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Author extends User implements EmailProvider
{
    private ArrayList<Book> publishedBooks;
    private ArrayList<Info> readingHistory;
    private String biography;
    private String penName;


    public Author(String firstName, String lastName, String userName, String password,int age, LocalDate dob, String gender, String emailId, long mobileNumber,String penName,String biography,String location) {
        super(firstName,lastName,"Author",userName, password,mobileNumber, emailId, gender, dob, age,location);
        this.publishedBooks = new ArrayList<>();
        this.readingHistory = new ArrayList<>();
        this.penName=penName;
        this.biography=biography;
    }

    //hii da this is for the geo tree okay va ??
    String authorName;
    String address;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Author(String authorName, String address) {
        this.authorName=authorName;
        this.address=address;
    }

    private List<Book> books;
    public Author(String firstName, String lastName, String username, String password, int age,
                  LocalDate dob, String gender, String emailId, long mobile, String penname,
                  String biography, String location, List<Book> books) {
        super(firstName,lastName,"Author",username, password,mobile, emailId, gender, dob, age,location);

        this.penName = penname;
        this.biography = biography;
        this.books = books;
    }

    public ArrayList<Book> getPublishedBooks() {
        return publishedBooks;
    }

    public void setPublishedBooks(ArrayList<Book> publishedBooks) {
        this.publishedBooks = publishedBooks;
    }

    public ArrayList<Info> getReadingHistory() {
        return readingHistory;
    }

    public void setReadingHistory(ArrayList<Info> readingHistory) {
        this.readingHistory = readingHistory;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPenName() {
        return penName;
    }

    public void setPenName(String penName) {
        this.penName = penName;
    }

    @Override
    public String getEmail() {
        return this.getEmailId();
    }
}