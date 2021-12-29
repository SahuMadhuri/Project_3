package in.co.rays.ctl;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.dto.BaseDTO;
import in.co.rays.dto.SubjectDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CourseModelInt;
import in.co.rays.model.ModelFactory;
import in.co.rays.model.SubjectModelInt;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * The Class SubjectCtl.
 * 
 * @author Madhuri
 * @version 1.0
 */
@WebServlet(name="SubjectCtl",urlPatterns={"/ctl/SubjectCtl"})
public class SubjectCtl extends BaseCtl{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The log. */
	private static Logger log=Logger.getLogger(SubjectCtl.class);
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	protected void  preload(HttpServletRequest request) {
		
		CourseModelInt courseModel=ModelFactory.getInstance().getCourseModel();
		
		try 
		{
			
			List l=courseModel.list();
			request.setAttribute("courseList",l);
		} 
		catch (ApplicationException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
	}

	
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		   log.debug("SubjectCtl Method Validate Started ");
    	   boolean pass=true;
    	   if (DataValidator.isNull(request.getParameter("subject"))){
    		  request.setAttribute("subject",PropertyReader.getValue("error.require","Subject Name"));
    	      pass=false;
    	    }
    	  if (DataValidator.isNull(request.getParameter("course"))){
    		  request.setAttribute("course",PropertyReader.getValue("error.require","Course Name"));
    	  pass=false;
    	  }
    	  if (DataValidator.isNull(request.getParameter("description"))){
    		  request.setAttribute("description",PropertyReader.getValue("error.require","Description"));
    	  pass=false;
    	  }
    	  log.debug("SubjectCtl Method validate Ended");
    	  
    	   return pass;
}
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
 	   log.debug("SubjectCtl Method populatebean Started");
		   
 	   SubjectDTO dto=new SubjectDTO();
	
 	   dto.setId(DataUtility.getLong(request.getParameter("id")));
	   dto.setSubjectName(DataUtility.getString(request.getParameter("subject")));
       dto.setCourseId(DataUtility.getLong(request.getParameter("course")));		   
       dto.setDescription(DataUtility.getString(request.getParameter("description")));
     
       log.debug("SubjectCtl Method populatebean Ended");
       populate(dto, request);
       return dto;
}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("SubjectCtl Method doGet Started");
	String op=DataUtility.getString(request.getParameter("operation"));
	SubjectModelInt model=ModelFactory.getInstance().getSubjectModel();
	long id=DataUtility.getLong(request.getParameter("id"));
	if(id>0||op!=null){
		SubjectDTO dto=new SubjectDTO();
		try{
			dto=model.findByPK(id);
			ServletUtility.setDto(dto, request);
			
		}catch(ApplicationException e){
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
	}
	ServletUtility.forward(getView(), request, response);
	log.debug("SubjectCtl Method doGet Ended");
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	    String op=DataUtility.getString(request.getParameter("operation"));
	    
	    SubjectModelInt model=ModelFactory.getInstance().getSubjectModel();
	    
	    long id =DataUtility.getLong(request.getParameter("id"));
	   
	    if(OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)){
	    
	    	SubjectDTO dto=(SubjectDTO)populateDTO(request) ;
	    	
	    	try {
	    		if(id>0){
	    		
					model.update(dto);
					ServletUtility.setSuccessMessage("data is successfully Updated", request);
				}else{
					long pk=model.add(dto);
					ServletUtility.setSuccessMessage("data is successfully saved", request);
				} 
	    		ServletUtility.setDto(dto, request);
	    		
	    	}catch (ApplicationException e) {
					// TODO Auto-generated catch block
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
				}catch(DuplicateRecordException e){
					ServletUtility.setDto(dto, request);
					ServletUtility.setErrorMessage("Subject already Exist", request);
				}
	    	}
	      else if(OP_DELETE.equalsIgnoreCase(op)){
	    		SubjectDTO dto=(SubjectDTO)populateDTO(request);
	    		try{
	    			model.delete(dto);
	    			ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
	    		return;
	    		}catch(ApplicationException e){
	    			log.error(e);
	    			ServletUtility.handleException(e, request, response);
	    			return;
	    		}
	    	}
	    	else if(OP_CANCEL.equalsIgnoreCase(op)){
	    		
	    		ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
	    	    return;
	    	}
	    	else if(OP_RESET.equalsIgnoreCase(op)){
	    		
	    		ServletUtility.redirect(ORSView.SUBJECT_CTL, request, response);
	    	    return;
	    	}
	    	ServletUtility.forward(getView(), request, response);
	    log.debug("SubjectCtl method doPost Ended");	
	    
		}

	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.SUBJECT_VIEW;
	}

}
