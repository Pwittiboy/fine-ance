package fineance.framework.screens.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import fineance.framework.ControlledScreen;
import fineance.framework.ScreensController;
import fineance.framework.ScreensFramework;
import fineance.utils.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;

public class LoginController implements Initializable, ControlledScreen {
	
	ScreensController myController;
	Effect glow = new Glow(1.0);
	
	@FXML
	private TextField tfUsername;
	
	@FXML
	private PasswordField pfPassword;
	
	@FXML
	private Button btnLogin;
	
	@FXML
	private Label lblStatus;
	
	@FXML
	private void onEnter(ActionEvent ae) throws Exception {
		login(ae);
	}
	
	public void login(ActionEvent ae) throws Exception {
		lblStatus.setVisible(true);
		if (tfUsername.getText().equals("master") && pfPassword.getText().equals("password")) {
			
			if (DbConnect.connect()) {
				lblStatus.setText("Login Successful");
				
				myController.setScreen(ScreensFramework.HOME_SCREEN_ID);
			} else {
				lblStatus.setText("Database Error");
				lblStatus.setTextFill(Color.RED);
				lblStatus.setEffect(glow);
			}
		} else {
			lblStatus.setText("Login Failed");
			lblStatus.setTextFill(Color.RED);
			lblStatus.setEffect(glow);
		}
	}

	@Override
	public void setScreenParent(ScreensController screenPage) {
		myController = screenPage;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	

}
