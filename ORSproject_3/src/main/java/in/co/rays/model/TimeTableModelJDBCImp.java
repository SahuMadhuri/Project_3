package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.dto.TimeTableDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;



/**
 * JDBC implements of TimeTable model
 * 
 * @author Madhuri
 * @version 1.0 
 *
 */
public class TimeTableModelJDBCImp implements TimeTableModelInt {
	private static Logger log = Logger.getLogger(TimeTableModelJDBCImp.class);

	/**
	* find pk
	* @return pk
	* @throws DatabaseException
	*/
	public long nextPK() throws DatabaseException {
		long pk = 0;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("select max(ID) from ST_TIMETABLE");
			ResultSet r = ps.executeQuery();
			while (r.next()) {
				pk = (int) r.getLong(1);
			}
		} catch (Exception e) {
			log.error("Database Exception", e);
			throw new DatabaseException("Exception getting in pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk = pk + 1;
	}
	
	/**
	* add new timetable
	* @param bean
	* @return
	* @throws ApplicationException
	* @throws DuplicateRecordException
	*/
	public long add(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		long pk = 0;
		try {
			pk = nextPK();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("insert into ST_TIMETABLE values(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setLong(1, pk);
			ps.setLong(2, dto.getCourseId());
			ps.setString(3, dto.getCourseName());
			ps.setLong(4, dto.getSubId());
			ps.setString(5, dto.getSubName());
			ps.setString(6, dto.getSemester());
			ps.setString(7, dto.getExamTime());
			ps.setDate(8, new java.sql.Date(dto.getExamDate().getTime()));
			ps.setString(9, dto.getCreatedBy());
			ps.setString(10, dto.getModifiedBy());
			ps.setTimestamp(11, dto.getCreatedDatetime());
			ps.setTimestamp(12, dto.getModifiedDatetime());
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
	* delete timetable information
	* @param b
	* @throws DatabaseException
	*/
	public void delete(TimeTableDTO dto) throws ApplicationException {
		Connection conn =null;
	
		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("delete from ST_TIMETABLE where ID=?");
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
			throw new ApplicationException("Exception : Exception in delete timetable");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
		
	}

	
	/**
	* update timetable detail
	* @param bean
	* @throws ApplicationException
	* @throws DuplicateRecordException
	*/
	public void update(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException {
		try {
			long pk = nextPK();
		} catch (DatabaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection conn = null;
		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(
					"update ST_TIMETABLE set SUBJECT_ID=?,SUBJECT_NAME=?,COURSE_ID=?,COURSE_NAME=?,SEMESTER=?,EXAM_DATE=?,EXAM_TIME=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? where ID=?");

			ps.setLong(1, dto.getSubId());
			ps.setString(2, dto.getSubName());
			ps.setLong(3, dto.getCourseId());
			ps.setString(4, dto.getCourseName());
			ps.setString(5, dto.getSemester());
			ps.setDate(6,new java.sql.Date(dto.getExamDate().getTime()) );
			ps.setString(7, dto.getExamTime());
			ps.setString(8, dto.getCreatedBy());
			ps.setString(9, dto.getModifiedBy());
			ps.setTimestamp(10, dto.getCreatedDatetime());
			ps.setTimestamp(11, dto.getModifiedDatetime());
			ps.setLong(12, dto.getId());
			ps.executeUpdate();
			ps.close();
			conn.commit();
			System.out.println(" time table update data successfully");

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating timetable ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
	}

	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	
	/**
	* to show list of timetable
	* @param pageNo
	* @param pageSize
	* @return list
	* @throws ApplicationException
	*/
	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from st_timetable");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;
		TimeTableDTO dto = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new TimeTableDTO();
				dto.setId(rs.getLong(1));
				dto.setCourseId(rs.getLong(2));
				dto.setCourseName(rs.getString(3));
				dto.setSubId(rs.getLong(4));
				dto.setSubName(rs.getString(5));
				dto.setSemester(rs.getString(6));
				dto.setExamTime(rs.getString(7));
				dto.setExamDate(rs.getDate(8));
				dto.setCreatedBy(rs.getString(9));
				dto.setModifiedBy(rs.getString(10));
				dto.setCreatedDatetime(rs.getTimestamp(11));
				dto.setModifiedDatetime(rs.getTimestamp(12));
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

	public List search(TimeTableDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	/**
	* search timetable information
	* @param cbean1
	* @param pageNo
	* @param pageSize
	* @return list
	* @throws ApplicationException
	*/
	public List search(TimeTableDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Connection conn =null;
		StringBuffer sql = new StringBuffer("select * from ST_TIMETABLE where 1=1");
		if (dto != null) {
			if (dto.getId() > 0) {
				sql.append(" AND ID = " + dto.getId());
			}
			if (dto.getSubId() > 0) {
				sql.append(" AND SUB_ID = " + dto.getSubId());
			}
			if ((dto.getSubName() != null) && (dto.getSubName().length() > 0)) {
				sql.append(" AND SUB_NAME like '" + dto.getSubName() + "%'");
			}
			if (dto.getCourseId() > 0) {
				sql.append(" AND COURSE_ID = " + dto.getCourseId());
			}
			if ((dto.getCourseName() != null) && (dto.getCourseName().length() > 0)) {
				sql.append(" AND COURSE_NAME like '" + dto.getCourseName() + "%'");

			}
			if ((dto.getSemester() != null) && (dto.getSemester().length() > 0)) {
				sql.append(" AND SEMESTER like '" + dto.getSemester() + "%'");

			}
			if ((dto.getExamDate() != null) && (dto.getExamDate().getDate() > 0)) {
				Date date = new Date(dto.getExamDate().getTime());
				System.out.println(">>>>" + date);
				sql.append(" AND EXAM_DATE = '" + date + "'");
			}

			if ((dto.getExamTime() != null) && (dto.getExamTime().length() > 0)) {
				sql.append(" AND EXAM_TIME like '" + dto.getExamTime() + "%'");

			}
			
		}
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + "," + pageSize);

			// sql.append(" limit " + pageNo + "," + pageSize);
		}
		ArrayList<TimeTableDTO> list = new ArrayList<TimeTableDTO>();

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dto = new TimeTableDTO();
				dto.setId(rs.getLong(1));
				dto.setCourseId(rs.getLong(2));
				dto.setCourseName(rs.getString(3));
				dto.setSubId(rs.getLong(4));
				dto.setSubName(rs.getString(5));
				dto.setSemester(rs.getString(6));
				dto.setExamTime(rs.getString(7));
				dto.setExamDate(rs.getDate(8));
				dto.setCreatedBy(rs.getString(9));
				dto.setModifiedBy(rs.getString(10));
				dto.setCreatedDatetime(rs.getTimestamp(11));
				dto.setModifiedDatetime(rs.getTimestamp(12));
				list.add(dto);
			}

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search time table");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");

		return list;
		
	}
	

	/**
	* find the information with the help of pk
	* @param pk
	* @return bean
	* @throws ApplicationException
	*/

	public TimeTableDTO findByPK(long pk) throws ApplicationException {
		Connection conn =null;
		TimeTableDTO dto = null;
		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from ST_TIMETABLE where ID=?");
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dto = new TimeTableDTO();
				dto.setId(rs.getLong(1));
				dto.setCourseId(rs.getLong(2));
				dto.setCourseName(rs.getString(3));
				dto.setSubId(rs.getLong(4));
				dto.setSubName(rs.getString(5));
				dto.setSemester(rs.getString(6));
				dto.setExamTime(rs.getString(7));
				dto.setExamDate(rs.getDate(8));
				dto.setCreatedBy(rs.getString(9));
				dto.setModifiedBy(rs.getString(10));
				dto.setCreatedDatetime(rs.getTimestamp(11));
				dto.setModifiedDatetime(rs.getTimestamp(12));

			}
			ps.close();
			conn.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Timetable by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("model findBy pk end");

		return dto;
	}

	
	
	public TimeTableDTO checkByCourseName(long courseId, Date examDate)throws ApplicationException, DuplicateRecordException {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		TimeTableDTO dto = null;
		

		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE COURSE_ID=? " + "AND EXAM_DATE=?");
		long l = examDate.getTime();
		java.sql.Date date = new java.sql.Date(l);
		try {
			Connection con = JDBCDataSource.getConnection();
			ps = con.prepareStatement(sql.toString());
			ps.setLong(1, courseId);
			ps.setDate(2, date);
			rs = ps.executeQuery();

			while (rs.next()) {
				dto = new TimeTableDTO();
				dto.setId(rs.getLong(1));
				dto.setCourseId(rs.getLong(2));
				dto.setCourseName(rs.getString(3));
				dto.setSubId(rs.getLong(4));
				dto.setSubName(rs.getString(5));
				dto.setSemester(rs.getString(6));
				dto.setExamTime(rs.getString(7));
				dto.setExamDate(rs.getDate(8));
				dto.setCreatedBy(rs.getString(9));
				dto.setModifiedBy(rs.getString(10));
				dto.setCreatedDatetime(rs.getTimestamp(11));
				dto.setModifiedDatetime(rs.getTimestamp(12));
			}
		} catch (Exception e) {
			throw new ApplicationException("Exception in timeTable model checkByCourseName..." + e.getMessage());
		}
		return dto;
		
	}

	public TimeTableDTO checkBySubjectName(long courseId, long subjectId, Date examDate)
			throws ApplicationException, DuplicateRecordException {
		
		long l = examDate.getTime();
		java.sql.Date date = new java.sql.Date(l);

		PreparedStatement ps = null;
		ResultSet rs = null;
		TimeTableDTO dto = null;
		
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_TIMETABLE WHERE COURSE_ID=? AND SUBJECT_ID=? AND" + " EXAM_DATE=?");

		try {
			Connection con = JDBCDataSource.getConnection();
			ps = con.prepareStatement(sql.toString());
			ps.setLong(1, courseId);
			ps.setLong(2, subjectId);
			ps.setDate(3, date);
			rs = ps.executeQuery();

			while (rs.next()) {
				dto = new TimeTableDTO();
				dto.setId(rs.getLong(1));
				dto.setCourseId(rs.getLong(2));
				dto.setCourseName(rs.getString(3));
				dto.setSubId(rs.getLong(4));
				dto.setSubName(rs.getString(5));
				dto.setSemester(rs.getString(6));
				dto.setExamTime(rs.getString(7));
				dto.setExamDate(rs.getDate(8));
				dto.setCreatedBy(rs.getString(9));
				dto.setModifiedBy(rs.getString(10));
				dto.setCreatedDatetime(rs.getTimestamp(11));
				dto.setModifiedDatetime(rs.getTimestamp(12));
			}
		} catch (Exception e) {
			throw new ApplicationException("Exception in timeTable model checkBySubjectName..." + e.getMessage());
		}
		return dto;
	}


	public TimeTableDTO checkBysemester(long courseId, long subjectId, String semester, Date examDate)
			throws ApplicationException, DuplicateRecordException {
		
		long l = examDate.getTime();
		java.sql.Date date = new java.sql.Date(l);

		PreparedStatement ps = null;
		ResultSet rs = null;
		TimeTableDTO dto = null;
		
		Date ExDate = new Date(examDate.getTime());

		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_TIMETABLE WHERE COURSE_ID=? AND SUB_ID=? AND" + " SEMESTER=? AND EXAM_DATE=?");

		try {
			Connection con = JDBCDataSource.getConnection();
			ps = con.prepareStatement(sql.toString());
			ps.setLong(1, courseId);
			ps.setLong(2, subjectId);
			ps.setString(3, semester);
			ps.setDate(4, date);
			rs = ps.executeQuery();

			while (rs.next()) {
				dto = new TimeTableDTO();
				dto.setId(rs.getLong(1));
				dto.setCourseId(rs.getLong(2));
				dto.setCourseName(rs.getString(3));
				dto.setSubId(rs.getLong(4));
				dto.setSubName(rs.getString(5));
				dto.setSemester(rs.getString(6));
				dto.setExamTime(rs.getString(7));
				dto.setExamDate(rs.getDate(8));
				dto.setCreatedBy(rs.getString(9));
				dto.setModifiedBy(rs.getString(10));
				dto.setCreatedDatetime(rs.getTimestamp(11));
				dto.setModifiedDatetime(rs.getTimestamp(12));
				
			}
		} catch (Exception e) {
			throw new ApplicationException("Exception in timeTable model checkBySubjectName..." + e.getMessage());
		}
		return dto;
		
	}

	public TimeTableDTO timeTableDuplicacy(long courseId, String semester, Date examDate) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public TimeTableDTO timeTableDuplicacy(long courseId, long subjectId, Date examDate) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public TimeTableDTO timeTableDuplicacy(long courseId, String semester, long subjectId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public TimeTableDTO name(String emailId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
