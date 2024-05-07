package com.example.taskmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("taskManagementSys");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        DatabaseController.connect();
        DatabaseController.retrieveOldData();
        launch();
    }
}