package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class Stat {
	
	public void procedure() {
		String getProcedure = "SELECT procedure_id FROM procedures";
		Statement st;
		ArrayList<Integer> procedureIDs = new ArrayList<Integer>();
		try {
			st = ConnectionSQL.getConn().createStatement();
			ResultSet result = st.executeQuery(getProcedure);
			while(result.next()) {
				procedureIDs.add(result.getInt("procedure_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Pour chaque procedure dans SQL, voir le nombre de demandes totale et le nombre des demandes traitées
		for(Integer id : procedureIDs) {
			MongoCollection<Document> collection = ConnectionMongo.getDatabase().getCollection("demandes");
			
			//Donne le nombre des demandes qui ont procedure_id = id
			long totalDemandes = collection.count(Filters.eq("procedure_id", id));
				
			/*
			 * Filtre pour les demandes sous la forme :
			 * 	procedure_id = id AND (etat = ACCEPTEE OR etat = TERMINEE)
			 */
			Bson filter = Filters.and(Filters.eq("procedure_id", id), 
								Filters.or(Filters.eq("etat", "ACCEPTEE"), Filters.eq("etat", "TERMINEE")));
			
			long nbrDemandesTraitees = collection.count(filter);
			
			System.out.println("Procedure " + id + " , Totale demandes : " + totalDemandes + " , Traitées: " + nbrDemandesTraitees);
		
		}
	}
	
	public static void main(String avg[]) {
		new Stat().procedure();
	}
}