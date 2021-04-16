DROP DATABASE IF EXISTS FinalProject;
CREATE DATABASE FinalProject;

Use FinalProject;

CREATE TABLE Users (
	UserID INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(15) NOT NULL,
    password_ VARCHAR(15) NOT NULL,
    firstname VARCHAR(15) NOT NULL,
    lastname VARCHAR(15) NOT NULL,
    joined_on VARCHAR(15) NOT NULL,
    date_of_birth VARCHAR(15) NOT NULL,
    bio VARCHAR(300) NOT NULL,		
    public_status INT NOT NULL #1 = public, 2 = friends only
);

CREATE TABLE Security (
	SecurityID INT Primary KEY AUTO_INCREMENT,
    UserID INT NOT NULL,
    FOREIGN KEY fk1(UserID) references Users(UserID),
    SecurityQuestion VARCHAR(100) NOT NULL,
    CustomAnswer VARCHAR(100) NOT NULL
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
    postText VARCHAR(100) NOT NULL,
    FOREIGN KEY fk1(UserID) references Users(UserID),
    security_status INT NOT NULL, #1 = public, 2 = friends only (private), 3 = private message
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
    PhotoData LONGBLOB,
    FOREIGN KEY fk1(PostID) references Posts(PostID),
    UserID INT NOT NULL,
    FOREIGN KEY fk2(UserID) references Users(UserID),
    time_stamp VARCHAR(30) NOT NULL
);

CREATE TABLE Trend (
	TrendID INT PRIMARY KEY AUTO_INCREMENT,
    TrendName VARCHAR(15) NOT NULL,
    PostID INT NOT NULL,
    FOREIGN KEY fk1(PostID) references Posts(PostID)
);

