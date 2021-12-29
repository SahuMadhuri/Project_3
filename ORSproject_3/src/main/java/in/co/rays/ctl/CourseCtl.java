package in.co.rays.ctl;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.dto.BaseDTO;
import in.co.rays.dto.CourseDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CourseModelInt;
import in.co.rays.model.ModelFactory;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * The Class CourseCtl.
 * 
 * @author Madhuri
 * @version 1.0
 */
@WebServlet(name = "CourseCtl", urlPatterns = { "/ctl/CourseCtl" })
public class CourseCtl extends BaseCtl {
	
	/** The log. */
	private static Logger log = Logger.getLogger(CourseCtl.class);

	
	
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		String courseName = request.getParameter("courseName");
		String duration = request.getParameter("duration");
		String description = request.getParameter("description");

		if (DataValidator.isNull(courseName)) {
			request.setAttribute("courseName", PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		}
		if (DataValidator.isNull(description)) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}
		if (DataValidator.isNull(duration)) {
			request.setAttribute("duration", PropertyReader.getValue("error.require", "Duration"));
		}
		log.debug("CourseCtl Method validate Ended");
		return pass;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		CourseDTO dto = new CourseDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setCourseName(DataUtility.getString(request.getParameter("courseName")));
		dto.setDescription(DataUtility.getString(request.getParameter("description")));
		
		//System.out.println("popppp    "+request.getParameter("duration"));
		
		dto.setDuration(DataUtility.getString(request.getParameter("duration")));
		populate(dto, request);
		return dto;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("CourseCtl Method doGet Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		CourseModelInt model =ModelFactory.getInstance().getCourseModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			CourseDTO dto;
			try {
				dto= model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("UserCtl Method doGet Ended");
	}

   /* (non-Javadoc)
    * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    */
   @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("CourseCtl Method doPost Started");
		String op=DataUtility.getString(request.getParameter("operation"));
	    
		CourseModelInt model=ModelFactory.getInstance().getCourseModel();
	
		long id=DataUtility.getLong(request.getParameter("id"));
	
	    if(OP_SAVE.equalsIgnoreCase(op) ||OP_UPDATE.equalsIgnoreCase(op)){
		   
	    	CourseDTO dto=(CourseDTO)populateDTO(request);
		
	    	try{
			     if(id>0){
				  
			      model.update(dto);
				  ServletUtility.setDto(dto, request);
	              ServletUtility.setSuccessMessage("Data is successfully Updated",
	                    request);
			    }
			   else
			   {
				    long pk=model.add(dto);
				    ServletUtility.setDto(dto, request);
	                ServletUtility.setSuccessMessage("Data is successfully Added",request);
			   }
			
		   }
	       catch(ApplicationException e){
			log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
		  }
	      catch(DuplicateRecordException e){
			 ServletUtility.setDto(dto, request);
             ServletUtility.setErrorMessage("Course already exists",
                     request);	
		}
	 }
	else if(OP_DELETE.equalsIgnoreCase(op)){
		CourseDTO dto = (CourseDTO) populateDTO(request);
        try {
            model.delete(dto);
            ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request,
                    response);
            return;
        } catch (ApplicationException e) {
            log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }

    } 
	else if(OP_CANCEL.equalsIgnoreCase(op)) {

        ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
        return;
    }
	else if(OP_RESET.equalsIgnoreCase(op)) {

         ServletUtility.redirect(ORSView.COURSE_CTL, request, response);
         return;

     }    
	    
    ServletUtility.forward(getView(), request, response);

    log.debug("CourseCtl Method doPostEnded");
}

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		return ORSView.COURSE_VIEW;
	}

}
