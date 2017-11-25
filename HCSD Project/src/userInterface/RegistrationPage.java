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
import java.util.Calendar;


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
		//add all days of the month title
		for (int i = 1; i<118;i++){
			yearOB.addItem(1900+i);
		}
		
		JTextField firstName = new JTextField(20);
		JTextField surname = new JTextField(20);
		JTextField phoneNo = new JTextField(11);
		JTextField houseNo = new JTextField(4);
		JTextField streetName = new JTextField(20);
		JTextField districtName = new JTextField(20);
		JTextField cityName = new JTextField(20);
		JTextField postcode = new JTextField(11);
		
		
		JComboBox healthPlan = new JComboBox();
		healthPlan.addItem("None");
		healthPlan.addItem("NHS plan");
		healthPlan.addItem("Maintenance plan");
		healthPlan.addItem("Dental repair plan");
		healthPlan.addItem("Oral health plan");
		
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
		formPanel.add(new JLabel("Phone number"));
		formPanel.add(phoneNo);
		formPanel.add(new JLabel("House number"));
		formPanel.add(houseNo);
		formPanel.add(new JLabel("Street name"));
		formPanel.add(streetName);
		formPanel.add(new JLabel("District name"));
		formPanel.add(districtName);
		formPanel.add(new JLabel("City name"));
		formPanel.add(cityName);
		formPanel.add(new JLabel("Postcode"));
		formPanel.add(postcode);
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
				String phoneNoValue=phoneNo.getText().toString();
				String healthValue=healthPlan.getSelectedItem().toString();
				String streetValue=streetName.getText().toString();
				String districtValue=districtName.getText().toString();
				String cityValue=houseNo.getText().toString();
				String houseNoValue=houseNo.getText().toString();
				String postcodeValue=postcode.getText().toString();
				
				Integer treatmentCode = null;
				String healthDate = null;		
				if (healthValue != "None"){
					if (healthValue == "NHS plan") {treatmentCode = 1;}
					if (healthValue == "Maintenance plan") {treatmentCode = 2;}
					if (healthValue == "Dental repair plan") {treatmentCode = 4;}
					if (healthValue == "Oral health plan") {treatmentCode = 3;}
					
					int year = Calendar.getInstance().get(Calendar.YEAR);
					healthDate = (Integer.toString(year+1)+"-01-01");
				}
				
				
				Connection con = null;
				
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					}
					catch(ClassNotFoundException ex) {
					   System.out.println("Error: unable to load driver class!");
					   System.exit(1);
					} catch (InstantiationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				String DB="jdbc:mysql://stusql.dcs.shef.ac.uk/team019?user=team019&password=6e84e2f3";
				
				
				try {
					con = DriverManager.getConnection(DB);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Statement statement;
				String maxString;
				ResultSet rs;
				Integer max;
				Integer num = 0;
				
				try {
					statement = con.createStatement();
					maxString = "SELECT MAX(PatientID) from Patient";
					rs = statement.executeQuery(maxString);
					rs.next();
					max = rs.getInt(1);
					num = max+1;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				String SQL = "INSERT INTO Patient VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt;
				try {
					pstmt = con.prepareStatement(SQL);
					pstmt.setInt(1, num);
					pstmt.setString(2, titleValue);
					pstmt.setString(3, yearOBValue+"-"+monthOBValue+"-"+dayOBValue);
					pstmt.setString(4, firstNameValue);
					pstmt.setString(5, surnameValue);
					pstmt.setInt(6, treatmentCode);
					pstmt.setString(7, phoneNoValue);
					pstmt.setString(8, healthDate);
					pstmt.executeUpdate();
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String addressSQL = "INSERT INTO Address VALUES (?, ?, ?, ?, ?, ?)";
				try {
					pstmt = con.prepareStatement(addressSQL);
					pstmt.setString(1, houseNoValue);
					pstmt.setString(2, streetValue);
					pstmt.setString(3, districtValue);
					pstmt.setString(4, cityValue);
					pstmt.setString(5, postcodeValue);
					pstmt.setInt(6, num);
					
					pstmt.executeUpdate();
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	
	public static void main(String[] args) {
		RegistrationPage regPage = new RegistrationPage();
		regPage.setVisible(true);
	}
	
}
