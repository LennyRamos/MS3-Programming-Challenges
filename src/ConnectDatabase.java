import java.sql.*;


/**
 * This is the class that connects me to the in-memory database that i am creating
 * The database is created using the SQLite library, sqlite-jdbc-3.16.1.jar library
 */
public class ConnectDatabase
{


    public static final String INSERT_INTO_COLUMN_NAME = "insert into Users (FirstName, LastName, Email" +
            ", Gender, Image, PaymentType, AmountPaid, Validator1, Validator2, City)";

    public static void connect() throws ClassNotFoundException {
        //This is for the SQLite driver
        final String SQLITE_DRIVER = "org.sqlite.JDBC";

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

        try {
            //create in-memory database, does not create any database file
            // connection = DriverManager.getConnection("jdbc:sqlite::memory:);"

            //Creates a database connection, creates the users.db if does not exist
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  //set timeout to 30 seconds

            statement.executeUpdate(DROP_MS3_TABLE);
            statement.executeUpdate(MS3_TABLE);

            statement.executeUpdate(INSERT_INTO_COLUMN_NAME + "values('Lenny', 'Ramos', 'tuf18062@temple.edu','male','asd'" +
                    ",'vize','23','true','false','philly')");
            //statement.executeUpdate("insert into Users (FirstName, LastName, Email) values('George', 'Keenan', '')");

            ResultSet resultSet = statement.executeQuery("select * from Users");

            while (resultSet.next()) {
                //Print out test example from database
                System.out.println("FirstName = " + resultSet.getString("FirstName"));
                System.out.println("LastName = " + resultSet.getString("LastName"));
                System.out.println("Email = " + resultSet.getString("Email"));
                System.out.println("City= " + resultSet.getString("City"));
            }
        } catch (SQLException e) {
            //catches any errors with database
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if (connection != null)
                    connection.close();
            }
            catch (SQLException e)
            {
                //error in trying to close
                System.err.println(e);
            }
        }
    }

    /*
        //This will get called from within the CSVParser and receive a line to insert into table
        public static void addEntry(List<String> line)
        {
            try
            {
                System.out.println("Value of line.get(0): " + line.get(0));
                statement.executeUpdate(INSERT_INTO_COLUMN_NAME + " values(" + line.get(0) + line.get(1) + line.get(2)
                + line.get(3) + line.get(4) + line.get(5) + line.get(6) + line.get(7) + line.get(8) + line.get(9) + ")");
            }
            catch (SQLException e)
            {
                System.err.println(e.getMessage());
            }
        }
    */
        public static void closeConnection()
        {

        }


}
