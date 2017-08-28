package by.htp.task2.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import by.htp.task2.dao.UserDAO;
import by.htp.task2.entity.User;

public class UserDAOImpl implements UserDAO {

	private static final SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
			.buildSessionFactory();
	
	@Override
	public void add(User user) {
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}

	@Override
	public User read(int id) {
		Session session = factory.openSession();
		User user = null;
		try {
			session.beginTransaction();
			user = session.get(User.class, id);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> readAll() {
		Session session = factory.openSession();
		List<User> list = null;
		try {
			session.beginTransaction();
			list = session.createQuery("FROM User u WHERE u.removed = 0").list();
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public void update(User user) {
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			
			session.update(user);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(int id) {
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			session.createQuery("UPDATE User u SET u.removed = 1 WHERE u.id = " + id).executeUpdate();
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteAll(String ids) {
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			session.createQuery("UPDATE User u SET u.removed = 1 WHERE u.id IN (" + ids + ")").executeUpdate();
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}

}

