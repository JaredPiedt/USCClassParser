import org.apache.metamodel.DataContext;

/******************************************************************
 * Driver class.
 * 
 * Tests all of the main functions of the 'CsvParser'.
 * 
 * @author Jared Piedt
 * 
 *         Last Modified on November 25, 2014
 *
 */
public class Driver {
	
	static final String URL = "http://jmvidal.cse.sc.edu/schedule/schedule.csv";

	public static void main(String[] args) {
		CsvParser csvParser = new CsvParser(URL);

		// Test the method to obtain all department names
		System.out.println(csvParser.getDepartmentNames());

		// Test the method to obtain a specified department and then print out
		// the properties of that department
		Department csceDepartment = csvParser.getDepartment("CSCE");
		System.out.println(csceDepartment.toString());
	}

}
