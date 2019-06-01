package presentation.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import presentation.controller.ConsulterDemandeEmployeController;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;

public class DetailsEtapeEmployeView extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomEtape;
	private JTextField txtEmpRespo;
	private JTextField txtTraitement;
	private ConsulterDemandeEmployeController controller;
	private JTextArea txtAreaRapport;
	/**
	 * Create the frame.
	 * @param controller 
	 */
	public DetailsEtapeEmployeView(ConsulterDemandeEmployeController controller) {
		this.controller = controller;
		
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1023, 618);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomEtape = new JLabel("Nom de l'etape");
		lblNomEtape.setBounds(221, 76, 184, 16);
		contentPane.add(lblNomEtape);
		
		JLabel lblEmpRespo = new JLabel("Employ\u00E9 responsable");
		lblEmpRespo.setBounds(221, 156, 184, 16);
		contentPane.add(lblEmpRespo);
		
		JLabel lblTraitement = new JLabel("Traitement");
		lblTraitement.setBounds(221, 244, 184, 16);
		contentPane.add(lblTraitement);
		
		JLabel lblRapport = new JLabel("Rapport");
		lblRapport.setBounds(221, 397, 184, 16);
		contentPane.add(lblRapport);
		
		txtNomEtape = new JTextField();
		txtNomEtape.setEditable(false);
		txtNomEtape.setBounds(503, 73, 271, 22);
		contentPane.add(txtNomEtape);
		txtNomEtape.setColumns(10);
		
		txtEmpRespo = new JTextField();
		txtEmpRespo.setEditable(false);
		txtEmpRespo.setBounds(503, 153, 271, 22);
		contentPane.add(txtEmpRespo);
		txtEmpRespo.setColumns(10);
		
		txtTraitement = new JTextField();
		txtTraitement.setEditable(false);
		txtTraitement.setBounds(503, 241, 271, 22);
		contentPane.add(txtTraitement);
		txtTraitement.setColumns(10);
		
		txtAreaRapport = new JTextArea();
		txtAreaRapport.setEditable(false);
		txtAreaRapport.setBounds(503, 348, 326, 117);
		contentPane.add(txtAreaRapport);
		remplirFields();
	}
	
	private void remplirFields() {
		txtAreaRapport.setText(controller.getModelDetailsEtape().getEtapeRapport());
		txtTraitement.setText(controller.getModelDetailsEtape().getEtapeEtat());
		txtEmpRespo.setText(controller.getModelDetailsEtape().getEtapeEmployeRespo().getNom() + " " + controller.getModelDetailsEtape().getEtapeEmployeRespo().getPrenom());
		txtNomEtape.setText(controller.getModelDetailsEtape().getEtapeNom());
	}
}
