package in.co.rays.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.dto.TimeTableDTO;
import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.ModelFactory;
import in.co.rays.model.TimeTableModelInt;

// TODO: Auto-generated Javadoc
/**
 * The Class TimeTableModelTest.
 * 
 * @author Madhuri
 * @version 1.0 
 */
public class TimeTableModelTest {
	
	
	/** The model. */
	public static TimeTableModelInt model=ModelFactory.getInstance().getTimeTableModel();
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws ParseException the parse exception
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordException the duplicate record exception
	 */
	public static void main(String[] args) throws ParseException, ApplicationException, DuplicateRecordException {
		TestAdd();
	//TestCheckByCourseName();
		//testSearch();
	//	testFindByPK();
	}
	
	
	
	/**
	 * Test search.
	 *
	 * @throws ApplicationException the application exception
	 */
	public static void testSearch() throws ApplicationException{
		
		TimeTableDTO dto=new TimeTableDTO();
		
		
	    List list=	model.search(dto);
	    
	    Iterator it =list.iterator();
	    while (it.hasNext()) {
			TimeTableDTO dto1 = (TimeTableDTO) it.next();
			System.out.println(dto1.getCourseId());
			System.out.println(dto.getCourseName());
			
		}
	}
	
	 /**
 	 * Test find by PK.
 	 */
 	public static void testFindByPK() {
	        try {
	            TimeTableDTO dto = new TimeTableDTO();
	            long pk = 1L;
	            dto = model.findByPK(pk);
	            if (dto == null) {
	                System.out.println("Test Find By PK fail");
	            }
	            System.out.println(dto.getId());
	            System.out.println(dto.getCourseName());
	            System.out.println(dto.getDescription());
	            System.out.println(dto.getExamTime());
	            System.out.println(dto.getSemester());
	            System.out.println(dto.getSubName());
	            System.out.println(dto.getExamDate());
	           System.out.println(dto.getCreatedBy());
	            System.out.println(dto.getModifiedBy());
	            System.out.println(dto.getCreatedDatetime());
	            System.out.println(dto.getModifiedDatetime());
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	 
	    }
	 
	
	

	/**
	 * Test add.
	 *
	 * @throws ParseException the parse exception
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordException the duplicate record exception
	 */
	public static void TestAdd() throws ParseException, ApplicationException, DuplicateRecordException{
		String date="17/12/2019";
		SimpleDateFormat sdt=new SimpleDateFormat("MM/dd/yyyy");
		Date d= sdt.parse(date);
		
		TimeTableDTO dto=new TimeTableDTO();
		dto.setCourseId(1);
		dto.setExamDate(d);
		
		model.add(dto);
		System.out.println("test Successfull");
		
		
	}
	
	
	
	/*public static void TestCheckByCourseName() throws ApplicationException, ParseException{
		
		String date="17/12/2019";
		SimpleDateFormat sdt=new SimpleDateFormat("MM/dd/yyyy");
		Date d= sdt.parse(date);
		
		TimeTableDTO dto=model.checkByCourseName(1,d);
	if(dto!=null){
		System.out.println("success");
	}else {
		System.out.println("failure");
	}
		
		
		dto.getCourseId();
		
		
	
	
	
	}
	*/}	
	

