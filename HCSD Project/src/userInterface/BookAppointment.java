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
		for (int i = 1; i<118;i++){
			year.addItem(1900+i);
		}
		
		JLabel t1 = new JLabel("Treatment 1");
		JComboBox t1Box = new JComboBox();
		t1Box.addItem("None");
		t1Box.addItem("Teeth clean");
		t1Box.addItem("Regular check up");
		t1Box.addItem("Remedial treatment");
		JLabel t2 = new JLabel("Treatment 2");
		JComboBox t2Box = new JComboBox();
		t2Box.addItem("None");
		t2Box.addItem("Teeth clean");
		t2Box.addItem("Regular check up");
		t2Box.addItem("Remedial treatment");
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
				Integer t1Code = null;
				Integer t2Code = null;
				Integer p1Code = null; //dentist code == 1
				Integer p2Code= null; //hygenist code == 2
				if (t1Value != "None") {
					if (t1Value== "Teeth clean"); {t1Code = 1; p1Code = 2;}
					if (t1Value=="Regular check up");{t1Code = 2; p1Code = 1;}
					if (t1Value=="Remedial treatment");{t1Code = 3; p1Code =1;}
					
				}
				if (t2Value != "None") {
					if (t2Value == "Teeth clean"); {t2Code = 1; p2Code = 2;}
					if (t2Value == "Regular check up");{t2Code = 2;p2Code =1;}
					if (t2Value == "Remedial treatment");{t2Code = 3;p2Code =1;}
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
				Integer patientID;
				Integer num = 0;
				Integer appointmentNum =0;
				try {
					statement  = con.createStatement();
					
					maxString = "SELECT Patient.PatientID FROM Patient WHERE Patient.Firstname=" + name + "AND Address.Postcode= "+address+ "INNER JOIN Address ON Patient.PatientID=Address.PatientID"  ;
					rs = statement.executeQuery(maxString);
					rs.next();
					patientID = rs.getInt(1);
					
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
				String SQL = "INSERT INTO Appointment VALUES (?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmt;
				try {
					pstmt = con.prepareStatement(SQL);
					pstmt.setInt(1, appointmentNum);
					pstmt.setInt(2, p1Code);
					pstmt.setInt(3, patientID);
					pstmt.setString(4, yearVal+"-" +monthVal+"-"+dayValue);
					pstmt.setString(5,start);
					pstmt.setString(6, end);
					pstmt.setInt(7, t1Code);
					pstmt.setInt(8, t2Code);
					pstmt.setInt(9, 100);
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
}

