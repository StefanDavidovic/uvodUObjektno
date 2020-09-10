package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import component.Automobili;
import component.Delovi;
import component.Korisnici;
import component.Servisi;
import gui.MainAdministratori;
import model.Administrator;
import model.Musterija;
import model.Osoba;
import model.Servis;
import model.Serviser;
import net.miginfocom.swing.MigLayout;

public class LogInProzor extends JFrame {

	private JLabel lblGreeting = new JLabel("Dobrodošli. Molimo da se prijavite.");
	private JLabel lblUsername = new JLabel("Korisničko ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	private JLabel lblPassword = new JLabel("Šifra");
	private JPasswordField pfPassword = new JPasswordField(20);
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");

	private Korisnici korisnici;
	private Automobili automobili;
	private Delovi delovi;
	private Servisi servisi;

	public LogInProzor(Korisnici korisnici, Automobili automobili, Delovi delovi, Servisi servisi) {
		this.korisnici = korisnici;
		this.automobili = automobili;
		this.delovi = delovi;
		this.servisi = servisi;
		setTitle("Prijava");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		pack();
	}

	public void initGUI() {
		MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
		setLayout(mig);

		add(lblGreeting, "span 2");
		add(lblUsername);
		add(txtKorisnickoIme);
		add(lblPassword);
		add(pfPassword);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);

		getRootPane().setDefaultButton(btnOk);
	}

	public void initActions() {
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LogInProzor.this.dispose();
				LogInProzor.this.setVisible(false);
			}
		});

		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnikoIme = txtKorisnickoIme.getText().trim();
				String sifra = new String(pfPassword.getPassword()).trim();

				if (korisnikoIme.equals("") || sifra.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke za prijavu.", "Greska",
							JOptionPane.WARNING_MESSAGE);
				} else {
					Osoba prijavljeni = korisnici.loginKorisnika(korisnikoIme, sifra);
					if (prijavljeni == null) {
						JOptionPane.showMessageDialog(null, "Pogrešni login podaci.", "Greška",
								JOptionPane.WARNING_MESSAGE);
					} else if (prijavljeni instanceof Musterija) {
						
						Musterija musterija = korisnici.nadjiMusterijuPoID(prijavljeni.getId());
						MainMusterija mm = new MainMusterija( korisnici ,musterija, servisi, automobili);
						mm.setVisible(true);
						System.out.println("OVO je musterija");
						
						LogInProzor.this.dispose();
						LogInProzor.this.setVisible(false);

					} else if (prijavljeni instanceof Administrator) {
						
						MainAdministratori ma = new MainAdministratori(korisnici, automobili, delovi, servisi);
						ma.setVisible(true);
						System.out.println("Ovo je Administrator");
						
						LogInProzor.this.dispose();
						LogInProzor.this.setVisible(false);
					

					} else if (prijavljeni instanceof Serviser) {
						Serviser serviser = korisnici.pronadjiServisera(prijavljeni.getId());
						MainServiseri ms = new MainServiseri(serviser, korisnici,automobili,servisi);
						ms.setVisible(true);
						System.out.println("Ovo je serviser");
						
						LogInProzor.this.dispose();
						LogInProzor.this.setVisible(false);

					}
				}
			}
		});
	}
}
