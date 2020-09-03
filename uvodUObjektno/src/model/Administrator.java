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


public class Administrator extends Osoba{
	
	public int plata;
	public static ArrayList<Administrator> administratori;
	

	public Administrator(String id, String ime, String prezime, int jmbg, String pol, String broj, String korisnickoIme,
			String lozinka, int plata, boolean obrisan ) {
		super(id, ime, prezime, jmbg, pol, broj, korisnickoIme, lozinka, obrisan);
		this.plata = plata;
		Administrator.administratori = new ArrayList<Administrator>();
	}
	
	public ArrayList<Administrator> getAdministratori() {
		return administratori;
	}

	public void dodajAdministratora(Administrator administrator) {
		Administrator.administratori.add(administrator);
	}
		
	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}
	
	@Override
	public String toString() {
		return id + "|" + ime + "|" + prezime + "|" + jmbg + "|" + pol + "|" + broj + "|" + korisnickoIme + "|" + lozinka + "|" + plata + "|" + obrisan;
	}
	
}