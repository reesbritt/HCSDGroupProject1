package userInterface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class DeleteAppointment extends JFrame {

			
	public DeleteAppointment() {
		//window properties 
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimensions = toolkit.getScreenSize();
		setTitle("Patient infomation");
		setSize(screenDimensions.width/2, screenDimensions.height/2); 
		setLocation(new Point(screenDimensions.width/4, screenDimensions.height/4));
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		
		JComboBox appointments = new JComboBox();
		panel.add(appointments);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		JButton delete = new JButton("Delete");
		buttonPanel.add(delete);
		JButton seen = new JButton("Seen");
		buttonPanel.add(seen);
		//ACCESS DATABASE
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
		String nameQuery;
		ResultSet rs;
		
		try {
			statement = con.createStatement();
			nameQuery = "SELECT * FROM Appointment";
			rs = statement.executeQuery(nameQuery);
			
			while (rs.next()) { 
				
				appointments.addItem(rs.getString("Appointment.AppointmentID")+" "+" Start time -"+rs.getString("Appointment.StartTime")+" End time -"+rs.getString("Appointment.EndTime")+ " "+"Date: "+rs.getString("Appointment.Date")); 
				
				}
			con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				
				String appointmentQuery;
				String receiptQuery;
			
				String box = appointments.getSelectedItem().toString();
				int i = 0;
				String appointmentID = "";
				while (box.charAt(i) != ' ') {
					appointmentID = appointmentID+box.charAt(i);
					i+=1;
				}
				try {
					receiptQuery = "DELETE FROM Receipt WHERE AppointmentID = ?";
					PreparedStatement rS = con.prepareStatement(receiptQuery);
					rS.setInt(1, Integer.valueOf(appointmentID));
					rS.execute();
					appointmentQuery = "DELETE FROM Appointment WHERE AppointmentID = ? ";
					PreparedStatement pS = con.prepareStatement(appointmentQuery);
					pS.setInt(1, Integer.valueOf(appointmentID));
					pS.execute();
				

					
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				
			}
		});
		seen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				
				String box = appointments.getSelectedItem().toString();
				int i = 0;
				String appointmentID = "";
				while (box.charAt(i) != ' ') {
					appointmentID = appointmentID+box.charAt(i);
					i+=1;
				}
				String SQL;
				PreparedStatement aS;
				try {

					SQL =  "UPDATE Appointment SET Seen = 1 WHERE AppointmentID = ?";
					aS = con.prepareStatement(SQL);
					aS.setInt(1, Integer.valueOf(appointmentID));
//					aS.execute();
					
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		
		
		
		Container content = getContentPane();
		content.setLayout(new BorderLayout());
		content.add(panel, BorderLayout.NORTH);
		content.add(buttonPanel, BorderLayout.SOUTH);
	}
	public static void main(String[] args) {
		DeleteAppointment d = new DeleteAppointment();
		d.setVisible(true);
	}
}
