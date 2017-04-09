# MS3-Programming-Challenges
Java application that will consume a CSV file, parse the data and insert to a SQLlite In-Memory database.

The DDL for the SQLite Table

Create Table Users
(
      FirstName   VARCHAR(20),
      LastName    VARCHAR(20),
      Email       VARCHAR(35), 
      Gender      CHAR(6), 
      Image       TEXT, 
      PaymentType VARCHAR(35), 
      AmountPaid  CHAR(7), 
      Validator1  BOOLEAN, 
      Validator2  BOOLEAN, 
      City        VARCHAR
);

//Reason for the different types of Datatypes is because i didnt know exaclty how long each column enrty would be
//Because i knew before hand that there were missing values, i did not know which column to make the Primary Key

The only external library i used was sqlite-jdbc-3.16.1.jar

I still plan on continuing this project so that eveything is fully functional 
Make it much more highly cohesive and low coupling, perhaps implement some type of interfaces.
