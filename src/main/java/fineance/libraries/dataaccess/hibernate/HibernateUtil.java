package fineance.libraries.dataaccess.hibernate;

import java.io.File;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class);
	
	private static String USERNAME, PASSWORD;
	
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			Configuration config = null;
			
			try {
				File file = new File("hibernate.cfg.xml");
				config = new Configuration().configure(file);
			} catch (HibernateException e) {
				LOGGER.error("Problem creating session factory");
				e.printStackTrace();
			}
			
			// add database credentials
			config.setProperty("hibernate.connection.username", USERNAME);
			config.setProperty("hibernate.connection.password", PASSWORD);
			
			// add entity classes
			config.addAnnotatedClass(fineance.libraries.entities.Account.class);
			config.addAnnotatedClass(fineance.libraries.entities.Statement.class);
			
			ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			try {
				sessionFactory = config.buildSessionFactory(sr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			LOGGER.info("Standard Service Registry built.");
		}
		return sessionFactory;
	}
	
	public static void setDatabaseCredentials(String username, String password) {
		USERNAME = username;
		PASSWORD = password;
	}

}
