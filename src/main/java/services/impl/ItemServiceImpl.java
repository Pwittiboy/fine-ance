package services.impl;

import org.hibernate.SessionFactory;

import fineance.libraries.dataaccess.dao.impl.AccountDAOImpl;
import fineance.libraries.dataaccess.dao.impl.StatementDAOImpl;
import fineance.libraries.dataaccess.hibernate.HibernateUtil;

public class ItemServiceImpl {
	
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	private static AccountDAOImpl adao = new AccountDAOImpl(sessionFactory);
	private static StatementDAOImpl sdao = new StatementDAOImpl(sessionFactory);
	
	public ItemServiceImpl() {
		
	}

}
