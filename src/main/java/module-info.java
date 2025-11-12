module com.example.project_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.project_1 to javafx.fxml;
    exports com.example.project_1;
    exports com.example.project_1.Classes;
    opens com.example.project_1.Classes to javafx.fxml;
    exports com.example.project_1.JavaFX;
    opens com.example.project_1.JavaFX to javafx.fxml;
}