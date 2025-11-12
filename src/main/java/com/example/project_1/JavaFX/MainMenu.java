package com.example.project_1.JavaFX;

import com.example.project_1.Algorithm;
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
        Button viewTasksB = new Button("View Tasks");
        Button saveToFileB = new Button("Save To File");
        Button exitB = new Button("Exit");

        readFromFileB.setStyle("-fx-font-size: 30");
        readFromFileB.setPrefWidth(250);
        saveToFileB.setStyle("-fx-font-size: 30");
        saveToFileB.setPrefWidth(250);
        viewTasksB.setStyle("-fx-font-size: 30");
        viewTasksB.setPrefWidth(250);
        exitB.setStyle("-fx-font-size: 30");
        exitB.setPrefWidth(250);

        VBox buttonsVB = new VBox(40, readFromFileB, viewTasksB, saveToFileB, exitB);
        buttonsVB.setAlignment(Pos.CENTER);
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
        textArea.setPrefHeight(500);
        textArea.setPrefWidth(600);
        textArea.setMaxHeight(500);
        textArea.setMaxWidth(600);
        textArea.setWrapText(true);


        VBox solutionVB = new VBox(40, calculateH, textArea);
        solutionVB.setAlignment(Pos.CENTER);

        setCenter(solutionVB);

        //Buttons actions
        readFromFileB.setOnAction(new ReadFile());

        exitB.setOnAction(e -> System.exit(0));

        viewTasksB.setOnAction(e->Main.setScene(new AddTasks()));

        calculateB.setOnAction(e -> {

            try {

                textArea.clear();

                if (hoursTF.getText().isEmpty())
                    throw new AlertException("The number of hours must be entered!");
                if (!hoursTF.getText().matches("\\d+"))
                    throw new AlertException("The number of hours must be a number!");
                if (Main.tasks.length == 0)
                    throw new AlertException("There are no tasks to calculate!");

                int hours = Integer.parseInt(hoursTF.getText());

                //To get the optimal solution for the number of hours entered by the user
                Algorithm.TotalProductivity[][] productivities = Algorithm.Knapsack(hours);

                textArea.appendText("The max number of productivity for "+ hours + " hours is: "+productivities[Main.tasks.length][hours].getTotalProductivity());
                textArea.appendText("\n\nTasks:\n\n");

                //To get the tasks that give the optimal solution printed up
                Task[] tasks = Algorithm.getTasks(productivities, hours);

                for (Task t : tasks) {
                    textArea.appendText(t.toString()+"\n\n");
                }

                hoursTF.clear();

            }catch (AlertException ex){
                Main.showErrorAlert(ex.getMessage());
            }

        });


    }


}


