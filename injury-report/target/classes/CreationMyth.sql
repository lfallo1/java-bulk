/*CREATE DATABASE injury;
USE injury;
CREATE TABLE position (
	position_id INT NOT NULL AUTO_INCREMENT,
	position_name VARCHAR(45) NOT NULL,
	PRIMARY KEY (position_id)
);
CREATE TABLE employee (
	employee_id INT NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(45) NOT NULL,
	last_name VARCHAR(45) NOT NULL,
	position_id INT NOT NULL,
	PRIMARY KEY (employee_id),
	FOREIGN KEY (position_id) REFERENCES position (position_id)
);
CREATE TABLE role (
	role_id INT NOT NULL AUTO_INCREMENT,
	role_name VARCHAR(45) NOT NULL,
	PRIMARY KEY (role_id)
);
CREATE TABLE user (
	user_id INT NOT NULL AUTO_INCREMENT,
	user_name VARCHAR(45) NOT NULL,
	user_pass VARCHAR(45) NOT NULL,
	role_id INT NOT NULL,
	employee_id INT NOT NULL,
	active BIT NOT NULL,
	PRIMARY KEY (user_id),
	FOREIGN KEY (role_id) REFERENCES role (role_id),
	FOREIGN KEY (employee_id) REFERENCES employee (employee_id)
);
CREATE TABLE bodypart (
	bodypart_id INT NOT NULL AUTO_INCREMENT,
	bodypart_name VARCHAR(45),
	PRIMARY KEY (bodypart_id)
);
CREATE TABLE injurytype (
	type_id INT NOT NULL AUTO_INCREMENT,
	type_name VARCHAR(45),
	PRIMARY KEY (type_id)
);
CREATE TABLE WEATHER (
	weather_id INT NOT NULL AUTO_INCREMENT,
	weather_condition VARCHAR(45) NOT NULL,
	PRIMARY KEY (weather_id)
);
CREATE TABLE REPORT (
	report_id INT NOT NULL AUTO_INCREMENT,
	employee INT NOT NULL,
	reporter INT NOT NULL,
	weather INT NOT NULL,
	bodypart INT NOT NULL,
	injury_type INT NOT NULL,
	approver INT NOT NULL,
	date_of_report DATE NOT NULL,
	date_reported_to_management DATE NOT NULL,
	time_of_injury TIME NOT NULL,
	description VARCHAR(1000),
	approver_comments VARCHAR(1000),
	date_updated DATE,
	enabled BIT NOT_NULL,
	PRIMARY KEY (report_id),
	FOREIGN KEY (employee) REFERENCES employee (employee_id),
	FOREIGN KEY (reporter) REFERENCES user (user_id),
	FOREIGN KEY (weather) REFERENCES weather (weather_id),
	FOREIGN KEY (bodypart) REFERENCES bodypart (bodypart_id),
	FOREIGN KEY (injury_type) REFERENCES injurytype (type_id),
	FOREIGN KEY (approver) REFERENCES user (user_id)
);
INSERT INTO weather (weather_condition)
VALUES ("N/A"),("Clear"),("Overcast"),("Foggy"),("Rain"),("Snow"),("Ice");
INSERT INTO injurytype (type_name)
VALUES ("Slip/Fall"),("Cut"),("Lift/Lower"),("Struck By"),("Struck Against");
INSERT INTO bodypart (bodypart_name)
VALUES ("Head"),("Arm"),("Torso"),("Leg"),("Foot"),("Finger"),("Toe"),("Hand"),("Shoulder"),("Knee"),("Neck");
INSERT INTO position (position_name)
VALUES ("Management"),("Dock Loader"),("Dock Unloader"),("Salesperson"),("Janitor"),("Clerk"),("Stockperson");
INSERT INTO role (role_name)
VALUES ("Admin"),("Manager");*/