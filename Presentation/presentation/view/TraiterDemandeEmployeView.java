package presentation.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.glass.ui.Cursor;

import helpers.Helpers;
import metier.Demande;
import metier.DocumentDemande;
import metier.EtapeDemande;
import metier.EtapeDemandeManager;
import ui.ArrowEtape;
import ui.DocumentBox;
import ui.DocumentType;
import ui.EtapeBox;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;

public class TraiterDemandeEmployeView extends JFrame {

	private JPanel contentPane;
	private JPanel panelDoc;
	private JPanel panelEtape;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TraiterDemandeEmployeView frame = new TraiterDemandeEmployeView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TraiterDemandeEmployeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1225, 778);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelDoc = new JPanel();
		panelDoc.setBounds(12, 13, 1183, 208);
		contentPane.add(panelDoc);
		panelDoc.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//DocumentBox lblNewLabel = new DocumentBox(DocumentType.PDF, "Nom du doc");
		//panelDoc.add(lblNewLabel);
		
		
		panelEtape = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelEtape.getLayout();
		flowLayout.setHgap(0);
		panelEtape.setBounds(12, 266, 1183, 208);
		contentPane.add(panelEtape);
	}
	
	public void addDocumentsIcons(ArrayList<DocumentDemande> listeDocDeposes) {
		for(DocumentDemande d : listeDocDeposes) {
			DocumentBox newDocIcon = new DocumentBox(d);
			panelDoc.add(newDocIcon);
			addListenerToDocumentBox(newDocIcon);
		}
	}
	
	public void addEtapesTimeline(Demande demande) {
		for(int i = 0; i < demande.getEtapes().size(); i++) {
			EtapeBox eb = new EtapeBox(demande.getEtapes().get(i));
			panelEtape.add(eb);
			addListenerToEtapeBox(eb);
			if(i != demande.getEtapes().size() -1 ) panelEtape.add(new ArrowEtape());
		}
	}
	
	private void addListenerToDocumentBox(DocumentBox db) {
		db.addMouseListener(new MouseAdapter() {
			@Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Yay you clicked me");
            }
		});
	}
	
	private void addListenerToEtapeBox(EtapeBox eb) {
		eb.addMouseListener(new MouseAdapter() {
			@Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Yay you clicked me" + eb.getEtape().getEtapeType().getEtape_nom());
            }
		});
	}
}
