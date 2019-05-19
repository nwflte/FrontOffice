package dao;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import metier.BackOfficeData;
import metier.Demande;
import metier.DocumentDemande;

public class DocumentDemandeDAO {
	static final String nomcollection = "documents";
	private static MongoCollection<Document> collection;
	
	public static ArrayList<DocumentDemande> getAll() {
		ArrayList<DocumentDemande> list = new ArrayList<DocumentDemande>();
		
		collection = ConnectionMongo.getDatabase().getCollection(nomcollection);
		MongoCursor<Document> cursor = collection.find().iterator();
		while(cursor.hasNext()) {
			list.add(resultToDocumentDemande(cursor.next()));
		}
		return list;
	}
	
	public static DocumentDemande get(int id) {
		collection = ConnectionMongo.getDatabase().getCollection(nomcollection);
		Document d = collection.find(Filters.eq("id", id)).first();
		return resultToDocumentDemande(d);
	}
	
	
	public static DocumentDemande resultToDocumentDemande(Document o) {
		DocumentDemande docDemande = new DocumentDemande();
		
		docDemande.setId(o.getInteger("id"));
		docDemande.setDate_depot(o.getDate("date_depot"));
		docDemande.setLien(o.getString("lien"));
		docDemande.setDocumentType(BackOfficeData.getDocument(o.getInteger("document_id")));		
		docDemande.setDemande(DemandeDAO.get(o.getInteger("demande_id")));
		return docDemande;
	}
}
