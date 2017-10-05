package services.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import fineance.libraries.dataaccess.dao.impl.AccountDAOImpl;
import fineance.libraries.dataaccess.dao.impl.StatementDAOImpl;
import fineance.libraries.dataaccess.hibernate.HibernateUtil;
import fineance.libraries.entities.Account;
import fineance.libraries.entities.Statement;

public class ItemServiceImpl {
	
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	private static AccountDAOImpl adao = new AccountDAOImpl(sessionFactory);
	private static StatementDAOImpl sdao = new StatementDAOImpl(sessionFactory);
	
	private static List<Account> accounts;
	private static List<Statement> statements;
	
	public ItemServiceImpl() {
		updateAccounts();
		updateStatements();
	}
	
	// Accounts
	public static List<Account> getAccounts() {
		return accounts;
	}
	
	public static void updateAccounts() {
		accounts = adao.findAll();
	}
	
	public static AccountDAOImpl getAccountDAO() {
		return adao;
	}
	
	// Statements
	public static List<Statement> getStatements() {
		return statements;
	}
	
	public static void updateStatements() {
		statements = sdao.findAll();
	}
	
	public static StatementDAOImpl getStatementDAO() {
		return sdao;
	}
	
	

}
