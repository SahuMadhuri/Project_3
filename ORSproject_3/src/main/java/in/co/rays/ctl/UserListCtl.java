package in.co.rays.ctl;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.dto.BaseDTO;
import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.ModelFactory;
import in.co.rays.model.RoleModelInt;
import in.co.rays.model.UserModelInt;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
@WebServlet(name="UserListCtl",urlPatterns={"/ctl/UserListCtl"})
public class UserListCtl extends BaseCtl {
	/** The log. */
	private static Logger log=Logger.getLogger(UserListCtl.class);
	
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void preload(HttpServletRequest request) {
   
		
		
		RoleModelInt model = ModelFactory.getInstance().getRoleModel();
		try 
		{
			List roleList=model.list();
			request.setAttribute("roleList", roleList);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	
	}
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
	UserDTO dto=new UserDTO();
	
	dto.setFirstname(DataUtility.getString(request.getParameter("firstName")));
	dto.setLastname(DataUtility.getString(request.getParameter("lastName")));
	dto.setLoginid(DataUtility.getString(request.getParameter("loginId")));
	dto.setRoleid(DataUtility.getLong(request.getParameter("roleId")));
	return dto;
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        log.debug("UserListCtl doGet Start");
        
        System.out.println("in do get of user ctl");
        List list = null;
        List nextList=null;
        int pageNo = 1;
        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
        UserDTO dto = (UserDTO) populateDTO(request);
        String op = DataUtility.getString(request.getParameter("operation"));
        // get the selected checkbox ids array for delete list
        String[] ids = request.getParameterValues("ids");
        UserModelInt model =ModelFactory.getInstance().getUserModel();
        try {
            list = model.Search(dto, pageNo, pageSize);
            
            nextList=model.Search(dto,pageNo+1,pageSize);
            request.setAttribute("nextlist", nextList.size());
            
			 ServletUtility.setList(list, request);
            if (list == null || list.size() == 0) {
                ServletUtility.setErrorMessage("No record found ", request);
            }
            
            ServletUtility.setDto(dto, request);
            ServletUtility.setList(list, request);
            ServletUtility.setPageNo(pageNo, request);
            ServletUtility.setPageSize(pageSize, request);
            ServletUtility.forward(getView(), request, response);
        } catch (ApplicationException e) {
            log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }
        log.debug("UserListCtl doget End");
        System.out.println("End of do get of user ctl");
    }

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        log.debug("UserListCtl doPost Start");
        List list = null;
        List nextList =null;
        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

        pageNo = (pageNo == 0) ? 1 : pageNo;
        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
                .getValue("page.size")) : pageSize;
        UserDTO dto = (UserDTO) populateDTO(request);
        String op = DataUtility.getString(request.getParameter("operation"));
        // get the selected checkbox ids array for delete list
        String ids[] = request.getParameterValues("ids");
        UserModelInt model =ModelFactory.getInstance().getUserModel();
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

            } else if (OP_NEW.equalsIgnoreCase(op)) {
                ServletUtility.redirect(ORSView.USER_CTL, request, response);
                return;
            
            }else if(OP_RESET.equalsIgnoreCase(op))
    		{
  			  ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);	
  			  return;
  		}

            
            else if (OP_DELETE.equalsIgnoreCase(op)) {
                pageNo = 1;
                if (ids != null && ids.length > 0) {
                    UserDTO deletedto = new UserDTO();
                    for (String id : ids) {
                    	
                    	Long idnew=DataUtility.getLong(id);
                    	
                        deletedto.setId(idnew);
                        
                        try {
							model.delete(deletedto);
						} catch (DuplicateRecordException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                        
                        ServletUtility.setErrorMessage(
                                "Data Deleted Successfully", request);
                    }
                } else if(OP_BACK.equalsIgnoreCase(op)){
                	ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
                }else{
                    ServletUtility.setErrorMessage(
                            "Select at least one record", request);
                }
            }
            list = model.Search(dto, pageNo, pageSize);
            System.out.println(list+"userListCtl");
           
            nextList=model.Search(dto,pageNo+1,pageSize);
            request.setAttribute("nextlist", nextList.size());
			
            ServletUtility.setList(list, request);
            if (list == null || list.size() == 0&&!OP_DELETE.equalsIgnoreCase(op)) {
                ServletUtility.setErrorMessage("No record found ", request);
            }
            
            ServletUtility.setDto(dto, request);
            ServletUtility.setList(list, request);
            ServletUtility.setPageNo(pageNo, request);
            ServletUtility.setPageSize(pageSize, request);
            ServletUtility.forward(getView(), request, response);

        } catch (ApplicationException e) {
            log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }
        log.debug("UserListCtl doGet End");
    }

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		return ORSView.USER_LIST_VIEW;
	}


}
