package component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Administrator;
import model.Automobil;
import model.Musterija;

public class Automobili {
	
	private ArrayList<Automobil> automobili;
	
	public Automobili() {
		
		this.automobili = new ArrayList<Automobil>();
		
	}
	
	public ArrayList<Automobil> getAutomobil() {
		return automobili;
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
 				String marka = lineSplit[2];
				String model= lineSplit[3];
				String godiste = lineSplit[4];
				int godisteInt= Integer.parseInt(godiste);
				String kubikazaMotora = lineSplit[5];
				int kubikazaMotoraInt= Integer.parseInt(kubikazaMotora);
				String snagaMotora = lineSplit[6];
				int snagaMotoraInt= Integer.parseInt(snagaMotora);
				String gorivo = lineSplit[7];

				Automobil a= new Automobil(id,vlasnik, marka, model, godisteInt, kubikazaMotoraInt, snagaMotoraInt, gorivo);
				automobili.add(a);
				System.out.println(id + " " + vlasnik);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}

}
