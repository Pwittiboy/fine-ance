package fineance.framework.screens.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import fineance.framework.ControlledScreen;
import fineance.framework.ScreensController;
import fineance.framework.ScreensFramework;
import fineance.framework.screens.components.AccountsTable;
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
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initHomeButton();
		initAddButton();
		initAccountsTable();
	}
	
	private void initHomeButton() {
		lblPwittiboy.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				myController.setScreen(ScreensFramework.HOME_SCREEN_ID);
			}
			
		});
	}
	
	private void initAddButton() {
		imgPlus.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				//TODO
			}
			
		});
	}
	
	private void initAccountsTable() {
		table = new AccountsTable(tvAccounts, tcProvider, tcAccountNumber);
		Runnable task = () -> {
			table.populateTable();
			
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					// TODO pb.setProgress(0);
				}
			});
		};
		
		Thread t = new Thread(task);
		t.start();
	}

}
