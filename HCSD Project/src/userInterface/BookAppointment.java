package userInterface;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class BookAppointment extends JFrame {
	public BookAppointment(){
		//window properties 
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimensions = toolkit.getScreenSize();
		setTitle("Patient infomation");
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
		JLabel message = new JLabel("Appointment has been booked");
		message.setVisible(false);
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton confirmAppointment = new JButton("Confirm Appointment");
		confirmAppointment.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				message.setVisible(true);
				appointmentPanel.setVisible(false);
				confirmAppointment.setVisible(false);
			}
		});
		
		
		appointmentPanel.add(new JLabel("Day"));
		appointmentPanel.add(day);
		appointmentPanel.add(new JLabel("Month"));
		appointmentPanel.add(month);	
		//add buttons
		buttonPanel.add(confirmAppointment);
		buttonPanel.add(message);
		//container
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.add(appointmentPanel, BorderLayout.CENTER);
		container.add(buttonPanel, BorderLayout.NORTH);
	}
}
