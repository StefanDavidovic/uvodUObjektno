package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Serviser extends Osoba {
	
	public int plata;
	public String specijalizacija;
	
	public Serviser(int id, String ime, String prezime, int jmbg, String pol, String broj, String korisnickoIme,
			String lozinka, int plata, String specijalizacija) {
		super(id, ime, prezime, jmbg, pol, broj, korisnickoIme, lozinka);
		this.plata = plata;
		this.specijalizacija = specijalizacija;
	}

	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}

	public String getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(String specijalizacija) {
		this.specijalizacija = specijalizacija;
	}
	
	public String toFile() {
		return id + "|" + ime + "|" + prezime + "|" + jmbg + "|" + pol + "|" + broj + "|" + korisnickoIme + "|" + lozinka + "|" + plata + "|" + specijalizacija + "|";
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
	
	public static void unesiServiseralUFajl() {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Unesite id : ");
		int id = s.nextInt();
		System.out.println("Unesite ime: ");
		String ime= s.next();
		System.out.println("Unesite prezime: ");
		String prezime = s.next();
		System.out.println("Unesite jmbg: ");
		String jmbg= s.next();
		System.out.println("Unesite pol: ");
		String pol= s.next();
		System.out.println("Unesite broj: ");
		String broj = s.next();
		System.out.println("Unesite korsnicko ime: ");
		String korisnickoIme= s.next();
		System.out.println("Unesite lozinku: ");
		String lozinka= s.next();
		System.out.println("Unesite platu: ");
		int plata = s.nextInt();
		System.out.println("Unesite specijalizaciju: ");
		String specijalizacija = s.next();
		s.hasNextLine();
		
		String stariSadrzaj = ucitajIzFajla("/home/stefan/git/uvodUObjektno/uvodUObjektno/serviseri.txt");
		String sadrzaj =  id + "|" + ime + "|" + prezime + "|" +  jmbg + "|" + pol + "|" + broj + "|" + korisnickoIme+ "|" + lozinka + "|" + plata + "|" + specijalizacija;
		
		s.close();
		
		try {
			File upisUFajl = new File("/home/stefan/git/uvodUObjektno/uvodUObjektno/serviseri.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(upisUFajl));
			writer.write(stariSadrzaj + sadrzaj);
			writer.close();
		}
		catch(IOException e) {
			System.out.println("Greska!");

		}
	}
	
	public static void ucitajServisere() {
		ArrayList<Serviser> serviseri= new ArrayList<Serviser>();
		try {
			File serviseriFile = new File("/home/stefan/git/uvodUObjektno/uvodUObjektno/serviseri.txt");
			BufferedReader reader = new BufferedReader(new FileReader(serviseriFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] lineSplit = line.split("\\|");
				String id = lineSplit[0];
				int idInt = Integer.parseInt(id);
				String ime = lineSplit[1];
				String prezime = lineSplit[2];
				String jmbg= lineSplit[3];
				int jmbgInt = Integer.parseInt(jmbg);
				String pol = lineSplit[4];
				String broj= lineSplit[5];
				String korisnickoIme= lineSplit[6];
				String lozinka= lineSplit[7];
				String plata = lineSplit[8];
				int plataInt = Integer.parseInt(plata);
				String specijalizacija = lineSplit[9];

				Serviser se= new Serviser(idInt,ime, prezime, jmbgInt, pol, broj, korisnickoIme, lozinka, plataInt, specijalizacija );
				serviseri.add(se);
				System.out.println(ime + prezime);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}
	
	

}

class test3 {
	public static void main(String[] args){
//		Serviser s = new Serviser(123, "Mark", "Bla", 12345, "Musko", "78534", "mar123", "mar123", 12000, "automehanicar");
//		System.out.println(s.toFile());
//		Serviser.unesiServiseralUFajl();
		Serviser.ucitajServisere();
	}
}
	


