package Projekti2.Projekti2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;

@ManagedBean(name="vleresim")
@RequestScoped

public class Vleresim implements Serializable{




		private static final long serialVersionUID = 1L;
		
		private String shqyrtues_id, rekomandim, emriFull, artikulli;
		private int artikull_id, meritaTeknike, kuptueshmeria, origjinaliteti, perkatesiKonference;
		private List<Vleresim> listaVleresime = new ArrayList<Vleresim>();
		private List<Vleresim> vleresimetEPerzgjedhura = new ArrayList<Vleresim>();
		private List<Vleresim> vleresimetEFiltruara;
		
		public Vleresim() {
			listaVleresime = VleresimDB.merrVleresimet();
		}
		
		public Vleresim(String shqyrtues_id, String rekomandim, String emriFull, String artikulli, int artikull_id,
				int meritaTeknike, int kuptueshmeria, int origjinaliteti, int perkatesiKonference) {
			super();
			this.shqyrtues_id = shqyrtues_id;
			this.rekomandim = rekomandim;
			this.emriFull = emriFull;
			this.artikulli = artikulli;
			this.artikull_id = artikull_id;
			this.meritaTeknike = meritaTeknike;
			this.kuptueshmeria = kuptueshmeria;
			this.origjinaliteti = origjinaliteti;
			this.perkatesiKonference = perkatesiKonference;
		}

		public String getShqyrtues_id() {
			return shqyrtues_id;
		}
		public void setShqyrtues_id(String shqyrtues_id) {
			this.shqyrtues_id = shqyrtues_id;
		}
		public String getRekomandim() {
			return rekomandim;
		}
		public void setRekomandim(String rekomandim) {
			this.rekomandim = rekomandim;
		}
		public String getEmriFull() {
			return emriFull;
		}
		public void setEmriFull(String emriFull) {
			this.emriFull = emriFull;
		}
		public String getArtikulli() {
			return artikulli;
		}
		public void setArtikulli(String artikulli) {
			this.artikulli = artikulli;
		}
		public int getMeritaTeknike() {
			return meritaTeknike;
		}
		public void setMeritaTeknike(int meritaTeknike) {
			this.meritaTeknike = meritaTeknike;
		}
		public int getKuptueshmeria() {
			return kuptueshmeria;
		}
		public void setKuptueshmeria(int kuptueshmeria) {
			this.kuptueshmeria = kuptueshmeria;
		}
		public int getOrigjinaliteti() {
			return origjinaliteti;
		}
		public void setOrigjinaliteti(int origjinaliteti) {
			this.origjinaliteti = origjinaliteti;
		}
		public int getPerkatesiKonference() {
			return perkatesiKonference;
		}
		public void setPerkatesiKonference(int perkatesiKonference) {
			this.perkatesiKonference = perkatesiKonference;
		}
		public List<Vleresim> getListaVleresime() {
			return listaVleresime;
		}
		public void setListaVleresime(List<Vleresim> listaVleresime) {
			this.listaVleresime = listaVleresime;
		}
		public List<Vleresim> getVleresimetEPerzgjedhura() {
			return vleresimetEPerzgjedhura;
		}
		public void setVleresimetEPerzgjedhura(List<Vleresim> vleresimetEPerzgjedhura) {
			this.vleresimetEPerzgjedhura = vleresimetEPerzgjedhura;
		}
		public List<Vleresim> getVleresimetEFiltruara() {
			return vleresimetEFiltruara;
		}
		public void setVleresimetEFiltruara(List<Vleresim> vleresimetEFiltruara) {
			this.vleresimetEFiltruara = vleresimetEFiltruara;
		}
		public String tabelaVleresimetAdmin() {
			return("/admin/vleresimet.xhtml?faces-redirect=true");
		}
		public int getArtikull_id() {
			return artikull_id;
		}
		public void setArtikull_id(int artikull_id) {
			this.artikull_id = artikull_id;
		}
		
		public void fshirjeVleresimi() {
			for(int i=0; i<vleresimetEPerzgjedhura.size(); i++) {
				boolean sukses = VleresimDB.uFshiVleresimi(vleresimetEPerzgjedhura.get(i).getShqyrtues_id());
				if(sukses) {
					System.out.println("Vleresimi u fshi me sukses");
					
				}else {
					System.out.println("Gabim! Nuk u fshi vleresimi");
				}
			}
		}
		
		
		public void shtimVleresimi() {
			boolean shtim = VleresimDB.shtoVleresim(this);
			if(shtim) {
				System.out.println("Vleresimi i ri u shtua me sukses");
				PrimeFaces current = PrimeFaces.current();
				current.executeScript("PF('dialogVleresimIRI').hide()");
			}else {
				System.out.println("Gabim ne Vleresim -> shtimVleresimi - Vleresimi nuk u shtua");
				PrimeFaces current = PrimeFaces.current();
				current.executeScript("PF('dialogVleresimIRI').show()");
			}
		}
		
		
		
	}
