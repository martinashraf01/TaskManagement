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

    @FXML
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
        Task x = new Task("rghr",2, new Date(),"disisiacnadc");
        printTask(x,allTasksList);
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

    public void printTask(Task t, ListView<Task> listView) {
        listView.getItems().add(t);

        // Set background color based on priority using a lambda expression
        listView.setCellFactory(cellFactory -> new ListCell<Task>() {
            @Override
            protected void updateItem(Task item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setStyle(null);
                } else {
                    setText(item.toString()); // Assuming Task has a meaningful toString() method

                    // Set background color based on priority
                    switch (item.priority) {
                        case 3:
                            setStyle("-fx-text-fill: FF5733");
                            break;
                        case 2:
                            setStyle("-fx-text-fill: C3BB13");
                            break;
                        case 1:
                            setStyle("-fx-text-fill: 13C33E");
                            break;
                        default:
                            // Handle default case (optional)
                    }
                }
            }
        });
    }


}