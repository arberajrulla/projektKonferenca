package com.ikubinfo.konferenca.dao.impl;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ikubinfo.konferenca.dao.ArtikullDao;
import com.ikubinfo.konferenca.entity.Artikull;

@Repository(value = "ArtikullDao")
@Scope("singleton")
public class ArtikullDaoImpl implements ArtikullDao {
	
	private static Logger log = Logger.getLogger(ArtikullDaoImpl.class);

	@PersistenceContext
	EntityManager entityManager;	
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Artikull> getAllArtikuj() {
		try {
			ArrayList<Artikull> artikujLista = new ArrayList<Artikull>();
			log.info("Fetching Artikuj list from database");
			artikujLista = (ArrayList<Artikull>) entityManager.createQuery("SELECT artikull FROM Artikull artikull").getResultList();
		return artikujLista;
		}catch(Exception e) {
			log.error("Error, couldn't retrieve Artikuj data from DB: " + e);
			return null;
		} finally {
			entityManager.close();
		}
	}
}
