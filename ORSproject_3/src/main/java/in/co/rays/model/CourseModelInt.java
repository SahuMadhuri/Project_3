package in.co.rays.model;

import java.util.List;

import in.co.rays.dto.CourseDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;



// TODO: Auto-generated Javadoc
/**
 * Data Access Object of Course.
 *
 * @author Madhuri
 * @version 1.0  
 */
public interface CourseModelInt {
	  
  	/**
  	 * Add a Course.
  	 *
  	 * @param dto the dto
  	 * @return the long
  	 * @throws ApplicationException the application exception
  	 * @throws DuplicateRecordException             : throws when Course already exists
  	 */
    public long add(CourseDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Update a Course.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException             : if updated user record is already exist
     */
    public void update(CourseDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Delete a Course.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     */
    public void delete(CourseDTO dto) throws ApplicationException;

    /**
     * Find Course by Name.
     *
     * @param name            : get parameter
     * @return dto
     * @throws ApplicationException the application exception
     */
    public CourseDTO findByName(String name) throws ApplicationException;

    /**
     * Find Course by PK.
     *
     * @param pk            : get parameter
     * @return dto
     * @throws ApplicationException the application exception
     */
    public CourseDTO findByPK(long pk) throws ApplicationException;

    /**
     * Search Course with pagination.
     *
     * @param dto            : Search Parameters
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of Course
     * @throws ApplicationException the application exception
     */
    public List search(CourseDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Search Course.
     *
     * @param dto            : Search Parameters
     * @return list : List of Course
     * @throws ApplicationException the application exception
     */
    public List search(CourseDTO dto) throws ApplicationException;

    /**
     * Gets List of Course.
     *
     * @return list : List of Course
     * @throws ApplicationException the application exception
     */
    public List list() throws ApplicationException;

    /**
     * get List of Course with pagination.
     *
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of Course
     * @throws ApplicationException the application exception
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;

}
