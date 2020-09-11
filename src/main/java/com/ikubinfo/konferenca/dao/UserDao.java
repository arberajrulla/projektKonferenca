package com.ikubinfo.konferenca.dao;

import java.util.ArrayList;
import com.ikubinfo.konferenca.entity.User;

public interface UserDao {
	ArrayList<User> getAllUser();
	User getUserForLogin(String email);
	boolean addUser(User u);
	boolean deleteUser(String username);
	boolean updateUser(User u);
}
