package model;

import java.util.ArrayList;
import java.util.List;

public class Servis {
	
	public String id;
	public Automobil automobil;
	public Serviser serviser;
	public String termin;
	public String opis;
	public ArrayList<Deo> listadelova;
	public StatusServisa statusServisa;
	public boolean obrisan;
	
	public Servis(String id, Automobil automobil, Serviser serviser , String termin, String opis, ArrayList<Deo> listadelova, StatusServisa statusServisa, boolean obrisan) {
		this.id = id;
		this.automobil = automobil;
		this.serviser = serviser;
		this.termin = termin;
		this.opis = opis;
		this.listadelova = listadelova;
		this.statusServisa = statusServisa;
		this.obrisan = obrisan;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Automobil getAutomobil() {
		return automobil;
	}

	public void setAutomobil(Automobil automobil) {
		this.automobil = automobil;
	}
	
	public Serviser getServiser() {
		return serviser;
	}

	public void setServiser(Serviser serviser) {
		this.serviser = serviser;
	}

	public String getTermin() {
		return termin;
	}

	public void setTermin(String termin) {
		this.termin = termin;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public ArrayList<Deo> getListadelova() {
		return listadelova;
	}

	public void setListadelova(ArrayList<Deo> listadelova) {
		this.listadelova = listadelova;
	}

	public StatusServisa getStatusServisa() {
		return statusServisa;
	}

	public void setStatusServisa(StatusServisa statusServisa) {
		this.statusServisa = statusServisa;
	}
	
	public boolean isObrisan() {
		return obrisan;
	}
	
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	public String toFile() {
		return id + "|" + termin + "|" + opis + "|" + "|" + statusServisa + "|" + obrisan;
	}
}

