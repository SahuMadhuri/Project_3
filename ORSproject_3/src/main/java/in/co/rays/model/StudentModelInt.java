package in.co.rays.model;

import java.util.List;

import in.co.rays.dto.StudentDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;

// TODO: Auto-generated Javadoc
/**
 * Data Access Object of Student.
 *
 * @author Madhuri
 * @version 1.0 
 */

public interface StudentModelInt {

    /**
     * Add a Student.
     *
     * @param dto the dto
     * @return the long
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException             : throws when Student already exists
     */
    public long add(StudentDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Update a Student.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException             : if updated user record is already exist
     */
    public void update(StudentDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Delete a Student.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     */
    public void delete(StudentDTO dto) throws ApplicationException;

    /**
     * Find Student by EmailId.
     *
     * @param emailId the email id
     * @return dto
     * @throws ApplicationException the application exception
     */
    public StudentDTO findByEmailId(String emailId) throws ApplicationException;

    /**
     * Find Student by PK.
     *
     * @param pk            : get parameter
     * @return dto
     * @throws ApplicationException the application exception
     */
    public StudentDTO findByPK(long pk) throws ApplicationException;

    /**
     * Search Student with pagination.
     *
     * @param dto the dto
     * @param pageNo the page no
     * @param pageSize : Size of Page
     * @return list : List of Student
     * @throws ApplicationException the application exception
     */
    public List search(StudentDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Search Student.
     *
     * @param dto the dto
     * @return list : List of Student
     * @throws ApplicationException the application exception
     */
    public List search(StudentDTO dto) throws ApplicationException;

    /**
     * Gets List of College.
     *
     * @return list : List of College
     * @throws ApplicationException the application exception
     */
    public List list() throws ApplicationException;

    /**
     * get List of College with pagination.
     *
     * @param pageNo : Current Page No.
     * @param pageSize the page size
     * @return list : List of College
     * @throws ApplicationException the application exception
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;

}

