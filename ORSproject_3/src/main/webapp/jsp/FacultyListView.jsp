<%@page import="in.co.rays.dto.FacultyDTO"%>
<%@page import="in.co.rays.ctl.FacultyListCtl"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Faculty List</title>
<!-- <meta name="viewport" content="width=device-width, initial-scale=1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
---- Include the above in your HEAD tag --------
 -->
<!-- <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
 -->
 
 <!-- Select All Library -->
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>
 

<style type="text/css" >

@import
	url(https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css)
	;

@import
	url(https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.4.3/css/mdb.min.css)
	;

body{
    background-image:  url('../img/photo.jpeg');
    background-position: center;
   background-size: cover;
   
}


.divider-text {
    position: relative;
    text-align: center;
    margin-top: 15px;
    margin-bottom: 15px;
}
.divider-text span {
    padding: 7px;
    font-size: 12px;
    position: relative;   
    z-index: 2;
}
.divider-text:after {
    content: "";
    position: absolute;
    width: 100%;
    border-bottom: 1px solid #ddd;
    top: 55%;
    left: 0;
    z-index: 1;
}

.btn-facebook {
    background-color: #405D9D;
    color: #fff;
}
.btn-twitter {
    background-color: #42AEEC;
    color: #fff;
}
</style>

</head>
<body>
<jsp:useBean id="dto" scope="request" class="in.co.rays.dto.FacultyDTO" />
<div>
<%@include file="Header.jsp" %>
</div>




<form action="<%=ORSView.FACULTY_LIST_CTL%>" method="post">

<div class="container-fluid" style="margin-bottom: 50px; margin-top:30px">
       
      
   
		
        
        <div align="center">    
         <H2 style="color: white">  <span class="fa fa-list-alt"></span><b > <u>Faculty List</u></b> </H2>
         <hr color="white">
       </div>
    

    <div class="text-center" >
       
			<%if(!ServletUtility.getSuccessMessage(request).equals("")){ %>
			<div class="alert alert-success alert-dismissible fade show">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request) %></font>
			</div>
			<%} %>

			<%if(!ServletUtility.getErrorMessage(request).equals("")){ %>
			<div class="alert alert-danger alert-dismissible fade show">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<font color="red"><%=ServletUtility.getErrorMessage(request) %></font>
			</div>

			<%} %>
			   </div>
			   
            <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">



<% List list = ServletUtility.getList(request);
List collegeList=(List)request.getAttribute("collegeList");
List courseList=(List)request.getAttribute("courseList");

