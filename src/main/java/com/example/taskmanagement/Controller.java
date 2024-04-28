package com.example.taskmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Vector;

public class Controller implements  Listupdater{
    static Listupdater  listupdater;
    static Vector<Task> allTasks = new Vector<>() ;
    static Vector<User>allUsers=new Vector<>();
    static Vector<Task> deadlineTasks ;

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
    void addNewTaskHandler(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("AddNewTask.fxml"));
        Stage newStage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        newStage.setTitle("add_new_task");
        newStage.setScene(scene);
        newStage.show();
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
        User user ;
        String username = uesername.getText(); // Get the text content of the username field
        String pass = password.getText(); // Get the text content of the password field
        allTasksList.getItems().clear();
        myDayList.getItems().clear();
        deadlineTasks = new Vector<>();

        if (username.equals("admin") && pass.equals("admin")) {
            addNewTaskBT.setVisible(true);
            addNewUserBT.setVisible(true);
            boolean DeadlineAlert = false;
            // Assuming allTasks is a Vector containing tasks
            for (Task task : allTasks) {
                printTask(task, allTasksList);
                if((task.deadLine).equals(LocalDate.now())){
                    DeadlineAlert = true;
                    deadlineTasks.add(task);
                }
            }
            if(DeadlineAlert){
                String tasksSting = (deadlineTasks.toString()).replaceAll(", ", "\n");
                showAlert("Alert", "Tasks with today's deadline:\n" + tasksSting, Alert.AlertType.WARNING);
            }


        } else {
            user = getUser(username);
            if (user != null) {
                if (user.getPassword().equals(pass)) {
                    addNewTaskBT.setVisible(false);
                    addNewUserBT.setVisible(false);
                    CurrentUser = user;
                    Boolean DeadlineAlert =false;
                    for (Task task : user.tasks) {
                        printTask(task, allTasksList);
                        if((task.deadLine).equals(LocalDate.now())){
                            DeadlineAlert = true;
                            deadlineTasks.add(task);
                        }
                    }
                    if(DeadlineAlert){
                        String tasksSting = (deadlineTasks.toString()).replaceAll(", ", "\n");
                        showAlert("Alert", "Tasks with today's deadline:\n" + tasksSting, Alert.AlertType.WARNING);
                    }
                } else {
                    showAlert("Error", "Incorrect password!", Alert.AlertType.ERROR);
                }
            }
        }
    }

    public static void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }







    @FXML
    void startSprintHandler(MouseEvent event) {

    }

    public static User getUser(String username){
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

    public void printinalltasks(Task f){
        printTask(f,allTasksList);
    }


    @FXML
    void initialize() {

        listupdater= this;
        addNewTaskBT.setVisible(false);
        addNewUserBT.setVisible(false);


/*test part*/
//        Task t1 = new Task("L",1,LocalDate.of(2024, 4, 28),"sdkhbakhshvc");
//        Task t2 = new Task("p",2,LocalDate.of(2024, 4, 27),"sdkhbakhghshvc");
//        Task t3 = new Task("K",3,LocalDate.of(2024, 4, 27),"sdkhbakhljshvc");
//        allTasks.add(t1);
//        allTasks.add(t2);
//        allTasks.add(t3);
//        User u1 = new User("User","3454");
//
//        allUsers.add(u1);
//        u1.addTask(t1);
//        u1.addTask(t3);
    }
}