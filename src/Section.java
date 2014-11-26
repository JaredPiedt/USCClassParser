import java.util.ArrayList;

/******************************************************************
 * Section class.
 * 
 * This class creates a 'Section' object for a 'Course'.
 * 
 * @author Jared Piedt
 * 
 *         Last Modified on November 25, 2014
 *
 */
public class Section {

	/**************************************************************
	 * Instance variables.
	 */
	private String number;
	private String location;
	private String instructor;
	private ArrayList<String> times;

	/**************************************************************
	 * Default constructor.
	 */
	public Section() {

	}

	/**************************************************************
	 * Constructor.
	 * 
	 * @param section
	 * @param location
	 * @param instructor
	 * @param times
	 */
	public Section(String section, String location, String instructor,
			ArrayList<String> times) {
		this.number = number;
		this.location = location;
		this.instructor = instructor;
		this.times = times;
	}

	/**************************************************************
	 * Accessors and mutators.
	 */
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public ArrayList<String> getTimes() {
		return times;
	}

	public void setTimes(ArrayList<String> times) {
		this.times = times;
	}

	public void addTime(String time) {
		this.times.add(time);
	}

	/**************************************************************
	 * The basic 'toString' method for a 'Section'.
	 * 
	 * @return the toString for the 'Section'
	 */
	public String toString() {
		String s = "";

		s += "  Section: " + number + "\n    ";
		s += String.format("%-12s", "Location: ");
		s += this.location + "\n    ";
		s += String.format("%-12s", "Instructor: ");
		s += this.instructor + "\n    ";
		String times = this.times.toString();
		s += times.substring(1, times.length() - 1);

		return s;
	}
}
