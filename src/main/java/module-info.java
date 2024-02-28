module com.justbelieveinmyself.numericalintegration {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires javafx.graphics;
    requires exp4j;

    opens com.justbelieveinmyself.numericalintegration to javafx.fxml;
    exports com.justbelieveinmyself.numericalintegration;
}