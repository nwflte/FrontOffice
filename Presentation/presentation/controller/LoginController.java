package presentation.controller;

import Business.Chef;
import Business.Employe;
import metier.BackOfficeData;

public class LoginController {
	private static UserType user;
	static Chef chef;
	static Employe employe = BackOfficeData.getEmploye(1);
	
	public static void setEmployeConnected(Employe e) {
		employe = e;
	}
	
	public static void setChefConnected(Chef c) {
		chef = c;
	}
	
	public static Employe getEmployeConnected() {
		return employe;
	}
	
	public static Chef getChefConnected() {
		return chef;
	}
}
