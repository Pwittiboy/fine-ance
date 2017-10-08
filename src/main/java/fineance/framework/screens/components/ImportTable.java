package fineance.framework.screens.components;

import org.hibernate.SessionFactory;

import fineance.libraries.dataaccess.hibernate.HibernateUtil;
import fineance.libraries.entities.Statement;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ImportTable {
	
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	// table items
	private TableView<Statement> tvImport;
//	private TableColumn<Statement, String> tcStatus;
	private TableColumn<Statement, String> tcDate;
	private TableColumn<Statement, String> tcType;
	private TableColumn<Statement, String> tcDescription;
	private TableColumn<Statement, Double> tcValue;
	private TableColumn<Statement, Double> tcBalance;
	private TableColumn<Statement, String> tcAccount;

	private ObservableList<Statement> data;
	
	public ImportTable(TableView<Statement> tvImport, TableColumn tcDate, TableColumn tcType,
			TableColumn tcDescription, TableColumn tcValue, TableColumn tcBalance) {
		this.tvImport = tvImport;
		this.tcDate = tcDate;
		this.tcType = tcType;
		this.tcDescription = tcDescription;
		this.tcValue = tcValue;
		this.tcBalance = tcBalance;
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
	
	public void populateTable(ObservableList<Statement> data) {
		tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		tcType.setCellValueFactory(new PropertyValueFactory<>("type"));
		tcDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		tcValue.setCellValueFactory(new PropertyValueFactory<>("value"));
		tcBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
		
		tvImport.setItems(data);
		
	}

}
