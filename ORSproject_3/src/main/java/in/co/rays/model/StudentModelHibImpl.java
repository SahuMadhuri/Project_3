package in.co.rays.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.dto.CollegeDTO;
import in.co.rays.dto.CourseDTO;
import in.co.rays.dto.StudentDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.HibDataSource;

// TODO: Auto-generated Javadoc
/**
 * Hibernate Implementation of StudentModel.
 *
 * @author Madhuri
 * @version 1.0 
 */
public class StudentModelHibImpl implements StudentModelInt {
	 
 	/** The log. */
 	private static Logger log = Logger.getLogger(StudentModelHibImpl.class);
	
	 
	   /**
   	 * add a Student.
   	 *
   	 * @param dto the dto
   	 * @return the long
   	 * @throws ApplicationException the application exception
   	 * @throws DuplicateRecordException the duplicate record exception
   	 */
	public long add(StudentDTO dto) throws ApplicationException, DuplicateRecordException {
		 
		    log.debug("Model add Started");
		
		    StudentDTO dtoExist = findByEmailId(dto.getEmail());

	        // Check if updated Email already exist
	        if (dtoExist != null && dtoExist.getId() != dto.getId()) {
	           
	        	throw new DuplicateRecordException("Email is already exist");
	        }
          
	    CollegeModelHibImpl cModel = new CollegeModelHibImpl();
	    
		 long pk=0;
		 
		 Session session=null;
		 
		 Transaction transaction =null;
		 
		 CollegeDTO cDto = null;
		    
		        
		  try{
			 cDto = cModel.findByPK(dto.getCollegeId());
			 
			 dto.setCollegeName(cDto.getName());
			 
			 session=HibDataSource.getSession();
			 
			 transaction=session.beginTransaction();
			 
			 session.save(dto);
			 
			 pk=dto.getId();
		     
			 transaction.commit();
			
		  }
		  catch(HibernateException e){
				log.error("Database Exception..", e);
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            throw new ApplicationException("Exception in Student Add "
	                    + e.getMessage());
	        } finally {
	            session.close();
	        }

	        log.debug("Model add End");
		
		return dto.getId();
	}
	   
