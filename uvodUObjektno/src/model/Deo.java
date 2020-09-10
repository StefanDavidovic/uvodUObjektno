package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

public class Deo {
	
	public String id;
	public MarkaAutomobila marka;
	public ModelAutomobila model;
	public String naziv;
	public double cena;
	public boolean obrisan;
	
	public Deo(String id, MarkaAutomobila marka, ModelAutomobila model, String naziv, double cena,  boolean obrisan) {
		this.id = id;
		this.marka = marka;
		this.model = model;
		this.naziv = naziv;
		this.cena = cena;
		this.obrisan = obrisan;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public MarkaAutomobila getMarka() {
		return marka;
	}

	public void setMarka(MarkaAutomobila marka) {
		this.marka = marka;
	}

	public ModelAutomobila getModel() {
		return model;
	}

	public void setModel(ModelAutomobila model) {
		this.model = model;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}
	
	public boolean isObrisan() {
		return obrisan;
	}
	
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	@Override
	public String toString() {
		return id + "|" + marka + "|" + model + "|" + naziv + "|" + cena + "|" + obrisan;
	}
	
	public static String ucitajIzFajla(String fajl) {
		
		String sadrzaj = "";
		File file = new File(fajl);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String linija;
			while((linija = br.readLine()) != null) {
				sadrzaj += linija + "\n";
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom citanja datoteke" + fajl);
			
		}
		return sadrzaj;
	}

}
