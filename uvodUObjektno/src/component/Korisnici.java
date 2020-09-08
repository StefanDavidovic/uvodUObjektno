package component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Administrator;
import model.Automobil;
import model.Musterija;
import model.Osoba;
import model.Pol;
import model.Serviser;
import model.ServiserSpecijalizacija;

public class Korisnici {

	private ArrayList<Administrator> administratori;
	private ArrayList<Musterija> musterije;
	private ArrayList<Serviser> serviseri;

	public Korisnici() {

		this.administratori = new ArrayList<Administrator>();
		this.musterije = new ArrayList<Musterija>();
		this.serviseri = new ArrayList<Serviser>();

	}

	public ArrayList<Administrator> getAdministratori() {
		return administratori;
	}

	public ArrayList<Musterija> getMusterije() {
		return musterije;
	}

	public ArrayList<Serviser> getServisere() {
		return serviseri;
	}

	public void dodajAdministratora(Administrator administrator) {
		this.administratori.add(administrator);
	}

	public void dodajMusteriju(Musterija musterija) {
		this.musterije.add(musterija);
	}

	public void dodajServisera(Serviser serviser) {
		this.serviseri.add(serviser);
	}
	
	public void obrisiServisera(Serviser serviser) {
		this.serviseri.remove(serviser);
	}
	
	public void obrisiAdmina(Administrator administrator) {
		this.administratori.remove(administrator);
	}
	
	public void obrisiMusteriju(Musterija musterija) {
		this.musterije.remove(musterija);
	}

