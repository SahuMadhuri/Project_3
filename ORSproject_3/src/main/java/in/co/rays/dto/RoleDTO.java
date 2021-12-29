package in.co.rays.dto;

/**
 * @author Madhuri
 * @version 1.0 
 *
 */
public class RoleDTO extends BaseDTO  {
	public static final int ADMIN=1;
	public static final int STUDENT=2;
	public static final int COLLEGE=3;
	public static final int FACULTY=4;
	private String name;
	private String Description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getKey() {
		// TODO Auto-generated method stub
		return id+ "";
	}
	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}
	
	

}
