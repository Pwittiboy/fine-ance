package fineance.libraries.dataaccess.dao;

import java.io.Serializable;
import java.util.List;

import fineance.libraries.entities.Account;
import fineance.libraries.entities.Statement;

public interface StatementDAO {
	
	List<Statement> findAll();
	
	Statement findById(long id);
	List<Statement> findByDate(long date);
	Statement findByType(String type);
	Statement findByDescription(String description);
	List<Statement> findByAccount(Account account);
	
	List<Statement> findByDateAndAccount(long date, Account account);
	
	void insertStatement(Statement statement);
	void updateStatement(Statement statement);
	void deleteStatementById(Class<Statement> type, Serializable id);

}
