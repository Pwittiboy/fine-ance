package fineance.libraries.dataaccess.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import fineance.libraries.dataaccess.dao.AccountDAO;
import fineance.libraries.entities.Account;
import fineance.libraries.entities.Statement;

public class AccountDAOImpl implements AccountDAO {
	
//	private static final Logger LOGGER = Logger.getLogger(AccountDAOImpl.class);
	
	private final SessionFactory sessionFactory;
	private Session session;
	
	private Account account;
	private List<Account> accounts;
	
	public AccountDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Criteria criteria() {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Account.class);
		return criteria;
	}

	@Override
	public List<Account> findAll() {
		accounts = criteria().list();
		session.close();
		return accounts;
	}

	@Override
	public Account findByName(String accountName) {
		account = (Account) criteria().add(Restrictions.eqOrIsNull("accountName", accountName)).uniqueResult();
		session.close();
		return account;
	}

	@Override
	public Account findByNumber(String accountNumber) {
		account = (Account) criteria().add(Restrictions.eqOrIsNull("accountNumber", accountNumber)).uniqueResult();
		session.close();
		return account;
	}

	@Override
	public Account findByStatement(Statement statement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertAccount(Account account) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(account);
		tx.commit();
		session.close();
	}

	@Override
	public void updateAccount(Account account) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(account);
		tx.commit();
		session.close();
	}

	@Override
	public void deleteAccountById(Class<Account> type, Serializable id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Object persistentInstance = session.load(type, id);
		if(persistentInstance != null) {
			session.delete(persistentInstance);
		}
		tx.commit();
		session.close();
	}

}
