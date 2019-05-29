package presentation.controller;

import java.util.ArrayList;

import Business.Etape;
import Business.GestionEmploye;
import Business.GestionEtape;
import metier.Demande;
import metier.DemandeManager;
import presentation.model.DemandeEmployeTableModel;
import presentation.view.EspaceEmployeView;

public class EspaceEmployeController {
	EspaceEmployeView fenetre;
	DemandeEmployeTableModel model;
	
	public EspaceEmployeController() {
		initModel();
		fenetre = new EspaceEmployeView(model);
		//fenetre.pack();
		fenetre.validate();
		fenetre.setVisible(true);
	}
	
	private void initModel() {
		//ArrayList<Etape> etapesDeEmploye = GestionEtape.getEtapesDeEmploye(LoginController.getEmployeConnected()); 
		ArrayList<Etape> etapesDeEmploye = GestionEtape.getEtapesDeEmploye(GestionEmploye.getEmploye(1)); 
		
		ArrayList<Demande> allDemandesNotArchived = DemandeManager.getAllNotArchived(null);
		ArrayList<Demande> allDemandesNotFinishedArchived = DemandeManager.getAllNotFinished(allDemandesNotArchived);
		
		ArrayList<Demande> allDemandesToHandle = new ArrayList<Demande>();
		
		for(Etape e : etapesDeEmploye) {
			allDemandesToHandle.addAll(DemandeManager.getAllDemandesAtEtape(allDemandesNotFinishedArchived, e));
		}
		
		model = new DemandeEmployeTableModel(allDemandesToHandle);
	}
	
	public static void main(String[] args) {
		EspaceEmployeController controller = new EspaceEmployeController();
	}
}
