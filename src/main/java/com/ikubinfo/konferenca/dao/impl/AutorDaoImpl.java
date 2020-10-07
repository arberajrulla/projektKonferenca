package com.ikubinfo.konferenca.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
			List<Autor> listAutoret;
			log.info("Fetching Autor list from DB");
				listAutoret = entityManager 
						.createQuery("SELECT autor FROM Autor autor", Autor.class)
						.getResultList();
				
				if(listAutoret==null) {
					throw new RuntimeException("Error in Database, Lista e Autoreve eshte null");
				}
				return listAutoret;
	}

	
	
	@Override
	public void addAutor(Autor a) {
			log.info("Persisting " + a.getEmri());
			entityManager.persist(a);
	}

	
	@Override
	public void deleteAutor(String idEmail) {
			Autor autorToDelete = entityManager.find(Autor.class, idEmail);
			entityManager.remove(autorToDelete);
	}
	

	@Override
	public void updateAutor(Autor a) {
			log.info("Updating Autor " + a.getEmri());
			entityManager.merge(a);
	}


	@Override
	public boolean checkAutorIfExists(String email) {
		log.info("Checking autor " + email + " from DB if exists!");
		TypedQuery<Autor> checkQuery = entityManager.createQuery("SELECT autor FROM Autor autor WHERE autor.emailId = :email", Autor.class);
		int a = checkQuery.setParameter("email", email).getResultList().size();
				log.info("into autor service number +: " + a);
		if(a>0) {
			return true;
		}
		return false;
	}

	@Override
	public Autor getAutor(String emailId) {
		log.info("Getting autor " + emailId + " from DB!");
		TypedQuery<Autor> fetchQuery = entityManager.createQuery("SELECT autor FROM Autor autor WHERE autor.emailId = :emailId", Autor.class);
		Autor autor = fetchQuery.setParameter("emailId", emailId).getSingleResult();
		return autor;
	}

	@Override
	public Autor getAutorByArtikullId(int artikullId) {
		log.info("Getting autor with Artikull Id " + artikullId + " from DB!");
		TypedQuery<Autor> fetchQuery = entityManager.createQuery("SELECT autor FROM Autor autor WHERE autor.artikuj.artikullId = :artikullId", Autor.class);
		Autor autor = fetchQuery.setParameter("artikullId", artikullId).getSingleResult();
		return autor;
	}
}
