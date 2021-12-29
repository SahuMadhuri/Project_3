package in.co.rays.ctl;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.dto.BaseDTO;
import in.co.rays.dto.CollegeDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CollegeModelInt;
import in.co.rays.model.ModelFactory;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * The Class CollegeCtl.
 * 
 * @author Madhuri
 * @version 1.0
 */
@WebServlet(name="CollegeCtl",urlPatterns={"/ctl/CollegeCtl"})
public class CollegeCtl extends BaseCtl {
	
	/** The log. */
	private static Logger log = Logger.getLogger(CollegeCtl.class);

/* (non-Javadoc)
 * @see in.co.rays.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
 */
@Override
protected boolean validate(HttpServletRequest request) {
	log.debug("CollegeCtl Method validate Started");
	boolean pass=true;
    
	String collegeName= request.getParameter("collegeName");
	String address= request.getParameter("address");
	String state= request.getParameter("state");
	String city= request.getParameter("city");
	String mobileNo= request.getParameter("mobileNo");
	
    if (DataValidator.isNull(collegeName)) {
        request.setAttribute("collegeName",PropertyReader.getValue("error.require", "College Name"));
        pass = false;
    }

    if (DataValidator.isNull(address)) {
        request.setAttribute("address",PropertyReader.getValue("error.require", "Address"));
        pass = false;
    }

    if (DataValidator.isNull(state)) {
        request.setAttribute("state",PropertyReader.getValue("error.require", "State"));
        pass = false;
    }
    if (DataValidator.isNull(city)) {
        request.setAttribute("city",PropertyReader.getValue("error.require", "City"));
        pass = false;
    }
    if (DataValidator.isNull(mobileNo)) {
        request.setAttribute("mobileNo",PropertyReader.getValue("error.require", "Phone No"));
        pass = false;
    }

    log.debug("CollegeCtl Method validate Ended");

    return pass;
}

/* (non-Javadoc)
 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
 */
@Override
protected BaseDTO populateDTO(HttpServletRequest request) {
	
	CollegeDTO dto=new CollegeDTO();
	
	dto.setId(DataUtility.getLong(request.getParameter("id")));
	dto.setName(DataUtility.getString(request.getParameter("collegeName")));
	dto.setAddress(DataUtility.getString(request.getParameter("address")));
	dto.setCity(DataUtility.getString(request.getParameter("city")));
	dto.setState(DataUtility.getString(request.getParameter("state")));
    dto.setPhoneNo(DataUtility.getString(request.getParameter("mobileNo")));
    populate(dto, request);
	return dto;
}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String op = DataUtility.getString(request.getParameter("operation"));
		
		CollegeModelInt model=ModelFactory.getInstance().getCollegeModel();
	    
		long id = DataUtility.getLong(request.getParameter("id"));
	    
		if (id > 0 || op != null) {
	            CollegeDTO dto;
	            try {
	                dto = model.findByPK(id);
	                ServletUtility.setDto(dto, request);
	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }
	        }

	        ServletUtility.forward(getView(), request, response);
	        }
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
        log.debug("CollegeCtl Method doPost Started");

        String op = DataUtility.getString(request.getParameter("operation"));

        // get model
        CollegeModelInt model = ModelFactory.getInstance().getCollegeModel();

        long id = DataUtility.getLong(request.getParameter("id"));

        if (OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)) {

            CollegeDTO dto = (CollegeDTO) populateDTO(request);

            try {
                if (id > 0) {
                    
                	model.update(dto);
                    ServletUtility.setDto(dto, request);
                    ServletUtility.setSuccessMessage("Data Updated Successfully ",request);
                } 
                else {
                   
                	model.add(dto);
                    ServletUtility.setDto(dto, request);
                    ServletUtility.setSuccessMessage("Data is Successfully Saved",request);
                }
                

            } catch (ApplicationException e) {
                e.printStackTrace();
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setDto(dto, request);
                ServletUtility.setErrorMessage("College Name already exists",request);
            }

        } else if (OP_DELETE.equalsIgnoreCase(op)) {

            CollegeDTO dto = (CollegeDTO) populateDTO(request);
            try {
                model.delete(dto);
                ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request,response);
                return;

            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }

        } 
        else if (OP_CANCEL.equalsIgnoreCase(op)) {

            ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request, response);
            return;

        }
        else if(OP_RESET.equalsIgnoreCase(op)){
        	ServletUtility.redirect(ORSView.COLLEGE_CTL, request, response);
        	return;
        }

        ServletUtility.forward(getView(), request, response);

        log.debug("CollegeCtl Method doGet Ended");
    }


	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.COLLEGE_VIEW;
	}
}
