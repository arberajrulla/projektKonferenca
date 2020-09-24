package com.ikubinfo.konferenca.dao;

import java.util.List;

import com.ikubinfo.konferenca.entity.Artikull;
import com.ikubinfo.konferenca.entity.User;

public interface ArtikullDao {
	List<Artikull> getAllArtikuj();
	boolean addArtikull(Artikull a);
	boolean updateArtikull(Artikull a);
	boolean deleteArtikull(int artikullId);
}
