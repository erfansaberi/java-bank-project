package com.bank.gui;

import com.bank.logics.CustomerLogics;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {
    @FXML
    private PasswordField password;

    @FXML
    private TextField IDnum;

    @FXML
    private DatePicker birthDay;

    @FXML
    private PasswordField ConfrimPass;
    
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
    private Label RegError;
    
    @FXML
    private RadioButton MaleGender;
    @FXML
    private Label SucMes;

    @FXML
    void Previous() {
        loginController.Regstage.close();
        GUI.preStage.show();
    }
   @FXML
   void Registor()
   {
//	   System.out.println(birthDay.getValue());
	   if(FemaleGender.isSelected())
	   {
		  
		   String status = CustomerLogics.customerRegister(fname.getText(),lname.getText(),emailAd.getText(),phnum.getText(),password.getText(),ConfrimPass.getText(),birthDay.getValue().toString(),"FEMALE",IDnum.getText()).toString();
		  if(!status.equals("SUCCESS"))
		  {
			  RegError.setText(status);
			  SucMes.setText(" ");
		  }
			 
		  else
		  {
			  SucMes.setText("Registered Successfully");
			  RegError.setText(" ");
		  }
			  
	   }
		       
	   if(MaleGender.isSelected())
	   {
		   
		   String status = CustomerLogics.customerRegister(fname.getText(),lname.getText(),emailAd.getText(),phnum.getText(),password.getText(),ConfrimPass.getText(),birthDay.getValue().toString(),"MALE",IDnum.getText()).toString();
			  if(!status.equals("SUCCESS"))
			  {
				  RegError.setText(status);
			      SucMes.setText(" ");
			  }
				  
			  else
			  {
				  SucMes.setText("Registered Successfully");
				  RegError.setText(" ");
			  }
	   }
		   
   }
   
    
}
