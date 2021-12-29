package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.dto.FacultyDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;



/**
 * JDBC implements of Faculty model
 *
 * @author Madhuri
 * @version 1.0 
 *
 */
public class FacultyModelJDBCImp implements FacultyModelInt {
    private static Logger log = Logger.getLogger(CourseModelJDBCImp.class);
    /**
	* find pk
	* @return pk
	* @throws DatabaseException
	*/
    public long nextPk() throws DatabaseException{
    	long pk = 0;
    	log.debug("nextpk method start");
    	Connection conn = null;
    	
    	try{
    		conn = JDBCDataSource.getConnection();
    		PreparedStatement ps = conn.prepareStatement("select max(id) from st_faculty");
    		ps.setLong(1, pk);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()){
    			
    			pk = rs.getLong(1);
    			
    		}
    		ps.close();
    		rs.close();
    	}catch (Exception e) {
			log.error(e);
			throw new DatabaseException("Exception in getting pk");
    		
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
    	log.debug("nextpk method ended");
		return pk=pk+1;
		}
    
    
    
    
    /**
	* add new faculty
	* @param bean
	* @return
	* @throws ApplicationException
	* @throws DuplicateRecordException
	*/
	public long add(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {
		long pk = 0;
		Connection conn =null;
		
		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("insert into st_faculty values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setLong(1, pk);
			ps.setString(2, dto.getFirstName());
			ps.setString(3, dto.getLastName());
			ps.setString(4, dto.getGender());
//			ps.setDate(5, new java.sql.Date(dto.getDateofjoining().getTime()));
			ps.setString(5, dto.getQualification());
			ps.setString(6, dto.getMobileNo());
			ps.setString(7, dto.getEmailId());
			ps.setLong(8, dto.getCollegeId());
			ps.setString(9, dto.getCollegeName());
			ps.setLong(10, dto.getCourseId());
			ps.setString(11, dto.getCourseName());
            ps.setLong(12, dto.getSubjectId());
			ps.setString(13, dto.getSubjectName());
			ps.setString(14, dto.getCreatedBy());
			ps.setString(15, dto.getModifiedBy());
			ps.setTimestamp(16, dto.getCreatedDatetime());
			ps.setTimestamp(17, dto.getModifiedDatetime());
		    ps.executeUpdate();
			
			ps.close();
			conn.commit();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	
	/**
	* delete faculty information
	* @param b
	* @throws DatabaseException
	*/
	public void delete(FacultyDTO dto) throws ApplicationException {
		
		Connection conn = null;
		try {
			System.out.println(dto.getId());
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("delete from st_faculty where ID=?");
			ps.setLong(1, dto.getId());
			System.out.println("Delete data successfully");
			ps.executeUpdate();
			ps.close();
			conn.commit();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");

		
	}

	
	/**
	* update faculty detail
	* @param bean
	* @throws ApplicationException
	* @throws DuplicateRecordException
	*/
	public void update(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(
					"update st_faculty set FIRST_NAME=?,LAST_NAME=?,GENDER=?,DATE_OF_JOINING=?,QUALIFICATION=?,MOBILE_NO=?,EMAIL_ID=?,COLLEGE_ID=?,COLLEGE_NAME=?,COURSE_ID=?,COURSE_NAME=?,SUBJECT_ID=?,SUBJECT_NAME=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? where ID=?");
		
			ps.setString(1, dto.getFirstName());
			ps.setString(2, dto.getLastName());
			ps.setString(3 , dto.getGender());
			/* ps.setDate(4, new java.sql.Date(dto.getDateofjoining().getTime())); */
			ps.setString(5, dto.getQualification());
			ps.setString(6, dto.getMobileNo());
			ps.setString(7, dto.getEmailId());
			ps.setLong(8, dto.getCollegeId());
			ps.setString(9, dto.getCollegeName());
			ps.setLong(10, dto.getCourseId());
			ps.setString(11, dto.getCourseName());
            ps.setLong(12, dto.getSubjectId());
			ps.setString(13, dto.getSubjectName());
			ps.setString(14, dto.getCreatedBy());
			ps.setString(15, dto.getModifiedBy());
			ps.setTimestamp(16, dto.getCreatedDatetime());
			ps.setTimestamp(17, dto.getModifiedDatetime());
			ps.setLong(18 , dto.getId());
		    ps.executeUpdate();
			ps.close();
			conn.commit();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating faculty ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("update method end");
	}
	

	public List list() throws ApplicationException {
		
		return list(0, 0);
	}

	
	/**
	* to show list of faculty
	* @param pageNo
	* @param pageSize
	* @return list
	* @throws ApplicationException
	*/
	
	
	public List list(int pageNo, int pageSize) throws ApplicationException {
		
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from st_faculty");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;
		FacultyDTO dto = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new FacultyDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setGender(rs.getString(4));
				/* dto.setDateofjoining(rs.getDate(5)); */
				dto.setQualification(rs.getString(5));
				dto.setMobileNo(rs.getString(6));
				dto.setEmailId(rs.getString(7));
				dto.setCollegeId(rs.getLong(8));
				dto.setCollegeName(rs.getString(9));
				dto.setCourseId(rs.getLong(10));
				dto.setCourseName(rs.getString(11));
				dto.setSubjectId(rs.getLong(12));
				dto.setSubjectName(rs.getString(13));
				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}

	public List search(FacultyDTO dto) throws ApplicationException {
		
		return search(dto, 0, 0);
	}
	
	
	
	/**
	* search faculty information
	* @param cbean1
	* @param pageNo
	* @param pageSize
	* @return list
	* @throws ApplicationException
	*/

	public List search(FacultyDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Connection conn = null;
		StringBuffer sql = new StringBuffer("select * from st_faculty where 1=1");
		if (dto != null) {
			if (dto.getId() > 0) {
				sql.append(" AND ID = " + dto.getId());
			}
			if ((dto.getFirstName() != null) && (dto.getFirstName().length() > 0)) {
				sql.append(" AND FIRST_NAME like '" + dto.getFirstName() + "%'");
			}
			if ((dto.getLastName() != null) && (dto.getLastName().length() > 0)) {
				sql.append(" AND LAST_NAME like '" + dto.getLastName() + "%'");
			}
			if ((dto.getGender() != null) && (dto.getGender().length() > 0)) {
				sql.append(" AND GENDER like '" + dto.getGender() + "%'");
			}
			/*
			 * if ((dto.getDateofjoining() != null) && (dto.getDateofjoining().getDate() >
			 * 0)) { sql.append(" AND DOB = " + dto.getDateofjoining()); }
			 */
			if ((dto.getQualification() != null) && (dto.getQualification().length() > 0)) {
				sql.append(" AND QUALIFICATION like '" + dto.getQualification() + "%'");
			}
			if ((dto.getEmailId() != null) && (dto.getEmailId().length() > 0)) {
				sql.append(" AND EMAILID like '" + dto.getEmailId() + "%'");
			}
			if ((dto.getMobileNo() != null) && (dto.getMobileNo().length() > 0)) {
				sql.append(" AND MOBILENO like '" + dto.getMobileNo() + "%'");
			}
			if (dto.getCollegeId() > 0) {
				sql.append(" AND COLLEGEID = " + dto.getCollegeId());
			}

			if ((dto.getCollegeName() != null) && (dto.getCollegeName().length() > 0)) {
				sql.append(" AND COLLEGE_NAME like '" + dto.getCollegeName() + "%'");
			}
			if (dto.getCourseId() > 0) {
				sql.append(" AND COURSEID = " + dto.getCourseId());
			}
			if ((dto.getCourseName() != null) && (dto.getCourseName().length() > 0)) {
				sql.append(" AND COURSE_NAME like '" + dto.getCourseName() + "%'");

			}
			if (dto.getSubjectId() > 0) {
				sql.append(" AND SUBJECTID = " + dto.getSubjectId());
			}

			if ((dto.getSubjectName() != null) && (dto.getSubjectName().length() > 0)) {
				sql.append(" AND SUBJECTNAME like '" + dto.getSubjectName() + "%'");
			}
		}
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + "," + pageSize);

			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList<FacultyDTO> list = new ArrayList<FacultyDTO>();
		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				FacultyDTO dto1 = new FacultyDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setGender(rs.getString(4));
				/* dto.setDateofjoining(rs.getDate(5)); */
				dto.setQualification(rs.getString(5));
				dto.setMobileNo(rs.getString(6));
				dto.setEmailId(rs.getString(7));
				dto.setCollegeId(rs.getLong(8));
				dto.setCollegeName(rs.getString(9));
				dto.setCourseId(rs.getLong(10));
				dto.setCourseName(rs.getString(11));
				dto.setSubjectId(rs.getLong(12));
				dto.setSubjectName(rs.getString(13));

				list.add(dto);
			}

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");

		return list;
	}

	public FacultyDTO findByPK(long pk) throws ApplicationException {
		Connection conn =null;
		FacultyDTO dto = null;
		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from st_faculty where ID=?");
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dto = new FacultyDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setGender(rs.getString(4));
				/* dto.setDateofjoining(rs.getDate(5)); */
				dto.setQualification(rs.getString(5));
				dto.setMobileNo(rs.getString(6));
				dto.setEmailId(rs.getString(7));
				dto.setCollegeId(rs.getLong(8));
				dto.setCollegeName(rs.getString(9));
				dto.setCourseId(rs.getLong(10));
				dto.setCourseName(rs.getString(11));
				dto.setSubjectId(rs.getLong(12));
				dto.setSubjectName(rs.getString(13));
			}
			ps.close();
			conn.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting faculty by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("model findBy pk end");

		return dto;

	}
		

	public FacultyDTO findByEmailId(String emailId) throws ApplicationException {
		
		Connection conn =null;
		FacultyDTO dto = null;
		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from st_faculty where EMAIL_ID=?");
			ps.setString(1, dto.getEmailId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dto = new FacultyDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setGender(rs.getString(4));
				/* dto.setDateofjoining(rs.getDate(5)); */
				dto.setQualification(rs.getString(5));
				dto.setMobileNo(rs.getString(6));
				dto.setEmailId(rs.getString(7));
				dto.setCollegeId(rs.getLong(8));
				dto.setCollegeName(rs.getString(9));
				dto.setCourseId(rs.getLong(10));
				dto.setCourseName(rs.getString(11));
				dto.setSubjectId(rs.getLong(12));
				dto.setSubjectName(rs.getString(13));
			}
			ps.close();
			conn.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting faculty by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("model findBy pk end");

		return dto;

	}

}
