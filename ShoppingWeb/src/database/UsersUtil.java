package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class UsersUtil
{
	private static SessionFactory sessionFactory= null;
	private static Session session = null;
	
	static
	{
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		StandardServiceRegistry standardServiceRegistry = standardServiceRegistryBuilder.build();
		sessionFactory = configuration.buildSessionFactory(standardServiceRegistry);

	}

	public static Session getSession() 
	{
		session = sessionFactory.openSession();
		return session;
	}

	public static SessionFactory getSessionFactory() 
	{
		return sessionFactory;
	}

	public static void closeSession(Session session)
	{
		if(session!=null)
		{
			session.close();
		}
	}
	
	
}
