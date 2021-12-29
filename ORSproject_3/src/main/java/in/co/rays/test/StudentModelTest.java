package in.co.rays.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.dto.StudentDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.StudentModelHibImpl;
import in.co.rays.model.StudentModelInt;

// TODO: Auto-generated Javadoc
/**
 * Student Model Test classes
 *  
 * @author Madhuri
 * @version 1.0  
 *  
 */
public class StudentModelTest {
 
    /** Model object to test. */
 
     public static StudentModelInt model = new StudentModelHibImpl();
 
   // public static StudentModelInt model = new StudentModelJDBCImpl();
 
    /**
    * Main method to call test methods.
    *  
    *
    * @param args the arguments
    * @throws Exception the exception
    */
    public static void main(String[] args) throws Exception {
      //   testAdd();
        // testDelete();
        // testUpdate();
        // testFindByPK();
       //  testFindByEmailId();
        // testSearch();
         testList();
 
    }
 
    /**
     * Tests add a Student
     *  
     *
     * @throws ParseException the parse exception
     */
    public static void testAdd() throws ParseException {
 
        try {
            StudentDTO dto = new StudentDTO();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
 
//            dto.setId(1L);
            dto.setFirstName("Anisha");
            dto.setLastName("Sahu");
            dto.setDob(sdf.parse("05/12/1999"));
            dto.setMobileNo("9165254357");
            dto.setEmail("Anisha@gmail.com");
            dto.setCollegeId(1L);
            dto.setCollegeName("jnu");
            dto.setCreatedBy("Admin");
            dto.setModifiedBy("Admin");
            dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
            dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
            long pk = model.add(dto);
            System.out.println("Test add succ");
           /* StudentDTO addedDto = model.findByPK(pk);
            if (addedDto == null) {
                System.out.println("Test add fail");
            }*/
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests delete a Student.
     */
    public static void testDelete() {
 
        try {
            StudentDTO dto = new StudentDTO();
            long pk = 9L;
            dto.setId(pk);
            model.delete(dto);
            System.out.println("Test Delete succ");
            StudentDTO deletedDto = model.findByPK(pk);
            if (deletedDto != null) {
                System.out.println("Test Delete fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Tests update a Student.
     */
    public static void testUpdate() {
 
        try {
            System.out.println("Test Update scc111");
            StudentDTO dto = model.findByPK(1L);
            System.out.println("Test Update scc222"+dto.getCollegeName());
            dto.setFirstName("Aditi");
            dto.setLastName("Sahu");
            dto.setMobileNo("9165254357");
            dto.setEmail("Aditi5@gmail.com");
            dto.setCollegeId(2L);
            dto.setCollegeName("LNCT");
            model.update(dto);
            /*StudentDTO updatedDTO = model.findByPK(10L);
            if (!"ram".equals(updatedDTO.getFirstName())) {
                System.out.println("Test Update fail");
            }*/
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Tests find a Student by PK.
     */
    public static void testFindByPK() {
        try {
            StudentDTO dto = new StudentDTO();
            long pk = 2L;
            dto = model.findByPK(pk);
            if (dto == null) {
                System.out.println("Test Find By PK fail");
            }
            System.out.println(dto.getId());
            System.out.println(dto.getFirstName());
            System.out.println(dto.getLastName());
            System.out.println(dto.getDob());
            System.out.println(dto.getMobileNo());
            System.out.println(dto.getEmail());
            System.out.println(dto.getCollegeId());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests find a Student by EmailId.
     */
    public static void testFindByEmailId() {
        try {
            StudentDTO dto = new StudentDTO();
            dto = model.findByEmailId("Anisha@gmail.com"
            		+ "");
            if (dto == null) {
                System.out.println("Test Find By EmailId fail");
            }
            System.out.println(dto.getId());
            System.out.println(dto.getFirstName());
            System.out.println(dto.getLastName());
            System.out.println(dto.getDob());
            System.out.println(dto.getMobileNo());
            System.out.println(dto.getEmail());
            System.out.println(dto.getCollegeId());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Tests get Search.
     */
    public static void testSearch() {
 
        try {
            StudentDTO dto = new StudentDTO();
            List list = new ArrayList();
           // dto.setFirstName("A");
            dto.setEmail("Anisha@gmail.com");
            list = model.search(dto, 0, 0);
            if (list.size() < 0) {
                System.out.println("Test Serach fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (StudentDTO) it.next();
                System.out.print(dto.getId());
                System.out.print("\t"+dto.getFirstName());
                System.out.print("\t"+dto.getLastName());
                System.out.print("\t"+dto.getDob());
                System.out.print("\t"+dto.getMobileNo());
                System.out.print("\t"+dto.getEmail());
                System.out.print("\t"+dto.getCollegeId());
                System.out.print("\t"+dto.getCreatedBy());
                System.out.print("\t"+dto.getCreatedDatetime());
                System.out.print("\t"+dto.getModifiedBy());
                System.out.println("\t"+dto.getModifiedDatetime());
            }
 
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests get List.
     */
    public static void testList() {
 
        try {
            StudentDTO dto = new StudentDTO();
            List list = new ArrayList();
            list = model.list(1, 10);
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (StudentDTO) it.next();
                System.out.print(dto.getId());
                System.out.print("\t"+dto.getFirstName());
                System.out.print("\t"+dto.getLastName());
                System.out.print("\t"+dto.getDob());
                System.out.print("\t"+dto.getMobileNo());
                System.out.print("\t"+dto.getEmail());
                System.out.print("\t"+dto.getCollegeId());
                System.out.print("\t"+dto.getCreatedBy());
                System.out.print("\t"+dto.getCreatedDatetime());
                System.out.print("\t"+dto.getModifiedBy());
                System.out.println("\t"+dto.getModifiedDatetime());
            }
 
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
}
