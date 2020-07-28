package Projekti2.Projekti2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;

@ManagedBean(name="autor")
@RequestScoped
public class Autor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String email_id, emri, mbiemri, artikull;
	private int id, artikull_id;
	
	private List<Autor> listaAutore = new ArrayList<Autor>();
	private List<Autor> autoretEPerzgjedhur = new ArrayList<Autor>();
	private List<Autor> autoretEFiltruar;
	
	public Autor() {
		listaAutore = AutorDB.merrAutoret();
	}
	public Autor(String email_id, String emri, String mbiemri, 
			String artikull, int id, int artikull_id) {
		super();
		this.email_id = email_id;
		this.emri = emri;
		this.mbiemri = mbiemri;
		this.artikull = artikull;
		this.id = id;
		this.artikull_id = artikull_id;
	}
	
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
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
	public int getArtikull_id() {
		return artikull_id;
	}
	public void setArtikull_id(int artikull_id) {
		this.artikull_id = artikull_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArtikull() {
		return artikull;
	}
	public void setArtikull(String artikull) {
		this.artikull = artikull;
	}
	public List<Autor> getAutoretEFiltruar() {
		return autoretEFiltruar;
	}
	public void setAutoretEFiltruar(List<Autor> autoretEFiltruar) {
		this.autoretEFiltruar = autoretEFiltruar;
	}
	public void setListaAutore(List<Autor> listaAutore) {
		this.listaAutore = listaAutore;
	}
	public List<Autor> getAutoretEPerzgjedhur() {
		return autoretEPerzgjedhur;
	}
	public void setAutoretEPerzgjedhur(List<Autor> autoretEPerzgjedhur) {
		this.autoretEPerzgjedhur = autoretEPerzgjedhur;
	}
	
	
	
	public List<Autor> getListaAutore() {
		return (listaAutore);
	}
	
	public String tabelaAutoretAdmin() {
		return("/admin/autoret.xhtml?faces-redirect=true");
	}
	
	public void fshirjeAutori() {
		for(int i=0; i<autoretEPerzgjedhur.size(); i++) {
			boolean sukses = AutorDB.uFshiAutori(autoretEPerzgjedhur.get(i).getId());
			if(sukses) {
				System.out.println("Autori u fshi me sukses");
				//listaAutore = AutorDB.merrAutoret();
			}else {
				System.out.println("Gabim! Nuk u fshi autori");
			}
		}
	}
	
	
	public void shtimAutori() {
		boolean shtim = AutorDB.shtoAutor(this);
		if(shtim) {
			System.out.println("Autori i ri u shtua me sukses");
			PrimeFaces current = PrimeFaces.current();
			current.executeScript("PF('dialogAutorIRI').hide()");
		}else {
			System.out.println("Gabim ne Autor -> shtimAutori - Autori nuk u shtua");
			PrimeFaces current = PrimeFaces.current();
			current.executeScript("PF('dialogAutorIRI').show()");
		}
	}
	
	
	public void dialogIRi() {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('dialogAutorIRI').hide()");
		current.executeScript("PF('dialogArtikullIRI').show()");
	}
	
	
	
}
