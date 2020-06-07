package Projekti2.Projekti2;

import java.awt.HeadlessException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PerdoruesDBinfo implements PerdoruesLookup{
	
	private Perdorues perdorues;
	
	private String JDBC_DRIVER = "org.postgresql.Driver";
	private String db_url = "jdbc:postgresql://localhost:5432/conference_review";
	private String db_username = "postgres";
	private String db_password = "toor";
	
	
	
	@Override
	public Perdorues gjejPerdoruesin(String id) {
		/*
		 * if(id == null) { System.out.println("ka hyre id null");
		 * perdorues.setId("aaa"); return perdorues; }
		 */
		 
		
		try {
			Class.forName(JDBC_DRIVER);
			Connection con = null;
			Statement stmt = null;
			con = DriverManager.getConnection(db_url, db_username, db_password);
			stmt = con.createStatement();
			
			String sql = "SELECT id, password, kategoria FROM login "
						+ " WHERE id = '" + id.trim() + "';";      
			
			System.out.println(sql);
			
			/*
			 * PreparedStatement dbId = con.prepareStatement(sql); dbId.setString(0,
			 * id.trim());
			 */
			
			
			ResultSet rezultati = stmt.executeQuery(sql);
			
			
			
			if(rezultati.next() == false) { 
				System.out.println("perdoruesi nuk ekziston");
				perdorues.setId(null); 
			} else {
				  	do {
					  String ngaDBid = rezultati.getString(1);
					  String ngaDBpassword = rezultati.getString(2);
					  char ngaDBkategoria = rezultati.getString(3).charAt(0);
				
					  perdorues = new Perdorues(ngaDBid, ngaDBpassword, ngaDBkategoria);
					
					  System.out.println("ka rezultat nga DB");
					  System.out.println(ngaDBid + "  " + ngaDBpassword);
					  
				  	}while(rezultati.next());
			}
			
			
	return perdorues;
		} catch (HeadlessException | ClassNotFoundException | NumberFormatException | SQLException e) {
		e.printStackTrace();
		return null;
		}
		

	};
	
	
	private void shtoPerdorues() {
		
	}

}