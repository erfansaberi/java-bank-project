package com.bank.gui;
import com.bank.models.Account;
import com.bank.models.Customer;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AccountListController {
	public static Stage PreStage;
	public static Stage CurrentStage;

	@FXML
    private TableColumn<Account, String> BalanceCol;

    @FXML
    private TableView<Account> AccountsTable;

    @FXML
    private TableColumn<Account, String> DateCol;

    @FXML
    private TableColumn<Account, Long> IdCol;

    @FXML
    private TableColumn<Account, Customer> OwnerCol;

    @FXML
    private TableColumn<Account, String> StatusCol;

    @FXML
    private TableColumn<Account, Account> TypeCol;

    

	    @FXML
	    void AccountLoader(MouseEvent event) {

	    	IdCol.setCellValueFactory(new PropertyValueFactory<Account , Long>("id"));  
	    	TypeCol.setCellValueFactory(new PropertyValueFactory<Account , Account>("type"));  
	    	OwnerCol.setCellValueFactory(new PropertyValueFactory<Account , Customer>("owner")); 
	    	DateCol.setCellValueFactory(new PropertyValueFactory<Account , String>("creationDate")); 
	    	BalanceCol.setCellValueFactory(new PropertyValueFactory<Account , String>("balance"));; 
	    	StatusCol.setCellValueFactory(new PropertyValueFactory<Account , String>("status"));
	    	
	    	
	    	for(int i = 0;i<Account.getAllAccounts().size();i++)
	    	       AccountsTable.getItems().add(Account.getAllAccounts().get(i));
	    }

	    @FXML
	    void AccountPrevious(MouseEvent event) {
	    	CurrentStage.close();
	    	PreStage.show(); 
	    }
	    static void setStage(Stage preStage,Stage currentStage)
	    {
	    	PreStage = preStage;
	    	CurrentStage = currentStage;	
	    }
}
