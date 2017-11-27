package userInterface;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
public class TreatmentReview extends JFrame {
	//properties
	
	//methods
	
	public TreatmentReview()  {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimensions = toolkit.getScreenSize();
		setTitle("Treatment review ");
		setSize(screenDimensions.width/2, screenDimensions.height/2); 
		setLocation(new Point(screenDimensions.width/4, screenDimensions.height/4));
		//Panel displaying the list of patients 
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JComboBox box = new JComboBox();
		JLabel label = new JLabel("Choose patients name: ");
		JButton confirm = new JButton("Confirm");
		panel.add(label);
		panel.add(box);
		panel.add(confirm);
		
		box.setEditable(true);
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
			nameQuery = "SELECT Patient.PatientID, Patient.Firstname, Patient.Surname, Address.Postcode from Patient INNER JOIN Address ON Patient.PatientID=Address.PatientID";
			rs = statement.executeQuery(nameQuery);
			
			while (rs.next()) { 
				
				box.addItem(rs.getString("Patient.Patient.ID")+" "+rs.getString("Patient.Firstname")+" "+rs.getString("Patient.Surname")+"("+rs.getString("Address.Postcode")+")"); 
				
				}
			con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Panel displaying all the patients treatment information
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(0,2));
		JLabel name = new JLabel("Name:");
		
		infoPanel.add(name);
		JTextField pName  = new JTextField(20);//patients name
		infoPanel.add(pName);
		JLabel treatments = new JLabel("Treatments given: ");
		infoPanel.add(treatments);
		DefaultListModel<String> costs = new DefaultListModel<>();
		JList<String> treatmentList = new JList<>(costs); //all treatments the patient has received 
		treatmentList.setLayoutOrientation(JList.VERTICAL);
		//To add to list 'treatmentList' do costs.addElement(String)
		infoPanel.add(treatmentList);
		JLabel p = new JLabel("Total Payments: ");
		JLabel payment = new JLabel(" ");
		payment.setBorder(new CompoundBorder(
				BorderFactory.createLineBorder(Color.black), new EmptyBorder(15,50,15,50)
				));
		infoPanel.add(p);
		infoPanel.add(payment);
		infoPanel.setVisible(false);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		//add a button to see breakdown of payments 
		JButton seePayments = new JButton("Click here to see a breakdown of your debt");
		JButton backButton = new JButton("Home");
		buttonPanel.add(seePayments);
		buttonPanel.add(backButton);
		
		//event listeners 
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				
				Connection conn = null;
				String DB="jdbc:mysql://stusql.dcs.shef.ac.uk/team019?user=team019&password=6e84e2f3";
				
				
				String boxString = box.getSelectedItem().toString();
				String PatientID = "";
				while (boxString.charAt(0) != ' ') {
					PatientID = PatientID + boxString.charAt(0);
				}
				
				String appointmentQuery;
				ResultSet ars;
				Statement appStatement;
				String treatmentType = "";
				
				try {
					conn = DriverManager.getConnection(DB);
					appStatement = conn.createStatement();
					appointmentQuery = "SELECT Appointment.TreatmentID_1, Appointment.CostAssigned, Receipt.Paid FROM Appointment WHERE PatientID = "+ PatientID +" INNER JOIN Receipt ON Appointment.AppointmentID =Receipt.AppointmentID";
					ars = appStatement.executeQuery(appointmentQuery);
					while (ars.next()) {
						
						if (ars.getInt("Appointment.TreatmentID_1") == 1) {treatmentType = "Check up";}
						else if (ars.getInt("Appointment.TreatmentID_1") == 2) {treatmentType = "Hygiene visit";}
						else if (ars.getInt("Appointment.TreatmentID_1") == 3) {treatmentType = "Silver amalgam filling";}
						else if (ars.getInt("Appointment.TreatmentID_1") == 4) {treatmentType = "White resin filling";}
						else if (ars.getInt("Appointment.TreatmentID_1") == 5) {treatmentType = "Gold crown";}
						
						Boolean paidStatus = false;
						if	(ars.getBoolean("Receipt.Paid")) {paidStatus = true;}
						
						
						costs.addElement(treatmentType + " £" + ars.getString("Appointment.CostAssigned")+ " " + paidStatus); } 
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				infoPanel.setVisible(true);
			}
		});
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPage.main(new String[] {});
				dispose();
				
			}
		});
		
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.add(panel, BorderLayout.NORTH);
		container.add(infoPanel, BorderLayout.CENTER);
		container.add(buttonPanel, BorderLayout.SOUTH);


		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		
	}
	public static void main(String[] args) {
		TreatmentReview t = new TreatmentReview();
		t.setVisible(true);
	}
}
