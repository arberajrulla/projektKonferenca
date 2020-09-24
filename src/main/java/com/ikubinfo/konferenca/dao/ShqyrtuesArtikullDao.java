package com.ikubinfo.konferenca.dao;

import java.util.List;
import com.ikubinfo.konferenca.entity.Shqyrtues;
import com.ikubinfo.konferenca.entity.ShqyrtuesArtikull;

public interface ShqyrtuesArtikullDao {
	List<ShqyrtuesArtikull> getAllShqyrtuesArtikull();
	boolean addShqyrtuesArtikull(ShqyrtuesArtikull sa);
	boolean deleteShqyrtuesArtikull(int arId);
	boolean updateShqyrtuesArtikull(ShqyrtuesArtikull saU);
}
