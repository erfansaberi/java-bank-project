package com.bank.gui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;
public class test1 {
    @FXML
    private Text mytext;

    @FXML
    private AnchorPane root;
    
    @FXML
    void got() 
    {
    	try
    	{
    		AnchorPane pane =  FXMLLoader.load(GUI.getClass().getResource("pakh.fxml"));
        	root.getChildren().setAll(pane);
    	}
    	catch(Exception e)
    	{
    		System.out.println("SDfsd");
    	}
    }
}
