package Projekti2.Projekti2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

	public class VleresimDB {
		
		public static List<Vleresim> merrVleresimet() {
				List <Vleresim> listaVleresime = new ArrayList<Vleresim>();		
				
			try (Connection con = Databaza.merrLidhjen();){
				PreparedStatement ps = con.prepareStatement("SELECT shqyrtues_artikull.*, shqyrtues.emri, shqyrtues.mbiemri, artikull.titulli FROM shqyrtues, shqyrtues_artikull, artikull WHERE shqyrtues_artikull.shqrtid=shqyrtues.id_email AND shqyrtues_artikull.arid = artikull.artikull_id ORDER BY shqyrtues.emri;");
				ResultSet rezultati = ps.executeQuery();
				
				  while(rezultati.next()) { 
					  String shqyrtues_id = rezultati.getString(1); 
					  int artikull_id = rezultati.getInt(2); 
					  int meritaTeknike = rezultati.getInt(3);
					  int kuptueshmeria = rezultati.getInt(4);
					  int origjinaliteti = rezultati.getInt(5);
					  int perkatesiKonference = rezultati.getInt(6);
					  String rekomandime = rezultati.getString(7);
					  String emri = rezultati.getString(8);
					  String mbiemri = rezultati.getString(9);
					  String titulli = rezultati.getString(10);
				  	  
					  listaVleresime.add(new Vleresim(shqyrtues_id, rekomandime, emri + " " + mbiemri, 
							  titulli, artikull_id, meritaTeknike, kuptueshmeria, origjinaliteti, perkatesiKonference
							  ));
				  }
					ps.close();
					rezultati.close();
					Databaza.mbyllLidhjen(con);			
				return (listaVleresime);
					
				}catch(Exception e){
					System.out.println("VleresimDB >> merrVleresimet()" + e);
					return (null);
				}
		}


		
		public static boolean shtoVleresim(Vleresim v) {
			if(v.getShqyrtues_id()==null || v.getMeritaTeknike()==-1 || v.getKuptueshmeria()==-1 
					|| v.getOrigjinaliteti()==-1 || v.getPerkatesiKonference()==-1
					|| v.getArtikull_id()==0 || v.getArtikull_id()==-1 ) {
				return false;
			}

			try {
				Connection lidhja = Databaza.merrLidhjen();
				PreparedStatement prpStm = null;
				String sql = "SELECT shqrtid FROM shqyrtues_artikull WHERE shqrtid=?";
				prpStm = lidhja.prepareStatement(sql);
				prpStm.setString(1, v.getShqyrtues_id());
				ResultSet rezultat = prpStm.executeQuery();
				
				if(rezultat.next()) {
					System.out.println("Vleresimi nga shqyrtuesi ekziston");
					return (false);
				}else {
					prpStm.clearParameters();
					sql = "INSERT INTO public.shqyrtues_artikull( shqrtid, arid, merita_teknike, kuptueshmeria, origjinaliteti, perkatesi_konference, rekomandime) " + 
							"	VALUES (?, ?, ?, ?, ?, ?, ?);";
					prpStm = lidhja.prepareStatement(sql);
					prpStm.setString(1, v.getShqyrtues_id().trim());
					prpStm.setInt(2, v.getArtikull_id());
					prpStm.setInt(3, v.getMeritaTeknike());
					prpStm.setInt(4, v.getKuptueshmeria());
					prpStm.setInt(5, v.getOrigjinaliteti());
					prpStm.setInt(6, v.getPerkatesiKonference());
					prpStm.setString(7, v.getRekomandim());
					int rreshtiRi = prpStm.executeUpdate();
					System.out.println(rreshtiRi);
					if(rreshtiRi == 1) {
						prpStm.close();
						rezultat.close();					
						Databaza.mbyllLidhjen(lidhja);
						return true;
					} else {
						prpStm.close();
						rezultat.close();					
						Databaza.mbyllLidhjen(lidhja);
						return false;
					}
				}
				
			} catch (Exception e) {
			e.printStackTrace();
			return false;
			}
		}

		public static boolean uFshiVleresimi(String shqyrtues_id) {
			try (Connection lidhja = Databaza.merrLidhjen();){ 
				System.out.println("Vleresimi per tu fshire " + shqyrtues_id);
				PreparedStatement ps = null; 
				String sql = "DELETE FROM shqyrtues_artikull WHERE shqrtid= ? ;";		
				ps = lidhja.prepareStatement(sql);
				ps.setString(1, shqyrtues_id);
				
				int rreshtIFshire = ps.executeUpdate();
				System.out.println(rreshtIFshire);
				if(rreshtIFshire==1) {
					System.out.println("Rreshti u fshi me sukses ne DB!");
					ps.close();			
					Databaza.mbyllLidhjen(lidhja);
					return (true);
				}else {
					System.out.println("Rreshti nuk u fshi ne DB - update value: " + rreshtIFshire);
					ps.close();				
					Databaza.mbyllLidhjen(lidhja);
					return (false);
				}
			}catch(Exception e){
				System.out.println("VleresimDB >> uFshiVlersimi()" + e);
				return (false);
			}
		}
	}
	
