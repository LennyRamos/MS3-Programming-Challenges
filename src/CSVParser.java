import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This will be the class that takes in the given .csv file
 * and will parse it. It will them store each parsed piece
 * into a string so that i can later place into my Users table
 * in the MS3 database.
 */
public class CSVParser
{

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';
    private static final String EMPTY_CELL = "NULL";
    private static final int NUMBER_OF_COLUMNS = 10;

    static public boolean pass_column_count = true;
    static public int number_received = 0;
    static public int number_passed = 0;
    static public int number_failed = 0;


    public static void doParse() throws FileNotFoundException
    {
        //this is the CSV file where ill be writing all the failed rows
        CSVBuilder failedFile = new CSVBuilder();
        createFile(failedFile);

        //Need to figure out how to import csv file, need to specify pathname depending what machine you're using
        String csvFile = "/Users/Lenny Ramos/IdeaProjects/MS3-Programming-Challenge/ms3Interview.csv";

        Scanner scanner = new Scanner(new File(csvFile));

        //while there is still a line in the scanner do the parsing
        while (scanner.hasNext())
        {
            number_received++;
            List<String> line = parseLine(scanner.nextLine());

            if(pass_column_count)
            {
                number_passed++;
                System.out.println("The size of the line is: " + line.size() + " Value of the name " + line.get(0)
                        + line.get(1) + line.get(2) + line.get(3) + line.get(4) + line.get(5) +
                        line.get(6) + line.get(7) + line.get(8) + line.get(9));
            }
            else
            {
                number_failed++;
                failedFile.writeToCSV(line);
            }

        }
        scanner.close();

    }

    public static List<String> parseLine(String csvLine)
    {

        //store the array of strings from the line we parse
        List<String> result = new ArrayList<>();

        //if the line is empty, return the array list
        if (csvLine == null && csvLine.isEmpty())
        {
            return result;
        }

        //This holds the characters in between the commas
        //using StringBuilder instead of String Buffer since only
        //one thread will be accessing it at a time, is not synchronized
        //faster for one thread than StingBuffer
        StringBuilder currentValue = new StringBuilder();
        boolean inQuotes = false;

        //convert the line int chars so i can begin to parse the line
        char[] chars = csvLine.toCharArray();

        for (char ch: chars)
        {

            if (inQuotes)
            {

                if (ch == DEFAULT_QUOTE)
                {
                    inQuotes = false;
                    currentValue.append(DEFAULT_QUOTE);

                }
                else if (ch == DEFAULT_SEPARATOR)   //will combine last two statements when refactoring
                    {
                        currentValue.append(ch);
                    }
                else
                    {
                        currentValue.append(ch);
                    }
            }
            else
            {
                if (ch == DEFAULT_QUOTE)
                {

                    inQuotes = true;
                    currentValue.append(DEFAULT_QUOTE);

                }
                else if (ch == DEFAULT_SEPARATOR)
                {
                    if (currentValue.length() == 0)
                    {
                        //mark empty cell as NULL and begin another String Buffer
                        result.add(EMPTY_CELL);

                        currentValue = new StringBuilder();
                    }
                    else
                    {
                        //we have reached the end of a cell entry
                        result.add(currentValue.toString());

                        //begin a new buffer for next cell entry
                        currentValue = new StringBuilder();
                    }
                }
                else if (ch == '\r')
                {
                    //ignore LF characters
                    continue;
                }
                else if (ch == '\n')
                {
                    //reached the end
                    break;
                }
                else
                {
                    currentValue.append(ch);
                }
            }
        }

        result.add(currentValue.toString());

        return result;
    }

    public static void createFile(CSVBuilder failedFile)
    {
        try
        {
            failedFile.createCSV();
        }
        catch(FileNotFoundException e)
        {
            System.err.println(e.getMessage());
        }
    }

    public static void closeFile(CSVBuilder failedFile)
    {
        try
        {
            failedFile.closeCSV();
        }
        catch (FileNotFoundException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
