package dao;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import Business.Procedure;
import metier.BackOfficeData;
import metier.Demande;
import metier.DocumentDemande;
import metier.EtatDemande;

public class DemandeDAO {
	
	static final String nomcollection = "demandes";
	private static MongoCollection<Document> collection;
	
	public static ArrayList<Demande> getAll() {
		ArrayList<Demande> list = new ArrayList<Demande>();
		
		collection = ConnectionMongo.getDatabase().getCollection(nomcollection);
		MongoCursor<Document> cursor = collection.find().iterator();
		while(cursor.hasNext()) {
			list.add(resultToDemande(cursor.next()));
		}
		return list;
	}
	
	public static Demande get(int id) {
		collection = ConnectionMongo.getDatabase().getCollection(nomcollection);
		Document d = collection.find(Filters.eq("id", id)).first();
		return resultToDemande(d);
	}
	
	public static boolean insert(Demande demande) {
		if(demande == null) return false;
		
		Document doc = demandeToMongoDocument(demande);		
		collection = ConnectionMongo.getDatabase().getCollection(nomcollection);
		collection.insertOne(doc);
		return true;
	}
	
	public static boolean update(Demande demande) {
		if(demande == null) return false;
		collection = ConnectionMongo.getDatabase().getCollection(nomcollection);
		
		UpdateResult result = collection.updateOne(Filters.eq("id", demande.getId()), demandeToMongoDocument(demande));
		if(result.getModifiedCount() > 0) return true;
		return false;
	}
	
	public static Demande resultToDemande(Document o) {
		Demande demande = new Demande();
		
		demande.setId(o.getInteger("id"));
		demande.setCitoyen_CIN(o.getString("citoyen_CIN"));
		demande.setArchived(o.getBoolean("archived"));
		demande.setEtat(EtatDemande.valueOf(o.getString("etat").toUpperCase()));
		demande.setJeton(o.getString("jeton"));
		demande.setDate_depot(o.getDate("date_depot"));
		demande.setDate_fin(o.getDate("date_fin"));
		demande.setProcedure(BackOfficeData.getProcedure(o.getInteger("procedure_id")));
		return demande;
	}
	
	public static Document demandeToMongoDocument(Demande demande) {
		Document doc = new Document();
		
		doc.put("id", demande.getId());
		doc.put("procedure_id", demande.getProcedure().getProcedure_id());
		doc.put("citoyen_CIN", demande.getCitoyen_CIN());
		doc.put("jeton", demande.getJeton());
		doc.put("archived", demande.isArchived());
		doc.put("date_depot", demande.getDate_depot());
		doc.put("date_fin", demande.getDate_fin());
		doc.put("etat", demande.getEtat());
		
		return doc;
	}
}
