module com.example.ejemplobuenobbdd {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;


    requires java.sql;
    requires java.sql.rowset;

    opens com.example.ejemplobuenobbdd to javafx.fxml;
    exports com.example.ejemplobuenobbdd;
}