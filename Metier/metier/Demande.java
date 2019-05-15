package metier;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Demande {
	private int id;
	private Procedure procedure;
	private Citoyen citoyen;
	private EtatDemande etat;
	private String jeton;
	private boolean archived;
	private LocalDateTime date_depot;
	private LocalDateTime date_fin;
	private ArrayList<Document> documents_deposes;
	private ArrayList<DemandeEtape> etapes;
}
