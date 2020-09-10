package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import component.Automobili;
import component.Delovi;
import component.Korisnici;
import model.Automobil;
import model.Deo;
import model.Gorivo;
import model.MarkaAutomobila;
import model.ModelAutomobila;
import model.Musterija;
import net.miginfocom.swing.MigLayout;

public class UnosDelova extends JFrame {
	
	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);

	private JLabel lblMarka = new JLabel("Marka");
	private JComboBox<MarkaAutomobila> cbMarka = new JComboBox<MarkaAutomobila>(MarkaAutomobila.values());
	
	private JLabel lblModel = new JLabel("Model");
	private JComboBox<ModelAutomobila> cbModel = new JComboBox<ModelAutomobila>(ModelAutomobila.values());
	
	private JLabel lblNaziv = new JLabel("Naziv");
	private JTextField txtNaziv = new JTextField(20);
	
	private JLabel lblCena = new JLabel("Cena");
	private JTextField txtCena = new JTextField(20);
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Delovi delovi;
	private Deo deo;
	
	
	public UnosDelova(Delovi delovi, Deo deo ) {
		this.delovi = delovi;
		this.deo = deo;
		if(deo == null) {
			setTitle("Dodavanje automobila");
		}else {
			setTitle("Izmena podataka - " + deo.getId());
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
		
		if(deo != null) {
			txtID.setEnabled(false);
			popuniPolja();
		}
		
		
		add(lblID);
		add(txtID);
		add(lblMarka);
		add(cbMarka);
		add(lblModel);
		add(cbModel);
		add(lblNaziv);
		add(txtNaziv);
		add(lblCena);
		add(txtCena);
		
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
					MarkaAutomobila marka = (MarkaAutomobila)cbMarka.getSelectedItem();
					ModelAutomobila model = (ModelAutomobila)cbModel.getSelectedItem();
					String naziv = txtNaziv.getText().trim();
					double cena = Double.parseDouble(txtCena.getText().trim());

					if(deo == null) { 
						Deo deo = new Deo(id, marka, model, naziv , cena, false);
						delovi.dodajDeo(deo);
					}else {
						deo.setMarka(marka);
						deo.setModel(model);
						deo.setNaziv(naziv);
						deo.setCena(cena);

					}
					delovi.snimiDelove();
					UnosDelova.this.dispose();
					UnosDelova.this.setVisible(false);
				}
			}
		});
	};
	
	private void popuniPolja() {
		txtID.setText(deo.getId());
		cbMarka.setSelectedItem(deo.getMarka());
		cbModel.setSelectedItem(deo.getModel());
		txtNaziv.setText(deo.getNaziv());
		txtCena.setText(String.valueOf(deo.getCena()));
		
	}
	

	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "- Morate uneti ID\n";
			ok = false;
		}else if(deo == null) {
			String id = txtID.getText().trim();
			Deo deo = delovi.pronadjiDeo(id);
			if(deo != null) {
				poruka += "- Deo sa unetim ID vec postoji\n";
				ok = false;
			}
		}
		
		
		if(txtNaziv.getText().trim().equals("")) {
			poruka += "- Morate uneti naziv\n";
			ok = false;
		}
		
		
		try {
			Double.parseDouble(txtCena.getText().trim());
			
		}catch (NumberFormatException e) {
			poruka += "- Cena mora biti broj\n";
			ok = false;
		}

		
		if(ok == false) {
			JOptionPane.showMessageDialog(null,poruka , "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}

}
