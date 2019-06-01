package ui;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import helpers.Helpers;
import metier.DocumentDemande;
import metier.EtapeDemande;
import metier.EtatEtape;

public class EtapeBox extends JLabel{
	private ImageIcon docIcon;
	private EtapeDemande etape;
	private final int height = 155;
	private final int width = 155;

	public EtapeBox(EtapeDemande etape) {
		super();
		this.etape = etape;
		Toolkit t= Toolkit.getDefaultToolkit();  
		switch(etape.getEtat()) {
			case ACCEPTEE :
				docIcon = new ImageIcon("images/etapeacceptee.png");
				break;
			case ATTENTE :
				docIcon = new ImageIcon("images/etapeattente.png");
				break;
			case MISEAJOUR :
				docIcon = new ImageIcon("images/etapemiseajour.png");
				break;
			case REFUSEE :
				docIcon = new ImageIcon("images/etapeattente.png");
				break;
			case REJETEE :
				docIcon = new ImageIcon("images/etapeattente.png");
				break;
			default :
				docIcon = new ImageIcon("pdf.png");
				break;
		}
		Image image = docIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		docIcon = new ImageIcon(newimg);  // transform it back
		super.setIcon(docIcon);
		super.setText(etape.getEtapeType().getEtape_nom());
		super.setHorizontalTextPosition(JLabel.CENTER);
		super.setVerticalTextPosition(JLabel.BOTTOM);
	}

	public EtapeDemande getEtape() {
		return etape;
	}
	
	
}
