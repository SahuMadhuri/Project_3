package in.co.rays.model;


import java.util.List;

import in.co.rays.dto.CollegeDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;

// TODO: Auto-generated Javadoc
/**
 * Data Access Object of College.
 *
 * @author Madhuri
 * @version 1.0  
 */

public interface CollegeModelInt {

    /**
     * Add a College.
     *
     * @param dto the dto
     * @return the long
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException             : throws when college already exists
     */
    public long add(CollegeDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Update a College.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException             : if updated user record is already exist
     */
    public void update(CollegeDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Delete a College.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     */
    public void delete(CollegeDTO dto) throws ApplicationException;

    /**
     * Find College by Name.
     *
     * @param name            : get parameter
     * @return dto
     * @throws ApplicationException the application exception
     */
    public CollegeDTO findByName(String name) throws ApplicationException;

    /**
     * Find College by PK.
     *
     * @param pk            : get parameter
     * @return dto
     * @throws ApplicationException the application exception
     */
    public CollegeDTO findByPK(long pk) throws ApplicationException;

    /**
     * Search College with pagination.
     *
     * @param dto            : Search Parameters
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of College
     * @throws ApplicationException the application exception
     */
    public List search(CollegeDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Search College.
     *
     * @param dto            : Search Parameters
     * @return list : List of College
     * @throws ApplicationException the application exception
     */
    public List search(CollegeDTO dto) throws ApplicationException;

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
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of College
     * @throws ApplicationException the application exception
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;

}
