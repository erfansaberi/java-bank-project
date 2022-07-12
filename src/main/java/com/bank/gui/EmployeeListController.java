package com.bank.gui;
import javafx.fxml.FXML;
import com.bank.models.Employee;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
//import com.bank.gui.AdminPanelController;
  
public class EmployeeListController {
	
	   
         
	  @FXML
	    private  TableView<Employee> EmployeeTable;

	    @FXML
	    private  TableColumn<Employee, Double> SalaryCol;

	    @FXML
	    private  TableColumn<Employee, String> StatusCol;

	    @FXML
	    private  TableColumn<Employee, String> emailCol;

	    @FXML
	    private  TableColumn<Employee, String> fnameCol;

	    @FXML
	    private  TableColumn<Employee, String> genderCol;

	    @FXML
	    private  TableColumn<Employee, String> lnameCol;

	    @FXML
	    private  TableColumn<Employee, String> passCol;

	    @FXML
	    private  TableColumn<Employee, String> phoneCol;

	    @FXML
	     void EmployeeLoader()
	    {
//	    	EmployeeTable.getItems().remove(0, EmployeeTable.getItems().size());
	    	fnameCol.setCellValueFactory(new PropertyValueFactory<Employee , String>("firstName"));  
	    	lnameCol.setCellValueFactory(new PropertyValueFactory<Employee , String>("lastName"));  
	    	emailCol.setCellValueFactory(new PropertyValueFactory<Employee , String>("email")); 
	    	phoneCol.setCellValueFactory(new PropertyValueFactory<Employee , String>("phoneNumber")); 
	    	genderCol.setCellValueFactory(new PropertyValueFactory<Employee , String>("gender"));
	    	passCol.setCellValueFactory(new PropertyValueFactory<Employee , String>("password")); 
	    	SalaryCol.setCellValueFactory(new PropertyValueFactory<Employee , Double>("Salary")); 
	    	StatusCol.setCellValueFactory(new PropertyValueFactory<Employee , String>("Status"));
	    	
	    	for(int i = 0;i<Employee.getAllEmployees().size();i++)
	    	         EmployeeTable.getItems().add(Employee.getAllEmployees().get(i));
	    	
	    	
	    	
	    	 
	    }
	    @FXML
	    void RemoveRow()
	    {
//	    EmployeeTable.getItems().removeAll(EmployeeTable.getSelectionModel().getSelectedItems());
	    	
//	    	
//	    	Employee.getAllEmployees().remove(EmployeeTable.getSelectionModel().getSelectedItem());
//	    	EmployeeLoader();
	    }
	    
	   @FXML
	    void EmployeePrevious() {
		      AdminPanelController.EmplooyeeList.close();
              ManagementController.AdminStage.show();
              
    
	
	   
	   }
	   
//	   @SuppressWarnings("exports")
//	public ObservableList<Employee> getEmps()
//	   {
//		   ObservableList<Employee> Employees = FXCollections.observableArrayList();
//		   Employees.add(new Employee(1,"ams1381@ut.ac.ir","ACTIVE"));
//		return Employees;
//		   
//	   }
}
