package org.example.miniproject1.Service;

import org.example.miniproject1.Model.*;
import org.example.miniproject1.Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.example.miniproject1.Model.Author;

public class AuthorService extends StudentService {
    private final Connection connection=DBConnection.getConnection();

    public AuthorService() throws SQLException {

    }
    public List<Book> getBookLikesByAuthor(String username) throws SQLException {
        String sql = "SELECT title, likes FROM books WHERE author = ?";
        List<Book> bookLikesList = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String title = rs.getString("title");
                    int likeCount = rs.getInt("likes");

                    Book bookLikes = new Book(title, likeCount);
                    bookLikesList.add(bookLikes);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookLikesList;
    }


    public boolean registerAuthor(Author author) throws SQLException {
        String sql = "INSERT INTO author (firstName, lastName, age, dob, gender, emailId, mobile, username, password, penname, biography, location, role) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'AUTHOR')";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, author.getFirstName());
            pstmt.setString(2, author.getLastName());
            pstmt.setInt(3, author.getAge());
            pstmt.setDate(4, Date.valueOf(author.getDob()));
            pstmt.setString(5, author.getGender());
            pstmt.setString(6, author.getEmailId());
            pstmt.setLong(7, author.getMobile());
            pstmt.setString(8, author.getUsername());
            pstmt.setString(9, author.getPassword());
            pstmt.setString(10, author.getPenName());
            pstmt.setString(11, author.getBiography());
            pstmt.setString(12, author.getLocation()); // Adding the location parameter

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM author WHERE username = ? AND password = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public List<Author> getAllAuthors() throws SQLException {
        String sql = "SELECT * FROM author";
        List<Author> authors = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Author author = new Author(
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("age"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getString("gender"),
                        rs.getString("emailId"),
                        rs.getLong("mobile"),
                        rs.getString("penname"),
                        rs.getString("biography"),
                        rs.getString("location")
                );
                authors.add(author);
            }
        }
        return authors;
    }

    public Author getAuthorByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM author WHERE username = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {

                    return new Author(
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getInt("age"),
                            rs.getDate("dob").toLocalDate(),
                            rs.getString("gender"),
                            rs.getString("emailId"),
                            rs.getLong("mobile"),
                            rs.getString("penname"),
                            rs.getString("biography"),
                            rs.getString("location")
                    );
                    }
            }
        }
        return null;
    }




    public List<Review> getReviews(String title) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM review WHERE book_title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,title);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reviews.add(new Review(
                        resultSet.getInt("book_id"),
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

    public String getGenreNameById(int genreId) throws SQLException {
        String genreName = null;
        String query = "SELECT name FROM genres WHERE id = ?";
        Connection dbConnection =DBConnection.getConnection();
        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            statement.setInt(1, genreId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                genreName = resultSet.getString("name");
            }
        }
        return genreName != null ? genreName : "Unknown Genre";
    }





    public boolean publishBook(Book book, StringBuilder content) throws SQLException {
        String sql = "INSERT INTO books (title, author, isPremium, domain,shortSummary, longSummary, pointsRequired, filePath, genre_id,content,copies) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getIsPremium());
            pstmt.setString(4, getGenreNameById(book.getGenreId()));
            pstmt.setString(5, book.getShortSummary());
            pstmt.setString(6, book.getLongSummary());
            pstmt.setInt(7, book.getPointsRequired());
            pstmt.setString(8, book.getFilePath());
            pstmt.setInt(9,book.getGenreId());
            pstmt.setString(10,content.toString());
            pstmt.setInt(11,book.getCopies());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedBookId = generatedKeys.getInt(1);
                        book.setBookId(generatedBookId);
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Book> getBooksByLoggedInAuthor(String username) throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE author = ?";
        Connection dbConnection=DBConnection.getConnection();
        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setIsPremium(resultSet.getInt("isPremium"));
                book.setDomain(resultSet.getString("domain"));
                book.setShortSummary(resultSet.getString("shortSummary"));
                book.setLongSummary(resultSet.getString("longSummary"));
                book.setPointsRequired(resultSet.getInt("pointsRequired"));
                book.setFilePath(resultSet.getString("filePath"));
                book.setCopies(resultSet.getInt("copies"));
                book.setGenreId(resultSet.getInt("genre_id"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public void showLeaderBoard() {
        String sql = "SELECT username, points FROM author WHERE role = 'author' ORDER BY points DESC";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("---- Leaderboard ----");
            System.out.printf("%-20s %s%n", "Username", "Points");
            System.out.println("---------------------");
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                int points = resultSet.getInt("points");
                System.out.printf("%-20s %d%n", username, points);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