   	/**
   	 * Update a Student.
   	 *
   	 * @param dto the dto
   	 * @throws ApplicationException the application exception
   	 * @throws DuplicateRecordException the duplicate record exception
   	 */
	public void update(StudentDTO dto) throws ApplicationException, DuplicateRecordException {
		
		log.debug("Model update Started");
        
		Session session = null;
        
		Transaction transaction = null;

        StudentDTO dtoExist = findByEmailId(dto.getEmail());

        // Check if updated Email already exist
        if (dtoExist != null && dtoExist.getId() != dto.getId()) {
            throw new DuplicateRecordException("Email is already exist");
        }

        CollegeModelHibImpl cModel = new CollegeModelHibImpl();
        
        CollegeDTO cDto = null;
        
        try {
        	 
        	 cDto = cModel.findByPK(dto.getCollegeId());
			 
			 dto.setCollegeName(cDto.getName());
			 
             session = HibDataSource.getSession();
            
             transaction = session.beginTransaction();
            
             session.update(dto);
            
             transaction.commit();
        } 
        catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
                throw new ApplicationException("Exception in Student Update"
                        + e.getMessage());
            }
        } finally {
            session.close();
        }
        log.debug("Model update End");
	}
	
	/**
	 * Delete a Student.
	 *
	 * @param dto the dto
	 * @throws ApplicationException the application exception
	 */
	public void delete(StudentDTO dto) throws ApplicationException {
		log.debug("Model delete Started");
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibDataSource.getSession();
            transaction = session.beginTransaction();
            session.delete(dto);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in Student Delete"
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model delete End");		
	}
	 
 	/**
 	 * Find Student by Login.
 	 *
 	 * @param email the email
 	 * @return dto
 	 * @throws ApplicationException the application exception
 	 */
	public StudentDTO findByEmailId(String email) throws ApplicationException {
           StudentDTO dto=null;
           Session session=null;
           try{
        	   session =HibDataSource.getSession();
        	   Criteria criterio=session.createCriteria(StudentDTO.class);
        	   criterio.add(Restrictions.eq("email", email));
        	   List list=criterio.list();
        	   if (list.size() == 1) {
                   dto = (StudentDTO) list.get(0);
               }
           }catch(HibernateException e){
        	   log.error("Database Exception..", e);
               throw new ApplicationException(
                       "Exception in getting Student by email " + e.getMessage());

           }finally {
               session.close();
           }

           log.debug("Model findByLoginId End");
		return dto;
	}
	
	/**
	 * Find Student by PK.
	 *
	 * @param pk the pk
	 * @return dto
	 * @throws ApplicationException the application exception
	 */
	public StudentDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
        Session session = null;
        StudentDTO dto = null;
        try {
            session = HibDataSource.getSession();
            dto = (StudentDTO) session.get(StudentDTO.class, pk);
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting Student by pk");
        } finally {
            session.close();
        }

        log.debug("Model findByPK End");
        return dto;	
        }
	
	/**
	 * Searches Student with pagination.
	 *
	 * @param dto the dto
	 * @param pageNo : Current Page No.
	 * @param pageSize the page size
	 * @return list : List of Student
	 * @throws ApplicationException the application exception
	 */

	public List search(StudentDTO dto, int pageNo, int pageSize) throws ApplicationException {
		 log.debug("Model search Started");
	        Session session = null;
	        List list = null;
	        try {
	            session = HibDataSource.getSession();
	            Criteria criteria = session.createCriteria(StudentDTO.class);

	            if (dto.getId() > 0) {
	                criteria.add(Restrictions.eq("id", dto.getId()));
	            }
	            if (dto.getCollegeId() > 0) {
	                criteria.add(Restrictions.eq("collegeId", dto.getCollegeId()));
	            }
	            if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
	                criteria.add(Restrictions.like("firstName", dto.getFirstName()
	                        + "%"));
	            }
	            if (dto.getLastName() != null && dto.getLastName().length() > 0) {
	                criteria.add(Restrictions.like("lastName", dto.getLastName()
	                        + "%"));
	            }
	            if (dto.getDob() != null) {
	                criteria.add(Restrictions.eq("dob", dto.getDob()));
	            }
	            if (dto.getEmail() != null && dto.getEmail().length() > 0) {
	                criteria.add(Restrictions.like("email", dto.getEmail()
	                        + "%"));
	            }
	            if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
	                criteria.add(Restrictions.like("mobileNo", dto.getMobileNo()
	                        + "%"));
	            }

	            // if page size is greater than zero the apply pagination
	            if (pageSize > 0) {
	                criteria.setFirstResult(((pageNo - 1) * pageSize));
	                criteria.setMaxResults(pageSize);
	            }

	            list = criteria.list();
	        } catch (HibernateException e) {
	            log.error("Database Exception..", e);
	            throw new ApplicationException("Exception in Student search");
	        } finally {
	            session.close();
	        }

	        log.debug("Model search End");
	        return list;
	}
	
	 /**
 	 * Searches Student.
 	 *
 	 * @param dto the dto
 	 * @return the list
 	 * @throws ApplicationException the application exception
 	 */
		public List search(StudentDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}
		 
 		/**
 		 * Gets List of Student.
 		 *
 		 * @return list : List of Student
 		 * @throws ApplicationException the application exception
 		 */
	public List list() throws ApplicationException {
		return list(0, 0);
	}
	 
 	/**
 	 * get List of Student with pagination.
 	 *
 	 * @param pageNo the page no
 	 * @param pageSize the page size
 	 * @return list : List of Student
 	 * @throws ApplicationException the application exception
 	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(StudentDTO.class);

            // if page size is greater than zero then apply pagination
            if (pageSize > 0) {
                pageNo = ((pageNo - 1) * pageSize) + 1;
                criteria.setFirstResult(pageNo);
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in  Student list");
        } finally {
            session.close();
        }

        log.debug("Model list End");
        return list;
	}

}
