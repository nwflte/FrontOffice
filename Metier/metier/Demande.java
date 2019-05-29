package metier;

import java.util.ArrayList;
import java.util.Date;

import Business.Procedure;

public class Demande {
	private int id;
	private Procedure procedure;
	private String citoyen_CIN;
	private EtatDemande etat;
	private String jeton;
	private boolean archived;
	private Date date_depot;
	private Date date_fin;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Procedure getProcedure() {
		return procedure;
	}
	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}
	public String getCitoyen_CIN() {
		return citoyen_CIN;
	}
	public void setCitoyen_CIN(String citoyen_CIN) {
		this.citoyen_CIN = citoyen_CIN;
	}
	public EtatDemande getEtat() {
		return etat;
	}
	public void setEtat(EtatDemande etat) {
		this.etat = etat;
	}
	public String getJeton() {
		return jeton;
	}
	public void setJeton(String jeton) {
		this.jeton = jeton;
	}
	public boolean isArchived() {
		return archived;
	}
	public void setArchived(boolean archived) {
		this.archived = archived;
	}
	public Date getDate_depot() {
		return date_depot;
	}
	public void setDate_depot(Date date_depot) {
		this.date_depot = date_depot;
	}
	public Date getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}
	public ArrayList<DocumentDemande> getDocuments_deposes() {
		return DocumentDemandeManager.getAllOfDemande(this);
	}

	public ArrayList<EtapeDemande> getEtapes() {
		return EtapeDemandeManager.getAllOfDemande(this);
	}

	public EtapeDemande getEtapeActuelle() {
		return getEtapes().stream().filter(et -> et.isActuelle() == true).findFirst().get();
	}
}
