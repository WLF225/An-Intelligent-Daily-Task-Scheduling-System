package com.example.project_1.JavaFX;

import com.example.project_1.Classes.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddTasks extends BorderPane {

    public AddTasks() {

        getStyleClass().add("border-pane");

        ImageView image = new ImageView("addTask.png");

        setRight(image);

        BorderPane.setAlignment(image, Pos.CENTER);

        Label headerL = new Label("Add Tasks");
        headerL.getStyleClass().add("header");

        HBox headerH = new HBox(headerL);
        headerH.setAlignment(Pos.CENTER);

        //To make spaces between components
        setPadding(new Insets(40));

        //TextFields section
        Label nameL = new Label("Task name: ");
        Label hoursL = new Label("Hours: ");
        Label productivityL = new Label("Productivity: ");

        TextField nameTF = new TextField();
        nameTF.setPromptText("Enter the task name");
        TextField hoursTF = new TextField();
        
        TextField productivityTF = new TextField();

        GridPane tfGP = new GridPane();
        tfGP.setHgap(20);
        tfGP.setVgap(20);
        tfGP.setAlignment(Pos.CENTER);

        //To add the labels and textFields to the gridPane
        tfGP.add(nameL, 0, 0);
        tfGP.add(nameTF, 1, 0);
        tfGP.add(hoursL, 0, 1);
        tfGP.add(hoursTF, 1, 1);
        tfGP.add(productivityL, 0, 2);
        tfGP.add(productivityTF, 1, 2);

        //Buttons section
        Button clearButton = new Button("Clear");
        Button addButton = new Button("Add");
        Button backButton = new Button("Back");
        backButton.getStyleClass().add("secondary");

        addButton.getStyleClass().add("primary");

        //The action for the clear button
        clearButton.setOnAction(e -> {
            nameTF.clear();
            hoursTF.clear();
            productivityTF.clear();
        });

        //The action for the add's button
        addButton.setOnAction(e -> {
            try {
                //To make sure the hours and productivity are numbers
                if (!hoursTF.getText().matches("\\d+"))
                    throw new AlertException("The number of hours must be a number!");
                if (!productivityTF.getText().matches("\\d+"))
                    throw new AlertException("The amount of productivity must be a number!");

                Task task = new Task(nameTF.getText(), Integer.parseInt(hoursTF.getText()), Integer.parseInt(productivityTF.getText()));

                for (Task t : Main.tasks) {
                    if (task.equals(t))
                        throw new AlertException("The task already exists!");
                }

                //To add the new task for the main array
                Task[] tasks = new Task[Main.tasks.length+1];
                for (int i = 0; i < Main.tasks.length; i++) {
                    tasks[i] = Main.tasks[i];
                }
                tasks[Main.tasks.length] = task;

                Main.tasks = tasks;

                Main.showInfoAlert("Task added successfully!");

                clearButton.fire();

            }catch (AlertException ex) {
                Main.showErrorAlert(ex.getMessage());
            }
        });

        //The action for the back button
        backButton.setOnAction(e -> Main.setScene(new MainMenu()));

        //To add the buttons to the HBox
        HBox buttonsH = new HBox(20,backButton, addButton, clearButton);
        buttonsH.setAlignment(Pos.CENTER);

        //To add all the components but the image to the main VBox
        VBox mainVB = new VBox(40,headerH, tfGP, buttonsH);
        mainVB.setAlignment(Pos.CENTER);


        setCenter(mainVB);

    }
}
