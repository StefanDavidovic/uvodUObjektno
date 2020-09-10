package component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Automobil;
import model.Deo;
import model.MarkaAutomobila;
import model.ModelAutomobila;
import model.Musterija;
import model.Servis;
import model.Serviser;
import model.ServisnaKnjizica;
import model.StatusServisa;

public class Servisi {
	
	private ArrayList<Servis> servisi;
	private ArrayList<ServisnaKnjizica> servisneKnjizice;

	public Servisi() {
		this.servisi = new ArrayList<Servis>();
		this.servisneKnjizice = new ArrayList<ServisnaKnjizica>();
	}

	public ArrayList<ServisnaKnjizica> getServisnaKnjizica() {
		return servisneKnjizice;
	}
	
	public ArrayList<Servis> getServis() {
		return servisi;
	}

	public void obrisiServisnuKnjizicu(ServisnaKnjizica servisnaKnjizica) {
		this.servisneKnjizice.remove(servisnaKnjizica);
	}
	
	public void obrisiServis(Servis servis) {
		this.servisi.remove(servis);
	}

	public void dodajServis(Servis servis) {
		this.servisi.add(servis);
	}
	
	public void dodajServisnuKnjizicu(ServisnaKnjizica servisnaKnjizica) {
		this.servisneKnjizice.add(servisnaKnjizica);
	}

	public void ucitajServise(Automobili automobili, Korisnici korisnici) {
		try {
			File servisiFile = new File("/home/stefan/git/uvodUObjektnoo/uvodUObjektno/src/fajlovi/servisi.txt");
			BufferedReader reader = new BufferedReader(new FileReader(servisiFile));
			String line;
			while ((line = reader.readLine()) != null) {

				String[] lineSplit = line.split("\\|");

				String id = lineSplit[0];
				
				String automobilID = lineSplit[1];
				Automobil automobil = automobili.pronadjiAutomobil(automobilID);
				
				String serviserID = lineSplit[2];
				Serviser serviser = korisnici.pronadjiServisera(serviserID);
				
				String termin = lineSplit[3];
				
				String opis = lineSplit[4];
				
				int statusInt = Integer.parseInt(lineSplit[5]);
				StatusServisa status= StatusServisa.values()[statusInt];
				
				ArrayList<Deo> listaDelova = new ArrayList<Deo>();

				String obrisanBool = lineSplit[6];
				boolean obrisan = Boolean.parseBoolean(obrisanBool);

				Servis servis= new Servis(id, automobil, serviser, termin, opis, listaDelova, status, obrisan);
				servisi.add(servis);

			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}
	
	
	public void ucitajServisneKnjizice(Automobili automobili) {
		try {
			File servisiFile = new File("/home/stefan/git/uvodUObjektnoo/uvodUObjektno/src/fajlovi/servisneKnjizice.txt");
			BufferedReader reader = new BufferedReader(new FileReader(servisiFile));
			String line;
			while ((line = reader.readLine()) != null) {

				String[] lineSplit = line.split("\\|");

				String id = lineSplit[0];
				
				String automobilID = lineSplit[1];
				Automobil automobil = automobili.pronadjiAutomobil(automobilID);
			
				ArrayList<Servis> listaServisa = new ArrayList<Servis>();

				String obrisanBool = lineSplit[3];
				boolean obrisan = Boolean.parseBoolean(obrisanBool);

				ServisnaKnjizica servisnaKnjizica= new ServisnaKnjizica(id, automobil, listaServisa , obrisan);
				servisneKnjizice.add(servisnaKnjizica);

			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}

	public Servis pronadjiServis(String id) {
		for (Servis servis : servisi) {
			if (servis.getId().equals(id)) {
				return servis;
			}
		}
		return null;
	}
	
	public ServisnaKnjizica pronadjiServisnuKnjizicu(String id) {
		for (ServisnaKnjizica servisnaKnjizica : servisneKnjizice) {
			if (servisnaKnjizica.getId().equals(id)) {
				return servisnaKnjizica;
			}
		}
		return null;
	}
	
	
	
	public ArrayList<Servis> sviNeobrisaniServisi() {
		ArrayList<Servis> neobrisani = new ArrayList<Servis>();
		for (Servis servis : servisi) {
			if (!servis.isObrisan()) {
				neobrisani.add(servis);
			}
		}
		return neobrisani;
	}
	
	public ArrayList<ServisnaKnjizica> sveNeobrisaneServisneKnjizice() {
		ArrayList<ServisnaKnjizica> neobrisani = new ArrayList<ServisnaKnjizica>();
		for (ServisnaKnjizica servisnaKnjizica : servisneKnjizice) {
			if (!servisnaKnjizica.isObrisan()) {
				neobrisani.add(servisnaKnjizica);
			}
		}
		return neobrisani;
	}
	
	public ArrayList<Servis> sviServisiZaAutomobil(String id) {
		ArrayList<Servis> sviServisi = new ArrayList<Servis>();
		for (Servis servis: servisi) {
			if (servis.getAutomobil().getId().equals(id)) {
				sviServisi.add(servis);
			}
		}
		return sviServisi;
	}

	public void snimiServise() {
		try {
			File file = new File("src/fajlovi/servisi.txt");
			String sadrzaj = "";
			for (Servis servis : servisi) {
				sadrzaj += servis.getId() + "|" + servis.getAutomobil().getId() + "|" + servis.getServiser().getId() + "|" + servis.getTermin() + "|" + servis.getOpis() + "|"
						+ servis.getStatusServisa().ordinal() + "|" +  servis.isObrisan() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(sadrzaj);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja servisa.");
		}
	}
	
	public void snimiServisneKnjizice() {
		try {
			File file = new File("src/fajlovi/servisneKnjizice.txt");
			String sadrzaj = "";
			for (ServisnaKnjizica servisnaKnjizica : servisneKnjizice) {
				sadrzaj += servisnaKnjizica.getId() + "|" + servisnaKnjizica.getAutomobili().getId() + "|" +  servisnaKnjizica.isObrisan() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(sadrzaj);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja servisne knjizice.");
		}
	}

}
