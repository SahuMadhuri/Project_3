package in.co.rays.ctl;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.dto.BaseDTO;
import in.co.rays.dto.SubjectDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.CourseModelInt;
import in.co.rays.model.ModelFactory;
import in.co.rays.model.SubjectModelInt;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;
// TODO: Auto-generated Javadoc

/**
 * The Class SubjectListCtl.
 * 
 * @author Madhuri
 * @version 1.0
 */
@WebServlet(name="SubjectListCtl",urlPatterns={"/ctl/SubjectListCtl"})
public class SubjectListCtl extends BaseCtl {
	
	/** The log. */
	private static Logger log = Logger.getLogger(SubjectListCtl.class);

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void preload(HttpServletRequest request) {

		CourseModelInt model=ModelFactory.getInstance().getCourseModel();
		 try {
			List courseList= model.list();
			request.setAttribute("courseList",courseList);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	
	}
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		log.debug("subject ctl populate bean start");
		SubjectDTO dto = new SubjectDTO();

		dto.setCourseId(DataUtility.getLong(request.getParameter("course")));
		dto.setSubjectName(DataUtility.getString(request.getParameter("subjectName")));
		log.debug("subject ctl populate bean end");
		return dto;

	}
	 
 	/**
 	 * Display Logics inside this method.
 	 *
 	 * @param request the request
 	 * @param response the response
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 * @throws ServletException the servlet exception
 	 */

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		log.debug("subject ctl do get start");
		List list;
		List nextList=null;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		SubjectDTO dto = (SubjectDTO) populateDTO(request);
		SubjectModelInt model = ModelFactory.getInstance().getSubjectModel();
		try {
			list = model.search(dto, pageNo, pageSize);
			nextList=model.search(dto,pageNo+1,pageSize);
            request.setAttribute("nextlist", nextList.size());
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found", request);
			}
			
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
		log.debug("subject ctl do get end");
	}
	
	/**
	 * Submit logic inside it.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		log.debug("subject ctl dopost start");
		List list=null;
		List nextList=null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
		String[] ids = request.getParameterValues("ids");
		
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("helkjkjlkjl"+ids+"jjj"+op);
		SubjectDTO bean = (SubjectDTO) populateDTO(request);
		SubjectModelInt model = ModelFactory.getInstance().getSubjectModel();
        try{
        	if(OP_SEARCH.equalsIgnoreCase(op)||"Next".equalsIgnoreCase(op)||"Previous".equalsIgnoreCase(op)){
        		if(OP_SEARCH.equalsIgnoreCase(op)){
        			pageNo=1;
        		}else if("Next".equalsIgnoreCase(op)){
        			pageNo++;
        		}else if("Previous".equalsIgnoreCase(op)){
        			pageNo--;
        		}
        	}
        	else if (OP_RESET.equalsIgnoreCase(op)) {

    			ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
    			return;
    		}else if(OP_NEW.equalsIgnoreCase(op)){
        		ServletUtility.redirect(ORSView.SUBJECT_CTL, request, response);
        		return;
        	}else if(OP_DELETE.equalsIgnoreCase(op)){
        		pageNo=1;
        		
        		if(ids !=null&& ids.length>0){
        			System.out.println("kjkjk____");
        			SubjectDTO deletedto=new SubjectDTO();
        			for(String id:ids){
        				deletedto.setId(DataUtility.getLong(id));
        				model.delete(deletedto);
        				ServletUtility.setErrorMessage("Data Deleted Successfully", request);
        			}
        		}else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
        	}
        	 if(OP_BACK.equalsIgnoreCase(op)){
        		 System.out.println("jijijij");
        		ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
        		return;
        	}
        	list = model.search(bean, pageNo, pageSize);
        	nextList = model.search(bean, pageNo+1, pageSize);
			
        	//nextList=model.search(dto,pageNo+1,pageSize);
            request.setAttribute("nextlist", nextList.size());
			ServletUtility.setList(list, request);
			
			if (list == null || list.size() == 0&&!OP_DELETE.equalsIgnoreCase(op)) {
				System.out.println("last endpopopop"+list);
				ServletUtility.setErrorMessage("No record found ", request);
			}
			
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		}
        catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		log.debug("subject ctl do post end");
	}

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.SUBJECT_LIST_VIEW;
	}


	
	
}
