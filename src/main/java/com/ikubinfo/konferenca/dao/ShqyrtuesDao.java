package com.ikubinfo.konferenca.dao;

import java.util.ArrayList;
import java.util.List;

import com.ikubinfo.konferenca.entity.Shqyrtues;
import com.ikubinfo.konferenca.entity.User;

public interface ShqyrtuesDao {
	List<Shqyrtues> getAllShqyrtues();
	boolean addShqyrtues(Shqyrtues s);
	boolean deleteShqyrtues(String idEmail);
	boolean updateShqyrtues(Shqyrtues s);
}
