package com.bank.gui;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class DashboardController {

    @FXML
    private Pane AcPane;

    @FXML
    private Pane ExitPane;

    @FXML
    private Pane HomePane;

    @FXML
    private Pane TransPane;

    @FXML
    void homeChecker() {
        System.out.println("Home Check");
    }

    @FXML
    void ExChecker() {
        System.out.println("Exit Check");
    }

    @FXML
    void AcChecker() {
        System.out.println("Account Check");
    }

    @FXML
    void TransChecker() {
        System.out.println("Trans Check");
    }

}
