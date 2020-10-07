package com.ikubinfo.konferenca.dao.impl;

import java.util.ArrayList;
import java.util.List;

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
			
			Criteria c = session.createCriteria(User.class);
			users = (ArrayList<User>) c.list();
			
			if(users.size()>0) {
				return users;
			}
			return null;
		}catch(Exception e) {
			log.error("Error while retrieving all users ", e);
			
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
			log.error("Problem retrieving user for login ",e);
			return null;
		} finally {
			session.close();
		}
	}

	
	
	// Using PersistenceContext for other interactions with DB
	
	@PersistenceContext
	EntityManager entityManager;


	@Override
	public void addUser(User u) {

			log.info("Persisting " + u.getEmri());
			entityManager.merge(u);
			log.info("User was persisted into DB successfully!");
	}

	@Override
	public void deleteUser(String username) {
			User userToDelete = entityManager.find(User.class, username);
			entityManager.remove(userToDelete);	
			log.info("User " + username + " deleted successfully from DB!");
	}

	@Override
	public void updateUser(User u) {
			log.info("Updating user " + u.getEmri());
			entityManager.merge(u);
			log.info("User was updated into DB successfully!");
	}
	
	@Override
	public boolean checkUserIfExists(String email, String username) {
			log.info("Checking if " + email + " if exists in DB!");
			TypedQuery<User> checkQuery = entityManager
					.createQuery("SELECT user FROM User user WHERE user.username = :username OR user.email = :email", User.class);
			int a = checkQuery.setParameter("username", username)
								.setParameter("email", email)
								.getResultList().size();
			if(a>0) {
				return true;
			}
			return false;
	}
	
	

	@Override
	public User getSingleUser(String email) {
		log.info("Getting user " + email + " from DB!");
		TypedQuery<User> checkQuery = entityManager
				.createQuery("SELECT user FROM User user WHERE user.email = :email", User.class);
		User u = checkQuery.setParameter("email", email).getSingleResult();
		return u;
	}
}
