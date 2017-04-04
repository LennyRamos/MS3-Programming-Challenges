/**---------------------------------------------------**
 *
 *              Created by Lenny Ramos
 *                   April 4, 2017
 *
 *                       Goals:
 *
 *   1) Will open a .csv file, parse the data and insert
 *      it into a SQLlite In-Memory database
 *
 *   2) Data sets can be extremely large, so optimize for
 *      efficiency. (Perhaps use of threads to delegate task)
 *
 *   3) Need to verify that each record has 10 data elements.
 *      If records does not meet the requirement then I need to
 *      write that record to a bad-data-<timestamp>.csv file.
 *      elements with commas (,) will be double quoted.
 *
 *   4) Finally need a log file with # of records received
 *                                   # of records successful
 *                                   # of records failed
 *
 *----------------------------------------------------**
 */


public class Main {
}
