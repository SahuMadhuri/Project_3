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
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
@WebServlet(name = "UserCtl", urlPatterns = { "/ctl/UserCtl" })
public class UserCtl extends BaseCtl {

	/** The log. */
	private static Logger log = Logger.getLogger(UserCtl.class);

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	protected void preload(HttpServletRequest request) {
		RoleModelInt roleModel = ModelFactory.getInstance().getRoleModel();
		try 
		{
			List list = roleModel.list();
			request.setAttribute("roleList", list);
		} 
		catch (ApplicationException e) 
		{
			e.printStackTrace();
			log.error(e);
		}
	}

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	protected boolean validate(HttpServletRequest request) {
		
		log.debug("UserCtl Method validate Started");
		
		boolean pass = true;
		
		String fname = request.getParameter("firstName");
		String lname = request.getParameter("lastName");
		String mobile = request.getParameter("mobileNumber");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		String role = request.getParameter("role");
		if (DataValidator.isNull(fname)) {
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		}else if(!DataValidator.isName(fname)){
			request.setAttribute("firstName", "");
            pass=false;
		}
		if (DataValidator.isNull(lname)) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		}else if(!DataValidator.isName(lname)){
			request.setAttribute("firstName", "Please Enter Proper Name");
			pass=false;
		}
		if (DataValidator.isNull(mobile)) {
			request.setAttribute("mobileNumber", PropertyReader.getValue("error.require", "Mobile Number"));
			pass = false;
		}else if(!DataValidator.isPhoneNo(mobile)){
			request.setAttribute("mobileNumber","Please Enter Valid Mobile Number");
			pass=false;
			
		}
		if (DataValidator.isNull(email)) {
			request.setAttribute("email", PropertyReader.getValue("error.require", "Email"));
			pass = false;
		} else if (!DataValidator.isEmail(email)) {
			request.setAttribute("email", PropertyReader.getValue("error.email", "Email"));
		}
		
        long id = DataUtility.getLong(request.getParameter("id"));
		
		if(id>0){
			/*System.out.println("if------------------>");*/
		}
		else {
		
		if (DataValidator.isNull(password)) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			pass = false;
		}else if (!DataValidator.isPasswordLength(password)) {
			request.setAttribute("password", "Password should contain 8 character");
			pass = false;
		}else if (!DataValidator.isPassword(password)) {
			request.setAttribute("password", "Password Contain alphabets,number and special char. ");
			pass = false;
		}
		if (DataValidator.isNull(confirmPassword)) {
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.require", "ConfirmPassword"));
			pass = false;
		}
	   }	
		
	
		if (DataValidator.isNull(gender)) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date of Birth"));
			pass = false;
		}/*else if (DataValidator.isValidAge(dob)) {
			request.setAttribute("dob", "Age Must Be Above 18");
			pass = false;
		}*/
		if (DataValidator.isNull(role)) {
			request.setAttribute("role", PropertyReader.getValue("error.require", "Role"));
			pass = false;
		}

		if (!password.equals(confirmPassword) && !"".equals(confirmPassword)) {
			ServletUtility.setErrorMessage("Confirm  Password  should not be matched.", request);

			pass = false;
		}
		log.debug("UserRegistrationCtl Method validate Ended");

		return pass;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		
		UserDTO dto = new UserDTO();
		
		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setFirstname(DataUtility.getString(request.getParameter("firstName")));

		dto.setLastname(DataUtility.getString(request.getParameter("lastName")));

		dto.setMobileno(DataUtility.getString(request.getParameter("mobileNumber")));

		dto.setLoginid(DataUtility.getString(request.getParameter("email")));

		dto.setPassword(DataUtility.getString(request.getParameter("password")));

		dto.setConfirmpassword(DataUtility.getString(request.getParameter("confirmPassword")));

		dto.setGender(DataUtility.getString(request.getParameter("gender")));

		dto.setDob(DataUtility.getDate(request.getParameter("dob")));

		dto.setRoleid(DataUtility.getLong(request.getParameter("role")));
		  populate(dto, request);
		return dto;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		UserModelInt model = ModelFactory.getInstance().getUserModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		if (id>0 || op!= null) {
			
			UserDTO dto;
			
			try {
				dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		UserModelInt model=ModelFactory.getInstance().getUserModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		if(OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)){
			
			UserDTO dto=(UserDTO)populateDTO(request);
			
			if(dto.getId()>0){
				
				try {
					
					model.update(dto);
					ServletUtility.setSuccessMessage("Data is Successfully Updated", request);
					ServletUtility.setDto(dto, request);
					ServletUtility.forward(getView(), request, response);
					
				} catch (ApplicationException e) {
					e.printStackTrace();
					ServletUtility.handleException(e, request, response);
					return;

				} catch (DuplicateRecordException e) {
			           ServletUtility.setDto(dto, request);
		                ServletUtility.setErrorMessage("Login id already exists",request);
					e.printStackTrace();
				}
			}
			
			try {
				
				model.add(dto);
				ServletUtility.setSuccessMessage("Data is Successfully Saved", request);
				ServletUtility.setDto(dto, request);
				ServletUtility.forward(getView(), request, response);
				
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
		           ServletUtility.setDto(dto, request);
	                ServletUtility.setErrorMessage("Login id already exists",
	                        request);
	       }
		}
		else if(OP_RESET.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.USER_CTL, request, response);
			return;
		}
		else if (OP_DELETE.equalsIgnoreCase(op)) {

            UserDTO dto = (UserDTO)populateDTO(request);
            try {
                try {
					model.delete(dto);
				} catch (DuplicateRecordException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                ServletUtility.setSuccessMessage("Data Deleted Successfilly ", request);
                ServletUtility.redirect(ORSView.USER_LIST_CTL, request,
                        response);
                return;
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }
		} 
		else if (OP_CANCEL.equalsIgnoreCase(op)) {

            ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
            return;
        }
        
		
		
		//ServletUtility.forward(getView(), request, response);

        log.debug("UserCtl Method doPostEnded");


		
	}

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.USER_VIEW;
	}

}
