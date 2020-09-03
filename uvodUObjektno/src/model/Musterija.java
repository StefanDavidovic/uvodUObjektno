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

public class Musterija extends Osoba {
	
	public int brojBodova;

	public Musterija(String id, String ime, String prezime, int jmbg, String pol, String broj, String korisnickoIme,
			String lozinka, int brojBodova, boolean obrisan) {
		super(id, ime, prezime, jmbg, pol, broj, korisnickoIme, lozinka, obrisan);
		this.brojBodova = brojBodova;
	}

	public int getBrojBodova() {
		return brojBodova;
	}

	public void setBrojBodova(int brojBodova) {
		this.brojBodova = brojBodova;
	}


	public String toString() {
		return "Musterija [id=" + id + "|ime=" + ime + "|prezime=" + prezime + "|jmbg=" + jmbg + "|pol=" + pol 
								+ "|broj=" + broj + "|korisnickoIme=" + korisnickoIme + "|lozinka=" + lozinka + "|brojBodova=" + brojBodova + "]" + obrisan;
		
	}
	
	public String toFile() {
		return id + "|" + ime + "|" + prezime + "|" + jmbg + "|" + pol + "|" + broj + "|" + korisnickoIme + "|" + lozinka + "|" + brojBodova + "|" + obrisan;
	}
	
}