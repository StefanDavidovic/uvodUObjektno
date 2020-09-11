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
import model.ServisnaKnjizica;

public class PrikazServisnihKnjizica extends JFrame {
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable servisneKnjiziceTabela;
	
	private Delovi delovi;
	private Automobili automobili;
	private Servisi servisi;
	
	public PrikazServisnihKnjizica(Delovi delovi, Automobili automobili, Servisi servisi) {
		this.delovi = delovi;
		this.automobili = automobili;
		this.servisi = servisi;
		setTitle("SERVISNE KNJIZICE");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}
	
	private void initGUI() {
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/icons/Add.gif"));
		btnAdd.setIcon(addIcon);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/icons/Delete.gif"));
		btnDelete.setIcon(deleteIcon);
		
		mainToolbar.add(btnAdd);
		mainToolbar.add(btnDelete);
		add(mainToolbar, BorderLayout.NORTH);
		
		mainToolbar.setFloatable(false);
		
		String[] zaglavlja = new String[] {"ID", "Automobil", "Opis Servisa"};
		Object[][] sadrzaj = new Object[servisi.sveNeobrisaneServisneKnjizice().size()][zaglavlja.length];
		
		for(int i=0; i<servisi.sveNeobrisaneServisneKnjizice().size(); i++) {
			ServisnaKnjizica servisnaKnjizica = servisi.sveNeobrisaneServisneKnjizice().get(i);
			sadrzaj[i][0] = servisnaKnjizica.getId();
			sadrzaj[i][1] = servisnaKnjizica.getAutomobili();
			sadrzaj[i][2] = servisnaKnjizica.getServisi();
			


		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		servisneKnjiziceTabela = new JTable(tableModel);
		
		servisneKnjiziceTabela.setRowSelectionAllowed(true);
		servisneKnjiziceTabela.setColumnSelectionAllowed(false);
		servisneKnjiziceTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		servisneKnjiziceTabela.setDefaultEditor(Object.class, null);
		servisneKnjiziceTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(servisneKnjiziceTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = servisneKnjiziceTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String skId = tableModel.getValueAt(red, 0).toString();
					ServisnaKnjizica servisnaKnjizica = servisi.pronadjiServisnuKnjizicu(skId);
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete deo?", 
							skId + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						servisnaKnjizica.setObrisan(true);
						tableModel.removeRow(red);
						servisi.snimiServisneKnjizice();;
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UnosServisneKnjizice ud = new UnosServisneKnjizice(null, servisi, automobili);
				ud.setVisible(true);
			}
		});

	}


}
