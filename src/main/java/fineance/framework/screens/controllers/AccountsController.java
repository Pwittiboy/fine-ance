package fineance.framework.screens.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import fineance.framework.ControlledScreen;
import fineance.framework.ScreensController;
import fineance.framework.ScreensFramework;
import fineance.framework.screens.components.AccountsTable;
import fineance.framework.screens.components.HomeButton;
import fineance.libraries.entities.Account;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class AccountsController implements Initializable, ControlledScreen {
	
	ScreensController myController;
	
	@FXML
	private Label lblPwittiboy;
	
	@FXML
	private ImageView imgPlus;
	
	@FXML
	private TableView<Account> tvAccounts;
	
	@FXML
	private TableColumn<Account, String> tcProvider, tcAccountNumber;
	
	private AccountsTable table = null;

	@Override
	public void setScreenParent(ScreensController screenPage) {
		myController = screenPage;
		
		// once we have controller, we can set up components that depend on it
		initComponents();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	private void initComponents() {
		HomeButton.init(lblPwittiboy, myController);
		table = AccountsTable.init(tvAccounts, tcProvider, tcAccountNumber, imgPlus);
	}
	
}
