package fineance.framework.screens.components;

import org.hibernate.SessionFactory;

import fineance.framework.screens.tabledata.ImportTableData;
import fineance.libraries.dataaccess.hibernate.HibernateUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ImportTable {
	
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	private TableView<ImportTableData> tvImport;
	private TableColumn<ImportTableData, String> tcStatus;
	private TableColumn<ImportTableData, String> tcDate;
	private TableColumn<ImportTableData, String> tcType;
	private TableColumn<ImportTableData, String> tcDescription;
	private TableColumn<ImportTableData, String> tcValue;
	private TableColumn<ImportTableData, String> tcBalance;
	private TableColumn<ImportTableData, String> tcAccount;

	private ObservableList<ImportTableData> data;
	
	public ImportTable(TableView tvImport, TableColumn tcStatus, TableColumn tcDate, TableColumn tcType,
			TableColumn tcDescription, TableColumn tcValue, TableColumn tcBalance, TableColumn tcAccount) {
		
		this.tvImport = tvImport;
		
		this.tcStatus = tcStatus;
		this.tcDate = tcDate;
		this.tcType = tcType;
		this.tcDescription = tcDescription;
		this.tcValue = tcValue;
		this.tcBalance = tcBalance;
		
		this.tcAccount = tcAccount;
		
		tableSetup();
	}
	
	private void tableSetup() {
		tvImport.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
	public static ImportTable init(TableView tvImport, TableColumn tcDate, TableColumn tcType,
			TableColumn tcDescription, TableColumn tcValue, TableColumn tcBalance, TableColumn tcAccount) {
		// TODO Auto-generated method stub
		
		
		return null;
	}
	
	public void populateTable(ObservableList<ImportTableData> data) {
		tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		tcType.setCellValueFactory(new PropertyValueFactory<>("type"));
		tcDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		tcValue.setCellValueFactory(new PropertyValueFactory<>("value"));
		tcBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));

		tcAccount.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
		
		tvImport.setItems(data);
		
	}
	
}
