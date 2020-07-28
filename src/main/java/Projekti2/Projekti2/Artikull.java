package Projekti2.Projekti2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;

@ManagedBean(name="artikull")
@RequestScoped

public class Artikull implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	int artikull_id;
	private String titulli, abstrakti, dokumenti_elektronik, kontakt;

	private List<Artikull> artikujtEPerzgjedhur = new ArrayList<Artikull>();
	private List<Artikull> artikujAll = new ArrayList<Artikull>();
	private List<Artikull> artikujtFiltruar;
	private Map<String, Integer> listaDropdownArtikuj = new LinkedHashMap<String, Integer>();

	private Artikull artikullIRi;

	
	public Artikull() {
		artikujAll = ArtikullDB.merrArtikujt();
	}
	public Artikull(int artikull_id, String titulli, String abstrakti, 
			String dokumenti_elektronik, String kontakt) {
		super();
		this.artikull_id = artikull_id;
		this.titulli = titulli;
		this.abstrakti = abstrakti;
		this.dokumenti_elektronik = dokumenti_elektronik;
		this.kontakt = kontakt;
	}

	public int getArtikull_id() {
		return artikull_id;
	}

	public void setArtikull_id(int artikull_id) {
		this.artikull_id = artikull_id;
	}

	public String getTitulli() {
		return titulli;
	}

	public void setTitulli(String titulli) {
		this.titulli = titulli.trim();
	}

	public String getAbstrakti() {
		return abstrakti;
	}

	public void setAbstrakti(String abstrakti) {
		this.abstrakti = abstrakti;
	}

	public String getDokumenti_elektronik() {
		return dokumenti_elektronik;
	}

	public void setDokumenti_elektronik(String dokumenti_elektronik) {
		this.dokumenti_elektronik = dokumenti_elektronik.trim();
	}

	public String getKontakt() {
		return kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}
	public List<Artikull> getArtikujtEPerzgjedhur() {
		return artikujtEPerzgjedhur;
	}
	public void setArtikujtEPerzgjedhur(List<Artikull> artikujtEPerzgjedhur) {
		this.artikujtEPerzgjedhur = artikujtEPerzgjedhur;
	}
	public List<Artikull> getArtikujAll() {
		return artikujAll;
	}
	public void setArtikujAll(List<Artikull> artikujAll) {
		this.artikujAll = artikujAll;
	}
	public List<Artikull> getArtikujtFiltruar() {
		return artikujtFiltruar;
	}
	public void setArtikujtFiltruar(List<Artikull> artikujtFiltruar) {
		this.artikujtFiltruar = artikujtFiltruar;
	}
	public Artikull getArtikullIRi() {
		return artikullIRi;
	}
	public void setArtikullIRi(Artikull artikullIRi) {
		this.artikullIRi = artikullIRi;
	}	
	public void setListaDropdownArtikuj(Map<String, Integer> listaDropdownArtikuj) {
		this.listaDropdownArtikuj = listaDropdownArtikuj;
	}
	
	
	public String tabelaArtikujAdmin() {
		return("/admin/artikuj.xhtml?faces-redirect=true");
	}
	
	
	/*
	public ArrayList<String> getlistaDropdownArtikuj() {
		List<Artikull> artTemp = artikujAll;
		for(int i=0; i<artTemp.size(); i++) {
			this.listaDropdownArtikuj.add(artTemp.get(i).titulli);
		}
		return listaDropdownArtikuj;
	}
	*/
	
	
	
	public Map<String, Integer> getListaDropdownArtikuj() {
		List<Artikull> artTemp = artikujAll;
		for(int i=0; i<artTemp.size(); i++) {
			this.listaDropdownArtikuj.put(artTemp.get(i).titulli, artTemp.get(i).artikull_id);
		}
		
		return listaDropdownArtikuj;
	}
	
	public void shtimArtikulli() {
		boolean shtim = ArtikullDB.shtoArtikull(this);
		if(shtim) {
			System.out.println("Artikulli i ri u shtua me sukses");
			PrimeFaces current = PrimeFaces.current();
			current.executeScript("PF('dialogArtikullIRI').hide()");
		}else {
			System.out.println("Gabim ne Artikull -> shtimArtikulli - Artikulli nuk u shtua");
			PrimeFaces current = PrimeFaces.current();
			current.executeScript("PF('dialogArtikullIRI').show()");
		}
	}
	
	
	
}
