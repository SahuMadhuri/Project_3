package in.co.rays.ctl;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.dto.BaseDTO;
import in.co.rays.dto.MarksheetDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.MarksheetModelInt;
import in.co.rays.model.ModelFactory;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * The Class MarksheetListCtl.
 * 
 * @author Madhuri
 * @version 1.0
 */
@WebServlet(name="MarksheetListCtl",urlPatterns={"/ctl/MarksheetListCtl"})
public class MarksheetListCtl extends BaseCtl {
	 
 	/** The log. */
 	private static Logger log = Logger.getLogger(MarksheetListCtl.class);

	 
	 /* (non-Javadoc)
 	 * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
 	 */
 	@Override
	protected void preload(HttpServletRequest request) {

		 MarksheetModelInt model=ModelFactory.getInstance().getMarksheetModel();
		 try {
			List rollList= model.list();
			request.setAttribute("rollList", rollList);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	 
	 }
	 
	 /* (non-Javadoc)
 	 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
 	 */
 	@Override
	 protected BaseDTO populateDTO(HttpServletRequest request) {
	        MarksheetDTO dto = new MarksheetDTO();

	        dto.setId(DataUtility.getLong(request.getParameter("roll")));

	        dto.setName(DataUtility.getString(request.getParameter("name")));
	        populate(dto, request);
	        return dto;
	    }

	 
	 
	 /* (non-Javadoc)
 	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 	 */
 	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        log.debug("UserListCtl doGet Start");
	        List list = null;
	        List nextList=null;
	        int pageNo = 1;
	        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
	        MarksheetDTO bean = (MarksheetDTO) populateDTO(request);
	        String op = DataUtility.getString(request.getParameter("operation"));
	        // get the selected checkbox ids array for delete list
	        String[] ids = request.getParameterValues("ids");
	        MarksheetModelInt model = ModelFactory.getInstance().getMarksheetModel();
	        try {
	            list = model.search(bean, pageNo, pageSize);
	            nextList=model.search(bean,pageNo+1,pageSize);
	            //nextList=model.search(dto,pageNo+1,pageSize);
	            request.setAttribute("nextlist", nextList.size());
				
	            ServletUtility.setList(list, request);
	            if (list == null || list.size() == 0) {
	                ServletUtility.setErrorMessage("No record found ", request);
	            }
	           
	            ServletUtility.setDto(bean, request);
	            ServletUtility.setList(list, request);
	            ServletUtility.setPageNo(pageNo, request);
	            ServletUtility.setPageSize(pageSize, request);
	            ServletUtility.forward(getView(), request, response);
	        } catch (ApplicationException e) {
	            log.error(e);
	            ServletUtility.handleException(e, request, response);
	            return;
	        }
	        log.debug("MarksheetListCtl doget End");

	 }


	 
	 /* (non-Javadoc)
 	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 	 */
 	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        log.debug("UserListCtl doPost Start");
	        List list = null;
	        List nextList =null;
	        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
	        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

	        pageNo = (pageNo == 0) ? 1 : pageNo;
	        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
	                .getValue("page.size")) : pageSize;
	        MarksheetDTO dto = (MarksheetDTO) populateDTO(request);
	        String op = DataUtility.getString(request.getParameter("operation"));
	        // get the selected checkbox ids array for delete list
	        String ids[] = request.getParameterValues("ids");
	        MarksheetModelInt model = ModelFactory.getInstance().getMarksheetModel();
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
	                ServletUtility.redirect(ORSView.MARKSHEET_CTL, request, response);
	                return;
	            
	            }else if(OP_RESET.equalsIgnoreCase(op))
	    		{
	  			  ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);	
	  			  return;
	  		}

	            
	            else if (OP_DELETE.equalsIgnoreCase(op)) {
	                pageNo = 1;
	                if (ids != null && ids.length > 0) {
	                    MarksheetDTO deletedto = new MarksheetDTO();
	                    for (String id : ids) {
	                    	
	                    	long idnew=DataUtility.getLong(id);
	                    	
	                        deletedto.setId(idnew);
	                        
	                        model.delete(deletedto);
	                        
	                        ServletUtility.setErrorMessage("Data Deleted Successfully", request);
	                    }
	                } else if(OP_BACK.equalsIgnoreCase(op)){
	                	ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
	                }else{
	                    ServletUtility.setErrorMessage("Select at least one record", request);
	                }
	            }
	            list = model.search(dto, pageNo, pageSize);
	            nextList=model.search(dto, pageNo+1, pageSize);
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
	        log.debug("MarksheetListCtl doGet End");

	 }
	 
	 
	 /* (non-Javadoc)
 	 * @see in.co.rays.controller.BaseCtl#getView()
 	 */
 	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.MARKSHEET_LIST_VIEW;
	}

}
