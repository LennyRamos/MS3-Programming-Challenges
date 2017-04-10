# MS3-Programming-Challenges
Java application that will consume a CSV file, parse the data and insert to a SQLlite In-Memory database.


The DDL for the SQLite Table

Create Table Users 
      ( FirstName       VARCHAR(20), 
        LastName 	      VARCHAR(20), 
        Email 	      VARCHAR(35),      
        Gender 	      CHAR(6), 
        Image 	      TEXT, 
        PaymentType     VARCHAR(35), 
        AmountPaid 	CHAR(7), 
        Validator1 	BOOLEAN, 
        Validator2	BOOLEAN, 
        City 		VARCHAR );

//Reason for the different types of Datatypes is because I didn’t know exactly how long 
//each column entry would be 
//Because I knew beforehand that there were missing values, I did not know which 
//column to make the Primary Key

The only external library I used was sqlite-jdbc-3.16.1.jar.

The application so far consumes the file, parses it, and writes all the bad entries in to a bad entry <timestamp>.csv. It also creates the log file to tell the user how many files were received, how many have passed and failed. 

The only thing that I am running into trouble is writing all the parsed information into SQLite database Users table I created. 

I still plan on continuing this project so that everything is fully functional. I need to refactor my code so that it has higher cohesion while less coupling, perhaps by implementing some interfaces.

