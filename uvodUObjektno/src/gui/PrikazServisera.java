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

import component.Korisnici;
import model.Serviser;

public class PrikazServisera extends JFrame {
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable serviseriTabela;
	
	private Korisnici korisnici;
	
	public PrikazServisera(Korisnici korisnici) {
		this.korisnici = korisnici;
		setTitle("SERVISERI");
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
		
		String[] zaglavlja = new String[] {"ID", "Ime", "Prezime", "JMBG", "Pol", "Broj", "Korisnicko Ime", "Lozinka", "Plata", "Specijalizacija"};
		Object[][] sadrzaj = new Object[korisnici.sviNeobrisaniServiseri().size()][zaglavlja.length];
		
		for(int i=0; i<korisnici.sviNeobrisaniServiseri().size(); i++) {
			Serviser serviser= korisnici.sviNeobrisaniServiseri().get(i);
			sadrzaj[i][0] = serviser.getId();
			sadrzaj[i][1] = serviser.getIme();
			sadrzaj[i][2] = serviser.getPrezime();
			sadrzaj[i][3] = serviser.getJmbg();
			sadrzaj[i][4] = serviser.getPol();
			sadrzaj[i][5] = serviser.getBroj();
			sadrzaj[i][6] = serviser.getKorisnickoIme();
			sadrzaj[i][7] = serviser.getLozinka();
			sadrzaj[i][8] = serviser.getPlata();
			sadrzaj[i][9] = serviser.getSpecijalizacija();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		serviseriTabela = new JTable(tableModel);
		
		serviseriTabela.setRowSelectionAllowed(true);
		serviseriTabela.setColumnSelectionAllowed(false);
		serviseriTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		serviseriTabela.setDefaultEditor(Object.class, null);
		serviseriTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(serviseriTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = serviseriTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String serviserId = tableModel.getValueAt(red, 0).toString();
					Serviser serviser = korisnici.pronadjiServisera(serviserId);
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete servisera?", 
							serviserId + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						serviser.setObrisan(true);
						tableModel.removeRow(red);
						korisnici.snimiServisere();
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UnosServisera us = new UnosServisera(korisnici, null);
				us.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = serviseriTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String serviserID = tableModel.getValueAt(red, 0).toString();
					Serviser serviser = korisnici.pronadjiServisera(serviserID);
					UnosServisera ua = new UnosServisera(korisnici , serviser);
					ua.setVisible(true);
				}
			}
		});
	}


}
