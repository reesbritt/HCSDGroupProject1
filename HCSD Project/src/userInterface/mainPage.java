package userInterface;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class mainPage extends JFrame {
	
	private static BookAppointment appointmentScreen = new BookAppointment();
	public mainPage(){
		
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0,2));
		
		JButton register = new JButton("Register client");
		JButton calender = new JButton("Open calender");
		JButton patientDeets = new JButton("Check patient details");
		JButton appointment = new JButton("Book appointment");
		
		buttonPanel.add(register);
		buttonPanel.add(calender);
		buttonPanel.add(patientDeets);
		buttonPanel.add(appointment);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(buttonPanel, BorderLayout.CENTER);
		
		
		setTitle("Patient regsitration");
		Dimension screenDimensions = toolkit.getScreenSize();
		setSize(screenDimensions.width/2, screenDimensions.height/2);
		setLocation(new Point(screenDimensions.width/4, screenDimensions.height/4));
		
		register.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				RegistrationPage.main(new String[] {});
			}
		});
		
		calender.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				SecretaryCalandar.main(new String[] {});
			}
		});
		
		patientDeets.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				PatientInfomation.main(new String[] {});
			}
		});
		
		appointment.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				appointmentScreen.setVisible(true);
			}
		});
		
	}
	
	public static void main(String[] args) {
		mainPage mainPage = new mainPage();
		mainPage.setVisible(true);
	}
}
