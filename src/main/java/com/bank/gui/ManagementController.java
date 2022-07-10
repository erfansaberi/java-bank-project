package com.bank.gui;

import com.bank.views.ManagementViews;
import com.bank.models.Admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ManagementController {
	enum accesslevel {admin,employee,none};
	
	  @FXML
	    private TextField EmailManageAuth;

	    @FXML
	    private Button ManageLogin;

	    @FXML
	    private PasswordField PasswordManageAuth;

	    @FXML
	    private Label UsPasError;
    
    @FXML
    void authentication()
    {
//    	if(!Employee.authenticate(EmailManageAuth.getText(),PasswordManageAuth.getText()))
  //  		UsPasError.setText("Username Or Password is Wrong");
//    	System.out.println(ManagementViews.authenticate(EmailManageAuth.getText(),PasswordManageAuth.getText()));
    	if(ManagementViews.authenticate(EmailManageAuth.getText(),PasswordManageAuth.getText()).toString().equals("NONE"))
    		  UsPasError.setText("Username Or Password is Wrong");	
    	else
    		System.out.println("amsdsd");
    	

    		
    }
   
    @FXML
   	void Previous()
   	{
    	   loginController.ManageMentStage.close();
           GUI.preStage.show();
   	}
}
