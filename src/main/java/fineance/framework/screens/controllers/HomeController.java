package fineance.framework.screens.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import fineance.framework.ControlledScreen;
import fineance.framework.ScreensController;
import fineance.framework.ScreensFramework;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class HomeController implements Initializable, ControlledScreen {
	
	ScreensController myController;
	
	@Override
	public void setScreenParent(ScreensController screenPage) {
		myController = screenPage;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void goToAccountsScreen(ActionEvent event) {
		myController.setScreen(ScreensFramework.ACCOUNTS_SCREEN_ID);
	}
	
	@FXML
	public void goToImportingScreen(ActionEvent event) {
		myController.setScreen(ScreensFramework.IMPORTING_SCREEN_ID);
	}
	
	@FXML
	public void goToReportingScreen(ActionEvent event) {
		myController.setScreen(ScreensFramework.REPORTING_SCREEN_ID);
	}
	
}
