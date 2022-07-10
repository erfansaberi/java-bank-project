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

	public static Stage CustomerLoginStage;
	public static Stage CustomerRegisterStage;
	
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
    			CustomerLoginStage = new Stage();
    			//set what you want on your stage
    			CustomerLoginStage.setTitle("Dashboard Panel");
    			CustomerLoginStage.setScene(new Scene(root1));
    			CustomerLoginStage.show();
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
   	void Previous()
   	{
    	   loginController.Loginstage.close();
           GUI.preStage.show();
   		
   	}
    @FXML
    void register()
    {
    	try
		{
			FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("Register.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			CustomerRegisterStage = new Stage();
			//set what you want on your stage
			CustomerRegisterStage.setTitle("Register Page");
			CustomerRegisterStage.setScene(new Scene(root1));
			CustomerRegisterStage.show();
			
		}
		catch(Exception e)
		{
			System.out.println("Error");
		}
    }
}
