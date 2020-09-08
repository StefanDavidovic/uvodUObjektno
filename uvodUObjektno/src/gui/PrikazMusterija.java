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
import model.Administrator;
import model.Musterija;

public class PrikazMusterija extends JFrame {
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable musterijeTabela;
	
	private Musterija musterije;
	private Korisnici korisnici;
	
	public PrikazMusterija(Korisnici korisnici) {
		this.korisnici = korisnici;
		setTitle("MUSTERIJE");
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
		
		String[] zaglavlja = new String[] {"ID", "Ime", "Prezime", "JMBG", "Pol", "Broj", "Korisnicko Ime", "Lozinka", "Plata", "BrojBodova"};
		Object[][] sadrzaj = new Object[korisnici.sveNeobrisaneMusterije().size()][zaglavlja.length];
		System.out.println("zaglavlja");
		
		for(int i=0; i<korisnici.sveNeobrisaneMusterije().size(); i++) {
			Musterija musterija= korisnici.sveNeobrisaneMusterije().get(i);
			sadrzaj[i][0] = musterija.getId();
			sadrzaj[i][1] = musterija.getIme();
			sadrzaj[i][2] = musterija.getPrezime();
			sadrzaj[i][3] = musterija.getJmbg();
			sadrzaj[i][4] = musterija.getPol();
			sadrzaj[i][5] = musterija.getBroj();
			sadrzaj[i][6] = musterija.getKorisnickoIme();
			sadrzaj[i][7] = musterija.getLozinka();
			sadrzaj[i][8] = musterija.getBrojBodova();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		musterijeTabela = new JTable(tableModel);
		
		musterijeTabela.setRowSelectionAllowed(true);
		musterijeTabela.setColumnSelectionAllowed(false);
		musterijeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		musterijeTabela.setDefaultEditor(Object.class, null);
		musterijeTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(musterijeTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = musterijeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String musterijaID = tableModel.getValueAt(red, 0).toString();
					Musterija musterija = korisnici.pronadjiMusteriju(musterijaID);
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete korisnika?", 
							musterijaID + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						musterija.setObrisan(true);
						tableModel.removeRow(red);
						korisnici.snimiMusterije();;
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UnosMusterije ua = new UnosMusterije(korisnici, null);
				ua.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = musterijeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String musterijaID = tableModel.getValueAt(red, 0).toString();
					Musterija musterija = korisnici.pronadjiMusteriju(musterijaID);
					UnosMusterije um = new UnosMusterije(korisnici , musterija);
					um.setVisible(true);
				}
			}
		});
		
	}
		
}
