package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import database.UsersUtil;
import model.User;

public class UsersDAO {
	
	public static void add(User user) {
		Session session = UsersUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
		UsersUtil.closeSession(session);
	}
	
	public static List<Object> selectName(){
		String hql = "select s.name from users s";
		Query query = UsersUtil.getSession().createQuery(hql);
		List<Object> list = query.list();
		return list;

	}
	
	public static List<Object []> selectPassword(){
		String hql = "select s.name,s.password from users s";
		Query query = UsersUtil.getSession().createQuery(hql);
		List<Object []> list = query.list();
		return list;
	}
}
