/*package in.co.rays.model;

import java.util.List;

import javax.net.ssl.SSLPeerUnverifiedException;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.dto.CollegeDTO;
import in.co.rays.dto.CourseDTO;
import in.co.rays.dto.FacultyDTO;
import in.co.rays.dto.SubjectDTO;
import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.HibDataSource;
*//**
 * Hibernate Implementation of FacultyModel
 *
 * @author 
 *//*
public class FacultyModelHibImpl implements FacultyModelInt {
	 private static Logger log = Logger.getLogger(StudentModelHibImpl.class);

	   *//**
	     * add a Faculty
	     *
	     * @param dto
	     * @throws DatabaseException
	     *//*
	public long add(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {
		 log.debug("Model add Started");
        FacultyDTO dtoExist=findByEmailId(dto.getEmailId());

         CollegeModelInt collegeModel=ModelFactory.getInstance().getCollegeModel();
         CourseModelInt courseModel=ModelFactory.getInstance().getCourseModel();
         SubjectModelInt subjectModel=ModelFactory.getInstance().getSubjectModel();
         
         CollegeDTO collegeDTO=collegeModel.findByPK(dto.getCollegeId());
         CourseDTO courseDTO=courseModel.findByPK(dto.getCourseId());
         SubjectDTO subjectDTO=subjectModel.findByPK(dto.getSubjectId());
         
         dto.setCollegeName(collegeDTO.getName());
         dto.setCourseName(courseDTO.getCourseName());
         dto.setSubjectName(subjectDTO.getSubjectName());
         
         System.out.println("sss"+dto.getCollegeName());
         System.out.println("sss"+dto.getCourseName());
         System.out.println("sss"+dto.getSubjectName());
         
         if(dtoExist!=null){
        	 throw new DuplicateRecordException("Faculty already Exist");
        	 }
		 long pk=0;
		 Session session=null;
		 Transaction transaction =null;
		 try{
			 session=HibDataSource.getSession();
			 transaction=session.beginTransaction();
			 session.save(dto);
			 pk=dto.getId();
		     transaction.commit();
			}catch(HibernateException e){
				log.error("Database Exception..", e);
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            throw new ApplicationException("Exception in Faculty Add "
	                    + e.getMessage());
	        } finally {
	            session.close();
	        }

	        log.debug("Model add End");
		
		return dto.getId();

		}
	*//**
     * Update a Faculty
     *
     * @param dto
     * @throws DatabaseException
     *//*

	public void update(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {
		Session session=null;
		Transaction transaction=null;
        FacultyDTO dtoExist=findByEmailId(dto.getEmailId());
        if(dtoExist!=null){
       	 throw new DuplicateRecordException("Faculty already Exist");
       	 }

		try{
			session=HibDataSource.getSession();
			transaction=session.beginTransaction();
			session.update(dto);
			transaction.commit();
		}catch(HibernateException e){
			log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in Faculty Update "
                    + e.getMessage());
        } finally {
            session.close();
        }

        log.debug("Model add End");
	

	}
	*//**
     * Delete a Faculty
     *
     * @param dto
     * @throws DatabaseException
     *//*

	public void delete(FacultyDTO dto) throws ApplicationException {
		Session session=null;
		Transaction transaction=null;
		try{
			session=HibDataSource.getSession();
			transaction=session.beginTransaction();
			session.delete(dto);
			System.out.println("delete successfull");
			transaction.commit();
			
		}catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in Faculty Delete"
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model delete End");		
	}
	*//**
     * Find Faculty by Login
     *
     * @param login: get parameter
     * @return dto
     * @throws DatabaseException
     *//*
	public FacultyDTO findByEmailId(String emailId) throws ApplicationException {
		Session session=null;
		FacultyDTO dto=null;
		try{
			session =HibDataSource.getSession();
			Criteria criteria=session.createCriteria(FacultyDTO.class);
			criteria.add(Restrictions.eq("emailId",emailId ));
			List list=criteria.list();
			if(list.size()==1){
			dto=(FacultyDTO)list.get(0);
			}
		}catch(HibernateException e){
     	   log.error("Database Exception..", e);
           throw new ApplicationException(
                   "Exception in getting Faculty by email " + e.getMessage());

       }finally {
           session.close();
       }

       log.debug("Model findByLoginId End");
	return dto;
	}

	public FacultyDTO findByPK(long pk) throws ApplicationException {
		Session session=null;
		FacultyDTO dto=null;
		try{
			session =HibDataSource.getSession();
			dto=(FacultyDTO)session.get(FacultyDTO.class,pk);
			
		}catch(HibernateException e){
     	   log.error("Database Exception..", e);
           throw new ApplicationException(
                   "Exception in getting Faculty by email " + e.getMessage());

       }finally {
           session.close();
       }

       log.debug("Model findByLoginId End");
	return dto;
	}

	public List search(FacultyDTO dto, int pageNo, int pageSize) throws ApplicationException {
		 log.debug("Model search Started");
		  Session session=null;
		  List list=null;
		  try{
			  session=HibDataSource.getSession();
			  Criteria criteria=session.createCriteria(FacultyDTO.class);
			  if (dto.getId() > 0) {
	                criteria.add(Restrictions.eq("id", dto.getId()));
	            }
	            if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
	                criteria.add(Restrictions.like("firstName", dto.getFirstName()
	                        + "%"));
	            }
	            if (dto.getLastName() != null && dto.getLastName().length() > 0) {
	                criteria.add(Restrictions.like("lastName", dto.getLastName()
	                        + "%"));
	            }
	            if (dto.getEmailId() != null && dto.getEmailId().length() > 0) {
	                criteria.add(Restrictions.like("emailId", dto.getEmailId() + "%"));
	            }
	            if (dto.getGender() != null && dto.getGender().length() > 0) {
	                criteria.add(Restrictions.like("gender", dto.getGender()
	                        + "%"));
	            }
	            if (dto.getGender() != null && dto.getGender().length() > 0) {
	                criteria.add(Restrictions.like("gender", dto.getGender() + "%"));
	            }
	            if (dto.getJoiningDob() != null) {
	                criteria.add(Restrictions.eq("joiningDob", dto.getJoiningDob()));
	            }
	            if (dto.getQualification() != null && dto.getQualification().length() > 0) {
	                criteria.add(Restrictions.eq("qualification", dto.getQualification()));
	            }
	            if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
	                criteria.add(Restrictions.eq("mobileNo", dto.getMobileNo()));
	            }
	            if (dto.getQualification() != null && dto.getQualification().length() > 0) {
	                criteria.add(Restrictions.eq("qualification", dto.getQualification()));
	            }
	            if (dto.getCollegeId() > 0) {
	                criteria.add(Restrictions.eq("collegeId", dto.getCollegeId()));
	            }
	            if (dto.getCourseId() > 0) {
	                criteria.add(Restrictions.eq("courseId", dto.getCourseId()));
	            }
	            if (dto.getSubjectId() > 0) {
	                criteria.add(Restrictions.eq("subjectId", dto.getSubjectId()));
	            }

  System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	            if(pageSize>0){
	            	criteria.setFirstResult((pageNo-1)*pageSize);
	            	criteria.setMaxResults(pageSize);
	            }
	            System.out.println("{{{{{{{{{{}}}}}}}}}}}}}}}}}");
	            list=criteria.list();
	            }
	            catch (HibernateException e) {
	                log.error("Database Exception..", e);
	                e.printStackTrace();
	                throw new ApplicationException("Exception in user search");
	            } finally {
	                session.close();
	            }

	            log.debug("Model search End");

		  
		return list;

	}

	public List search(FacultyDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	public List list() throws ApplicationException {
		return list(0,0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		Session session=null;
		List list=null;
		try{
			session=HibDataSource.getSession();
			Criteria criteria=session.createCriteria(FacultyDTO.class);
			
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
                    "Exception : Exception in  Users list");
        } finally {
            session.close();
        }

        log.debug("Model list End");
		
		return list;

	}

}
*/

