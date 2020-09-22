package com.ikubinfo.konferenca.dao.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.ikubinfo.konferenca.dao.UserDao;
import com.ikubinfo.konferenca.entity.User;
import com.ikubinfo.konferenca.utils.SessionFactoryUtil;

@Repository(value = "UserDao")

public class UserDaoImpl implements UserDao {
	private static Logger log = Logger.getLogger(UserDaoImpl.class);
	
	private Session session;
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ArrayList<User> getAllUser() {
		try {
			ArrayList<User> users = new ArrayList<User>();
			session = SessionFactoryUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			/*
			 * CriteriaBuilder builder = session.getCriteriaBuilder(); CriteriaQuery<User>
			 * criteria = builder.createQuery(User.class); TypedQuery<User> typo =
			 * session.createQuery(criteria);
			 */
			Criteria c = session.createCriteria(User.class);
			users = (ArrayList<User>) c.list();
			
			//ArrayList<User> users =  (ArrayList<User>) session.createCriteria(User.class).list();
			
			System.out.println("Lista u be fetch: " + users.size() + " objekte");
			
			if(users.size()>0) {
				return users;
			}
			return null;
		}catch(Exception e) {
			System.out.println("Error while retrieving all users " + e);
			return null;
		}finally {
			session.close();
		}
	}
	

	@SuppressWarnings("unused")
	@Override
	public User getUserForLogin(String username) {
		try {
			User user = new User();
			session = SessionFactoryUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			user = (User) session.get(User.class, username);
			
			System.out.println("Objekti u be fetch: " + user.getEmri());
			
			if(user != null) {
				return user;
			}
			return null;
			
		} catch(Exception e) {
			System.out.println("Problem retrieving user for login " + e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	
	
	// Using PersistenceContext for other interactions with DB
	
	@PersistenceContext
	EntityManager entityManager;

		

	@Override
	public boolean addUser(User u) {
		try {
			log.info("Persisting " + u.getPassword() + "  " /*+ u.getSalt()*/);
			//entityManager.getTransaction().begin();
			entityManager.merge(u);
			//entityManager.getTransaction().commit();
			log.info("User was persisted into DB successfully!");
			return true;
		}catch(Exception e) {
			log.error("Error persisting new user into DB ", e);
			return false;
		}
	}

	@Override
	public boolean deleteUser(String username) {
		try {
			User userToDelete = entityManager.find(User.class, username);
			entityManager.remove(userToDelete);	
			log.info("User " + username + " deleted successfully from DB!");
			return true;
		}catch(Exception e) {
			log.error("Couldn't delete User from Database ", e);
			return false;
		}
	}

	@Override
	public boolean updateUser(User u) {
		try {
			log.info("Updating user " + u.getEmri());
			
			entityManager.merge(u);
			
			log.info("User was updated into DB successfully!");
			return true;
		}catch(Exception e) {
			log.error("Error updating user into DB ", e);
			return false;
		}
		
	}
}
