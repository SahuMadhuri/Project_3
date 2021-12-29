package in.co.rays.dto;

// TODO: Auto-generated Javadoc
/**
 * Subject DTO Classes.
 *
 * @author Madhuri
 * @version 1.0 
 */
public class SubjectDTO extends BaseDTO{
	
	/** Name of Subject. */
	private String subjectName;
	
	/** Description of Subject. */
	private String description;
	
	/** Name of Course. */
	private String courseName;
	
	/** ID of Course. */
	private long courseId;

	/**
	 * accessor.
	 *
	 * @return the subject name
	 */
	public String getSubjectName() {
		return subjectName;
	}
	
	/**
	 * Sets the subject name.
	 *
	 * @param subjectName the new subject name
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the course name.
	 *
	 * @return the course name
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * Sets the course name.
	 *
	 * @param courseName the new course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * Gets the course id.
	 *
	 * @return the course id
	 */
	public long getCourseId() {
		return courseId;
	}
	
	/**
	 * Sets the course id.
	 *
	 * @param courseId the new course id
	 */
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	
	
	/* (non-Javadoc)
	 * @see in.co.rays.dto.DropdownList#getKey()
	 */
	public String getKey() {
		return id+"";
	}


	/* (non-Javadoc)
	 * @see in.co.rays.dto.DropdownList#getValue()
	 */
	public String getValue() 
	{
		return subjectName;
	}

	


}

