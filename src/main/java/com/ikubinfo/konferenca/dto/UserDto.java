package com.ikubinfo.konferenca.dto;

public class UserDto {

	private String username;
	private String emri;
	private String mbiemri;
	private String email;
	private String password;
	private String kategoria;
	private String nrcel;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmri() {
		return emri;
	}

	public void setEmri(String emri) {
		this.emri = emri;
	}

	public String getMbiemri() {
		return mbiemri;
	}

	public void setMbiemri(String mbiemri) {
		this.mbiemri = mbiemri;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKategoria() {
		return kategoria;
	}

	public void setKategoria(String kategoria) {
		this.kategoria = kategoria;
	}

	public String getNrcel() {
		return nrcel;
	}

	public void setNrcel(String nrcel) {
		this.nrcel = nrcel;
	}
}
