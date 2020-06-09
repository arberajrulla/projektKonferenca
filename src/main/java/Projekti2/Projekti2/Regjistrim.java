package Projekti2.Projekti2;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean (name = "regjistrim")
@SessionScoped
@RequestScoped

public class Regjistrim {
	
	private String emri, mbiemri, email, nrCel, fjalekalimi1, fjalekalimi2;

	
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

	private Perdorues perdorues;
	private String msg;
	
	
	public String regjistrohu() {
		
		
		return (null);
	}
	
	
}
