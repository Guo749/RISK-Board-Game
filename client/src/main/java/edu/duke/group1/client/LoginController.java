package edu.duke.group1.client;

import edu.duke.group1.shared.Action;
import edu.duke.group1.shared.LoginAction;
import edu.duke.group1.shared.RegisterAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is used to handle the login event
 * can return the information the user input in text field
 */
public class LoginController implements Initializable {
    /* this is the password the user input */
    public static String thisPassword = null;

    /* this is the user name the user input */
    public static String thisUserName = null;

    /* this is to represent the status for user to log in or sign up */
    public static boolean signUp;

    /* password field in FXML */
    @FXML
    TextField password;

    /* user name field in FXML */
    @FXML
    TextField userName;


    /**
     * the action handling for user to hit button
     * as well as close the window
     * then it will lead to the page of joining / creating room
     *
     * @param ae the event for user to hit button
     */
    @FXML
    void submit(ActionEvent ae) throws Exception {
        thisPassword = password.getText();
        thisUserName = userName.getText();

        System.out.println(thisUserName + " ----- "+ thisPassword);

        Button b  = (Button) ae.getSource();

        if(b.getText().equals("Sign Up")){
            signUp = true;
        }else{
            signUp = false;
        }

        Stage stage = (Stage) b.getScene().getWindow();


        Action action = null;
        if(signUp){
            action = new RegisterAction(thisUserName, thisPassword);
        }else{
            action = new LoginAction(thisUserName, thisPassword);
        }

        Helper.sendAction(action);
        System.out.println("send successfully");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(thisPassword != null){
            password.setText(thisPassword);
            userName.setText(thisUserName);
        }
    }

    public static Scene show() throws IOException {
        return Helper.getScene("/fxml/logInPage.fxml", 600, 400);
    }
}
