package com.bank.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class RegisterController {
    @FXML
    private TextArea Address;

    @FXML
    private TextField IDnum;

    @FXML
    private DatePicker birthDay;

    @FXML
    private Button confirmbutt;

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
	void Previous()
	{
    	loginController.Regstage.close();
        GUI.preStage.show();
		
	}
    
    
}
