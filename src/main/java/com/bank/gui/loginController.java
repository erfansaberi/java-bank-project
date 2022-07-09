package com.bank.gui;

import javafx.fxml.FXML;
//import com.bank.views.*;
//import javafx.application.Application;
import javafx.fxml.FXMLLoader;
//import javafx.scene.control.Label;
//import javafx.fxml.Initializable;
//import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class loginController {

	@FXML
	private Button loginbutt;

	@FXML
	private Button managbutt;

	@FXML
	private Button regbutt;
	@FXML
	private BorderPane myBorderPane;

	@FXML
	void RegisterClick(MouseEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("Register.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			;
			stage.setTitle("Register Page");
			stage.setScene(new Scene(root1));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();

		} catch (Exception e) {
			System.out.println("Error");
		}

	}

	@FXML
	void LoginClick(MouseEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("CustomerLogin.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			// set what you want on your stage
			stage.setTitle("Customer Login");
			stage.setScene(new Scene(root1));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		} catch (Exception e) {
			System.out.println("Error");
		}

	}

	@FXML
	void managmentClick() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("management.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			// set what you want on your stage
			stage.setTitle("Managment Page");
			stage.setScene(new Scene(root1));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		} catch (Exception e) {
			System.out.println("Error");
		}

	}
}