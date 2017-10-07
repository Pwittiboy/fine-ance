package fineance.framework.screens.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import fineance.framework.ControlledScreen;
import fineance.framework.ScreensController;
import fineance.framework.screens.components.AccountsTable;
import fineance.framework.screens.components.HomeButton;
import fineance.libraries.entities.Account;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ImportingController implements Initializable, ControlledScreen {

	ScreensController myController;
	
	@FXML
	private Label lblPwittiboy;
	
	@FXML
	private ImageView imgPlus;
	
	@FXML
	private TableView<Account> tvAccounts;
	
	@FXML
	private TableColumn<Account, String> tcProvider, tcAccountNumber;
	
	@FXML
	private ComboBox cbFileType;
	
	@FXML
	private TextField tfImportFile;
	
	@FXML
	private VBox vboxPbp;
	
	private AccountsTable table = null;
	
	@Override
	public void setScreenParent(ScreensController screenPage) {
		myController = screenPage;

		// once we have controller, we can set up components that depend on it
		initComponents();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	private void initComponents() {
		HomeButton.init(lblPwittiboy, myController);
		table = AccountsTable.init(tvAccounts, tcProvider, tcAccountNumber, imgPlus);
	}
	
	@FXML
	public void btnGoAction() {
		
	}

}
