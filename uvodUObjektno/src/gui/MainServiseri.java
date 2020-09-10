package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import component.Automobili;
import component.Korisnici;
import component.Servisi;
import model.Osoba;
import model.Servis;
import model.Serviser;

public class MainServiseri extends JFrame {
	
	private JMenuBar mainMenu = new JMenuBar();
	private JMenu sevisiMenu = new JMenu("Servisi");
	private JMenuItem zakazaniItem = new JMenuItem("Zakazani");
	private JMenuItem zakaziSevisItem = new JMenuItem("Zakazi");
	private JMenu automobiliMenu = new JMenu("Automobili");
	
	private Korisnici korisnici;
	private Automobili automobili;
	private Servisi servisi;
	private Serviser prijavljeni;
	
	
	public MainServiseri(Serviser prijavljeni, Korisnici korisnici, Automobili automobili, Servisi servisi) {
		this.korisnici = korisnici;
		this.automobili = automobili;
		this.servisi = servisi;
		this.prijavljeni = prijavljeni;
		setTitle("Prijavljeni korisnik: " + prijavljeni.getKorisnickoIme());
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenu();
		initActions();
	}
	
	private void initMenu() {
		setJMenuBar(mainMenu);
		mainMenu.add(sevisiMenu);
		sevisiMenu.add(zakazaniItem);
		sevisiMenu.add(zakaziSevisItem);
		mainMenu.add(automobiliMenu);
	}
	
	private void initActions() {
		zakazaniItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ServisiZaServisere pa = new ServisiZaServisere(prijavljeni, servisi, korisnici ,automobili);
				pa.setVisible(true);
			}
		});
	
	}

}
