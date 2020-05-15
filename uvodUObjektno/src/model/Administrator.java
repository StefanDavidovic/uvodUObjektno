package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Administrator extends Osoba{
	
	public int plata;

	public Administrator(int id, String ime, String prezime, int jmbg, String pol, String broj, String korisnickoIme,
			String lozinka, int plata) {
		super(id, ime, prezime, jmbg, pol, broj, korisnickoIme, lozinka);
		this.plata = plata;
	}

	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}
	

	public String toFile() {
		return id + "|" + ime + "|" + prezime + "|" + jmbg + "|" + pol + "|" + broj + "|" + korisnickoIme + "|" + lozinka + "|" + plata + "|";
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
	
	public static void unesiAdministratorailUFajl() {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Unesite id administratora: ");
		int id = s.nextInt();
		System.out.println("Unesite ime: ");
		String ime= s.next();
		System.out.println("Unesite prezime: ");
		String prezime = s.next();
		System.out.println("Unesite jmbg: ");
		int jmbg = s.nextInt();
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
		s.hasNextLine();
		
		String stariSadrzaj = ucitajIzFajla("administratori.txt");
		String sadrzaj =  id + "|" + ime + "|" + prezime + "|" +  jmbg + "|" + pol + "|" + broj + "|" + korisnickoIme+ "|" + lozinka + "|" + plata;

		s.close();
		
		try {
			File upisUFajl = new File("administratori.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(upisUFajl));
			writer.write(stariSadrzaj + sadrzaj);
			writer.close();
		}
		catch(IOException e) {
			System.out.println("Greska!");

		}
	}
	
	public static void ucitajAdministratore() {
		ArrayList<Administrator> administratori= new ArrayList<Administrator>();
		try {
			File administratoriFile = new File("/home/stefan/git/uvodUObjektno/uvodUObjektno/administratori.txt");
			BufferedReader reader = new BufferedReader(new FileReader(administratoriFile));
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

				Administrator ad= new Administrator(idInt,ime, prezime, jmbgInt, pol, broj, korisnickoIme, lozinka, plataInt);
				administratori.add(ad);
				System.out.println(ime + prezime);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}
	
	
}

class test2 {
	
	public static void main(String[] args){ 
//		Administrator ma = new Administrator(124, "Marko", "Pre", 1234343, "muski", "0342324", "MP", "marko123", 2600);
//		System.out.println(ma.toFile());
		Administrator.unesiAdministratorailUFajl();
		Administrator.ucitajAdministratore();
	}
}