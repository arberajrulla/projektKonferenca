package com.ikubinfo.konferenca.dao;

import java.util.ArrayList;
import java.util.List;

import com.ikubinfo.konferenca.entity.Artikull;
import com.ikubinfo.konferenca.entity.Shqyrtues;
import com.ikubinfo.konferenca.entity.User;

public interface ShqyrtuesDao {
	List<Shqyrtues> getAllShqyrtues();
	void addShqyrtues(Shqyrtues s);
	void deleteShqyrtues(String idEmail);
	void updateShqyrtues(Shqyrtues s);
	boolean checkShqyrtuesIfExists(String idEmail);
}
