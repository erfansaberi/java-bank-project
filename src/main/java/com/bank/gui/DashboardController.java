package com.bank.gui;

import java.util.ArrayList;

import com.bank.models.Customer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import java.util.Random;
import javafx.scene.layout.Pane;
import com.bank.models.Account;
import com.bank.models.Core;

public class DashboardController {
	private static ArrayList<Account> allAccounts;
	private static Customer customer;
	private static String AccountNumber;

	@FXML
    private  Pane AcPane;

	@FXML
    private  Label AccountBalance;

    @FXML
    private  Label AccountNum;

    @FXML
    private  Label CustomerName;

    @FXML
    private  Pane ExitPane;

    @FXML
    private  Pane HomePane;

    @FXML
    private  Pane TransPane;
    @FXML
    private ToggleGroup GenderC;
   
	@FXML
	 void  initiate()
	{
	//	AccountNum.setText(customer.getAllAccounts().get(0).getType());
	//	System.out.println(customer.getAllAccounts().get(0).get);
//		System.out.println(allAccounts);
		AccountNum.setText(AccountNumber);
	//	
//		System.out.println(customer.getActiveAccounts().get(0).getId());
		AccountBalance.setText(String.valueOf(customer.getSafeBoxBalance()) + " $");
		CustomerName.setText(customer.getFirstName() + " " + customer.getLastName());
	}
	@FXML
	void Previous() {
		CustomerLoginController.DashboardStage.close();
		loginController.Loginstage.show();
	}
    
	public static void DashBoardSetter(ArrayList<Account> Myaccount,Customer Mycustomer)
	{
		allAccounts = Myaccount;
	    customer = Mycustomer;
	}
	public static String getRandomNumberString() {
	    // It will generate 6 digit random Number.
	    // from 0 to 999999
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999999);

	    // this will convert any number sequence into 6 character.
	    return String.format("%015d", number);
	}
	public static void ACNsetter(String AN)
	{
		AccountNumber = AN;
	}
	@FXML
	void exitbut()
	{
		Core.shutdown();
		CustomerLoginController.DashboardStage.close();
	}
}