package in.co.rays.model;

import java.util.List;

import in.co.rays.dto.FacultyDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;

// TODO: Auto-generated Javadoc
/**
 * Data Access Object of Faculty.
 *
 * @author Madhuri
 * @version 1.0 
 */

public interface FacultyModelInt {

    /**
     * Add a Faculty.
     *
     * @param dto the dto
     * @return the long
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException             : throws when Faculty already exists
     */
    public long add(FacultyDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Update a Faculty.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException             : if updated user record is already exist
     */
    public void update(FacultyDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Delete a Faculty.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     */
    public void delete(FacultyDTO dto) throws ApplicationException;

    /**
     * Find Faculty by EmailId.
     *
     * @param emailId the email id
     * @return dto
     * @throws ApplicationException the application exception
     */
    public FacultyDTO findByEmailId(String emailId) throws ApplicationException;

    /**
     * Find Faculty by PK.
     *
     * @param pk            : get parameter
     * @return dto
     * @throws ApplicationException the application exception
     */
    public FacultyDTO findByPK(long pk) throws ApplicationException;

    /**
     * Search Faculty with pagination.
     *
     * @param dto the dto
     * @param pageNo the page no
     * @param pageSize : Size of Page
     * @return list : List of Faculty
     * @throws ApplicationException the application exception
     */
    public List search(FacultyDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Search Faculty.
     *
     * @param dto the dto
     * @return list : List of Faculty
     * @throws ApplicationException the application exception
     */
    public List search(FacultyDTO dto) throws ApplicationException;

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

