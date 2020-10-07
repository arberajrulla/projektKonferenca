package com.ikubinfo.konferenca.dao;

import java.util.ArrayList;
import java.util.List;

import com.ikubinfo.konferenca.entity.User;

public interface UserDao {
	ArrayList<User> getAllUser();
	User getUserForLogin(String username);
	void addUser(User u);
	void deleteUser(String username);
	void updateUser(User u);
	boolean checkUserIfExists(String email, String username);
	User getSingleUser(String email);
}
