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
import model.Automobil;
import model.Deo;

public class prikazDelova extends JFrame {
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	private JButton btnSymetric = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable deloviTabela;
	
	private Delovi delovi;
	private Korisnici korisnici;
	
	public prikazDelova(Delovi delovi, Korisnici korisnici) {
		this.delovi = delovi;
		this.korisnici = korisnici;
		setTitle("DELOVI");
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
		mainToolbar.add(btnSymetric);
		add(mainToolbar, BorderLayout.NORTH);
		
		mainToolbar.setFloatable(false);
		
		String[] zaglavlja = new String[] {"ID", "Marka", "Model", "Naziv", "Cena"};
		Object[][] sadrzaj = new Object[delovi.sviNeobrisaniDelovi().size()][zaglavlja.length];
		
		for(int i=0; i<delovi.sviNeobrisaniDelovi().size(); i++) {
			Deo deo = delovi.sviNeobrisaniDelovi().get(i);
			sadrzaj[i][0] = deo.getId();
			sadrzaj[i][1] = deo.getMarka();
			sadrzaj[i][2] = deo.getModel();
			sadrzaj[i][3] = deo.getNaziv();
			sadrzaj[i][4] = deo.getCena();

		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		deloviTabela = new JTable(tableModel);
		
		deloviTabela.setRowSelectionAllowed(true);
		deloviTabela.setColumnSelectionAllowed(false);
		deloviTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		deloviTabela.setDefaultEditor(Object.class, null);
		deloviTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(deloviTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = deloviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String autoId = tableModel.getValueAt(red, 0).toString();
					Deo deo = delovi.pronadjiDeo(autoId);
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete deo?", 
							autoId + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						deo.setObrisan(true);
						tableModel.removeRow(red);
						delovi.snimiDelove();;
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UnosDelova ud = new UnosDelova(delovi, null);
				ud.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = deloviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String deoID = tableModel.getValueAt(red, 0).toString();
					Deo deo = delovi.pronadjiDeo(deoID);
					UnosDelova ud = new UnosDelova(delovi , deo);
					ud.setVisible(true);
				}
			}
		});
		
		btnSymetric.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = deloviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String deoID = tableModel.getValueAt(red, 0).toString();
					Deo deo = delovi.pronadjiDeo(deoID);
					SimetricniDeo sm = new SimetricniDeo(delovi, deo);
					sm.setVisible(true);
					
				}
			}
		});
	}
}



