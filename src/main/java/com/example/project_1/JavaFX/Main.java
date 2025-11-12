package com.example.project_1.JavaFX;

import com.example.project_1.Classes.Task;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Main extends Application {

    private static Scene scene;
    public static Task[] tasks = new Task[0];

    public void start(Stage stage) throws Exception {

        MainMenu mainMenu = new MainMenu();

        scene = new Scene(mainMenu);

        scene.getStylesheets().add("style.css");

        stage.setMaximized(true);
        stage.setTitle("Project 1");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //To change the pages of the application
    public static void setScene(Parent root){
        scene.setRoot(root);
    }

    //To show an error alert
    public static void showErrorAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //To show an information alert
    public static void showInfoAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
