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

import component.Automobili;
import component.Korisnici;
import model.Administrator;
import model.Automobil;
import model.Gorivo;
import model.MarkaAutomobila;
import model.ModelAutomobila;
import model.Musterija;
import model.Pol;
import net.miginfocom.swing.MigLayout;

public class UnosAutomobila extends JFrame {
	
	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	
	private JLabel lblVlasnik = new  JLabel("Vlasnik");
	private JComboBox<String> cbVlasnik = new JComboBox<String>();

	private JLabel lblMarka = new JLabel("Marka");
	private JComboBox<MarkaAutomobila> cbMarka = new JComboBox<MarkaAutomobila>(MarkaAutomobila.values());
	
	private JLabel lblModel = new JLabel("Model");
	private JComboBox<ModelAutomobila> cbModel = new JComboBox<ModelAutomobila>(ModelAutomobila.values());
	
	private JLabel lblGodiste = new JLabel("Godiste");
	private JTextField txtGodiste = new JTextField(20);
	
	private JLabel lblKubikazaMotora = new JLabel("Kubikaza Motora");
	private JTextField txtKubikazaMotora = new JTextField(20);
	
	private JLabel lblSnagaMotora = new JLabel("SnagaMotora");
	private JTextField txtSnagaMotora = new JTextField(20);
	
	private JLabel lblGorivo = new JLabel("Gorivo");
	private JComboBox<Gorivo> cbGorivo = new JComboBox<Gorivo>(Gorivo.values());
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Automobili automobili;
	private Automobil auto;
	private Korisnici korisnici;
	
	
	public UnosAutomobila(Automobili automobili, Automobil auto, Korisnici korisnici ) {
		this.automobili = automobili;
		this.auto = auto;
		this.korisnici = korisnici;
		if(auto == null) {
			setTitle("Dodavanje automobila");
		}else {
			setTitle("Izmena podataka - " + auto.getId());
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
		
		if(auto != null) {
			txtID.setEnabled(false);
			popuniPolja();
		}
		
		for (Musterija musterija : korisnici.getMusterije()) {
			cbVlasnik.addItem(musterija.getKorisnickoIme());
		}
		
		
		add(lblID);
		add(txtID);
		add(lblVlasnik);
		add(cbVlasnik);
		add(lblMarka);
		add(cbMarka);
		add(lblModel);
		add(cbModel);
		add(lblGodiste);
		add(txtGodiste);
		add(lblKubikazaMotora);
		add(txtKubikazaMotora);
		add(lblSnagaMotora);
		add(txtSnagaMotora);
		add(lblGorivo);
		add(cbGorivo);
		
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
					String korisnickoIme = (String)cbVlasnik.getSelectedItem();
					Musterija vlasnik = korisnici.nadjiMusteriju(korisnickoIme);
					MarkaAutomobila marka = (MarkaAutomobila)cbMarka.getSelectedItem();
					ModelAutomobila model = (ModelAutomobila)cbModel.getSelectedItem();
					Integer godiste = Integer.parseInt(txtGodiste.getText().trim());
					Integer kubikaza = Integer.parseInt(txtKubikazaMotora.getText().trim());
					Integer snaga = Integer.parseInt(txtSnagaMotora.getText().trim());
					Gorivo gorivo = (Gorivo)cbGorivo.getSelectedItem();

					if(auto == null) { 
						Automobil auto = new Automobil(id,vlasnik, marka, model, godiste, kubikaza, snaga, gorivo, false);
						automobili.dodajAutomobil(auto);
					}else {
						auto.setvlasnik(vlasnik);
						auto.setMarka(marka);
						auto.setModel(model);
						auto.setGodiste(godiste);
						auto.setKubikazaMotora(kubikaza);
						auto.setSnagaMotora(snaga);
						auto.setGorivo(gorivo);
					}
					automobili.snimiAutomobile();
					UnosAutomobila.this.dispose();
					UnosAutomobila.this.setVisible(false);
				}
			}
		});
	};
	
	private void popuniPolja() {
		txtID.setText(auto.getId());
		cbVlasnik.setSelectedItem(auto.getvlasnik().getKorisnickoIme());
		cbMarka.setSelectedItem(auto.getMarka());
		cbModel.setSelectedItem(auto.getModel());
		txtGodiste.setText(String.valueOf(auto.getGodiste()));
		txtKubikazaMotora.setText(String.valueOf(auto.getKubikazaMotora()));
		txtSnagaMotora.setText(String.valueOf(auto.getSnagaMotora()));
		cbGorivo.setSelectedItem(auto.getGorivo());
		
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "- Morate uneti ID\n";
			ok = false;
		}else if(auto == null) {
			String id = txtID.getText().trim();
			Automobil auto = automobili.pronadjiAutomobil(id);
			if(auto != null) {
				poruka += "- Automobil sa unetim ID vec postoji\n";
				ok = false;
			}
		}
		
		try {
			Integer.parseInt(txtGodiste.getText().trim());
			
		}catch (NumberFormatException e) {
			poruka += "- Godiste mora biti broj\n";
			ok = false;
		}
		
		try {
			Integer.parseInt(txtKubikazaMotora.getText().trim());
			
		}catch (NumberFormatException e) {
			poruka += "- Kubikaza motora mora biti broj\n";
			ok = false;
		}
		
		try {
			Integer.parseInt(txtSnagaMotora.getText().trim());
			
		}catch (NumberFormatException e) {
			poruka += "- Snaga motora mora biti broj\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null,poruka , "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}

}
