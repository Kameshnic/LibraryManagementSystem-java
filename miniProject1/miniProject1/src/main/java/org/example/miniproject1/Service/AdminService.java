package org.example.miniproject1.Service;

import org.example.miniproject1.Model.*;
import org.example.miniproject1.Util.DBConnection;

import java.sql.*;

public class AdminService {
    DBConnection dbConnection=new DBConnection();
    private Connection connection=dbConnection.getConnection();


    public AdminService() throws SQLException {


    }


    public boolean addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (firstName, lastName, age, dob, gender, emailId, mobile, username, password, points, location) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setInt(3, student.getAge());
            pstmt.setObject(4, student.getDob());
            pstmt.setString(5, student.getGender());
            pstmt.setString(6, student.getEmail());
            pstmt.setLong(7, student.getMobile());
            pstmt.setString(8, student.getUsername());
            pstmt.setString(9, student.getPassword());
            pstmt.setInt(10, student.getPoints());
            pstmt.setString(11, student.getLocation());
            return pstmt.executeUpdate() > 0;
        }
    }



    public boolean deleteStudent(String username) throws SQLException {
        String sql = "DELETE FROM students WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            return pstmt.executeUpdate() > 0;
        }
    }






    public boolean addAuthor(Author author) throws SQLException {
        String sql = "INSERT INTO author (firstName, lastName, username, password, age, dob, gender, emailId, mobile, penname, biography, location) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, author.getFirstName());
            pstmt.setString(2, author.getLastName());
            pstmt.setString(3, author.getUsername());
            pstmt.setString(4, author.getPassword());
            pstmt.setInt(5, author.getAge());
            pstmt.setDate(6, Date.valueOf(author.getDob()));
            pstmt.setString(7, author.getGender());
            pstmt.setString(8, author.getEmailId());
            pstmt.setLong(9, author.getMobile());
            pstmt.setString(10, author.getPenName());
            pstmt.setString(11, author.getBiography());
            pstmt.setString(12, author.getLocation());

            return pstmt.executeUpdate() > 0;
        }
    }




    public boolean deleteAuthor(String userName) throws SQLException {
        String sql = "DELETE FROM author WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            return pstmt.executeUpdate() > 0;
        }
    }



    public boolean updateAuthor(Author author) throws SQLException {
        String sql = "UPDATE author SET firstName = ?, lastName = ?, password = ?, age = ?, dob = ?, gender = ?, emailId = ?, mobile = ?, penname = ?, biography = ?, location = ? WHERE username = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, author.getFirstName());
            pstmt.setString(2, author.getLastName());
            pstmt.setString(3, author.getPassword());
            pstmt.setInt(4, author.getAge());
            pstmt.setDate(5, Date.valueOf(author.getDob())); // assuming dob is a LocalDate
            pstmt.setString(6, author.getGender());
            pstmt.setString(7, author.getEmailId());
            pstmt.setLong(8, author.getMobile());
            pstmt.setString(9, author.getPenName());
            pstmt.setString(10, author.getBiography());
            pstmt.setString(11, author.getLocation());
            pstmt.setString(12, author.getUsername());

            return pstmt.executeUpdate() > 0;
        }
    }



    public boolean addBook(Book book) throws SQLException {
        String sql = "INSERT INTO books ( title, author, isPremium, domain, shortSummary, longSummary, pointsRequired, filePath, copies, genre_id, likes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getIsPremium());
            pstmt.setString(4, book.getDomain());
            pstmt.setString(5, book.getShortSummary());
            pstmt.setString(6, book.getLongSummary());
            pstmt.setInt(7, book.getPointsRequired());
            pstmt.setString(8, book.getFilePath());
            pstmt.setInt(9, book.getCopies());
            pstmt.setInt(10, book.getGenreId());
            pstmt.setInt(11, book.getLikes());
            return pstmt.executeUpdate() > 0;
        }
    }



    public boolean deleteBook(String title) throws SQLException {
        String sql = "DELETE FROM books WHERE title = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, title);
            return pstmt.executeUpdate() > 0;
        }
    }



    public boolean addQuiz(Quiz quiz) throws SQLException {
        String sql = "INSERT INTO quizzes (correct_answer, option_a, option_b, option_c, option_d, question_text) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, quiz.getCorrect_answer());
            pstmt.setString(2, quiz.getOptionA());
            pstmt.setString(3, quiz.getOptionB());
            pstmt.setString(4, quiz.getOptionC());
            pstmt.setString(5, quiz.getOptionD());
            pstmt.setString(6, quiz.getQuestion_text());
            return pstmt.executeUpdate() > 0;
        }
    }
    public boolean deleteQuiz(int quizId) throws SQLException {
        String sql = "DELETE FROM quizzes WHERE quiz_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, quizId);
            return pstmt.executeUpdate() > 0;
        }
    }


}
