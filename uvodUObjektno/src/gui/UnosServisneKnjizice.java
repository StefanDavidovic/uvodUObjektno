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
import component.Servisi;
import model.Automobil;
import model.Deo;
import model.MarkaAutomobila;
import model.ModelAutomobila;
import model.Servis;
import model.ServisnaKnjizica;
import net.miginfocom.swing.MigLayout;

public class UnosServisneKnjizice extends JFrame {
	
	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	
	private JLabel lblAutomobil = new JLabel("Automobil");
	private JComboBox<String> cbAutomobil = new JComboBox<String>();
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private ServisnaKnjizica servisnaKnjizica;
	private Servisi servisi;
	private Automobili automobili;

	
	public UnosServisneKnjizice(ServisnaKnjizica servisnaKnjizica, Servisi servisi, Automobili automobili) {
		this.servisi = servisi;
		this.servisnaKnjizica = servisnaKnjizica;
		this.automobili = automobili;
		if(servisnaKnjizica == null) {
			setTitle("Dodavanje servisne knjizice");
		}else {
			setTitle("Izmena podataka - " + servisnaKnjizica.getId());
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
		
		if(servisnaKnjizica != null) {
			txtID.setEnabled(false);
			popuniPolja();
		}
		
		for (Automobil automobil: automobili.getAutomobil()) {
			cbAutomobil.addItem(automobil.getId() + "|" + automobil.getvlasnik().getKorisnickoIme());
		}
		
		
		add(lblID);
		add(txtID);
		add(lblAutomobil);
		add(cbAutomobil);
	
		
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
					Automobil auto= (Automobil)cbAutomobil.getSelectedItem();


					if(servisnaKnjizica == null) { 
						ServisnaKnjizica servisnaKnjizica = new ServisnaKnjizica(id, auto,new ArrayList<Servis>(), false);
						servisi.dodajServisnuKnjizicu(servisnaKnjizica);
					}else {
						servisnaKnjizica.setAutomobili(auto);

					}
					servisi.snimiServisneKnjizice();
					UnosServisneKnjizice.this.dispose();
					UnosServisneKnjizice.this.setVisible(false);
				}
			}
		});
	};
	
	private void popuniPolja() {
		txtID.setText(servisnaKnjizica.getId());
		cbAutomobil.setSelectedItem(servisnaKnjizica.getAutomobili());
		
	}
	
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "- Morate uneti ID\n";
			ok = false;
		}else if(servisnaKnjizica == null) {
			String id = txtID.getText().trim();
			ServisnaKnjizica servisnaKnjizica= servisi.pronadjiServisnuKnjizicu(id);
			if(servisnaKnjizica != null) {
				poruka += "- Deo sa unetim ID vec postoji\n";
				ok = false;
			}
		}

		
		if(ok == false) {
			JOptionPane.showMessageDialog(null,poruka , "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}

	
}
