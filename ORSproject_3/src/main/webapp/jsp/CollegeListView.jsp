<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.dto.*"%>
<%@page import="in.co.rays.ctl.CollegeListCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>College List</title>
<!-- Select All Library -->
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>	

<!-- font-awesome library -->
<style type="text/css">
@import url(https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css);
@import url(https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.4.3/css/mdb.min.css);

body{
    background-image:  url('../img/photo.jpeg');
    background-position: center;
   background-size: cover;
   
}

.darken-grey-text {
    color: #2E2E2E;
}
.danger-text {
    color: #ff3547; }
.default-text {
    color: #2BBBAD; 
}
.info-text {
    color: #33b5e5; 
}
.paddingclass{
padding-top: 10px;
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
border-radius:5px;width:100px; padding:5px;color:white;font-size:17px;background-color:#00cc88
}
.fonta{
font-size: 20px;
}
</style>
</head>
<body >
<jsp:useBean id="dto" scope="request" class="in.co.rays.dto.CollegeDTO" />
<div>
<%@include file="Header.jsp" %>
</div>
<form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="post">

<div class="container-fluid" style="margin-bottom: 50px; margin-top:30px">
       
      	
        
    
    
        
        <div align="center">    
         <H2 style="color: white">  <span class="fa fa-list-alt"></span><b > <u>College List</u></b> </H2>
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
			
			<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
			
			<input type="hidden" name="modifiedBy"
				value="<%=dto.getModifiedBy()%>"> 
				
				<input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
			
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">




  
	  	<%
	
	
	List list = ServletUtility.getList(request);
	System.out.println(list);
        if (list.size()==0){ %>
			 <table align="center">
				<tr>
					<td>
					 
					  <button type="submit" name="operation" class=" form-control btn btn-warning" 
			     value="<%=CollegeListCtl.OP_BACK%>" style=" width: 150px; height: 47px; font-size: 16px; background-color: gray;">
                 <span style="margin-right: 7px;" class="	fa fa-folder-open-o"></span>  Back </button>
                 
					</td>
			
				</tr>
			</table>
			
			<%}else{ %>
			
		 
         
               <%--   <br>
                 <div>
                 <table width="80%"  align="center">
                 <tr>
	<%List listSize=ServletUtility.getList(request); %>
	    <div class="container-fluid text-center">
          <!--  <div class="form-inline" > -->
          <div class="form-inline">
          
           &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
			 
          
         <label style="color: white" >City :</label> 
           	
				 
				 				<div class="input-group">
				
					
					<div class="input-group-prepend">
							<span class="input-group-text" style="background-color:white-space; height: 38px; width: 45px;"><i  class="fa fa-address-book"></i></span>
						</div>
					 
					<input type="text"  class="form-control" style="height: 21px; background-color:white" name="city" 
					placeholder="Enter First Name" value="<%=ServletUtility.getParameter("city", request)%>">
				
				</div>&emsp;&emsp;
				
				
					<%  List collegeList=(List)request.getAttribute("collegeList");%>
			 <div class="form-group text-center">
					 <label style="color: white;">College Name :</label>
					 
					 
					<!-- <div class="input-group ">
                             <div class="input-group-prepend">
                             <span class="input-group-text"  style="background-color:white-space; height: 38px; width: 40px;"><i  class="fa fa-user"></i></span>
                                      </div>
                           
                                      </div>  -->
					 
					 <%=HTMLUtility.getList("college",String.valueOf(dto.getId()),collegeList) %></div>	
				
				
				
			
			
				<div class="form-group">
				<button type="submit" name="operation" class=" form-control btn btn-info" style="font-size: 13px"
				 value="<%=CollegeListCtl.OP_SEARCH%>">
                <span class="fa fa-search"></span> Search </button>
       
			     <button type="submit" name="operation" class=" form-control btn btn-warning"  style="font-size: 13px"
			     value="<%=CollegeListCtl.OP_RESET%>" >
                 <span class="	fa fa-refresh"></span> Reset </button>
        
        </div>
     
        </tr>
        </table>
         </div>
 --%>   
 
   <div class="row no-gutters">
	
	<div class="col-sm-2"></div>

	 <!--  <label class="form-check-label" for="check2" style = "max-width:72px"> -->
	 <strong style="color: white">College Name :~</strong>&nbsp;
	 	
				<%  List collegeList=(List)request.getAttribute("collegeList");%>
		
    <div class="col-sm-2"> <%=HTMLUtility.getList("college",String.valueOf(dto.getId()),collegeList) %></div>	
	&emsp;&nbsp;			
	
	<strong style="color: white">City Name :~</strong>&nbsp;
    <div class="col-sm-2">		 
    <input placeholder="Enter City Name" style="background-color: white"  type="text"   name="city" class="border"  value="<%=ServletUtility.getParameter("city", request)%>"> 
	
</div>
	&emsp;
	
	 <div class="col-sm-2 pl-4" style="max-width: 140px">
	  <button type="submit" name="operation" class=" form-control btn btn-info" style="font-size: 13px"
				 value="<%=CollegeListCtl.OP_SEARCH%>">
             <span class="fa fa-search"></span> Search </button>
        </div>
        &nbsp;
      <div class="col-sm-2 pl-1" style="max-width: 100px">     
      <button type="submit" name="operation" class=" form-control btn btn-warning"  style="font-size: 13px"
			     value="<%=CollegeListCtl.OP_RESET%>" >
                 <span class="fa fa-refresh"></span>Reset</button>
      </div>
	 
	 <div class="col-sm-2"></div>
	  </div>
 
 
 
 
        <hr color="white">
       
 
 			<table width="100%">
				<tr>
				
				<%-- 	 <td align="left">
  <button type="submit" name="operation" style="font-size: 12px"
	value="<%=CollegeListCtl.OP_PREVIOUS%>"<%=(pageNo == 1) ? "disabled" : ""%>   class="btn btn-primary">
	<span class="fa fa-chevron-left"></span> <%=CollegeListCtl.OP_PREVIOUS%> </button> 

			</td> --%>
			
	 
				
	
    
      <td align="right">
      <button type="submit" style="font-size: 13px" name="operation" value="<%=CollegeListCtl.OP_DELETE%>"
	  class="btn btn-danger"><span class="fa fa-trash"></span> 
	  <%=CollegeListCtl.OP_DELETE%> </button>
	  </td>	
			
			
			
					
 
               <%--   <td align="right">
        <button type="submit" style="font-size: 13px" name="operation" value="<%=CollegeListCtl.OP_NEXT%>"<%=(list.size()<pageSize||next==0) ? "disabled" : ""%>  class="btn btn-primary" 
        ><%=CollegeListCtl.OP_NEXT%> <span class="fa fa-chevron-right"></span> 
        </button></div>

			</td> --%>

			</table>

		
      <div class="box-body table-responsive " >
      <!-- <table class="table table-striped" width="100%"> -->
     <table  border="3" width="100%"  cellpadding=12px cellspacing=".6">
     
      <thead>
      <tr align="center" style="background:#ffaaff">
      <th class="text-center"><input type="checkbox" id="select_all" name="select"><strong>Select All</strong></th>
                    <th><strong>S.NO</strong></th>
					<th><strong>Name</strong></th>
					<th><strong>Address</strong></th>
					<th><strong>State</strong></th>
					<th><strong>City</strong></th>
					<th><strong>Phone No.</strong></th>
					<th><strong>Edit</strong></th>
      </tr>
      </thead>
      <%
	                int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;
                    int next=DataUtility.getInt(request.getAttribute("nextlist").toString());

                    Iterator<CollegeDTO> it = list.iterator();
                    while (it.hasNext()) {
                    	
                        dto = it.next();
               %>
               <tbody>
				<%-- <tr class="<%=((index)%2==0)?"table-info":"table-danger"%>"> --%>
				<tr align="center" style="background:white">
					<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="<%=dto.getId()%>"></td>
					<td class=""><strong><%=index++%></strong></td>
					<td><strong><%=dto.getName()%></strong></td>
					<td><strong><%=dto.getAddress()%></strong></td>
					<td><strong><%=dto.getState()%></strong></td>
					<td><strong><%=dto.getCity()%></strong></td>
					<td><strong><%=dto.getPhoneNo()%></td>
					<%-- <td><strong><a href="CollegeCtl?id=<%=dto.getId()%>">Edit</strong></a></td> --%>
					
					 <td style="size: 20%; text-align: center;">
					
					<a href="CollegeCtl?id=<%=dto.getId()%>">
					<span class="fa fa-edit"></span></a>
					
					
				</tr>
				</tbody>
				<%
                    }
                %>
			</table>
			</div>
			
			
			
			<table width="100%">
				<tr>
				
					 <td align="left">
  <button type="submit" name="operation" style="font-size: 12px"
	value="<%=CollegeListCtl.OP_PREVIOUS%>"<%=(pageNo == 1) ? "disabled" : ""%>   class="btn btn-primary">
	<span class="fa fa-chevron-left"></span> <%=CollegeListCtl.OP_PREVIOUS%> </button> 

			</td>
			<%-- 		<td>
				
      <button type="submit" style="font-size: 13px" name="operation" value="<%=CollegeListCtl.OP_DELETE%>"
	  class="btn btn-danger"><span class="fa fa-trash"></span> 
	  <%=CollegeListCtl.OP_DELETE%> </button>
			</td>	
			
				
							<td>
    <button type="submit" style="font-size: 13px" name="operation" value="<%=CollegeListCtl.OP_NEW%>"
	class="btn btn-primary"> <span class="fas fa-plus-square"></span> 
	<%=CollegeListCtl.OP_NEW%> </button>
			</td>
			 --%>
			<td align="center"><button type="submit" style="font-size: 13px" name="operation" value="<%=CollegeListCtl.OP_NEW%>"
	class="btn btn-primary"> <span class="fas fa-plus-square"></span> 
	<%=CollegeListCtl.OP_NEW%> </button></td>
					
 
                 <td align="right">
        <button type="submit" style="font-size: 13px" name="operation" value="<%=CollegeListCtl.OP_NEXT%>"<%=(list.size()<pageSize||next==0) ? "disabled" : ""%>  class="btn btn-primary" 
        ><%=CollegeListCtl.OP_NEXT%> <span class="fa fa-chevron-right"></span> 
       </button></div>

			</td>

			</table>

			
			<%} %>
			</div>
</form>
<%@include file="Footer.jsp" %>
</body>


</html>