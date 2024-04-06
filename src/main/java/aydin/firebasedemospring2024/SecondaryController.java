package aydin.firebasedemospring2024;

import java.io.IOException;
import java.net.Authenticator;
import java.util.Arrays;

import com.google.auth.oauth2.OAuth2Credentials;
import com.google.auth.oauth2.UserCredentials;
import com.google.firebase.auth.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SecondaryController {

    public Button registrationButton;
    public Button SigninButton;
    public Button secondaryButton;
    public TextField emailTextField;
    public TextField passwordTextField;
    public TextField nameTextField;
    public TextField phoneTextField;
    public TextField emailPasswordField;
    public TextField signInPasswordField;

    private boolean key;
    private ObservableList<Person> listOfUsers = FXCollections.observableArrayList();
    private Person person;

    @FXML
    void registerButtonClicked(ActionEvent event) {
        registerUser();
    }

    @FXML
    void signInButtonClicked(ActionEvent event) { signInUser();}

    public void signInUser() {
        UserRecord.CreateRequest request1 = new UserRecord.CreateRequest()
                .setEmail(emailPasswordField.getText())
                .setPassword(signInPasswordField.getText());
        UserRecord userRecord2;
        System.out.println(Authenticator.getDefault());
        try {
            userRecord2 = DemoApp.fauth.getUserByEmail(emailPasswordField.getText());
            System.out.println(DemoApp.getFauth());
            System.out.println(userRecord2);
            switchToPrimary();
        } catch (IOException | FirebaseAuthException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean registerUser() {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(emailTextField.getText())
                .setEmailVerified(false)
                .setPassword(passwordTextField.getText())
                .setPhoneNumber(phoneTextField.getText())
                .setDisplayName(nameTextField.getText())
                .setDisabled(false);
        UserRecord userRecord;
        try {
            userRecord = DemoApp.fauth.createUser(request);
            System.out.println("Successfully created new user with Firebase Uid: " + userRecord.getUid()
                    + " check Firebase > Authentication > Users tab");
            System.out.println(userRecord.getUid());
            return true;

        } catch (FirebaseAuthException ex) {
            // Logger.getLogger(FirestoreContext.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            System.out.println("Error creating a new user in the firebase");
            System.out.println(emailTextField.getText());
            System.out.println(passwordTextField.getText());
            System.out.println(phoneTextField.getText());
            System.out.println(nameTextField.getText());
            return false;
        }

    }

    @FXML
    private void switchToPrimary() throws IOException {
        DemoApp.setRoot("primary");
    }
}
