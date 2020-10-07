package com.ikubinfo.konferenca.dao;

import java.util.List;
import com.ikubinfo.konferenca.entity.Shqyrtues;

public interface ShqyrtuesDao {
	Shqyrtues getShqyrtues(String emailId);
	List<Shqyrtues> getAllShqyrtues();
	void addShqyrtues(Shqyrtues s);
	void deleteShqyrtues(String idEmail);
	void updateShqyrtues(Shqyrtues s);
	boolean checkShqyrtuesIfExists(String idEmail);
}
