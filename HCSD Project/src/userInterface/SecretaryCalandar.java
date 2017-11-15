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
                               
	public SecretaryCalandar(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimensions = toolkit.getScreenSize();
		setTitle("Weekly calandar");
		setSize(screenDimensions.width/2, screenDimensions.height/2); 
		setLocation(new Point(screenDimensions.width/4, screenDimensions.height/4));
		//event handler for text fields in a non static member class
	
		
		//field panels 
		JPanel fieldPanel = new JPanel(); 
		fieldPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		for (int i = 1; i < 3; i++){
			for(int j = 1; j < 2; j++){
				c.insets = new Insets(5,5,5,5); //top padding
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = j-1;
				c.gridy = i;
				c.ipady = 120;
				c.ipadx = 120;
				fieldPanel.add(new JTextField(20), c);
			}
		}
		Border blueborder = BorderFactory.createLineBorder(Color.BLUE, 1);
		Border orangeborder = BorderFactory.createLineBorder(Color.ORANGE,1);
		Border margin = new EmptyBorder(10,50,10,50);
		String daysOfWeek[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
		Color bC = new Color(152, 175, 199);
		Color gC = new Color(199, 176, 151 );
		//create array of jlabels displaying the days of the week 
		JLabel labelsOfWeek[] = new JLabel[5]; 
		for (int i=0; i<5; i++){
			labelsOfWeek[i] = new JLabel(daysOfWeek[i]);
			labelsOfWeek[i].setOpaque(true);
			if (i % 2 == 0) {
				labelsOfWeek[i].setBorder(new CompoundBorder(
						blueborder, margin 
						));
				labelsOfWeek[i].setBackground(bC);
			}else {
				labelsOfWeek[i].setBorder(new CompoundBorder(
						orangeborder,margin));
				labelsOfWeek[i].setBackground(gC);
			}
		}
		JPanel days = new JPanel(); 
		days.setLayout(new BoxLayout(days,BoxLayout.X_AXIS));
		for (int i = 0; i<5; i++){

			days.add(labelsOfWeek[i]);
		}
		
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
		contentPane.add(fieldPanel, BorderLayout.CENTER);
		contentPane.add(days, BorderLayout.NORTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
	}
	public static void main(String[] args){
		SecretaryCalandar calandar = new SecretaryCalandar(); 
		calandar.setVisible(true);
	}
	
}
