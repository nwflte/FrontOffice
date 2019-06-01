package metier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import Business.Etape;
import dao.DemandeDAO;
import dao.EtapeDemandeDAO;
import dao.EtapeDemandeDAO;

public class EtapeDemandeManager {
	private static ArrayList<EtapeDemande> listeEtapes;
	
	static {
		listeEtapes = EtapeDemandeDAO.getAll();
	}
	
	public static ArrayList<EtapeDemande> getAll() {
		return listeEtapes;
	}
	
	public static EtapeDemande get(int id) {
		Optional<EtapeDemande> etape = listeEtapes.stream().filter(et -> et.getId() == id).findAny();
		return etape.isPresent() == true ? etape.get() : null;
	}
	
	public static ArrayList<EtapeDemande> getAllOfEtapeType(Etape etapeType) {
		return new ArrayList<EtapeDemande>(listeEtapes.stream().filter(et -> et.getEtapeType().getEtape_id() == etapeType.getEtape_id()).collect(Collectors.toList()));
	}
	
	public static ArrayList<EtapeDemande> getAllOfDemande(Demande demande) {
		//int nbrEtapes = demande.getProcedure().getNbr_etapes();
		Comparator<EtapeDemande> comparator = Comparator.comparing( EtapeDemande::getOrdre );
		return new ArrayList<EtapeDemande>(listeEtapes.stream().filter(et -> et.getDemande().getId() == demande.getId()).sorted(comparator).collect(Collectors.toList()));
	}
	
	public static boolean insert(EtapeDemande etape) {
		if(!isValid(etape)) return false;
		Comparator<EtapeDemande> comparator = Comparator.comparing( EtapeDemande::getId );
		Optional<EtapeDemande> maxObject = listeEtapes.stream().max(comparator);
		//Si l'etape est la premiere dans la BD, donner ID = 1, sinon auto increment ID.
		if(!maxObject.isPresent()) etape.setId(1);
		else etape.setId(maxObject.get().getId()+1);
		return EtapeDemandeDAO.insert(etape);
	}
	
	public static boolean update(EtapeDemande etape) {
		if(!isValid(etape)) return false;
		if(etape.getEtat() == EtatEtape.REJETEE) {
			etape.getDemande().setEtat(EtatDemande.REJETEE);
		}
		
		if(etape.getEtat() == EtatEtape.REFUSEE ) {
			etape.getDemande().getEtapes().get(etape.getOrdre()-2).setActuelle(true);
			etape.getDemande().getEtapes().get(etape.getOrdre()-2).setEtat(EtatEtape.ATTENTE);
			for(EtapeDemande e : etape.getDemande().getEtapes()) {
				if(e.getOrdre() != etape.getOrdre()-1)
					e.setActuelle(false);
			}
		}
		
		if(etape.getEtat() == EtatEtape.ACCEPTEE) {
			etape.getDemande().getEtapes().get(etape.getOrdre()).setActuelle(true);
			for(EtapeDemande e : etape.getDemande().getEtapes()) {
				if(e.getOrdre() != etape.getOrdre()+1)
					e.setActuelle(false);
			}
		}
		
		DemandeManager.update(etape.getDemande());
		return EtapeDemandeDAO.update(etape);
	}
	
	private static boolean isValid(EtapeDemande etape) {
		if(etape == null || etape.getDate_traitement() == null || etape.getDemande() == null || etape.getEtapeType() == null)
			return false;
		return true;
	}
}
