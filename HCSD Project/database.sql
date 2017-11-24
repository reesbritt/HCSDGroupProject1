BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `treatment` (
	`Name`	INTEGER,
	`Cost`	INTEGER,
	`PartnerID`	INTEGER
);
CREATE TABLE IF NOT EXISTS `System` (
	`SystemID`	INTEGER
);
CREATE TABLE IF NOT EXISTS `Receptionist` (
	`ReceptionistID`	INTEGER UNIQUE,
	`Appointments`	INTEGER,
	`PartnerID`	INTEGER,
	PRIMARY KEY(`ReceptionistID`)
);
CREATE TABLE IF NOT EXISTS `Receipt` (
	`Totalcost`	INTEGER,
	`PatientID`	INTEGER,
	`Date`	INTEGER,
	`SystemID`	INTEGER
);
CREATE TABLE IF NOT EXISTS `Patient` (
	`PatientID`	 INTEGER,
    `Title`  	 VarChar(4),
    `DOB`		 Date,
    `First name` VarChar(20),
    `Surname`	 VarChar(20),
    `Health plan`VarChar(1),
    
	PRIMARY KEY(`PatientID`)
);
CREATE TABLE IF NOT EXISTS `Hygienist` (
	`PartnerID`	INTEGER,
	`Appointment`	INTEGER
);
CREATE TABLE IF NOT EXISTS `Healthcare` (
	`Field1`	INTEGER
);
CREATE TABLE IF NOT EXISTS `Dentist` (
	`Appointments`	INTEGER,
	`PartnerID`	INTEGER UNIQUE,
	PRIMARY KEY(`PartnerID`)
);
CREATE TABLE IF NOT EXISTS `Appointment` (
	`Field1`	INTEGER
);
COMMIT;
