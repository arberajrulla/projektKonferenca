package Projekti2.Projekti2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArtikullDB {
	
	public static List<Artikull> merrArtikujt() {
		try (Connection con = Databaza.merrLidhjen();){
			PreparedStatement ps = con.prepareStatement("SELECT * FROM artikull;");
			ResultSet rezultati = ps.executeQuery();
			ArrayList<Artikull> listaArtikuj = new ArrayList<Artikull>();
			while(rezultati.next()) {
				int artikull_id = rezultati.getInt(1);
				String titulli = rezultati.getString(2);
				String abstrakti = rezultati.getString(3);
				String dokumenti_elektronik = rezultati.getString(4);
				String kontakt = rezultati.getString(5);
				listaArtikuj.add(new Artikull(artikull_id, titulli, abstrakti, dokumenti_elektronik, kontakt));
			}
			
			Databaza.mbyllLidhjen(con);
			return (listaArtikuj);
			
			}catch(Exception e){
				System.out.println("ArtikullDB >> merrArtikujt()" + e);
				return (null);
			}
	}
	
	
	public static boolean shtoArtikull(Artikull ar) {

		if(ar.getTitulli()==null || ar.getAbstrakti()==null || ar.getDokumenti_elektronik()==null
				|| ar.getKontakt()==null ) {
			return false;
		}

		try {
			Connection lidhja = Databaza.merrLidhjen();
			PreparedStatement prpStm = null;
			String sql = "SELECT kontakt FROM artikull WHERE kontakt=?";
			prpStm = lidhja.prepareStatement(sql);
			prpStm.setString(1, ar.getKontakt());
			ResultSet rezultat = prpStm.executeQuery();
			
			if(rezultat.next()) {
				System.out.println("Artikulli ekziston");
				return (false);
			}else {
				prpStm.clearParameters();
				sql = "INSERT INTO public.artikull(titulli, abstrakti, dokumenti_elektronik, kontakt)" + 
						" VALUES (?, ?, ?, ?);";
				prpStm = lidhja.prepareStatement(sql);
				prpStm.setString(1, ar.getTitulli().trim());
				prpStm.setString(2, ar.getAbstrakti().trim());
				prpStm.setString(3, ar.getDokumenti_elektronik().trim());
				prpStm.setString(4, ar.getKontakt());
				
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
	
}
