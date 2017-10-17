package fineance.framework.screens.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fineance.framework.ControlledScreen;
import fineance.framework.ScreensController;
import fineance.framework.screens.components.AccountsTable;
import fineance.framework.screens.components.BalanceChart;
import fineance.framework.screens.components.ExpenditureChart;
import fineance.framework.screens.components.HomeButton;
import fineance.framework.screens.components.StatementsTable;
import fineance.framework.screens.tabledata.StatementsTableData;
import fineance.libraries.entities.Account;
import fineance.libraries.entities.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import services.impl.ItemServiceImpl;

public class AccountsController implements Initializable, ControlledScreen {
	
	ScreensController myController;
	
	@FXML
	private Label lblPwittiboy;
	
	@FXML
	private ImageView imgPlus;
	
	@FXML
	private TableView<Account> tvAccounts;
	
	@FXML
	private TableView tvStatements;
	
	@FXML
	private TableColumn<Account, String> tcProvider, tcAccountNumber;
	
	@FXML
	private TableColumn tcDate, tcType, tcDescription, tcValue, tcBalance, tcAccount;
	
	@FXML
	private AreaChart achartBalance, achartExpenditure;
	
	private AccountsTable table = null;
	private StatementsTable statementsTable = null;
	private BalanceChart balanceChart = null;
	private ExpenditureChart expenditureChart = null;
	
	private List<Statement> statements;
	private ObservableList<StatementsTableData> data;
	
	private XYChart.Series series;

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
		setTableListener();
	}
	
	private void setTableListener() {
		tvAccounts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				populateStatementsTable(tvAccounts.getSelectionModel().getSelectedItem());
				populateCharts();
			}
		});
	}
	
	public void populateStatementsTable(Account account) {
		statementsTable = new StatementsTable(tvStatements, tcDate, tcType, tcDescription, tcValue, tcBalance, tcAccount);
		
		data = FXCollections.observableArrayList();
		statements = ItemServiceImpl.getStatementDAO().findByAccount(account);
		
		for (Statement s : statements) {
			data.add(new StatementsTableData(s));
		}
		
		statementsTable.populateTable(data);
	}
	
	public void populateCharts() {
		
		// remove data if present
		if (balanceChart != null && !achartBalance.getData().isEmpty()) achartBalance.getData().clear();;
		if (expenditureChart != null && !achartExpenditure.getData().isEmpty()) achartExpenditure.getData().clear();
		
		// instantiate the charts
		if (balanceChart == null) balanceChart = new BalanceChart(achartBalance);
		
		Runnable task = () -> {
			balanceChart.populate();
		};
		
		Thread t = new Thread(task);
		t.start();
	}
	
}
