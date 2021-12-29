package in.co.rays.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.model.UserModelHibImp;
import in.co.rays.model.UserModelInt;

/**
 * @author Madhuri
 * @version 1.0 
 *
 */
public class UserModelTest {
	private static UserModelInt model= new UserModelHibImp();

	public static void main(String[] args) throws Exception {
		//testAdd();
	//	testDelete();
	//	testUpdate();
		//testfindByPK();
	//	testfindByLogin();
	//testlist();
	//	testsearch();
		changePassword();
	//	testauthenticate();

	}
	private static void testAdd() throws ParseException, ApplicationException, DuplicateRecordException {
		UserDTO dto=new UserDTO();
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		dto.setFirstname("Madhuri");
		dto.setLastname("Sahu");
		dto.setLoginid("Anisha555@gmail.com");
		dto.setPassword("12345");
		dto.setConfirmpassword("12345");
		dto.setGender("Female");
		dto.setMobileno("0988877553");
		dto.setRoleid(1);
		dto.setDob(sdf.parse("12/04/2000"));
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.add(dto);
		System.out.println("add Success");
	}
	private static void testDelete() throws ApplicationException, DuplicateRecordException {
		UserDTO dto= new UserDTO();
		dto.setId(5L);
		model.delete(dto);
		System.out.println("Delete Data Successfully");
		
	}
	private static void testUpdate() throws ParseException, ApplicationException, DuplicateRecordException {
		UserDTO dto = new UserDTO();
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		dto.setId(3L);
		dto.setFirstname("Madhuri8");
		dto.setLastname("sahu");
		dto.setGender("female");
		dto.setDob(sdf.parse("09/09/2000"));
		dto.setPassword("555555");
		dto.setConfirmpassword("555555");
		dto.setRoleid(1);
		dto.setLoginid("Madhuri1266@gmail.com");
		dto.setMobileno("9886654534");
		dto.setCreatedBy("Admin");
		dto.setModifiedBy("Admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(dto);
		System.out.println("Test user update success");
	}
	private static void testfindByPK() throws ApplicationException, DuplicateRecordException {
		UserDTO dto= model.findByPK(1L);
		System.out.print(dto.getFirstname());
		System.out.print("\t"+dto.getLastname());
		System.out.print("\t"+dto.getGender());
		System.out.print("\t"+dto.getLoginid());
		System.out.print("\t"+dto.getMobileno());
		System.out.print("\t"+dto.getGender());
		System.out.println("\t"+dto.getRoleid());
	System.out.println("findBy Pk Success");
	}	
	private static void testfindByLogin() throws ApplicationException, DuplicateRecordException {
		UserDTO dto =model.findByLogin("Madhu123@gmail.com");
		System.out.print(dto.getFirstname());
		System.out.print("\t"+dto.getLastname());
		System.out.print("\t"+dto.getGender());
		System.out.print("\t"+dto.getLoginid());
		System.out.print("\t"+dto.getMobileno());
		System.out.print("\t"+dto.getGender());
		System.out.println("\t"+dto.getRoleid());
	System.out.println("findByLogin Success");
	}
	private static void testlist() throws ApplicationException {
		UserDTO dto= new UserDTO();
		List list =new ArrayList();
		list =model.list(1, 10);
		if(list.size()<0) {
			System.out.println("list fail");
		}
		Iterator it =list.iterator();
		while(it.hasNext()) {
		 dto=(UserDTO)it.next();
		 System.out.print(dto.getFirstname());
			System.out.print("\t"+dto.getLastname());
			System.out.print("\t"+dto.getGender());
			System.out.print("\t"+dto.getLoginid());
			System.out.print("\t"+dto.getMobileno());
			System.out.print("\t"+dto.getGender());
			System.out.println("\t"+dto.getRoleid());
			
		}
		System.out.println("user list success");
	}
	private static void testsearch() throws ApplicationException {
		UserDTO dto= new UserDTO();
		
		dto.setFirstname("Madhu");
		List list=new ArrayList();
		list=model.Search(dto, 1, 5);
		
		Iterator it =list.iterator();
		while(it.hasNext()) {
			 dto=(UserDTO)it.next();
			 System.out.print(dto.getFirstname());
				System.out.print("\t"+dto.getLastname());
				System.out.print("\t"+dto.getGender());
				System.out.print("\t"+dto.getLoginid());
				System.out.print("\t"+dto.getMobileno());
				System.out.print("\t"+dto.getGender());
				System.out.println("\t"+dto.getRoleid());
				
			}
			System.out.println("user list search success");
		
	}
	public static void changePassword() throws ApplicationException, DuplicateRecordException, RecordNotFoundException {
		UserDTO dto = model.findByLogin("Madhu123@gmail.com");
				model.changePassword(1L, "Madhu123@", "Madhu123@4");
				System.out.println("Password change Successfully");
				
	}
	private static void testauthenticate() {
		
		try{
					
					UserDTO dto = new UserDTO();
					dto.setLoginid("Madhuri1266@gmail.com");
					dto.setPassword("555565");
					dto =model.authenticate(dto.getLoginid(),dto.getPassword());
					if(dto!=null){
						
						System.out.println("Authenticate  successful");
					}
					else{
						
						System.out.println("invalid loginid & password");
					}
					
					
					
				}catch(Exception e){
					e.printStackTrace();
				}		
				
			}

}

