package Projekti2.Projekti2;

import javax.faces.bean.*;

@ManagedBean (name = "autentikim")
@SessionScoped
@RequestScoped

public class Autentikim {
	
	private String id;
	private String fjalekalim;
	private Perdorues perdorues;
	private String msg1,msg2;
	private static PerdoruesLookup lookupPerdorues = new PerdoruesDBinfo();

	public String getMsg1() {
		return msg1;
	}

	public void setMsg1(String msg1) {
		this.msg1 = msg1;
	}

	public String getMsg2() {
		return msg2;
	}

	public void setMsg2(String msg2) {
		this.msg2 = msg2;
	}


	
	public String gabim(int poz) {
		
		
		switch(poz) {
			case 0:
				return ("ID nuk mund te jete bosh!");
			case 1:
				return ("Fjalekalimi nuk mund te jete bosh!");
			
			default:
				return ("Gabim i pergjithshem!");
		}
	}
	

	public String getId() {
		return id;
	}
   
	public void setId(String id) {
		this.id = id.trim();
		System.out.println("id e futur eshte " + id);
		if (this.id.isEmpty()) {
			System.out.println("id eshte bosh");
		}
	}

	public String getFjalekalim() {
		return fjalekalim;
	}

	public void setFjalekalim(String fjalekalim) {
		System.out.println("Pswd i futur eshte " + fjalekalim);
		this.fjalekalim = fjalekalim;
		
	}

	public String hyr() {
		System.out.println("u thirr hyr " + id + " i "+ fjalekalim);
		
		if((this.id.equals("")) && (this.fjalekalim.equals(""))) {
			msg1 = gabim(0);
			msg2 = gabim(1);
			System.out.println("ketu");
			return("login");
		}else if(this.id.equals("")){
			msg1 = gabim(0);
			System.out.println("ketrrr");
			return("login");
		
		}else if(this.fjalekalim.equals("")){
					msg2 = gabim(1);
					return("login");
		
		}else{
			System.out.println("lookup");
			perdorues = lookupPerdorues.gjejPerdoruesin(this.id);
			
			System.out.println("nga dbja perd "+ perdorues.getId());

			if(!this.id.equals(perdorues.getId())) {
			System.out.println(perdorues.getId()  + "  id  "+ this.id);
			
			return ("error");
			
			}else if (!perdorues.getFjalekalimi().equals(this.fjalekalim) ) {
				System.out.println(perdorues.getFjalekalimi() +"  psw   " + this.fjalekalim);
				return ("error");
			
			}else {
			
				switch (perdorues.getKategoria()) {
					case 'a':
						return ("autor");
					case 's':
						return ("shqyrtues");

					default:
						return ("error");
				}	
			}
			
			
		}	
			
		
		
		
	}
}
