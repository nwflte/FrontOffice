package metier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import Business.Document;
import Business.Etape;
import dao.DemandeDAO;

public class DemandeManager {
		private static ArrayList<Demande> listeDemandes;
		
		static {
			listeDemandes = DemandeDAO.getAll();
		}
		
		public static ArrayList<Demande> getAll() {
			return listeDemandes;
		}
		
		public static Demande get(int id) {
			Optional<Demande> demande = listeDemandes.stream().filter(et -> et.getId() == id).findAny();
			return demande.isPresent() == true ? demande.get() : null;
		}
		
		public static ArrayList<Demande> getAllNotArchived(ArrayList<Demande> listToFilter) {
			if(listToFilter == null) listToFilter = listeDemandes;
			return new ArrayList<Demande>(listToFilter.stream().filter(et -> et.isArchived() == false).collect(Collectors.toList()));
		}
		
		
		public static  ArrayList<Demande> getAllDemandesAtEtape(ArrayList<Demande> listToFilter, Etape etape) {
			if(listToFilter == null) listToFilter = listeDemandes;
			ArrayList<Demande> liste = new ArrayList<Demande>();
			ArrayList<EtapeDemande> etapesDeType = EtapeDemandeManager.getAllOfEtapeType(etape);
			for(EtapeDemande ed : etapesDeType) {
				for(Demande d : listToFilter) {
					if(ed.getDemande().getId() == d.getId())
						liste.add(d);
				}
			}
				
			return liste;
		}
		
		public static ArrayList<Demande> getAllNotFinished(ArrayList<Demande> listToFilter) {
			if(listToFilter == null) listToFilter = listeDemandes;
			return new ArrayList<Demande>(listToFilter.stream().filter(et -> et.getEtat() != EtatDemande.TERMINEE).collect(Collectors.toList()));
		
		}
		
		public static boolean insert(Demande demande) {
			if(!isValid(demande)) return false;
			Comparator<Demande> comparator = Comparator.comparing( Demande::getId );
			Optional<Demande> maxObject = listeDemandes.stream().max(comparator);
			//Si la demande est la premiere dans la BD, donner ID = 1, sinon auto increment ID.
			if(!maxObject.isPresent()) demande.setId(1);
			else demande.setId(maxObject.get().getId()+1);
			return DemandeDAO.insert(demande);
		}
		
		public static boolean update(Demande demande) {
			if(!isValid(demande)) return false;
			return DemandeDAO.update(demande);
		}
		
		private static boolean isValid(Demande demande) {
			if(demande == null || demande.getProcedure() == null || demande.getDate_depot() == null || demande.getDate_fin() == null)
				return false;
			return true;
		}
}
