package in.co.rays.model;

import java.util.List;

import in.co.rays.dto.SubjectDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;

// TODO: Auto-generated Javadoc
/**
 * Data Access Object of Subject.
 *
 * @author Madhuri
 * @version 1.0 
 */
public interface SubjectModelInt {
	  
  	/**
  	 * Add a Subject.
  	 *
  	 * @param dto the dto
  	 * @return the long
  	 * @throws ApplicationException the application exception
  	 * @throws DuplicateRecordException             : throws when Subject already exists
  	 */
    public long add(SubjectDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Update a Subject.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException             : if updated user record is already exist
     */
    public void update(SubjectDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Delete a Subject.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     */
    public void delete(SubjectDTO dto) throws ApplicationException;

    /**
     * Find Subject by Name.
     *
     * @param name            : get parameter
     * @return dto
     * @throws ApplicationException the application exception
     */
    public SubjectDTO findByName(String name) throws ApplicationException;

    /**
     * Find Subject by PK.
     *
     * @param pk            : get parameter
     * @return dto
     * @throws ApplicationException the application exception
     */
    public SubjectDTO findByPK(long pk) throws ApplicationException;

    /**
     * Search Subject with pagination.
     *
     * @param dto            : Search Parameters
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of Subject
     * @throws ApplicationException the application exception
     */
    public List search(SubjectDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Search Subject.
     *
     * @param dto            : Search Parameters
     * @return list : List of Subject
     * @throws ApplicationException the application exception
     */
    public List search(SubjectDTO dto) throws ApplicationException;

    /**
     * Gets List of Subject.
     *
     * @return list : List of Subject
     * @throws ApplicationException the application exception
     */
    public List list() throws ApplicationException;

    /**
     * get List of Subject with pagination.
     *
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of Subject
     * @throws ApplicationException the application exception
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;

}

