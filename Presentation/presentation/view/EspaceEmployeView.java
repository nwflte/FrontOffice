package presentation.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import presentation.model.DemandeEmployeTableModel;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class EspaceEmployeView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public EspaceEmployeView(DemandeEmployeTableModel Tablemodel) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1003, 622);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(104, 116, 828, 374);
		contentPane.add(scrollPane);
		
		table = new JTable(Tablemodel);
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setBounds(100, 62, 468, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Rechercher");
		btnNewButton.setBounds(748, 61, 97, 25);
		contentPane.add(btnNewButton);
		
		JButton btnTraiter = new JButton("Traiter");
		btnTraiter.setBounds(430, 526, 97, 25);
		contentPane.add(btnTraiter);
	}
}
