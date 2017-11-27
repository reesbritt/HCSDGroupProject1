package userInterface;

import java.sql.*;

public class calculateCost {
	
	private static final int check_up = 1;
	private static final int hygiene = 2;
	private static final int silver = 3;
	private static final int white = 4;
	private static final int gold = 5;
	
	private static final int NHS = 1;				// 2 checkup, 2 hygiene, 6 repairs
	private static final int maintainence = 2;		// 2 checkup, 2 hygiene
	private static final int oral = 3;				// 2 checkup, 4 hygiene
	private static final int dental = 4;			// 2 checkup, 2 hygiene, 2 repairs
	
	public static int calculate(Integer patientID,Integer costTreatmentID ) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		Connection con = null;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String DB="jdbc:mysql://stusql.dcs.shef.ac.uk/team019?user=team019&password=6e84e2f3";
		con = DriverManager.getConnection(DB);
		
		String noTreatmentQuery; 
		Statement statement;
		ResultSet rs;
		
		statement = con.createStatement();
		noTreatmentQuery = "SELECT treatmentID_1 from Appointment where PatientID="+patientID;
		rs = statement.executeQuery(noTreatmentQuery);
		rs.next();
		
		int x = 0;
		int noTreatment = 0;
		
		while(rs.next()) {
			x = rs.getInt("treatmentID_1");
			if (x == costTreatmentID) {noTreatment += 1;}
			else if (costTreatmentID == 3 || costTreatmentID == 4 || costTreatmentID == 5) {
				if (x == 3 || x == 4 || x == 5){noTreatment += 1;}
			}
		}
		statement.close();
		
		Statement healthStatement;
		ResultSet hrs;
		String healthQuery;
		
		healthStatement = con.createStatement();
		healthQuery = "SELECT HealthcareID from Patient where PatientID="+patientID;
		hrs = healthStatement.executeQuery(healthQuery);
		hrs.next();
		int healthcare = hrs.getInt("HealthcareID");
		
		Statement costStatement;
		ResultSet crs;
		String costQuery;
		healthStatement.close();
		
		costStatement = con.createStatement();
		costQuery = "SELECT TreatmentID, Cost from Treatment";
		crs = costStatement.executeQuery(costQuery);
		
		int checkUpCost = 0;
		int hygieneCost = 0;
		int silverCost = 0;
		int whiteCost = 0;
		int goldCost = 0;
		
		while(crs.next()) {
			if (crs.getInt("TreatmentID") == check_up) {checkUpCost = crs.getInt("Cost");}
			else if (crs.getInt("TreatmentID") == hygiene) {hygieneCost = crs.getInt("Cost");}
			else if (crs.getInt("TreatmentID") == silver) {silverCost = crs.getInt("Cost");}
			else if (crs.getInt("TreatmentID") == white) {whiteCost = crs.getInt("Cost");}
			else if (crs.getInt("TreatmentID") == gold) {goldCost = crs.getInt("Cost");}
		}
		
		int repairPrice = repairCost(costTreatmentID, silverCost, whiteCost, goldCost);
		int noTreatments = 0;
		
		con.close();
		if (costTreatmentID == check_up) {
			if (healthcare == 0) {return checkUpCost;}
			else if (noTreatments > 1) {return checkUpCost;}
			else {return 0;}
		}
		else if (costTreatmentID == silver || costTreatmentID == white || costTreatmentID == gold) {
			if (healthcare != NHS || healthcare != dental) {return repairPrice;}
			else if (noTreatments > 1 && healthcare != NHS) {return repairPrice;}
			else if (noTreatments > 5) {return repairPrice;}
			else {return 0;}
		}
		else if (costTreatmentID == hygiene) {
			if (healthcare == 0) {return hygieneCost;}
			else if (noTreatments > 1 && healthcare != oral) {return hygieneCost;}
			else if (noTreatments > 3 ) {return hygieneCost;}
			else {return 0;}
		}
		else {return 0;}
	}
	
	public static int repairCost(int treatmentID, int silverCost, int whiteCost, int goldCost) {
		if (treatmentID == silver) {return silverCost;}
		else if (treatmentID == white) {return whiteCost;}
		else if (treatmentID == gold) {return goldCost;}
		else {return 0;}
	}
}
