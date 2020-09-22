package com.ikubinfo.konferenca.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.log4j.Logger;
import org.primefaces.component.log.Log;

import com.ikubinfo.konferenca.dao.impl.UserDaoImpl;

public class HashSaltedPassword {
	
	private static Logger log = Logger.getLogger(HashSaltedPassword.class);

	private static String algorithm = "SHA-512";
	
	public String hashGenerate(String plainPassword, byte[] salt){
		try {
			MessageDigest dig;
			dig = MessageDigest.getInstance(algorithm);
			dig.reset();
			log.info("Hashing decrypt Trying to print the salt " + salt);
			dig.update(salt);
			byte[] hash = dig.digest(plainPassword.getBytes());
			return bytesToStringHex(hash);
		} catch (NoSuchAlgorithmException e) {
			log.error("Couldn't generate hashed password!");
			e.printStackTrace();
			return null;
		}
	}

	
	 private final static char[] hexValuesArray = "0123456789ABCDEF".toCharArray();
	 
	 public static String bytesToStringHex(byte[] bytes) { 
		 char[] hexChars = new char[bytes.length * 2];
		 for(int i = 0; i < bytes.length; i++) {
			 int var = bytes[i] & 0xFF;
			 hexChars[i*2]= hexValuesArray[var >>> 4];
			 hexChars[i*2 +1] = hexValuesArray[var & 0x0F];
		 }
		 return new String(hexChars); 
	 }
	 
	
	public byte[] createSalt() {
		byte[] bytes = new byte[20];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(bytes);
		return bytes;
	}
	

}
