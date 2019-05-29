package metier;

import java.util.Date;

import Business.Etape;

public class EtapeDemande {
	private int id;
	private Etape etapeType;
	private int ordre;
	private Demande demande;
	private EtatEtape etat;
	private Date date_traitement;
	private String rapport;
	private boolean actuelle;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Etape getEtapeType() {
		return etapeType;
	}
	public void setEtapeType(Etape etapeType) {
		this.etapeType = etapeType;
	}
	public int getOrdre() {
		return ordre;
	}
	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}
	public Demande getDemande() {
		return demande;
	}
	public void setDemande(Demande demande) {
		this.demande = demande;
	}
	public EtatEtape getEtat() {
		return etat;
	}
	public void setEtat(EtatEtape etat) {
		this.etat = etat;
	}
	public Date getDate_traitement() {
		return date_traitement;
	}
	public void setDate_traitement(Date date_traitement) {
		this.date_traitement = date_traitement;
	}
	public String getRapport() {
		return rapport;
	}
	public void setRapport(String rapport) {
		this.rapport = rapport;
	}
	public boolean isActuelle() {
		return actuelle;
	}
	public void setActuelle(boolean actuelle) {
		this.actuelle = actuelle;
	}
	
	
}
