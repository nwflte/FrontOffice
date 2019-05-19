package metier;

import java.util.Date;

import Business.Document;

public class DocumentDemande {
	private int id;
	private Document documentType;
	private Demande demande;
	private Date date_depot;
	private String lien;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Document getDocumentType() {
		return documentType;
	}
	public void setDocumentType(Document documentType) {
		this.documentType = documentType;
	}
	public Demande getDemande() {
		return demande;
	}
	public void setDemande(Demande demande) {
		this.demande = demande;
	}
	public Date getDate_depot() {
		return date_depot;
	}
	public void setDate_depot(Date date_depot) {
		this.date_depot = date_depot;
	}
	public String getLien() {
		return lien;
	}
	public void setLien(String lien) {
		this.lien = lien;
	}
	
	
}
