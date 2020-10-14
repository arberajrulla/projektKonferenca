package com.ikubinfo.konferenca.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.ikubinfo.konferenca.dao.ArtikullDao;
import com.ikubinfo.konferenca.entity.Artikull;
import com.ikubinfo.konferenca.entity.Autor;
import com.ikubinfo.konferenca.entity.User;

@Repository(value = "ArtikullDao")
public class ArtikullDaoImpl implements ArtikullDao {
	
	private static Logger log = Logger.getLogger(ArtikullDaoImpl.class);

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Artikull> getAllArtikuj() {
			List<Artikull> artikujLista;
			log.info("Fetching Artikuj list from database");
			artikujLista = entityManager
					.createQuery("SELECT artikull FROM Artikull artikull", Artikull.class)
					.getResultList();
			
		return artikujLista;
	}

	@Override
	public void addArtikull(Artikull a) {
			log.info("Persisting " + a.getTitulli());
			entityManager.merge(a);
	}

	@Override
	public void updateArtikull(Artikull a) {
			log.info("Updating Artikull " + a.getTitulli());
			entityManager.merge(a);
	}

	@Override
	public void deleteArtikull(int artikullId){
			Artikull artikullToDelete = entityManager.find(Artikull.class, artikullId);
			entityManager.remove(artikullToDelete);	
	}

	@Override
	public boolean checkArtikullIfExists(String dokumentiElektronik) {
		log.info("Checking artikull with e-doc: " + dokumentiElektronik + " from DB if exists!");
		TypedQuery<Artikull> checkQuery = entityManager.createQuery("SELECT artikull FROM Artikull artikull WHERE artikull.dokumentiElektronik = :dokumentiElektronik", Artikull.class);
		int a = checkQuery.setParameter("dokumentiElektronik", dokumentiElektronik).getResultList().size();
		if(a>0) {
			return true;
		}
		return false;
	}
}
