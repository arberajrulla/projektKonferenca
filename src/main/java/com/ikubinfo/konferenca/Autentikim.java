package com.ikubinfo.konferenca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.*;
import javax.validation.constraints.NotNull;

@ManagedBean(name="autentikim")
@RequestScoped
@ViewScoped
public class Autentikim implements Serializable {

	private static final long serialVersionUID = 1L;
 
	private String username;
	private String emri;
	private String email;
	private String fjalekalim;
	private Perdorues perdorues;
	private static PerdoruesLookup lookupPerdorues = new PerdoruesDBinfo();

	public Autentikim() {
	}

	public String getUsername() {
		return (username);
	}

	public void setUsername(String username) {
		this.username = username.trim();
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getFjalekalim() {
		return fjalekalim;
	}

	public void setFjalekalim(String fjalekalim) {
		this.fjalekalim = fjalekalim;
	}
	public String getEmri() {
		return emri;
	}

	public void setEmri(String emri) {
		this.emri = emri;
	}


	public String hyr() {

		if (this.username == null || (this.username.equals("")) && (this.fjalekalim.equals(""))) {
			return ("login");
		} else if (this.username.equals("")) {
			return ("login");

			
		}else if(this.fjalekalim.equals("")){
			return("login");
			 
		} else {
			System.out.println("lookup");
			perdorues = lookupPerdorues.gjejPerdoruesin(this.username);

			if (!this.username.equals(perdorues.getUsername())) {
				return ("error");
			} else if (!perdorues.getFjalekalimi().equals(this.fjalekalim)) {
				return ("error");
			} else if((this.username.equals(perdorues.getUsername())) &&
					(perdorues.getFjalekalimi().equals(this.fjalekalim))) {
				switch (perdorues.getKategoria()) {
				case "autor":
					return ("autor");
				case "shqyrtues":
					return ("shqyrtues");
				case "admin":
					emri = perdorues.getEmri() + " " + perdorues.getMbiemri();
					return ("/admin/kryesore.xhtml?faces-redirect=true");
					
				default:
					return ("error");
				}
			}else {
				return("error");
			}
		}
	}
	
	
	
	public String harrimFjalekalimi() {
		Perdorues hperdorues = lookupPerdorues.gjejPerdoruesin(this.username);
		return null;
	}
	
}
