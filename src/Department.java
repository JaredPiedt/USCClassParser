import java.util.ArrayList;

/******************************************************************
 * Department class.
 * 
 * This class create a 'Department' object and holds all of the courses offered
 * in a 'Department'.
 * 
 * @author Jared Piedt
 * 
 *         Last Modified on November 17, 2014
 *
 */
public class Department {

	/**************************************************************
	 * Instance variables.
	 */
	private String name;
	private ArrayList<Course> courses;

	/**************************************************************
	 * Default constructor.
	 */
	public Department() {

	}

	/**************************************************************
	 * Constructor.
	 * 
	 * @param name
	 *            the name of the department
	 */
	public Department(String name) {
		this.name = name;
	}

	/**************************************************************
	 * Accessors and mutators.
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	public void addCourse(Course course) {
		this.courses.add(course);
	}

	/**************************************************************
	 * toString method.
	 * 
	 * Basic 'toString' method that iterates through and prints out all course
	 * offered in a department.
	 * 
	 * @return the toString of the 'Department'
	 */
	public String toString() {
		String s = "";

		for (Course course : courses) {
			s += course.toString();
		}

		return s;
	}
}
