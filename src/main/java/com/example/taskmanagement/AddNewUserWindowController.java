package com.example.taskmanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddNewUserWindowController {

    @FXML
    private Button createUserBt;

    @FXML
    private PasswordField new_password;

    @FXML
    private TextField newUsername;

    @FXML
    private PasswordField re_entered;

    @FXML
    void createUserBtHandler(MouseEvent event) {
        String username = newUsername.getText();
        if(username.length()==0){
            Controller.showAlert("error","please enter the new username", Alert.AlertType.ERROR);
            return;
        }
        for (User u: Controller.allUsers) {
            if(username.equals(u.getUserName())){
                Controller.showAlert("error","this username is already used", Alert.AlertType.ERROR);
                return;
            }
        }


        String password = new_password.getText();
        if(password.length()==0){
            Controller.showAlert("error","please enter the new password", Alert.AlertType.ERROR);
            return;
        }
        if(!password.equals(re_entered.getText())){
            Controller.showAlert("error","the 2 passwords are not similar", Alert.AlertType.ERROR);
            return;
        }

        Controller.allUsers.add(new User(username,password));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
