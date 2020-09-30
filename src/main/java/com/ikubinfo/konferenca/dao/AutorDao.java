package com.ikubinfo.konferenca.dao;

import java.util.List;

import com.ikubinfo.konferenca.entity.Autor;
import com.ikubinfo.konferenca.entity.User;

public interface AutorDao {
	List<Autor> getAllAutor();
	void addAutor(Autor a);
	void deleteAutor(String idEmail);
	void updateAutor(Autor a);
	boolean checkAutorIfExists(String email);
}
