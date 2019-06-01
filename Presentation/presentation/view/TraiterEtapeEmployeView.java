package presentation.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import presentation.controller.ConsulterDemandeEmployeController;
import presentation.model.DetailsEtapeEmployeModel;
import presentation.model.TraiterEtapeEmployeModel;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import metier.EtatEtape;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TraiterEtapeEmployeView extends JFrame {

	private JPanel contentPane;
	private ConsulterDemandeEmployeController controller;
	private JLabel boxAccepter;
	private JLabel boxRefuser;
	private JLabel boxMettreAJour;
	private JLabel boxRejeter;
	private JPanel panel;
	private JTextPane textPaneRapport;

	/**
	 * Create the frame.
	 * @param traiterDemandeEmployeController 
	 */
	public TraiterEtapeEmployeView(ConsulterDemandeEmployeController controller) {
		this.controller = controller;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1036, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInfoEtape = new JLabel(controller.getModelTraiterEtape().getEtapeNom() + ", Ordre : " + controller.getModelTraiterEtape().getEtapeOrdre());
		lblInfoEtape.setBounds(438, 45, 235, 30);
		contentPane.add(lblInfoEtape);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(46, 355, 932, 211);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		
		textPaneRapport = new JTextPane();
		textPaneRapport.setBounds(284, 150, 694, 160);
		contentPane.add(textPaneRapport);
		
		JLabel lblRapport = new JLabel("Veuiller rédiger un rapport : ");
		lblRapport.setLabelFor(textPaneRapport);
		lblRapport.setBounds(89, 216, 56, 16);
		contentPane.add(lblRapport);
		
		initialiserIcones();
	}
	
	private void initialiserIcones() {
		ArrayList<JLabel> listLabels = new ArrayList<JLabel>();
		boxAccepter = new JLabel(); boxRefuser = new JLabel(); boxMettreAJour = new JLabel(); boxRejeter = new JLabel();
		listLabels.add(boxAccepter);  listLabels.add(boxRefuser);  listLabels.add(boxMettreAJour); listLabels.add(boxRejeter); 
		for(JLabel j : listLabels) {
			panel.add(j);
			j.setHorizontalTextPosition(JLabel.CENTER);
			j.setVerticalTextPosition(JLabel.BOTTOM);
		}
				
		
		boxAccepter.setIcon(transformIcone("images/accepter.png"));
		boxAccepter.setText("ACCEPTER");
		boxRefuser.setIcon(transformIcone("images/refuser.png"));
		boxRefuser.setText("REFUSER");
		boxRejeter.setIcon(transformIcone("images/rejeter.png"));
		boxRejeter.setText("REJETER");
		boxMettreAJour.setIcon(transformIcone("images/miseajour.png"));
		boxMettreAJour.setText("METTRE A JOUR");
		
	}
	
	private ImageIcon transformIcone(String lienImg) {
		ImageIcon docIcon = new ImageIcon(lienImg);
		Image image = docIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(155, 155,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		docIcon = new ImageIcon(newimg);  // transform it back
		return docIcon;
	}
	
	private void addListenerToTraitementIcones(JLabel jlbl) {
		jlbl.addMouseListener(new MouseAdapter() {
			@Override
            public void mouseClicked(MouseEvent e) {
                controller.traiterEtape(EtatEtape.valueOf(jlbl.getText()), textPaneRapport.getText());
            }
		});
	}
}
