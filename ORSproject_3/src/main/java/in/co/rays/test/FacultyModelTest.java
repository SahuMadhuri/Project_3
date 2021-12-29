package in.co.rays.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.dto.FacultyDTO;
import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.FacultyModelHibImpl;
import in.co.rays.model.FacultyModelInt;

// TODO: Auto-generated Javadoc
/**
 * The Class FacultyModelTest.
 * 
 * @author Madhuri
 * @version 1.0 
 */
public class FacultyModelTest {
	 
 	/** Model object to test. */
 
	 public static FacultyModelInt model = new FacultyModelHibImpl();
	 
 	/**
 	 * Main method to call test methods.
 	 *  
 	 *
 	 * @param args the arguments
 	 * @throws Exception the exception
 	 */
	    public static void main(String[] args) throws Exception {
	         testAdd();
	        // testDelete();
	        //testUpdate();
	        // testFindByPK();
	        // testFindByEmailId();
	     //   testSearch();
	         //testList();
	 
	    }
	    
    	/**
    	 * Test add.
    	 */
    	public static void testAdd(){
	    	FacultyDTO dto=new FacultyDTO();
	    	SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
	    	dto.setCollegeId(1L);
	    	dto.setCollegeName("GSITS");
	    	try {
				model.add(dto);
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
    	/**
    	 * Test update.
    	 */
    	public static void testUpdate(){
	    	FacultyDTO dto=new FacultyDTO();
	    	SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
	    	dto.setId(1L);
	    	dto.setCollegeId(1L);
	    	dto.setCollegeName("cdgi");
	    	try {
				model.update(dto);
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
    	/**
    	 * Test delete.
    	 */
    	public static void testDelete(){
	    	FacultyDTO dto=new FacultyDTO();
	    	dto.setId(1L);
	    	try {
				model.delete(dto);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
	    }
	        
        	/**
        	 * Test search.
        	 */
        	public static void testSearch() {
	        	 
	            try {
	                FacultyDTO dto = new FacultyDTO();
	                List list = new ArrayList();
	                // dto.setFirstName("ranjit");
	                // dto.setLastName("ch");
	                dto.setId(1l);
	     //dto.setFirstName("shivam");
	                list = model.search(dto, 0, 0);
	                if (list.size() < 0) {
	                    System.out.println("Test Serach fail");
	                }
	                Iterator it = list.iterator();
	                while (it.hasNext()) {
	                    dto = (FacultyDTO) it.next();
	                    System.out.println(dto.getId());
	                    System.out.println(dto.getFirstName());
	                    System.out.println(dto.getLastName());
	                               }
	     
	            } catch (ApplicationException e) {
	                e.printStackTrace();
	            }
	     
	        }

	        /**
        	 * Test list.
        	 */
        	public static void testList() {
	        	 
	            try {
	            	FacultyDTO dto = new FacultyDTO();
	                List list = new ArrayList();
	                list = model.list();
	                if (list.size() < 0) {
	                    System.out.println("Test list fail");
	                }
	                Iterator it = list.iterator();
	                while (it.hasNext()) {
	                    dto = (FacultyDTO) it.next();
	                    System.out.println(dto.getId());
	                    System.out.println(dto.getFirstName());
	                    System.out.println(dto.getLastName());
	                    System.out.println(dto.getGender());
	                    System.out.println(dto.getCourseName());
	                    System.out.println(dto.getCollegeName());
	                    System.out.println(dto.getMobileNo());
	                    System.out.println(dto.getQualification());
	                    System.out.println(dto.getGender());
	                    System.out.println(dto.getSubjectName());
	                               }
	     
	            } catch (ApplicationException e) {
	                e.printStackTrace();
	            }
	        }
	     
	     
	    	
	    }
	    