System.out.println(list);
 if (list.size()==0){ %>
	
        <table align="center">
				<tr>
					<td>
					 
					  <button type="submit" name="operation" class=" form-control btn btn-warning" 
			     value="<%=FacultyListCtl.OP_BACK%>" style=" width: 150px; height: 47px; font-size: 16px; background-color: gray;">
                 <span style="margin-right: 7px;" class="	fa fa-folder-open"></span>  Back </button>
                 
					</td>
			
				</tr>
			</table>
			
			<%}else{ %>
	 
<br>
	 
	  
	
	  
	 <div class="row no-gutters">
	&emsp;
	 <!--  <label class="form-check-label" for="check2" style = "max-width:72px"> -->
   
    <strong style="color: white">First Name :~</strong>&nbsp;
    <div class="col-sm-2">		 
    <input placeholder="Enter First Name" style="background-color: white"  type="text"   name="firstName" class="border"  value="<%=ServletUtility.getParameter("firstName", request)%>">
    </div>
     &emsp;  
   
    <strong style="color: white">Course :~</strong>&nbsp;
    <div class="col-sm-2"><%=HTMLUtility.getList("course",String.valueOf(dto.getCourseId()),courseList) %></div>
	&emsp;
	<strong style="color: white">  Email :~</strong>&nbsp;
      <div class="col-sm-2">
	   <input placeholder="Enter EmailId" style="background-color: white" type="text"  name="email" class="border"  value="<%=ServletUtility.getParameter("email", request)%>">
 </div>
	 
	   
	  <div class="col-sm-2 pl-4" style="max-width: 140px">
	  <button type="submit" name="operation" class=" form-control btn btn-info" style="font-size: 13px"
				 value="<%=FacultyListCtl.OP_SEARCH%>">
             <span class="fa fa-search"></span> Search </button>
        </div>
        &nbsp;
      <div class="col-sm-2 pl-1" style="max-width: 100px">     
      <button type="submit" name="operation" class=" form-control btn btn-warning"  style="font-size: 13px"
			     value="<%=FacultyListCtl.OP_RESET%>" >
                 <span class="fa fa-refresh"></span>Reset</button>
      </div>
	 
	  <div class="col-sm-2"></div>
	 
	  </div>
	
	
   <hr color="white">
    
    
     <table width="100%">
				<tr>
				
					
	
					
	  <td align="right">
      <button type="submit" style="font-size: 13px" name="operation" value="<%=FacultyListCtl.OP_DELETE%>"
	  class="btn btn-danger"><span class="fa fa-trash"></span> 
	  <%=FacultyListCtl.OP_DELETE%> </button>
	  </td>	
	 </tr>		
	</table>
       
   
	  
     <div class="box-body table-responsive ">
     <table border="3" width="100%"  cellpadding=12px cellspacing=".6">
				<thead>
      <tr align="center" style="background:#ffaaff">
      <th class="text-center"><input type="checkbox" id="select_all" name="select"><strong>Select All</strong></th>
                   <th><strong>S.NO</strong></th>
					<th><strong>FirstName</strong></th>
					<th><strong>LastName</strong></th>
					<th><strong>Email Id</strong></th>
					<th><strong>Qualification</strong></th>
					<th><strong>Mobile No.</strong></th>
					<th><strong>Joining Date</strong></th>
					<th><strong>College Name</strong></th>
					<th><strong>Course Name</strong></th>
					<th><strong>Edit</strong></th>
      </tr>
      </thead>
     <%
	                int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;
                    int next=DataUtility.getInt(request.getAttribute("nextlist").toString());

                	
                    Iterator<FacultyDTO> it = list.iterator();
                    while (it.hasNext()) {
                    	
                        dto = it.next();
               %>
               <tbody>
				<%-- <tr   class="<%=((index)%2==0)?"table-info":"table-danger"%>"> --%>
				<tr align="center" style="background:white" >
					<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="<%=dto.getId()%>"></td>
					<td class=""><strong><%=index++%></strong></td>
					<td><strong><%=dto.getFirstName()%></strong></td>
					<td><strong><%=dto.getLastName()%></strong></td>
					<td><strong><%=dto.getEmailId()%></strong></td>
					<td><strong><%=dto.getQualification()%></strong></td>
					<td><strong><%=dto.getMobileNo()%></strong></td>
					<td><strong><%=DataUtility.getDateString(dto.getJoiningDate())%></strong></td>
					<td><strong><%=dto.getCollegeName()%></strong></td>
					<td><strong><%=dto.getCourseName()%></strong></td>
					
					<td style="size: 20%; text-align: center;">
					
					<a href="FacultyCtl?id=<%=dto.getId()%>">
					<span class="fa fa-edit"></span></a>
					
					</td></tr>
				</tbody>
				<%
                    }
                %>
			</table>
			
			</div>
			 
			 <table width="100%">
				<tr>
				
					 <td align="left">
  <button type="submit" name="operation" style="font-size: 13px"
	value="<%=FacultyListCtl.OP_PREVIOUS%>"<%=(pageNo == 1) ? "disabled" : ""%>   class="btn btn-primary">
	<span class="fa fa-chevron-left"></span> <%=FacultyListCtl.OP_PREVIOUS%> </button> 

			</td>
					<%-- <td>
				
      <button type="submit" style="font-size: 13px" name="operation" value="<%=FacultyListCtl.OP_DELETE%>"
	  class="btn btn-danger"><span class="fa fa-trash"></span> 
	  <%=FacultyListCtl.OP_DELETE%> </button>
			</td>	
			
				
							<td>
    <button type="submit" style="font-size: 13px" name="operation" value="<%=FacultyListCtl.OP_NEW%>"
	class="btn btn-primary"> <span class="fas fa-plus-square"></span> 
	<%=FacultyListCtl.OP_NEW%> </button>
			</td>
			 --%>
			 <td> <button type="submit" style="font-size: 13px" name="operation" value="<%=FacultyListCtl.OP_NEW%>"
	class="btn btn-primary"> <span class="fas fa-plus-square"></span> 
	<%=FacultyListCtl.OP_NEW%> </button></td>
			
					
 
                 <td align="right">
        <button type="submit" style="font-size: 13px" name="operation" value="<%=FacultyListCtl.OP_NEXT%>"<%=(list.size()<pageSize||next==0) ? "disabled" : ""%>  class="btn btn-primary" 
        ><%=FacultyListCtl.OP_NEXT%> <span class="fa fa-chevron-right"></span> 
        </button></div>

			</td>

			</table>
			 
			 	
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> 
			<input type="hidden" name="pageSize" value="<%=pageSize%>">
						
			<%} %>
</div></div></div>
 <br><br>     <br><br> 
</form>

</body>
<%@include file="Footer.jsp" %>
</html>