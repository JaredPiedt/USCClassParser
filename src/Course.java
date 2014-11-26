import java.util.ArrayList;

/******************************************************************
 * Course class.
 * 
 * This class create a Course object and holds all of the sections offered for a
 * given course.
 * 
 * @author Jared Piedt
 * 
 *         Last Modified on November 17, 2014
 *
 */
public class Course {

	/**************************************************************
	 * Instance variables.
	 */
	private String id;
	private String semester;
	private String department;
	private String number;
	private String name;
	private String credits;
	private ArrayList<Section> sections;

	/**************************************************************
	 * Default constructor.
	 */
	public Course() {

	}

	/**************************************************************
	 * Constructor
	 * 
	 * @param id
	 * @param semester
	 * @param department
	 * @param number
	 * @param name
	 * @param credits
	 */
	public Course(String id, String semester, String department, String number,
			String name, String credits) {
		this.id = id;
		this.semester = semester;
		this.department = department;
		this.number = number;
		this.name = name;
		this.credits = credits;
	}

	/**************************************************************
	 * Accessors and mutators.
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCredits() {
		return credits;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}

	public ArrayList<Section> getSections() {
		return sections;
	}

	public void setSections(ArrayList<Section> sections) {
		this.sections = sections;
	}

	public void addClassTime(Section section) {
		this.sections.add(section);
	}

	/**************************************************************
	 * Basic 'toString' for the class.
	 * 
	 * @return the toString for the 'Class'
	 */
	public String toString() {
		String s = "";

		s += "*****" + department + number + "*****\n";
		s += String.format("%-10s", "Name: ");
		s += this.name + "\n";
		s += String.format("%-10s", "Credits: ");
		s += this.credits + "\n";

		if (sections != null) {
			for (Section section : sections) {
				s += section.toString() + "\n";
			}
		}

		return s;
	}
}
