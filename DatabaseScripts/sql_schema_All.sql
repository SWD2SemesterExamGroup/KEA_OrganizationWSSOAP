-- Setup and install a new Database with startup setting --
## Database creation of Course Registration
create database if not exists KEA_Organization_DB;

use KEA_Organization_DB;

## Table Priviledges
create table if not exists Priviledges (
	ID int auto_increment primary key,
	pLevel varchar(25) not null unique
);

## Table KEA_Deparments
create table if not exists KEA_Departments (
	ID tinyint primary key auto_increment,
	Name varchar(50) not null default 'Digital'
);

## Table Accounts
create table if not exists Accounts (
	ID bigint auto_increment,
	Email varchar(150),
	Username varchar(150) not null,
	PriviledgeID int,
	Password varchar(150) not null,
	primary key(ID, Email),
	foreign key(PriviledgeID) REFERENCES Priviledges(ID)
);

# Table of Classes. Ex. SWD Team 10
creATE TABLE if not exists Classes (
	ID bigint auto_increment primary key,
	Title varchar(50) not null,
	isFall bit default 0,
	Year varchar(4) default '2018',
	DepartmentNo tinyint default 1,
	foreign Key(DepartmentNo) references KEA_Departments(ID)
);

## Table Teachers
create table if not exists Teachers (
	ID int auto_increment,
	Email varchar(150),
	Name varchar(150) not null,
	DepartmentNo tinyint default 1,
	primary key(ID, Email),
	foreign key(DepartmentNo) references KEA_Departments(ID)
);

## Table Courses
create table if not exists Courses (
	ID bigint auto_increment primary key,
	TeacherIDOne int,
	TeacherIDTwo int default null,
	ClassID bigint,
	Title varchar(50) not null,
	ETCS tinyint not null default 5,
	foreign Key(TeacherIDOne) references Teachers(ID),
	foreign Key(TeacherIDTwo) references Teachers(ID),
	foreign Key(ClassID) references Classes(ID)
);

## Table Students
create table if not exists Students (
	ID bigint auto_increment,
	Email varchar(150),
	Name varchar(150) not null,
	ClassID bigint,
	primary key(ID, Email),
	foreign key(ClassID) references Classes(ID)
);

## Table StudentCourses
create table if not exists StudentCourses (
	StudentID bigint,
	CourseID bigint,
	primary key(StudentID, CourseID),
	foreign key(StudentID) references Students(ID),
	foreign key(CourseID) references Courses(ID)
);

-- DML Data --
## Data to be inserted into KEA_Organization_DB.KEA_Departments
insert ignore into KEA_Departments(Name) values('Digital');

## Data to be inserted into KEA_Organization_DB.Classes
insert ignore into Classes(Title) value("SWD Team 11");
insert ignore into Classes(Title) value("Datamatiker 17B");

## Data to be inserted into KEA_Organization_DB.Priviledges
insert ignore into Priviledges(pLevel) values('admin');
insert ignore into Priviledges(pLevel) values('student');
insert ignore into Priviledges(pLevel) values('teacher');
insert ignore into Priviledges(pLevel) values('kea_administrator');

## Data to be inserted into KEA_Organization_DB.Teachers
insert ignore into Teachers(Email, Name, DepartmentNo) values('trhj@kea.dk', 'Troels Helbo Jensen', 1);
insert ignore into Teachers(Email, Name, DepartmentNo) values('asba@kea.dk', 'Asger Batting', 1);

## Data to be inserted into KEA_Organization_DB.Accounts
insert ignore into Accounts(Email, Username, PriviledgeID, Password) values('trhj@kea.dk', 'trhj', 1, '12345678');
insert ignore into Accounts(Email, Username, PriviledgeID, Password) values('troe2725@stud.kea.dk', 'troe2725', 2, '1234');
insert ignore into Accounts(Email, Username, PriviledgeID, Password) values('asba@kea.dk', 'asba', 2, '12345678');

## Data to be inserted into KEA_Organization_DB.Courses
insert ignore into Courses(TeacherIDOne, ClassID, Title, ETCS) values(1, 1, 'Construction 1', 15);
insert ignore into Courses(TeacherIDOne, ClassID, Title, ETCS) values(2, 1, 'Design 1', 10);
insert ignore into Courses(TeacherIDOne, ClassID, Title, ETCS) values(1, 2, 'Python', 5);
insert ignore into Courses(TeacherIDOne, ClassID, Title, ETCS) values(2, 2, 'Patterns', 5);

## Data to be inserted into KEA_Organization_DB.Students
insert ignore into Students(Email, Name, ClassID) values('troe2725@stud.kea.dk', 'Troels Helbo Jensen', 1);

## Data to be inserted into KEA_Organization_DB.StudentCourses
insert ignore into StudentCourses values(1, 1);
insert ignore into StudentCourses values(1, 2);

-- Views --

-- Techer requesting Course and Class List
-- IN: TeacherID
-- OUT: Course List (ID, Title) join by Class(ID, Name)

-- AS VIEW
create view v_getCourseClassList as

select t.ID as TeacherID, t.Name as TeacherName, crs.ID as CourseID,
		crs.Title as CourseTitle,
		cls.ID as ClassID,
		cls.Title as ClassTitle
	from Teachers as t 
	join Courses as crs 
		on t.ID = crs.TeacherIDOne
	join Classes as cls
		on crs.ClassID = cls.ID;

-- Logins --
-- User Creation for service login
	-- Link: https://dev.mysql.com/doc/refman/5.5/en/adding-users.html
-- Adding Users
CREATE user IF NOT EXISTS 'teacher'@'localhost' identified by 'pw1234';

-- Grant priviledges
	-- List Link: https://dev.mysql.com/doc/refman/5.5/en/privileges-provided.html
grant select, show view on KEA_Organization_DB.v_getCourseClassList to 'teacher'@'localhost';