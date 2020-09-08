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
	
	public double plata;
	public ServiserSpecijalizacija specijalizacija;
	
	public Serviser(String id, String ime, String prezime, int jmbg, Pol pol, String broj, String korisnickoIme,
			String lozinka, double plata, ServiserSpecijalizacija specijalizacija, boolean obrisan) {
		super(id, ime, prezime, jmbg, pol, broj, korisnickoIme, lozinka, obrisan);
		this.plata = plata;
		this.specijalizacija = specijalizacija;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public ServiserSpecijalizacija getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(ServiserSpecijalizacija specijalizacija) {
		this.specijalizacija = specijalizacija;
	}
	
	public String toFile() {
		return id + "|" + ime + "|" + prezime + "|" + jmbg + "|" + pol + "|" + broj + "|" + korisnickoIme + "|" + lozinka + "|" + plata + "|" + specijalizacija + "|" + obrisan;
	}
	
}
