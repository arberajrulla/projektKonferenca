package com.ikubinfo.konferenca.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.ikubinfo.konferenca.entity.User;

public class SessionFactoryUtil {

	public static SessionFactory sessionFactory;

	private SessionFactoryUtil() {
	}

	public static synchronized SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			/*
			 * Configuration config = new Configuration().configure();
			 * StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
			 * .applySettings(config.getProperties()); sessionFactory =
			 * config.buildSessionFactory(builder.build());
			 */
			
			sessionFactory = new Configuration().configure().buildSessionFactory();
			 
		}

		return sessionFactory;
	}

}
