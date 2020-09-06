package model;

import java.util.ArrayList;

public class ServisnaKnjizica {
	
	private String id;
	private ArrayList<Automobil> automobili;
	private ArrayList<Servis>  servisi;
	

	public ServisnaKnjizica(String id, ArrayList<Automobil> automobili, ArrayList<Servis> servisi) {
		this.id = id;
		this.automobili = automobili;
		this.servisi = servisi;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public ArrayList<Automobil> getAutomobili() {
		return automobili;
	}


	public void setAutomobili(ArrayList<Automobil> automobili) {
		this.automobili = automobili;
	}


	public ArrayList<Servis> getServisi() {
		return servisi;
	}


	public void setServisi(ArrayList<Servis> servisi) {
		this.servisi = servisi;
	}


	@Override
	public String toString() {
		return "ServisnaKnjizica [id=" + id + ", automobili=" + automobili + ", servisi=" + servisi + "]";
	}

}
