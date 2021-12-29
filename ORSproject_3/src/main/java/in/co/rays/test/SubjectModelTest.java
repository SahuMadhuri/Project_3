package in.co.rays.test;

import in.co.rays.dto.CourseDTO;
import in.co.rays.dto.SubjectDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.SubjectModelHibImpl;
import in.co.rays.model.SubjectModelInt;

// TODO: Auto-generated Javadoc
/**
 * College Model Test classes
 *  
 * @author 
 *  
 */
public class SubjectModelTest {
	 
 	/** Model object to test. */
 public static SubjectModelInt model = new SubjectModelHibImpl();
 
 /**
  * Main method to call test methods.
  *  
  *
  * @param args the arguments
 * @throws ApplicationException 
  */
 public static void main(String[] args) throws ApplicationException {
   //  testAdd();
  //   testdelete();
 // testUpdate();    //exception
      testFindByName();
    // testFindByPK();
     //testSearch();
//     testList();

}
 private static void testFindByName() throws ApplicationException {
		SubjectDTO dto = model.findByName("Physics");
		System.out.print(dto.getId());
		System.out.print("\t" + dto.getSubjectName());
		System.out.print("\t" + dto.getCourseId());
		System.out.print("\t" + dto.getCourseName());
		System.out.print("\t" + dto.getDescription());
		System.out.print("\t"+dto.getCreatedBy());
		System.out.print("\t"+dto.getModifiedBy());
		System.out.print("\t"+dto.getCreatedDatetime());
		System.out.print("\t"+dto.getModifiedDatetime());
		
	}
 
 /**
  * Tests add a Course.
  */
 public static void testAdd(){
	 SubjectDTO dto=new SubjectDTO();
	 dto.setSubjectName("Maths");;
	 dto.setCourseName("MCA");
	 dto.setDescription("best");
	 dto.setCourseId(2L);
	 try {
		model.add(dto);
	} catch (ApplicationException e) {
		e.printStackTrace();
	} catch (DuplicateRecordException e) {
		e.printStackTrace();
	}
	 }
 private static void testdelete() throws ApplicationException {
		// TODO Auto-generated method stub
		
		SubjectDTO dto = new SubjectDTO();
		dto.setId(1l);
		model.delete(dto);
	}

	 
 	/**
 	 * Test update.
 	 */
 	public static void testUpdate(){
		 SubjectDTO dto=new SubjectDTO();
	     dto.setId(2L);
		 dto.setSubjectName("Maths11");;
		 dto.setCourseName("MCA");
		 dto.setDescription("best");
		 try {
			model.update(dto);
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

 }


}
