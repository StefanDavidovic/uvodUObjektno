package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import component.Automobili;
import component.Delovi;
import component.Korisnici;
import component.Servisi;
import model.Administrator;
import model.Automobil;
import model.Musterija;
import model.Osoba;
import model.Servis;

public class MainAdministratori extends JFrame {
	
	private JMenuBar mainMenu = new JMenuBar();
	private JMenu sevisiMenu = new JMenu("Servisi");
	private JMenuItem servisneKnjiziceItem = new JMenuItem("ServisneKnjizice");
	private JMenuItem zakaziSevisItem = new JMenuItem("Servisi");
	private JMenu korisniciMenu = new JMenu("Korisnici");
	private JMenuItem adminiItem = new JMenuItem("Administratori");
	private JMenuItem serviseriItem = new JMenuItem("Serviseri");
	private JMenuItem musterijeItem = new JMenuItem("Musterije");
	private JMenu automobiliMenu = new JMenu("Automobili");
	private JMenuItem automobiliItem = new JMenuItem("Svi Automobili");
	private JMenu deloviMenu = new JMenu("Delovi");
	private JMenuItem deloviItem = new JMenuItem("Svi Delovi");
	
	private Korisnici korisnici;
	private Automobili automobili;
	private Delovi delovi;
	private Servisi servisi;

	
	public MainAdministratori(Korisnici korisnici, Automobili automobili, Delovi delovi, Servisi servisi) {
		this.korisnici = korisnici;
		this.automobili = automobili;
		this.delovi = delovi;
		this.servisi = servisi;
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
		sevisiMenu.add(servisneKnjiziceItem);
		sevisiMenu.add(zakaziSevisItem);
		mainMenu.add(korisniciMenu);
		korisniciMenu.add(adminiItem);
		korisniciMenu.add(serviseriItem);
		korisniciMenu.add(musterijeItem);
		mainMenu.add(automobiliMenu);
		automobiliMenu.add(automobiliItem);
		mainMenu.add(deloviMenu);
		deloviMenu.add(deloviItem);
	}
	
	
	
	private void initActions() {
		adminiItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazAdmina pa = new PrikazAdmina(korisnici);
				pa.setVisible(true);
			}
		});
		
		musterijeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazMusterija pm = new PrikazMusterija(korisnici);
				pm.setVisible(true);
			}
		});
		
		serviseriItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazServisera ps = new PrikazServisera(korisnici);
				ps.setVisible(true);
			}
		});
		
		automobiliItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazAutomobila pa = new PrikazAutomobila(automobili, korisnici);
				pa.setVisible(true);
			}
		});
		
		deloviItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prikazDelova pd = new prikazDelova(delovi, korisnici);
				pd.setVisible(true);
			}
		});
		
		zakaziSevisItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazServisa ps = new PrikazServisa(servisi, korisnici, automobili);
				ps.setVisible(true);
			}
		});
		
		servisneKnjiziceItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazServisnihKnjizica psk = new PrikazServisnihKnjizica(delovi ,automobili, servisi);
				psk.setVisible(true);
			}
		});
	
	}
	
	
}

