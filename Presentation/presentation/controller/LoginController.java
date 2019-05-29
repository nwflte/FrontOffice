package presentation.controller;

import Business.Chef;
import Business.Employe;

public class LoginController {
	static UserType user;
	static Chef chef;
	static Employe employe;
	
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
