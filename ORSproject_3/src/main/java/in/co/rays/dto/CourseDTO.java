package in.co.rays.dto;

// TODO: Auto-generated Javadoc
/**
 * Cousre DTO classes.
 *
 * @author Madhuri
 * @version 1.0 
 */
public class CourseDTO extends BaseDTO{
	
	/** Name of Course. */
	private String courseName;
	
	/** Description of Course. */
	private String description;
	
	/** Duration of course. */
	private String duration;
	
	/**
	 * accessor.
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
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}
	
	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	/* (non-Javadoc)
	 * @see in.co.rays.dto.DropdownList#getKey()
	 */
	public String getKey(){
		return id+"";
	}
	
	/* (non-Javadoc)
	 * @see in.co.rays.dto.DropdownList#getValue()
	 */
	public String getValue(){
		return courseName;
	}
	}
