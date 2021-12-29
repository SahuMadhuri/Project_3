package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.exception.ApplicationException;
import in.co.rays.model.MarksheetModelInt;
import in.co.rays.model.ModelFactory;
import in.co.rays.model.StudentModelInt;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;
import in.co.rays.dto.BaseDTO;
import in.co.rays.dto.MarksheetDTO;
import in.co.rays.dto.StudentDTO;

/**
 * Get Marksheet functionality Controller. Performs operation for Get Marksheet
 * 
 * @author Madhuri
 * @version 1.0
 */
@WebServlet(name = "GetMarksheetCtl", urlPatterns = "/ctl/GetMarksheetCtl")
public class GetMarksheetCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(GetMarksheetCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("GetMarksheetCTL Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", PropertyReader.getValue("error.require", "Roll Number"));
			pass = false;
		}

		log.debug("GetMarksheetCTL Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("GetMarksheetCtl Method populatebean Started");

		MarksheetDTO dto = new MarksheetDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setRollNo(DataUtility.getString(request.getParameter("rollNo")));

		dto.setName(DataUtility.getString(request.getParameter("name")));

		dto.setPhysics(DataUtility.getInt(request.getParameter("physics")));

		dto.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));

		dto.setMaths(DataUtility.getInt(request.getParameter("maths")));
		  populate(dto, request);
		log.debug("GetMarksheetCtl Method populatebean Ended");

		return dto;
	}

	/**
	 * View Logic
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		log.debug("do get method GetMarkseetCtl started");

		ServletUtility.forward(getView(), req, resp);

		log.debug("do get method GetMarkseetCtl started");

	}

	/**
	 * Submit Logic
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("GetMarksheetCtl Method doGet Started");
		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		MarksheetModelInt model = ModelFactory.getInstance().getMarksheetModel();

		MarksheetDTO dto = (MarksheetDTO) populateDTO(request);
		StudentModelInt sModel = ModelFactory.getInstance().getStudentModel();
		StudentDTO sDto = new StudentDTO();
		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_GO.equalsIgnoreCase(op)) {

			try {
				dto = model.findByRollNo(dto.getRollNo());

				if (dto != null) 
				{
					sDto = sModel.findByPK(dto.getStudentId());
					request.setAttribute("CollegeName", sDto.getCollegeName());
					ServletUtility.setDto(dto, request);
				} 
				else 
				{
					ServletUtility.setErrorMessage("RollNo Does Not exists", request);
				}
			} catch (ApplicationException e) {
				e.printStackTrace();
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} 
		else if (OP_RESET.equalsIgnoreCase(op)) 
		{
			ServletUtility.redirect(ORSView.GET_MARKSHEET_CTL, request, response);
			return;
		}
		else if (OP_BACK.equalsIgnoreCase(op)) 
		{
			ServletUtility.redirect(ORSView.GET_MARKSHEET_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("MarksheetCtl Method doGet Ended");
	}

	protected String getView() {

		return ORSView.GET_MARKSHEET_VIEW;
	}

}
