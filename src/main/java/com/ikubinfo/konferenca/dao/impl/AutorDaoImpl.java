package com.ikubinfo.konferenca.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.ikubinfo.konferenca.dao.AutorDao;
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
	
}
