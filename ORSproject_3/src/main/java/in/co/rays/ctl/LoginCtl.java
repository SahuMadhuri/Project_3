package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.rays.dto.BaseDTO;
import in.co.rays.dto.RoleDTO;
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
@WebServlet(urlPatterns={"/LoginCtl"},name="LoginCtl")
public class LoginCtl extends BaseCtl {
	/** The Constant serialVersionUID. */
 	private static final long serialVersionUID = 1L;
	 
 	/** The Constant OP_REGISTER. */
 	public static final String OP_REGISTER = "Register";
	 
 	/** The Constant OP_SIGN_IN. */
 	public static final String OP_SIGN_IN = "SignIn";
	 
 	/** The Constant OP_SIGN_UP. */
 	public static final String OP_SIGN_UP = "SignUp";
	 
 	/** The Constant OP_LOG_OUT. */
 	public static final String OP_LOG_OUT = "logout";
	 
 	/** The log. */
 	private static Logger log = Logger.getLogger(LoginCtl.class);
	 
 	/* (non-Javadoc)
 	 * @see in.co.rays.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
 	 */
 	@Override
	 protected boolean validate(HttpServletRequest request){
		 log.debug("LoginCtl Method validate Started");
		 
		 boolean pass=true;
		 
		 String op=DataUtility.getString(request.getParameter("operation"));
		 
		 if(OP_SIGN_UP.equals(op)||OP_LOG_OUT.equals(op))
		 {
			 return pass;
		 }
	
		 if(DataValidator.isNull(DataUtility.getString(request.getParameter("login"))))
		 {
			 request.setAttribute("login", PropertyReader.getValue("error.require","Login ID"));
			 pass=false;
		 }
		 else if (!DataValidator.isEmail(DataUtility.getString(request.getParameter("login")))) 
		 {
	            request.setAttribute("login",PropertyReader.getValue("error.email", "Login "));
	            pass = false;
	     }

		 if(DataValidator.isNull(DataUtility.getString(request.getParameter("password"))))
		 {
			 request.setAttribute("password", PropertyReader.getValue("error.require","Password"));
			 pass=false;
		 }
		 
		 log.debug("LoginCtl Method validate Ended");
		 
		 return pass;
	 }
	 
 	/* (non-Javadoc)
 	 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
 	 */
 	@Override
	    protected BaseDTO populateDTO(HttpServletRequest request) {
	 
	        log.debug("LoginCtl Method populateDTO Started");
	 
	        UserDTO dto = new UserDTO();
	 
	        dto.setId(DataUtility.getLong(request.getParameter("id")));
	        dto.setLoginid(DataUtility.getString(request.getParameter("login")));
	        dto.setPassword(DataUtility.getString(request.getParameter("password")));
	 
	        log.debug("LoginCtl Method populateDTO Ended");
	        populate(dto, request);
	        return dto;
	    }
	   
   	/* (non-Javadoc)
   	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   	 */
   	protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	       
   		    HttpSession session = request.getSession(true);
	        
   		    log.debug(" Method doGet Started");

	        String op = DataUtility.getString(request.getParameter("operation"));

	        // get model
	        UserModelInt model =ModelFactory.getInstance().getUserModel();
	        RoleModelInt role =ModelFactory.getInstance().getRoleModel() ;
	        
	         if(OP_LOG_OUT.equals(op)) {

	            session = request.getSession();
	            
	            session.invalidate();
	            
	            ServletUtility.setSuccessMessage("Logout Successfully", request);
	        
	            ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);

	            return;

	        }
	         
            ServletUtility.forward(getView(), request, response);

	        log.debug("UserCtl Method doPost Ended");

	    }

	 /**
 	 * Do post.
 	 *
 	 * @param request the request
 	 * @param response the response
 	 * @throws ServletException the servlet exception
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
 	 *      response)
 	 */
  protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	    
	  	HttpSession session=request.getSession(true);
	  
	  	log.debug(" Method doGet Started");
	  
	  	String op=DataUtility.getString(request.getParameter("operation"));
	   
	    UserModelInt model=ModelFactory.getInstance().getUserModel();
	    
	    RoleModelInt role=ModelFactory.getInstance().getRoleModel();
	    
	    long id = DataUtility.getLong(request.getParameter("id"));
	    
	    if (OP_SIGN_IN.equalsIgnoreCase(op)) {
	    	 
            UserDTO dto = (UserDTO) populateDTO(request);
 
            try {
                  dto = model.authenticate(dto.getLoginid(), dto.getPassword());
 
                  if (dto != null) {
                    
                	session.setAttribute("user", dto);
                    
                	long rollId = dto.getRoleid();
                	System.out.println("rollid 8888888: "+rollId);
 
                    RoleDTO roleDTO = role.findByPK(rollId);
 
                    if (roleDTO != null) {
                    	System.out.println("in role Dto---------");
                        session.setAttribute("role", roleDTO.getName());
                    }
 
                    String uri = request.getParameter("uri");
					
                    if (uri == null || "null".equalsIgnoreCase(uri)) 
                    {
						ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
						return;
					} 
                    else 
                    {
						ServletUtility.redirect(uri, request, response);
						return;
					}

			   }
               else
                  {
                    dto = (UserDTO) populateDTO(request);
                    ServletUtility.setDto(dto, request);
                    ServletUtility.setErrorMessage("Invalid LoginId And Password", request);
                  }

            } 
            catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }

	    }
	    else if(OP_SIGN_UP.equalsIgnoreCase(op))
	      {
	    	ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
	    	return;
	     }
	    else {
	    	 
	    	if (id > 0 || op != null) {
	                UserDTO userDTO;
	                try {
	                    userDTO = model.findByPK(id);
	                    ServletUtility.setDto(userDTO, request);
	                } catch (ApplicationException e) {
	                    log.error(e);
	                    ServletUtility.handleException(e, request, response);
	                    return;
	                } catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
		}
	    
	    ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
	    log.debug("UserCtl Method doGet Ended");
	 }
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		return ORSView.LOGIN_VIEW;
	}
}
