package fineance.framework.screens.tabledata;

import fineance.libraries.entities.Statement;
import fineance.utils.DateFormatter;

public class ImportTableData {

	private String status;
	private String date;
	private String type;
	private String description;
	private String value; // using String to concatenate a £ sign
	private String balance;
	private String accountNumber; // accountNumber

	public ImportTableData(String status, Statement statement) {
		this.status = status;

		date = DateFormatter.formatEpochToString(statement.getDate());
		type = statement.getType();
		description = statement.getDescription();
		value = "£"+statement.getValue();
		balance = "£"+statement.getBalance();

		accountNumber = statement.getAccount().getAccountNumber();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
