package com.ikubinfo.konferenca.dto;

public class AutorDto {
	
	private String emailId;
	private String emri;
	private String mbiemri;
	private int artikullId;
	private String artikullName;
	
	
	public String getArtikullName() {
		return artikullName;
	}
	public void setArtikullName(String artikullName) {
		this.artikullName = artikullName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	public int getArtikullId() {
		return artikullId;
	}
	public void setArtikullId(int i) {
		this.artikullId = i;
	}
}
