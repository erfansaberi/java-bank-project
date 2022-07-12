module com.bank.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
//<<<<<<< HEAD
////    requires validatorfx;
//=======
//>>>>>>> 44dfab4b19e4eb10e3bd41de3bca4841b77d4319
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    
    
    
    opens com.bank.gui to javafx.fxml;
    opens com.bank.models to javafx.base;
    exports com.bank.gui;
}