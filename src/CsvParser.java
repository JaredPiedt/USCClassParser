import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.metamodel.DataContext;
import org.apache.metamodel.DataContextFactory;
import org.apache.metamodel.csv.CsvConfiguration;
import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.data.Row;
import org.apache.metamodel.schema.Schema;
import org.apache.metamodel.schema.Table;

/******************************************************************
 * CsvParser class.
 * 
 * Performs all of the work for parsing and performing queries on the .csv file
 * containing all University of South Carolina course information.
 * 
 * @author Jared Piedt
 * 
 *         Last Modified on November 25, 2014
 *
 */
public class CsvParser {

	/**************************************************************
	 * Instance variables.
	 */
	private Table table;
	private DataContext context;

	/**************************************************************
	 * Constructor.
	 * 
	 * @param url
	 *            the URL of the .csv file.
	 */
	public CsvParser(String url) {
		this.parseCsv(url);
	}

	/**************************************************************
	 * parseCsv method.
	 * 
	 * This method performs all of the HTTP functions for getting the .csv file
	 * from a URL and then initializes the 'DataContext' used to perform queries
	 * on.
	 * 
	 * @param url
	 *            the URL of the .csv file
	 */
	protected void parseCsv(String url) {
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);

		httpPost.setHeader("Content-type", "text/csv");

		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();

			CsvConfiguration conf = new CsvConfiguration();
			this.context = DataContextFactory.createCsvDataContext(
					entity.getContent(), conf);

			Schema schema = context.getDefaultSchema();
			Table[] tables = schema.getTables();
			this.table = tables[0];
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**************************************************************
	 * getDepartmentNames method.
	 * 
	 * This method performs the query to obtain a list of all unique department
	 * names.
	 * 
	 * @return the 'ArrayList' of department names
	 */
	protected ArrayList<String> getDepartmentNames() {
		ArrayList<String> departments = new ArrayList<String>();
		DataSet dataSet = context
				.executeQuery("SELECT DISTINCT department FROM "
						+ table.getName() + " ORDER BY department");

		for (Row row : dataSet) {
			departments.add(row.getValue(0).toString());
		}

		return departments;
	}

	/**************************************************************
	 * getDepartment method.
	 * 
	 * Method to get a single 'Department' and all of the courses offered by the
	 * 'Department'.
	 * 
	 * @param name
	 *            the four letter representation of a 'Department'
	 * @return the specified 'Department'
	 */
	protected Department getDepartment(String name) {
		Department department = new Department(name);
		department.setCourses(getCoursesForDepartment(name));

		return department;
	}

	/**************************************************************
	 * getCoursesForDepartment method.
	 * 
	 * This method gets all courses for a specified 'Department'. It performs
	 * SQL query to get all unique course numbers and the properties for those
	 * courses.
	 * 
	 * @param name
	 *            the 'Department' in which to obtain courses
	 * @return the 'ArrayList' of courses for a specified 'Department'
	 */
	protected ArrayList<Course> getCoursesForDepartment(String name) {
		ArrayList<Course> courses = new ArrayList<Course>();

		DataSet dataSet = context
				.executeQuery("SELECT MAX(identifier), MAX(department), number, MAX(name), MAX(credits) FROM "
						+ table.getName()
						+ " WHERE department = '"
						+ name
						+ "' GROUP BY number ORDER BY number");

		for (Row row : dataSet) {
			Course course = new Course();
			course.setId(row.getValue(1).toString());
			course.setDepartment(row.getValue(1).toString());
			course.setNumber(row.getValue(2).toString());
			course.setName(row.getValue(3).toString());
			course.setCredits(row.getValue(4).toString());

			course.setSections(getSectionsForCourse(course));
			courses.add(course);
		}

		return courses;
	}

	/**************************************************************
	 * getSectionsForCourse method.
	 * 
	 * This method performs all of the functions to obtain all the sections for
	 * a specified course. Each 'Section' has a unique identifier, but these
	 * identifiers can have multiple class times. We first perform a query to
	 * get all unique identifiers for a 'Course'. Then we iterate through each
	 * identifier to get class times for the particular 'Section'.
	 * 
	 * 
	 * @param course
	 *            the 'Course' in which to obtain sections for
	 * @return an 'ArrayList' of sections
	 */
	protected ArrayList<Section> getSectionsForCourse(Course course) {
		String department = course.getDepartment();
		String number = course.getNumber();
		String identifier = course.getId();

		ArrayList<Section> sections = new ArrayList<Section>();
		DataSet dataSet = context
				.executeQuery("SELECT DISTINCT identifier, section, MAX(instructors), MAX(location) FROM "
						+ table.getName()
						+ " WHERE department = '"
						+ department
						+ "' AND number = '"
						+ number
						+ "' ORDER BY identifier, section");

		for (Row row1 : dataSet) {

			DataSet temp = context.executeQuery("SELECT days, time FROM "
					+ table.getName() + " WHERE identifier = '"
					+ row1.getValue(0) + "'");

			Section section = new Section();
			section.setInstructor(row1.getValue(2).toString());
			section.setLocation(row1.getValue(3).toString());
			section.setNumber(row1.getValue(1).toString());
			ArrayList<String> times = new ArrayList<String>();
			
			for (Row row2 : temp) {
				times.add(row2.getValue(0) + " " + row2.getValue(1));
			}
			
			section.setTimes(times);
			sections.add(section);
		}


		return sections;
	}
}
