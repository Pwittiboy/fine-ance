package fineance.framework.screens.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import fineance.framework.ControlledScreen;
import fineance.framework.ScreensController;
import fineance.framework.screens.components.AccountsTable;
import fineance.framework.screens.components.HomeButton;
import fineance.framework.screens.components.ImportTable;
import fineance.libraries.entities.Account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.impl.ImportServiceImpl;

public class ImportingController implements Initializable, ControlledScreen {

	ScreensController myController;

	private static final Logger LOGGER = Logger.getLogger(ImportingController.class);
	private static ImportServiceImpl importService = new ImportServiceImpl();

	@FXML
	private Label lblPwittiboy;
	
	@FXML //stats
	private Label lblTransactionsImported, lblExistingTransactionsSkipped, lblDuration, lblTransactionsPerSecond;

	@FXML
	private ImageView imgPlus;

	@FXML
	private TableView<Account> tvAccounts;

	@FXML
	private TableColumn<Account, String> tcProvider, tcAccountNumber;

	@FXML
	private ComboBox<String> cbFileType;

	@FXML
	private TextField tfImportFile;

	@FXML
	private VBox vboxPbp;

	@FXML
	private Button btnUpload, btnGo;
	
	@FXML
	private TableView tvImport;
	
	@FXML
	private TableColumn tcStatus, tcDate, tcType, tcDescription, tcValue, tcBalance, tcAccount;

	private AccountsTable table = null;
	private ImportTable importTable = null;

	private File temp;

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
//		importTable = ImportTable.init(tvImport, tcDate, tcType, tcDescription, tcValue, tcBalance, tcAccount);
		cbFileTypePopulate();
	}

	@FXML
	private void btnUploadAction() {
		Stage fcStage = new Stage();

		FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle("Open Import File");

		File file = fileChooser.showOpenDialog(fcStage);

		if(file != null) {

			LOGGER.info("File type: "+cbFileType.getValue());
			tfImportFile.setText(file.getAbsolutePath());

			try {
				// prepare a temp file to write to
				temp = File.createTempFile("fine-ance", ".tmp");
				BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
				LOGGER.info("Temp file absolute path: "+temp.getAbsolutePath());

				// read contents of import file and write to bw
				LOGGER.info("Replacement path for import file: " + tfImportFile.getText().replace("\\", "//"));
				BufferedReader br = new BufferedReader(new FileReader(tfImportFile.getText().replace("\\", "//")));
				String s = br.readLine();
				while (s!=null) {
					bw.write((s+"\n"));
					s = br.readLine();
				}
				br.close();
				bw.close();
			} catch (IOException e) {
				LOGGER.error("Import failed");
				e.printStackTrace();
			}

			btnGo.setDisable(false);
		}
	}

	@FXML
	private void btnGoAction() {
		try {
			readCSV();
			updateStats();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		btnGo.setDisable(true);
	}

	private void readCSV() throws FileNotFoundException {
		switch(String.valueOf(cbFileType.getValue())) {
			case "Natwest" : {
				importService.readCSVNatwest(temp);
				break;
			}
		}
		importTable = new ImportTable(tvImport, tcDate, tcType, tcDescription, tcValue, tcBalance);
		importTable.populateTable(importService.getData());
	}
	
	private void cbFileTypePopulate() {
		ObservableList<String> options = FXCollections.observableArrayList(
				"Natwest",
				"Virgin"
				);
		cbFileType.getItems().addAll(options);
	}
	
	private void updateStats() {
		lblTransactionsImported.setText(String.valueOf(importService.getTransactionsImported()));
		lblExistingTransactionsSkipped.setText(String.valueOf(importService.getExistingTransactionsSkipped()));
		lblDuration.setText(String.valueOf(importService.getDuration()));
		lblTransactionsPerSecond.setText(String.valueOf(importService.getTransactionsImportedPerSecond()));
	}

}
