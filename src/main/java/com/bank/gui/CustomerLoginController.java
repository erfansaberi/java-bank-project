package com.bank.gui;

import javafx.fxml.FXML;
import com.bank.models.Customer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerLoginController {

	@FXML
	private Button LoginButt;
	
	@FXML
    private Label ErrorLable;

	@FXML
	private PasswordField PassConfirm;
	

	@FXML
	private TextField PhoneConfirm;

	@FXML
	void loginConfirmed() {
		if(Customer.authenticate(PhoneConfirm.getText(),PassConfirm.getText()))
		{
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("Dashboard.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setTitle("Dashboard Panel");
				stage.setScene(new Scene(root1));
				stage.show();
			} catch (Exception e) {
				System.out.println("Error");
			}
		}
		else
			ErrorLable.setText("PhoneNumber or Password is Wrong!");



	}

	@FXML
	void register() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("Register.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Register Page");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			System.out.println("Error");
		}
	}
	@FXML
	void Previous() {
		loginController.Loginstage.close();
		GUI.preStage.show();
	}
}
