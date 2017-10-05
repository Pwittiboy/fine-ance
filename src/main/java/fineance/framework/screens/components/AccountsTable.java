package fineance.framework.screens.components;

import java.util.List;

import org.hibernate.SessionFactory;

import fineance.libraries.dataaccess.dao.impl.AccountDAOImpl;
import fineance.libraries.dataaccess.hibernate.HibernateUtil;
import fineance.libraries.entities.Account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.impl.ItemServiceImpl;

public class AccountsTable {
	
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	// DAO
	private AccountDAOImpl adao = new AccountDAOImpl(sessionFactory);
	
	// Table items
	private TableView<Account> tv;
	private TableColumn<Account, String> tcProvider;
	private TableColumn<Account, String> tcAccountNumber;
	private ObservableList<Account> data;
	
	List<Account> accounts;
	
	public AccountsTable(TableView<Account> tv,
			TableColumn<Account, String> tcProvider,
			TableColumn<Account, String> tcAccountNumber) {
		this.tv = tv;
		this.tcProvider = tcProvider;
		this.tcAccountNumber = tcAccountNumber;
	}
	
	public void populateTable() {
		data = FXCollections.observableArrayList();
		
		accounts = ItemServiceImpl.getAccountDAO().findAll();
		data.addAll(accounts);
		
		// map data to columns
		tcProvider.setCellValueFactory(new PropertyValueFactory<Account, String>("provider"));
		tcAccountNumber.setCellValueFactory(new PropertyValueFactory<Account, String>("accountNumber"));
		
		// populate table with data
		tv.setItems(data);
	}

}
