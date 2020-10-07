package com.ikubinfo.konferenca.managedBeans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;
import com.ikubinfo.konferenca.dto.ArtikullDto;
import com.ikubinfo.konferenca.dto.AutorDto;
import com.ikubinfo.konferenca.dto.ShqyrtuesArtikullDto;
import com.ikubinfo.konferenca.service.ArtikullService;
import com.ikubinfo.konferenca.service.AutorService;
import com.ikubinfo.konferenca.service.VleresimeService;

@ManagedBean(name = "shqyrtuesHolderBean")
@SessionScoped
	
public class ShqyrtuesHolderBean {
	
	private static Logger log = Logger.getLogger(ShqyrtuesHolderBean.class);
	
	@ManagedProperty(value = "#{autorService}")
	AutorService autorService;
	
	@ManagedProperty(value = "#{vleresimeService}")
	VleresimeService vleresimeService;
	
	@ManagedProperty(value = "#{loggedUserBean}")
	LoggedUserBean loggedUserBean;
	
	@ManagedProperty(value = "#{artikullService}")
	ArtikullService artikullService;
	

	private ArtikullDto artikullPerVleresim = new ArtikullDto();
	private ShqyrtuesArtikullDto vleresimIRi = new ShqyrtuesArtikullDto();
	private ShqyrtuesArtikullDto vleresimEdit = new ShqyrtuesArtikullDto();
	private List<AutorDto> autoretPerArtikull;
	private List<ShqyrtuesArtikullDto> listaVleresimetEMia;
	private List<ArtikullDto> artikujPotencialePerVlersim;
	

	public ArtikullService getArtikullService() {
		return artikullService;
	}
	public void setArtikullService(ArtikullService artikullService) {
		this.artikullService = artikullService;
	}
	public VleresimeService getVleresimeService() {
		return vleresimeService;
	}
	public void setVleresimeService(VleresimeService vleresimeService) {
		this.vleresimeService = vleresimeService;
	}
	public LoggedUserBean getLoggedUserBean() {
		return loggedUserBean;
	}
	public void setLoggedUserBean(LoggedUserBean loggedUserBean) {
		this.loggedUserBean = loggedUserBean;
	}
	public List<ArtikullDto> getArtikujPotencialePerVlersim() {
		return artikujPotencialePerVlersim;
	}
	public void setArtikujPotencialePerVlersim(List<ArtikullDto> artikujPotencialePerVlersim) {
		this.artikujPotencialePerVlersim = artikujPotencialePerVlersim;
	}
	public List<ShqyrtuesArtikullDto> getListaVleresimetEMia() {
		return listaVleresimetEMia;
	}
	public void setListaVleresimetEMia(List<ShqyrtuesArtikullDto> listaVleresimetEMia) {
		this.listaVleresimetEMia = listaVleresimetEMia;
	}
	public ArtikullDto getArtikullPerVleresim() {
		return artikullPerVleresim;
	}
	public void setArtikullPerVleresim(ArtikullDto artikullPerVleresim) {
		this.artikullPerVleresim = artikullPerVleresim;
	}
	public ShqyrtuesArtikullDto getVleresimIRi() {
		return vleresimIRi;
	}
	public void setVleresimIRi(ShqyrtuesArtikullDto vleresimIRi) {
		this.vleresimIRi = vleresimIRi;
	}
	public List<AutorDto> getAutoretPerArtikull() {
		return autoretPerArtikull;
	}
	public void setAutoretPerArtikull(List<AutorDto> autoretPerArtikull) {
		this.autoretPerArtikull = autoretPerArtikull;
	}
	public AutorService getAutorService() {
		return autorService;
	}
	public void setAutorService(AutorService autorService) {
		this.autorService = autorService;
	}
	public ShqyrtuesArtikullDto getVleresimEdit() {
		return vleresimEdit;
	}
	public void setVleresimEdit(ShqyrtuesArtikullDto vleresimEdit) {
		this.vleresimEdit = vleresimEdit;
	}
	
	
	
	
	
	public void fillAutoretPerArtikull(){
		List<AutorDto> listaAutore = new ArrayList<AutorDto>();
		AutorDto autorDto = new AutorDto();
		autorDto = autorService.getAutorByArtikullId(artikullPerVleresim.getArtikullId());

		if(autorDto.getArtikullDto()!=null) {
			listaAutore = autorDto.getArtikullDto().getAutorDtoListForArtikull();
		}
		this.autoretPerArtikull = listaAutore;
	} 
	
	
	
	public void fillListaVleresimetEMia(){
		List<ShqyrtuesArtikullDto> lista = new ArrayList<ShqyrtuesArtikullDto>();
		List<ShqyrtuesArtikullDto> listafull = new ArrayList<ShqyrtuesArtikullDto>();
		listafull = vleresimeService.getShqyrtuesArtikullListForShqyrtues();
		if(!listafull.isEmpty() 
				&& loggedUserBean.getLoggedShqyrtues()!=null) {
			for(ShqyrtuesArtikullDto shaDTO: listafull) {
				if(shaDTO.getShqrtid().equals(loggedUserBean.getLoggedShqyrtues().getIdEmail())) {
					lista.add(shaDTO);
				}
			}
		}
		listaVleresimetEMia = lista;
	}
	
	
	
	public void fillListaArtikujPotencialePerVleresim(){
		List<ArtikullDto> listafull = new ArrayList<ArtikullDto>();
		listafull = artikullService.getArtikujLista();
		
		if(!listafull.isEmpty()) {
			
			for(Iterator<ArtikullDto> iterator = listafull.iterator(); iterator.hasNext();) {
				ArtikullDto art = iterator.next();

				if (!listaVleresimetEMia.isEmpty() && listaVleresimetEMia!=null) {
					for(ShqyrtuesArtikullDto shaDTO: listaVleresimetEMia) {
						if (art.getArtikullId()==shaDTO.getArid()) {
							iterator.remove();
						}
					}
				}
			}
		}
		artikujPotencialePerVlersim = listafull;
	}
	
	
	public String shqyrtuesArtikujListaOpen() {
		fillListaVleresimetEMia();
		fillListaArtikujPotencialePerVleresim();
		return "/shqyrtues_res/artikuj.xhtml?faces-redirect=true";
	}
	
	public String shqyrtuesVleresimetEMia() {
		fillListaVleresimetEMia();
		fillListaArtikujPotencialePerVleresim();
		return "/shqyrtues_res/vleresimetEMia.xhtml?faces-redirect=true";
	}
	
	
	
}
