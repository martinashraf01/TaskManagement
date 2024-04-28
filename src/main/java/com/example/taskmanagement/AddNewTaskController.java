package com.example.taskmanagement;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddNewTaskController implements Initializable {

    @FXML
    private Stage stage;

    @FXML
    private Button addTaskbt;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox<Integer> priorityBox;

    @FXML
    private TextArea taskDescription;

    @FXML
    private TextField taskName;

    @FXML
    private TextField taskOwner;

    @FXML
    void addHandler(MouseEvent event) {
        User currentUser =null;
        currentUser = Controller.getUser(taskOwner.getText());
        if (currentUser != null) {
            LocalDate date = datePicker.getValue();
            String desc = taskDescription.getText();
            if(desc.length()==0)desc="no description provided";
            String name = taskName.getText();
            int priority = priorityBox.getSelectionModel().getSelectedItem()==null?1:priorityBox.getSelectionModel().getSelectedItem();
            if(name.length()==0)name = "task"+Task.getCount();
            if(date == null){
                Controller.showAlert("missing_data","please select the task deadline", Alert.AlertType.ERROR);
                return;

            }
            Task t =new Task(name,priority,date,desc);
            currentUser.tasks.add(t);
            Controller.allTasks.add(t);
            ((Controller)(Controller.listupdater)).printinalltasks(t);

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        priorityBox.getItems().addAll(1,2,3);

    }
}

interface Listupdater{

}