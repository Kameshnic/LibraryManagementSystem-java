package org.example.miniproject1.Service;

import org.example.miniproject1.Model.Book;
import org.example.miniproject1.Model.Quiz;
import org.example.miniproject1.Model.Review;
import org.example.miniproject1.Model.Student;
import org.example.miniproject1.Util.DBConnection;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class StudentService {
    private final Connection connection;
    Scanner scanner=new Scanner(System.in);
    public StudentService() throws SQLException {
        connection = DBConnection.getConnection();
    }


    public List<Student> getAllStudents() throws SQLException {
        String sql = "SELECT * FROM students";
        List<Student> students = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getString("gender"),
                        rs.getString("emailId"),
                        rs.getLong("mobile"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("points"),
                        rs.getString("location")
                );
                students.add(student);
            }
        }
        return students;
    }

    public boolean registerStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (firstName, lastName, age, dob, gender, emailId, mobile, username, password, points,location,role) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,'STUDENTS')";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setInt(3, student.getAge());
            pstmt.setDate(4, Date.valueOf(student.getDob()));
            pstmt.setString(5, student.getGender());
            pstmt.setString(6, student.getEmailId());
            pstmt.setLong(7, student.getMobile());
            pstmt.setString(8, student.getUsername());
            pstmt.setString(9, student.getPassword());
            pstmt.setInt(10, student.getPoints());
            pstmt.setString(11, student.getLocation());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public List<Quiz> MachineLearningQuiz() throws SQLException {
        List<Quiz> listOfQuiz = new ArrayList<>();
        String sql = "SELECT * FROM ML ORDER BY RAND() LIMIT ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, 10);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setQuestion_text(rs.getString("question_text"));
                quiz.setOptionA(rs.getString("optionA"));
                quiz.setOptionB(rs.getString("optionB"));
                quiz.setOptionC(rs.getString("optionC"));
                quiz.setOptionD(rs.getString("optionD"));
                quiz.setCorrect_answer(rs.getString("correct_answer"));
                listOfQuiz.add(quiz);
            }
        } catch (SQLException E) {
            System.out.println(E);
        }
        return listOfQuiz;
    }

    public List<Quiz> Algebra() throws SQLException {
        List<Quiz> listOfQuiz = new ArrayList<>();
        String sql = "SELECT * FROM Algebra ORDER BY RAND() LIMIT ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, 10);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setQuestion_text(rs.getString("question_text"));
                quiz.setOptionA(rs.getString("optionA"));
                quiz.setOptionB(rs.getString("optionB"));
                quiz.setOptionC(rs.getString("optionC"));
                quiz.setOptionD(rs.getString("optionD"));
                quiz.setCorrect_answer(rs.getString("correct_answer"));
                listOfQuiz.add(quiz);
            }
        } catch (SQLException E) {
            System.out.println(E);
        }
        return listOfQuiz;
    }

    public List<Quiz> Calculus() throws SQLException {
        List<Quiz> listOfQuiz = new ArrayList<>();
        String sql = "SELECT * FROM Calculus ORDER BY RAND() LIMIT ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, 10);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setQuestion_text(rs.getString("question_text"));
                quiz.setOptionA(rs.getString("optionA"));
                quiz.setOptionB(rs.getString("optionB"));
                quiz.setOptionC(rs.getString("optionC"));
                quiz.setOptionD(rs.getString("optionD"));
                quiz.setCorrect_answer(rs.getString("correct_answer"));
                listOfQuiz.add(quiz);
            }
        } catch (SQLException E) {
            System.out.println(E);
        }
        return listOfQuiz;
    }

    public boolean login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM students WHERE username = ? AND password = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }
    public Student getStudentByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM students WHERE username = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getInt("age"),
                            rs.getDate("dob").toLocalDate(),
                            rs.getString("gender"),
                            rs.getString("emailId"),
                            rs.getLong("mobile"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getInt("points"),
                            rs.getString("location")
                    );
                }
            }
        }
        return null;
    }
    public List<Book> getByPoints() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY pointsRequired DESC";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                books.add(new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getInt("isPremium"),
                        resultSet.getString("domain"),
                        resultSet.getString("shortSummary"),
                        resultSet.getString("longSummary"),
                        resultSet.getInt("pointsRequired"),
                        resultSet.getString("filePath"),
                        resultSet.getInt("copies")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public List<Book> getByLikes() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY likes DESC";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                books.add(new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getInt("isPremium"),
                        resultSet.getString("domain"),
                        resultSet.getString("shortSummary"),
                        resultSet.getString("longSummary"),
                        resultSet.getInt("pointsRequired"),
                        resultSet.getString("filePath"),
                        resultSet.getInt("copies"),
                        resultSet.getInt("likes")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> getByAuthor(String authorName) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE author = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, authorName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    books.add(new Book(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getInt("isPremium"),
                            resultSet.getString("domain"),
                            resultSet.getString("shortSummary"),
                            resultSet.getString("longSummary"),
                            resultSet.getInt("pointsRequired"),
                            resultSet.getString("filePath"),
                            resultSet.getInt("copies")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                books.add(new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getInt("isPremium"),
                        resultSet.getString("domain"),
                        resultSet.getString("shortSummary"),
                        resultSet.getString("longSummary"),
                        resultSet.getInt("pointsRequired"),
                        resultSet.getString("filePath"),
                        resultSet.getInt("copies")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public Map<String,List<Book>> getBooksCategorized(){
        Map<String,List<Book>>categorizedBooks=new HashMap<>();
        String sql="select * from books";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String category = resultSet.getString("category");
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getInt("isPremium"),
                        resultSet.getString("domain"),
                        resultSet.getString("shortSummary"),
                        resultSet.getString("longSummary"),
                        resultSet.getInt("pointsRequired"),
                        resultSet.getString("filePath"),
                        resultSet.getInt("copies"),
                        resultSet.getInt("genre_id"),
                        resultSet.getInt("likes")
                );
                if (!categorizedBooks.containsKey(category)) {
                    categorizedBooks.put(category, new ArrayList<>());
                }
                categorizedBooks.get(category).add(book);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorizedBooks;
    }
    public List<Book> getBooksByCategory(String category) {
        Map<String,List<Book>>getBooksCategorised=getBooksCategorized();
        return getBooksCategorised.get(category);
    }


    public Boolean addReview(String username,String title) throws SQLException {
        String sqlCheckPurchase = "SELECT * FROM bought_books WHERE book_title = ? AND username = ?";
        boolean hasPurchased = false;
        try (PreparedStatement psCheck = connection.prepareStatement(sqlCheckPurchase)) {
            psCheck.setString(1, title);
            psCheck.setString(2, username);
            ResultSet rs = psCheck.executeQuery();
            hasPurchased = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!hasPurchased) {
            System.out.println("You must purchase the book before you can review it.");
            return false;
        }
        Scanner scanner=new Scanner(System.in);
        System.out.print("Review Content: ");
        String reviewContent = scanner.nextLine();
        System.out.print("Rating (1-5): ");
        int rating = scanner.nextInt();
        Review review = new Review(username,reviewContent, rating,title);
        String sql = "INSERT INTO review (reviewer_name, book_title, review_text, rating) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, review.getReviewerName());
            ps.setString(2, review.getBookTitle());
            ps.setString(3, review.getReviewText());
            ps.setInt(4, review.getRating());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void likeBook(String title) throws SQLException {
        String sql = "UPDATE books SET likes = likes + 1 WHERE title = ?";
        String selectSql = "SELECT likes FROM books WHERE title = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             PreparedStatement selectLike = connection.prepareStatement(selectSql)) {
            ps.setString(1, title);
            ps.executeUpdate();
            selectLike.setString(1, title);
            ResultSet rs = selectLike.executeQuery();
            if (rs.next()) {
                int newLikes = rs.getInt("likes");
                System.out.println("Book \"" + title + "\" now has " + newLikes + " likes.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Review> getReviews(String title) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM review WHERE book_title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reviews.add(new Review(
                        resultSet.getString("reviewer_name"),
                        resultSet.getString("review_text"),
                        resultSet.getInt("rating"),
                        resultSet.getString("book_title")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;

    }

    public void updatePoints(String username, int points) throws SQLException {
        String sql = "UPDATE students SET points=points + ? WHERE username=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, points);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (Exception E) {
            System.out.println(E);
        }
    }
    public boolean buyBooks(String username, String title) throws SQLException {
        String sql = "SELECT * FROM books WHERE title=?";
        int requiredPoints = 0, copies = 0;
        String authorUsername = "";
        String content="";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, title);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                requiredPoints = rs.getInt("pointsRequired");
                copies = rs.getInt("copies");
                authorUsername = rs.getString("author");
                content=rs.getString("content");
            } else {
                System.out.println("Invalid Book Title");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        if (copies <= 0) {
            System.out.println("No copies available for this book.");
            return false;
        }

        String sqlStudent = "SELECT points FROM students WHERE username = ?";
        int points = 0;

        try (PreparedStatement pstmt = connection.prepareStatement(sqlStudent)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    points = rs.getInt("points");
                } else {
                    System.out.println("Invalid username.");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        if (points >= requiredPoints) {
            String sqlUpdateCopies = "UPDATE books SET copies = copies - 1 WHERE title = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateCopies)) {
                preparedStatement.setString(1, title);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            String sqlUpdatePoints = "UPDATE students SET points = points - ? WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdatePoints)) {
                preparedStatement.setInt(1, requiredPoints);
                preparedStatement.setString(2, username);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            String sqlPurchase = "INSERT INTO bought_books (username, book_title, points_spent) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlPurchase)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, title);
                preparedStatement.setInt(3, requiredPoints);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            String sqlAuthor = "UPDATE author SET points = points + ? WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlAuthor)) {
                preparedStatement.setInt(1, requiredPoints);
                preparedStatement.setString(2, authorUsername);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\"+title + ".txt"))) {
                writer.write(content);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            System.out.println("Book purchased successfully and name of the file is "+title);
            return true;
        } else {
            System.out.println("Insufficient points for this purchase.");
            return false;
        }
    }


    public List<Student> getLeaderboard(int topN) throws SQLException {
        List<Student> leaderboard = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY points DESC LIMIT ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, topN);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student(
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getInt("age"),
                            rs.getDate("dob").toLocalDate(),
                            rs.getString("gender"),
                            rs.getString("emailId"),
                            rs.getLong("mobile"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getInt("points"),
                            rs.getString("location")
                    );
                    leaderboard.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaderboard;
    }

    public Boolean borrowBooks(String title, String username) {
        String sql = "SELECT * FROM books WHERE title=?";
        int copies = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, title);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                copies = rs.getInt("copies");
            } else {
                System.out.println("Invalid Book Title");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        if (copies <= 0) {
            System.out.println("No copies available for this book.");
            return false;
        }


        String checkBorrowedSql = "SELECT * FROM borrowed_books WHERE username=? AND book_title=?";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkBorrowedSql)) {
            checkStmt.setString(1, username);
            checkStmt.setString(2, title);
            ResultSet checkRs = checkStmt.executeQuery();
            int isReturned=1;
            if(checkRs.next()) {
                isReturned = checkRs.getInt("returned");
            }
            if (checkRs.next() && isReturned==0) {
                System.out.println("You have already borrowed this book.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        String sqlBorrow = "INSERT INTO borrowed_books (username, book_title, borrow_date, expiry_date) VALUES (?, ?, ?, ?)";
        LocalDate borrowDate = LocalDate.now();
        LocalDate expiryDate = borrowDate.plusDays(14);
        System.out.println("You must return the book before " + expiryDate);
        String sqlUpdateCopies = "UPDATE books SET copies = copies - 1 WHERE title = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateCopies)) {
            preparedStatement.setString(1, title);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlBorrow)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, title);
            preparedStatement.setDate(3, Date.valueOf(borrowDate));
            preparedStatement.setDate(4, Date.valueOf(expiryDate));
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void calculateFine(String username) {
        int totalFine = 0;
        String checkBorrowedSql = "SELECT * FROM borrowed_books WHERE username=?";
        String title="";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkBorrowedSql)) {
            checkStmt.setString(1, username);
            ResultSet checkRs = checkStmt.executeQuery();
            LocalDate currentDate = LocalDate.now();
            final int finePerDay = 10;
            while (checkRs.next())
            {
                int isReturned=checkRs.getInt("returned");
                title=checkRs.getString("book_title");
                LocalDate expiryDate = checkRs.getDate("expiry_date").toLocalDate();
                if (currentDate.isAfter(expiryDate) &&isReturned==0) {
                    int overdueDays =Period.between(expiryDate, currentDate).getDays();
                    totalFine += overdueDays * finePerDay;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Total fine:"+totalFine);
    }

    private boolean payFine(int totalFine, String username, String title) {
        String sqlStudent = "SELECT points FROM students WHERE username = ?";
        int points = 0;
        try (PreparedStatement pstmt = connection.prepareStatement(sqlStudent)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    points = rs.getInt("points");
                } else {
                    System.out.println("Invalid username.");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        if(points>=totalFine){
            String sqlUpdatePoints = "UPDATE students SET points = points - ? WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdatePoints)) {
                preparedStatement.setInt(1, totalFine);
                preparedStatement.setString(2, username);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            String sqlGetAuthor = "SELECT author FROM books WHERE title = ?";
            String author;
            try (PreparedStatement pstmt = connection.prepareStatement(sqlGetAuthor)) {
                pstmt.setString(1, title);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        author = rs.getString("author");
                    } else {
                        System.out.println("Book not found.");
                        return false;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

            String sqlUpdateAuthorPoints = "UPDATE author SET points = points + ? WHERE username = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sqlUpdateAuthorPoints)) {
                pstmt.setInt(1, totalFine);
                pstmt.setString(2, author);
                pstmt.executeUpdate();
                System.out.println("Fine paid and author points updated successfully.");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Insuffienct Points,play quiz and earn the points");
        }
        return true;

    }


    public void generateReport(String username) {
        String userDetailsSql = "SELECT * FROM students WHERE username = ?";
        String purchasedBooksSql = "SELECT book_title, points_spent FROM bought_books WHERE username = ?";
        String reportFileName = "D:\\"+username + "_report.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportFileName))) {
            try (PreparedStatement userDetailsStmt = connection.prepareStatement(userDetailsSql)) {
                userDetailsStmt.setString(1, username);
                ResultSet userDetailsRs = userDetailsStmt.executeQuery();
                if (userDetailsRs.next()) {
                    String userId = userDetailsRs.getString("id");
                    int points = userDetailsRs.getInt("points");
                    writer.write("User ID: " + userId + "\n");
                    writer.write("Username: " + username + "\n");
                    writer.write("Points: " + points + "\n");
                    writer.write("\n--- Purchased Books ---\n");
                } else {
                    System.out.println("User not found.");
                    return;
                }
            }

            try (PreparedStatement purchasedBooksStmt = connection.prepareStatement(purchasedBooksSql)) {
                purchasedBooksStmt.setString(1, username);
                ResultSet purchasedBooksRs = purchasedBooksStmt.executeQuery();
                while (purchasedBooksRs.next()) {
                    String bookTitle = purchasedBooksRs.getString("book_title");
                    int pointsSpent = purchasedBooksRs.getInt("points_spent");
                    writer.write("Book Title: " + bookTitle + ", Points Spent: " + pointsSpent + "\n");
                }
            }
            System.out.println("Report generated successfully: " + reportFileName);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void returnBook(String title, String username) {
        String query = "SELECT * FROM borrowed_books WHERE book_title = ? AND username = ? AND returned = 0";
        boolean isPaid=false;
        try (Connection conn =DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                LocalDate expiryDate = rs.getDate("expiry_date").toLocalDate();
                LocalDate today = LocalDate.now();
                long daysLate = Period.between(expiryDate, today).getDays();;
                if (daysLate > 0) {
                    double fine = daysLate * 10;
                    System.out.println("Fine to be paid: " + fine);
                    isPaid=payFine((int) fine,username,title);
                }
                if(isPaid ||daysLate<=0) {
                    String updateQuery = "UPDATE borrowed_books SET returned = 1 WHERE book_title = ? AND username = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                        updateStmt.setString(1, title);
                        updateStmt.setString(2, username);
                        updateStmt.executeUpdate();
                        String sqlUpdateCopies = "UPDATE books SET copies = copies + 1 WHERE title = ?";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateCopies)) {
                            preparedStatement.setString(1, title);
                            preparedStatement.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                            return;
                        }
                        System.out.println("Book returned successfully.");
                    }
                }
            } else {
                System.out.println("This book was not borrowed by the user or has already been returned.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Quiz> physics() {
        List<Quiz> listOfQuiz = new ArrayList<>();
        String sql = "SELECT * FROM Physics ORDER BY RAND() LIMIT ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, 10);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setQuestion_text(rs.getString("question_text"));
                quiz.setOptionA(rs.getString("optionA"));
                quiz.setOptionB(rs.getString("optionB"));
                quiz.setOptionC(rs.getString("optionC"));
                quiz.setOptionD(rs.getString("optionD"));
                quiz.setCorrect_answer(rs.getString("correct_answer"));
                listOfQuiz.add(quiz);
            }
        } catch (SQLException E) {
            System.out.println(E);
        }
        return listOfQuiz;
    }

    public List<Quiz> chemistry() {
        List<Quiz> listOfQuiz = new ArrayList<>();
        String sql = "SELECT * FROM Chemistry ORDER BY RAND() LIMIT ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, 10);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setQuestion_text(rs.getString("question_text"));
                quiz.setOptionA(rs.getString("optionA"));
                quiz.setOptionB(rs.getString("optionB"));
                quiz.setOptionC(rs.getString("optionC"));
                quiz.setOptionD(rs.getString("optionD"));
                quiz.setCorrect_answer(rs.getString("correct_answer"));
                listOfQuiz.add(quiz);
            }
        } catch (SQLException E) {
            System.out.println(E);
        }
        return listOfQuiz;
    }
}