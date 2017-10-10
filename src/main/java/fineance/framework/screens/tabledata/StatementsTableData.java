package fineance.framework.screens.tabledata;

import fineance.libraries.entities.Statement;
import fineance.utils.DateFormatter;

public class StatementsTableData {
	
	private String date;
	private String type;
	private String description;
	private double value;
	private double balance;
	private String accountNumber;
	
	public StatementsTableData(Statement statement) {
		date = DateFormatter.formatEpochToString(statement.getDate());
		type = statement.getType();
		description = statement.getDescription();
		
		value = statement.getValue();
		balance = statement.getBalance();

		accountNumber = statement.getAccount().getAccountNumber();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
