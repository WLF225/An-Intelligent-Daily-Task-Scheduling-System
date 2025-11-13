package com.example.project_1.JavaFX;

import com.example.project_1.Classes.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ViewTasks extends BorderPane {

    public ViewTasks() {

        setPadding(new Insets(40));

        Label headerL = new Label("View Tasks");
        headerL.getStyleClass().add("header");

        TableView<Task> tableView = getTableView();

        setCenter(tableView);

        //For the search operation
        Label searchL = new Label("Search:");
        TextField searchTF = new TextField();
        searchTF.setPromptText("Enter the task name");

        Button searchB = new Button("Search");
        searchB.getStyleClass().add("primary");

        Button resetB = new Button("Reset");

        HBox searchHB = new HBox(40, searchL, searchTF, searchB, resetB);
        searchHB.setAlignment(Pos.CENTER);

        VBox searchVB = new VBox(40, headerL, searchHB);
        searchVB.setAlignment(Pos.CENTER);
        searchVB.setPadding(new Insets(0, 0, 40, 0));

        setTop(searchVB);

        //Other buttons
        Button editB = new Button("Edit");
        Button deleteB = new Button("Delete");
        Button backB = new Button("Back");
        backB.getStyleClass().add("secondary");

        VBox buttonsVB = new VBox(40, editB, deleteB, backB);
        buttonsVB.setAlignment(Pos.CENTER);
        buttonsVB.setPrefWidth(200);
        buttonsVB.setPadding(new Insets(0, 40, 0, 0));

        //To make all buttons the same width as the buttons VBox
        editB.setMaxWidth(Double.MAX_VALUE);
        deleteB.setMaxWidth(Double.MAX_VALUE);
        backB.setMaxWidth(Double.MAX_VALUE);

        setLeft(buttonsVB);


        //The action that search's for tasks in the table
        searchB.setOnAction(e -> {
            if (!searchTF.getText().isEmpty()) {
                ObservableList<Task> filteredList = FXCollections.observableArrayList(Main.tasks);
                filteredList.removeIf(task -> !task.getName().toLowerCase().contains(searchTF.getText().toLowerCase()));
                tableView.setItems(filteredList);
            } else {
                Main.showErrorAlert("Please enter a task name to search!");
            }
        });

        //The action that resets the table to the original list
        resetB.setOnAction(e -> {
            ObservableList<Task> filteredList = FXCollections.observableArrayList(Main.tasks);
            tableView.setItems(filteredList);
            searchTF.clear();
        });

        //The action for the delete the selected task
        deleteB.setOnAction(e -> {
            Task selectedTask = tableView.getSelectionModel().getSelectedItem();
            deleteTask(selectedTask);
            resetB.fire();
        });

        //The action for the edit the selected task
        editB.setOnAction(e -> {
            Task selectedTask = tableView.getSelectionModel().getSelectedItem();
            if (selectedTask == null)
                Main.showErrorAlert("Please select a task from the table to edit!");
            else{
                for (int i = 0; i < Main.tasks.length; i++) {
                    if (Main.tasks[i].equals(selectedTask)){
                        Main.setScene(new EditTask(i));
                        return;
                    }
                }
            }
        });

        //The back button action
        backB.setOnAction(e -> Main.setScene(new MainMenu()));
    }

    public TableView<Task> getTableView() {

        ObservableList<Task> list = FXCollections.observableArrayList(Main.tasks);

        TableView<Task> tableView = new TableView<>(list);


        //The name column
        TableColumn<Task, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setMinWidth(250);

        //The hours column
        TableColumn<Task, Number> hoursCol = new TableColumn<>("Hours");
        hoursCol.setCellValueFactory(new PropertyValueFactory<>("hours"));
        hoursCol.setMinWidth(250);

        //The productivity column
        TableColumn<Task, Number> productivityCol = new TableColumn<>("Productivity");
        productivityCol.setCellValueFactory(new PropertyValueFactory<>("productivity"));
        productivityCol.setMinWidth(250);

        tableView.getColumns().addAll(nameCol, hoursCol, productivityCol);

        return tableView;
    }

    //To delete the selected task
    private static void deleteTask(Task selectedTask){
        if (selectedTask == null)
            Main.showErrorAlert("Please select a task from the table to delete!");
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete " + selectedTask.getName() + " task?");
            if (alert.showAndWait().get() == ButtonType.OK) {

                Task[] tasks = new Task[Main.tasks.length - 1];

                for (int i = 0, j = 0; i <= tasks.length; i++) {
                    if (!Main.tasks[i].equals(selectedTask))
                        tasks[j++] = Main.tasks[i];
                }

                Main.tasks = tasks;

                Main.showInfoAlert("Task deleted successfully!");
            }
        }
    }
}
