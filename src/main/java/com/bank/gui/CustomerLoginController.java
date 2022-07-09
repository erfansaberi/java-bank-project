package com.bank.gui;

import com.bank.models.Customer;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerLoginController {

    @FXML
    private Button LoginButt;

    @FXML
    private PasswordField PassConfirm;

    @FXML
    private TextField PhoneConfirm;

    @FXML
    private Label ErrorLable;

    @FXML
    void loginConfirmed()
    
    {
//    	System.out.println(PhoneConfirm.getText().length());
    	System.out.println(Customer.authenticate(PhoneConfirm.getText(),PassConfirm.getText()));
    	try
		{
//    		if(Customer.authenticate(PhoneConfirm.getText(),PassConfirm.getText()))
//    		{
    			FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("Dashboard.fxml"));
    			Parent root1 = (Parent) fxmlLoader.load();
    			Stage stage = new Stage();
    			//set what you want on your stage
    			stage.setTitle("Dashboard Panel");
    			stage.setScene(new Scene(root1));
    			stage.show();
    			loginController.Loginstage.close();
    		}
//    		else
//    			ErrorLable.setText("Phone Number or Password is Wrong");
//    		
//			
//		}
		catch(Exception e)
		{
			System.out.println("Error");
		}	    	
		
    }
    @FXML
    void register()
    {
    	try
		{
			FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("Register.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			//set what you want on your stage
			stage.setTitle("Register Page");
			stage.setScene(new Scene(root1));
			stage.show();
		}
		catch(Exception e)
		{
			System.out.println("Error");
		}
    }
}
