
CREATE TABLE LMS.USERS(
id INTEGER PRIMARY KEY,
USER_NAME varchar(50) unique not null,
Password varchar(50) not null,
is_active numeric(1) check (is_active in (0,1)),
name varchar(50) not null,
courseId numeric(3) not null,
email varchar(30) not null,
streetAddress1 varchar(100) not null,
streetAddress2 varchar(100),
mobileNum numeric(10) not null,
city varchar(50) not null,
state varchar(10) not null,
zipCode numeric(7) not null,
country varchar(10) not null
);
/



CREATE TABLE LMS.PROFILE(
id numeric(10) NOT NULL PRIMARY KEY,
User_ID numeric(10) NOT NULL,
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
id numeric(10) NOT NULL PRIMARY KEY ,
Course_name varchar(50) NOT NULL ,
Course_fees numeric(10) NOT NULL ,
course_duration numeric(10),
Course_Content VARCHAR (2000)
);
/

CREATE TABLE LMS.FACULTY(
id numeric(10) not null primary key,
First_name varchar(50) not null,
Last_name varchar(50) not null,
Faculty_Email_id varchar(50) not null,
Faculty_Contact_number varchar(10) not null,
Faculty_Address Varchar(200)
);
/

CREATE TABLE LMS.SCHEDULE(
Course_Id numeric(10) not null,
Batch_id numeric(5) not null,
user_id numeric(10),
start_date date not null,
end_date date not null
);
/
CREATE TABLE LMS.BATCH(
id numeric(5) primary key,
BATCH_CODE varchar(20)
);
/

CREATE TABLE LMS.EVALUATION(
id numeric(10) not null primary key,
user_id numeric(10),
Batch_id numeric(5) not null, 
Course_Id numeric(10) not null,
Submission_date date,
Total_marks numeric(5),
Obtained_marks numeric(5),
percentage numeric(5),
Grade varchar(10)
);
/

CREATE TABLE LMS.Assignment(
user_id numeric(10),
Batch_id numeric(5) not null, 
Course_Id numeric(10) not null,
Question_id numeric(10) not null,
Submitted_answer varchar(2000)
);
/

CREATE TABLE LMS.Assignment_Question(
id numeric(10) not null primary key,
Course_Id numeric(10) not null,
Question varchar(500)
);

/

Create table LMS.FEEDBACK(
id numeric(10) primary key,
Course_Id numeric(10) not null,
Feedback_area varchar(20) not null,
Feedback varchar(2000) not null,
Is_Approved numeric(1) 
);

CREATE table LMS.Fee(
id numeric(10) primary key,
user_id numeric(10),
Course_Id numeric(10) not null,
Course_fees numeric(10),
Fee_Submitted numeric(10),
Fee_remaining numeric(10)
);
/

--- Alter Table for Profile ---

ALTER TABLE LMS.PROFILE ADD Country varchar(20);
ALTER TABLE LMS.PROFILE ADD State varchar(20);
ALTER TABLE LMS.PROFILE ADD City varchar(20);
ALTER TABLE LMS.PROFILE ADD Pincode numeric(6);
/

-- Alter table Profile to Add User_id--
Alter Table Feedback add user_id numeric(10) not null;
/
