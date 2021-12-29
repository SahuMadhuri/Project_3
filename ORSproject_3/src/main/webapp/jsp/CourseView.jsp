<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.ctl.CourseCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course View</title>
<!-- font-awesome library -->
<style type="text/css">
@import url(https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css);

@import url(https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.3/css/mdb.min.css);

body {
	background-image:url('/ORSproject_3/img/abc.jpg');
	background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
}
.darken-grey-text {
    color: #2E2E2E;
}
.danger-text {
    color: #ff3547; }
.default-text {
    color: #fff; 
}
.info-text {
    color: #33b5e5; 
}
.form-white .md-form label {
  color: #fff; 
}
.card{
  background-color:#3751a1!important;
  }

.paddingclass{
padding-top:10px;
}

</style>
<!-- Bootstrap library -->

<style type="text/css">
.setForm{
padding-top: 5%;
padding-left: 25%;
width: 130%
}
.button{
border-radius:10px;padding:10px;color:white;font-size:20px;background-color:#00cc88
}
.textfield{
border: 1px solid #8080803b;height: 38px; padding-left: 6px;
}
</style>
</head>

<body >
<jsp:useBean id="dto" scope="request" class="in.co.rays.dto.CourseDTO" />
<div>
<%@include file="Header.jsp" %>
</div>
<form action="<%=ORSView.COURSE_CTL%>" method="post">



    
    
    <main>
        
        <!--MDB Forms-->
        <div class="container mt-3">

            
            <!-- Grid row -->
            <div class="row">
            <div class="col-md-4"></div>
                <!-- Grid column -->
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body">
                                       <%long id=DataUtility.getLong(request.getParameter("id")); %>
                                                       <h3 class="text-center default-text py-3"><u><%= (id==0)? "Add Course": "Update Course" %></u></h3>
                                               <hr color="white"> 
                            <!--Body-->
                         
    <%if(!ServletUtility.getSuccessMessage(request).equals("")){ %>
    <div class="alert alert-success alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <font color="green"><%=ServletUtility.getSuccessMessage(request) %></font>
    </div>
    <%} %>                            
	
	<%if(!ServletUtility.getErrorMessage(request).equals("")){ %>
    <div class="alert alert-danger alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <font color="red"><%=ServletUtility.getErrorMessage(request) %></font>
    </div>
    <%} %>                            
		
            <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
                         
                         
                           <h6  style="color: #fff " >Course Name<font color="red">*</font></h6> 
                           
                            <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space;"><i  class="fa fa-book"></i></span>
                                 </div>
                        <input type="text" placeholder="Enter Course Name" class="form-control border" style="background-color:white;" name="courseName" value="<%=DataUtility.getStringData(dto.getCourseName())%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("courseName",request) %> </font>
                    
                        
    
    
    
                           <h6 class=" paddingclass" style="color: #fff " >Description<font color="red">*</font></h6>
                         
                            <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space;"><i  class="fa fa-book"></i></span>
                                 </div>
                        <input type="text" placeholder="Enter description" class="form-control border" style="background-color:white;" name="description" value="<%=DataUtility.getStringData(dto.getDescription())%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("description",request) %> </font>
                    
                            
                            
                            
                            
                            <h6 class=" paddingclass" style="color: #fff " >Duration<font color="red">*</font></h6>
                                
                       <%HashMap<String,String> map = new HashMap<String,String>();
							map.put("1 year", "1 year");
							map.put("2 year", "2 year");
							map.put("3 year", "3 year");
							map.put("4 year", "4 year");
							map.put("5 year", "5 year");
							String HtmlList = HTMLUtility.getList("duration", dto.getDuration(), map);
					   %>           
				            
				            <div class="input-group">
                             <div class="input-group-prepend">
                             <span class="input-group-text"><i style="width: 17px" class="fa fa-clock"></i></span>
                                      </div>
                           	<%=HtmlList%>
                                      </div>
								<font color="red"> <%=ServletUtility.getErrorMessage("duration", request) %></font>
                   	</div>
    
    
    
                            <%if (id>0){ %>
                         <div class="text-center paddingclass">
							
								 <button type="submit" class="btn btn-success" name="operation"
									style="font-size: 13px"	value="<%=CourseCtl.OP_UPDATE%>">
										<span class="fa fa-check-square"></span> Update
									</button>
									 
									
							 <button type="submit" class="btn btn-warning" name="operation"
									style="font-size: 13px"	value="<%=CourseCtl.OP_CANCEL%>">
										<span class="fa fa-refresh"></span> Cancel
									</button>
							
							</div>
                          
                          
                          <%}else{ %> 
                           <div class="text-center paddingclass">
							
								 <button type="submit" class="btn btn-success" name="operation"
									style="font-size: 14px"	value="<%=CourseCtl.OP_SAVE%>">
										<span class="fa fa-check-square"></span> Save
									</button>
									 
									
							 <button type="submit" class="btn btn-warning" name="operation"
									style="font-size: 13px"	value="<%=CourseCtl.OP_RESET%>">
										<span class="fa fa-refresh"></span> Reset
									</button>
							
							</div>
                          
                             <%} %>
                        </div>
                    </div>
                </div>

<div class="col-md-4"></div>
</div>               
</div>
</form>
<br><br>
</body>
<%@include file="Footer.jsp" %>
</html>
