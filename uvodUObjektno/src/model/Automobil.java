package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Automobil {
	
	public int id;
	public String Vlasnik;
	public String Marka;
	public String Model;
	public int Godiste;
	public int KubikazaMotora;
	public int SnagaMotora;
	public String Gorivo;
	public Automobil(int id, String vlasnik, String marka, String model, int godiste, int kubikazaMotora, int snagaMotora,
			String gorivo) {
		this.id = id;
		this.Vlasnik = vlasnik;
		this.Marka = marka;
		this.Model = model;
		this.Godiste = godiste;
		this.KubikazaMotora = kubikazaMotora;
		this.SnagaMotora = snagaMotora;
		this.Gorivo = gorivo;
		
	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getvlasnik() {
		return Vlasnik;
	}
	public void setvlasnik(String vlasnik) {
		this.Vlasnik = vlasnik;
	}
	public String getMarka() {
		return Marka;
	}
	public void setMarka(String marka) {
		Marka = marka;
	}
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}
	public int getGodiste() {
		return Godiste;
	}
	public void setGodiste(int godiste) {
		Godiste = godiste;
	}
	public int getKubikazaMotora() {
		return KubikazaMotora;
	}
	public void setKubikazaMotora(int kubikazaMotora) {
		KubikazaMotora = kubikazaMotora;
	}
	public int getSnagaMotora() {
		return SnagaMotora;
	}
	public void setSnagaMotora(int snagaMotora) {
		SnagaMotora = snagaMotora;
	}
	public String getGorivo() {
		return Gorivo;
	}
	public void setGorivo(String gorivo) {
		this.Gorivo = gorivo;
	}
	
	public String toFile() {
		return id + "|" + Vlasnik + "|" + Marka + "|" + Model + "|" + Godiste + "|" + KubikazaMotora + "|" + SnagaMotora + "|" + Gorivo + "|";
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
	
	public static void unesiNoviAutomobilUFajl() {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Unesite id vozila: ");
		int id = s.nextInt();
		System.out.println("Unesite vlasnika vozila: ");
		String vlasnik = s.next();
		System.out.println("Unesite marku vozila: ");
		String marka = s.next();
		System.out.println("Unesite model vozila: ");
		String model = s.next();
		System.out.println("Unesite godiste vozila: ");
		int godiste= s.nextInt();
		System.out.println("Unesite kubikazu vozila: ");
		int kubikazaMotora= s.nextInt();
		System.out.println("Unesite snagu vozila: ");
		int snagaMotora= s.nextInt();
		System.out.println("Unesite gorivo vozila: ");
		String gorivo= s.next();
		s.hasNextLine();
		
		String stariSadrzaj = ucitajIzFajla("automobili.txt");
		String sadrzaj =  id + "|" + vlasnik + "|" + marka + "|" +  model + "|" + godiste + "|" + kubikazaMotora + "|" + snagaMotora + "|" + gorivo;

		s.close();
		
		try {
			File upisUFajl = new File("automobili.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(upisUFajl));
			writer.write(stariSadrzaj + sadrzaj);
			writer.close();
		}
		catch(IOException e) {
			System.out.println("Greska!");

		}
	}

	
	public static void ucitajAutomobile() {
		ArrayList<Automobil> automobili = new ArrayList<Automobil>();
		try {
			File automobiliFile = new File("/home/stefan/git/uvodUObjektno/uvodUObjektno/automobili.txt");
			BufferedReader reader = new BufferedReader(new FileReader(automobiliFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] lineSplit = line.split("\\|");
				String id = lineSplit[0];
				int idInt = Integer.parseInt(id);
				String vlasnik = lineSplit[1];
				String marka = lineSplit[2];
				String model= lineSplit[3];
				String godiste = lineSplit[4];
				int godisteInt= Integer.parseInt(godiste);
				String kubikazaMotora = lineSplit[5];
				int kubikazaMotoraInt= Integer.parseInt(kubikazaMotora);
				String snagaMotora = lineSplit[6];
				int snagaMotoraInt= Integer.parseInt(snagaMotora);
				String gorivo = lineSplit[7];

				Automobil a= new Automobil(idInt,vlasnik, marka, model, godisteInt, kubikazaMotoraInt, snagaMotoraInt, gorivo);
				automobili.add(a);
				System.out.println(idInt + " " + vlasnik);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}
		
}



class test4 {
	public static void main(String[] args){
//		Automobil auto = new Automobil(134,"Marko", "BMW", "320d", 2003, 1400, 99, "Benzin" );
		Automobil.unesiNoviAutomobilUFajl();
//		Automobil.ucitajAutomobile();
	}
}



