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

public class GUI extends Application {
//	public static Stage newStage;

    	@Override
    	public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("login.fxml"));
            
			stage.setIconified(true);
			
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
//            stage.getIcons().add(icon);
            stage.setTitle("Login Page");
//            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    

    public static void main(String[] args) {
        launch();
    }
}