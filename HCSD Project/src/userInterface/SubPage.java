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
		
		
	}
}
