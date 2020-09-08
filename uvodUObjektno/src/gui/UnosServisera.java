package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import component.Korisnici;
import model.Administrator;
import model.Pol;
import model.Serviser;
import model.ServiserSpecijalizacija;
import net.miginfocom.swing.MigLayout;

public class UnosServisera extends JFrame {
	
	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel lblIme = new  JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	private JLabel lblPrezime = new JLabel("Prezime");
	private JTextField txtPrezime = new JTextField(20);
	private JLabel lblJMBG = new JLabel("JMBG");
	private JTextField txtJMBG = new JTextField(20);
	private JLabel lblPol = new JLabel("Pol");
	private JComboBox<Pol> cbPol = new JComboBox<Pol>(Pol.values());
	private JLabel lblBroj = new JLabel("Broj");
	private JTextField txtBroj = new JTextField(20);
	private JLabel lblKorisnickoIme = new JLabel("Korisnicko Ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	private JLabel lblLozinka = new JLabel("Lozinka");
	private JPasswordField pfLozinka = new JPasswordField(20);
	private JLabel lblPlata = new JLabel("Plata");
	private JTextField txtPlata = new JTextField(20);
	private JLabel lblSpecijalizacija = new JLabel("Specijalizacija");
	private JComboBox<ServiserSpecijalizacija> cbSpecijalizacija = new JComboBox<ServiserSpecijalizacija>(ServiserSpecijalizacija.values());
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Korisnici korisnici;
	private Serviser prijavljeni;
	
	public UnosServisera(Korisnici korisnici, Serviser prijavljeni) {
		this.korisnici = korisnici;
		this.prijavljeni = prijavljeni;
		if(prijavljeni == null) {
			setTitle("Dodavanje servisera");
		}else {
			setTitle("Izmena podataka - " + prijavljeni.getIme());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][]20[]");
		setLayout(layout);
		
		if(prijavljeni != null) {
			txtID.setEnabled(false);
			popuniPolja();
		}
		
		add(lblID);
		add(txtID);
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblJMBG);
		add(txtJMBG);
		add(lblPol);
		add(cbPol);
		add(lblBroj);
		add(txtBroj);
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblLozinka);
		add(pfLozinka);
		add(lblPlata);
		add(txtPlata);
		add(lblSpecijalizacija);
		add(cbSpecijalizacija);
		
		
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
	}
	
	private void initActions() {
		btnOK.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					String id = txtID.getText().trim();
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					Integer jmbg = Integer.parseInt(txtJMBG.getText().trim());
					Pol pol = (Pol)cbPol.getSelectedItem();
					String broj = txtBroj.getText().trim();
					String korisnickoIme = txtKorisnickoIme.getText().trim();
					String lozinka = new String(pfLozinka.getPassword()).trim();
					double plata = Double.parseDouble(txtPlata.getText().trim());
					ServiserSpecijalizacija specijalizacija = (ServiserSpecijalizacija)cbSpecijalizacija.getSelectedItem();
					
					

					
					if(prijavljeni == null) { 
						Serviser serviser = new Serviser(id,ime, prezime, jmbg, pol, broj, korisnickoIme, lozinka, plata, specijalizacija,  false);
						korisnici.dodajServisera(serviser);
					}else {
						prijavljeni.setIme(ime);
						prijavljeni.setPrezime(prezime);
						prijavljeni.setJmbg(jmbg);
						prijavljeni.setPol(pol);
						prijavljeni.setBroj(broj);
						prijavljeni.setKorisnickoIme(korisnickoIme);
						prijavljeni.setLozinka(lozinka);
						prijavljeni.setPlata(plata);
						prijavljeni.setSpecijalizacija(specijalizacija);
					}
					korisnici.snimiServisere();
					UnosServisera.this.dispose();
					UnosServisera.this.setVisible(false);
				}
			}
		});
	};
	
	private void popuniPolja() {
		txtID.setText(prijavljeni.getId());
		txtIme.setText(String.valueOf(prijavljeni.getIme()));
		txtPrezime.setText(String.valueOf(prijavljeni.getPrezime()));
		txtJMBG.setText(String.valueOf(prijavljeni.getJmbg()));;
		cbPol.setSelectedItem(prijavljeni.getPol());
		txtBroj.setText(prijavljeni.getBroj());
		txtKorisnickoIme.setText(prijavljeni.getKorisnickoIme());
		pfLozinka.setText(prijavljeni.getLozinka());
		txtPlata.setText(String.valueOf(prijavljeni.getPlata()));
		cbSpecijalizacija.setSelectedItem(prijavljeni.getPol());
		
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "- Morate uneti ID\n";
			ok = false;
		}else if(prijavljeni == null) {
			String id = txtID.getText().trim();
			Serviser pronadjeni = korisnici.pronadjiServisera(id);
			if(pronadjeni != null) {
				poruka += "- Serviser sa unetim ID vec postoji\n";
				ok = false;
			}
		}
		
		try {
			Integer.parseInt(txtJMBG.getText().trim());
			
		}catch (NumberFormatException e) {
			poruka += "- JMBG mora biti broj\n";
			ok = false;
		}
		
		try {
			Double.parseDouble(txtPlata.getText().trim());
			
		}catch (NumberFormatException e) {
			poruka += "- Plata mora biti broj\n";
			ok = false;
		}
		
		try {
			Integer.parseInt(txtBroj.getText().trim());
			
		}catch (NumberFormatException e) {
			poruka += "- Broj telefona mora biti tipa broj\n";
			ok = false;
		}
		
		if(txtIme.getText().trim().equals("")) {
			poruka += "- Morate uneti ime\n";
			ok = false;
		}
		
		if(txtPrezime.getText().trim().equals("")) {
			poruka += "- Morate uneti prezime\n";
			ok = false;
		}
		
		if(txtKorisnickoIme.getText().trim().equals("")) {
			poruka += "- Unesite korisnicko ime\n";
			ok = false;
		}else if(prijavljeni == null){
			String korisnickoIme = txtKorisnickoIme.getText().trim();
			Serviser serviser = korisnici.nadjiServisera(korisnickoIme);
			if(serviser != null) {
				poruka += "- Prodavac sa tim korisnickim imenom vec postoji\n";
				ok = false;
			}
		}
		
		String lozinka = new String(pfLozinka.getPassword()).trim();
		if(lozinka.equals("")) {
			poruka += "- Unesite sifru\n";
			ok = false;
		}
		

		
		if(ok == false) {
			JOptionPane.showMessageDialog(null,poruka , "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}

}
