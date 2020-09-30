package com.ikubinfo.konferenca.dao;

import java.util.List;

import com.ikubinfo.konferenca.entity.Artikull;
import com.ikubinfo.konferenca.entity.Autor;
import com.ikubinfo.konferenca.entity.User;

public interface ArtikullDao {
	List<Artikull> getAllArtikuj();
	void addArtikull(Artikull a);
	void updateArtikull(Artikull a);
	void deleteArtikull(int artikullId);
	boolean checkArtikullIfExists(String dokumentiElektronik);
}
