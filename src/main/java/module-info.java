module com.example.ejemplobuenobbdd {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.java;
    requires java.sql;

    opens com.example.ejemplobuenobbdd to javafx.fxml;
    exports com.example.ejemplobuenobbdd;
}