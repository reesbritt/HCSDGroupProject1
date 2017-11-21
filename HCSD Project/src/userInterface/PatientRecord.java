package userInterface;
import java.awt.*;
import javax.swing.*; 
public class PatientRecord extends JFrame {
	//variables
	
	//methods 
	public PatientRecord(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimensions = toolkit.getScreenSize();
		setTitle("Record");
		setSize(screenDimensions.width/2, screenDimensions.height/2); 
		setLocation(new Point(screenDimensions.width/4, screenDimensions.height/4));
	}
	public static void Main(String[] args){
		PatientRecord record = new PatientRecord();
		record.setVisible(true);
	}
}
