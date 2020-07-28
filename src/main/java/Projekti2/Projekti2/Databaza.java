package Projekti2.Projekti2;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Databaza {
	
	public static Connection merrLidhjen() {
		
		final String JDBC_DRIVER = "org.postgresql.Driver";
		final String db_url = "jdbc:postgresql://localhost:5432/conference_review";
		final String db_username = "postgres";
		final String db_fjalekalim = "toor";
		
		try {
			Class.forName(JDBC_DRIVER);
			Connection lidhja = DriverManager.getConnection(db_url, db_username, db_fjalekalim);
			return lidhja;
		} catch(Exception e) {
			System.out.println("Merr Lidhjen me DB: " + e.getMessage());
			return null;
		}
	}
	
	
	public static void mbyllLidhjen(Connection con) {
		try {
			con.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
