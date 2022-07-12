package com.bank.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmployeePanelController {
	
	public static Stage CustomerListforEmployee;
	
	@FXML
	void CustomerList()
	{
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("CustomersList.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			CustomerListforEmployee = new Stage();
			CustomerListforEmployee.setTitle("Customer List");
			CustomerListforEmployee.setScene(new Scene(root1));
			CustomerListforEmployee.initModality(Modality.APPLICATION_MODAL);
			CustomerListforEmployee.getIcons().add(new Image("file:icons/ManagmentLogo.png"));		
			
			CustomerListforEmployee.show();		
			ManagementController.EmployeeStage.close();
			CustomersListPanelController.setStage(ManagementController.EmployeeStage,CustomerListforEmployee);
			
			
			
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@FXML
    void Previous() {
        ManagementController.EmployeeStage.close();
        loginController.ManageMentStage.show();
    }

}
