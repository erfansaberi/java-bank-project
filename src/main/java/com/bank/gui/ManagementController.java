package com.bank.gui;

import com.bank.logics.ManagementLogics;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ManagementController {
	public static Stage AdminStage;
	public static Stage EmployeeStage;
	enum accesslevel {
		admin, employee, none
	};

	@FXML
	private TextField EmailManageAuth;

	@FXML
	private Button ManageLogin;

	@FXML
	private PasswordField PasswordManageAuth;

	@FXML
	private Label UsPasError;

	@FXML
	void authentication() {
		
		if(ManagementLogics.authenticate(EmailManageAuth.getText(), PasswordManageAuth.getText()).toString().equals("EMPLOYEE"))
		{
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("EmployeePanel.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				EmployeeStage = new Stage();
				EmployeeStage.setTitle("Employee Panel");
				EmployeeStage.setScene(new Scene(root1));
				EmployeeStage.initModality(Modality.APPLICATION_MODAL);
				EmployeeStage.getIcons().add(new Image("file:icons/ManagmentLogo.png"));
				EmployeeStage.show();
				loginController.ManageMentStage.close();
				

			} catch (Exception e) {
				System.out.println("Error");
			}
		}
		else if(ManagementLogics.authenticate(EmailManageAuth.getText(), PasswordManageAuth.getText()).toString().equals("ADMIN"))
		{
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("AdminPanel.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				AdminStage = new Stage();
				AdminStage.setTitle("Admin Panel");
				AdminStage.setScene(new Scene(root1));
				AdminStage.initModality(Modality.APPLICATION_MODAL);
				AdminStage.getIcons().add(new Image("file:icons/ManagmentLogo.png"));
				AdminStage.show();
				loginController.ManageMentStage.close();
				UsPasError.setText(" ");

			} catch (Exception e) {
				System.out.println("Error");
			}
		}
		else
			UsPasError.setText("Username Or Password is Wrong");
			
	}

	@FXML
	void Previous() {
		loginController.ManageMentStage.close();
		GUI.preStage.show();
	}
}
