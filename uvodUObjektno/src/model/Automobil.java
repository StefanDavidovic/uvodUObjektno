package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import component.Korisnici;

public class Automobil {
	
	public String id;
	public Musterija Vlasnik;
	public MarkaAutomobila Marka;
	public ModelAutomobila Model;
	public int Godiste;
	public int KubikazaMotora;
	public int SnagaMotora;
	public Gorivo Gorivo;
	public boolean obrisan;
	public Automobil(String id, Musterija vlasnik, MarkaAutomobila marka, ModelAutomobila model, int godiste, int kubikazaMotora, int snagaMotora,
			Gorivo gorivo, boolean obrisan) {
		this.id = id;
		this.Vlasnik = vlasnik;
		this.Marka = marka;
		this.Model = model;
		this.Godiste = godiste;
		this.KubikazaMotora = kubikazaMotora;
		this.SnagaMotora = snagaMotora;
		this.Gorivo = gorivo;
		this.obrisan = obrisan;
		
	
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Musterija getvlasnik() {
		return Vlasnik;
	}
	public void setvlasnik(Musterija vlasnik) {
		this.Vlasnik = vlasnik;
	}
	public MarkaAutomobila getMarka() {
		return Marka;
	}
	public void setMarka(MarkaAutomobila marka) {
		Marka = marka;
	}
	public ModelAutomobila getModel() {
		return Model;
	}
	public void setModel(ModelAutomobila model) {
		Model = model;
	}
	public int getGodiste() {
		return Godiste;
	}
	public void setGodiste(int godiste) {
		Godiste = godiste;
	}
	public int getKubikazaMotora() {
		return KubikazaMotora;
	}
	public void setKubikazaMotora(int kubikazaMotora) {
		KubikazaMotora = kubikazaMotora;
	}
	public int getSnagaMotora() {
		return SnagaMotora;
	}
	public void setSnagaMotora(int snagaMotora) {
		SnagaMotora = snagaMotora;
	}
	public Gorivo getGorivo() {
		return Gorivo;
	}
	public void setGorivo(Gorivo gorivo) {
		this.Gorivo = gorivo;
	}
	
	public boolean isObrisan() {
		return obrisan;
	}
	
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	public String toFile() {
		return id + "|" + Vlasnik + "|" + Marka + "|" + Model + "|" + Godiste + "|" + KubikazaMotora + "|" + SnagaMotora + "|" + Gorivo + "|" + obrisan;
	}	
	
}

