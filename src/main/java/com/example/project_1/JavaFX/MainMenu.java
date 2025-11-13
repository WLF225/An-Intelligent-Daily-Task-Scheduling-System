package com.example.project_1.JavaFX;

import com.example.project_1.Algorithms.DynamicProgramming;
import com.example.project_1.Classes.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainMenu extends BorderPane {

    public MainMenu() {

        setPadding(new Insets(40));

        //The header
        Label headerL = new Label("Main Menu");
        headerL.getStyleClass().add("header");

        setTop(headerL);

        setAlignment(headerL, Pos.CENTER);

        //The buttons section
        Button readFromFileB = new Button("Read From File");
        Button addTaskB = new Button("Add Task");
        Button viewTasksB = new Button("View Tasks");
        Button saveToFileB = new Button("Save To File");
        Button exitB = new Button("Exit");
        Button showDetailsB = new Button("Show details");
        exitB.getStyleClass().add("secondary");

        showDetailsB.setDisable(true);

        //To make the buttons larger
        readFromFileB.setStyle("-fx-font-size: 30");
        //To make the buttons the same width as the buttons VBox
        readFromFileB.setMaxWidth(Double.MAX_VALUE);

        addTaskB.setStyle("-fx-font-size: 30");
        addTaskB.setMaxWidth(Double.MAX_VALUE);

        saveToFileB.setStyle("-fx-font-size: 30");
        saveToFileB.setMaxWidth(Double.MAX_VALUE);

        viewTasksB.setStyle("-fx-font-size: 30");
        viewTasksB.setMaxWidth(Double.MAX_VALUE);

        exitB.setStyle("-fx-font-size: 30");
        exitB.setMaxWidth(Double.MAX_VALUE);

        VBox buttonsVB = new VBox(40, readFromFileB, addTaskB, viewTasksB, saveToFileB, exitB);
        buttonsVB.setAlignment(Pos.CENTER);
        buttonsVB.setPrefWidth(350);
        buttonsVB.setPadding(new Insets(0, 0, 0, 100));

        setLeft(buttonsVB);

        //The main functionality section
        Label label = new Label("Number of Hours: ");
        TextField hoursTF = new TextField();
        hoursTF.setPromptText("Enter the number of hours");

        //To give the optimal solution for the number of hours entered by the user
        Button calculateB = new Button("Calculate");
        calculateB.getStyleClass().add("primary");

        HBox calculateH = new HBox(40, label, hoursTF, calculateB);
        calculateH.setAlignment(Pos.CENTER);

        //The text area that will have the answer
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setPrefHeight(350);
        textArea.setPrefWidth(600);
        textArea.setMaxHeight(350);
        textArea.setMaxWidth(600);
        textArea.setWrapText(true);

        VBox solutionVB = new VBox(40, calculateH, textArea,showDetailsB);
        solutionVB.setAlignment(Pos.CENTER);

        setCenter(solutionVB);

        //Buttons actions
        readFromFileB.setOnAction(new ReadFile());

        saveToFileB.setOnAction(new SaveToFile());

        showDetailsB.setOnAction(e->{
            //To make sure the hours text field have a number
            if (hoursTF.getText().isEmpty())
                Main.showErrorAlert("Please enter the number of hours!");
            else if (!hoursTF.getText().matches("\\d+"))
                Main.showErrorAlert("The number of hours must be a positive number!");
            else
                Main.setScene(new ShowDetails(Integer.parseInt(hoursTF.getText())));


        });

        exitB.setOnAction(e -> System.exit(0));

        addTaskB.setOnAction(e -> Main.setScene(new AddTasks()));

        viewTasksB.setOnAction(e -> {
            if (Main.tasks.length == 0)
                Main.showErrorAlert("There are no tasks to view!");
            else
                Main.setScene(new ViewTasks());
        });

        calculateB.setOnAction(e -> {
            try {
                textArea.clear();

                if (hoursTF.getText().isEmpty())
                    throw new AlertException("The number of hours must be entered!");
                if (!hoursTF.getText().matches("\\d+"))
                    throw new AlertException("The number of hours must be a positive number!");
                if (Main.tasks.length == 0)
                    throw new AlertException("There are no tasks to calculate!");

                int hours = Integer.parseInt(hoursTF.getText());

                //To get the optimal solution for the number of hours entered by the user
                DynamicProgramming.TotalProductivity[][] productivities = DynamicProgramming.dpSolution(hours);

                textArea.appendText("The max number of productivity for " + hours + " hours is: " + productivities[Main.tasks.length][hours].getTotalProductivity());
                textArea.appendText("\n\nTasks:\n\n");

                //To get the tasks that give the optimal solution printed up
                Task[] tasks = DynamicProgramming.getTasks(productivities, hours);

                for (Task t : tasks) {
                    textArea.appendText(t.toString() + "\n\n");
                }

                showDetailsB.setDisable(false);

            } catch (AlertException ex) {
                Main.showErrorAlert(ex.getMessage());
            }
        });
    }
}


