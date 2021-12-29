package in.co.rays.dto;

import java.util.*;
import java.util.Date;
/*import java.sql.Timestamp;*/


// TODO: Auto-generated Javadoc
/**
 * Time Table DTO Classes.
 *
 * @author Madhuri
 * @version 1.0 
 */
public class TimeTableDTO extends BaseDTO {
	
	/** ID of Subject. */
	private long subId;
	
	/** Name of Subject. */
	private String subName;
	
	/** ID of Course. */
	private long courseId;
	
	/** Name of Course. */
	private String courseName;
	
	/** Semester. */
	private String semester;
	
	/** Date of Exam. */
	private Date examDate;
	
	/** Time of Exam. */
	private String examTime;
	
	/** Description of TimeTable. */
	private String description;

	/**
	 * accessor.
	 *
	 * @return the sub id
	 */
	public long getSubId() {
		return subId;
	}

	/**
	 * Sets the sub id.
	 *
	 * @param subId the new sub id
	 */
	public void setSubId(long subId) {
		this.subId = subId;
	}

	/**
	 * Gets the sub name.
	 *
	 * @return the sub name
	 */
	public String getSubName() {
		return subName;
	}

	/**
	 * Sets the sub name.
	 *
	 * @param subName the new sub name
	 */
	public void setSubName(String subName) {
		this.subName = subName;
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
	 * Gets the semester.
	 *
	 * @return the semester
	 */
	public String getSemester() {
		return semester;
	}

	/**
	 * Sets the semester.
	 *
	 * @param semester the new semester
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}

	/**
	 * Gets the exam date.
	 *
	 * @return the exam date
	 */
	public Date getExamDate() {
		return examDate;
	}

	/**
	 * Sets the exam date.
	 *
	 * @param examDate the new exam date
	 */
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	/**
	 * Gets the exam time.
	 *
	 * @return the exam time
	 */
	public String getExamTime() {
		return examTime;
	}

	/**
	 * Sets the exam time.
	 *
	 * @param examTime the new exam time
	 */
	public void setExamTime(String examTime) {
		this.examTime = examTime;
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
	
	/* (non-Javadoc)
	 * @see in.co.rays.dto.DropdownList#getKey()
	 */
	public String getKey() {
		return id+"";
	}
	
	/* (non-Javadoc)
	 * @see in.co.rays.dto.DropdownList#getValue()
	 */
	public String getValue() {
		return subName;
	}
}

