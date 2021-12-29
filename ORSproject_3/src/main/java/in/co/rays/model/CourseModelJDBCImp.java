package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jfree.util.Log;

import in.co.rays.dto.CourseDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.HibDataSource;
import in.co.rays.util.JDBCDataSource;


/**
 * JDBC implements of Course model
 * 
 * @author Madhuri
 * @version 1.0 
 *
 */
public class CourseModelJDBCImp implements CourseModelInt {

	private static Logger log = Logger.getLogger(CourseModelJDBCImp.class);
	
	/**
	* find pk
	* @return pk
	* @throws DatabaseException
	*/
	
	public long nextPK() throws ApplicationException{
		Connection conn = null;
		long pk = 0;
		log.debug("model nextPk start");
		try{
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("select max(id) from st_course");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				pk = rs.getLong(1);
			}
			ps.close();
			rs.close();
			
		}catch(Exception e){
			log.error(e);
			throw new ApplicationException("Exception : Exception in getting pk");
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk = pk+1;
		}
	
	/**
	* add new course
	* @param bean
	* @return
	* @throws ApplicationException
	* @throws DuplicateRecordException
	*/
	
	public long add(CourseDTO dto) throws ApplicationException, DuplicateRecordException {
		long pk = 0;
		Connection conn = null;
		log.debug("add method start");
		CourseDTO duplicaterecord = findByName(dto.getCourseName());
		
		if(duplicaterecord!=null){
			throw new DuplicateRecordException("coursename alrady exist");
		}
		
		try{
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("insert into st_course values(?,?,?,?,?,?,?,?)");
			ps.setLong(1, dto.getId());
			ps.setString(2, dto.getCourseName());
			ps.setString(3, dto.getDuration());
			ps.setString(4, dto.getDescription());
			ps.setString(5, dto.getCreatedBy());
			ps.setString(6, dto.getModifiedBy());
			ps.setTimestamp(7, dto.getCreatedDatetime());
			ps.setTimestamp(8, dto.getModifiedDatetime());
			ps.executeUpdate();
			ps.close();
			conn.commit();
			
		}catch(Exception e){
			log.error(e);
			try{
				conn.rollback();
				
			}catch(Exception ex){
				ex.printStackTrace();
				throw new ApplicationException("Exception in rollback course");
			}
			throw new ApplicationException("Exception : Exception in adding course"+e.getMessage());
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		
		log.debug("add method end");
		return pk;
	}
	
	
	/**
	* delete course information
	* @param b
	* @throws DatabaseException
	*/

	public void delete(CourseDTO dto) throws ApplicationException {
		log.debug("delete method start");
		Connection conn = null;
		try{
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("delete from st_course where id=?");
			ps.setLong(1, dto.getId());
			ps.executeUpdate();
			ps.close();
			conn.commit();
		}catch(Exception e){
			log.error(e);
			try{
				conn.rollback();
			}catch(Exception ex){
				throw new ApplicationException("Exception in rollback delete");
			}
			throw new ApplicationException("Exception : Exception in deleting course"+e.getMessage());
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		
		log.debug("delete method ended");
	}

	
	/**
	* update course detail
	* @param bean
	* @throws ApplicationException
	* @throws DuplicateRecordException
	*/
	
	
	public void update(CourseDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("update method start");
		Connection conn = null;
		try{
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("update st_course set course_name=? , duration=? , description=? , created_by=? , modified_by=? , created_datetime=? , modified_datetime=? where id =?");
		    ps.setString(1, dto.getCourseName());
		    ps.setString(2, dto.getDuration());
		    ps.setString(3, dto.getDescription());
		    ps.setString(4, dto.getCreatedBy());
		    ps.setString(5, dto.getModifiedBy());
		    ps.setTimestamp(6, dto.getCreatedDatetime());
		    ps.setTimestamp(7, dto.getModifiedDatetime());
		    ps.setLong(8, dto.getId());
		    ps.executeUpdate();
		    ps.close();
		    conn.commit();
 		}catch(Exception e){
 			log.error(e);
 			try{
 				conn.rollback();
 				
 			}catch(Exception ex){
 				throw new ApplicationException("Exception in rollback update");
 			}
 			throw new ApplicationException("Exception : exception in update course");
 		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("update method end");
	}

	public List list() throws ApplicationException {
		
		return list(0,0);
	}

	/**
	* to show list of course
	* @param pageNo
	* @param pageSize
	* @return list
	* @throws ApplicationException
	*/
	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("list method started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from st_course");
		if(pageSize>0){
			pageNo = (pageNo-1)*pageSize;
			sql.append(" limit "+pageNo+","+pageSize);
		}
		Connection conn = null;
		CourseDTO dto = null;
		try{
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
		    ResultSet rs = ps.executeQuery();
		    while(rs.next()){
		    	dto = new CourseDTO();
		    	dto.setId(rs.getLong(1));
		    	dto.setCourseName(rs.getString(2));
				dto.setDuration(rs.getString(3));
				dto.setDescription(rs.getString(4));
				dto.setCreatedBy(rs.getString(5));
				dto.setModifiedBy(rs.getString(6));
				dto.setCreatedDatetime(rs.getTimestamp(7));
				dto.setModifiedDatetime(rs.getTimestamp(8));
				
				list.add(dto);
		    }
			rs.close();
		}catch (Exception e) {
			log.error(e);
			throw new ApplicationException("Exception : Exception in getting list of course"+e.getMessage());
		}finally {
			JDBCDataSource.closeConnection(conn);
		}	
		log.debug("list method ended");
		return list;
	}

	public List search(CourseDTO dto) throws ApplicationException {
		
		return search(dto,0,0);
	}

	
	
	/**
	* search course information
	* @param cbean1
	* @param pageNo
	* @param pageSize
	* @return list
	* @throws ApplicationException
	*/
	
	public List search(CourseDTO dto, int pageNo, int pageSize) throws ApplicationException {
		log.debug("search method start");
		ArrayList list = new ArrayList();
		Connection conn = null;
		StringBuffer sql = new StringBuffer("select * from st_course where 1=1");
		if(dto!=null){
			if(dto.getId()>0){
				sql.append(" and id = "+dto.getId());
			}
			if(dto.getCourseName()!=null&&dto.getCourseName().length()>0){
				sql.append(" and course_name like '"+dto.getCourseName()+"%'");
			}
			if(dto.getDuration()!=null&&dto.getDuration().length()>0){
				sql.append(" and duration like '"+dto.getDuration()+"%'");
			}
			if(dto.getDescription()!=null&&dto.getDescription().length()>0){
				sql.append(" and descritpion like '"+dto.getDescription()+"%'");
			}
			
		}
		
		if(pageSize>0){
			pageNo = (pageNo-1)*pageSize;
			sql.append(" limit "+pageNo+","+pageSize);
		}
		
		try{
			conn=JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
		    while(rs.next()){
		    	dto = new CourseDTO();
		    	dto.setId(rs.getLong(1));
		    	dto.setCourseName(rs.getString(2));
				dto.setDuration(rs.getString(3));
				dto.setDescription(rs.getString(4));
				dto.setCreatedBy(rs.getString(5));
				dto.setModifiedBy(rs.getString(6));
				dto.setCreatedDatetime(rs.getTimestamp(7));
				dto.setModifiedDatetime(rs.getTimestamp(8));
				
				list.add(dto);
		    }
		    rs.close();
		    ps.close();
		}catch(Exception e){
			log.error(e);
			throw new ApplicationException("Exception : Exception in search course"+e.getMessage());
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("search method end");
		return list;
	}

	
	/**
	* find the information with the help of pk
	* @param pk
	* @return bean
	* @throws ApplicationException
	*/
	public CourseDTO findByPK(long pk) throws ApplicationException {
		Log.debug("findbypk method start");
		CourseDTO dto = null;
		Connection conn = null;
		try{
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from st_course where id=?");
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				
				dto = new CourseDTO();
				dto.setId(rs.getLong(1));
				dto.setCourseName(rs.getString(2));
				dto.setDuration(rs.getString(3));
				dto.setDescription(rs.getString(4));
				dto.setCreatedBy(rs.getString(5));
				dto.setModifiedBy(rs.getString(6));
				dto.setCreatedDatetime(rs.getTimestamp(7));
				dto.setModifiedDatetime(rs.getTimestamp(8));
				
			}
			rs.close();
			ps.close();
		}catch(Exception e){
			Log.error(e);
			throw new ApplicationException("exception : exception in getting course by pk"+e.getMessage());
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		Log.debug("findbypk method end");
		return dto;
	}
	
	/**
	* find the infromation with the help of course name
	* @param name
	* @return bean
	* @throws ApplicationException
	*/
	public CourseDTO findByName(String name) throws ApplicationException {
	Log.debug("findbyname method start");
	Connection conn =null;
	CourseDTO dto = null;
	try{
		conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from st_course where course_name=?");
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			dto = new CourseDTO();
			dto.setId(rs.getLong(1));
			dto.setCourseName(rs.getString(2));
			dto.setDuration(rs.getString(3));
			dto.setDescription(rs.getString(4));
			dto.setCreatedBy(rs.getString(5));
			dto.setModifiedBy(rs.getString(6));
			dto.setCreatedDatetime(rs.getTimestamp(7));
			dto.setModifiedDatetime(rs.getTimestamp(8));
		}
		ps.close();
		rs.close();
	}catch(Exception e){
		Log.error(e);
		throw new ApplicationException("Exception : Exception in getting course by name"+e.getMessage());
	}finally {
		JDBCDataSource.closeConnection(conn);
	}
	Log.debug("findbyname method ended");
		return dto;
	}
}
