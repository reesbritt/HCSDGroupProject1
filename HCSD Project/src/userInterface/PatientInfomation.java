package userInterface;
import javax.swing.*; 

import java.awt.*;
import java.awt.event.*; 
public class PatientInfomation extends JFrame {
	
	//variables 
	private static BookAppointment bA = new BookAppointment(); 
	private static PatientInfomation info = new PatientInfomation();
	//constructor 
	public PatientInfomation(){	
		bA.setVisible(false);
		//window properties 
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimensions = toolkit.getScreenSize();
		setTitle("Patient infomation");
		setSize(screenDimensions.width/2, screenDimensions.height/2); 
		setLocation(new Point(screenDimensions.width/4, screenDimensions.height/4));
		
		//patient details, make invisible until confirm has been clicked
		JTextField  name = new JTextField(20);
		JTextField healthcare = new JTextField(20);
		JTextField outstandingPayment = new JTextField(20);
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(0,2));
		infoPanel.add(new JLabel("Name:"));
		infoPanel.add(name);
		infoPanel.add(new JLabel("Healthcare plan"));
		infoPanel.add(healthcare);
		infoPanel.add(new JLabel("Outstanding payments:  ")); 
		infoPanel.add(outstandingPayment);
		infoPanel.setVisible(false);
		
		//add all names to combo box, click a name and click enter then open all the details about that patient
		JPanel panel = new JPanel(); 
		panel.setLayout(new FlowLayout());
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		JButton confirm = new JButton("Confirm"); 
		//event listener to show patient information 
		confirm.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				infoPanel.setVisible(true);
				comboBox.setVisible(false);
			}
		});

		panel.add(comboBox);
		panel.add(confirm);
		//BUTTON TO CHANGE TO APPOINTMENT PAGE 
		JButton bookappointment = new JButton("Book next appointment"); 
		bookappointment.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bA.setVisible(true);
				info.setVisible(false);
			}
		});
		panel.add(bookappointment);
		
		
		
		
		
		//add button to
		JButton back = new JButton("back");
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				infoPanel.setVisible(false);
				comboBox.setVisible(true);
			}
		});
		//Container
		Container contentPane = getContentPane(); 
		contentPane.setLayout(new BorderLayout());
		contentPane.add(panel, BorderLayout.WEST);
		contentPane.add(back, BorderLayout.SOUTH);
		contentPane.add(infoPanel,BorderLayout.CENTER);
	}
	
	
	
	public static void main(String[] args){
		info.setVisible(true);
	}
}
