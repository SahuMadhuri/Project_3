package in.co.rays.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.dto.RoleDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.HibDataSource;

/**
 * @author Madhuri
 * @version 1.0 
 *
 */
public class RoleModelHibImp implements RoleModelInt{
	private static Logger log = Logger.getLogger(RoleModelHibImp.class);
	public long add(RoleDTO dto) throws ApplicationException, DuplicateRecordException {
		Session session = null;
		Transaction tx = null;
		long pk = 0L;
		
		/*
		 * RoleDTO existDto = findByName(dto.getName()); if (existDto != null) { throw
		 * new DuplicateRecordException("Role already exist"); }
		 */
		
		
		System.out.println("Before try");
		try {
			System.out.println("After try");
			session = HibDataSource.getSession();
			System.out.println("After get Session"+session);
			tx = session.beginTransaction();
			System.out.println(" After tx");
			session.save(dto);
			System.out.println("save");
			pk = dto.getId();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();

			}
	e.printStackTrace();
			throw new ApplicationException("Exception in Role Add " + e.getMessage());
		} finally {
			session.close();
		}
		return pk;
		
	}
	
	public void delete(RoleDTO dto) throws ApplicationException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();

			}
			throw new ApplicationException("Exception in Role delete " + e.getMessage());
		} finally {
			session.close();
		}
		
	}

	public void update(RoleDTO dto) throws ApplicationException, DuplicateRecordException {
log.debug("Model update Started");
		
		Session session=null;
		
		Transaction transaction=null;
		
		RoleDTO duplicataRole = findByName(dto.getName());
	        
	        if (duplicataRole != null && duplicataRole.getId() != dto.getId())
	        {
	            throw new DuplicateRecordException("Role already exists");
	        }
		
		try{
			session=HibDataSource.getSession();
			transaction=session.beginTransaction();
			session.update(dto);
			transaction.commit();
		}catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
                throw new ApplicationException("Exception in Role Update"
                        + e.getMessage());
            }
        } finally {
            session.close();
        }
        log.debug("Model update End");
	}
		
	

	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(RoleDTO.class);
			if (pageSize > 0) {
				pageNo = ((pageNo - 1) * pageSize) + 1;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
				System.out.println("RoleModel list 1"+criteria.setFirstResult(pageNo));
				System.out.println("RoleModel list2 "+criteria.setMaxResults(pageSize));
			}
			list = criteria.list();
		} catch (HibernateException e) {

			throw new ApplicationException("Exception : Exception in  role list");
		} finally {
			session.close();
		}
		return list;
	}

	public List search(RoleDTO dto) throws ApplicationException {
	
		return search(dto, 0, 0);
	}

	public List search(RoleDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Session session=null;
		List list=null;
		try {
			session=HibDataSource.getSession();
			Criteria criteria=session.createCriteria(RoleDTO.class);
			if(dto.getId()>0){
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if(dto.getName()!=null&& dto.getName().length()>0){
				criteria.add(Restrictions.like("name", dto.getName()+"%"));
			}
			if(dto.getDescription()!=null&& dto.getDescription().length()>0){
				criteria.add(Restrictions.like("description", dto.getDescription()+"%"));
			}
			if(pageSize>0){
				criteria.setFirstResult((pageNo-1)*pageSize);
				criteria.setMaxResults(pageSize);
			}
			list=criteria.list();
		} catch (HibernateException e) {
            
            throw new ApplicationException("Exception in course search");
        } finally {
            session.close();
        }
		return list;
	}

	public RoleDTO findByPK(long pk) throws ApplicationException {
		 Session session = HibDataSource.getSession();
			
			try {
				RoleDTO dto = (RoleDTO) session.get(RoleDTO.class, pk);
				return dto;
			} catch (HibernateException e) {

				throw new ApplicationException("Exception : Exception in getting Role by pk");
			} finally {
				session.close();}
	}

	public RoleDTO findByName(String name) throws ApplicationException {
		Session session = null;
		RoleDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(RoleDTO.class);
			criteria.add(Restrictions.eq("name", name));
			List list = criteria.list();
			if (list.size() > 0) {
				dto = (RoleDTO) list.get(0);
			}
		} catch (HibernateException e) {

			throw new ApplicationException("Exception in getting Role by Login " + e.getMessage());

		} finally {
			session.close();
		}
		return dto;

	}
	}


