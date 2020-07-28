package Projekti2.Projekti2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShqyrtuesDB {
	
	public static List<Shqyrtues> merrShqyrtuesit() {
		try (Connection con = Databaza.merrLidhjen();){
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM shqyrtues;");
			ResultSet rezultati = ps.executeQuery();
			ArrayList<Shqyrtues> listaShqyrtues = new ArrayList<Shqyrtues>();
			while(rezultati.next()) {
				String email = rezultati.getString(1);
				String emri = rezultati.getString(2);
				String mbiemri = rezultati.getString(3);
				String institucioni = rezultati.getString(4);
				
				listaShqyrtues.add(new Shqyrtues(email, emri, mbiemri, institucioni));
			}
			
			Databaza.mbyllLidhjen(con);
			return (listaShqyrtues);
			
			}catch(Exception e){
				System.out.println("ShqyrtuesDB >> merrShqyrtuesit()" + e);
				return (null);
			}
	}
	
	
	
	public static boolean shtoShqyrtues(Shqyrtues s) {
		if(s.getId_email()==null || s.getEmri()==null || s.getMbiemri()==null
				|| s.getInstitucioni()==null) {
			return false;
		}

		try {
			Connection lidhja = Databaza.merrLidhjen();
			PreparedStatement prpStm = null;
			String sql = "SELECT id_email FROM shqyrtues WHERE id_email=?";
			prpStm = lidhja.prepareStatement(sql);
			prpStm.setString(1, s.getId_email());
			ResultSet rezultat = prpStm.executeQuery();
			
			if(rezultat.next()) {
				System.out.println("Shqyrtuesi me kete email ekziston");
				return (false);
			}else {
				prpStm.clearParameters();
				sql = "INSERT INTO public.shqyrtues(id_email, emri, mbiemri, institucioni)" + 
						" VALUES (?, ?, ?, ?);";
				prpStm = lidhja.prepareStatement(sql);
				prpStm.setString(1, s.getId_email().trim());
				prpStm.setString(2, s.getEmri().trim());
				prpStm.setString(3, s.getMbiemri().trim());
				prpStm.setString(4, s.getInstitucioni());
				
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

	public static boolean uFshiShqyrtuesi(String email) {
		try (Connection lidhja = Databaza.merrLidhjen();){ 
			System.out.println("Id shqyrtuesi per tu fshire " + email);
			PreparedStatement ps = null; 
			String sql = "DELETE FROM shqyrtues WHERE id_email= ? ;";		
			ps = lidhja.prepareStatement(sql);
			ps.setString(1, email);
			
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
			System.out.println("ShqyrtuesDB >> fshiShqyrtues()" + e);
			return (false);
		}
	}
	
	
}
