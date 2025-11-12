package com.example.project_1.JavaFX;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainMenu extends BorderPane {

    public MainMenu() {

        getStyleClass().add("border-pane");

        Button addButton = new Button("Add Tasks");
        setLeft(addButton);

        addButton.setOnAction(e -> Main.setScene(new AddTasks()));

        Button readButton = new Button("Read File");
        setTop(readButton);

        Button writeButton = new Button("Write File");
        setBottom(writeButton);

        writeButton.getStyleClass().add("primary");

        Label label = new Label("Project 1");

        setCenter(label);


    }


}


