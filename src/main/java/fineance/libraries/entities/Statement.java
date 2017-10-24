package fineance.libraries.entities;

import java.time.LocalDate;
import java.time.ZoneId;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Statement", uniqueConstraints = @UniqueConstraint(columnNames={
		"date",
		"type",
		"description",
		"value",
		"balance",
		"account_id"
}))
public class Statement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "statement_id", unique = true, nullable = false, length = 256)
	private long id;
	
	@Column(name = "date", unique = false, nullable = false, length = 256)
	private long date;
	
	@Column(name = "type", unique = false, nullable = true, length = 256)
	private String type;
	
	@Column(name = "description", unique = false, nullable = false, length = 256)
	private String description;
	
	@Column(name = "value", unique = false, nullable = true, length = 256)
	private double value;
	
	@Column(name = "balance", unique = false, nullable = true, length = 256)
	private double balance;
	
	// many statements with a single account attached to them
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id", nullable = false)
	public Account account;
	
	public Statement() {}
	
	public Statement(LocalDate localDate, String type, String description, double value, double balance, Account account) {

		ZoneId zoneId = ZoneId.systemDefault();
		date = localDate.atStartOfDay(zoneId).toEpochSecond();
		
		this.type = type;
		this.description = description;
		this.value = value;
		this.balance = balance;
		this.account = account;
	}
	
	public void setDate(LocalDate localDate) {
		ZoneId zoneId = ZoneId.systemDefault();
		date = localDate.atStartOfDay(zoneId).toEpochSecond();
	}
	
	public long getDate() {
		return date;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public Account getAccount() {
		return account;
	}
	
	@Override
	public String toString() {
		return "Statement - date: "+getDate()+", type: "+getType()+", description: "+
				getDescription()+", value: "+getValue()+", balance: "+getBalance()+
				", account: "+account.getProvider();
	}
	
	

}
