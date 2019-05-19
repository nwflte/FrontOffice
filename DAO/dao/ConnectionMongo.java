package dao;

import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import Business.Procedure;

public class ConnectionMongo {
	
	private static MongoClient mongoClient;
	private static MongoDatabase mydatabase;
	
	static {
		mongoClient = new MongoClient("localhost", 27017);
		mydatabase = mongoClient.getDatabase("projet");
		//Deux lignes pour desactiver les messages dans la console, pas necessaire
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE); 
	}
	
	public static MongoDatabase getDatabase( ) {
		return mydatabase;
	}
	
	public static MongoClient getConnection() {
		return mongoClient;
	}
	
	/*public static void main(String[] args) {
		try {
			Class.forName("dao.ConnectionMongo").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		MongoCollection<Document> collection = mydatabase.getCollection("documents");
		Document newDoc = new Document("test", "test").append("iddd", Date.valueOf("1999-12-12"));
		//collection.insertOne(newDoc);
		
		Document first = collection.find().first();
		System.out.println(first.toJson());
		
		DBObject emptyDoc = new BasicDBObject();
        System.out.println("emptyDoc = " + emptyDoc);
        
	}*/
}
