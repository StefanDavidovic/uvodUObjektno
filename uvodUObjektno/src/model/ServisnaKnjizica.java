package model;

import java.util.ArrayList;

public class ServisnaKnjizica {
	
	private String id;
	private Automobil automobili;
	private ArrayList<Servis>  servisi;
	private boolean obrisan;
	

	public ServisnaKnjizica(String id, Automobil automobili, ArrayList<Servis> servisi, boolean obrisan) {
		this.id = id;
		this.automobili = automobili;
		this.servisi = servisi;
		this.obrisan = obrisan;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Automobil getAutomobili() {
		return automobili;
	}


	public void setAutomobili(Automobil automobili) {
		this.automobili = automobili;
	}


	public ArrayList<Servis> getServisi() {
		return servisi;
	}


	public void setServisi(ArrayList<Servis> servisi) {
		this.servisi = servisi;
	}
	
	public boolean isObrisan() {
		return obrisan;
	}
	
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}


	@Override
	public String toString() {
		return "ServisnaKnjizica [id=" + id + ", automobili=" + automobili + ", servisi=" + servisi + "]" + obrisan;
	}

}
