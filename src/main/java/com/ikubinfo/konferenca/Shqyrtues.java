package com.ikubinfo.konferenca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;


@ManagedBean(name="shqyrtues")
@RequestScoped
public class Shqyrtues implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String id_email, emri, mbiemri, institucioni;
	private List<Shqyrtues> listaShqyrtues = new ArrayList<Shqyrtues>();
	private List<Shqyrtues> shqyrtuesitEPerzgjedhur = new ArrayList<Shqyrtues>();
	private List<Shqyrtues> shqyrtuesitEFiltruar;
	
	public Shqyrtues() {
		listaShqyrtues = ShqyrtuesDB.merrShqyrtuesit();
	}
	
	
	public Shqyrtues(String id_email, String emri, 
			String mbiemri, String institucioni) {
		super();
		this.id_email = id_email;
		this.emri = emri;
		this.mbiemri = mbiemri;
		this.institucioni = institucioni;
	}

	public String getId_email() {
		return id_email;
	}

	public void setId_email(String id_email) {
		this.id_email = id_email;
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

	public String getInstitucioni() {
		return institucioni;
	}

	public void setInstitucioni(String institucioni) {
		this.institucioni = institucioni;
	}

	public void setListaShqyrtues(List<Shqyrtues> listaShqyrtues) {
		this.listaShqyrtues = listaShqyrtues;
	}
	public List<Shqyrtues> getShqyrtuesitEPerzgjedhur() {
		return shqyrtuesitEPerzgjedhur;
	}
	public void setShqyrtuesitEPerzgjedhur(List<Shqyrtues> shqyrtuesitEPerzgjedhur) {
		this.shqyrtuesitEPerzgjedhur = shqyrtuesitEPerzgjedhur;
	}
	public List<Shqyrtues> getShqyrtuesitEFiltruar() {
		return shqyrtuesitEFiltruar;
	}
	public void setShqyrtuesitEFiltruar(List<Shqyrtues> shqyrtuesitEFiltruar) {
		this.shqyrtuesitEFiltruar = shqyrtuesitEFiltruar;
	}

	
	public List<Shqyrtues> getListaShqyrtues() {
		return listaShqyrtues;
	}
	
	public void shtimShqyrtuesi() {
		boolean shtim = ShqyrtuesDB.shtoShqyrtues(this);
		if(shtim) {
			System.out.println("Shqyrtuesi i ri u shtua me sukses");
			PrimeFaces current = PrimeFaces.current();
			current.executeScript("PF('dialogShqyrtuesIRI').hide()");
		}else {
			System.out.println("Gabim ne Shqyrtues -> shtimShqyrtuesi - Shqyrtuesi nuk u shtua");
			PrimeFaces current = PrimeFaces.current();
			current.executeScript("PF('dialogShqyrtuesIRI').show()");
		}
	}
	
	
	public void fshirjeShqyrtuesi() {
		for(int i=0; i<shqyrtuesitEPerzgjedhur.size(); i++) {
			boolean sukses = ShqyrtuesDB.uFshiShqyrtuesi(shqyrtuesitEPerzgjedhur.get(i).getId_email());
			if(sukses) {
				System.out.println("Shqyrtuesi u fshi me sukses");
				//listaAutore = AutorDB.merrAutoret();
			}else {
				System.out.println("Gabim! Nuk u fshi shqyrtuesi");
			}
		}
	}
	
	public String tabelaShqyrtuesitAdmin() {
		return("/admin/shqyrtuesit.xhtml?faces-redirect=true");
	}
}
