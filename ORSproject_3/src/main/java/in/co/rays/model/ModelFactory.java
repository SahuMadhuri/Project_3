package in.co.rays.model;

import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * @author Madhuri
 * @version 1.0 
 *
 */
public class ModelFactory {

	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.bundle.system");
	private static final String DATABASE = rb.getString("DATABASE");
	private static ModelFactory mFactory = null;
	private static HashMap modelCache = new HashMap();

	private ModelFactory() {

	}

	public static ModelFactory getInstance() {
		if (mFactory == null) {
			mFactory = new ModelFactory();
		}
		return mFactory;
	}
	

	
	 
	  
	  
	  public CollegeModelInt getCollegeModel() {
	        CollegeModelInt collegeModel = (CollegeModelInt) modelCache.get("collegeModel");
	        if (collegeModel == null) {
	            if ("Hibernate".equals(DATABASE)) {
	                collegeModel = new CollegeModelHibImpl();
	            }
	            if ("JDBC".equals(DATABASE)) {
	                collegeModel = new CollegeModelHibImpl();
	            }
	            modelCache.put("collegeModel", collegeModel);
	        }

	        return collegeModel;
	    }
	 
	
public UserModelInt getUserModel() {
		
		System.out.println("hhhhhhhhhhhhh"+DATABASE);
		UserModelInt userModel = (UserModelInt) modelCache.get("userModel");
		if (userModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				 userModel = new UserModelHibImp();
			}
			/*
			 * if ("JDBC".equals(DATABASE)) { userModel = new UserModelJDBCImp(); }
			 */
			modelCache.put("userModel", userModel);
		}

		 System.out.println("mf end-------------->"+userModel);
		return userModel;
	}
public RoleModelInt getRoleModel() {
	RoleModelInt roleModel = (RoleModelInt) modelCache.get("roleModel");
	if (roleModel == null) {
		if ("Hibernate".equals(DATABASE)) {
			 roleModel=new RoleModelHibImp();

		}
			/*
			 * if ("JDBC".equals(DATABASE)) { roleModel=new RoleModelJDBCImp(); }
			 */
		modelCache.put("roleModel", roleModel);
	}
	return roleModel;
}

	
public CourseModelInt getCourseModel() {
	CourseModelInt CourseModel = (CourseModelInt) modelCache.get("CourseModel");
    if (CourseModel == null) {
        if ("Hibernate".equals(DATABASE)) {
        	CourseModel = new CourseModelHibImpl();
        }
        /*if ("JDBC".equals(DATABASE)) {
            marksheetModel = new MarksheetModelJDBCImpl();
        }*/
        modelCache.put("CourseModel", CourseModel);
    }

    return CourseModel;
} 
	 
	  
	 
	  
	  
	  
public SubjectModelInt getSubjectModel() {
	SubjectModelInt SubjectModel = (SubjectModelInt)modelCache.get("SubjectModel");
    if (SubjectModel == null) {
        if ("Hibernate".equals(DATABASE)) {
        	SubjectModel = new SubjectModelHibImpl();
        }
        /*if ("JDBC".equals(DATABASE)) {
            marksheetModel = new MarksheetModelJDBCImpl();
        }*/
        modelCache.put("SubjectModel", SubjectModel);
    }

    return SubjectModel;
}
public MarksheetModelInt getMarksheetModel() {
    MarksheetModelInt marksheetModel = (MarksheetModelInt) modelCache
            .get("marksheetModel");
    if (marksheetModel == null) {
        if ("Hibernate".equals(DATABASE)) {
            marksheetModel = new MarksheetModelHibImpl();
        }
        /*if ("JDBC".equals(DATABASE)) {
            marksheetModel = new MarksheetModelJDBCImpl();
        }*/
        modelCache.put("marksheetModel", marksheetModel);
    }

    return marksheetModel;
}
	  
public StudentModelInt getStudentModel() {
    StudentModelInt studentModel = (StudentModelInt) modelCache
            .get("StudentModel");
    if (studentModel == null) {
        if ("Hibernate".equals(DATABASE)) {
            studentModel = new StudentModelHibImpl();
        }
        /*if ("JDBC".equals(DATABASE)) {
            studentModel = new StudentModelJDBCImpl();
        }*/
        modelCache.put("studentModel", studentModel);
    }

    return studentModel;
}
public FacultyModelInt getFacultyModel() {
	FacultyModelInt facultyModel = (FacultyModelInt)modelCache.get("facultyModel");
    if (facultyModel == null) 
    {
        if ("Hibernate".equals(DATABASE)) {
        	facultyModel = new FacultyModelHibImpl();
        }
        /*if ("JDBC".equals(DATABASE)) {
            marksheetModel = new MarksheetModelJDBCImpl();
        }*/
        modelCache.put("facultyModel", facultyModel);
    }

    return facultyModel;
}
public TimeTableModelInt getTimeTableModel() {
	
	TimeTableModelInt timeTableModel = (TimeTableModelInt) modelCache.get("timeTableModel");
    
	if (timeTableModel == null) 
	{
        if ("Hibernate".equals(DATABASE)) {
        	timeTableModel = new TimeTableModelHibImpl();
        }
        /*if ("JDBC".equals(DATABASE)) {
            marksheetModel = new MarksheetModelJDBCImpl();
        }*/
        modelCache.put("timeTableModel", timeTableModel);
    }

    return timeTableModel;
}  
	 
}
