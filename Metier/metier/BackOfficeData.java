package metier;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Business.Chef;
import Business.Document;
import Business.Employe;
import Business.Etape;
import Business.Procedure;
import Business.User;
import util.StoreObjectsToJson;

public class BackOfficeData {
	private static ArrayList<Employe> listeEmployes;
	private static ArrayList<Chef> listeChefs;
	private static ArrayList<User> listeUsers;
	private static ArrayList<Procedure> listeProcedures;
	private static ArrayList<Etape> listeEtapes;
	private static ArrayList<Document> listeDocuments;
	
	static {
		getDataFromJson();
	}
	
	public static ArrayList<Employe> getListeEmployes() {
		return listeEmployes;
	}
	public static ArrayList<Chef> getListeChefs() {
		return listeChefs;
	}
	public static ArrayList<User> getListeUsers() {
		return listeUsers;
	}
	public static ArrayList<Procedure> getListeProcedures() {
		return listeProcedures;
	}
	public static ArrayList<Etape> getListeEtapes() {
		return listeEtapes;
	}
	public static ArrayList<Document> getListeDocuments() {
		return listeDocuments;
	}
	
	public static Employe getEmploye(int id) {
		Optional<Employe> employe = listeEmployes.stream().filter(emp -> emp.getEmploye_id() == id).findAny();
		return employe.isPresent() == true ? employe.get() : null;
	}
	
	public static Chef getChef(int id) {
		Optional<Chef> chef = listeChefs.stream().filter(ch -> ch.getChef_id() == id).findAny();
		return chef.isPresent() == true ? chef.get() : null;
	}
	
	public static User getUser(int id) {
		Optional<User> user = listeUsers.stream().filter(usr -> usr.getUser_id() == id).findAny();
		return user.isPresent() == true ? user.get() : null;
	}
	
	public static Procedure getProcedure(int id) {
		Optional<Procedure> procedure = listeProcedures.stream().filter(proc -> proc.getProcedure_id() == id).findAny();
		return procedure.isPresent() == true ? procedure.get() : null;
	}
	
	public static Etape getEtape(int id) {
		Optional<Etape> etape = listeEtapes.stream().filter(et -> et.getEtape_id() == id).findAny();
		return etape.isPresent() == true ? etape.get() : null;
	}
	
	public static Document getDocument(int id) {
		Optional<Document> document = listeDocuments.stream().filter(et -> et.getDocument_id() == id).findAny();
		return document.isPresent() == true ? document.get() : null;
	}
	
	private static void getDataFromJson() {
		StoreObjectsToJson.Store();
		Gson gson = new Gson();
		
		for(int i = 0; i < 6; i++) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader("../ObjectsJson/file" + i + ".json"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			switch (i) {
			case 0 :
				listeEmployes = gson.fromJson(br, new TypeToken<List<Employe>>(){}.getType());
				break;
			case 1 :
				listeChefs = gson.fromJson(br, new TypeToken<List<Chef>>(){}.getType());
				break;
			case 2 :
				listeUsers = gson.fromJson(br, new TypeToken<List<User>>(){}.getType());
				break;
			case 3 :
				listeProcedures = gson.fromJson(br, new TypeToken<List<Procedure>>(){}.getType());
				break;
			case 4 :
				listeEtapes = gson.fromJson(br, new TypeToken<List<Etape>>(){}.getType());
				break;
			case 5 :
				listeDocuments = gson.fromJson(br, new TypeToken<List<Document>>(){}.getType());
				break;
			default :
					break;
			}
		}
	}
	
}
