package presentation.controller;

import java.util.Date;

import Business.Employe;
import metier.Demande;
import metier.DemandeManager;
import metier.EtatEtape;
import presentation.model.DetailsEtapeEmployeModel;
import presentation.model.ConsulterDemandeEmployeModel;
import presentation.model.TraiterEtapeEmployeModel;
import presentation.view.DetailsEtapeEmployeView;
import presentation.view.ConsulterDemandeEmployeView;
import presentation.view.TraiterEtapeEmployeView;

public class ConsulterDemandeEmployeController {
	private ConsulterDemandeEmployeModel modelTraiterDemande;
	private ConsulterDemandeEmployeView viewTraiterDemande;
	
	private DetailsEtapeEmployeView viewDetailsEtape;
	private DetailsEtapeEmployeModel modelDetailsEtape;
	
	private TraiterEtapeEmployeView viewTraiterEtape;
	private TraiterEtapeEmployeModel modelTraiterEtape;
	
	public ConsulterDemandeEmployeController(Demande demande) {
		viewTraiterDemande = new ConsulterDemandeEmployeView(this);
		modelTraiterDemande = new ConsulterDemandeEmployeModel(demande);
		viewTraiterDemande.addDocumentsIcons(modelTraiterDemande.getDemandeATraiter().getDocuments_deposes());
		viewTraiterDemande.addEtapesTimeline(demande);
		viewTraiterDemande.setVisible(true);
	}
	
	public void showDetailsEtape() {
		viewDetailsEtape = new DetailsEtapeEmployeView(this);
		viewDetailsEtape.setVisible(true);
	}
	
	public void showTraiterEtape() {
		viewTraiterEtape = new TraiterEtapeEmployeView(this);
		viewTraiterEtape.setVisible(true);
	}


	public void setModelTraiterDemande(ConsulterDemandeEmployeModel modelTraiterDemande) {
		this.modelTraiterDemande = modelTraiterDemande;
	}


	public void setModelTraiterEtape(TraiterEtapeEmployeModel modelTraiterEtape) {
		this.modelTraiterEtape = modelTraiterEtape;
	}

	public void setModelDetailsEtape(DetailsEtapeEmployeModel modelDetailsEtape) {
		this.modelDetailsEtape = modelDetailsEtape;
	}
	
	public ConsulterDemandeEmployeModel getModelTraiterDemande() {
		return modelTraiterDemande;
	}

	public DetailsEtapeEmployeModel getModelDetailsEtape() {
		return modelDetailsEtape;
	}

	public TraiterEtapeEmployeModel getModelTraiterEtape() {
		return modelTraiterEtape;
	}

	public static void main(String[] args) {
		ConsulterDemandeEmployeController controller = new ConsulterDemandeEmployeController(DemandeManager.get(1));
	}

	public void traiterEtape(EtatEtape etatEtape, String rapport) {
		if(rapport.equals("")) {
			// show window error
			return;
		}
		modelTraiterEtape.setEtapeEtat(etatEtape);
		modelTraiterEtape.setEtapeRapport(rapport);
		modelTraiterEtape.setEtapeDateTraitement(new Date());
	}



	
}
