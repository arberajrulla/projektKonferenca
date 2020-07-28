package Projekti2.Projekti2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;

@ManagedBean(name="admin")
@RequestScoped
public class Admin implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Perdorues> perdoruesitEPerzgjedhur = new ArrayList<Perdorues>();
	private List<Perdorues> perdoruesitAll;
	private List<Perdorues> perdoruesitFiltruar;
	private PerdoruesiRi perdoruesIRi = new PerdoruesiRi();
	public Admin(){
		perdoruesitAll = PerdoruesDBinfo.merrPerdoruesit();
	}
	
	public List<Perdorues> getPerdoruesitEPerzgjedhur() {
		return perdoruesitEPerzgjedhur;
	}
	public void setPerdoruesitEPerzgjedhur(List<Perdorues> perdoruesitEPerzgjedhur) {
		this.perdoruesitEPerzgjedhur = perdoruesitEPerzgjedhur;
	}
	public List<Perdorues> getPerdoruesitAll() {
		return perdoruesitAll;
	}
	public void setPerdoruesitAll(List<Perdorues> perdoruesitAll) {
		this.perdoruesitAll = perdoruesitAll;
	}
	public List<Perdorues> getPerdoruesitFiltruar() {
		return perdoruesitFiltruar;
	}
	public void setPerdoruesitFiltruar(List<Perdorues> perdoruesitFiltruar) {
		this.perdoruesitFiltruar = perdoruesitFiltruar;
	}
	public PerdoruesiRi getPerdoruesIRi() {
		return perdoruesIRi;
	}
	public void setPerdoruesIRi(PerdoruesiRi perdoruesIRi) {
		this.perdoruesIRi = perdoruesIRi;
	}
	
	
	public String kryesoreLinkAdmin() {
		return("/admin/faqja1.xhtml?faces-redirect=true");
	}
	
	public String modifikoProfilinAdmin() {
		return("/admin/modifikoProfilin.xhtml?faces-redirect=true");
	}	
	
	
	public String ndryshoPasswordAdmin() {
		return("/admin/ndryshimFjalekalimi.xhtml?faces-redirect=true");
	}	
	
	public String dilAdmin() {
		return("login.xhtml");
	}	
	
	public void fshiPerdorues(ActionEvent event) {
		for(int i=0; i<perdoruesitEPerzgjedhur.size(); i++) {
			boolean sukses = PerdoruesDBinfo.uFshiPerdoruesi(perdoruesitEPerzgjedhur.get(i).getUsername());
			if(sukses) {
				System.out.println("Perdoruesi u fshi me sukses");
				perdoruesitAll = PerdoruesDBinfo.merrPerdoruesit();
			}else {
				System.out.println("Gabim! Nuk u fshi perdoruesi");
			}
		}
	}

	public void shtimPerdoruesi() {
		boolean shtim = PerdoruesDBinfo.shtoPerdorues(perdoruesIRi);
		if(shtim) {
			System.out.println("Perdoruesi i ri u shtua me sukses");
		}else {
			System.out.println("Gabim ne adminDB -> shtimPerdoruesi - Perdoruesi nuk u shtua");
			PrimeFaces current = PrimeFaces.current();
			current.executeScript("PF('dialogPerdoruesIRI').show()");
		}
	}
}
