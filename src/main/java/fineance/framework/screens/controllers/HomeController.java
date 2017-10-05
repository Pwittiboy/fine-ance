package fineance.framework.screens.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fineance.framework.ControlledScreen;
import fineance.framework.ScreensController;
import fineance.framework.ScreensFramework;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeController implements Initializable, ControlledScreen {
	
	ScreensController myController;
	
	@FXML
	private Button btnAccounts, btnImporting, btnReporting;
	
	@FXML
	private Label lblHoverTip, lblActivity, lblDescription;
	
	
	@Override
	public void setScreenParent(ScreensController screenPage) {
		myController = screenPage;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initializeButtons();
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
	
	private void initializeButtons() {
		List<Button> buttons = new ArrayList();
		buttons.add(btnAccounts);
		buttons.add(btnImporting);
		buttons.add(btnReporting);
		
		for(Button b : buttons) {
			b.hoverProperty().addListener((o) -> {
				if (b.isHover()) {
					lblHoverTip.setVisible(false);
					setButtonDescription(b.getText());
				}
			});
		}
		
	}
	
	private void setButtonDescription(String buttonText) {
		switch (buttonText) {
			case "Accounts" : 
				lblActivity.setText("Account Manager");
				lblDescription.setText("Create, delete, and manage your accounts.");
				break;
			case "Importing" :
				lblActivity.setText("Importing");
				lblDescription.setText("Select a bank provider and import your bank statements.");
				break;
			case "Reporting" : 
				lblActivity.setText("Reporting");
				lblDescription.setText("Create visual reports from your financial data.");
				break;
		}
	}
	
}
