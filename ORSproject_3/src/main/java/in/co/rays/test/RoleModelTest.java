package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.dto.RoleDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.ModelFactory;
import in.co.rays.model.RoleModelInt;

/**
 * @author Madhuri
 * @version 1.0 
 *
 */
public class RoleModelTest {
	private static RoleModelInt model = ModelFactory.getInstance().getRoleModel();
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		//testadd();
		//testupdate();
		//testdelete();
		//testfindbypk();
		//testfindbyname();
		//testsearch();
		testlist();
	}
private static void testadd() throws ApplicationException, DuplicateRecordException {
		
		RoleDTO dto=new RoleDTO();
		dto.setName("Faculty");
		dto.setDescription("XYZ");
		
		  dto.setCreatedBy("admin"); 
		  dto.setModifiedBy("admin");
		  dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		  dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		 System.out.println("ROle ADd");
		model.add(dto);
	}
private static void testupdate() throws ApplicationException, DuplicateRecordException {
	// TODO Auto-generated method stub
	RoleDTO dto=new RoleDTO();
	dto.setId(2L);
	dto.setName("Student");
	dto.setDescription("XYZ");
	dto.setCreatedBy("admin");
	dto.setModifiedBy("admin");
	dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
	dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
	model.update(dto);
	
	
}
private static void testdelete() throws ApplicationException {
	// TODO Auto-generated method stub
	RoleDTO dto=new RoleDTO();
	dto.setId(5L);
	model.delete(dto);
}
private static void testfindbypk() throws ApplicationException {
	// TODO Auto-generated method stub
	RoleDTO dto=model.findByPK(1l);
	System.out.println(dto.getId()+"\t"+dto.getName()+"\t"+dto.getDescription());
}
private static void testsearch() throws ApplicationException {
	// TODO Auto-generated method stub
	RoleDTO dto1=new RoleDTO();
	dto1.setName("Faculty");
	List<RoleDTO> a=(List<RoleDTO>) model.search(dto1, 0, 0);
	
	for(RoleDTO dto: a){
		System.out.println(dto.getId()+"\t"+dto.getName()+"\t"+dto.getDescription());
	}
	
}

private static void testfindbyname() throws ApplicationException {
	// TODO Auto-generated method stub
	RoleDTO dto=model.findByName("admin");
	System.out.println(dto.getId()+"\t"+dto.getName()+"\t"+dto.getDescription());
}
private static void testlist() throws ApplicationException {
	// TODO Auto-generated method stub
	List list=model.list(0, 0);
	Iterator it=list.iterator();
	while(it.hasNext()){
		RoleDTO dto=(RoleDTO) it.next();
		System.out.println(dto.getId()+"\t"+dto.getName()+"\t"+dto.getDescription()+"\t"+dto.getCreatedBy()+"\t"+dto.getCreatedDatetime());
	}
	
	
}

}
