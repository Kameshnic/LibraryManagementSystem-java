package org.example.miniproject1.Interfaces;

import java.sql.SQLException;


public interface AuthorControllerInterface  {
    void displayMenu();
    void Login() throws SQLException;
    void registerAuthor();
    void authorOperations();


}
