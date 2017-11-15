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
		//Colours to be used 
		Color bC = new Color(152, 175, 199);
		Color gC = new Color(199, 176, 151);
		Color ggC = new Color(150, 197, 173);
		Color ttC = new Color(197, 150, 173);
	
		//add text fields
		JTextField fTexts[] = new JTextField[5];
		for (int i = 0; i < 5; i++){
			fTexts[i] = new JTextField(20);
			fTexts[i].setBackground(ggC);
		}
		//field panels 
		JPanel fieldPanel = new JPanel(); 
		fieldPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		for (int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				fieldPanel.setOpaque(false);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = j;
				c.gridy = i;
				c.ipady = 30;
				c.ipadx = 100;
				c.ipadx = c.ipadx + 50;
				fieldPanel.add(fTexts[i], c);
				
			}
		}
		Border blueborder = BorderFactory.createLineBorder(Color.BLUE, 1);
		Border orangeborder = BorderFactory.createLineBorder(Color.ORANGE,1);
		Border margin = new EmptyBorder(10,50,10,50);
		String daysOfWeek[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
		
		
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
		contentPane.setBackground(bC);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
	}
	public static void main(String[] args){
		SecretaryCalandar calandar = new SecretaryCalandar(); 
		calandar.setVisible(true);
	}
	
}
