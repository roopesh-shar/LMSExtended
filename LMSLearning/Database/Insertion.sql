insert into users values (1,'testuser','testpassword',1);
commit;
/


insert into profile values (1,1,'STUDENT','TESTSTUDENTFNAME','TESTSTUDENTLNAME','TESTFATHERNAME','testemail@test.com',9873569464,'test Address');
commit;
/


insert into course values (1,'Javatest',50000,90,'test content');
commit;
/

insert into faculty values (1,'TESTFACULTYFNAME','TESTFACULTYLNAME','testfacemail@test.com',9873569464,'test Address');
commit;
/



insert into Batch values(1,'test-batch-001');
commit;
/


insert into assignment_question values (1,1,'test question');
commit;
/


insert into Feedback values (1, 1, 'course related', 'test feedback', '1');
commit;
/


insert into Assignment values (1,1,1,1,'Test Answer');
commit;
/

insert into schedule values (1,1,1,'10-NOV-2016','10-FEB-2017');
commit;
/

insert into evaluation values (1,1,1,1,'11-may-2016',500,400,80.56,'B++');
commit;
/

insert into fees values (1,1,1,50000,40000,10000);
commit;
/

insert into LMS.Course values((select max(id) from LMS.course)+1, 'Core Java', 60000, 120, 'test Core Java content') ; 
insert into LMS.Course values((select max(id) from LMS.course)+1, 'J2EE', 60000, 120, 'test J2EE content') ; 
insert into LMS.Course values((select max(id) from LMS.course)+1, 'Big Data And Hadoop', 60000, 120, 'test Big Data And Hadoop content') ;
insert into LMS.Course values((select max(id) from LMS.course)+1, 'Android', 60000, 120, 'test Android content') ;
insert into LMS.Course values((select max(id) from LMS.course)+1, '.NET', 60000, 120, 'test .NET content') ;
commit;
/