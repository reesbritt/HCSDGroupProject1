package userInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class SubPage extends JFrame  {
	public SubPage() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimensions = toolkit.getScreenSize();
		setTitle("Treatment review ");
		setSize(screenDimensions.width/2, screenDimensions.height/2); 
		setLocation(new Point(screenDimensions.width/4, screenDimensions.height/4));
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JLabel label = new JLabel("Choose subscription plan");
		JComboBox box = new JComboBox();
		panel.add(label);
		panel.add(box);
		//button panel
		JPanel buttonPanel = new JPanel();
		JButton confirm = new JButton("Confirm");
		buttonPanel.add(confirm);
		//container
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.add(panel, BorderLayout.CENTER);
		container.add(confirm, BorderLayout.SOUTH);
	
	}
	public static void main(String[] args) {
		SubPage sub = new SubPage();
		sub.setVisible(true);
	}
}
