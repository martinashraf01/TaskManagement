package com.example.taskmanagement;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.Date;
import java.util.Optional;
import java.util.Vector;

public class Controller {
    static Vector<Task> allTasks ;
    static Vector<User>allUsers=new Vector<>();
    Vector<Task>myDay;
    User CurrentUser ;


    private Button addNewTaskBT;

    @FXML
    private Button addNewUserBT;

    @FXML
    private Button addToMyDayBT;

    @FXML
    private ListView<Task> allTasksList;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private ListView<Task> myDayList;

    @FXML
    private PasswordField password;

    @FXML
    private Button signInBT;

    @FXML
    private Button startSprintBT;

    @FXML
    private Button taskDiskBT;

    @FXML
    private TextField uesername;
    @FXML
    void addNewTaskHandler(MouseEvent event) {
//        Task x = new Task("rghr",3, new Date(),"disisiacnadc");
//        printTask(x,allTasksList);
    }

    @FXML
    void addNewUserHandler(MouseEvent event) {

    }


    @FXML
    void addTomyDayHandler(MouseEvent event) {

    }

    @FXML
    void seeTaskDiskHandler(MouseEvent event) {

    }

    @FXML
    void signInHandler(MouseEvent event) {

    }

    @FXML
    void startSprintHandler(MouseEvent event) {

    }

    public User getUser(String username){
        for (User x:
             allUsers) {
            if (x.getUserName().equals(username))
                return x;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alert!");
        alert.setContentText("wrong username!");
        Optional<ButtonType>result = alert.showAndWait();
        return null;
    }

    void printTask(Task t , ListView listView){
        listView.getItems().add(t);
        listView.backgroundProperty()
    }

}