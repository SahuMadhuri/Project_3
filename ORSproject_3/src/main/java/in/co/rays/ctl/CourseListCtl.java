package in.co.rays.ctl;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.dto.BaseDTO;
import in.co.rays.dto.CourseDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.CourseModelInt;
import in.co.rays.model.ModelFactory;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
@WebServlet(name="CourseListCtl",urlPatterns={"/ctl/CourseListCtl"})
public class CourseListCtl extends BaseCtl {

	
	/** The log. */
	private static Logger log = Logger.getLogger("CourseListCtl.class");

	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void preload(HttpServletRequest request) {
	  
		CourseModelInt model=ModelFactory.getInstance().getCourseModel();
		List courseList;
		try {
			courseList = model.list();
			request.setAttribute("courseList", courseList);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		CourseDTO dto = new CourseDTO();
          dto.setId(DataUtility.getLong(request.getParameter("course")));
          System.out.println(DataUtility.getLong(request.getParameter("course"))+"=ggg");
		return dto;

	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
		log.debug("Course ctl do get start");
		List list=null;
		List nextList=null;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		CourseDTO bean = (CourseDTO) populateDTO(request);
		
		System.out.println(bean.getId());
		CourseModelInt model =ModelFactory.getInstance().getCourseModel();
		try {
			list = model.search(bean, pageNo, pageSize);
			
			nextList = model.search(bean, pageNo + 1, pageSize);
			
	        request.setAttribute("nextlist", nextList.size());
				
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found", request);
			}
			
			ServletUtility.setDto(bean, request);
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;

		} catch (Exception e) {
			e.printStackTrace();
		}

		log.debug("Course ctl do get end");

	}
	
	
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
		log.debug("Course List do post start");
		List list=null;
		List nextList=null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
		CourseDTO bean = (CourseDTO) populateDTO(request);
		String op = DataUtility.getString(request.getParameter("operation"));
		String[] ids = request.getParameterValues("ids");
		CourseModelInt model = ModelFactory.getInstance().getCourseModel();
		try {
			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}
			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.COURSE_CTL, request, response);
				return;
			} else if (OP_RESET.equalsIgnoreCase(op)) {

				ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
				return;
			} else if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					CourseDTO deletedto = new CourseDTO();
					for (String id : ids) {
						deletedto.setId(DataUtility.getLong(id));
						model.delete(deletedto);
						ServletUtility.setErrorMessage("Data Deleted Successfully", request);
					}

				} else {
					ServletUtility.setErrorMessage("Select atleast one record", request);
				}
			}
			list = model.search(bean, pageNo, pageSize);
		System.out.println(	bean.getId()+"ddd");
			 nextList = model.search(bean, pageNo + 1, pageSize);
			// nextList=model.search(dto,pageNo+1,pageSize);
	            request.setAttribute("nextlist", nextList.size());
				
			if (list == null || list.size() == 0&&!OP_DELETE.equalsIgnoreCase(op)) {
				ServletUtility.setErrorMessage("No record found", request);
			}
			
			ServletUtility.setDto(bean, request);
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("Course List do post end");

	}

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.COURSE_LIST_VIEW;
	}
	
}
