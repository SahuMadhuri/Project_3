package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.util.ServletUtility;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
@WebServlet(name = "WelcomeCtl", urlPatterns = { "/WelcomeCtl" })
public class WelcomeCtl extends BaseCtl {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("welcome ctl doget started");
		ServletUtility.forward(getView(), request, response);
		System.out.println("welcome ctl doget end");
	}
	
	@Override
	protected String getView() {
		System.out.println("welcome ctl doview started");
		return ORSView.WELCOME_VIEW;
	}

}
