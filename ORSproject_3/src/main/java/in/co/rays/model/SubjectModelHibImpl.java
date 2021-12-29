package in.co.rays.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.dto.CourseDTO;
import in.co.rays.dto.SubjectDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.HibDataSource;
// TODO: Auto-generated Javadoc

/**
 * Hibernate Implementation of SubjectModel.
 *
 * @author Madhuri
 * @version 1.0 
 */

public class SubjectModelHibImpl implements SubjectModelInt{
    
    /** The log. */
    private static Logger log = Logger.getLogger(CourseModelHibImpl.class);
    
    /**
     * Add a Course.
     *
     * @param dto the dto
     * @return the long
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException the duplicate record exception
     */

	public long add(SubjectDTO dto) throws ApplicationException, DuplicateRecordException {
		
		log.debug("Model add Started");
		
		Long pk=0L;
        
		SubjectDTO dtoExist=findByName(dto.getSubjectName());
		
		if(dtoExist!=null){
			
			throw new DuplicateRecordException("Subject already exist");
		}
        
	    CourseModelInt courseModel=ModelFactory.getInstance().getCourseModel();
		CourseDTO courseDTO=courseModel.findByPK(dto.getCourseId());
		dto.setCourseName(courseDTO.getCourseName());
		
		Session session=null;
        
		Transaction transaction=null;
        
		try{
        	
			session=HibDataSource.getSession();
        	transaction=session.beginTransaction();
        	session.save(dto);
        	pk = dto.getId();
        	transaction.commit();
        }
		catch(HibernateException e)
        {	
        	e.printStackTrace();
            log.error("Database Exception..", e);
           
            if(transaction != null) {
              transaction.rollback();
             }
           throw new ApplicationException("Exception in Subject Add "
                + e.getMessage());
    } finally {
        session.close();
    }

    log.debug("Model add End");
    
    return dto.getId();
	}
	
	/**
	 * Update a Subject.
	 *
	 * @param dto the dto
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordException the duplicate record exception
	 */
	public void update(SubjectDTO dto) throws ApplicationException, DuplicateRecordException {
		
		log.debug("Model update Started");
		
		Long pk=0L;
        
		SubjectDTO dtoExist=findByName(dto.getSubjectName());
		
		if(dtoExist!=null && dtoExist.getId() != dto.getId()){
			
			throw new DuplicateRecordException("Subject already exist");
		}
        
		 
		CourseModelInt courseModel=ModelFactory.getInstance().getCourseModel();
		CourseDTO courseDTO=courseModel.findByPK(dto.getCourseId());
		dto.setCourseName(courseDTO.getCourseName());
			
		Session session=null;
        
		Transaction transaction=null;
        
		try{
        
			 session = HibDataSource.getSession();
             transaction = session.beginTransaction();
             session.update(dto);
             transaction.commit();
        }
		catch(HibernateException e)
		 {
        	log.error("Database Exception..", e);
         
        	if (transaction != null)
        	{
                transaction.rollback();
                throw new ApplicationException("Exception in College Update"
                        + e.getMessage());
             }
        } 
		finally 
		{
           
			session.close();
        }
        log.debug("Model update End");
	}
	
	/**
	 * Delete a Course.
	 *
	 * @param dto the dto
	 * @throws ApplicationException the application exception
	 */
	public void delete(SubjectDTO dto) throws ApplicationException {
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
            throw new ApplicationException("Exception in Subject Delete"
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model delete End");	
	}
	
	/**
	 * Find course by Subject Name.
	 *
	 * @param name the name
	 * @return dto
	 * @throws ApplicationException the application exception
	 */
	public SubjectDTO findByName(String name) throws ApplicationException {
		log.debug("Model findByName Started");
		 Session session = null;
	     SubjectDTO dto = null;
	     try {
	            session = HibDataSource.getSession();
	            Criteria criteria = session.createCriteria(SubjectDTO.class);
	            criteria.add(Restrictions.eq("subjectName", name));
	            List list = criteria.list();
	            if (list.size() > 0) {
	                dto = (SubjectDTO) list.get(0);
	            }

	        } catch (HibernateException e) {
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception in getting User by Login " + e.getMessage());

	        } finally {
	            session.close();
	        }

	        log.debug("Model findByName End");
		return dto;
	}
	
	/**
	 * Find Subject by PK.
	 *
	 * @param pk            : get parameter
	 * @return dto
	 * @throws ApplicationException the application exception
	 */
	public SubjectDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
        Session session = null;
        SubjectDTO dto = null;
        try {
            session = HibDataSource.getSession();
            dto = (SubjectDTO)session.get(SubjectDTO.class, pk);
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting College by pk");
        } finally {
            session.close();
        }

        log.debug("Model findByPK End");
		return dto;
	}
	
	/**
	 * Searches Subject with pagination.
	 *
	 * @param dto            : Search Parameters
	 * @param pageNo            : Current Page No.
	 * @param pageSize            : Size of Page
	 * @return list : List of Subject
	 * @throws ApplicationException the application exception
	 */
	public List search(SubjectDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Session session=null;
		List list=null;
		try{
			session=HibDataSource.getSession();
			Criteria criteria=session.createCriteria(SubjectDTO.class);
			if(dto.getId()>0){
				criteria.add(Restrictions.eq("id",dto.getId()));
			}
			if(dto.getSubjectName()!=null&&dto.getSubjectName().length()>0){
				criteria.add(Restrictions.like("subjectName", dto.getSubjectName()));
			}
			if(dto.getCourseName()!=null&&dto.getCourseName().length()>0){
				criteria.add(Restrictions.like("courseName", dto.getCourseName()));
			}
			if(dto.getCourseId()>0){
				criteria.add(Restrictions.eq("courseId",dto.getCourseId()));
			}
		    if (pageSize > 0) {
                criteria.setFirstResult(((pageNo - 1) * pageSize));
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException("Exception in course search");
        } finally {
            session.close();
        }

        log.debug("Model search End");
		return list;
		}
	
	/**
	 * Search Subject.
	 *
	 * @param dto            : Search Parameters
	 * @return the list
	 * @throws ApplicationException the application exception
	 */

	public List search(SubjectDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}
	
	/**
	 * Gets List of Course.
	 *
	 * @return list : List of Course
	 * @throws ApplicationException the application exception
	 */

	public List list() throws ApplicationException {
		return list(0, 0);
	}
	
	/**
	 * get List of Subject with pagination.
	 *
	 * @param pageNo            : Current Page No.
	 * @param pageSize            : Size of Page
	 * @return list : List of subject
	 * @throws ApplicationException the application exception
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(SubjectDTO.class);

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
                    "Exception : Exception in  Course list");
        } finally {
            session.close();
        }

        log.debug("Model list End");
        return list;
	}

}
