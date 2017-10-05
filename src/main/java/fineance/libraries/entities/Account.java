package fineance.libraries.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_id", unique = true, nullable = false, length = 256)
	private long id;
	
	@Column(name = "accountName", unique = true, nullable = true, length = 256)
	private String accountName;
	
	@Column(name = "accountNumber", unique = true, nullable = true, length = 256)
	private String accountNumber;
	
	@Column(name = "provider", unique = true, nullable = true, length = 256)
	private String provider;
	
	public Account() {}
	
	public Account(String accountName, String accountNumber, String provider) {
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.provider = provider;
	}
	
	public long getId() {
		return id;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getAccountName() {
		return accountName;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	public String getProvider() {
		return provider;
	}
	
	// one account has many statements
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name = "account_statement",
			joinColumns = @JoinColumn(name = "account_id"),
			inverseJoinColumns = @JoinColumn(name = "statement_id")
			)
	private Set<Statement> statements;
	
	@Override
	public String toString() {
		return "Account name: "+accountName+"\nAccount number: "+accountNumber;
	}
	

}
