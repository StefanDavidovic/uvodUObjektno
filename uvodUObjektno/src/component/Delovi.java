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
import model.Deo;
import model.Gorivo;
import model.MarkaAutomobila;
import model.ModelAutomobila;
import model.Musterija;

public class Delovi {

	private ArrayList<Deo> delovi;

	public Delovi() {
		this.delovi = new ArrayList<Deo>();
	}

	public ArrayList<Deo> getDeo() {
		return delovi;
	}

	public void obrisiDeo(Deo deo) {
		this.delovi.remove(deo);
	}

	public void dodajDeo(Deo deo) {
		this.delovi.add(deo);
	}

	public void ucitajDelove() {
		try {
			File deloviFile = new File("/home/stefan/git/uvodUObjektnoo/uvodUObjektno/src/fajlovi/delovi.txt");
			BufferedReader reader = new BufferedReader(new FileReader(deloviFile));
			String line;
			while ((line = reader.readLine()) != null) {

				String[] lineSplit = line.split("\\|");

				String id = lineSplit[0];

				int markaInt = Integer.parseInt(lineSplit[1]);
				MarkaAutomobila marka = MarkaAutomobila.values()[markaInt];

				int modelInt = Integer.parseInt(lineSplit[2]);
				ModelAutomobila model = ModelAutomobila.values()[modelInt];

				String naziv = lineSplit[3];

				String cenaDouble = lineSplit[4];
				double cena = Double.parseDouble(cenaDouble);

				String obrisanBool = lineSplit[5];
				boolean obrisan = Boolean.parseBoolean(obrisanBool);

				Deo deo = new Deo(id, marka, model, naziv, cena, obrisan);
				delovi.add(deo);

			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}

	public Deo pronadjiDeo(String id) {
		for (Deo deo : delovi) {
			if (deo.getId().equals(id)) {
				return deo;
			}
		}
		return null;
	}

	public ArrayList<Deo> sviNeobrisaniDelovi() {
		ArrayList<Deo> neobrisani = new ArrayList<Deo>();
		for (Deo deo : delovi) {
			if (!deo.isObrisan()) {
				neobrisani.add(deo);
			}
		}
		return neobrisani;
	}

	public void snimiDelove() {
		try {
			File file = new File("src/fajlovi/delovi.txt");
			String sadrzaj = "";
			for (Deo deo : delovi) {
				sadrzaj += deo.getId() + "|" + deo.getMarka().ordinal() + "|" + deo.getModel().ordinal() + "|"
						+ deo.getNaziv() + "|" + deo.getCena() + "|" + deo.isObrisan() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(sadrzaj);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja delova.");
		}
	}

}
