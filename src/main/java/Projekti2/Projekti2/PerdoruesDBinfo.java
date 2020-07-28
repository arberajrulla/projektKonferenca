package Projekti2.Projekti2;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@ManagedBean (name="perdoruesDBinfo")
@SessionScoped
@RequestScoped
public class PerdoruesDBinfo implements PerdoruesLookup{
	
	private Perdorues perdorues;
	private static String mesazh;
	
	
	public String getMesazh() {
		return mesazh;
	}	

	public static List<Perdorues> merrPerdoruesit() {
		List <Perdorues> listaPerdorues = new ArrayList<Perdorues>();
		Connection con = Databaza.merrLidhjen();
		try { 
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM login;");
			ResultSet rezultati = ps.executeQuery();
			
			while(rezultati.next()) {
				String emri = rezultati.getString(1);
				String mbiemri = rezultati.getString(2);
				String username = rezultati.getString(3);
				String email = rezultati.getString(4);
				String fjalekalimi = rezultati.getString(5);
				String kategoria = rezultati.getString(6);
				String nrCel = rezultati.getString(7);
				
				listaPerdorues.add(new Perdorues(username, emri, mbiemri, 
						email, nrCel, fjalekalimi, kategoria));
			}
			
			ps.close();
			rezultati.close();
			
			return (listaPerdorues);
				
			}catch(Exception e){
				System.out.println("PerdoruesDBInfo >> merrPerdoruesit()" + e);
				return (null);
			}finally {
				Databaza.mbyllLidhjen(con);
			}
		
		
	}

	@Override
	public Perdorues gjejPerdoruesin(String username) {

		try {
			Connection lidhja = Databaza.merrLidhjen();
			PreparedStatement prpStm = null;
			String sql = "SELECT username, password, kategoria FROM login "
						+ " WHERE username = ? ;";
			
			prpStm = lidhja.prepareStatement(sql);
			prpStm.setString(1, username.trim());
			ResultSet rezultati = prpStm.executeQuery();
			
			if(rezultati.next() == false) { 
				System.out.println("perdoruesi nuk ekziston");
				perdorues = new Perdorues("", "", "");
				Databaza.mbyllLidhjen(lidhja);
			} else {
					do {
						String ngaDBusername = rezultati.getString(1);
						String ngaDBpassword = rezultati.getString(2);
						String ngaDBkategoria = rezultati.getString(3);
						
						perdorues = new Perdorues(ngaDBusername, ngaDBpassword, ngaDBkategoria);
						
						System.out.println("ka rezultat nga DB");
						System.out.println(ngaDBusername + "  " + ngaDBpassword);
				  	}while(rezultati.next());
				  	Databaza.mbyllLidhjen(lidhja);
			}
			Databaza.mbyllLidhjen(lidhja);
			return perdorues;
	
		} catch (Exception e) {
		e.printStackTrace();
		return null;
		}
	};
	
	
	public static boolean shtoPerdorues(PerdoruesiRi p) {
		if(p.getEmri()==null || p.getMbiemri()==null || 
				p.getUsername()==null || p.getFjalekalimi1()==null ||
				p.getFjalekalimi2()==null || p.getEmail()==null ||
				p.getKategoria()==null || p.getNrCel()==null) {
			return false;
		}
		
		try {
			Connection lidhja = Databaza.merrLidhjen();
			PreparedStatement prpStm = null;
			String sql = "SELECT username FROM login WHERE username=?";
			prpStm = lidhja.prepareStatement(sql);
			prpStm.setString(1, p.getUsername());
			ResultSet rezultat = prpStm.executeQuery();
			
			
			
			if(rezultat.next()) {
				mesazh = "Perdoruesi me kete username ekziston";
				return (false);
			}else {
				prpStm.clearParameters();
				sql = "INSERT INTO public.login(emri, mbiemri, username, email, password, kategoria, nrcel)" + 
						" VALUES (?, ?, ?, ?, ?, ?, ?);";
				System.out.println(p.getEmri().trim());
				prpStm = lidhja.prepareStatement(sql);
				prpStm.setString(1, p.getEmri().trim());
				prpStm.setString(2, p.getMbiemri().trim());
				prpStm.setString(3, p.getUsername().trim());
				prpStm.setString(4, p.getEmail().trim());
				prpStm.setString(5, p.getFjalekalimi1().trim());
				prpStm.setString(6, p.getKategoria());
				prpStm.setString(7, p.getNrCel().trim());
				
				int rreshtiRi = prpStm.executeUpdate();
				System.out.println(rreshtiRi);
				if(rreshtiRi == 1) {
					Databaza.mbyllLidhjen(lidhja);
					return true;
				} else {
					Databaza.mbyllLidhjen(lidhja);
					return false;
				}
			}
			

		} catch (Exception e) {
		e.printStackTrace();
		return false;
		}
	}
	
	public static boolean uFshiPerdoruesi(String username) {
			try (Connection lidhja = Databaza.merrLidhjen();){ 
				System.out.println("Username per tu fshire " + username);
				PreparedStatement ps = null; 
				String sql = "DELETE FROM login WHERE username= ? ;";		
				ps = lidhja.prepareStatement(sql);
				ps.setString(1, username.trim());
				
				int rreshtIFshire = ps.executeUpdate();
				System.out.println(rreshtIFshire);
				if(rreshtIFshire==1) {
					System.out.println("Rreshti u fshi me sukses ne DB!");
					Databaza.mbyllLidhjen(lidhja);
					return (true);
				}else {
					System.out.println("Rreshti nuk u fshi ne DB - update value: " + rreshtIFshire);
					Databaza.mbyllLidhjen(lidhja);
					return (false);
				}
					
			}catch(Exception e){
				System.out.println("PerdoruesDBInfo >> fshiPerdorues()" + e);
				return (false);
			}
	}
	
	
}
