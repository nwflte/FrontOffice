package presentation.controller;

import java.util.ArrayList;

import Business.Etape;
import Business.GestionEmploye;
import Business.GestionEtape;
import metier.Demande;
import metier.DemandeManager;
import presentation.model.EspaceEmployeTableModel;
import presentation.view.EspaceEmployeView;

public class EspaceEmployeController {
	EspaceEmployeView viewEspaceEmploye;
	EspaceEmployeTableModel modelEspaceEmploye;
	
	public EspaceEmployeController() {
		initModel();
	}
	
	private void initModel() {
		ArrayList<Etape> etapesDeEmploye = GestionEtape.getEtapesDeEmploye(LoginController.getEmployeConnected()); 
		//ArrayList<Etape> etapesDeEmploye = GestionEtape.getEtapesDeEmploye(GestionEmploye.getEmploye(1)); 
		
		ArrayList<Demande> allDemandesNotArchived = DemandeManager.getAllNotArchived(null);
		ArrayList<Demande> allDemandesNotFinishedArchived = DemandeManager.getAllNotFinished(allDemandesNotArchived);
		
		ArrayList<Demande> allDemandesToHandle = new ArrayList<Demande>();
		
		for(Etape e : etapesDeEmploye) {
			allDemandesToHandle.addAll(DemandeManager.getAllDemandesAtEtape(allDemandesNotFinishedArchived, e));
		}
		
		modelEspaceEmploye = new EspaceEmployeTableModel(allDemandesToHandle);
	}
	
	
	public void showEspaceEmploye() {
		viewEspaceEmploye = new EspaceEmployeView(this);
		viewEspaceEmploye.setVisible(true);
	}

	public EspaceEmployeTableModel getModelEspaceEmploye() {
		return modelEspaceEmploye;
	}
	
	public void slideChangedLeft() {
		int indexActuel = modelEspaceEmploye.getIndexDemandeATraiter();
		if( indexActuel == 0)
			return;
		modelEspaceEmploye.setIndexDemandeATraiter(indexActuel-1);
		modelEspaceEmploye.setDemandeATraiter(modelEspaceEmploye.getListeDemandes().get(indexActuel-1));
		viewEspaceEmploye.initialiserSlide();
	}
	
	public void slideChangedRight() {
		int indexActuel = modelEspaceEmploye.getIndexDemandeATraiter();
		if( indexActuel == modelEspaceEmploye.getListeDemandes().size() -1 )
			return;
		modelEspaceEmploye.setIndexDemandeATraiter(indexActuel+1);
		modelEspaceEmploye.setDemandeATraiter(modelEspaceEmploye.getListeDemandes().get(indexActuel+1));
		viewEspaceEmploye.initialiserSlide();
	}
	
	public static void main(String[] args) {
		EspaceEmployeController controller = new EspaceEmployeController();
		controller.showEspaceEmploye();
	}
}
