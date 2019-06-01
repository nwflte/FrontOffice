package presentation.model;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import metier.Demande;

public class EspaceEmployeTableModel extends AbstractTableModel {
	
	private ArrayList<Demande> listeDemandes;
	private Demande demandeATraiter;
	private int indexDemandeATraiter;
	
	private String[] columnsName = {"Procedure", "CIN", "Date Depot", "Etape á traitée", "Taux de Completion", "#Documents Deposés/Documents Requis"};
	private Class[] columnsClass = {String.class, String.class, Date.class, String.class, String.class, String.class};
	
	public EspaceEmployeTableModel(ArrayList<Demande> liste) {
		listeDemandes = liste;
		indexDemandeATraiter = 0;
		demandeATraiter = liste.get(indexDemandeATraiter);
		
	}
	
	@Override
	public int getColumnCount() {
		return columnsName.length;
	}

	@Override
	public int getRowCount() {
		return listeDemandes.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Demande row = listeDemandes.get(rowIndex);
		switch(columnIndex) {
			case 0 : return row.getProcedure().getProcedure_nom();
			case 1 : return row.getCitoyen_CIN();
			case 2 : return row.getDate_depot();
			case 3 : return row.getEtapeActuelle().getEtapeType().getEtape_nom();
			case 4 : return (row.getEtapeActuelle().getOrdre() -1) / (double)row.getEtapes().size() + "%";
			case 5 : return "" + row.getDocuments_deposes().size() + "/" + row.getProcedure().getNbr_documents();
			default : return null;
		}
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return this.columnsName[columnIndex];
	}
	@Override
	public Class getColumnClass(int columnIndex) {
		return this.columnsClass[columnIndex];
	}

	public Demande getDemandeATraiter() {
		return demandeATraiter;
	}

	public void setDemandeATraiter(Demande demandeATraiter) {
		this.demandeATraiter = demandeATraiter;
	}

	public int getIndexDemandeATraiter() {
		return indexDemandeATraiter;
	}

	public void setIndexDemandeATraiter(int indexDemandeATraiter) {
		this.indexDemandeATraiter = indexDemandeATraiter;
	}

	public ArrayList<Demande> getListeDemandes() {
		return listeDemandes;
	}

	public void setListeDemandes(ArrayList<Demande> listeDemandes) {
		this.listeDemandes = listeDemandes;
	}
	
	
	
}
