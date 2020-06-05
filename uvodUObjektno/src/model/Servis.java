package model;

import java.util.ArrayList;
import java.util.List;

public class Servis {
	
	public ID id;
	public String termin;
	public String opis;
	public List<String> listadelova;
	public String statusServisa;
	
	public Servis(ID id, String termin, String opis, List<String> listadelova, String statusServisa) {
		this.id = id;
		this.termin = termin;
		this.opis = opis;
		this.listadelova = listadelova;
		this.statusServisa = statusServisa;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
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

	public List<String> getListadelova() {
		return listadelova;
	}

	public void setListadelova(List<String> listadelova) {
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

class test6 {
	public static void main(String[] args){
		ArrayList<String> listadela = new ArrayList<String>();
		listadela.add("Branik");
		listadela.add("Svecice");
		Servis ser =new Servis(122, "12/12/2012","Zamena", listadela , "U toku" );
		System.out.println(ser.toFile());
	}
}