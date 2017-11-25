package userInterface;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		JLabel t = new JLabel("Treatment");
		JComboBox treatmentBox = new JComboBox();
		
		JLabel message = new JLabel("Appointment has been booked");
		message.setVisible(false);
		JButton bookAgain = new JButton("Back");
		bookAgain.setVisible(false);
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton confirmAppointment = new JButton("Confirm Appointment");
		confirmAppointment.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
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
		appointmentPanel.add(new JLabel("Day"));
		appointmentPanel.add(day);
		appointmentPanel.add(new JLabel("Month"));
		appointmentPanel.add(month);	
		appointmentPanel.add(t);
		appointmentPanel.add(treatmentBox);
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
