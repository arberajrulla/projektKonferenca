package com.ikubinfo.konferenca.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.ikubinfo.konferenca.dao.ArtikullDao;
import com.ikubinfo.konferenca.entity.Artikull;
import com.ikubinfo.konferenca.entity.User;

@Repository(value = "ArtikullDao")
//@Scope("singleton")
public class ArtikullDaoImpl implements ArtikullDao {
	
	private static Logger log = Logger.getLogger(ArtikullDaoImpl.class);

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Artikull> getAllArtikuj() {
		try {
			List<Artikull> artikujLista;
			log.info("Fetching Artikuj list from database");
			artikujLista = entityManager.createQuery("SELECT artikull FROM Artikull artikull", Artikull.class).getResultList();
			//artikujLista = entityManager.createTypedQuery("SELECT artikull FROM Artikull artikull", Artikull.class).getResultList();
		return artikujLista;
		}catch(Exception e) {
			log.error("Error, couldn't retrieve Artikuj data from DB: " + e);
			return null;
		}
	}

	@Override
	public boolean addArtikull(Artikull a) {
		try {
			log.info("Persisting " + a.getTitulli());
			entityManager.merge(a);
			log.info("Artikull was persisted into DB successfully!");
			return true;
		}catch(Exception e) {
			log.error("Error persisting new Artikull into DB ", e);
			return false;
		}
	}

	@Override
	public boolean updateArtikull(Artikull a) {
		try {
			log.info("Updating Artikull " + a.getTitulli());
			
			entityManager.merge(a);
			
			log.info("Artikull was updated into DB successfully!");
			return true;
		}catch(Exception e) {
			log.error("Error updating Artikull into DB ", e);
			return false;
		}
	}

	@Override
	public boolean deleteArtikull(int artikullId) {
		try {
			Artikull artikullToDelete = entityManager.find(Artikull.class, artikullId);
			entityManager.remove(artikullToDelete);	
			log.info("Artikull " + artikullId + " deleted successfully from DB!");
			return true;
		}catch(Exception e) {
			log.error("Couldn't delete Artikull from Database ", e);
			return false;
		}
	}
}
