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
	public Automobil marka;
	public Automobil model;
	public String naziv;
	public float cena;
	
	public Deo(String id, Automobil marka, Automobil model, String naziv, float cena) {
		this.id = id;
		this.marka = marka;
		this.model = model;
		this.naziv = naziv;
		this.cena = cena;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Automobil getMarka() {
		return marka;
	}

	public void setMarka(Automobil marka) {
		this.marka = marka;
	}

	public Automobil getModel() {
		return model;
	}

	public void setModel(Automobil model) {
		this.model = model;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}
	
	public String toFile() {
		return id + "|" + marka + "|" + model + "|" + naziv + "|" + cena + "|";
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

		
		
	public static void unesiDeolUFajl() {
		Scanner s = new Scanner(System.in);
		
		String id = UUID.randomUUID().toString();
		System.out.println("Unesite marku: ");
		String marka= s.next();
		System.out.println("Unesite model: ");
		String model = s.next();
		System.out.println("Unesite naziv: ");
		String naziv= s.next();
		System.out.println("Unesite cenu: ");
		Integer cena= s.nextInt();

			
		String stariSadrzaj = ucitajIzFajla("/home/stefan/git/uvodUObjektnoo/uvodUObjektno/src/fajlovi/delovi.txt");
		String sadrzaj =  id + "|" + marka+ "|" + model + "|" +  naziv + "|" + cena+ "|" ;
			
		s.close();
			
		try {
			File upisUFajl = new File("/home/stefan/git/uvodUObjektnoo/uvodUObjektno/src/fajlovi/delovi.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(upisUFajl));
			writer.write(stariSadrzaj + sadrzaj);
			writer.close();
		}catch(IOException e) {
			System.out.println("Greska!");

		}
	}
	
}

class test5 {
	public static void main(String[] args){
//		Deo d = new Deo(111, "BMW", "320d", "Amortizer", 2500);
//		System.out.println(d.toFile());
		Deo.unesiDeolUFajl();
	}
}
