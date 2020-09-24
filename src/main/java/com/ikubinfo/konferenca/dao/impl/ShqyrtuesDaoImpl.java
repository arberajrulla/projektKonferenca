package com.ikubinfo.konferenca.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import com.ikubinfo.konferenca.dao.ShqyrtuesDao;
import com.ikubinfo.konferenca.entity.Shqyrtues;

@Repository(value = "ShqyrtuesDao")
public class ShqyrtuesDaoImpl implements ShqyrtuesDao {

	private static Logger log = Logger.getLogger(ShqyrtuesDaoImpl.class);	
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Shqyrtues> getAllShqyrtues() {
		try {
			List<Shqyrtues> listaAllShqyrtuesit = new ArrayList<Shqyrtues>();
			log.info("Fetching Shqyrtues list from database");
			listaAllShqyrtuesit = entityManager.createQuery("SELECT shqyrtues FROM Shqyrtues shqyrtues", Shqyrtues.class).getResultList();
			return listaAllShqyrtuesit;
		}catch(Exception e) {
			log.error("Error, couldn't retrieve Shqyrtues data from DB: " + e);
			return null;
		}
	}

	@Override
	public boolean addShqyrtues(Shqyrtues s) {
		try {
			log.info("Persisting the Shqyrtues object into DB");
			entityManager.persist(s);
			return true;
		}catch(Exception e) {
			log.error("Error, Object Shqyrtues wasn't persisted into DB", e);
			return false;
		}
		
	}

	@Override
	public boolean deleteShqyrtues(String idEmail) {
		try {
			Shqyrtues shqyrtuesToDelete = entityManager.find(Shqyrtues.class, idEmail);
			entityManager.remove(shqyrtuesToDelete);	
			log.info("Shqyrtues " + idEmail + " deleted successfully from DB!");
			return true;
		}catch(Exception e) {
			log.error("Couldn't delete Shqyrtues from Database ", e);
			return false;
		}
	}

	@Override
	public boolean updateShqyrtues(Shqyrtues s) {
		try {
			log.info("Updating Shqyrtues " + s.getEmri());
			
			entityManager.merge(s);
			
			log.info("Shqyrtues was updated into DB successfully!");
			return true;
		}catch(Exception e) {
			log.error("Error updating Shqyrtues into DB ", e);
			return false;
		}
	}
}
