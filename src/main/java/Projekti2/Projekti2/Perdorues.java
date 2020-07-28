package Projekti2.Projekti2;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

@ManagedBean (name="perdorues")
@RequestScoped 
public class Perdorues {
	
	
	private String username, emri, mbiemri, email, nrcel;
	private String fjalekalimi;
	private String kategoria;
	
	public Perdorues() {};
	public Perdorues(String username, String fjalekalimi, String kategoria) {
		
		this.username = username;
		this.fjalekalimi = fjalekalimi;
		this.kategoria = kategoria;
	}
	
	
	public Perdorues(String username, String emri, String mbiemri, 
			String email, String nrcel, String fjalekalimi,
			String kategoria) {
		super();
		this.username = username;
		this.emri = emri;
		this.mbiemri = mbiemri;
		this.email = email;
		this.nrcel = nrcel;
		this.fjalekalimi = fjalekalimi;
		this.kategoria = kategoria;
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


	public String getNrcel() {
		return nrcel;
	}

	public void setNrcel(String nrcel) {
		this.nrcel = nrcel;
	}	
		
	public String getKategoria() {
		return kategoria;
	}
	public void setKategoria(String kategoria) {
		this.kategoria = kategoria;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFjalekalimi() {
		return fjalekalimi;
	}
	public void setFjalekalimi(String fjalekalimi) {
		this.fjalekalimi = fjalekalimi;
	}

	
	
	public String tabelaPerdoruesAdmin() {
		return("/admin/perdoruesit.xhtml?faces-redirect=true");
	}
	
	
	
}
