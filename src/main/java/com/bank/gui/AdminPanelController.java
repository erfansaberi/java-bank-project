package com.bank.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AdminPanelController {
	public static Stage EmplooyeeList;
	public static Stage CustomerListForAdmin;
	public static Stage AccountListforAdmin;

	@FXML
	void Previous() {
		ManagementController.AdminStage.close();
		loginController.ManageMentStage.show();
	}
	@FXML
	void EmployeeList() throws Exception
	{
		FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("EmployeeList.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		EmplooyeeList = new Stage();
		EmplooyeeList.setTitle("Employee List");
		EmplooyeeList.setScene(new Scene(root1));
		EmplooyeeList.initModality(Modality.APPLICATION_MODAL);
		EmplooyeeList.getIcons().add(new Image("file:src/main/resources/com/bank/icons/ManagmentLogo.png"));
		EmplooyeeList.show();
		ManagementController.AdminStage.close();
		
	
	}
	@FXML
	void CustomerList()
	{

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("CustomersList.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			CustomerListForAdmin = new Stage();
			CustomerListForAdmin.setTitle("Customer List");
			CustomerListForAdmin.setScene(new Scene(root1));
			CustomerListForAdmin.initModality(Modality.APPLICATION_MODAL);
			CustomerListForAdmin.getIcons().add(new Image("file:src/main/resources/com/bank/icons/ManagmentLogo.png"));
			CustomerListForAdmin.show();
			ManagementController.AdminStage.close();
			CustomersListPanelController.setStage(ManagementController.AdminStage,CustomerListForAdmin);
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	
	}
	@FXML
	void AccountList()
	{
		try
		{
			FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("AccountList.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			AccountListforAdmin = new Stage();
			AccountListforAdmin.setTitle("Customer List");
			AccountListforAdmin.setScene(new Scene(root1));
			AccountListforAdmin.initModality(Modality.APPLICATION_MODAL);
			AccountListforAdmin.getIcons().add(new Image("file:src/main/resources/com/bank/icons/ManagmentLogo.png"));
			AccountListforAdmin.show();
			ManagementController.AdminStage.close();
			AccountListController.setStage(ManagementController.AdminStage,AccountListforAdmin);
		}
		 catch (Exception e) {
				System.out.println(e.getMessage());
			}
		
	}
}
