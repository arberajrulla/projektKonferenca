package com.ikubinfo.konferenca;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean (name = "perdoruesiRi")
@RequestScoped

public class PerdoruesiRi {
	
	
	private Perdorues perdorues;
	private String msg;
	
	private String emri, mbiemri, email, username, nrCel, fjalekalimi1, fjalekalimi2;
	private String kategoria = "";
	
	public String getKategoria() {
		return kategoria;
	}
	public void setKategoria(String kategoria) {
		this.kategoria = kategoria;
		System.out.println(this.kategoria);
	}
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
	public String getNrCel() {
		return nrCel;
	}
	public void setNrCel(String nrCel) {
		this.nrCel = nrCel;
	}
	public String getFjalekalimi1() {
		return fjalekalimi1;
	}
	public void setFjalekalimi1(String fjalekalimi1) {
		this.fjalekalimi1 = fjalekalimi1;
	}
	public String getFjalekalimi2() {
		return fjalekalimi2;
	}
	public void setFjalekalimi2(String fjalekalimi2) {
		this.fjalekalimi2 = fjalekalimi2;
	}


	public String regjistrohu() {
		boolean sukses = PerdoruesDBinfo.shtoPerdorues(this);
		if(sukses) {
			return ("regjistrimSukses");
		}else {
			return ("error");
		}
	}
}