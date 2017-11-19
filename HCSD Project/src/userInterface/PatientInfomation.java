package userInterface;
import javax.swing.*; 
import java.awt.*;
public class PatientInfomation extends JFrame {
	
	
	//constructor 
	public PatientInfomation(){	
		//window properties 
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimensions = toolkit.getScreenSize();
		setTitle("Patient infomation");
		setSize(screenDimensions.width/2, screenDimensions.height/2); 
		setLocation(new Point(screenDimensions.width/4, screenDimensions.height/4));
		
		//add all names to combo box, click a name and click enter then open all the details about that patient
		JPanel panel = new JPanel(); 
		panel.setLayout(new FlowLayout());
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		JButton confirm = new JButton("Confirm"); 
		panel.add(comboBox);
		panel.add(confirm);
		//homepage button
		JButton back = new JButton("back");
		//patient details, make invisible until confirm has been clicked
		JTextField  outstandingPayment = new JTextField(20);
		JTextField healthcare = new JTextField(20);
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(0,2));
		infoPanel.add(new JLabel("Name:"));
		infoPanel.add(outstandingPayment);
		infoPanel.add(new JLabel("Healthcare plan"));
		infoPanel.add(healthcare);
		//Container
		Container contentPane = getContentPane(); 
		contentPane.setLayout(new BorderLayout());
		contentPane.add(panel, BorderLayout.WEST);
		contentPane.add(back, BorderLayout.SOUTH);
		
	}
	
	
	
	public static void main(String[] args){
		PatientInfomation info = new PatientInfomation();
		info.setVisible(true);
	}
}
