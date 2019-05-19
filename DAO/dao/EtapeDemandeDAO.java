package dao;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import metier.BackOfficeData;
import metier.DocumentDemande;
import metier.EtapeDemande;
import metier.EtatEtape;

public class EtapeDemandeDAO {
	static final String nomcollection = "etapes";
	private static MongoCollection<Document> collection;
	
	public static ArrayList<EtapeDemande> getAll() {
		ArrayList<EtapeDemande> list = new ArrayList<EtapeDemande>();
		
		collection = ConnectionMongo.getDatabase().getCollection(nomcollection);
		MongoCursor<Document> cursor = collection.find().iterator();
		while(cursor.hasNext()) {
			list.add(resultToEtapeDemande(cursor.next()));
		}
		return list;
	}
	
	public static EtapeDemande get(int id) {
		collection = ConnectionMongo.getDatabase().getCollection(nomcollection);
		Document d = collection.find(Filters.eq("id", id)).first();
		return resultToEtapeDemande(d);
	}
	
	public static EtapeDemande resultToEtapeDemande(Document o) {
		EtapeDemande etapeDemande = new EtapeDemande();
		
		etapeDemande.setId(o.getInteger("id"));
		etapeDemande.setOrdre(o.getInteger("ordre"));
		etapeDemande.setEtapeType(BackOfficeData.getEtape(o.getInteger("etape_id")));
		etapeDemande.setDemande(DemandeDAO.get(o.getInteger("demande_id")));
		etapeDemande.setEtat(EtatEtape.valueOf(o.getString("etat")));
		etapeDemande.setRapport(o.getString("rapport"));
		etapeDemande.setDate_traitement(o.getDate("date_traitement"));
		return etapeDemande;
	}
}
