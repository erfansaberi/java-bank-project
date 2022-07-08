package com.bank.gui;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class DashboardController {

    @FXML
    private Pane AcPane;

    @FXML
    private Pane ExitPane;

    @FXML
    private Pane HomePane;

    @FXML
    private Pane TransPane;
  
    @FXML
    void homechecker()
    {
    	System.out.println("Home Check");
    }
    @FXML
    void Exchecker()
    {
    	System.out.println("Exit Check");
    }
    @FXML
    void Acchecker()
    {
    	System.out.println("Account Check");
    }
    @FXML
    void Transchecker()
    {
    	System.out.println("Trans Check");
    }
    
}