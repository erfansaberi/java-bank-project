package com.bank.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RegisterController {
    @FXML
    private TextArea Address;

    @FXML
    private TextField IDnum;

    @FXML
    private DatePicker birthDay;

    @FXML
    private Button confirmButt;

    @FXML
    private TextField emailAd;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField phnum;

    @FXML
    private RadioButton FemaleGender;

    @FXML
    private RadioButton MaleGender;

    @FXML
    void Previous() {
        loginController.RegStage.close();
        GUI.preStage.show();
    }

}
