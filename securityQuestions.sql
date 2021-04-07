DROP DATABASE if exists finalproject;
CREATE DATABASE finalproject;
USE finalproject;

CREATE TABLE SecurityQuestions(
	securityQuestionID int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    question varchar(75) NOT NULL
);

INSERT INTO SecurityQuestions (question) VALUES ("What is your mother's maiden name?"), ("What is the name of your first pet?"), 
("What was your first car?"), ("What elementary school did you attend?"), ("What is the name of the city where you were born?"),
("What high school did you attend?"), ("What is the name of the road you grew up on?"), ("What is the name of your favorite childhood friend?"),
("In what city did you meet your spouse/significant other?"), ("In what city or town was your first job?");


