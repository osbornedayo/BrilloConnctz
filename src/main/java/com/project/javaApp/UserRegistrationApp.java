package com.project.javaApp;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.Period;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class UserRegistrationApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Date of Birth (yyyy-mm-dd): ");
        String dobString = scanner.nextLine();

        boolean validationResult = validateInputs(username, email, password, dobString);

        if (validationResult) {
            System.out.println("All validations passed!");
            String jwtToken = generateJWT(username);
            System.out.println("Generated JWT: " + jwtToken);

            System.out.print("Enter the JWT for verification: ");
            String enteredToken = scanner.nextLine();
            String verificationResult = verifyJWT(enteredToken) ? "verification pass" : "verification fails";
            System.out.println("JWT Verification: " + verificationResult);
        }

        scanner.close();
    }

    private static boolean validateInputs(String username, String email, String password, String dobString) {
        boolean isValid = true;
        StringBuilder errorMessage = new StringBuilder();

        // Username validation
        if (username.isEmpty() || username.length() < 4) {
            isValid = false;
            errorMessage.append("Username: not empty, min 4 characters\n");
        }

        // Email validation
        if (email.isEmpty() || !isValidEmail(email)) {
            isValid = false;
            errorMessage.append("Email: not empty, valid email address\n");
        }

        // Password validation
        if (password.isEmpty() || !isValidPassword(password)) {
            isValid = false;
            errorMessage.append("Password: not empty, strong password with at least 1 upper case, " +
                    "1 special character, 1 number, and minimum 8 characters\n");
        }

        // Date of Birth validation
        if (dobString.isEmpty() || !isValidDateOfBirth(dobString)) {
            isValid = false;
            errorMessage.append("Date of Birth: not empty, should be 16 years or greater\n");
        }

        if (!isValid) {
            System.out.println("Validation failed:");
            System.out.println(errorMessage);
        }

        return isValid;
    }

    private static boolean isValidEmail(String email) {
        // You can use a more complex email validation regex here
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(emailRegex, email);
    }

    private static boolean isValidPassword(String password) {
        // Password validation logic here
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}$";
        return Pattern.matches(passwordRegex, password);
    }

    private static boolean isValidDateOfBirth(String dobString) {
        try {
            LocalDate dob = LocalDate.parse(dobString);
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(dob, currentDate);
            return period.getYears() >= 16;
        } catch (Exception e) {
            return false;
        }
    }

    private static String generateJWT(String username) {
        String secretKey = "testSecretKey"; // Replace with your secret key
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }



    private static boolean verifyJWT(String jwtToken) {
        String secretKey = "testSecretKey"; // Replace with your secret key
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

