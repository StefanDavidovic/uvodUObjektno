package gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.Osoba;
import model.Servis;

public class MainServiseri extends JFrame {
	
	private JMenuBar mainMenu = new JMenuBar();
	private JMenu sevisiMenu = new JMenu("Servisi");
	private JMenuItem zakazaniItem = new JMenuItem("Zakazani");
	private JMenuItem zakaziSevisItem = new JMenuItem("Zakazi");
	private JMenu automobiliMenu = new JMenu("Automobili");
	
	private Osoba prijavljeni;
	private Servis servis;
	
	
	public MainServiseri(Osoba prijavljeni, Servis servis) {
		this.prijavljeni = prijavljeni;
		this.servis = servis;
		setTitle("Prijavljeni korisnik: " + prijavljeni.getKorisnickoIme());
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenu();
//		initActions();
	}
	
	private void initMenu() {
		setJMenuBar(mainMenu);
		mainMenu.add(sevisiMenu);
		sevisiMenu.add(zakazaniItem);
		sevisiMenu.add(zakaziSevisItem);
		mainMenu.add(automobiliMenu);
	}

}
