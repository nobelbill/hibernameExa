package com.multicampus.service.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SessionFactoryBean {
	private static final SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public static void closeSession() {
		sessionFactory.getCurrentSession().close();
	}

	public static Transaction beginTransaction() {
		return sessionFactory.getCurrentSession().beginTransaction();
	}

	public static void commitTransaction() {
		sessionFactory.getCurrentSession().getTransaction().commit();
	}

	public static void rollbackTransaction() {
		if (sessionFactory.getCurrentSession().isOpen()) {
			Transaction tx = sessionFactory.getCurrentSession()
					.getTransaction();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
	}
}
