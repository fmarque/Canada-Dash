module com.group47.canadadash {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.json;
    requires com.google.gson;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.group47.canadadash to javafx.fxml, com.google.gson; // Correct this line
    exports com.group47.canadadash;
    opens com.group47.canadadash.processing to com.google.gson;

    opens com.group47.canadadash.game to javafx.graphics;//Allows game to be rendered
}
