import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * All this class does is take in as input the three variables, received, passed, failed
 * and writes them to a log file. For now log file is simply a .txt file.
 *
 */

public class LogFile {

    static int number_received;
    static int number_passed;
    static int number_failed;

    static PrintWriter pw;

    public LogFile(int number_received, int number_passed, int number_failed )
    {
        this.number_received = number_received;
        this.number_passed = number_passed;
        this.number_failed = number_failed;
    }

    public static void CreateLog()throws FileNotFoundException
    {
        pw = new PrintWriter(new File("StatisticsLog.txt"));

        pw.write("These are statistics of the .csv file you have passed in" +
                "\nThe row entries failed in any of the columns where NULL/Empty");

        pw.write("\nThese are all the files that were received: " + number_received);
        pw.write("\nThese are all the files that passed: " + number_passed);
        pw.write("\nThese are all the files the failed: " + number_failed);

        pw.close();
    }
}
