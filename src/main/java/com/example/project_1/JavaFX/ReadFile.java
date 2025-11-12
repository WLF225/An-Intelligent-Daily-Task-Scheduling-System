package com.example.project_1.JavaFX;

import com.example.project_1.Classes.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        try {
            //To choose the file to read from
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);

            if (file == null)
                throw new IOException();

            Scanner scanner = new Scanner(file);
            //To skip the first line
            scanner.nextLine();

            //To know the number of lines in the file to create initial array
            int lineCount = 0;
            while (scanner.hasNextLine()){
                scanner.nextLine();
                lineCount++;
            }

            scanner = new Scanner(file);
            //To skip the first line
            scanner.nextLine();

            Task[] tasks = new Task[lineCount];

            boolean chack = true;
            //To know what line is the error, and what index in the array to insert into
            int lineNum = 0, index = 0;
            while (scanner.hasNextLine()) {
                try {
                    lineNum++;
                    String[] line = scanner.nextLine().split(",");

                    if (line.length != 3)
                        throw new AlertException("The format in the file is wrong!" +
                                "\nThe format should be: name, hours, productivity");

                    Task task = new Task(line[0].trim(), Integer.parseInt(line[1].trim()), Integer.parseInt(line[2].trim()));

                    for (Task t : Main.tasks) {
                        if (task.equals(t))
                            throw new AlertException("The task already exists!");
                    }

                    tasks[index++] = task;

                } catch (AlertException e1) {
                    if (chack) {
                        //To show an alert of the error
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);

                        //To close all the error alerts
                        ButtonType closeAllB = new ButtonType("Close all");
                        alert.getButtonTypes().add(closeAllB);

                        alert.setContentText(e1.getMessage() + "\nThe error is in line " + lineNum);

                        if (alert.showAndWait().get() == closeAllB)
                            chack = false;
                    }
                } catch (NumberFormatException e2) {
                    if (chack) {
                        //To show an alert of the error
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);

                        //To close all the error alerts
                        ButtonType closeAllB = new ButtonType("Close all");
                        alert.getButtonTypes().add(closeAllB);

                        alert.setContentText("Wrong data format!\nThe data should be: String,integer,integer"
                                + "\nThe error is in line " + lineNum);

                        if (alert.showAndWait().get() == closeAllB)
                            chack = false;
                    }
                }
            }
            scanner.close();

            //To put the tasks into the main array
            Main.tasks = new Task[index];
            for (int i = 0; i < index; i++) {
                Main.tasks[i] = tasks[i];
            }

        } catch (IOException ex) {
            Main.showErrorAlert("Please choose a file to read from!");
        }
    }
}
