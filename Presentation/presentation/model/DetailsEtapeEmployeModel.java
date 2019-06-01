package presentation.model;

import Business.Employe;
import metier.EtapeDemande;

public class DetailsEtapeEmployeModel {
	private EtapeDemande etape;
	
	
	public DetailsEtapeEmployeModel(EtapeDemande etape) {
		super();
		this.etape = etape;
	}

	public void setEtape(EtapeDemande etape) {
		this.etape = etape;
	}
	
	public String getEtapeRapport() {
		return etape.getRapport();
	}

	public String getEtapeEtat() {
		return etape.getEtat().name();
	}

	public Employe getEtapeEmployeRespo() {
		return etape.getEtapeType().getEmploye();
	}

	public String getEtapeNom() {
		return etape.getEtapeType().getEtape_nom();
	}
	
}
