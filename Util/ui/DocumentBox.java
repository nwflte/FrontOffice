package ui;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import Business.Document;
import helpers.Helpers;
import metier.DocumentDemande;

public class DocumentBox extends JLabel{

	private static final long serialVersionUID = 1L;
	private ImageIcon docIcon;
	private String nom;
	DocumentType type;
	private DocumentDemande document;
	private final int height = 155;
	private final int width = 155;

	public DocumentBox(DocumentDemande document) {
		super();
		this.document = document;
		type = DocumentType.valueOf(Helpers.getExtension(document.getLien()));
		nom = document.getDocumentType().getDocument_nom();
		Toolkit t= Toolkit.getDefaultToolkit();  
		switch(type) {
			case PDF :
				docIcon = new ImageIcon("images/pdf.png");
				break;
			case WORD :
				docIcon = new ImageIcon("images/word.png");
				break;
			case IMAGE :
				docIcon = new ImageIcon("images/image.png");
				break;
			default :
				docIcon = new ImageIcon("images/autre.png");
				break;
		}
		Image image = docIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		docIcon = new ImageIcon(newimg);  // transform it back
		super.setIcon(docIcon);
		super.setText(nom);
		super.setHorizontalTextPosition(JLabel.CENTER);
		super.setVerticalTextPosition(JLabel.BOTTOM);
		 Border border = LineBorder.createGrayLineBorder();
		 super.setBorder(border);
	}

	public DocumentDemande getDocument() {
		return document;
	}
	
	
}
