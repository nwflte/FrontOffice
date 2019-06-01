package presentation.model;

import java.util.Date;

import Business.Employe;
import metier.EtapeDemande;
import metier.EtatEtape;

public class TraiterEtapeEmployeModel {
	EtapeDemande etape;

	public TraiterEtapeEmployeModel(EtapeDemande etape) {
		this.etape = etape;
	}

	public EtapeDemande getEtapeToTraiter() {
		return etape;
	}

	public void setEtapeToTraiter(EtapeDemande etapeToTraiter) {
		this.etape = etapeToTraiter;
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
	
	public int getEtapeOrdre( ) {
		return etape.getOrdre();
	}
	
	public void setEtapeRapport(String rapport) {
		etape.setRapport(rapport);
	}

	public void setEtapeEtat(EtatEtape etat) {
		etape.setEtat(etat);
	}
	
	public void setEtapeDateTraitement(Date date_traitement) {
		etape.setDate_traitement(date_traitement);
	}
}
