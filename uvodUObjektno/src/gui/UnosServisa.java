package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import component.Automobili;
import component.Delovi;
import component.Korisnici;
import component.Servisi;
import model.Automobil;
import model.Deo;
import model.MarkaAutomobila;
import model.ModelAutomobila;
import model.Musterija;
import model.Servis;
import model.Serviser;
import model.StatusServisa;
import net.miginfocom.swing.MigLayout;

public class UnosServisa extends JFrame {

	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);

	private JLabel lblAutomobil = new JLabel("Automobil");
	private JComboBox<String> cbAutomobil = new JComboBox<String>();

	private JLabel lblServiser = new JLabel("Serviser");
	private JComboBox<String> cbServiser = new JComboBox<String>();

	private JLabel lblTermin = new JLabel("Termin (dd/mm/yyyy)");
	private JTextField txtTermin = new JTextField(20);

	private JLabel lblOpis = new JLabel("Opis");
	private JTextField txtOpis = new JTextField(20);

	private JLabel lblStatus = new JLabel("Status servisa");
	private JComboBox<StatusServisa> cbStatus = new JComboBox<StatusServisa>(StatusServisa.values());

	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");

	private Servisi servisi;
	private Servis servis;
	private Korisnici korisnici;
	private Automobili automobili;

	public UnosServisa(Servisi servisi, Servis servis, Korisnici korisnici, Automobili automobili) {
		this.servisi = servisi;
		this.servis = servis;
		this.korisnici = korisnici;
		this.automobili = automobili;
		if (servis == null) {
			setTitle("Dodavanje automobila");
		} else {
			setTitle("Izmena podataka - " + servis.getId());
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

		if (servis != null) {
			txtID.setEnabled(false);
			popuniPolja();
		}
		
		for (Serviser serviser : korisnici.getServisere()) {
			cbServiser.addItem(serviser.getKorisnickoIme());
		}
		
		for (Automobil automobil: automobili.getAutomobil()) {
			cbAutomobil.addItem(automobil.getId());
		}

		add(lblID);
		add(txtID);
		add(lblAutomobil);
		add(cbAutomobil);
		add(lblServiser);
		add(cbServiser);
		add(lblTermin);
		add(txtTermin);
		add(lblOpis);
		add(txtOpis);
		add(lblStatus);
		add(cbStatus);

		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
	}

	private void initActions() {
		btnOK.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validacija()) {
					String id = txtID.getText().trim();
					String autoId = (String)cbAutomobil.getSelectedItem();
					Automobil automobil = automobili.pronadjiAutomobil(autoId);
					String korisnickoIme = (String)cbServiser.getSelectedItem();
					Serviser serviser = korisnici.nadjiServisera(korisnickoIme);
					String termin = txtTermin.getText().trim();
					String opis = txtOpis.getText().trim();
					StatusServisa status = (StatusServisa) cbStatus.getSelectedItem();

					if (servis == null) {
						Servis servis = new Servis(id,automobil, serviser, termin, opis, new ArrayList<Deo>(), status, false);
						servisi.dodajServis(servis);
					} else {
						servis.setTermin(termin);
						servis.setOpis(opis);
						servis.setStatusServisa(status);

					}
					servisi.snimiServise();
					UnosServisa.this.dispose();
					UnosServisa.this.setVisible(false);
				}
			}
		});
	};

	private void popuniPolja() {
		txtID.setText(servis.getId());
		cbAutomobil.setSelectedItem(servis.getAutomobil().getModel());
		cbServiser.setSelectedItem(servis.getServiser().getKorisnickoIme());
		txtTermin.setText(servis.getTermin());
		txtOpis.setText(servis.getOpis());
		cbStatus.setSelectedItem(servis.getStatusServisa());

	}

	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";

		if (txtID.getText().trim().equals("")) {
			poruka += "- Morate uneti ID\n";
			ok = false;
		} else if (servis == null) {
			String id = txtID.getText().trim();
			Servis servis = servisi.pronadjiServis(id);
			if (servis != null) {
				poruka += "- Servis sa unetim ID vec postoji\n";
				ok = false;
			}
		}

		if (txtTermin.getText().trim().equals("")) {
			poruka += "- Morate uneti termin u datom formatu\n";
			ok = false;
		}

		if (txtOpis.getText().trim().equals("")) {
			poruka += "- Morate uneti opis \n";
			ok = false;
		}

		if (ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}

}
