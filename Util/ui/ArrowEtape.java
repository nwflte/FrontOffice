package ui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ArrowEtape extends JLabel {
	
	public ArrowEtape() {
		super();
		ImageIcon arr = new ImageIcon("images\\arrow.png");
		Image image = arr.getImage(); // transform it 
		Image newimg = image.getScaledInstance(240, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		arr = new ImageIcon(newimg);  // transform it back
		super.setIcon(arr);
		super.setSize(786, 61);
	}
}
