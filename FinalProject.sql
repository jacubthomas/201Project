DROP DATABASE IF EXISTS FinalProject;
CREATE DATABASE FinalProject;

Use FinalProject;

CREATE TABLE Security (
	SecurityID INT Primary KEY AUTO_INCREMENT,
    SecurityQuestion VARCHAR(100) NOT NULL,
    CustomAnswer VARCHAR(100) NOT NULL
);

CREATE TABLE Users (
	UserID INT PRIMARY KEY AUTO_INCREMENT,
    PhotoID INT NOT NULL,
    username VARCHAR(15) NOT NULL,
    password_ VARCHAR(15) NOT NULL,
    firstname VARCHAR(15) NOT NULL,
    lastname VARCHAR(15) NOT NULL,
    joined_on VARCHAR(15) NOT NULL,
    date_of_birth VARCHAR(15) NOT NULL,
    SecurityID INT NOT NULL,
    FOREIGN KEY fk1(SecurityID) references Security(SecurityID),
    public_status INT NOT NULL #1 = public, 2 = private
);

CREATE TABLE Friends (
	FriendsID INT PRIMARY KEY AUTO_INCREMENT,
    UserID1 INT NOT NULL,
    FOREIGN KEY fk1(UserID1) references Users(UserID),
    UserID2 INT NOT NULL,
    FOREIGN KEY fk2(UserID2) references Users(UserID)
);

CREATE TABLE Posts (
	PostID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT NOT NULL,
    FOREIGN KEY fk1(UserID) references Users(UserID),
    security_status INT NOT NULL, #1 = public, 2 = private
    time_stamp VARCHAR(30) NOT NULL,
    likes INT NOT NULL,
    shares INT NOT NULL
);

CREATE TABLE Comments (
	CommentID INT PRIMARY KEY AUTO_INCREMENT,
    PostID INT NOT NULL,
    FOREIGN KEY fk1(PostID) references Posts(PostID),
    UserID INT NOT NULL,
    FOREIGN KEY fk2(UserID) references Users(UserID),
    text_ VARCHAR(100) NOT NULL,
    time_stamp VARCHAR(30) NOT NULL
);

CREATE TABLE Photos (
	PhotoID INT PRIMARY KEY AUTO_INCREMENT,
    PostID INT NOT NULL,
    FOREIGN KEY fk1(PostID) references Posts(PostID),
    UserID INT NOT NULL,
    FOREIGN KEY fk2(UserID) references Users(UserID),
    time_stamp VARCHAR(30) NOT NULL
);

