package com.ikubinfo.konferenca.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.ikubinfo.konferenca.dao.AutorDao;
import com.ikubinfo.konferenca.entity.Artikull;
import com.ikubinfo.konferenca.entity.Autor;

@Repository(value = "AutorDao")
public class AutorDaoImpl implements AutorDao {

	private static Logger log = Logger.getLogger(AutorDaoImpl.class);
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Autor> getAllAutor() {
	
		try {//join fetch autor.artikuj a
			List<Autor> listAutoret;
			log.info("Fetching Autor list from DB");
				listAutoret = entityManager 
						.createQuery("SELECT autor FROM Autor autor", Autor.class)
						.getResultList();
			return listAutoret;
		}catch(Exception e) {
			log.error("Error, couldn't retrieve Autor data from DB: " + e);
			return null;
		}
		
	}

	
	
	@Override
	public boolean addAutor(Autor a) {
		try {
			log.info("Persisting " + a.getEmri());
			entityManager.persist(a);
			log.info("Autor was persisted into DB successfully!");
			return true;
		}catch(Exception e) {
			log.error("Error persisting new Autor into DB ", e);
			return false;
		}
	}

	@Override
	public boolean deleteAutor(String idEmail) {
		try {
			Autor autorToDelete = entityManager.find(Autor.class, idEmail);
			entityManager.remove(autorToDelete);	
			log.info("Autor " + idEmail + " deleted successfully from DB!");
			return true;
		}catch(Exception e) {
			log.error("Couldn't delete Autor from Database ", e);
			return false;
		}
	}

	@Override
	public boolean updateAutor(Autor a) {
		try {
			log.info("Updating Autor " + a.getEmri());
		
			entityManager.merge(a);
			
			
			log.info("Autor was updated into DB successfully!");
			return true;
		}catch(Exception e) {
			log.error("Error updating Autor into DB ", e);
			return false;
		}
	}
}
