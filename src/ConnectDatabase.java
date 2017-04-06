import java.sql.*;

/**
 * This is the class that connects me to the in-memory database that i am creating
 * The database is created using the SQLite library, sqlite-jdbc-3.16.1.jar library
 */
public class ConnectDatabase
{

    public static void connect() throws ClassNotFoundException
    {
        //load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        Connection connection = null;

        try
        {
            //create in-memory database, does not create any database file
            // connection = DriverManager.getConnection("jdbc:sqlite::memory:);"

            //Creates a database connection, creates the users.db if does not exist
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  //set timeout to 30 seconds

            statement.executeUpdate("drop table if exists person");
            statement.executeUpdate("create table person(id integer, name string)");
            statement.executeUpdate("insert into person values(1, 'leo')");
            statement.executeUpdate("insert into person values(2, 'yui')");
            ResultSet resultSet = statement.executeQuery("select * from person");

            while(resultSet.next())
            {
                System.out.println("name = " + resultSet.getString("name"));
                System.out.println("id = " + resultSet.getInt("id"));
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
