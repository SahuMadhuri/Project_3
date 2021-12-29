package in.co.rays.ctl;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.impl.SessionImpl;

import in.co.rays.dto.UserDTO;
import in.co.rays.util.HibDataSource;
//import in.co.rays.util.JDBCDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;



// TODO: Auto-generated Javadoc
/**
 * The Class JasperCtl.
 * 
 * @author Madhuri
 * @version 1.0
 */
//@WebServlet({"/ctl/JasperCtl"})
@WebServlet(name= "JasperCtl" ,urlPatterns={"/ctl/JasperCtl"})
public class JasperCtl extends BaseCtl {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new jasper ctl.
	 */
	public JasperCtl() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("DoGet Jasper Report");
		try {                                                              
			
			JasperReport jasperReport = JasperCompileManager.compileReport("F:\\Project_3 new\\ORSproject_3\\src\\main\\webapp\\report\\Blank_A4_2_new.jrxml");
			
			HttpSession session = request.getSession(true);
			UserDTO dto = (UserDTO) session.getAttribute("user");
			dto.getFirstname();
			dto.getLastname();
			
			Map<String, Object> map = new HashMap();
			map.put("user", dto.getFirstname() + " " + dto.getLastname());
			Connection conn = null;
			
			ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.bundle.system");
			
			String Database = rb.getString("DATABASE");
			
			if ("Hibernate".equalsIgnoreCase(Database)) {
				conn = ((SessionImpl) HibDataSource.getSession()).connection();
			}

//			if ("JDBC".equalsIgnoreCase(Database)) {
//				conn = JDBCDataSource.getConnection();
//			}

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);
			
			byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
			
			response.setContentType("application/pdf");
			response.getOutputStream().write(pdf);
			response.getOutputStream().flush();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	protected String getView() {
		return null;
	}
}

