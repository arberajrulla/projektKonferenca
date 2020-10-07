package com.ikubinfo.konferenca.dao;

import java.util.List;
import com.ikubinfo.konferenca.entity.Autor;

public interface AutorDao {
	Autor getAutor(String emailId);
	Autor getAutorByArtikullId(int artikullId);
	List<Autor> getAllAutor();
	void addAutor(Autor a);
	void deleteAutor(String idEmail);
	void updateAutor(Autor a);
	boolean checkAutorIfExists(String email);
}
