package com.example.project_1.JavaFX;

import com.example.project_1.Classes.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.PrintWriter;

public class SaveToFile implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showSaveDialog(null);

        if (file == null)
            Main.showErrorAlert("Please select a file to save");
        else{
            try (PrintWriter pw = new PrintWriter(file)){
                Task[] tasks = Main.tasks;
                pw.println("Task name, hours, productivity");
                for (int i = 0; i < tasks.length; i++) {
                    pw.println(tasks[i].getName() + ", " + tasks[i].getHours() + ", " + tasks[i].getProductivity());
                }
            }catch (Exception e){
                Main.showErrorAlert(e.getMessage());
            }
        }
    }
}
