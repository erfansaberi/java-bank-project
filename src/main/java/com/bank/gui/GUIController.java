package com.bank.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.bank.views.*;

public class GUIController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Hello");
    }
}