package in.co.rays.ctl;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.dto.BaseDTO;
import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.model.ModelFactory;
import in.co.rays.model.UserModelInt;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * The Class ForgetPasswordCtl.
 * 
 * @author Madhuri
 * @version 1.0
 */
@WebServlet(name="ForgetPasswordCtl" ,urlPatterns={"/ForgetPasswordCtl"})
public class ForgetPasswordCtl extends BaseCtl {
	
	/** The log. */
	private static Logger log = Logger.getLogger(ForgetPasswordCtl.class);
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		 log.debug("ForgetPasswordCtl Method validate Started");

	        boolean pass = true;

	        String login = request.getParameter("login");

	        if (DataValidator.isNull(login)) {
	            request.setAttribute("login",
	                    PropertyReader.getValue("error.require", "Email Id"));
	            pass = false;
	        } else if (!DataValidator.isEmail(login)) {
	            request.setAttribute("login",
	                    PropertyReader.getValue("error.email", "Login "));
	            pass = false;
	        }
	        log.debug("ForgetPasswordCtl Method validate Ended");

	        return pass;

	}
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
        log.debug("ForgetPasswordCtl Method populatebean Started");

        UserDTO dto = new UserDTO();

        dto.setLoginid(DataUtility.getString(request.getParameter("login")));
        populate(dto, request);
        log.debug("ForgetPasswordCtl Method populatebean Ended");

        return dto;

	
	}
	
	
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		log.debug("ForgetPasswordCtl Method doGet Started");

        ServletUtility.forward(getView(), request, response);

	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("ForgetPasswordCtl Method doPost Started");

        String op = DataUtility.getString(request.getParameter("operation"));

        UserDTO dto = (UserDTO) populateDTO(request);

        // get model
        UserModelInt model=ModelFactory.getInstance().getUserModel();

        if (OP_GO.equalsIgnoreCase(op)) {

            try {
                model.forgetPassword(dto.getLoginid());
                ServletUtility.setSuccessMessage(
                        "Password has been sent to your email id.", request);
            } catch (RecordNotFoundException e) {
                ServletUtility.setErrorMessage(e.getMessage(), request);
                log.error(e);
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            ServletUtility.setDto(dto, request);
            ServletUtility.forward(getView(), request, response);
        }
        else if(OP_RESET.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.FORGET_PASSWORD_CTL, request, response);
			return;
		}

        log.debug("ForgetPasswordCtl Method doPost Ended");

	
	}

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.FORGET_PASSWORD_VIEW;
	}
	
	
}