/*
 * 
 */
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
import in.co.rays.dto.FacultyDTO;
import in.co.rays.dto.SubjectDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.HibDataSource;
// TODO: Auto-generated Javadoc

/**
 * Hibernate Implementation of FacultyModel.
 * 
 * 
 * @author Madhuri
 * @version 1.0 
 */
public class FacultyModelHibImpl implements FacultyModelInt {
	 
 	/** The log. */
 	private static Logger log = Logger.getLogger(FacultyModelHibImpl.class);

	   /**
   	 * add a Faculty.
   	 *
   	 * @param dto the dto
   	 * @return the long
   	 * @throws ApplicationException the application exception
   	 * @throws DuplicateRecordException the duplicate record exception
   	 */
	public long add(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {
		
		 log.debug("Model add Started");
         
		 FacultyDTO dtoExist=findByEmailId(dto.getEmailId());

         CollegeModelInt collegeModel=ModelFactory.getInstance().getCollegeModel();
         CourseModelInt courseModel=ModelFactory.getInstance().getCourseModel();
         SubjectModelInt subjectModel=ModelFactory.getInstance().getSubjectModel();
         
         CollegeDTO collegeDTO=collegeModel.findByPK(dto.getCollegeId());
         CourseDTO courseDTO=courseModel.findByPK(dto.getCourseId());
         SubjectDTO subjectDTO=subjectModel.findByPK(dto.getSubjectId());
         
         dto.setCollegeName(collegeDTO.getName());
         dto.setCourseName(courseDTO.getCourseName());
         dto.setSubjectName(subjectDTO.getSubjectName());
       
         if(dtoExist!=null){
        	
        	 throw new DuplicateRecordException("Faculty already Exist");
          }
         
		 long pk=0;
		 
		 Session session=null;
		 
		 Transaction transaction =null;
		 
		 try
		  {
		
			 session=HibDataSource.getSession();
			 transaction=session.beginTransaction();
			 session.save(dto);
			 pk=dto.getId();
		     transaction.commit();
		  }
		 catch(HibernateException e)
		   {
				log.error("Database Exception..", e);
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            throw new ApplicationException("Exception in Faculty Add "
	                    + e.getMessage());
	        } finally {
	            session.close();
	        }

	        log.debug("Model add End");
		
		return dto.getId();

		}
	
	/**
	 * Update a Faculty.
	 *
	 * @param dto the dto
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordException the duplicate record exception
	 */

	public void update(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {
		
		 System.out.println("---------------------------->UPDATE<-------------------");
		
		 Session session=null;
		
		 Transaction transaction=null;
     
	     FacultyDTO dtoExist=findByEmailId(dto.getEmailId());
        
	     if(dtoExist!=null && dtoExist.getId() != dto.getId())
         {
       	 
        	 throw new DuplicateRecordException("Faculty already Exist");
       	 }

		try{
			 CollegeModelInt collegeModel=ModelFactory.getInstance().getCollegeModel();
	         CourseModelInt courseModel=ModelFactory.getInstance().getCourseModel();
	         SubjectModelInt subjectModel=ModelFactory.getInstance().getSubjectModel();
	         
	         CollegeDTO collegeDTO=collegeModel.findByPK(dto.getCollegeId());
	         CourseDTO courseDTO=courseModel.findByPK(dto.getCourseId());
	         SubjectDTO subjectDTO=subjectModel.findByPK(dto.getSubjectId());
	         
	         dto.setCollegeName(collegeDTO.getName());
	         dto.setCourseName(courseDTO.getCourseName());
	         dto.setSubjectName(subjectDTO.getSubjectName());
	       
			session=HibDataSource.getSession();
			transaction=session.beginTransaction();
			session.update(dto);
			
			transaction.commit();
		}
		catch(HibernateException e){
			log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in Faculty Update "
                    + e.getMessage());
        } finally {
            session.close();
        }

        log.debug("Model add End");
	

	}
	
	/**
	 * Delete a Faculty.
	 *
	 * @param dto the dto
	 * @throws ApplicationException the application exception
	 */

	public void delete(FacultyDTO dto) throws ApplicationException {
		Session session=null;
		Transaction transaction=null;
		try{
			session=HibDataSource.getSession();
			transaction=session.beginTransaction();
			session.delete(dto);
//			System.out.println("delete successfull");
			transaction.commit();
			
		}catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in Faculty Delete"
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model delete End");		
	}
	
	/**
	 * Find Faculty by Login.
	 *
	 * @param emailId the email id
	 * @return dto
	 * @throws ApplicationException the application exception
	 */
	public FacultyDTO findByEmailId(String emailId) throws ApplicationException {
		Session session=null;
		FacultyDTO dto=null;
		try{
			session =HibDataSource.getSession();
			Criteria criteria=session.createCriteria(FacultyDTO.class);
			criteria.add(Restrictions.eq("emailId",emailId ));
			List list=criteria.list();
			if(list.size()==1){
			dto=(FacultyDTO)list.get(0);
			}
		}catch(HibernateException e){
     	   log.error("Database Exception..", e);
           throw new ApplicationException(
                   "Exception in getting Faculty by email " + e.getMessage());

       }finally {
           session.close();
       }

       log.debug("Model findByLoginId End");
	return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.FacultyModelInt#findByPK(long)
	 */
	public FacultyDTO findByPK(long pk) throws ApplicationException {
		Session session=null;
		FacultyDTO dto=null;
		try{
			session =HibDataSource.getSession();
			dto=(FacultyDTO)session.get(FacultyDTO.class,pk);
			
		}catch(HibernateException e){
     	   log.error("Database Exception..", e);
           throw new ApplicationException(
                   "Exception in getting Faculty by email " + e.getMessage());

       }finally {
           session.close();
       }

       log.debug("Model findByLoginId End");
	return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.FacultyModelInt#search(in.co.rays.dto.FacultyDTO, int, int)
	 */
	public List search(FacultyDTO dto, int pageNo, int pageSize) throws ApplicationException {
		 log.debug("Model search Started");
		  Session session=null;
		  List list=null;
		  try{
			  session=HibDataSource.getSession();
			  Criteria criteria=session.createCriteria(FacultyDTO.class);
			  if (dto.getId() > 0) {
	                criteria.add(Restrictions.eq("id", dto.getId()));
	            }
	            if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
	                criteria.add(Restrictions.like("firstName", dto.getFirstName()
	                        + "%"));
	            }
	            if (dto.getLastName() != null && dto.getLastName().length() > 0) {
	                criteria.add(Restrictions.like("lastName", dto.getLastName()
	                        + "%"));
	            }
	            if (dto.getEmailId() != null && dto.getEmailId().length() > 0) {
	                criteria.add(Restrictions.like("emailId", dto.getEmailId() + "%"));
	            }
	            if (dto.getGender() != null && dto.getGender().length() > 0) {
	                criteria.add(Restrictions.like("gender", dto.getGender()
	                        + "%"));
	            }
	            if (dto.getGender() != null && dto.getGender().length() > 0) {
	                criteria.add(Restrictions.like("gender", dto.getGender() + "%"));
	            }
	            if (dto.getJoiningDate() != null) {
	                criteria.add(Restrictions.eq("joiningDate", dto.getJoiningDate()));
	            }
	            if (dto.getQualification() != null && dto.getQualification().length() > 0) {
	                criteria.add(Restrictions.eq("qualification", dto.getQualification()));
	            }
	            if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
	                criteria.add(Restrictions.eq("mobileNo", dto.getMobileNo()));
	            }
	            if (dto.getQualification() != null && dto.getQualification().length() > 0) {
	                criteria.add(Restrictions.eq("qualification", dto.getQualification()));
	            }
	            if (dto.getCollegeId() > 0) {
	                criteria.add(Restrictions.eq("collegeId", dto.getCollegeId()));
	            }
	            if (dto.getCourseId() > 0) {
	                criteria.add(Restrictions.eq("courseId", dto.getCourseId()));
	            }
	            if (dto.getSubjectId() > 0) {
	                criteria.add(Restrictions.eq("subjectId", dto.getSubjectId()));
	            }


	            if(pageSize>0){
	            	criteria.setFirstResult((pageNo-1)*pageSize);
	            	criteria.setMaxResults(pageSize);
	            }
	            list=criteria.list();
	            }
	            catch (HibernateException e) {
	                log.error("Database Exception..", e);
	                throw new ApplicationException("Exception in user search");
	            } finally {
	                session.close();
	            }

	            log.debug("Model search End");

		  
		return list;

	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.FacultyModelInt#search(in.co.rays.dto.FacultyDTO)
	 */
	public List search(FacultyDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.FacultyModelInt#list()
	 */
	public List list() throws ApplicationException {
		return list(0,0);
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.FacultyModelInt#list(int, int)
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		Session session=null;
		List list=null;
		try{
			session=HibDataSource.getSession();
			Criteria criteria=session.createCriteria(FacultyDTO.class);
			
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
                    "Exception : Exception in  Users list");
        } finally {
            session.close();
        }

        log.debug("Model list End");
		
		return list;

	}

}
