package dao;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import metier.BackOfficeData;
import metier.Demande;
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
	
	public static boolean insert(EtapeDemande etape) {
		if(etape == null) return false;
		
		Document doc = etapeDemandeToMongoDoc(etape);
		collection = ConnectionMongo.getDatabase().getCollection(nomcollection);
		collection.insertOne(doc);
		return true;
	}
	
	public static boolean update(EtapeDemande etape) {
		if(etape == null) return false;
		collection = ConnectionMongo.getDatabase().getCollection(nomcollection);
		
		UpdateResult result = collection.updateOne(Filters.eq("id", etape.getId()), etapeDemandeToMongoDoc(etape));
		if(result.getModifiedCount() > 0) return true;
		return false;
	}
	
	private static EtapeDemande resultToEtapeDemande(Document o) {
		EtapeDemande etapeDemande = new EtapeDemande();
		
		etapeDemande.setId(o.getInteger("id"));
		etapeDemande.setOrdre(o.getInteger("ordre"));
		etapeDemande.setEtapeType(BackOfficeData.getEtape(o.getInteger("etape_id")));
		etapeDemande.setDemande(DemandeDAO.get(o.getInteger("demande_id")));
		etapeDemande.setEtat(EtatEtape.valueOf(o.getString("etat")));
		etapeDemande.setRapport(o.getString("rapport"));
		etapeDemande.setDate_traitement(o.getDate("date_traitement"));
		etapeDemande.setActuelle(o.getBoolean("actuelle"));
		return etapeDemande;
	}
	
	private static Document etapeDemandeToMongoDoc(EtapeDemande etape) {
		Document doc = new Document();
		
		doc.put("id", etape.getId());
		doc.put("etape_id", etape.getEtapeType().getEtape_id());
		doc.put("ordre", etape.getOrdre());
		doc.put("demande_id", etape.getDemande().getId());
		doc.put("etat", etape.getEtapeType());
		doc.put("date_traitement", etape.getDate_traitement());
		doc.put("rapport", etape.getRapport());
		doc.put("actuelle", etape.isActuelle());
		
		return doc;
	}
}
