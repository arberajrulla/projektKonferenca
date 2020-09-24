package com.ikubinfo.konferenca.dao;

import java.util.List;

import com.ikubinfo.konferenca.entity.Autor;
import com.ikubinfo.konferenca.entity.User;

public interface AutorDao {
	List<Autor> getAllAutor();
	boolean addAutor(Autor a);
	boolean deleteAutor(String idEmail);
	boolean updateAutor(Autor a);
}
