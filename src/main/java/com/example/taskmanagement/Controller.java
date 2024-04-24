package com.example.taskmanagement;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.Vector;

public class Controller {
    static Vector<Task> allTasks;
    static Vector<User>allUsers;
    Vector<Task>myDay;
    User CurrentUser ;


    private Button addNewTaskBT;

    @FXML
    private Button addNewUserBT;

    @FXML
    private Button addToMyDayBT;

    @FXML
    private ListView<?> allTasksList;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private ListView<?> myDayList;

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


}