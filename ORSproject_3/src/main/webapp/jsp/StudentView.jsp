<%@page import="in.co.rays.ctl.StudentCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Student</title>
<!-- date picker library -->


<style type="text/css">
@import
	url(https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css)
	;

 @import
	url(https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.3/css/mdb.min.css)
	;
 
body {
	background-image:url("/ORSproject_3/img/abc.jpg");
	background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
}
.card{
background-color:#3751a1!important;
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
.paddingclass{
padding-top:10px;
}




</style>


<style type="text/css">
.setForm{
padding-top: 5%;
padding-left: 25%;
width: 130%
}
.button{
border-radius:10px;
padding:10px;
color:white;
font-size:20px;
background-color:#00cc88
}
.textfield{
border: 1px solid #8080803b;height: 38px; padding-left: 6px;
}

</style>

</head>




<body >
<jsp:useBean id="dto" scope="request" class="in.co.rays.dto.StudentDTO" />

<div>
<%@include file="Header.jsp"%>
</div>
<script>
  $( function() {
    $( "#datepicker" ).datepicker({ 
    	changeMonth :true,
		  changeYear :true,
		  yearRange :'-40 :-18',
		  dateFormat:'dd/mm/yy',
		  	 
		
    	
		 }) ;
  } );
  </script>




<form action="<%=ORSView.STUDENT_CTL%>" method="post">
<!-- getting list for preload -->
<%List collegeList= (List)request.getAttribute("collegeList");
List subjectList= (List)request.getAttribute("subjectList");
		%>
    
    
    <main>
        
        <!--MDB Forms-->
        <div class="container mt-3">

            
            <!-- Grid row -->
            <div class="row">
                <!-- Grid column -->
                 <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body">
                                                   <%long id=DataUtility.getLong(request.getParameter("id")); %>
                                                  <h3 class="text-center default-text py-3"><u><%=(id==0)? "Add Student": "Update Student" %></u></h3>
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



                           <h6 style="color: #fff ">First Name<font color="red">*</font></h6>
                   
					        <div class="input-group">
                            <div class="input-group-prepend">
                              <span class="input-group-text" style="background-color:white-space;"><i class="fa fa-user"></i></span>
                                
                                 </div>
                        <input type="text" placeholder="Enter First Name" class="form-control border" style="background-color:white;" name="firstName" value="<%=DataUtility.getStringData(dto.getFirstName())%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("firstName",request) %> </font>

                      

                           
                           <h6 class="paddingclass" style="color: #fff " >Last Name<font color="red">*</font></h6>
                           

					 <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space;"><i class="fa fa-user"></i></span>
                                 </div>
                        <input type="text" placeholder="Enter Last Name" class="form-control border" style="background-color:white;" name="lastName" value="<%=DataUtility.getStringData(dto.getLastName())%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("lastName",request) %> </font>

                 
                           
                           
                           
                           <h6 class="paddingclass" style="color: #fff" >College<font color="red">*</font></h6>
                           <%String college= HTMLUtility.getList("collegeId",DataUtility.getStringData(dto.getCollegeId()),collegeList); %>
                     
                          
                         <div class="input-group">
                             <div class="input-group-prepend">
                             <span class="input-group-text"><i style="width: 17px" class="fa fa-institution"></i></span>
                                      </div>
                           	<%=college%>
                                      </div>
								<font color="red"> <%=ServletUtility.getErrorMessage("collegeId", request) %></font>
                          
                  
                           
                           <h6 class="paddingclass" style="color: #fff " >Date of Birth<font color="red">*</font></h6>
                         

					 <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" style="background-color:white-space;"><i class="fa fa-calendar"></i></span>
                                 </div>
                        <input type="text" readonly="readonly" id="datepicker" placeholder="Enter Date of Birth" class="form-control border" 
                               style="background-color:white;" name="dob" value="<%=DataUtility.getDateString(dto.getDob())%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("dob",request) %> </font>
                            

                           <h6 class="paddingclass" style="color: #fff " >Mobile Number<font color="red">*</font></h6>
                         

					 <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space;"><i class="fa fa-mobile"></i></span>
                                 </div>
                        <input maxlength="10" type="text" placeholder="Enter Mobile Number" class="form-control border" 
                               style="background-color:white;" name="mobileNo" value="<%=DataUtility.getStringData(dto.getMobileNo())%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("mobileNo",request) %> </font>
                             
                           
                           
                           
                           
                           <h6 class="paddingclass" style="color: #fff "  >Email<font color="red">*</font></h6>
                         
					 <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space;"><i class="fa fa-envelope"></i></span>
                                 </div>
                        <input type="text" placeholder="Enter Email" class="form-control border" style="background-color:white;" name="email" value="<%=DataUtility.getStringData(dto.getEmail())%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("email",request) %> </font>





                                               <%if (id>0){ %>
                                               
                            <div class="text-center paddingclass">
							
								 <button type="submit" class="btn btn-success" name="operation"
									style="font-size: 13px"	value="<%=StudentCtl.OP_UPDATE%>">
										<span class="fa fa-check-square"></span> Update
									</button>
									 
									
							 <button type="submit" class="btn btn-warning" name="operation"
									style="font-size: 13px"	value="<%=StudentCtl.OP_CANCEL%>">
										<span class="fa fa-refresh"></span> Cancel
									</button>
							
							</div>
                          
                          
                          <%}else{ %> 
                           <div class="text-center paddingclass">
							
								 <button type="submit" class="btn btn-success" name="operation"
									style="font-size: 14px"	value="<%=StudentCtl.OP_SAVE%>">
										<span class="fa fa-check-square"></span> Save
									</button>
									 
									
							 <button type="submit" class="btn btn-warning" name="operation"
									style="font-size: 13px"	value="<%=StudentCtl.OP_RESET%>">
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
<br>
<br>

<div>
<%@include file="Footer.jsp" %>
</div>

</body>
</html>