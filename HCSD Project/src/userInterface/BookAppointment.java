package userInterface;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Calendar;

import javax.swing.*;

public class BookAppointment extends JFrame {
	private static String[] times = new String[48]; 
	public BookAppointment(){
		//window properties 
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimensions = toolkit.getScreenSize();
		setTitle("Book appointment");
		setSize(screenDimensions.width/2, screenDimensions.height/2); 
		setLocation(new Point(screenDimensions.width/4, screenDimensions.height/4));
		//ADD NEXT APPOINTMENT
		JPanel appointmentPanel = new JPanel();
		appointmentPanel.setLayout(new GridLayout(0,2));
		//COMBO BOX FOR DATE AND TIME 
		JComboBox day = new JComboBox(); 
		JComboBox month = new JComboBox();
		for (int i = 1; i < 32; i++){
			if (i < 13){
				day.addItem(i);
				month.addItem(i);
			}else{
				day.addItem(i);
			}
		}
		JComboBox year = new JComboBox();
		//add all days of the month title
		for (int i = 0; i<8;i++){
			year.addItem(2017+i);
		}
		
		JLabel t1 = new JLabel("Treatment 1");
		JComboBox t1Box = new JComboBox();
		t1Box.addItem("None");
		t1Box.addItem("Hygiene Visit");
		t1Box.addItem("Check up");
		t1Box.addItem("Silver filling");
		t1Box.addItem("White resin filling");
		t1Box.addItem("Gold Crown");
		JLabel t2 = new JLabel("Treatment 2");
		JComboBox t2Box = new JComboBox();
		t2Box.addItem("None");
		t2Box.addItem("Hygiene Visit");
		t2Box.addItem("Check up");
		t2Box.addItem("Silver filling");
		t2Box.addItem("White resin filling");
		t2Box.addItem("Gold Crown");
		JLabel message = new JLabel("Appointment has been booked");
		message.setVisible(false);
		JButton bookAgain = new JButton("Back");
		bookAgain.setVisible(false);
		JTextField postcode = new JTextField(7);
		JTextField firstname = new JTextField(10);
		
		
		
		//get times for appointments
		String[] hours = {"9", "10", "11", "12", "13","14","15","16"};
		String[] mins = {"00", "10","20","30","40","50"};
		int counter = 0;
		for (int i = 0; i <hours.length; i++){
			for(int j = 0; j< mins.length; j++){
				times[counter]=hours[i]+":"+mins[j];
				counter++;
			}
		}
		JComboBox stimes = new JComboBox(); 
		for (int i = 0; i < times.length;i++){
			stimes.addItem(times[i]);
		}
		JComboBox etimes = new JComboBox();
		for(int i = 0; i< times.length;i++){
			etimes.addItem(times[i]);
		}
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton confirmAppointment = new JButton("Confirm Appointment");
		confirmAppointment.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String dayValue = day.getSelectedItem().toString();
				String monthVal = month.getSelectedItem().toString();
				String yearVal = year.getSelectedItem().toString();
				String t1Value = t1Box.getSelectedItem().toString();
				String t2Value = t2Box.getSelectedItem().toString();
				String start = stimes.getSelectedItem().toString();
				String end = etimes.getSelectedItem().toString();
				String address = postcode.getText();
				String name = firstname.getText();
				Integer treatmentCode = null;
				Integer partnerCode = null; //dentist code == 1
				
				if (t1Value != "None") {
					if (t1Value== "Check up"); {treatmentCode = 1; partnerCode = 2;}
					if (t1Value=="Hygiene Visit");{treatmentCode = 2; partnerCode = 2;}
					if (t1Value=="Silver filling");{treatmentCode = 3; partnerCode =1;}
					if (t1Value=="White resin filling");{treatmentCode =4; partnerCode=1;}
					if (t1Value =="Gold Crown"); {treatmentCode=5; partnerCode=1;}
					
				}
				//connect to database 
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
				Integer patientID = 0;
				Integer num = 0;
				Integer appointmentNum =0;
				Integer max = 0;
				try {
					statement  = con.createStatement();
					maxString = "SELECT Patient.PatientID, Address.Postcode  FROM Patient INNER JOIN Address ON Patient.PatientID=Address.PatientID WHERE Patient.Firstname = '"+ name+"'";	
					rs = statement.executeQuery(maxString);
					while(rs.next()) {if (address == rs.getString("Address.Postcode")); {patientID = rs.getInt(1);}}
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
				try {
					statement = con.createStatement();
					maxString = "SELECT MAX(AppointmentID) from Appointment";
					rs = statement.executeQuery(maxString);
					rs.next();
					max = rs.getInt(1);
					appointmentNum = max+1;
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String SQL = "INSERT INTO Appointment VALUES (?,?,?,?,?,?,?,?,0)";
				java.sql.PreparedStatement pstmt;
				int calculatedCost = 0;
				
				try {
					calculatedCost = calculateCost.calculate(patientID,treatmentCode);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				try {
					pstmt = con.prepareStatement(SQL);
					pstmt.setInt(1, appointmentNum);
					pstmt.setInt(2, partnerCode);
					pstmt.setInt(3, patientID);
					pstmt.setString(4, yearVal+"-" +monthVal+"-"+dayValue);
					pstmt.setString(5,start);
					pstmt.setString(6, end);
					pstmt.setInt(7, treatmentCode);
					pstmt.setInt(8, calculatedCost);
					pstmt.executeUpdate();
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Statement maxStatement;
				try {
					maxStatement = con.createStatement();
					maxString = "SELECT MAX(ReceiptID) from Receipt";
					rs = maxStatement.executeQuery(maxString);
					rs.next();
					max = rs.getInt(1);
					int receiptNum = max+1;
					
				
					String receiptSQL = "INSERT INTO Receipt VALUES (?,?,?,?)";
					java.sql.PreparedStatement receiptPS;
					receiptPS = con.prepareStatement(receiptSQL);
					receiptPS.setInt(1, receiptNum);
					receiptPS.setInt(2, appointmentNum);
					receiptPS.setInt(3, calculatedCost);
					receiptPS.setBoolean(4, false);
					receiptPS.executeUpdate();
					receiptPS.close();
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				bookAgain.setVisible(true);
				message.setVisible(true);
				appointmentPanel.setVisible(false);
				confirmAppointment.setVisible(false);
			}
				
		});
		
		bookAgain.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bookAgain.setVisible(false); 
				message.setVisible(false);
				appointmentPanel.setVisible(true);
				confirmAppointment.setVisible(true);
			
			}
		});
		
		appointmentPanel.add(new JLabel("Day"));
		appointmentPanel.add(day);
		appointmentPanel.add(new JLabel("Month"));
		appointmentPanel.add(month);	
		appointmentPanel.add(new JLabel("Year"));
		appointmentPanel.add(year);
		appointmentPanel.add(t1);
		appointmentPanel.add(t1Box);
		appointmentPanel.add(new JLabel("Post code"));
		appointmentPanel.add(postcode);
		appointmentPanel.add(new JLabel("First name"));
		appointmentPanel.add(firstname);
		appointmentPanel.add(new JLabel("Treatment 2"));
		appointmentPanel.add(t2Box);
		appointmentPanel.add(new JLabel("Start time")); 
		appointmentPanel.add(stimes);
		appointmentPanel.add(new JLabel("End time"));
		appointmentPanel.add(etimes);
		
		//add buttons
		buttonPanel.add(confirmAppointment);
		buttonPanel.add(message);

		buttonPanel.add(bookAgain);
		//container
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.add(appointmentPanel, BorderLayout.CENTER);
		container.add(buttonPanel, BorderLayout.NORTH);
	}
	public static void main(String[]  args) {
		BookAppointment page = new BookAppointment();
		page.setVisible(true);
	}
}

