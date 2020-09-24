package com.ikubinfo.konferenca.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ikubinfo.konferenca.dao.ShqyrtuesArtikullDao;
import com.ikubinfo.konferenca.entity.Autor;
import com.ikubinfo.konferenca.entity.ShqyrtuesArtikull;
import com.ikubinfo.konferenca.service.impl.ShqyrtuesServiceImpl;

@Repository(value = "ShqyrtuesArtikullDao")
public class ShqyrtuesArtikullDaoImpl implements ShqyrtuesArtikullDao{

	private static Logger log = Logger.getLogger(ShqyrtuesArtikullDaoImpl.class);
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<ShqyrtuesArtikull> getAllShqyrtuesArtikull() {
		try {
			List<ShqyrtuesArtikull> listaVleresime;
			listaVleresime = entityManager
					.createQuery("SELECT shqyrtuesArtikull FROM ShqyrtuesArtikull shqyrtuesArtikull", ShqyrtuesArtikull.class)
					.getResultList();
			log.info("Fetching vleresime from DataBase: " + listaVleresime.size() + " objects!");
			return listaVleresime;
		}catch(Exception e) {
			log.error("Error fetching vleresime from DataBase! ", e);			
			return null;
		}
	}

	@Override
	public boolean addShqyrtuesArtikull(ShqyrtuesArtikull sa) {
		try {
			log.info("Persisting " + sa.getArtikullVleresim().getTitulli());
			entityManager.persist(sa);
			log.info("ShqyrtuesArtikull was persisted into DB successfully!");
			return true;
		}catch(Exception e) {
			log.error("Error persisting new ShqyrtuesArtikull into DB ", e);
			return false;
		}
	}

	@Override
	public boolean deleteShqyrtuesArtikull(int vleresimId) {
		try {
			log.info("ShqyrtuesArtikull delete from DB! " + vleresimId );
			ShqyrtuesArtikull vleresimToDelete = entityManager.find(ShqyrtuesArtikull.class, vleresimId);
			entityManager.remove(vleresimToDelete);	
			log.info("ShqyrtuesArtikull " + vleresimToDelete.getArtikullVleresim().getTitulli() + " deleted successfully from DB!");
			return true;
		}catch(Exception e) {
			log.error("Couldn't delete ShqyrtuesArtikull from Database ", e);
			return false;
		}
	}

	@Override
	public boolean updateShqyrtuesArtikull(ShqyrtuesArtikull saU) {
		try {
			log.info("Updating ShqyrtuesArtikull " + saU.getShqyrtuesVleresim().getEmri() + " " + saU.getArtikullVleresim().getTitulli());
		
			entityManager.merge(saU);
			
			log.info("ShqyrtuesArtikull was updated into DB successfully!");
			return true;
		}catch(Exception e) {
			log.error("Error updating ShqyrtuesArtikull into DB ", e);
			return false;
		}
	}

}
