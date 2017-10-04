package fineance.libraries.dataaccess.dao;

import java.io.Serializable;
import java.util.List;

import fineance.libraries.entities.Account;
import fineance.libraries.entities.Statement;

public interface AccountDAO {
	
	List<Account> findAll();
	
	Account findByName(String accountName);
	Account findByNumber(String accountNumber);
	
	Account findByStatement(Statement statement);
	
	void insertAccount(Account account);
	void updateAccount(Account account);
	void deleteAccountById(Class<Account> type, Serializable id);
	
	
}
