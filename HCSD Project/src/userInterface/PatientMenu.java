package userInterface;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class PatientMenu extends JFrame{
	public PatientMenu() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimensions = toolkit.getScreenSize();
		setTitle("Existing patient menu ");
		setSize(screenDimensions.width/2, screenDimensions.height/2); 
		setLocation(new Point(screenDimensions.width/4, screenDimensions.height/4));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0,2));
		
		JButton info = new JButton("View patient information");
		JButton subscribe = new JButton("Subscribe patient to a healthcare plan");
		JButton appointment = new JButton("Book appointment");
		JButton home = new JButton("Home");
		
		buttonPanel.add(info);
		buttonPanel.add(subscribe);
		buttonPanel.add(appointment);
		buttonPanel.add(home);
		//event listeners
		info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				PatientInfomation.main(new String[] {});
			}
		});
		subscribe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubPage.main(new String[] {});
			}
		});
		appointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookAppointment.main(new String[] {});
			}
		});
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPage.main(new String[] {});
			}
		});
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(buttonPanel, BorderLayout.CENTER);
		
	}
	public static void main(String[] args) {
		PatientMenu p = new PatientMenu();
		p.setVisible(true);
	}
}
