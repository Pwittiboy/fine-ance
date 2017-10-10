package fineance.libraries.dataaccess.dao.impl;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import fineance.libraries.dataaccess.dao.StatementDAO;
import fineance.libraries.entities.Account;
import fineance.libraries.entities.Statement;

public class StatementDAOImpl implements StatementDAO {
	
	private final SessionFactory sessionFactory;
	private Session session;
	private Statement statement;
	private List<Statement> statements;
	private long date;
	
	public StatementDAOImpl (SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Criteria criteria() {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Statement.class);
		return criteria;
	}

	@Override
	public List<Statement> findAll() {
		List<Statement> statements = criteria().list();
		session.close();
		return statements;
	}
	
	private Statement buildCriteria(String columnName, Object value) {
		statement = (Statement) criteria().add(Restrictions.eqOrIsNull(columnName, value));
		session.close();
		return statement;
	}

	@Override
	public Statement findById(long id) {
		return buildCriteria("statement_id", id);
	}
	
	public Statement findStatement(LocalDate localDate, String type, String description, double value, double balance, Account account) {
		ZoneId zoneId = ZoneId.systemDefault();
		date = localDate.atStartOfDay(zoneId).toEpochSecond();
		statement = (Statement) criteria().add(Restrictions.eqOrIsNull("date", date)).
				add(Restrictions.eqOrIsNull("type", type)).
				add(Restrictions.eqOrIsNull("description", description)).
				add(Restrictions.eqOrIsNull("value", value)).
				add(Restrictions.eqOrIsNull("balance", balance)).
//				add(Restrictions.eqOrIsNull("account", account.getId())).
				uniqueResult();
		session.close();
		return statement;
	}

	@Override
	public List<Statement> findByDate(long date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement findByType(String type) {
		return buildCriteria("type", type);
	}

	@Override
	public Statement findByDescription(String description) {
		return buildCriteria("description", description);
	}

	@Override
	public List<Statement> findByAccount(Account account) {
//		List<Statement> statements = criteria().add(Restrictions.eqOrIsNull("account", account.getId())).list();
		
		// workaround
		statements = new ArrayList<>();
		for (Statement s : findAll()) {
			if (s.getAccount().getId() == account.getId()) {
				statements.add(s);
			}
		}
		session.close();
		return statements;
	}

	@Override
	public List<Statement> findByDateAndAccount(long date, Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertStatement(Statement statement) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(statement);
		tx.commit();
		session.close();
	}

	@Override
	public void updateStatement(Statement statement) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(statement);
		tx.commit();
		session.close();
	}

	@Override
	public void deleteStatementById(Class<Statement> type, Serializable id) {
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
