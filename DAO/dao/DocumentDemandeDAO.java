package dao;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

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
	
	public static boolean insert(DocumentDemande docDem) {
		if(docDem == null) return false;
		
		Document doc = documentDemandeToMongoDoc(docDem);		
		collection = ConnectionMongo.getDatabase().getCollection(nomcollection);
		collection.insertOne(doc);
		return true;
	}
	
	public static boolean update(DocumentDemande docDem) {
		if(docDem == null) return false;
		collection = ConnectionMongo.getDatabase().getCollection(nomcollection);
		
		UpdateResult result = collection.updateOne(Filters.eq("id", docDem.getId()), documentDemandeToMongoDoc(docDem));
		if(result.getModifiedCount() > 0) return true;
		return false;
	}
	
	private static DocumentDemande resultToDocumentDemande(Document o) {
		DocumentDemande docDemande = new DocumentDemande();
		
		docDemande.setId(o.getInteger("id"));
		docDemande.setDate_depot(o.getDate("date_depot"));
		docDemande.setLien(o.getString("lien"));
		docDemande.setDocumentType(BackOfficeData.getDocument(o.getInteger("document_id")));		
		docDemande.setDemande(DemandeDAO.get(o.getInteger("demande_id")));
		return docDemande;
	}
	
	private static Document documentDemandeToMongoDoc(DocumentDemande docDem) {
		Document doc = new Document();
		
		doc.put("id", docDem.getId());
		doc.put("document_id", docDem.getDocumentType().getDocument_id());
		doc.put("demande_id", docDem.getDemande().getId());
		doc.put("date_depot", docDem.getDate_depot());
		doc.put("lien", docDem.getLien());
		
		return doc;
	}
}
