package com.bank.gui;

import com.bank.models.Employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import com.bank.models.Customer;

public class CustomersListPanelController {
	public static Stage PreStage;
	public static Stage CurrentStage;
	
     public CustomersListPanelController()
     {
    	 
     }
	
	    @FXML
	    private  TableView<Customer> CustomersTable;

	    @FXML
	    private  TableColumn<Customer, String> StatusCol;

	    @FXML
	    private  TableColumn<Customer, String> emailCol;

	    @FXML
	    private  TableColumn<Customer, String> fnameCol;

	    @FXML
	    private  TableColumn<Customer, String> genderCol;
	    
	    @FXML
	    private  TableColumn<Customer, Double> BalanceCol;
	    
	    @FXML 
	    private  TableColumn<Customer, String> lnameCol;

	    @FXML
	    private  TableColumn<Customer, String> passCol;

	    @FXML
	    private  TableColumn<Customer, String> phoneCol;

	    @FXML
	    void CustomerPrevious() {
	    	CurrentStage.close();
	    	PreStage.show();          
	    }
	    
	    
//	    
	    @FXML
	     void CustomerLoader()
	    {
	   
	    	fnameCol.setCellValueFactory(new PropertyValueFactory<Customer , String>("firstName"));  
	    	lnameCol.setCellValueFactory(new PropertyValueFactory<Customer , String>("lastName"));  
	    	emailCol.setCellValueFactory(new PropertyValueFactory<Customer , String>("email")); 
	    	phoneCol.setCellValueFactory(new PropertyValueFactory<Customer , String>("phoneNumber")); 
	    	genderCol.setCellValueFactory(new PropertyValueFactory<Customer , String>("gender"));
	    	passCol.setCellValueFactory(new PropertyValueFactory<Customer , String>("password")); 
	    	BalanceCol.setCellValueFactory(new PropertyValueFactory<Customer , Double>("safeBoxBalance")); 
	    	StatusCol.setCellValueFactory(new PropertyValueFactory<Customer , String>("Status"));
	    	
	    	
	    	for(int i = 0;i<Customer.getAllCustomers().size();i++)
	    	       CustomersTable.getItems().add(Customer.getAllCustomers().get(i));
	   
}	
	    
	    static void setStage(Stage preStage,Stage currentStage)
	    {
	    	PreStage = preStage;
	    	CurrentStage = currentStage;
	    	
	    	
	    }
}
	    	
//	    	ObservableList<Customer> myCus = FXCollections.observableArrayList(
//	    			Customer.getAllCustomers().get(0),
//	    			Customer.getAllCustomers().get(1),
//	    			Customer.getAllCustomers().get(2)
//	    			);
//	    	CustomersTable.setItems(myCus);
	    	
//	    	for(int i = 0;i<Customer.getAllCustomers().size();i++)
//	             System.out.println(Customer.getAllCustomers().get(i));
//	    	
	    	
//	    }
	   

