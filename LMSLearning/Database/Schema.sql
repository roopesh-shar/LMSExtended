--- Create USER DB ----
CREATE user lms identified by lmsroot;
grant all privileges to lms;
/
------------------------------------



CREATE TABLE LMS.USERS(
id number(10) NOT NULL PRIMARY KEY,
USER_NAME varchar(50) unique not null,
Password varchar(50) not null,
is_active number(1) check (is_active in (0,1))
);
/



CREATE TABLE LMS.PROFILE(
id number(10) NOT NULL PRIMARY KEY,
User_ID number(10) NOT NULL,
type varchar(20),
First_name varchar(50) not null,
Last_name varchar(50) not null,
Father_name varchar(100),
Email_id varchar(50) not null,
Contact_number varchar(10) not null,
Address Varchar(200)
);
/

CREATE TABLE LMS.COURSE(
id number(10) NOT NULL PRIMARY KEY ,
Course_name varchar(50) NOT NULL ,
Course_fees number(10) NOT NULL ,
course_duration NUMBER(10),
Course_Content VARCHAR (2000)
);
/

CREATE TABLE LMS.FACULTY(
id number(10) not null primary key,
First_name varchar(50) not null,
Last_name varchar(50) not null,
Faculty_Email_id varchar(50) not null,
Faculty_Contact_number varchar(10) not null,
Faculty_Address Varchar(200)
);
/

CREATE TABLE LMS.SCHEDULE(
Course_Id number(10) not null,
Batch_id number(5) not null,
user_id number(10),
start_date date not null,
end_date date not null
);
/
CREATE TABLE LMS.BATCH(
id number(5) primary key,
BATCH_CODE varchar(20)
);
/

CREATE TABLE LMS.EVALUATION(
id number(10) not null primary key,
user_id number(10),
Batch_id number(5) not null, 
Course_Id number(10) not null,
Submission_date date,
Total_marks number(5),
Obtained_marks number(5),
percentage number(5),
Grade varchar(10)
);
/

CREATE TABLE LMS.Assignment(
user_id number(10),
Batch_id number(5) not null, 
Course_Id number(10) not null,
Question_id number(10) not null,
Submitted_answer varchar(2000)
);
/

CREATE TABLE LMS.Assignment_Question(
id number(10) not null primary key,
Course_Id number(10) not null,
Question varchar(500)
);

/

Create table LMS.FEEDBACK(
id number(10) primary key,
Course_Id number(10) not null,
Feedback_area varchar(20) not null,
Feedback varchar(2000) not null,
Is_Approved number(1) 
);

CREATE table LMS.Fees(
id number(10) primary key,
user_id number(10),
Course_Id number(10) not null,
Course_fees number(10),
Fees_Submitted number(10),
Fees_remaining number(10)
);
/

--- Alter Table for Profile ---

ALTER TABLE LMS.PROFILE ADD Country varchar(20);
ALTER TABLE LMS.PROFILE ADD State varchar(20);
ALTER TABLE LMS.PROFILE ADD City varchar(20);
ALTER TABLE LMS.PROFILE ADD Pincode number(6);
/

-- Alter table Profile to Add User_id and Course ID--
Alter Table Feedback add user_id number(10) not null;
alter table profile add Course_id number(10);
/