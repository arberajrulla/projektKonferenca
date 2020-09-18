package com.ikubinfo.konferenca.dao.impl;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import com.ikubinfo.konferenca.dao.ShqyrtuesDao;
import com.ikubinfo.konferenca.entity.Shqyrtues;

@Repository(value = "ShqyrtuesDao")
@Scope("singleton")
public class ShqyrtuesDaoImpl implements ShqyrtuesDao {

	private static Logger log = Logger.getLogger(ShqyrtuesDaoImpl.class);	
	
	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Shqyrtues> getAllShqyrtues() {
		try {
			ArrayList<Shqyrtues> listaAllShqyrtuesit = new ArrayList<Shqyrtues>();
			log.info("Fetching Shqyrtues list from database");
			listaAllShqyrtuesit = (ArrayList<Shqyrtues>) entityManager
					.createQuery("SELECT shqyrtues FROM Shqyrtues shqyrtues").getResultList();
			return listaAllShqyrtuesit;
		}catch(Exception e) {
			log.error("Error, couldn't retrieve Shqyrtues data from DB: " + e);
			return null;
		}finally {
			entityManager.close();
		}
	}
}
