package in.co.rays.test;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.dto.CollegeDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CollegeModelHibImpl;
import in.co.rays.model.CollegeModelInt;

// TODO: Auto-generated Javadoc
/**
 * College Model Test classes
 *  
 * @author Madhuri
 * @version 1.0 
 *  
 */
public class CollegeModelTest {
 
    /** Model object to test. */
 
     public static CollegeModelInt model = new CollegeModelHibImpl();
 
  //  public static CollegeModelInt model = new CollegeModelJDBCImpl();
 
    /**
   * Main method to call test methods.
   *  
   *
   * @param args the arguments
   */
    public static void main(String[] args) {
         testAdd();
        // testDelete();
      //  testUpdate();
      //   testFindByName();
      //  testFindByPK();
      //   testSearch();
        testList();
 
    }
 
    /**
     * Tests add a College.
     */
    public static void testAdd() {
 
        try {
            CollegeDTO dto = new CollegeDTO();
            //dto.setId(1L);
            dto.setName("Holkar College");
            dto.setAddress("INDORE");
            dto.setState("mp");
            dto.setCity("indore");
            dto.setPhoneNo("9876543654");
            dto.setCreatedBy("Admin");
            dto.setModifiedBy("Admin");
            dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
            dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
            long pk = model.add(dto);
            System.out.println("Test add succ");
         /*CollegeDTO  addedDto = model.findByPK(pk);
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
     * Tests delete a College.
     */
    public static void testDelete() {
 
        try {
            CollegeDTO dto = new CollegeDTO();
            long pk = 2L;
            dto.setId(pk);
            model.delete(dto);
            System.out.println("Test Delete succ");
            CollegeDTO deletedDto = model.findByPK(pk);
            if (deletedDto != null) {
                System.out.println("Test Delete fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Tests update a College.
     */
    public static void testUpdate() {
 
        try {
            CollegeDTO dto = model.findByPK(1L);
            dto.setName("MIST");
            model.update(dto);
            CollegeDTO updatedDTO = model.findByPK(2L);
            System.out.println("Test Update succ");
            if (!"ocm".equals(updatedDTO.getName())) {
                System.out.println("Test Update fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests find a College by Name.
     */
 
    public static void testFindByName() {
 
        try {
            CollegeDTO dto = model.findByName("MIST");
            if (dto == null) {
                System.out.println("Test Find By Name fail");
            }
            System.out.println(dto.getId());
            System.out.println(dto.getName());
            System.out.println(dto.getAddress());
            System.out.println(dto.getState());
            System.out.println(dto.getCity());
            System.out.println(dto.getPhoneNo());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests find a College by PK.
     */
    public static void testFindByPK() {
        try {
            CollegeDTO dto = new CollegeDTO();
            long pk = 1L;
            dto = model.findByPK(pk);
            if (dto == null) {
                System.out.println("Test Find By PK fail");
            }
            System.out.println(dto.getId());
            System.out.println(dto.getName());
            System.out.println(dto.getAddress());
            System.out.println(dto.getState());
            System.out.println(dto.getCity());
            System.out.println(dto.getPhoneNo());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests search a College.
     */
 
    public static void testSearch() {
        try {
            CollegeDTO dto = new CollegeDTO();
            List list = new ArrayList();
            //dto.setName("Truba");
            list = model.search(dto, 1, 10);
            if (list.size() < 0) {
                System.out.println("Test Search fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (CollegeDTO) it.next();
                System.out.print(dto.getId());
                System.out.print("\t"+dto.getName());
                System.out.print("\t"+dto.getAddress());
                System.out.print("\t"+dto.getState());
                System.out.print("\t"+dto.getCity());
                System.out.print("\t"+dto.getPhoneNo());
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
            CollegeDTO dto = new CollegeDTO();
            List list = new ArrayList();
            list = model.list(0, 10);
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (CollegeDTO) it.next();
                System.out.print(dto.getId());
                System.out.print("\t"+dto.getName());
                System.out.print("\t"+dto.getAddress());
                System.out.print("\t"+dto.getState());
                System.out.print("\t"+dto.getCity());
                System.out.print("\t"+dto.getPhoneNo());
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

