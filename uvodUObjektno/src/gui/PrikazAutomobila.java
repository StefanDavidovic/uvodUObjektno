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
import component.Korisnici;
import model.Administrator;
import model.Automobil;

public class PrikazAutomobila extends JFrame {
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable automobiliTabela;
	
	private Automobili automobili;
	private Korisnici korisnici;
	
	public PrikazAutomobila(Automobili automobili, Korisnici korisnici) {
		this.automobili = automobili;
		this.korisnici = korisnici;
		setTitle("AUTOMOBILI");
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
		
		String[] zaglavlja = new String[] {"ID", "Vlasnik", "Marka", "Model", "Godiste", "Kubikaza", "Snaga", "Gorivo"};
		Object[][] sadrzaj = new Object[automobili.sviNeobrisaniAutomobili().size()][zaglavlja.length];
		
		for(int i=0; i<automobili.sviNeobrisaniAutomobili().size(); i++) {
			Automobil auto = automobili.sviNeobrisaniAutomobili().get(i);
			sadrzaj[i][0] = auto.getId();
			sadrzaj[i][1] = auto.getvlasnik().getKorisnickoIme();
			sadrzaj[i][2] = auto.getMarka();
			sadrzaj[i][3] = auto.getModel();
			sadrzaj[i][4] = auto.getGodiste();
			sadrzaj[i][5] = auto.getKubikazaMotora();
			sadrzaj[i][6] = auto.getSnagaMotora();
			sadrzaj[i][7] = auto.getGorivo();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		automobiliTabela = new JTable(tableModel);
		
		automobiliTabela.setRowSelectionAllowed(true);
		automobiliTabela.setColumnSelectionAllowed(false);
		automobiliTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		automobiliTabela.setDefaultEditor(Object.class, null);
		automobiliTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(automobiliTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = automobiliTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String autoId = tableModel.getValueAt(red, 0).toString();
					Automobil auto = automobili.pronadjiAutomobil(autoId);
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete automobil?", 
							autoId + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						auto.setObrisan(true);
						tableModel.removeRow(red);
						automobili.snimiAutomobile();;
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UnosAutomobila ua = new UnosAutomobila(automobili, null, korisnici);
				ua.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = automobiliTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String autoID = tableModel.getValueAt(red, 0).toString();
					Automobil auto = automobili.pronadjiAutomobil(autoID);
					UnosAutomobila ua = new UnosAutomobila(automobili , auto, korisnici);
					ua.setVisible(true);
				}
			}
		});
	}

}
