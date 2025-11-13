package com.example.project_1.JavaFX;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditTask extends BorderPane {

    public EditTask(int index) {

        setPadding(new Insets(40));

        ImageView image = new ImageView("editTask.png");

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
        Button resetB = new Button("Reset");
        Button editB = new Button("Edit");
        Button backB = new Button("Back");

        //Theis 2 buttons for traveling through the tasks
        ImageView leftIV = new ImageView("left.png");
        leftIV.setFitWidth(100);
        leftIV.setFitHeight(100);
        ImageView rightIV = new ImageView("right.png");
        rightIV.setFitWidth(100);
        rightIV.setFitHeight(100);

        Button prevB = new Button("",leftIV);
        Button nextB = new Button("",rightIV);

        prevB.setStyle("-fx-background-color: transparent;"
            +"-fx-border-color: transparent;");

        nextB.setStyle("-fx-background-color: transparent;"
                +"-fx-border-color: transparent;");


        if (index == 0)
            prevB.setDisable(true);
        if (index == Main.tasks.length-1)
            nextB.setDisable(true);

        backB.getStyleClass().add("secondary");

        editB.getStyleClass().add("primary");

        //To add the main buttons to the HBox
        HBox buttonsH = new HBox(20, backB, editB, resetB);
        buttonsH.setAlignment(Pos.CENTER);

        //To add the travel buttons to the HBox
        HBox travelH = new HBox(20, prevB, nextB);
        travelH.setAlignment(Pos.CENTER);

        //To add all the components but the image to the main VBox
        VBox mainVB = new VBox(40,headerH, tfGP, buttonsH, travelH);
        mainVB.setAlignment(Pos.CENTER);


        setCenter(mainVB);


        //Buttons actions section

        //The action for the reset button
        resetB.setOnAction(e -> {
            nameTF.setText(Main.tasks[index].getName());
            hoursTF.setText(Main.tasks[index].getHours()+"");
            productivityTF.setText(Main.tasks[index].getProductivity()+"");
        });

        resetB.fire();

        //The edit button action
        editB.setOnAction(e -> {
            Alert confAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confAlert.setTitle("Edit Task");
            confAlert.setHeaderText(null);
            confAlert.setContentText("Are you sure you want to edit this task?");

            if (confAlert.showAndWait().get() == ButtonType.OK){

                Main.tasks[index].setName(nameTF.getText());
                Main.tasks[index].setHours(Integer.parseInt(hoursTF.getText()));
                Main.tasks[index].setProductivity(Integer.parseInt(productivityTF.getText()));

                Main.showInfoAlert("The task has been edited!");
            }
        });

        backB.setOnAction(e -> Main.setScene(new ViewTasks()));

        prevB.setOnAction(e -> Main.setScene(new EditTask(index-1)));
        nextB.setOnAction(e -> Main.setScene(new EditTask(index+1)));

    }
}
