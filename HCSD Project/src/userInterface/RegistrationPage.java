package userInterface;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class RegistrationPage extends JFrame {
	 
	public RegistrationPage(){
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		JButton button = new JButton("Submit");
		
		
		JComboBox title = new JComboBox();
		title.addItem("Mr");
		title.addItem("Mrs");
		title.addItem("Miss");
		title.addItem("Ms");
		title.addItem("Dr");
		
		JComboBox dayOB = new JComboBox();
		//add all days of the month 
		for (int i = 1; i<32;i++){
			dayOB.addItem(i);
		}
		
		JComboBox monthOB = new JComboBox();
		//add all days of the month 
		for (int i = 1; i<13;i++){
			monthOB.addItem(i);
		}
		
		JComboBox yearOB = new JComboBox();
		//add all days of the month 
		for (int i = 1; i<118;i++){
			yearOB.addItem(1900+i);
		}
		
		JTextField firstName = new JTextField(20);
		JTextField surname = new JTextField(20);
		
		JRadioButton healthPlan = new JRadioButton("Health plan?");
		
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(0,2));
		formPanel.add(new JLabel("Title:"));
		formPanel.add(title);
		formPanel.add(new JLabel("Forename:"));
		formPanel.add(firstName);
		formPanel.add(new JLabel("Surname:"));
		formPanel.add(surname);
		formPanel.add(new JLabel("Day of birth"));
		formPanel.add(dayOB);
		formPanel.add(new JLabel("Month of birth"));
		formPanel.add(monthOB);
		formPanel.add(new JLabel("Year of birth"));
		formPanel.add(yearOB);
		formPanel.add(new JLabel(""));
		formPanel.add(healthPlan);
		
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(button,BorderLayout.SOUTH);
		contentPane.add(formPanel, BorderLayout.CENTER);
		
		//Window properties
		setTitle("Patient regsitration");
		Dimension screenDimensions = toolkit.getScreenSize();
		setSize(screenDimensions.width/2, screenDimensions.height/2);
		setLocation(new Point(screenDimensions.width/4, screenDimensions.height/4));	
		
		//event listener
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String titleValue = title.getSelectedItem().toString();
				String dayOBValue = dayOB.getSelectedItem().toString();
				String monthOBValue = monthOB.getSelectedItem().toString();
				String yearOBValue = yearOB.getSelectedItem().toString();
				String firstNameValue=firstName.getText().toString();
				String surnameValue=surname.getText().toString();
				String healthPlanValue = "N";
						
				if (healthPlan.isSelected()){
					healthPlanValue = "Y";
				}
				
				Connection con = null;
				
				try {
					   Class.forName("com.mysql.jdbc.Driver");
					}
					catch(ClassNotFoundException ex) {
					   System.out.println("Error: unable to load driver class!");
					   System.exit(1);
					}
				
				String URL = "jdbc:mysql://localhost/database";
				String USER = "username";
				String PASS = "password";
						
				con = DriverManager.getConnection(URL, USER, PASS);
				
				Statement statement = con.createStatement();
				String maxString = "SELECT MAX(PatientID) from Patient";
				ResultSet rs = statement.executeQuery(maxString);
				rs.next();
				Integer max = rs.getInt(1);
				Integer num = max+1;
				
				String SQL = "INSERT INTO Patients VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, num);
				pstmt.setString(2, titleValue);
				pstmt.setString(3, dayOBValue+"/"+monthOBValue+"/"+yearOBValue);
				pstmt.setString(4, firstNameValue);
				pstmt.setString(5, surnameValue);
				pstmt.setString(6, healthPlanValue);
				pstmt.executeUpdate();
				pstmt.close();
			}
		});
	}
	
	
	public static void main(String[] args) {
		RegistrationPage regPage = new RegistrationPage();
		regPage.setVisible(true);
	}
	
}
