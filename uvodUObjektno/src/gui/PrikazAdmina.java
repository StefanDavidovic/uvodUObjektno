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
import model.Osoba;

public class PrikazAdmina extends JFrame {
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable adminiTabela;
	
	private Korisnici korisnici;
	
	public PrikazAdmina(Korisnici korisnici) {
		this.korisnici = korisnici;
		setTitle("ADMINISTRATORI");
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
		
		String[] zaglavlja = new String[] {"ID", "Ime", "Prezime", "JMBG", "Pol", "Broj", "Korisnicko Ime", "Lozinka", "Plata"};
		Object[][] sadrzaj = new Object[korisnici.sviNeobrisaniAdmini().size()][zaglavlja.length];
		
		for(int i=0; i<korisnici.sviNeobrisaniAdmini().size(); i++) {
			Administrator admin= korisnici.sviNeobrisaniAdmini().get(i);
			sadrzaj[i][0] = admin.getId();
			sadrzaj[i][1] = admin.getIme();
			sadrzaj[i][2] = admin.getPrezime();
			sadrzaj[i][3] = admin.getJmbg();
			sadrzaj[i][4] = admin.getPol();
			sadrzaj[i][5] = admin.getBroj();
			sadrzaj[i][6] = admin.getKorisnickoIme();
			sadrzaj[i][7] = admin.getLozinka();
			sadrzaj[i][8] = admin.getPlata();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		adminiTabela = new JTable(tableModel);
		
		adminiTabela.setRowSelectionAllowed(true);
		adminiTabela.setColumnSelectionAllowed(false);
		adminiTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		adminiTabela.setDefaultEditor(Object.class, null);
		adminiTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(adminiTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = adminiTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String adminId = tableModel.getValueAt(red, 0).toString();
					Administrator admin = korisnici.pronadjiAdmina(adminId);
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete administratora?", 
							adminId + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						admin.setObrisan(true);
						tableModel.removeRow(red);
						korisnici.snimiAdmine();;
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UnosAdmina ua = new UnosAdmina(korisnici, null);
				ua.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = adminiTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String adminID = tableModel.getValueAt(red, 0).toString();
					Administrator admin = korisnici.pronadjiAdmina(adminID);
					UnosAdmina ua = new UnosAdmina(korisnici , admin);
					ua.setVisible(true);
				}
			}
		});
	}

}
