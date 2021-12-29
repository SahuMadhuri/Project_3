package in.co.rays.model;

import java.util.List;

import in.co.rays.dto.MarksheetDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;

// TODO: Auto-generated Javadoc
/**
 * Service of Marksheet.
 *
 * @author Madhuri
 * @version 1.0 
 */
public interface MarksheetModelInt {
    
    /**
     * Adds a Marksheet.
     *
     * @param dto the dto
     * @return the long
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException the duplicate record exception
     */

    public long add(MarksheetDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Deletes a Marksheet.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     */
    public void delete(MarksheetDTO dto) throws ApplicationException;

    /**
     * Finds Marksheet by Roll No.
     *
     * @param rollNo            : get parameter
     * @return dto
     * @throws ApplicationException the application exception
     */
    public MarksheetDTO findByRollNo(String rollNo) throws ApplicationException;

    /**
     * Finds Marksheet by PK.
     *
     * @param pk            : get parameter
     * @return dto
     * @throws ApplicationException the application exception
     */

    public MarksheetDTO findByPK(long pk) throws ApplicationException;

    /**
     * Updates a Marksheet.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException the duplicate record exception
     */
    public void update(MarksheetDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Searches Marksheet.
     *
     * @param dto            : Search Parameters
     * @return the list
     * @throws ApplicationException the application exception
     */
    public List search(MarksheetDTO dto) throws ApplicationException;

    /**
     * Searches Marksheet with pagination.
     *
     * @param dto            : Search Parameters
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of Marksheets
     * @throws ApplicationException the application exception
     */
    public List search(MarksheetDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Gets List of Marksheet.
     *
     * @return list : List of Marksheets
     * @throws ApplicationException the application exception
     */
    public List list() throws ApplicationException;

    /**
     * get List of Marksheet with pagination.
     *
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of Marksheets
     * @throws ApplicationException the application exception
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;

    /**
     * get Merit List of Marksheet with pagination.
     *
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of Marksheets
     * @throws ApplicationException the application exception
     */
    public List getMeritList(int pageNo, int pageSize)
            throws ApplicationException;
}
