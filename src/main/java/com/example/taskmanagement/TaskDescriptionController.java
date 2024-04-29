package com.example.taskmanagement;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class TaskDescriptionController implements Initializable {

    @FXML
    private Label taskname;
    @FXML
    private Label taskdescription;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        taskdescription.setWrapText(true);
        taskname.setText(Controller.taskname);
        taskdescription.setText(Controller.taskdescription);
    }
}
