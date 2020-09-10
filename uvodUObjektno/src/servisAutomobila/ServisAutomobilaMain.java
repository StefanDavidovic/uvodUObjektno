package servisAutomobila;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import component.Automobili;
import component.Delovi;
import component.Korisnici;
import gui.LogInProzor;
import model.Administrator;
import model.Automobil;
import model.Musterija;

public class ServisAutomobilaMain {

	public static void main(String[] args) {
		
		
		Korisnici korisnici = new Korisnici();
		Automobili automobili = new Automobili();
		Delovi delovi = new Delovi();
		korisnici.ucitajAdministratore();
		korisnici.ucitajMusterije();
		korisnici.ucitajServisere();
		automobili.ucitajAutomobile(korisnici);
		delovi.ucitajDelove();
		
		
		LogInProzor lp = new LogInProzor(korisnici, automobili, delovi);
		lp.setVisible(true);
		
		
		
	}
		
}

