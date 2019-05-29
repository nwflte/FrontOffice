package metier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import dao.DemandeDAO;
import dao.DocumentDemandeDAO;

public class DocumentDemandeManager {
	public static ArrayList<DocumentDemande> listeDocuments;
	
	static {
		listeDocuments = DocumentDemandeDAO.getAll();
	}
	
	public static ArrayList<DocumentDemande> getAll() {
		return listeDocuments;
	}
	
	public static DocumentDemande get(int id) {
		Optional<DocumentDemande> document = listeDocuments.stream().filter(et -> et.getId() == id).findAny();
		return document.isPresent() == true ? document.get() : null;
	}

	public static ArrayList<DocumentDemande> getAllOfDemande(Demande demande) {
			return new ArrayList<DocumentDemande>(listeDocuments.stream().filter(et -> et.getDemande().getId() == demande.getId()).collect(Collectors.toList()));
		
	}
	
	public static boolean insert(DocumentDemande docDem) {
		if(!isValid(docDem)) return false;
		Comparator<DocumentDemande> comparator = Comparator.comparing( DocumentDemande::getId );
		Optional<DocumentDemande> maxObject = listeDocuments.stream().max(comparator);
		//Si la document est le premier dans la BD, donner ID = 1, sinon auto increment ID.
		if(!maxObject.isPresent()) docDem.setId(1);
		else docDem.setId(maxObject.get().getId()+1);
		return DocumentDemandeDAO.insert(docDem);
	}
	
	public static boolean update(DocumentDemande docDem) {
		if(!isValid(docDem)) return false;
		return DocumentDemandeDAO.update(docDem);
	}
	
	private static boolean isValid(DocumentDemande docDem) {
		if(docDem == null || docDem.getDate_depot() == null || docDem.getDemande() == null || docDem.getDocumentType() == null)
			return false;
		return true;
	}
}
