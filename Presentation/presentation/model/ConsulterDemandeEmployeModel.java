package presentation.model;

import metier.Demande;

public class ConsulterDemandeEmployeModel {
	private Demande demandeATraiter;
	
	public ConsulterDemandeEmployeModel(Demande demande) {
		demandeATraiter = demande;
	}

	public Demande getDemandeATraiter() {
		return demandeATraiter;
	}
	
}
