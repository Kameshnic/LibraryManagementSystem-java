module org.example.miniproject1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.json;
    requires com.google.gson;
    requires java.sql;
    requires java.desktop;
    requires java.mail;
    opens org.example.miniproject1 to com.google.gson; // Open the package to Gson

    exports org.example.miniproject1;

    opens org.example.miniproject1.Model to javafx.fxml;
    opens org.example.miniproject1.Service to javafx.fxml;
    opens org.example.miniproject1.Controller to javafx.fxml;
    opens org.example.miniproject1.Interfaces to javafx.fxml;
    opens org.example.miniproject1.Util to javafx.fxml;
    exports org.example.miniproject1.Model;
    exports org.example.miniproject1.Service;
    exports org.example.miniproject1.Controller;
    exports org.example.miniproject1.Util;
    exports org.example.miniproject1.Interfaces;
}