	public Osoba loginKorisnika(String korisnickoIme, String lozinka) {
		for (Administrator administrator : administratori) {
			if (administrator.getKorisnickoIme().equalsIgnoreCase(korisnickoIme)
					&& administrator.getLozinka().equals(lozinka) && !administrator.isObrisan()) {
				return administrator;
			}
		}
		for (Musterija musterija : musterije) {
			if (musterija.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) && musterija.getLozinka().equals(lozinka)
					&& !musterija.isObrisan()) {
				return musterija;
			}
		}
		for (Serviser serviser : serviseri) {
			if (serviser.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) && serviser.getLozinka().equals(lozinka)
					&& !serviser.isObrisan()) {
				return serviser;
			}
		}
		return null;
	}

	public void ucitajAdministratore() {
		try {
			File administratoriFile = new File(
					"/home/stefan/git/uvodUObjektnoo/uvodUObjektno/src/fajlovi/administratori.txt");
			BufferedReader reader = new BufferedReader(new FileReader(administratoriFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] lineSplit = line.split("\\|");
				String id = lineSplit[0];
				String ime = lineSplit[1];
				String prezime = lineSplit[2];
				String jmbg = lineSplit[3];
				int jmbgInt = Integer.parseInt(jmbg);
				int polInt = Integer.parseInt(lineSplit[4]);
				Pol pol = Pol.values()[polInt];
				String broj = lineSplit[5];
				String korisnickoIme = lineSplit[6];
				String lozinka = lineSplit[7];
				String plata = lineSplit[8];
				double plataInt = Double.parseDouble(plata);
				String obrisanBool = lineSplit[9];
				boolean obrisan = Boolean.parseBoolean(obrisanBool);

				Administrator ad = new Administrator(id, ime, prezime, jmbgInt, pol, broj, korisnickoIme, lozinka,
						plataInt, obrisan);
				administratori.add(ad);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}

	public void ucitajMusterije() {
		try {
			File musterijeFile = new File("/home/stefan/git/uvodUObjektnoo/uvodUObjektno/src/fajlovi/musterije.txt");
			BufferedReader reader = new BufferedReader(new FileReader(musterijeFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] lineSplit = line.split("\\|");
				String id = lineSplit[0];
				String ime = lineSplit[1];
				String prezime = lineSplit[2];
				String jmbg = lineSplit[3];
				int jmbgInt = Integer.parseInt(jmbg);
				int polInt = Integer.parseInt(lineSplit[4]);
				Pol pol = Pol.values()[polInt];
				String broj = lineSplit[5];
				String korisnickoIme = lineSplit[6];
				String lozinka = lineSplit[7];
				String brojBodova = lineSplit[8];
				int brojBodovaInt = Integer.parseInt(brojBodova);
				String obrisanStr = lineSplit[9];
				boolean obrisan = Boolean.parseBoolean(obrisanStr);

				Musterija m = new Musterija(id, ime, prezime, jmbgInt, pol, broj, korisnickoIme, lozinka, brojBodovaInt,
						obrisan);
				musterije.add(m);

			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}

	public void ucitajServisere() {
		try {
			File serviseriFile = new File("/home/stefan/git/uvodUObjektnoo/uvodUObjektno/src/fajlovi/serviseri.txt");
			BufferedReader reader = new BufferedReader(new FileReader(serviseriFile));
			String line;

			while ((line = reader.readLine()) != null) {
				String[] lineSplit = line.split("\\|");

				String id = lineSplit[0];
				String ime = lineSplit[1];
				String prezime = lineSplit[2];
				String jmbg = lineSplit[3];
				int jmbgInt = Integer.parseInt(jmbg);
				int polInt = Integer.parseInt(lineSplit[4]);
				Pol pol = Pol.values()[polInt];
				String broj = lineSplit[5];
				String korisnickoIme = lineSplit[6];
				String lozinka = lineSplit[7];
				String plata = lineSplit[8];
				double plataDouble = Double.parseDouble(plata);
				int specijalizacijaInt = Integer.parseInt(lineSplit[9]);
				ServiserSpecijalizacija specijalizacija = ServiserSpecijalizacija.values()[specijalizacijaInt];
				String obrisanStr = lineSplit[10];
				boolean obrisan = Boolean.parseBoolean(obrisanStr);

				Serviser se = new Serviser(id, ime, prezime, jmbgInt, pol, broj, korisnickoIme, lozinka, plataDouble,
						specijalizacija, obrisan);
				serviseri.add(se);

			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}

	public Musterija nadjiMusterijuPoID(String id) {
		for (Musterija musterija : musterije) {

			if (musterija.getId().equals(id)) {
				return musterija;
			}
		}
		return null;
	}

	public ArrayList<Administrator> sviNeobrisaniAdmini() {
		ArrayList<Administrator> neobrisani = new ArrayList<Administrator>();
		for (Administrator administrator : administratori) {
			if (!administrator.isObrisan()) {
				neobrisani.add(administrator);
			}
		}
		return neobrisani;
	}

	public ArrayList<Musterija> sveNeobrisaneMusterije() {
		ArrayList<Musterija> neobrisani = new ArrayList<Musterija>();
		for (Musterija musterija : musterije) {
			if (!musterija.isObrisan()) {
				neobrisani.add(musterija);
			}
		}
		return neobrisani;
	}

	public ArrayList<Serviser> sviNeobrisaniServiseri() {
		ArrayList<Serviser> neobrisani = new ArrayList<Serviser>();
		for (Serviser serviser : serviseri) {
			if (!serviser.isObrisan()) {
				neobrisani.add(serviser);
			}
		}
		return neobrisani;
	}

	public void snimiAdmine() {
		try {
			File file = new File("src/fajlovi/administratori.txt");
			String sadrzaj = "";
			for (Administrator administrator : administratori) {
				sadrzaj += administrator.getId() + "|" + administrator.getIme() + "|" + administrator.getPrezime() + "|"
						+ administrator.getJmbg() + "|" + administrator.getPol().ordinal() + "|" + administrator.getBroj() + "|"
						+ administrator.getKorisnickoIme() + "|" + administrator.getLozinka() + "|" + administrator.getPlata()
						+ "|" + administrator.isObrisan() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(sadrzaj);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja administratora.");
		}
	}

	public void snimiMusterije() {
		try {
			File file = new File("src/fajlovi/musterije.txt");
			String sadrzaj = "";
			for (Musterija musterija : musterije) {
				sadrzaj += musterija.getId() + "|" + musterija.getIme() + "|" + musterija.getPrezime() + "|"
						+ musterija.getJmbg() + "|" + musterija.getPol().ordinal() + "|" + musterija.getBroj() + "|"
						+ musterija.getKorisnickoIme() + "|" +  musterija.getLozinka() + "|" + musterija.getBrojBodova() + "|" + musterija.isObrisan() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(sadrzaj);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja musterija.");
		}
	}
	
	public void snimiServisere() {
		try {
			File file = new File("src/fajlovi/serviseri.txt");
			String sadrzaj = "";
			for (Serviser serviser : serviseri) {
				sadrzaj += serviser.getId() + "|" + serviser.getIme() + "|" + serviser.getPrezime() + "|"
						+ serviser.getJmbg() + "|" + serviser.getPol().ordinal() + "|" + serviser.getBroj() + "|"
						+ serviser.getKorisnickoIme() + "|" + serviser.getLozinka() + "|" + + serviser.getPlata() + "|" + serviser.getSpecijalizacija().ordinal() + "|" + serviser.isObrisan() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(sadrzaj);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja servisera.");
		}
	}
	
	public Serviser pronadjiServisera(String id) {
		for (Serviser serviser : serviseri) {
				if(serviser.getId().equals(id)) {
					return serviser;
				}
			}
		return null;
	}
	
	public Administrator pronadjiAdmina(String id) {
		for (Administrator administrator : administratori) {
				if(administrator.getId().equals(id)) {
					return administrator;
				}
			}
		return null;
	}
	
	public Musterija pronadjiMusteriju(String id) {
		for (Musterija musterija : musterije) {
				if(musterija.getId().equals(id)) {
					return musterija;
				}
			}
		return null;
	}
	
	public Musterija nadjiMusteriju(String korisnickoIme) {
		for (Musterija musterija : musterije ) {
			if (musterija.getKorisnickoIme().equals(korisnickoIme)) {
				return musterija;
			}
		}
		return null;
	}
	
	public Administrator nadjiAdmina(String korisnickoIme) {
		for (Administrator administrator : administratori ) {
			if (administrator.getKorisnickoIme().equals(korisnickoIme)) {
				return administrator;
			}
		}
		return null;
	}
	
	public Serviser nadjiServisera(String korisnickoIme) {
		for (Serviser serviser : serviseri ) {
			if (serviser.getKorisnickoIme().equals(korisnickoIme)) {
				return serviser;
			}
		}
		return null;
	}

}
