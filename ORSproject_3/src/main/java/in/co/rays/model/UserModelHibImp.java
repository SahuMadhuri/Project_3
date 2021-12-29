package in.co.rays.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.util.EmailBuilder;
import in.co.rays.util.EmailMessage;
import in.co.rays.util.EmailUtility;
import in.co.rays.util.HibDataSource;

/**
 * @author Madhuri
 * @version 1.0 
 *
 */
public class UserModelHibImp implements UserModelInt{

	public long add(UserDTO dto) throws ApplicationException, DuplicateRecordException {
		System.out.println("UserDto Add mathod");
		
		UserDTO existDto=null;
		existDto = findByLogin(dto.getLoginid());
		if(existDto != null) {
			throw new DuplicateRecordException("Login id already Exist");
		}
		Session session = HibDataSource.getSession();
		Transaction tx= null;
		try {
			tx=session.beginTransaction();
			session.save(dto);
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			if(tx!=null) {
				tx.rollback();
			}
		}
		return 0;
	}

	public void delete(UserDTO dto) throws ApplicationException, DuplicateRecordException {
		Session session =null;
		Transaction tx=null;
		try {
			session= HibDataSource.getSession();
			tx=session.beginTransaction();
			session.delete(dto);
			tx.commit();
		}catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in user Delete"+e.getMessage());
			
		}finally {
			session.close();
		}
		
	}

	public void update(UserDTO dto) throws ApplicationException, DuplicateRecordException {
		Session session =null;
		Transaction tx= null;
		UserDTO existDTO= findByLogin(dto.getLoginid());
		if (existDTO != null && existDTO.getId() != dto.getId()) {
			throw new DuplicateRecordException("LoginId is already exist");
		}

		try {
			session =HibDataSource.getSession();
			tx=session.beginTransaction();
			session.update(dto);
			tx.commit();
			
		}catch(HibernateException e) {
			if(tx !=null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in user update"+e.getMessage());
		}finally {
			session.close();
		}
		
	}

	public UserDTO findByPK(long pk) throws ApplicationException {
		Session session =null;
		UserDTO dto =null;
		
		try {
			session = HibDataSource.getSession();
			dto = (UserDTO) session.get(UserDTO.class, pk);
			
			
		}catch(HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting User by pk");
		}
		System.out.println("userdto findBy pk dto"+dto);
		return dto;
	}

	public UserDTO findByLogin(String login) throws ApplicationException, DuplicateRecordException {
		Session session = null;
		UserDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(UserDTO.class);
			criteria.add(Restrictions.eq("loginid", login));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (UserDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting User by Login " + e.getMessage());

		} finally {
			session.close();
		}

		return dto;
	}

	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0,0);
	}

	public List list(int PageNo, int PageSize) throws ApplicationException {
		Session session=null;
		List list=null;
		try {
			session =HibDataSource.getSession();
			Criteria criteria= session.createCriteria(UserDTO.class);
			if(PageSize>0) {
				PageNo =(PageNo -1)* PageSize;
				criteria.setFirstResult(PageNo);
				criteria.setMaxResults(PageSize);
			}
			list=criteria.list();
		}catch(HibernateException e) {
			throw new ApplicationException("Exception : Exception in user list");
			
		}
		return list;
	}

	public List Search(UserDTO dto, int PageNo, int PageSize) throws ApplicationException {
		Session session = null;
		ArrayList<UserDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(UserDTO.class);
			if (dto != null) {
				if (dto.getId() > 0) {
					criteria.add(Restrictions.like("id", dto.getId()));
				}
				if (dto.getFirstname() != null && dto.getFirstname().length() > 0) {
					criteria.add(Restrictions.like("firstname", dto.getFirstname() + "%"));
				}

				if (dto.getLastname() != null && dto.getLastname().length() > 0) {
					criteria.add(Restrictions.like("lastname", dto.getLastname() + "%"));
				}
				if (dto.getLoginid() != null && dto.getLoginid().length() > 0) {
					criteria.add(Restrictions.like("loginid", dto.getLoginid() + "%"));
				}
				if (dto.getPassword() != null && dto.getPassword().length() > 0) {
					criteria.add(Restrictions.like("password", dto.getPassword() + "%"));
				}
				if (dto.getGender() != null && dto.getGender().length() > 0) {
					criteria.add(Restrictions.like("gender", dto.getGender() + "%"));
				}
				if (dto.getDob() != null && dto.getDob().getDate() > 0) {
					criteria.add(Restrictions.eq("dob", dto.getDob()));
				}
//				if (dto.getLastlogin() != null && dto.getLastlogin().getTime() > 0) {
//					criteria.add(Restrictions.eq("lastLogin", dto.getLastlogin()));
//				}
				if (dto.getRoleid() > 0) {
					criteria.add(Restrictions.eq("roleid", dto.getRoleid()));
				}
//				if (dto.getUnsuccessfullogin() > 0) {
//					criteria.add(Restrictions.eq("unSuccessfulLogin", dto.getUnsuccessfullogin()));
//				}
			}
			// if pageSize is greater than 0
			if (PageSize > 0) {
				PageNo = (PageNo - 1) * PageSize;
				criteria.setFirstResult(PageNo);
				criteria.setMaxResults(PageSize);
			}
			list = (ArrayList<UserDTO>) criteria.list();
			//System.out.println("888888888"+criteria);
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in user search");
		} finally {
			session.close();
		}

		return list;
	}

	public List Search(UserDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return Search(dto,0,0);
	}

	public boolean changePassword(long id, String oldPassword, String newPassword)
			throws RecordNotFoundException, ApplicationException {
		
		boolean flag = false;
		
		UserDTO dtoExist = null;

		dtoExist = findByPK(id);
		
		if (dtoExist != null && dtoExist.getPassword().equals(oldPassword)) {
			
			dtoExist.setPassword(newPassword);
			
			try {
				update(dtoExist);
			} catch (Exception e) {

				throw new ApplicationException("LoginId is already exist");
			}
			flag = true;
		} else {
			throw new RecordNotFoundException("Login not exist");
		}

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("login", dtoExist.getLoginid());
		map.put("password", dtoExist.getPassword());
		map.put("firstName", dtoExist.getFirstname());
		map.put("lastName", dtoExist.getLastname());

		String message = EmailBuilder.getChangePasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(dtoExist.getLoginid());
		msg.setSubject("SUNARYS ORS Password has been changed Successfully.");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		return flag;

	}

	public UserDTO authenticate(String login, String password) throws ApplicationException {
		Session session = null;
		UserDTO dto = null;
		session = HibDataSource.getSession();
		Query q = session.createQuery("from UserDTO where LOGINID=? and PASSWORD=?");
		q.setString(0, login);
		q.setString(1, password);
		List list = q.list();
		if (list.size() > 0) {
			dto = (UserDTO) list.get(0);
		} else {
			dto = null;

		}
		return dto;
	}

	public boolean forgetPassword(String login) throws ApplicationException, RecordNotFoundException, DuplicateRecordException {
        UserDTO dto = findByLogin(login);
        boolean flag = false;
        System.out.println("userdto forget mathod dto: "+dto);
        if(dto==null) {
        	throw new RecordNotFoundException("Email id Does not matches");
        }
        HashMap<String, String> map= new HashMap<String, String>();
        map.put("login", dto.getLoginid());
		map.put("password", dto.getPassword());
		map.put("firstName", dto.getFirstname());
		map.put("lastName", dto.getLastname());
		String message = EmailBuilder.getForgetPasswordMessage(map);
		EmailMessage msg = new EmailMessage();
		msg.setTo(login);
		msg.setSubject("SUNARYS ORS Password reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);
		EmailUtility.sendMail(msg);
		flag = true;

		return flag;
	}

public boolean resetPassword(UserDTO dto) throws ApplicationException, RecordNotFoundException {
		
		String newPassword = String.valueOf(new Date().getTime()).substring(0, 4);
		

		try {
			UserDTO userData = findByPK(dto.getId());
			userData.setPassword(newPassword);
			update(userData);
		} catch (DuplicateRecordException e) {
			return false;
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", dto.getLoginid());
		map.put("password", dto.getPassword());

		String message = EmailBuilder.getForgetPasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(dto.getLoginid());
		msg.setSubject("Password has been reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		return true;
	}

	public long registerUser(UserDTO dto) throws ApplicationException, DuplicateRecordException {
		long pk = add(dto);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", dto.getLoginid());
		map.put("password", dto.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(dto.getLoginid());
		msg.setSubject("Registration is successful for ORS Project SUNRAYS Technologies");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		return pk;
	}

	public List getRoles(UserDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}


}
