package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.dto.MarksheetDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;



/**
 * JDBC implements of Marksheet model
 * 
 * @author Madhuri
 * @version 1.0 
 *
 */
public class MarksheetModelJDBCImp implements MarksheetModelInt{
	private static Logger log = Logger.getLogger(MarksheetModelJDBCImp.class);
     
	/**
	 * add new id
	 * @return pk
	 * @throws DatabaseException
	 */
	public static long nextPK() throws DatabaseException {
		long nextPK = 0;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID)FROM st_marksheet");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nextPK = rs.getLong(1);
			}
			ps.close();
			conn.close();
		} catch (Exception e) {
			log.error("Database Exception", e);
			throw new DatabaseException("Exception getting in pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return nextPK + 1;
	}
	
	
	/**
	* add new marksheet
	* @param bean
	* @return
	* @throws ApplicationException
	* @throws DuplicateRecordException
	*/
	public long add(MarksheetDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		long pk = 0;
		Connection conn = null;
		
		try {
			pk = nextPK();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("insert into st_marksheet values(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setLong(1, pk);
			ps.setString(2, dto.getRollNo());
			ps.setLong(3, dto.getStudentId());
			ps.setString(4, dto.getName());
			ps.setInt(5, dto.getPhysics());
			ps.setInt(6, dto.getChemistry());
			ps.setInt(7, dto.getMaths());
			
			ps.setString(8, dto.getCreatedBy());
			ps.setString(9, dto.getModifiedBy());
			ps.setTimestamp(10, dto.getCreatedDatetime());
			ps.setTimestamp(11, dto.getModifiedDatetime());
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
			throw new ApplicationException("Exception : Exception in add Student"+e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
		
	}

	
	/**
	* delete marksheet information
	* @param b
	* @throws DatabaseException
	*/
	
	public void delete(MarksheetDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("delete from st_marksheet where ID=?");
			ps.setLong(1, dto.getId());
			ps.execute();
			ps.close();
			conn.commit();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete marksheet");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	
	/**
	* update marksheet detail
	* @param bean
	* @throws ApplicationException
	* @throws DuplicateRecordException
	*/
	
	public void update(MarksheetDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(
					"update st_marksheet set ROLL_NO=?, STUDENT_ID=?, NAME=?, PHYSICS=?, CHEMISTRY=?, MATHS=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? where ID=? ");
			ps.setString(1, dto.getRollNo());
			ps.setLong(2, dto.getStudentId());
			ps.setString(3, dto.getName());
			ps.setInt(4, dto.getPhysics());
			ps.setInt(5, dto.getChemistry());
			ps.setInt(6, dto.getMaths());
			ps.setString(7, dto.getCreatedBy());
			ps.setString(8, dto.getModifiedBy());
			ps.setTimestamp(9, dto.getCreatedDatetime());
			ps.setTimestamp(10, dto.getModifiedDatetime());
			ps.setLong(11, dto.getId());
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
			throw new ApplicationException("Exception in updating marksheet ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}
	
	
	/**
	* to show list of marksheet
	* @param pageNo
	* @param pageSize
	* @return list
	* @throws ApplicationException
	*/

	public List list(int pageNo, int pageSize) throws ApplicationException {
		
		log.debug("Model  list Started");

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from st_marksheet");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MarksheetDTO dto = new MarksheetDTO();
				dto.setId(rs.getLong(1));
				dto.setRollNo(rs.getString(2));
				dto.setStudentId(rs.getLong(3));
				dto.setName(rs.getString(4));
				dto.setChemistry(rs.getInt(5));
				dto.setMaths(rs.getInt(6));
				dto.setPhysics(rs.getInt(7));
				dto.setCreatedBy(rs.getString(8));
				dto.setModifiedBy(rs.getString(9));
				dto.setCreatedDatetime(rs.getTimestamp(10));
				dto.setModifiedDatetime(rs.getTimestamp(11));
				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {
			log.error(e);
			throw new ApplicationException("Exception in getting list of Marksheet");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model  list End");
		return list;

	}
		

	public List search(MarksheetDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	
	/**
	* search marksheet information
	* @param cbean1
	* @param pageNo
	* @param pageSize
	* @return list
	* @throws ApplicationException
	*/
	
	public List search(MarksheetDTO dto, int pageNo, int pageSize) throws ApplicationException {
		
		Connection con = null;
       
		StringBuffer sql = new StringBuffer("select * from st_marksheet where 1=1");
		if (dto != null) {
			if (dto.getId() > 0) {
				sql.append(" AND ID = " + dto.getId());
			}
			if ((dto.getRollNo() != null) && (dto.getRollNo().length() > 0)) {
				
				sql.append(" AND ROLL_NO like '" + dto.getRollNo() + "%'");
			}
			if (dto.getStudentId() > 0) {
				sql.append(" AND STUDENT_ID = " + dto.getStudentId());
			}
			if (dto.getName() != null && dto.getName().length() > 0) {
				sql.append(" AND NAME like '" + dto.getName() + "%'");
			}
			if (dto.getPhysics() > 0) {
				sql.append(" AND PHYSICS = " + dto.getPhysics());
			}
			if (dto.getChemistry() > 0) {
				sql.append(" AND CHEMISTRY = " + dto.getChemistry());
			}
			if (dto.getMaths() > 0) {
				sql.append(" AND MATHS = " + dto.getMaths());
			}
		}
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + "," + pageSize);

			// sql.append(" limit " + pageNo + "," + pageSize);
		}
		List list = new ArrayList();
		try {

			con = JDBCDataSource.getConnection();

			PreparedStatement ps = con.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MarksheetDTO dto1 = new MarksheetDTO();
				dto1.setId(rs.getLong(1));
				dto1.setRollNo(rs.getString(2));
				dto1.setStudentId(rs.getLong(3));
				dto1.setName(rs.getString(4));
				dto1.setChemistry(rs.getInt(5));
				dto1.setMaths(rs.getInt(6));
				dto1.setPhysics(rs.getInt(7));
				dto1.setCreatedBy(rs.getString(8));
				dto1.setModifiedBy(rs.getString(9));
				dto1.setCreatedDatetime(rs.getTimestamp(10));
				dto1.setModifiedDatetime(rs.getTimestamp(11));
				list.add(dto1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			
			//throw new ApplicationException("Exception : Exception in search time table");
		} finally {
			JDBCDataSource.closeConnection(con);
		}

		log.debug("Model search End");

		return list;

		
	}

	public MarksheetDTO findByPK(long pk) throws ApplicationException {
		
		MarksheetDTO dto = null;
		Connection con = null;

		try {

			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from st_marksheet where ID=?");
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dto = new MarksheetDTO();
				dto.setId(rs.getLong(1));
				dto.setRollNo(rs.getString(2));
				dto.setStudentId(rs.getLong(3));
				dto.setName(rs.getString(4));
				dto.setChemistry(rs.getInt(5));
				dto.setMaths(rs.getInt(6));
				dto.setPhysics(rs.getInt(7));
				dto.setCreatedBy(rs.getString(8));
				dto.setModifiedBy(rs.getString(9));
				dto.setCreatedDatetime(rs.getTimestamp(10));
				dto.setModifiedDatetime(rs.getTimestamp(11));

				
			}

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting marksheet by pk");
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("model findBy pk end");

		return dto;

		
		
	}

	public MarksheetDTO findByRollNo(String rollNo) throws ApplicationException {
		
		MarksheetDTO dto = null;
		Connection con = null;

		try {

			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from st_marksheet where ROLL_NO=?");
			ps.setString(1, rollNo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dto = new MarksheetDTO();
				dto.setId(rs.getLong(1));
				dto.setRollNo(rs.getString(2));
				dto.setStudentId(rs.getLong(3));
				dto.setName(rs.getString(4));
				dto.setChemistry(rs.getInt(5));
				dto.setMaths(rs.getInt(6));
				dto.setPhysics(rs.getInt(7));
				dto.setCreatedBy(rs.getString(8));
				dto.setModifiedBy(rs.getString(9));
				dto.setCreatedDatetime(rs.getTimestamp(10));
				dto.setModifiedDatetime(rs.getTimestamp(11));

			}

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by marksheet");
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("Model findByRollNo End");
		return dto;
		
	}

	public List getMeritList(int pageNo, int pageSize) throws ApplicationException {
		
log.debug("marksheet model get merit list start");
		
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer(
				"select ID,ROLL_NO,NAME,PHYSICS,CHEMISTRY,MATHS (PHYSICS+CHEMISTRY+MATHS)as Total from st_marksheet order by Total desc ");
		if(pageSize>0){
			pageNo=(pageNo-1)*pageSize;
			sql.append(" limit "+pageNo+","+pageSize);
		}
		
		Connection con = null;
		MarksheetDTO dto =null;
		try {
			

			con = JDBCDataSource.getConnection();

			PreparedStatement ps = con.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dto = new MarksheetDTO();
				dto.setId(rs.getLong(1));
				dto.setRollNo(rs.getString(2));
				dto.setStudentId(rs.getLong(3));
				dto.setName(rs.getString(4));
				dto.setChemistry(rs.getInt(5));
				dto.setMaths(rs.getInt(6));
				dto.setPhysics(rs.getInt(7));
				dto.setCreatedBy(rs.getString(8));
				dto.setModifiedBy(rs.getString(9));
				dto.setCreatedDatetime(rs.getTimestamp(10));
				dto.setModifiedDatetime(rs.getTimestamp(11));
				list.add(dto);
			}
		}catch (Exception e) {
            log.error(e);
            throw new ApplicationException(
                    "Exception in getting merit list of Marksheet");
        } finally {
            JDBCDataSource.closeConnection(con);
        }
        log.debug("Model  MeritList End");
        return list;
		
	}


	

}
