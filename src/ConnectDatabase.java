import java.sql.*;

/**
 * This is the class that connects me to the in-memory database that i am creating
 * The database is created using the SQLite library, sqlite-jdbc-3.16.1.jar library
 */
public class ConnectDatabase
{

    public static void connect() throws ClassNotFoundException
    {
        //This is for the SQLite driver
        final String SQLITE_DRIVER= "org.sqlite.JDBC";

        //Creating a database connection, to MS3.db
        final String DATABASE = "jdbc:sqlite:MS3.db";

        //This is to drop the table that we created previously with each new run
        final String DROP_MS3_TABLE = "Drop Table if exists Users";

        //This is the DDL for the CREATE TABLE of my SQLite database
        final String MS3_TABLE = "Create Table Users " +
                              "(FirstName   VARCHAR(20), " +
                              " LastName    VARCHAR(20), " +
                              " Email       VARCHAR(35), " +
                              " Gender      CHAR(6), " +
                              " Image       TEXT, " +
                              " PaymentType VARCHAR(35), " +
                              " AmountPaid  CHAR(7), " +
                              " Validator1  BOOLEAN, " +
                              " Validator2  BOOLEAN, " +
                              " City        VARCHAR)";

        //load the sqlite-JDBC driver using the current class loader
        Class.forName(SQLITE_DRIVER);

        Connection connection = null;

        try
        {
            //create in-memory database, does not create any database file
            // connection = DriverManager.getConnection("jdbc:sqlite::memory:);"

            //Creates a database connection, creates the users.db if does not exist
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  //set timeout to 30 seconds

            statement.executeUpdate(DROP_MS3_TABLE);
            statement.executeUpdate(MS3_TABLE);

            //statement.executeUpdate("insert into Users (FirstName,LastName,Email) values('Lenny', 'Ramos', 'tuf18062@temple.edu')");
            //statement.executeUpdate("insert into Users (FirstName, LastName, Email) values('George', 'Keenan', 'bitch@aol.com')");
            ResultSet resultSet = statement.executeQuery("select * from Users");

            while(resultSet.next())
            {
                //Print out test example from database
                System.out.println("FirstName = " + resultSet.getString("FirstName"));
                System.out.println("LastName = " + resultSet.getString("LastName"));
                System.out.println("Email = " + resultSet.getString("Email"));
            }
        }
        catch(SQLException e)
        {
            //catches any errors with database
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                //error in trying to close
                System.err.println(e);
            }
        }
    }
}
