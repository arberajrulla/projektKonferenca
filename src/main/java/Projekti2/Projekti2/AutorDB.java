package Projekti2.Projekti2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AutorDB {
	
	public static List<Autor> merrAutoret() {
			List <Autor> listaAutore = new ArrayList<Autor>();		
			
		try (Connection con = Databaza.merrLidhjen();){
			PreparedStatement ps = con.prepareStatement("SELECT autor.*, artikull.titulli FROM autor, artikull WHERE autor.artikull_id= artikull.artikull_id ORDER BY autor.emri;");
			ResultSet rezultati = ps.executeQuery();
			
			  while(rezultati.next()) { 
				  String email = rezultati.getString(1); 
				  String emri = rezultati.getString(2); 
				  String mbiemri = rezultati.getString(3);
				  int artikull_id = rezultati.getInt(4);
				  int id = rezultati.getInt(5);
				  String artikulli = rezultati.getString(6);
			  	  
			  listaAutore.add(new Autor(email, emri, mbiemri, artikulli, id, artikull_id));
			  }
				ps.close();
				rezultati.close();
				Databaza.mbyllLidhjen(con);			
			return (listaAutore);
				
			}catch(Exception e){
				System.out.println("AutorDB >> merrAutoret()" + e);
				return (null);
			}
	}

	/*
	 * public void fshiAutor() { for(int i=0; i<autoretEPerzgjedhur.size(); i++) {
	 * boolean sukses =
	 * PerdoruesDBinfo.uFshiPerdoruesi(perdoruesitEPerzgjedhur.get(i).getUsername())
	 * ; if(sukses) { System.out.println("Perdoruesi u fshi me sukses");
	 * perdoruesitAll = PerdoruesDBinfo.merrPerdoruesit(); }else {
	 * System.out.println("Gabim! Nuk u fshi perdoruesi"); } } }
	 */
	
	public static boolean shtoAutor(Autor a) {
		System.out.println("Artikull id: " + a.getArtikull_id());
		if(a.getEmail_id()==null || a.getEmri()==null || a.getMbiemri()==null
				|| a.getArtikull_id()==0 || a.getArtikull_id()==-1 ) {
			return false;
		}

		try {
			Connection lidhja = Databaza.merrLidhjen();
			PreparedStatement prpStm = null;
			String sql = "SELECT id FROM autor WHERE email_id=?";
			prpStm = lidhja.prepareStatement(sql);
			System.out.println("Autor email id "+ a.getEmail_id());
			prpStm.setString(1, a.getEmail_id());
			ResultSet rezultat = prpStm.executeQuery();
			
			if(rezultat.next()) {
				System.out.println("Autori me kete email ekziston");
				return (false);
			}else {
				prpStm.clearParameters();
				sql = "INSERT INTO public.autor(email_id, emri, mbiemri, artikull_id)" + 
						" VALUES (?, ?, ?, ?);";
				prpStm = lidhja.prepareStatement(sql);
				prpStm.setString(1, a.getEmail_id().trim());
				prpStm.setString(2, a.getEmri().trim());
				prpStm.setString(3, a.getMbiemri().trim());
				prpStm.setInt(4, a.getArtikull_id());
				
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

	public static boolean uFshiAutori(int id) {
		try (Connection lidhja = Databaza.merrLidhjen();){ 
			System.out.println("Id autori per tu fshire " + id);
			PreparedStatement ps = null; 
			String sql = "DELETE FROM autor WHERE id= ? ;";		
			ps = lidhja.prepareStatement(sql);
			ps.setInt(1, id);
			
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
			System.out.println("AutorDB >> fshiAutor()" + e);
			return (false);
		}
	}
}