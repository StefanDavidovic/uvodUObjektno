package gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import component.Korisnici;
import model.Musterija;
import model.Osoba;
import model.Servis;

public class MainMusterija extends JFrame {
	
	private JMenuBar mainMenu = new JMenuBar();
	private JMenu servisiMenu = new JMenu("Servisi");
	private JMenu automobiliMenu = new JMenu("Automobili");
	
	private Korisnici korisnici;
	private Musterija musterija;
	
	
	public MainMusterija(Korisnici korisnici, Musterija musterija) {
		this.korisnici = korisnici;
		this.musterija = musterija;
		setTitle("Prijavljeni korisnik: " + musterija.getKorisnickoIme());
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenu();
//		initActions();
	}
	
	private void initMenu() {
		setJMenuBar(mainMenu);
		mainMenu.add(servisiMenu);
		mainMenu.add(automobiliMenu);
	}
	
	
}
