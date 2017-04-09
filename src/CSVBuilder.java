import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * I will be passing in all the Lines that failed to have all column full
 * They will have 'NULL' in the column which they are missing an entry
 */

public class CSVBuilder
{
    private static final char DEFAULT_SEPARATOR = ',';
    //only going to need one printer writer
    static PrintWriter pw;
    List<String> failedLine;

    //constructor which will take each newly failed line from the CSV Parser
    //For now, initialize this class in the Parser and pass each line that fails
    public CSVBuilder()
    {
    }

    public static void createCSV() throws FileNotFoundException
    {
        pw = new PrintWriter(new File("Failed.csv"));
    }

    public void writeToCSV(List<String> line) throws FileNotFoundException
    {
        failedLine = line;
        int count = 0;
        int amountOfStr = failedLine.size() - 1;
        StringBuilder sb = new StringBuilder();

        while(count < (amountOfStr))
        {
            sb.append(failedLine.get(count));

            //this is to write the commas in, except the last one
            if(count != amountOfStr)
            {
                sb.append(DEFAULT_SEPARATOR);
            }

            count++;
        }

        //need the comma separator inorder to be a csv
        //sb.append(DEFAULT_SEPARATOR);

        pw.write(sb.toString());

    }

    public static void closeCSV() throws FileNotFoundException
    {
        pw.close();
    }
}
