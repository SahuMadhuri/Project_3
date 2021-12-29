package in.co.rays.ctl;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.dto.BaseDTO;
import in.co.rays.dto.StudentDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.CollegeModelInt;
import in.co.rays.model.ModelFactory;
import in.co.rays.model.StudentModelInt;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentListCtl.
 * 
 * @author Madhuri
 * @version 1.0
 */
@WebServlet(name="StudentListCtl",urlPatterns={"/ctl/StudentListCtl"})
public class StudentListCtl extends BaseCtl {
    
    /** The log. */
    private static Logger log = Logger.getLogger(StudentListCtl.class);

    
    
    /* (non-Javadoc)
     * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected void preload(HttpServletRequest request) {

    	CollegeModelInt model=ModelFactory.getInstance().getCollegeModel();
    	try {
			List collegeList=  model.list();
			request.setAttribute("collegeList", collegeList);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    
    }
    
    /* (non-Javadoc)
     * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected BaseDTO populateDTO(HttpServletRequest request) {

        StudentDTO dto = new StudentDTO();

        dto.setFirstName(DataUtility.getString(request
                .getParameter("firstName")));
        dto.setEmail(DataUtility.getString(request.getParameter("email")));
        dto.setCollegeId(DataUtility.getLong(request.getParameter("college")));

        return dto;
    }
    
    
    
    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	
        log.debug("StudentListCtl doGet Start");
        List list = null;
        List nextList=null;
        int pageNo = 1;

        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

        StudentDTO dto = (StudentDTO) populateDTO(request);

        String op = DataUtility.getString(request.getParameter("operation"));

        StudentModelInt model = ModelFactory.getInstance().getStudentModel();
        try {
            list = model.search(dto, pageNo, pageSize);
            nextList=model.search(dto,pageNo+1,pageSize);
            request.setAttribute("nextlist", nextList.size());
			 ServletUtility.setList(list, request);
            if (list == null || list.size() == 0) {
                ServletUtility.setErrorMessage("No record found ", request);
            }
            
            ServletUtility.setList(list, request);

            ServletUtility.setPageNo(pageNo, request);
            ServletUtility.setPageSize(pageSize, request);
            ServletUtility.forward(getView(), request, response);

        } catch (ApplicationException e) {
            log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }
        log.debug("StudentListCtl doGet End");

    }
    
    
    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("StudentListCtl doPost Start");

        
        
        List list = null;
        List nextList=null;
        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
        pageNo = (pageNo == 0) ? 1 : pageNo;
        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
                .getValue("page.size")) : pageSize;

        StudentDTO dto = (StudentDTO) populateDTO(request);
        String op = DataUtility.getString(request.getParameter("operation"));
        StudentModelInt model = ModelFactory.getInstance().getStudentModel();
         String[] ids=request.getParameterValues("ids");
        try {

            if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
                    || "Previous".equalsIgnoreCase(op)) {

                if (OP_SEARCH.equalsIgnoreCase(op)) {
                    pageNo = 1;
                } else if (OP_NEXT.equalsIgnoreCase(op)) {
                    pageNo++;
                } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
                    pageNo--;
                }

            }else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.STUDENT_CTL, request, response);
				return;
			}else if (OP_RESET.equalsIgnoreCase(op)) {

				ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					StudentDTO deletedto = new StudentDTO();
					for (String id : ids) {
						deletedto.setId(DataUtility.getLong(id));
						model.delete(deletedto);
						ServletUtility.setErrorMessage("Data Deleted Successfully", request);
					}
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			}
            
			 if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request, response);
				return;
			}
            list = model.search(dto, pageNo, pageSize);
            nextList=model.search(dto,pageNo+1,pageSize);
            request.setAttribute("nextlist", nextList.size());
			ServletUtility.setList(list, request);
            if (list == null || list.size() == 0&&!OP_DELETE.equalsIgnoreCase(op)) {
                ServletUtility.setErrorMessage("No record found ", request);
            }
            ServletUtility.setList(list, request);

            ServletUtility.setPageNo(pageNo, request);
            ServletUtility.setPageSize(pageSize, request);
            ServletUtility.forward(getView(), request, response);

        } catch (ApplicationException e) {
            log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }
        log.debug("StudentListCtl doGet End");

    
    }

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.STUDENT_LIST_VIEW;
	}

}
