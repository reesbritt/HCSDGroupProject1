package userInterface;
import javax.swing.*; 
import java.sql.*;
import java.awt.*;
import java.awt.event.*; 
public class PatientInfomation extends JFrame {
	
	//variables 
	private static BookAppointment bA = new BookAppointment(); 
	private static PatientInfomation info = new PatientInfomation();
	//constructor 
	public PatientInfomation(){	
		bA.setVisible(false);
		//window properties 
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimensions = toolkit.getScreenSize();
		setTitle("Patient infomation");
		setSize(screenDimensions.width/2, screenDimensions.height/2); 
		setLocation(new Point(screenDimensions.width/4, screenDimensions.height/4));
		
		
				
		
		
		
		//patient details, make invisible until confirm has been clicked
		JTextField  name = new JTextField(20);
		JTextField healthcare = new JTextField(20);
		JTextField outstandingPayment = new JTextField(20);
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(0,2));
		infoPanel.add(new JLabel("Name:"));
		infoPanel.add(name);
		infoPanel.add(new JLabel("Healthcare plan"));
		infoPanel.add(healthcare);
		infoPanel.add(new JLabel("Outstanding payments:  ")); 
		infoPanel.add(outstandingPayment);
		infoPanel.setVisible(false);
		//BUTTON TO CHANGE TO APPOINTMENT PAGE 
		JButton bookappointment = new JButton("Book next appointment"); 
		bookappointment.setVisible(false);
		bookappointment.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bA.setVisible(true);
				info.setVisible(false);
			}
		});
		
		//add all names to combo box, click a name and click enter then open all the details about that patient
		JPanel panel = new JPanel(); 
		panel.setLayout(new FlowLayout());
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		
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
			nameQuery = "SELECT * from Patient";
			rs = statement.executeQuery(nameQuery);
			while (rs.next()) { 
				comboBox.addItem(rs.getString("Firstname")+" "+rs.getString("Surname")); 
				
				}
			con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		JButton confirm = new JButton("Confirm"); 
		//event listener to show patient information 
		confirm.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				infoPanel.setVisible(true);
				comboBox.setVisible(false);
				bookappointment.setVisible(true); 
				confirm.setVisible(false);
			}
		});

		panel.add(comboBox);
		panel.add(confirm);
	
		panel.add(bookappointment);
		
		
		
		
		
		//add button to
		JButton back = new JButton("Back");
		
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				infoPanel.setVisible(false);
				comboBox.setVisible(true);
				confirm.setVisible(true);
				bookappointment.setVisible(false);
			}
		});

		//Container
		Container contentPane = getContentPane(); 
		contentPane.setLayout(new BorderLayout());
		contentPane.add(panel, BorderLayout.WEST);
		contentPane.add(back, BorderLayout.SOUTH);
		contentPane.add(infoPanel, BorderLayout.CENTER); 
		contentPane.add(infoPanel,BorderLayout.CENTER);

	}
	
	
	
	public static void main(String[] args){
		info.setVisible(true);
	}
}
