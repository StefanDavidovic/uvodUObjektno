package model;

import java.util.Random;

public class ID {
	
	private final String letters =  "abcdefghijklmnopqrstuvwxyz";
	
	private final char[] alphanumeric = (letters + letters.toUpperCase() + "0123456789").toCharArray();
	 
	public String generateRandomID(int duzina)  {
		
		StringBuilder rezultat = new StringBuilder();
		
		for(int i=0; i<duzina; i++) {
			rezultat.append(alphanumeric[new Random().nextInt(alphanumeric.length)]);
		}
		return rezultat.toString();
	}

	public static ID parse(ID ide) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ID parse(String ide) {
		// TODO Auto-generated method stub
		return null;
	}

}
