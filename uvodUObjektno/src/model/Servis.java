package model;

import java.util.ArrayList;
import java.util.List;

public class Servis {
	
	public String id;
	public String termin;
	public String opis;
	public ArrayList<Deo> listadelova;
	public String statusServisa;
	
	public Servis(String id, String termin, String opis, ArrayList<Deo> listadelova, String statusServisa) {
		this.id = id;
		this.termin = termin;
		this.opis = opis;
		this.listadelova = listadelova;
		this.statusServisa = statusServisa;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getStatusServisa() {
		return statusServisa;
	}

	public void setStatusServisa(String statusServisa) {
		this.statusServisa = statusServisa;
	}
	
	public String toFile() {
		return id + "|" + termin + "|" + opis + "|" + listadelova + "|" + statusServisa + "|";
	}
}

