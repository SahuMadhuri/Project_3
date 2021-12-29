package in.co.rays.ctl;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

// TODO: Auto-generated Javadoc
/**
 * The Class MyProfileCtl.
 * 
 * @author Madhuri
 * @version 1.0
 */
@WebServlet(name="MyProfileCtl" ,urlPatterns={"/ctl/MyProfileCtl"})
public class MyProfileCtl extends BaseCtl {
	
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/** The log. */
 private static Logger log = Logger.getLogger(MyProfileCtl.class);
	
	 /** The Constant OP_CHANGE_MY_PASSWORD. */
 	public static final String OP_CHANGE_MY_PASSWORD = "ChangePassword";
	
	 /* (non-Javadoc)
 	 * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
 	 */
 	@Override
		protected void preload(HttpServletRequest request) {
         RoleModelInt roleModel=ModelFactory.getInstance().getRoleModel();
         try {
			List roleList=roleModel.list();
			request.setAttribute("roleList",roleList);
		} catch (ApplicationException e) {
			e.printStackTrace();
		
		}
         
	 
	 }
	 
	 /* (non-Javadoc)
 	 * @see in.co.rays.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
 	 */
 	@Override
	protected boolean validate(HttpServletRequest request) {
		    log.debug("MyProfileCtl Method validate Started");

	        boolean pass = true;

	        String op = DataUtility.getString(request.getParameter("operation"));
	        
	        System.out.println("In Validate :"+op);

	        if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op) || op == null) {

	            return pass;
	        }
            
	        String fname = request.getParameter("firstName");
			String lname = request.getParameter("lastName");
			String mobile = request.getParameter("mobileNumber");
			String email = request.getParameter("email");
			String gender = request.getParameter("gender");
			String dob = request.getParameter("dob");
			String role = request.getParameter("role");
	        
	        if (DataValidator.isNull(fname)) {
	            
	            request.setAttribute("firstName",
	                    PropertyReader.getValue("error.require", "First Name"));
	            pass = false;
	        }

	        if (DataValidator.isNull(lname)) {
	            request.setAttribute("lastName",
	                    PropertyReader.getValue("error.require", "Last Name"));
	            pass = false;
	        }
            
	        if (DataValidator.isNull(email)) {
				request.setAttribute("email", PropertyReader.getValue("error.require", "Email"));
				pass = false;
			}
	        else if (!DataValidator.isEmail(email)) {
				request.setAttribute("email", PropertyReader.getValue("error.email", "Email"));
			}
	        
	      
	       if (DataValidator.isNull(mobile)) {
	            request.setAttribute("mobileNo",
	                    PropertyReader.getValue("error.require", "MobileNo"));
	            pass = false;
	        }
	        else if(!DataValidator.isPhoneNo(mobile)){
				request.setAttribute("mobileNo","Please Enter Valid Mobile Number");
				pass=false;
			}
	        
	        if (DataValidator.isNull(gender)) {
	            request.setAttribute("gender",
	                    PropertyReader.getValue("error.require", "Gender"));
	            pass = false;
	        }
	       
            
	        if (DataValidator.isNull(role)) {
	            request.setAttribute("role",
	                    PropertyReader.getValue("error.require", "Role"));
	            pass = false;
	        }
	        
	        if (DataValidator.isNull(dob)) {
	            request.setAttribute("dob",
	                    PropertyReader.getValue("error.require", "Date Of Birth"));
	            pass = false;
	        }

	        log.debug("MyProfileCtl Method validate Ended");

	        return pass;

	}
	 
	 /* (non-Javadoc)
 	 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
 	 */
 	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		    log.debug("MyProfileCtl Method populatebean Started");

	        UserDTO dto = new UserDTO();

	        dto.setId(DataUtility.getLong(request.getParameter("id")));

	        dto.setLoginid(DataUtility.getString(request.getParameter("email")));

	        dto.setFirstname(DataUtility.getString(request.getParameter("firstName")));

	        dto.setLastname(DataUtility.getString(request.getParameter("lastName")));

	        dto.setMobileno(DataUtility.getString(request.getParameter("mobileNumber")));

	        dto.setGender(DataUtility.getString(request.getParameter("gender")));

	        dto.setDob(DataUtility.getDate(request.getParameter("dob")));
	        
	        dto.setRoleid(DataUtility.getLong(request.getParameter("role")));

            return dto;

	 }

	 
	 /* (non-Javadoc)
 	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 	 */
 	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	        HttpSession session = request.getSession(true);
	        log.debug("MyprofileCtl Method doGet Started");

	        UserDTO UserBean = (UserDTO) session.getAttribute("user");
	        long id = UserBean.getId();
	        String op = DataUtility.getString(request.getParameter("operation"));

	        // get model
	        UserModelInt model = ModelFactory.getInstance().getUserModel();
	        
	        if (id > 0 || op != null) {
	         
	            UserDTO dto;
	          
	            try {
	            
	            	dto = model.findByPK(id);
	                ServletUtility.setDto(dto, request);
	            
	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            } catch (DuplicateRecordException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        ServletUtility.forward(getView(), request, response);

	        log.debug("MyProfileCtl Method doGet Ended");

	 
	 }
	 
	 
	 /* (non-Javadoc)
 	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 	 */
 	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        
 		    System.out.println("In ProfileCtl");
	        HttpSession session = request.getSession(true);
	        log.debug("MyprofileCtl Method doPost Started");

	        UserDTO UserBean = (UserDTO) session.getAttribute("user");
	        long id = UserBean.getId();
	        String op = DataUtility.getString(request.getParameter("operation"));
	        
	        // get model
	        UserModelInt model = ModelFactory.getInstance().getUserModel();

	        if (OP_UPDATE.equalsIgnoreCase(op)) {
	        	
	            UserDTO dto = (UserDTO) populateDTO(request);
	          
	            try {
	                
	            	if(id > 0) {
	                    
	            		UserBean.setFirstname(dto.getFirstname());
	                    UserBean.setLastname(dto.getLastname());
	                    UserBean.setGender(dto.getGender());
	                    UserBean.setMobileno(dto.getMobileno());
	                    UserBean.setDob(dto.getDob());
	                    model.update(UserBean);

	                }
	                ServletUtility.setDto(dto, request);
	                ServletUtility.setSuccessMessage(
	                        "Profile has been updated Successfully. ", request);
	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            } catch (DuplicateRecordException e) {
	                ServletUtility.setDto(dto, request);
	                ServletUtility.setErrorMessage("Login id already exists",
	                        request);
	            }
	        } else if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)) {

	            ServletUtility.redirect(ORSView.CHANGE_PASSWORD_CTL, request,
	                    response);
	            return;

	        }

	        ServletUtility.forward(getView(), request, response);

	        log.debug("MyProfileCtl Method doPost Ended");

	 
	 }
	 

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.MY_PROFILE_VIEW;
	}
}
