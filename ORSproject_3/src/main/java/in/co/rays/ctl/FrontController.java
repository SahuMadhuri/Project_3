package in.co.rays.ctl;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.util.ServletUtility;


// TODO: Auto-generated Javadoc
/**
 * The Class FrontController.
 * 
 * @author Madhuri
 * @version 1.0
 */
@WebFilter(urlPatterns={"/ctl/*","/doc/*"})
public class FrontController implements Filter {
	 
 	/* (non-Javadoc)
 	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
 	 */
 	public void init(FilterConfig conf) throws ServletException {
	    }
 

 /* (non-Javadoc)
  * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
  */

 	public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) 
		                                   throws IOException, ServletException {

            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;

            HttpSession session = request.getSession(true);
    
            String uri = request.getRequestURI();
	
            request.setAttribute("uri", uri);
		
	       if(session.getAttribute("user") == null) 
	        {
         
	    	   request.setAttribute("error","Your session has been expired,Please re-Login.");
        
	    	   ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
         
	    	   return;
           } 
	      else
	       {
    	     chain.doFilter(req, resp);
           }
 }


 /* (non-Javadoc)
  * @see javax.servlet.Filter#destroy()
  */
 public void destroy() {
		
	}

}