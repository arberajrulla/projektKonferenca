package com.ikubinfo.konferenca.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.ikubinfo.konferenca.dao.ShqyrtuesArtikullDao;
import com.ikubinfo.konferenca.entity.ShqyrtuesArtikull;
import com.ikubinfo.konferenca.entity.User;

@Repository(value = "ShqyrtuesArtikullDao")
public class ShqyrtuesArtikullDaoImpl implements ShqyrtuesArtikullDao{

	private static Logger log = Logger.getLogger(ShqyrtuesArtikullDaoImpl.class);
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<ShqyrtuesArtikull> getAllShqyrtuesArtikull() {
			List<ShqyrtuesArtikull> listaVleresime;
			listaVleresime = entityManager
					.createQuery("SELECT shqyrtuesArtikull FROM ShqyrtuesArtikull shqyrtuesArtikull", ShqyrtuesArtikull.class)
					.getResultList();
			log.info("Fetching vleresime from DataBase: " + listaVleresime.size() + " objects!");
			return listaVleresime;
	}

	@Override
	public void addShqyrtuesArtikull(ShqyrtuesArtikull sa) {
			log.info("Persisting " + sa.getArtikullVleresim().getTitulli());
			entityManager.persist(sa);
	}

	@Override
	public void deleteShqyrtuesArtikull(int vleresimId) {
			log.info("Deleting ShqyrtuesArtikull from DB! " + vleresimId );
			ShqyrtuesArtikull vleresimToDelete = entityManager.find(ShqyrtuesArtikull.class, vleresimId);
			entityManager.remove(vleresimToDelete);	
	}

	@Override
	public void updateShqyrtuesArtikull(ShqyrtuesArtikull saU) {
			log.info("Updating ShqyrtuesArtikull " + saU.getShqyrtuesVleresim().getEmri() + " " + saU.getArtikullVleresim().getTitulli());
			entityManager.merge(saU);
	}

	@Override
	public boolean checkShqyrtuesArtikullIfExists(String shqrtid, int arid) {
		log.info("Checking shqyrtuesArtikull from DB if exists!");
		TypedQuery<ShqyrtuesArtikull> checkQuery = entityManager
				.createQuery("SELECT shqyrtuesArtikull FROM ShqyrtuesArtikull shqyrtuesArtikull WHERE shqyrtuesArtikull.shqyrtuesVleresim.idEmail = :shqrtid AND shqyrtuesArtikull.artikullVleresim.artikullId = :arid", ShqyrtuesArtikull.class);
		int a = checkQuery.setParameter("shqrtid", shqrtid)
							.setParameter("arid", arid).getResultList().size();
		if(a>0) {
			return true;
		}
		return false;		
	}

}
