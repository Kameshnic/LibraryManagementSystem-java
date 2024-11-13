package org.example.miniproject1.Service;

import org.example.miniproject1.Model.Genre;
import org.example.miniproject1.Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreService {
    private Connection connection;

    public GenreService() throws SQLException {
        this.connection = DBConnection.getConnection();
    }

    public List<Genre> getAllGenres() throws SQLException {
        List<Genre> genres = new ArrayList<>();
        String query = "SELECT * FROM genres";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                genres.add(new Genre(rs.getInt("id"), rs.getString("name")));
            }
        }
        return genres;
    }

    public void addGenre(String name) throws SQLException {
        String query = "INSERT INTO genres (name) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }

    public String getGenreNameById(int genreId) throws SQLException {
        String genreName = null;
        String query = "SELECT name FROM Genre WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, genreId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                genreName = resultSet.getString("name");
            }
        }
        return genreName;
    }

}
