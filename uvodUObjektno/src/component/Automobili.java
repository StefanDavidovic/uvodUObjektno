package component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Administrator;
import model.Automobil;
import model.Gorivo;
import model.MarkaAutomobila;
import model.ModelAutomobila;
import model.Musterija;
import model.Pol;
import model.Serviser;

public class Automobili {
	
	private ArrayList<Automobil> automobili;
	
	public Automobili() {
		
		this.automobili = new ArrayList<Automobil>();
		
	}
	
	public ArrayList<Automobil> getAutomobil() {
		return automobili;
	}
	
	public void obrisiAutomobil(Automobil automobil) {
		this.automobili.remove(automobil);
	}
	
	public void dodajAutomobil(Automobil automobil) {
		this.automobili.add(automobil);
	}
	
	public void ucitajAutomobile(Korisnici korisnici) {
		try {
			File automobiliFile = new File("/home/stefan/git/uvodUObjektnoo/uvodUObjektno/src/fajlovi/automobili.txt");
			BufferedReader reader = new BufferedReader(new FileReader(automobiliFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] lineSplit = line.split("\\|");
				String id = lineSplit[0];
				String vlasnikID = lineSplit[1];
				Musterija vlasnik = korisnici.nadjiMusterijuPoID(vlasnikID);
				int markaInt = Integer.parseInt(lineSplit[2]);
				MarkaAutomobila marka = MarkaAutomobila.values()[markaInt];
				int modelInt = Integer.parseInt(lineSplit[3]);
				ModelAutomobila model = ModelAutomobila.values()[modelInt];
				String godiste = lineSplit[4];
				int godisteInt= Integer.parseInt(godiste);
				String kubikazaMotora = lineSplit[5];
				int kubikazaMotoraInt= Integer.parseInt(kubikazaMotora);
				String snagaMotora = lineSplit[6];
				int snagaMotoraInt= Integer.parseInt(snagaMotora);
				int gorivoInt = Integer.parseInt(lineSplit[7]);
				Gorivo gorivo = Gorivo.values()[gorivoInt];
				String obrisanStr = lineSplit[8];
				boolean obrisan = Boolean.parseBoolean(obrisanStr);

				Automobil a= new Automobil(id,vlasnik, marka, model, godisteInt, kubikazaMotoraInt, snagaMotoraInt, gorivo, obrisan);
				automobili.add(a);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}
	
	
	
	public Automobil pronadjiAutomobil(String id) {
		for (Automobil automobil : automobili) {
				if(automobil.getId().equals(id)) {
					return automobil;
				}
			}
		return null;
	}
	
	
	public ArrayList<Automobil> sviNeobrisaniAutomobili() {
		ArrayList<Automobil> neobrisani = new ArrayList<Automobil>();
		for (Automobil automobil : automobili) {
			if (!automobil.isObrisan()) {
				neobrisani.add(automobil);
			}
		}
		return neobrisani;
	}
	
	public void snimiAutomobile() {
		try {
			File file = new File("src/fajlovi/automobili.txt");
			String sadrzaj = "";
			for (Automobil automobil : automobili) {
				sadrzaj += automobil.getId() + "|" + automobil.getvlasnik() + "|" + automobil.getMarka().ordinal() + "|"
						+ automobil.getModel().ordinal() + "|" + automobil.getGodiste() + "|" + automobil.getKubikazaMotora() + "|"
						+ automobil.getSnagaMotora() + "|" + automobil.getGorivo().ordinal() + "|" + automobil.isObrisan();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(sadrzaj);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja administratora.");
		}
	}

}
