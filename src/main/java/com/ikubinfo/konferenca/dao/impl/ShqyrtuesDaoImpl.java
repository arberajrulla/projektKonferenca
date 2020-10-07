package com.ikubinfo.konferenca.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import com.ikubinfo.konferenca.dao.ShqyrtuesDao;
import com.ikubinfo.konferenca.entity.Autor;
import com.ikubinfo.konferenca.entity.Shqyrtues;

@Repository(value = "ShqyrtuesDao")
public class ShqyrtuesDaoImpl implements ShqyrtuesDao {

	private static Logger log = Logger.getLogger(ShqyrtuesDaoImpl.class);	
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Shqyrtues> getAllShqyrtues() {
			List<Shqyrtues> listaAllShqyrtuesit = new ArrayList<Shqyrtues>();
			log.info("Fetching Shqyrtues list from database");
			listaAllShqyrtuesit = entityManager.createQuery("SELECT shqyrtues FROM Shqyrtues shqyrtues", Shqyrtues.class).getResultList();
			return listaAllShqyrtuesit;
	}

	@Override
	public void addShqyrtues(Shqyrtues s) {
			log.info("Persisting the Shqyrtues object into DB");
			entityManager.persist(s);
	}

	@Override
	public void deleteShqyrtues(String idEmail) {
			Shqyrtues shqyrtuesToDelete = entityManager.find(Shqyrtues.class, idEmail);
			entityManager.remove(shqyrtuesToDelete);	
			log.info("Shqyrtues " + idEmail + " deleted successfully from DB!");
	}

	@Override
	public void updateShqyrtues(Shqyrtues s) {
			log.info("Updating Shqyrtues " + s.getEmri());
			entityManager.merge(s);
			log.info("Shqyrtues was updated into DB successfully!");
	}

	@Override
	public boolean checkShqyrtuesIfExists(String idEmail) {
		log.info("Checking shqyrtues with email: " + idEmail + " from DB if exists!");
		TypedQuery<Shqyrtues> checkQuery = entityManager.createQuery("SELECT shqyrtues FROM Shqyrtues shqyrtues WHERE shqyrtues.idEmail = :idEmail", Shqyrtues.class);
		int a = checkQuery.setParameter("idEmail", idEmail).getResultList().size();
		if(a>0) {
			return true;
		}
		return false;
	}

	@Override
	public Shqyrtues getShqyrtues(String emailId) {
		log.info("Getting shqyrtues " + emailId + " from DB!");
		TypedQuery<Shqyrtues> fetchQuery = entityManager.createQuery("SELECT shqyrtues FROM Shqyrtues shqyrtues WHERE shqyrtues.idEmail = :emailId", Shqyrtues.class);
		Shqyrtues shqyrtues = fetchQuery.setParameter("emailId", emailId).getSingleResult();
		return shqyrtues;
	}
}
