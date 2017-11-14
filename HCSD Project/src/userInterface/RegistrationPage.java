package userInterface;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Point;

import javax.swing.*;

public class RegistrationPage extends JFrame {
	
	public RegistrationPage(){
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		JButton button = new JButton("Submit");
		
		JComboBox title = new JComboBox();
		title.addItem("Mr");
		title.addItem("Mrs");
		title.addItem("Miss");
		title.addItem("Ms");
		title.addItem("Dr");
		
		JComboBox dayOB = new JComboBox();
		
		dayOB.addItem("1");
		
		JTextField firstName = new JTextField(20);
		JTextField surname = new JTextField(20);
		
		
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(0,2));
		formPanel.add(new JLabel("Title:"));
		formPanel.add(title);
		formPanel.add(new JLabel("Forename:"));
		formPanel.add(firstName);
		formPanel.add(new JLabel("Surname:"));
		formPanel.add(surname);
		formPanel.add(new JLabel("Day of birth"));
		formPanel.add(dayOB);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(button,BorderLayout.SOUTH);
		contentPane.add(formPanel, BorderLayout.CENTER);
		
		//Window properties
		setTitle("Patient regsitration");
		Dimension screenDimensions = toolkit.getScreenSize();
		setSize(screenDimensions.width/2, screenDimensions.height/2);
		setLocation(new Point(screenDimensions.width/4, screenDimensions.height/4));	
	}
	
	public static void main(String[] args) {
		RegistrationPage regPage = new RegistrationPage();
		regPage.setVisible(true);
	}
	
}
