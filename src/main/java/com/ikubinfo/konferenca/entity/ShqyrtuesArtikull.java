package com.ikubinfo.konferenca.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shqyrtues_artikull")
public class ShqyrtuesArtikull {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "shqrtid")
	private String shqrtid;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "arid")
	private int arid;

	@Column(name = "meritaTeknike")
	private int meritaTeknike; 
	
	@Column(name = "kuptueshmeria")
	private int kuptueshmeria; 
	
	@Column(name = "origjinaliteti")
	private int origjinaliteti; 
	
	@Column(name = "perkatesiKonference")
	private int perkatesiKonference; 
	
}
