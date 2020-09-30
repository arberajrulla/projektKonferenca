package com.ikubinfo.konferenca.dao;

import java.util.List;
import com.ikubinfo.konferenca.entity.Shqyrtues;
import com.ikubinfo.konferenca.entity.ShqyrtuesArtikull;

public interface ShqyrtuesArtikullDao {
	List<ShqyrtuesArtikull> getAllShqyrtuesArtikull();
	void addShqyrtuesArtikull(ShqyrtuesArtikull sa);
	void deleteShqyrtuesArtikull(int arId);
	void updateShqyrtuesArtikull(ShqyrtuesArtikull saU);
	boolean checkShqyrtuesArtikullIfExists(String shqrtid, int arid);
}
