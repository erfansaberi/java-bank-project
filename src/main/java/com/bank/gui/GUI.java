package com.bank.gui;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import java.io.IOException;

import com.bank.models.Core;

public class GUI extends Application {
	
	public static Stage preStage;

    	@Override
    	public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            stage.setTitle("Login Page");
            Image myimg = new Image("file:icons/loginLogo.png");
            stage.getIcons().add(myimg);
            stage.setScene(scene);
            stage.show();
            preStage = stage;
        }
    

    public static void main(String[] args) {
        Core.startup();
        launch();
        Core.shutdown();
    }
}