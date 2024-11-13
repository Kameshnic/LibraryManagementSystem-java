package org.example.miniproject1.Service;

import org.example.miniproject1.Exceptions.Credentials.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CredentialService {

    public void validateName(String name) throws InvalidNameException {
        if (name == null || name.isEmpty()) {
            throw new InvalidNameException("Name cannot be empty.");
        }

        for (char c : name.toCharArray()) {
            if (!Character.isLetter(c)) {
                throw new InvalidNameException("Name must contain only letters.");
            }
        }
    }

    // Validate age (must be between 5 and 120)
    public void validateAge(int age) throws InvalidAgeException {
        if (age < 5 || age > 120) {
            throw new InvalidAgeException("Age must be between 5 and 120.");
        }
    }

    // Validate date of birth in format "YYYY-MM-DD"
    public LocalDate validateDateOfBirth(String dob) throws InvalidDateException {
        try {
            return LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Invalid date format. Use YYYY-MM-DD.");
        }
    }

    // Check if username is unique
    public void validateUniqueUsername(String username, StudentService studentService)
            throws UsernameAlreadyExistsException, SQLException {
        if (studentService.getStudentByUsername(username) != null) {
            throw new UsernameAlreadyExistsException("Username already exists. Choose a different one.");
        }
    }

    // Validate mobile number (exactly 10 digits)
    public void validateMobileNumber(long mobile) throws InvalidMobileNumberException {
        String mobileStr = String.valueOf(mobile);
        if (mobileStr.length() != 10) {
            throw new InvalidMobileNumberException("Mobile number must be exactly 10 digits.");
        }

        for (char c : mobileStr.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new InvalidMobileNumberException("Mobile number must contain only digits.");
            }
        }
    }

    // Validate student card number (must be "student001" for this example)
    public void validateStudentCardNumber(String studentCardNumber) throws InvalidStudentCardNumberException {
        if (!"student001".equals(studentCardNumber)) {
            throw new InvalidStudentCardNumberException("Invalid student card number.");
        }
    }

    // Validate email
    public void validateEmail(String email) throws InvalidEmailException {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new InvalidEmailException("Email must contain '@' and '.'");
        }

        String[] parts = email.split("@");
        if (parts.length != 2 || parts[1].indexOf('.') == -1) {
            throw new InvalidEmailException("Invalid email format.");
        }
    }

    // Validate password (at least 8 characters, one letter, one digit, one special character)
    public void validatePassword(String password) throws InvalidPasswordException {
        if (password == null || password.length() < 8) {
            throw new InvalidPasswordException("Password must be at least 8 characters.");
        }

        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                hasSpecialChar = true;
            }
        }

        if (!hasLetter || !hasDigit || !hasSpecialChar) {
            throw new InvalidPasswordException("Password must contain a letter, a digit, and a special character.");
        }
    }

    public void validateAuthorCardNumber(String authorCardNumber) throws InvalidAuthorCardNumberException {

        if (!"author001".equals(authorCardNumber)) {
            throw new InvalidAuthorCardNumberException("Invalid student card number.");
        }


    }
}