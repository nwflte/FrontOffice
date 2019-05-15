package dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class ConnectionNoSQL {
	
	private static MongoClient mongoClient;
	private static DB database;
	
	static {
		mongoClient = new MongoClient("localhost", 27017);
		database = mongoClient.getDB("projet");
		//Deux lignes pour desactiver les messages dans la console, pas necessaire
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE); 
	}
	
	public DB getDatabase( ) {
		return database;
	}
	
	public MongoClient getConnection() {
		return mongoClient;
	}
	
	public static void main(String[] args) {
		try {
			Class.forName("dao.ConnectionNoSQL").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		database.getCollectionNames().forEach(System.out::println);
	}
}
