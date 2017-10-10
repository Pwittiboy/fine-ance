package fineance.framework.screens.components;

import java.util.List;

import fineance.framework.screens.tabledata.StatementsTableData;
import fineance.libraries.entities.Statement;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StatementsTable {
	
	private TableView<StatementsTableData> tv;
	private TableColumn<StatementsTableData, String> tcDate, tcType, tcDescription, tcAccount;
	private TableColumn<StatementsTableData, Double> tcValue, tcBalance;
	
	private ObservableList<StatementsTableData> data; // all data
	
	public StatementsTable(TableView<StatementsTableData> tvStatementsTable,
			TableColumn<StatementsTableData, String> tcDate,
			TableColumn<StatementsTableData, String> tcType,
			TableColumn<StatementsTableData, String> tcDescription,
			TableColumn<StatementsTableData, Double> tcValue,
			TableColumn<StatementsTableData, Double> tcBalance,
			TableColumn<StatementsTableData, String> tcAccount) {
		
		tv = tvStatementsTable;
		this.tcDate = tcDate;
		this.tcType = tcType;
		this.tcDescription = tcDescription;
		this.tcValue = tcValue;
		this.tcBalance = tcBalance;
		this.tcAccount = tcAccount;
	}
	
	public void populateTable(ObservableList<StatementsTableData> data) {
		this.data = data;
		
		tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		tcType.setCellValueFactory(new PropertyValueFactory<>("type"));
		tcDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		tcValue.setCellValueFactory(new PropertyValueFactory<>("value"));
		tcBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));

		tcAccount.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
		
		tv.setItems(data);
	}
	
	public ObservableList<StatementsTableData> getData() {
		return data;
	}
}
