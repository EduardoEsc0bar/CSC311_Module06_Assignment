package org.example.csc311mod6assignment;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.regex.Pattern;

/**
 * Controller class for the registration form FXML.
 * Handles all user interactions and validation logic for the registration form.
 *
 * @author Registration Form Creator
 * @version 1.0
 * @since 2025-04-14
 */
public class RegistrationFormController {

    // Using RegEx for validation
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]{2,25}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@farmingdale\\.edu$");
    private static final Pattern DOB_PATTERN = Pattern.compile("^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(19|20)\\d\\d$");//is there any easier way to do this?
    private static final Pattern ZIP_PATTERN = Pattern.compile("^\\d{5}$");

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField dobField;
    @FXML
    private TextField zipField;
    @FXML
    private Button addButton;
    @FXML
    private Label firstNameValidation;
    @FXML
    private Label lastNameValidation;
    @FXML
    private Label emailValidation;
    @FXML
    private Label dobValidation;
    @FXML
    private Label zipValidation;

    /**
     * Initializes the controller class.
     *
     * @throws NullPointerException
     */
    @FXML
    public void initialize() {
        // Initially disable the add button (we shouldn't add a user when their information isn't all valid)
        addButton.setDisable(true);

        // Default 'X' text from all of the validation labels is set to blank
        firstNameValidation.setText("");
        lastNameValidation.setText("");
        emailValidation.setText("");
        dobValidation.setText("");
        zipValidation.setText("");

        // Set prompt text for better user guidance
        firstNameField.setPromptText("Enter first name (2-25 letters)");
        lastNameField.setPromptText("Enter last name (2-25 letters)");
        emailField.setPromptText("Enter email (@farmingdale.edu)");
        dobField.setPromptText("MM/DD/YYYY");
        zipField.setPromptText("5-digit zip code");

        // Set up focus listeners for validation
        setupValidationListeners();

        // Set up add button action
        addButton.setOnAction(event -> {
            navigateToSuccessPage();
        });
    }

    /**
     * Sets up focus listeners for all form fields to trigger validation
     * when the user moves to the next field.
     */
    private void setupValidationListeners() {
        firstNameField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) { // Focus lost
                validateFirstName();
                checkAllValidations();
            }
        });

        lastNameField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) { // Focus lost
                validateLastName();
                checkAllValidations();
            }
        });

        emailField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) { // Focus lost
                validateEmail();
                checkAllValidations();
            }
        });

        dobField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) { // Focus lost
                validateDateOfBirth();
                checkAllValidations();
            }
        });

        zipField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) { // Focus lost
                validateZipCode();
                checkAllValidations();
            }
        });
    }

    /**
     * Validates the first name field against the required pattern.
     *
     * @return true if the first name is valid, false otherwise
     */
    private boolean validateFirstName() {
        String firstName = firstNameField.getText().trim();
        boolean isValid = NAME_PATTERN.matcher(firstName).matches();

        if (isValid) {
            firstNameValidation.setText("✓");
            firstNameValidation.setTextFill(Color.GREEN);
            firstNameField.setStyle("");
        } else {
            firstNameValidation.setText("✗");
            firstNameValidation.setTextFill(Color.RED);

            if (firstName.isEmpty()) {
                firstNameField.setStyle("-fx-border-color: red;");
                firstNameValidation.setTooltip(new Tooltip("First name cannot be empty"));
            } else if (firstName.length() < 2) {
                firstNameField.setStyle("-fx-border-color: red;");
                firstNameValidation.setTooltip(new Tooltip("First name must be at least 2 characters"));
            } else if (firstName.length() > 25) {
                firstNameField.setStyle("-fx-border-color: red;");
                firstNameValidation.setTooltip(new Tooltip("First name cannot exceed 25 characters"));
            } else {
                firstNameField.setStyle("-fx-border-color: red;");
                firstNameValidation.setTooltip(new Tooltip("First name must contain only letters"));
            }
        }

        return isValid;
    }

    /**
     * Validates the last name field against the required pattern.
     *
     * @return true if the last name is valid, false otherwise
     */
    private boolean validateLastName() {
        String lastName = lastNameField.getText().trim();
        boolean isValid = NAME_PATTERN.matcher(lastName).matches();

        if (isValid) {
            lastNameValidation.setText("✓");
            lastNameValidation.setTextFill(Color.GREEN);
            lastNameField.setStyle("");
        } else {
            lastNameValidation.setText("✗");
            lastNameValidation.setTextFill(Color.RED);

            if (lastName.isEmpty()) {
                lastNameField.setStyle("-fx-border-color: red;");
                lastNameValidation.setTooltip(new Tooltip("Last name cannot be empty"));
            } else if (lastName.length() < 2) {
                lastNameField.setStyle("-fx-border-color: red;");
                lastNameValidation.setTooltip(new Tooltip("Last name must be at least 2 characters"));
            } else if (lastName.length() > 25) {
                lastNameField.setStyle("-fx-border-color: red;");
                lastNameValidation.setTooltip(new Tooltip("Last name cannot exceed 25 characters"));
            } else {
                lastNameField.setStyle("-fx-border-color: red;");
                lastNameValidation.setTooltip(new Tooltip("Last name must contain only letters"));
            }
        }

        return isValid;
    }

    /**
     * Validates the email field to ensure it ends with @farmingdale.edu
     *
     * @return true if the email is valid, false otherwise
     */
    private boolean validateEmail() {
        String email = emailField.getText().trim();
        boolean isValid = EMAIL_PATTERN.matcher(email).matches();

        if (isValid) {
            emailValidation.setText("✓");
            emailValidation.setTextFill(Color.GREEN);
            emailField.setStyle("");
        } else {
            emailValidation.setText("✗");
            emailValidation.setTextFill(Color.RED);
            emailField.setStyle("-fx-border-color: red;");

            if (email.isEmpty()) {
                emailValidation.setTooltip(new Tooltip("Email cannot be empty"));
            } else if (!email.contains("@")) {
                emailValidation.setTooltip(new Tooltip("Email must contain @ symbol"));
            } else if (!email.endsWith("@farmingdale.edu")) {
                emailValidation.setTooltip(new Tooltip("Email must end with @farmingdale.edu"));
            } else {
                emailValidation.setTooltip(new Tooltip("Invalid email format"));
            }
        }

        return isValid;
    }

    /**
     * Validates the date of birth field against the MM/DD/YYYY format.
     *
     * @return true if the date of birth is valid, false otherwise
     */
    private boolean validateDateOfBirth() {
        String dob = dobField.getText().trim();
        boolean isValid = DOB_PATTERN.matcher(dob).matches();

        if (isValid) {
            // Additional validation for realistic dates
            try {
                String[] parts = dob.split("/");
                int month = Integer.parseInt(parts[0]);
                int day = Integer.parseInt(parts[1]);
                int year = Integer.parseInt(parts[2]);

                // Simple validation for days in month
                boolean validDay = true;
                if (month == 2) { // February
                    boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
                    if (isLeapYear && day > 29) validDay = false;
                    if (!isLeapYear && day > 28) validDay = false;
                } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
                    validDay = false;
                }

                if (!validDay) {
                    dobValidation.setText("✗");
                    dobValidation.setTextFill(Color.RED);
                    dobField.setStyle("-fx-border-color: red;");
                    dobValidation.setTooltip(new Tooltip("Invalid day for the given month"));
                    return false;
                }

                dobValidation.setText("✓");
                dobValidation.setTextFill(Color.GREEN);
                dobField.setStyle("");
            } catch (Exception e) {
                dobValidation.setText("✗");
                dobValidation.setTextFill(Color.RED);
                dobField.setStyle("-fx-border-color: red;");
                dobValidation.setTooltip(new Tooltip("Invalid date format"));
                return false;
            }
        } else {
            dobValidation.setText("✗");
            dobValidation.setTextFill(Color.RED);
            dobField.setStyle("-fx-border-color: red;");
            dobValidation.setTooltip(new Tooltip("Date must be in MM/DD/YYYY format"));
        }

        return isValid;
    }

    /**
     * Validates the zip code field to ensure it contains exactly 5 digits.
     *
     * @return true if the zip code is valid, false otherwise
     */
    private boolean validateZipCode() {
        String zip = zipField.getText().trim();
        boolean isValid = ZIP_PATTERN.matcher(zip).matches();

        if (isValid) {
            zipValidation.setText("✓");
            zipValidation.setTextFill(Color.GREEN);
            zipField.setStyle("");
        } else {
            zipValidation.setText("✗");
            zipValidation.setTextFill(Color.RED);
            zipField.setStyle("-fx-border-color: red;");

            if (zip.isEmpty()) {
                zipValidation.setTooltip(new Tooltip("Zip code cannot be empty"));
            } else if (!zip.matches("\\d*")) {
                zipValidation.setTooltip(new Tooltip("Zip code must contain only digits"));
            } else if (zip.length() != 5) {
                zipValidation.setTooltip(new Tooltip("Zip code must be exactly 5 digits"));
            }
        }

        return isValid;
    }

    /**
     * Checks if all of the registration form validations pass and enables/disables the Add button accordingly.
     */
    private void checkAllValidations() {
        boolean allValid = validateFirstName() &&
                validateLastName() &&
                validateEmail() &&
                validateDateOfBirth() &&
                validateZipCode();

        addButton.setDisable(!allValid);
    }

    /**
     * Navigates to a success page after successful form submission.
     * Creates a new scene to display the submitted information.
     *
     */
    private void navigateToSuccessPage() {
        try {
            // Get the current stage
            Stage primaryStage = (Stage) addButton.getScene().getWindow();

            // Create success page layout
            AnchorPane successPane = new AnchorPane();
            successPane.setPrefSize(600, 493);

            // Success message
            Label successLabel = new Label("Registration Successful!");
            successLabel.setLayoutX(150);
            successLabel.setLayoutY(30);
            successLabel.setStyle("-fx-font-size: 33px; -fx-font-weight: bold;");

            // Display registered information
            Label infoLabel = new Label("Registered Information:");
            infoLabel.setLayoutX(50);
            infoLabel.setLayoutY(100);
            infoLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

            // User information using GridPane for better organization
            // Easier to do this than a whole new fxml file
            GridPane infoGrid = new GridPane();
            infoGrid.setLayoutX(100);
            infoGrid.setLayoutY(150);
            infoGrid.setVgap(15);
            infoGrid.setHgap(20);

            // Add field labels and values
            Label firstNameLabel = new Label("First Name:");
            firstNameLabel.setStyle("-fx-font-size: 16px;");
            Label firstNameValue = new Label(firstNameField.getText());
            firstNameValue.setStyle("-fx-font-size: 16px;");

            Label lastNameLabel = new Label("Last Name:");
            lastNameLabel.setStyle("-fx-font-size: 16px;");
            Label lastNameValue = new Label(lastNameField.getText());
            lastNameValue.setStyle("-fx-font-size: 16px;");

            Label emailLabel = new Label("Email:");
            emailLabel.setStyle("-fx-font-size: 16px;");
            Label emailValue = new Label(emailField.getText());
            emailValue.setStyle("-fx-font-size: 16px;");

            Label dobLabel = new Label("Date of Birth:");
            dobLabel.setStyle("-fx-font-size: 16px;");
            Label dobValue = new Label(dobField.getText());
            dobValue.setStyle("-fx-font-size: 16px;");

            Label zipLabel = new Label("Zip Code:");
            zipLabel.setStyle("-fx-font-size: 16px;");
            Label zipValue = new Label(zipField.getText());
            zipValue.setStyle("-fx-font-size: 16px;");

            // Add all labels and values to the grid
            infoGrid.add(firstNameLabel, 0, 0);
            infoGrid.add(firstNameValue, 1, 0);

            infoGrid.add(lastNameLabel, 0, 1);
            infoGrid.add(lastNameValue, 1, 1);

            infoGrid.add(emailLabel, 0, 2);
            infoGrid.add(emailValue, 1, 2);

            infoGrid.add(dobLabel, 0, 3);
            infoGrid.add(dobValue, 1, 3);

            infoGrid.add(zipLabel, 0, 4);
            infoGrid.add(zipValue, 1, 4);

            // Button to close the application
            Button closeButton = new Button("Close");
            closeButton.setLayoutX(400);
            closeButton.setLayoutY(400);
            closeButton.setPrefSize(100, 40);
            closeButton.setStyle("-fx-font-size: 16px;");
            closeButton.setOnAction(e -> primaryStage.close());

            // Add all of the components
            successPane.getChildren().addAll(
                    successLabel, infoLabel, infoGrid, closeButton
            );

            Scene successScene = new Scene(successPane, 600, 493);
            primaryStage.setScene(successScene);
            primaryStage.setTitle("Registration Success");

        } catch (Exception e) {
            System.err.println("Error navigating to success page: " + e.getMessage());
            e.printStackTrace();
        }
    }
}