package in.co.rays.model;

import java.util.List;

import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;

/**
 * @author Madhuri
 * @version 1.0 
 *
 */
public interface UserModelInt {
	public long add(UserDTO dto)throws ApplicationException, DuplicateRecordException;
	public void delete(UserDTO dto)throws ApplicationException, DuplicateRecordException;
	public void update(UserDTO dto)throws ApplicationException,DuplicateRecordException;
	public UserDTO findByPK(long pk)throws ApplicationException,DuplicateRecordException;
	public UserDTO findByLogin(String login)throws ApplicationException,DuplicateRecordException ;
	public List list() throws ApplicationException;
	public List list(int PageNo, int PageSize)throws ApplicationException;
	public List Search(UserDTO dto, int PageNo,int PageSize)throws ApplicationException;
	public List Search(UserDTO dto)throws ApplicationException;
	public boolean changePassword(long id,String newPassword, String oldPassword)throws ApplicationException, RecordNotFoundException, DuplicateRecordException;
	public UserDTO authenticate(String login, String password)throws ApplicationException;
	public boolean forgetPassword(String login)throws ApplicationException,RecordNotFoundException, DuplicateRecordException;
	public boolean resetPassword(UserDTO dto)throws ApplicationException,RecordNotFoundException;
	public long registerUser(UserDTO dto)throws ApplicationException,RecordNotFoundException,DuplicateRecordException;
	public List getRoles(UserDTO dto)throws ApplicationException;
	
	

}
