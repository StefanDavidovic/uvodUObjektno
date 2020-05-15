package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Musterija extends Osoba {
	
	public int brojBodova;

	public Musterija(int id, String ime, String prezime, int jmbg, String pol, String broj, String korisnickoIme,
			String lozinka, int brojBodova) {
		super(id, ime, prezime, jmbg, pol, broj, korisnickoIme, lozinka);
		this.brojBodova = brojBodova;
	}

	public int getBrojBodova() {
		return brojBodova;
	}

	public void setBrojBodova(int brojBodova) {
		this.brojBodova = brojBodova;
	}


	public String toString() {
		return "Musterija [id=" + id + "|ime=" + ime + "|prezime=" + prezime + "|jmbg=" + jmbg + "|pol=" + pol 
								+ "|broj=" + broj + "|korisnickoIme=" + korisnickoIme + "|lozinka=" + lozinka + "|brojBodova=" + brojBodova + "]";
		
	}
	
	public String toFile() {
		return id + "|" + ime + "|" + prezime + "|" + jmbg + "|" + pol + "|" + broj + "|" + korisnickoIme + "|" + lozinka + "|" + brojBodova + "|";
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
	
	public static void unesiMusterijulUFajl() {
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
		System.out.println("Unesite broj bodova: ");
		int brojBodova = s.nextInt();
		s.hasNextLine();
		
		String stariSadrzaj = ucitajIzFajla("/home/stefan/git/uvodUObjektno/uvodUObjektno/src/musterije.txt");
		String sadrzaj =  id + "|" + ime + "|" + prezime + "|" +  jmbg + "|" + pol + "|" + broj + "|" + korisnickoIme+ "|" + lozinka + "|" + brojBodova;
		
		s.close();
		
		try {
			File upisUFajl = new File("/home/stefan/git/uvodUObjektno/uvodUObjektno/src/musterije.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(upisUFajl));
			writer.write(stariSadrzaj + sadrzaj);
			writer.close();
		}
		catch(IOException e) {
			System.out.println("Greska!");

		}
	}
	
	public static void ucitajMusterije() {
		ArrayList<Musterija> musterije= new ArrayList<Musterija>();
		try {
			File musterijeFile = new File("/home/stefan/git/uvodUObjektno/uvodUObjektno/musterije.txt");
			BufferedReader reader = new BufferedReader(new FileReader(musterijeFile));
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
				String brojBodova = lineSplit[8];
				int brojBodovaInt = Integer.parseInt(brojBodova);

				Musterija m= new Musterija(idInt,ime, prezime, jmbgInt, pol, broj, korisnickoIme, lozinka, brojBodovaInt);
				musterije.add(m);
				System.out.println(ime + prezime);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}
	
}


class test1 {
	
	public static void main(String[] args){ 
//		Musterija mu = new Musterija(123, "Marko", "Pre", 1234343, "muski", "0342324", "MP", "marko123", 3);
//		System.out.println(mu);
//		System.out.println(mu.toFile());
//		Musterija.unesiMusterijulUFajl();
		Musterija.ucitajMusterije();
		
		}
}
		
