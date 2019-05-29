package presentation.model;

import metier.Demande;

public class TraiterDemandeEmployeModel {
	private Demande demandeATraiter;
	
	public TraiterDemandeEmployeModel(Demande demande) {
		demandeATraiter = demande;
	}

	public Demande getDemandeATraiter() {
		return demandeATraiter;
	}
	
}
