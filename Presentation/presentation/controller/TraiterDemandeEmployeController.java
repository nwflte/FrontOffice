package presentation.controller;

import metier.Demande;
import metier.DemandeManager;
import presentation.model.TraiterDemandeEmployeModel;
import presentation.view.TraiterDemandeEmployeView;

public class TraiterDemandeEmployeController {
	private TraiterDemandeEmployeModel model;
	private TraiterDemandeEmployeView view;
	
	public TraiterDemandeEmployeController(Demande demande) {
		view = new TraiterDemandeEmployeView();
		model = new TraiterDemandeEmployeModel(demande);
		view.addDocumentsIcons(model.getDemandeATraiter().getDocuments_deposes());
		view.addEtapesTimeline(demande);
		view.setVisible(true);
	}
	
	public static void main(String[] args) {
		TraiterDemandeEmployeController controller = new TraiterDemandeEmployeController(DemandeManager.get(1));
	}
}
