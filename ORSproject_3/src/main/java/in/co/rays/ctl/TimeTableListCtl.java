package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.dto.BaseDTO;
import in.co.rays.dto.TimeTableDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.CourseModelInt;
import in.co.rays.model.ModelFactory;
import in.co.rays.model.SubjectModelInt;
import in.co.rays.model.TimeTableModelInt;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * The Class TimeTableListCtl.
 * 
 * @author Madhuri
 * @version 1.0
 */
@WebServlet(name = "TimeTableListCtl", urlPatterns = { "/ctl/TimeTableListCtl" })
public class TimeTableListCtl extends BaseCtl {
	
	/** The log. */
	private static Logger log = Logger.getLogger(TimeTableListCtl.class);

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void preload(HttpServletRequest request) {

		CourseModelInt courseModel= ModelFactory.getInstance().getCourseModel();
		
		SubjectModelInt subjectModel= ModelFactory.getInstance().getSubjectModel();
		
		
		try
		{
			
			List courseList=courseModel.list();
			List subjectList=subjectModel.list();
			request.setAttribute("courseList", courseList);
			request.setAttribute("subjectList", subjectList);
		}
		catch(ApplicationException e){}
		
	
	}
	
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		TimeTableDTO dto = new TimeTableDTO();

		dto.setCourseId(DataUtility.getLong(request.getParameter("course")));

		dto.setSubId(DataUtility.getLong(request.getParameter("subject")));

		dto.setExamDate(DataUtility.getDate(request.getParameter("Exdate")));
		
		System.out.println(request.getParameter("Exdate"));
		
		return dto;

	
	}
	
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		log.debug("Time table ctl doGet Start");
        
		List list = null;
        
        List nextList=null;
        
        int pageNo = 1;
        
        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
       
        TimeTableDTO dto = (TimeTableDTO) populateDTO(request);
        
        String op = DataUtility.getString(request.getParameter("operation"));
        // get the selected checkbox ids array for delete list
        
        String[] ids = request.getParameterValues("ids");
        
        TimeTableModelInt model =ModelFactory.getInstance().getTimeTableModel();
        
        try {
            
        	list = model.search(dto, pageNo, pageSize);
            
        	System.out.println("List=>"+list);
            
        	nextList=model.search(dto,pageNo+1,pageSize);
            
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
        } 
        catch (ApplicationException e) {
            log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }
		log.debug("Time table ctl doPOst End");

	
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
		log.debug("Time table ctl doPost Start");
		List list = null;
        List nextList =null;
        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

        pageNo = (pageNo == 0) ? 1 : pageNo;
        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
        
        TimeTableDTO dto = (TimeTableDTO) populateDTO(request);
        
        String op = DataUtility.getString(request.getParameter("operation"));
        
        // get the selected checkbox ids array for delete list
        String ids[] = request.getParameterValues("ids");
        
        TimeTableModelInt model = ModelFactory.getInstance().getTimeTableModel();
        
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
                ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
                return;
            
            }else if(OP_RESET.equalsIgnoreCase(op))
    		{
  			  ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);	
  			  return;
  		}

            
            else if (OP_DELETE.equalsIgnoreCase(op)) {
                
            	pageNo = 1;
               
                if (ids != null && ids.length > 0) {
                
                	TimeTableDTO deleteDto = new TimeTableDTO();
                    
                	for (String id : ids) {
                    	
                    	Long idnew=DataUtility.getLong(id);
                    	
                        deleteDto.setId(idnew);
                        
                        model.delete(deleteDto);
                        
                        ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
                    }
                } 
                else if(OP_BACK.equalsIgnoreCase(op)){
                	ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
                }
                else{
                    ServletUtility.setErrorMessage(
                            "Select at least one record", request);
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
		log.debug("Time table ctl doGet End");

		
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		return ORSView.TIMETABLE_LIST_VIEW;
	}


}
