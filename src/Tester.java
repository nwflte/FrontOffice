import java.util.ArrayList;

import dao.DemandeDAO;
import metier.Demande;

public class Tester {

	public static void main(String[] args) {
		try {
			Class.forName("metier.BackOfficeData").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Demande> list = DemandeDAO.getAll();
		System.out.println("");
	}

}
