package com.bank.gui;

import com.bank.models.Employee;
import com.bank.models.Admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ManagementController {
	  @FXML
	    private TextField EmailManageAuth;

	    @FXML
	    private Button ManageLogin;

	    @FXML
	    private PasswordField PasswordManageAuth;

	    @FXML
	    private Label UsPasError;
    
    @FXML
    void Adminauthentication()
    {
//    	if(!Employee.authenticate(EmailManageAuth.getText(),PasswordManageAuth.getText()))
  //  		UsPasError.setText("Username Or Password is Wrong");
    		
    	if(Admin.authenticate(EmailManageAuth.getText(),PasswordManageAuth.getText()))
    		UsPasError.setText("Success!");
    	else
    		UsPasError.setText("Username Or Password is Wrong");	
    		
    }
    @FXML
    void Employeeauthentication()
    {
    	
    }
    
}
