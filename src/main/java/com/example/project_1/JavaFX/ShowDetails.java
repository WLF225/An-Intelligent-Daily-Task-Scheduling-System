package com.example.project_1.JavaFX;

import com.example.project_1.Algorithms.DynamicProgramming;
import com.example.project_1.Algorithms.Greedy;
import com.example.project_1.Classes.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ShowDetails extends BorderPane {

    public ShowDetails(int hours) {

        setPadding(new Insets(60));

        Label headerL = new Label("Show Details");
        headerL.getStyleClass().add("header");
        setTop(headerL);

        setAlignment(headerL, Pos.CENTER);

        //This grid pane is to put the dynamic programming table in
        GridPane dpGP = new GridPane();
        dpGP.setHgap(20);
        dpGP.setVgap(20);

        Task[] tasks = Main.tasks;

        dpGP.add(new Label("Tasks\\hors"),0,0);
        dpGP.add(new Label("No tasks"),0,1);

        //To enter all hours from 0 to hours
        for (int i = 0; i <= hours; i++) {
            dpGP.add(new Label(i+"h"),i+1,0);
        }

        //To enter all tasks
        for (int i = 0; i < tasks.length; i++) {
            dpGP.add(new Label(tasks[i].getName()),0,2+i);
        }

        //To get the table of the optimal solutions
        DynamicProgramming.TotalProductivity[][] tot = DynamicProgramming.dpSolution(hours);

        //To show all the table's productivity
        for (int i = 0; i < tot.length; i++) {
            for (int j = 0; j < tot[i].length; j++) {
                dpGP.add(new Label(tot[i][j].getTotalProductivity()+""),j+1,1+i);
            }
        }

        //The scroll pane is to make sure the table wont be too big
        ScrollPane scrollPane = new ScrollPane(dpGP);
        scrollPane.setPrefSize(500,350);

        //The table title
        Label dpTitle = new Label("Dynamic Programming Table");
        dpTitle.getStyleClass().add("title");


        //The dynamic programming relation and advantage
        TextArea dpTA = new TextArea();
        dpTA.setWrapText(true);
        dpTA.setEditable(false);
        dpTA.setPrefHeight(230);
        dpTA.setPrefWidth(500);

        dpTA.appendText("Base Case: dp[i][0] = 0, dp[0][j] = 0\n\n");
        dpTA.appendText("Recurrence Relation: " +
                "\ndp[i][j] = max(dp[i-1][j], dp[i-1][j-h[i]] + p[i]) \t if j>h[i]\n"
                +"dp[i][j] = dp[i-1][j] \t otherwise\n\n");

        dpTA.appendText("Advantages:\nDynamic Programming give the optimal solution for every hour on any task, " +
                "and it takes n^2 to find the solution, which is much faster than trying " +
                "all the combinations which takes 2^n");

        VBox dpVB = new VBox(20,dpTitle,scrollPane,dpTA);
        dpVB.setAlignment(Pos.CENTER);
        dpVB.setPadding(new Insets(40));

        setLeft(dpVB);

        //Greedy section
        Label greedyL = new Label("Greedy solution");
        greedyL.getStyleClass().add("title");

        //The text area that will have the greedy answer
        TextArea greedyTA = new TextArea();
        greedyTA.setWrapText(true);
        greedyTA.setEditable(false);
        greedyTA.setPrefHeight(400);
        greedyTA.setPrefWidth(500);

        greedyTA.appendText("\n\nTasks:\n\n");

        Task[] greedyTasks = Greedy.greedySolution(hours);
        //I make it long because the sum of integers may overflow if it was integer
        long greedyProductivity = 0;
        for (Task task : greedyTasks) {
            greedyTA.appendText(task.toString()+"\n\n");
            greedyProductivity += task.getProductivity();
        }

        greedyTA.setText("The productivity of the greedy solution for "+hours+" hours is: "+greedyProductivity+greedyTA.getText());

        //The text area that will have the explanation of the greedy method
        TextArea greedyExplanationTA = new TextArea();
        greedyExplanationTA.setWrapText(true);
        greedyExplanationTA.setEditable(false);
        greedyExplanationTA.setPrefHeight(300);
        greedyExplanationTA.setPrefWidth(500);

        greedyExplanationTA.setText("The greedy method give the answer by taking the " +
                "tasks having the highest ratios until no time or no tasks left.\n\n"
                +"The greedy give answer, but its not always the optimal solution.");

        VBox greedyVB = new VBox(20,greedyL,greedyTA,greedyExplanationTA);
        greedyVB.setAlignment(Pos.CENTER);
        greedyVB.setPadding(new Insets(40));
        setRight(greedyVB);


        //The back button
        Button backB = new Button("Back");
        backB.getStyleClass().add("secondary");
        setBottom(backB);

        backB.setOnAction(e->Main.setScene(new MainMenu()));
        setAlignment(backB,Pos.CENTER);
    }

}
