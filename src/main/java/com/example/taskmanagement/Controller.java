package com.example.taskmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    private Button viewAllTasksBT;

    @FXML
    private ChoiceBox<User> taskBox;

    @FXML
    Label TaskBokLabel;




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
    void addNewUserHandler(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("AddNewUserWindow.fxml"));
        Stage newStage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        newStage.setTitle("Create_User");
        newStage.setScene(scene);
        newStage.show();
    }


    @FXML
    void addTomyDayHandler(MouseEvent event) {
        boolean isFound = false;
        Task selectedTask = allTasksList.getSelectionModel().getSelectedItem();
        if(selectedTask != null){

            for (Task task : myDayList.getItems()) {
                if (task.equals(selectedTask)) {
                    isFound = true;
                    break;
                }
            }
            if(!isFound)
            printTask(selectedTask,myDayList);
        }
    }

    public static String taskname=null;
    public static String taskdescription=null;
    @FXML
    void seeTaskDiskHandler(MouseEvent event) throws IOException {
        Task currentT;
        try {
            currentT = allTasksList.getSelectionModel().getSelectedItem();
//            System.out.println(currentT);
            if(currentT != null) {
                taskname = currentT.taskName;
                taskdescription = currentT.taskDiscription;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TaskDescription.fxml"));
                Stage newstage = new Stage();
                Scene scene = new Scene(fxmlLoader.load());
                newstage.setTitle("Task Description");
                newstage.setScene(scene);
                newstage.show();
            }
        }
        catch(Exception e){
            return ;
        }
    }

    @FXML
    void taskBoxHandler(MouseEvent event){
        taskBox.getItems().clear();
        for(User user : allUsers) {
            taskBox.getItems().add(user);
        }
        taskBox.setOnAction(this::getUserTasks);
    }

    void getUserTasks(ActionEvent event){
        allTasksList.getItems().clear();
        myDayList.getItems().clear();
        User u = taskBox.getValue();
        for (Task task : u.tasks) {
            printTask(task, allTasksList);
            if((task.deadLine).equals(LocalDate.now())){
                deadlineTasks.add(task);
            }
        }
    }

    @FXML
    void viewAllTasksHandler(MouseEvent event){
        allTasksList.getItems().clear();
        myDayList.getItems().clear();
        for (Task task : allTasks) {
            printTask(task, allTasksList);
        }
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

            taskBox.getItems().clear();
            for(User u : allUsers) {
                taskBox.getItems().add(u);
            }
            taskBox.setOnAction(this::getUserTasks);

            addNewTaskBT.setVisible(true);
            addNewUserBT.setVisible(true);
            viewAllTasksBT.setVisible(true);
            taskBox.setVisible(true);
            TaskBokLabel.setVisible(true);
            CurrentUser = null;

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
                    viewAllTasksBT.setVisible(false);
                    taskBox.setVisible(false);
                    TaskBokLabel.setVisible(false);

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
        if(myDayList.getSelectionModel().getSelectedItem()==null){
            showAlert("no task","No task is selected.", Alert.AlertType.ERROR);
            return;}

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sprint.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("sprint");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch(Exception e){
            System.out.println("cant load");
        }

    }

    public void removeDay(){

        myDayList.getItems().remove(myDayList.getSelectionModel().getSelectedItem());
    }
    public void removeFromAllTasks(){
        Task currentTask=myDayList.getSelectionModel().getSelectedItem();
        allTasksList.getItems().remove(currentTask);
        allTasks.remove(currentTask);

        if(CurrentUser!=null){
            CurrentUser.tasks.remove(currentTask);}
        else {
            User u=null;
            for (User x: allUsers) {
                for (Task t: x.tasks) {
                    if(t==currentTask){
                        u=x;
                        break;
                    }
                }
            }
            u.tasks.remove(currentTask);
        }
        //Database delete
        DatabaseController.deleteTaskDatabase(currentTask);
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

    public void printinalltasks(){
        taskBox.setValue(new User("",""));
        allTasksList.getItems().clear();
        for(Task task : allTasks){
            printTask(task,allTasksList);
        }
    }


    @FXML
    void initialize() {

        listupdater= this;
        addNewTaskBT.setVisible(false);
        addNewUserBT.setVisible(false);
        viewAllTasksBT.setVisible(false);
        taskBox.setVisible(false);
        TaskBokLabel.setVisible(false);



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
