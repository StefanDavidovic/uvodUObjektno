package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import component.Automobili;
import component.Delovi;
import component.Korisnici;
import component.Servisi;
import model.Deo;
import model.Servis;

public class PrikazServisa extends JFrame {
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable servisiTabela;
	
	private Servisi servisi;
	private Korisnici korisnici;
	private Automobili automobili;
	
	public PrikazServisa(Servisi servisi, Korisnici korisnici, Automobili automobili) {
		this.servisi = servisi;
		this.korisnici = korisnici;
		this.automobili = automobili;
		setTitle("SERVISI");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}
	
	private void initGUI() {
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/icons/Add.gif"));
		btnAdd.setIcon(addIcon);
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/icons/Modify.gif"));
		btnEdit.setIcon(editIcon);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/icons/Delete.gif"));
		btnDelete.setIcon(deleteIcon);
		
		mainToolbar.add(btnAdd);
		mainToolbar.add(btnEdit);
		mainToolbar.add(btnDelete);
		add(mainToolbar, BorderLayout.NORTH);
		
		mainToolbar.setFloatable(false);
		
		String[] zaglavlja = new String[] {"ID","Automobil", "Serviser", "Termin", "Opis", "Status"};
		Object[][] sadrzaj = new Object[servisi.sviNeobrisaniServisi().size()][zaglavlja.length];
		
		for(int i=0; i<servisi.sviNeobrisaniServisi().size(); i++) {
			Servis servis = servisi.sviNeobrisaniServisi().get(i);
			sadrzaj[i][0] = servis.getId();
			sadrzaj[i][1] = servis.getAutomobil().getModel();
			sadrzaj[i][2] = servis.getServiser().getKorisnickoIme();
			sadrzaj[i][3] = servis.getTermin();
			sadrzaj[i][4] = servis.getOpis();
			sadrzaj[i][5] = servis.getStatusServisa();

		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		servisiTabela = new JTable(tableModel);
		
		servisiTabela.setRowSelectionAllowed(true);
		servisiTabela.setColumnSelectionAllowed(false);
		servisiTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		servisiTabela.setDefaultEditor(Object.class, null);
		servisiTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(servisiTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = servisiTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String servisId = tableModel.getValueAt(red, 0).toString();
					Servis servis= servisi.pronadjiServis(servisId);
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete servis?", 
							servisId + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						servis.setObrisan(true);
						tableModel.removeRow(red);
						servisi.snimiServise();;
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UnosServisa us = new UnosServisa(servisi, null, korisnici, automobili);
				us.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = servisiTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String servisID = tableModel.getValueAt(red, 0).toString();
					Servis servis = servisi.pronadjiServis(servisID);
					UnosServisa us = new UnosServisa(servisi , servis, korisnici, automobili);
					us.setVisible(true);

				}
			}
		});
	}
}
