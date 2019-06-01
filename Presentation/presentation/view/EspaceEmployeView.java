package presentation.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Business.Employe;
import metier.Demande;
import presentation.controller.ConsulterDemandeEmployeController;
import presentation.controller.EspaceEmployeController;
import presentation.controller.LoginController;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EspaceEmployeView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtRecherche;
	private EspaceEmployeController controller;
	private JButton btnArrowLeft;
	private JButton btnArrowRight;
	private JPanel panelInfoDemande;
	private JPanel panelSlide;
	private JLabel lblTraiter;
	/**
	 * Create the frame.
	 */
	public EspaceEmployeView(EspaceEmployeController controller) {
		this.controller = controller;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1263, 775);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(867, 117, 340, 374);
		contentPane.add(scrollPane);
		
		table = new JTable(controller.getModelEspaceEmploye());
		scrollPane.setViewportView(table);*/
		
		txtRecherche = new JTextField();
		txtRecherche.setBounds(563, 99, 339, 22);
		contentPane.add(txtRecherche);
		txtRecherche.setColumns(10);
		
		
		
		panelSlide = new JPanel();
		panelSlide.setBounds(441, 151, 549, 564);
		contentPane.add(panelSlide);
		panelSlide.setLayout(new BorderLayout(0, 0));
		
		panelInfoDemande = new JPanel();
		panelSlide.add(panelInfoDemande, BorderLayout.CENTER);
		panelInfoDemande.setLayout(new GridLayout(7, 1, 0, 0));
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(359, 13, 24, 702);
		contentPane.add(separator);
		addPanelInfoEmploye();
		
		initialiserSlide();
		initialiserSlideButtons();
	}
	
	public void initialiserSlide() {
		ArrayList<JLabel> list = new ArrayList<JLabel>();
		JLabel lblNomProcedure = new JLabel("", JLabel.CENTER); list.add(lblNomProcedure);
		JLabel lblCIN = new JLabel("", JLabel.CENTER); list.add(lblCIN);
		JLabel lblDateDepot = new JLabel("", JLabel.CENTER); list.add(lblDateDepot);
		JLabel lblNomEtape = new JLabel("", JLabel.CENTER); list.add(lblNomEtape);
		JLabel lblTauxCompletion = new JLabel("", JLabel.CENTER); list.add(lblTauxCompletion);
		JLabel lblDocuments = new JLabel("", JLabel.CENTER); list.add(lblDocuments);
		lblTraiter = new JLabel("", JLabel.CENTER);
		
		Demande demandeATraiter = controller.getModelEspaceEmploye().getDemandeATraiter();
		
		lblNomProcedure.setText(demandeATraiter.getProcedure().getProcedure_nom());
		lblNomProcedure.setIcon(scaleIcone("images/procedure.png", 70, 70));
		lblCIN.setText("CIN: " + demandeATraiter.getCitoyen_CIN());
		lblCIN.setIcon(scaleIcone("images/cin.png", 70, 70));
		lblDateDepot.setText("Date de Depot: " + demandeATraiter.getDate_depot().toString().substring(0, 10));
		lblDateDepot.setIcon(scaleIcone("images/datedepot.png", 70, 70));
		lblNomEtape.setText("Etape à traiter: " + demandeATraiter.getEtapeActuelle().getEtapeType().getEtape_nom());
		lblNomEtape.setIcon(scaleIcone("images/etape.png", 70, 70));
		lblTauxCompletion.setText("Taux de Completion: " + (demandeATraiter.getEtapeActuelle().getOrdre() - 1) / (double)demandeATraiter.getEtapes().size() + "%");
		lblTauxCompletion.setIcon(scaleIcone("images/tauxcompletion.png", 70, 70));
		lblDocuments.setText("Doc deposés/Requis: " + demandeATraiter.getDocuments_deposes().size() + "/" + demandeATraiter.getProcedure().getNbr_documents());
		lblDocuments.setIcon(scaleIcone("images/document.png", 70, 70));
		lblTraiter.setText("Traiter");
		lblTraiter.setIcon(scaleIcone("images/traiter.png", 60, 60));
		
		for(JLabel jlbl : list) {
			jlbl.setHorizontalTextPosition(JLabel.RIGHT);
			jlbl.setVerticalTextPosition(JLabel.CENTER);
			jlbl.setIconTextGap(50);
			panelInfoDemande.add(jlbl);
		}
		
		lblTraiter.setHorizontalTextPosition(JLabel.CENTER);
		lblTraiter.setVerticalTextPosition(JLabel.BOTTOM);
		panelInfoDemande.add(lblTraiter);
		
		addListenerToTraitementIcon();
	}
	
	private void addPanelInfoEmploye() {
		JPanel panel = new JPanel();
		panel.setBounds(41, 99, 287, 532);
		
		panel.setLayout(new GridLayout(4, 1, 0, 0));
		
		Employe employe = LoginController.getEmployeConnected();
		JLabel lblNewLabel = new JLabel("Nom : " + employe.getNom());
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prenom : " + employe.getPrenom());
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Somme : " + employe.getSomme());
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Genre : " + employe.getGenre());
		panel.add(lblNewLabel_3);
		
		
		contentPane.add(panel);
	}
	
	private void initialiserSlideButtons() {
		btnArrowLeft = new JButton(scaleIcone("images/arrowleft.png", 70, 70));
		
		btnArrowRight = new JButton(scaleIcone("images/arrowright.png", 70, 70));
		
		panelSlide.add(btnArrowLeft, BorderLayout.WEST);
		panelSlide.add(btnArrowRight, BorderLayout.EAST);
		
		addListenerToSlideButtons();
		
	}
	
	private ImageIcon scaleIcone(String lien, int width, int height) {
		ImageIcon docIcon = new ImageIcon(lien);
		Image image = docIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		docIcon = new ImageIcon(newimg);  // transform it back
		return docIcon;
	}
	
	public void addListenerToSlideButtons() {
		btnArrowLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.slideChangedLeft();
			}
		});
		
		btnArrowRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.slideChangedRight();
			}
		});
		
	}
	public void addListenerToTraitementIcon() {
		lblTraiter.addMouseListener(new MouseAdapter() {
			@Override
            public void mouseClicked(MouseEvent e) {
				ConsulterDemandeEmployeController consulterDemandeController = new ConsulterDemandeEmployeController(controller.getModelEspaceEmploye().getDemandeATraiter());
			}
		});
	}
}
