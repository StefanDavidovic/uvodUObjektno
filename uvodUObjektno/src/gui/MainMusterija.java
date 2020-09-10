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
import model.Musterija;
import model.Osoba;
import model.Servis;

public class MainMusterija extends JFrame {
	
	private JMenuBar mainMenu = new JMenuBar();
	private JMenu servisiMenu = new JMenu("Servisi");
	private JMenuItem servisiItem = new JMenuItem("Servisi");
	
	private Korisnici korisnici;
	private Musterija musterija;
	private Servisi servisi;
	private Automobili automobili;
	
	
	public MainMusterija(Korisnici korisnici, Musterija musterija, Servisi servisi, Automobili automobili) {
		this.korisnici = korisnici;
		this.servisi = servisi;
		this.automobili = automobili;
		this.servisi = servisi;
		setTitle("Prijavljeni korisnik: " + musterija.getKorisnickoIme());
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenu();
		initActions();
	}
	
	private void initMenu() {
		setJMenuBar(mainMenu);
		mainMenu.add(servisiMenu);
		servisiMenu.add(servisiItem);
	}
	
	private void initActions() {
		servisiItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazServisa pa = new PrikazServisa(servisi, korisnici ,automobili);
				pa.setVisible(true);
			}
		});
	
	}
}
