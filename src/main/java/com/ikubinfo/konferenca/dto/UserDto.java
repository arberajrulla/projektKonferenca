package com.ikubinfo.konferenca.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UserDto {

	@NotEmpty(message = "Username nuk mund te jete bosh!")
	@Size(min=6, max=40, message = "Username duhet te kete mbi 6 karaktere!")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username duhet te permbaje vetem shkronja dhe numra!")
	private String username;
	
	@NotNull(message = "Emri nuk duhet te jete bosh")
	@Size(message = "Emri duhet te kete nga 2 deri 40 karaktere!", min= 2, max = 40)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Emri duhet te permbaje vetem shkronja!")
	private String emri;
	
	@NotNull(message = "Mbiemri nuk duhet te jete bosh")
	@Size(message = "Mbiemri duhet te kete nga 2 deri 40 karaktere!", min= 2, max = 40)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Mbiemri duhet te permbaje vetem shkronja!")	
	private String mbiemri;
	
	@NotNull(message = "E-mail nuk duhet te jete bosh")
	@Size(message = "E-mail duhet te kete nga 2 deri 40 karaktere!", min= 2, max = 40)
	@Email(message = "Formati i e-mail nuk eshte i sakte!")
	private String email;
	
	@NotEmpty(message = "Fjalekalimi nuk mund te jete bosh!")
	@Size(min=6, max=20, message = "Fjalekalimi duhet te kete nga 6 deri 20 karaktere!")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[@!#$%^&+=]).{6,}$", message = "Fjalekalimi duhet te permbaje te pakten nje numer dhe nje karakter special (@,!,#,$,%,^,&,+,=)!")
	private String password;
	
	@NotEmpty(message = "Zgjidhni te pakten nje kategori!")
	private String kategoria;
	
	@NotNull(message = "Numri i celularit nuk mund te jete bosh")
	@Size(message = "Numri i celularit duhet te kete nga 8 deri 40 karaktere!", min= 8, max = 40)
	@Pattern(regexp = "^[0-9+]*$", message = "Numri i celularit mund te permbaje vetem numra dhe simbolin \"+\"!")
	private String nrcel;
	
	
	private byte[] salt;
	
	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmri() {
		return emri;
	}

	public void setEmri(String emri) {
		this.emri = emri;
	}

	public String getMbiemri() {
		return mbiemri;
	}

	public void setMbiemri(String mbiemri) {
		this.mbiemri = mbiemri;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKategoria() {
		return kategoria;
	}

	public void setKategoria(String kategoria) {
		this.kategoria = kategoria;
	}

	public String getNrcel() {
		return nrcel;
	}

	public void setNrcel(String nrcel) {
		this.nrcel = nrcel;
	}
}
