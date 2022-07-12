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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class loginController {

	public static Stage Regstage;
	public static Stage Loginstage;
	public static Stage ManageMentStage;

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
			Regstage = new Stage();
			Regstage.setTitle("Register Page");
			Regstage.setScene(new Scene(root1));
			Regstage.initModality(Modality.APPLICATION_MODAL);
			Regstage.getIcons().add(new Image("file:icons/RegisterFormLogo.png"));
			Regstage.show();
			GUI.preStage.close();

		} catch (Exception e) {
			System.out.println("Error");
		}

	}

	@FXML
	void LoginClick(MouseEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("CustomerLogin.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Loginstage = new Stage();
			Loginstage.setTitle("Customer Login");
			Loginstage.setScene(new Scene(root1));
			Loginstage.initModality(Modality.APPLICATION_MODAL);
			Loginstage.show();
			Loginstage.getIcons().add(new Image("file:icons/CustomerLogo.png"));
			GUI.preStage.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@FXML
	void managmentClick() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("management.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			ManageMentStage = new Stage();
			ManageMentStage.setTitle("Managment Page");
			ManageMentStage.setScene(new Scene(root1));
			ManageMentStage.initModality(Modality.APPLICATION_MODAL);
			ManageMentStage.getIcons().add(new Image("file:icons/ManagmentLogo.png"));
			ManageMentStage.show();
			GUI.preStage.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}