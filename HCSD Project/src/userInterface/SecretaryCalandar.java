package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.*; 
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder; 

public class SecretaryCalandar extends JFrame{ 
    //variables 
	private static Color bC = new Color( 0, 98, 139);
	//font 
	private static Font font = new Font(Font.SERIF, Font.BOLD,15);
	private static Border grayborder = BorderFactory.createLineBorder(Color.gray, 1);
	private static Border margin = new EmptyBorder(15,50,15,50);
	
	//methods
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
	public SecretaryCalandar(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimensions = toolkit.getScreenSize();
		setTitle("Weekly calandar");
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
		JLabel timeSlots[] = new JLabel[7];
		designLabels(timeSlots, times); 
		
		JPanel time = new JPanel();
		time.setLayout(new BoxLayout(time,BoxLayout.PAGE_AXIS));
		addToPanel(timeSlots, time); 
		  
		//create appointment slot boxes 
		JPanel appointments = new JPanel();
		appointments.setLayout(new GridLayout());
		//add menu 
		JMenuBar menuBar = new JMenuBar(); 
		this.setJMenuBar(menuBar);
		JMenu menu = new JMenu ("File");
		menuBar.add(menu); 
		JMenuItem createAppointment = new JMenuItem("Create Appointment");
		menu.add(createAppointment);

        // add container for entire screen
		Container contentPane = getContentPane(); 
		contentPane.setLayout(new BorderLayout());
		contentPane.add(days, BorderLayout.NORTH);
		contentPane.add(time, BorderLayout.WEST); 
		contentPane.setBackground(Color.GRAY);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
	}
	public static void main(String[] args){
		SecretaryCalandar calandar = new SecretaryCalandar(); 
		calandar.setVisible(true);
	}
	
}
