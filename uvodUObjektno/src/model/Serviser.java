package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Serviser extends Osoba {
	
	public int plata;
	public String specijalizacija;
	
	public Serviser(String id, String ime, String prezime, int jmbg, String pol, String broj, String korisnickoIme,
			String lozinka, int plata, String specijalizacija, boolean obrisan) {
		super(id, ime, prezime, jmbg, pol, broj, korisnickoIme, lozinka, obrisan);
		this.plata = plata;
		this.specijalizacija = specijalizacija;
	}

	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}

	public String getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(String specijalizacija) {
		this.specijalizacija = specijalizacija;
	}
	
	public String toFile() {
		return id + "|" + ime + "|" + prezime + "|" + jmbg + "|" + pol + "|" + broj + "|" + korisnickoIme + "|" + lozinka + "|" + plata + "|" + specijalizacija + "|" + obrisan;
	}
	
}
