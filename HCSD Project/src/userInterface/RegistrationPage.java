package userInterface;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Point;

import javax.swing.*;

public class RegistrationPage extends JFrame {
	
	public RegistrationPage(){
		setTitle("Patient regsitration");
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		Dimension screenDimensions = toolkit.getScreenSize();
		setSize(screenDimensions.width/2, screenDimensions.height/2);
		setLocation(new Point(screenDimensions.width/4, screenDimensions.height/4));	
	}
	
	public static void main(String[] args) {
		RegistrationPage regPage = new RegistrationPage();
		regPage.setVisible(true);
	}
	
}
