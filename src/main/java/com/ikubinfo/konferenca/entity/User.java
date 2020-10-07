package com.ikubinfo.konferenca.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="login")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name = "emri")
	private String emri;
	
	@Column(name = "mbiemri")
	private String mbiemri;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;

	@Column(name = "kategoria")
	private String kategoria;
	
	@Column(name = "nrcel")
	private String nrcel;
	
	@Column(name = "salt")
	private byte[] salt;
	
	@Column(name = "register_status")
	private int registerStatus;
	
	
	public byte[] getSalt() { return salt; }
	
	public void setSalt(byte[] salt) { this.salt = salt; }


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
	public int getRegisterStatus() {
		return registerStatus;
	}
	public void setRegisterStatus(int registerStatus) {
		this.registerStatus = registerStatus;
	}
	
}
