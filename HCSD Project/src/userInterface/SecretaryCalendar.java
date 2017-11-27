package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.*;
import java.awt.*; 
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent; 
import javax.swing.event.ChangeListener;

public class SecretaryCalendar extends JFrame{ 
    //variables 
	private static Color bC = new Color( 0, 98, 139);
	private static Color silver = new Color(192,192,192);
	//font 
	private static Font font = new Font(Font.SERIF, Font.BOLD,15);
	private static Font tFieldFont = new Font(Font.SERIF,Font.BOLD,2);
	private static Border grayborder = BorderFactory.createLineBorder(Color.gray, 1);
	private static Border margin = new EmptyBorder(15,50,15,50);
	private static Box[] box = new Box[10];
	private static BookAppointment appointmentScreen = new BookAppointment(); 
	private static SecretaryCalendar doctorsAppointment = new SecretaryCalendar();
	private static SecretaryCalendar hygienistAppointment = new SecretaryCalendar();
	//methods
	private JPanel makePanel(String text) {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		panel.add(new Label(text)); 
		panel.setLayout(new GridLayout(1,1));
		return panel;
	}
	//event listener for appointment booking slots highlight when hovered over 

	//take an array of J Labels, give them the same design
	public JLabel[] designLabels (JLabel[] l, String[] s){
		
		for (int i = 0; i < l.length; i++){
			l[i] = new JLabel(s[i]);
			l[i].setOpaque(true);
			l[i].setFont(font);
			l[i].setForeground(Color.WHITE);
			l[i].setBorder(new CompoundBorder(
					grayborder, margin 
					));
			l[i].setBackground(bC);
		}
		return l; 
	}
	//take a panel and add labels to the panel 
	public JPanel addToPanel (JLabel[] labels, JPanel panel ){
		for (int i = 0; i < labels.length; i++){
			panel.add(labels[i]);
		}
		return panel; 
	}
	public JLabel[] editLabels (JLabel[] fields){
		for (int i = 0;i< fields.length; i++){
			fields[i] = new JLabel(" ");
			fields[i].setOpaque(true);
			fields[i].setForeground(silver);
			fields[i].setBorder(grayborder);
			
		}
		return fields ;
	}
	public JPanel addTextField (JLabel[] fields, JPanel panel){
		for (int i = 0; i < fields.length; i++){
			panel.add(fields[i]);
		}
		return panel;
	}
	
	public SecretaryCalendar(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimensions = toolkit.getScreenSize();
		setTitle("Weekly calendar");
		setSize(screenDimensions.width/2, screenDimensions.height/2); 
		setLocation(new Point(screenDimensions.width/4, screenDimensions.height/4));
	    String daysOfWeek[] = {"         ","Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
		
		//create array of jlabels displaying the days of the week 
		JLabel labelsOfWeek[] = new JLabel[6]; 
		designLabels(labelsOfWeek, daysOfWeek); //add colour to the labels and add them to an array 
		JPanel days = new JPanel(); //create a panel for the labels 
		days.setLayout(new BoxLayout(days,BoxLayout.X_AXIS));
		addToPanel(labelsOfWeek, days);//add labels to panel 
		
		//Create timeslots for appointments 9,5
		String times[] = {"9am  ", "10am","11am", "12pm", "1pm  ", "2pm  ", "3pm  ", "4pm  "};
		JLabel timeSlots[] = new JLabel[8];
		designLabels(timeSlots, times); 
		
		JPanel time = new JPanel();
		time.setLayout(new BoxLayout(time,BoxLayout.PAGE_AXIS));
		addToPanel(timeSlots, time); 
		  
		//create appointment slot boxes 
		JLabel[] aLabels = new JLabel[40];
		for(int i =0; i<aLabels.length;i++) {
			aLabels[i] = new JLabel();
		}
		JPanel appointments = new JPanel();
		appointments.setLayout(new GridLayout(8,5));
		editLabels(aLabels);
		addToPanel(aLabels,appointments);
		//add event listener for appointment bookings 
		
		
		//add menu 
		JMenuBar menuBar = new JMenuBar(); 
		this.setJMenuBar(menuBar);
		JMenu menu = new JMenu ("File");
		menuBar.add(menu); 
		JMenuItem createAppointment = new JMenuItem("Create Appointment");
		menu.add(createAppointment);
		createAppointment.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				appointmentScreen.setVisible(true);
			} 
		});
		
		//ADD TABBED PANES TO SWITCH BETWEEN APPOINTMENTS 
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Hygienist Appointments", new JPanel());
		tabbedPane.addTab("Doctors Appointments ", new JPanel());
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged( ChangeEvent e) {
				int tab = tabbedPane.getSelectedIndex();
				int i = 0; 
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
				if (tab == 0) {
					try {
						statement  = con.createStatement();
						maxString = "SELECT * FROM Appointment WHERE PartnerID = 1 ";
						rs = statement.executeQuery(maxString);

							
							
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
				if (tab == 1) {
					try {
						statement  = con.createStatement();
						maxString = "SELECT * FROM Appointment WHERE PartnerID = 2";
						rs = statement.executeQuery(maxString);


							
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					
						}
			
				
				//add SQL QUERIES HERE TO ADD EITHER SECRETARY OR DENTIST APPOINTMENTS
				//IF TAB == 0 THEN SHOW SECRETARY APPOINTMENTS ELSE SHOW DOCTORS APPOINTMENTS
				}
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
        // add container for entire screen
		Container contentPane = getContentPane(); 
		contentPane.setLayout(new BorderLayout());
		contentPane.add(days, BorderLayout.NORTH);
		contentPane.add(time, BorderLayout.WEST); 
		contentPane.add(tabbedPane, BorderLayout.SOUTH);
		contentPane.add(appointments, BorderLayout.CENTER);
		//contentPane.add(appointments, BorderLayout.CENTER);
		contentPane.setBackground(Color.GRAY);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		
	}
	
	public static void main(String[] args){
		SecretaryCalendar calendar = new SecretaryCalendar(); 
		calendar.setVisible(true);
		appointmentScreen.setVisible(false);
	}
	
}



