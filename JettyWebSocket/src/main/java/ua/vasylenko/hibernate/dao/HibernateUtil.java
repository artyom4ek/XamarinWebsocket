package ua.vasylenko.hibernate.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Класс-настройка Hibernate.
 * @Created by Тёма on 05.08.2017.
 * @version 1.0
 */
public class HibernateUtil {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Создаем фабрику SessionFactory из hibernate.cfg.xml
			return new Configuration().configure()
					.buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Закрываем подключение.
		getSessionFactory().close();
	}

}