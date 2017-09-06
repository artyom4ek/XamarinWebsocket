package ua.vasylenko.worker;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import ua.vasylenko.hibernate.dao.HibernateUtil;
import ua.vasylenko.hibernate.domain.User;

/**
 * Класс для работы с БД.
 * @Created by Тёма on 05.08.2017.
 * @version 1.0
 */
public class UserWorker {
	/**
     * Метод получает обьект User по айдишнику в БД.
     * @param userId айдишник пользователя
     */
	public User getUserById(int userId) {
	        Session session = null;
	        User user = null;
	        
	        try {
	            session = HibernateUtil.getSessionFactory().openSession();
	            user =  (User) session.get(User.class, userId);
	            Hibernate.initialize(user);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (session != null && session.isOpen()) {
	                session.close();
	            }
	        }
	        
	        return user;
	    }
	
}

