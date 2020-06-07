package Projekti2.Projekti2;

public class Perdorues {
	
	
	private String id;
	private String fjalekalimi;
	private char kategoria;
	
	public Perdorues(String id, String fjalekalimi, char kategoria) {
		
		this.id = id;
		this.fjalekalimi = fjalekalimi;
		this.kategoria = kategoria;
	}
	
	
	public char getKategoria() {
		return kategoria;
	}
	public void setKategoria(char kategoria) {
		this.kategoria = kategoria;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFjalekalimi() {
		return fjalekalimi;
	}
	public void setFjalekalimi(String fjalekalimi) {
		this.fjalekalimi = fjalekalimi;
	}
	
}
