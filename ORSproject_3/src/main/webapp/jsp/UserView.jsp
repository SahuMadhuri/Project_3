 <%@page import="java.util.List"%>
<%@page import="in.co.rays.ctl.UserCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">



<title>Add User</title>

<!-- date picker library -->


<style type="text/css">
@import url(https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css);

@import url(https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.3/css/mdb.min.css);

 body {
	background-image:url("/ORSproject_3/img/abc.jpg");
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

.card{
  /*  background-color:#0080ff82!important; */
  background-color:#3751a1!important;
  /*  background-color:#5271d1!important; */
   border-style: solid;
  border-width: 50px;
}
.form-white .md-form label {
  color: #fff; 
}
.paddingclass{
padding-top:10px;
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
<div>
<%@include file="Header.jsp"%>
</div>
<script>
  /* $( function() {
    $( "#datepicker" ).datepicker({ 
    	changeMonth :true,
		  changeYear :true,
		  yearRange :'-40:-18',
		  dateFormat:'dd/mm/yy',
		  	 
		
    	
		 }) ;
  } ); */
  </script>

<jsp:useBean id="dto" scope="request" class="in.co.rays.dto.UserDTO" />
<form action="<%=ORSView.USER_CTL%>" method="post">
<!-- getting role list for preload -->
<%List list= (List)request.getAttribute("roleList"); %>

    
    
    <main>
        
        <!--MDB Forms-->
        <div class="container mt-3">

            
            <!-- Grid row -->
            <div class="row">
                <!-- Grid column -->
               <div class="col-md-4"></div>
               
                <div class="col-md-4">
                    <div class="card" >
                        <div class="card-body" >
                        
                        <%long id=DataUtility.getLong(request.getParameter("id")); %>
                       
                           <h3 class="text-center default-text py-3"><%=(id==0)? "Add User": "Update User" %></h3>
                       
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
                               <span class="input-group-text" style="background-color:white-space;">
                               <i  class="fa fa-user"></i></span>
                                 </div>
                        <input type="text" placeholder="Enter First Name" class="form-control border" 
                               style="background-color:white" name="firstName" value="<%=DataUtility.getStringData(dto.getFirstname())%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("firstName",request) %> </font>




                           <h6 class="paddingclass" style="color: #fff " >Last Name<font color="red">*</font></h6>
                             <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space;">
                               <i  class="fa fa-user"></i></span>
                                 </div>
                        <input type="text" placeholder="Enter Last Name" class="form-control border" 
                               style="background-color: white;" name="lastName" value="<%=DataUtility.getStringData(dto.getLastname())%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("lastName",request) %> </font>                   


                        <h6 class="paddingclass" style="color: #fff " >Email<font color="red">*</font></h6>
                          <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space;">
                               <i class="fa fa-envelope"></i></span>
                                 </div>
                        <input type="text" placeholder="Enter Email" class="form-control border" 
                               style="background-color:white;" name="email" value="<%=DataUtility.getStringData(dto.getLoginid())%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("email",request) %> </font>

      
                           
                   
                   
                           <h6 class="paddingclass" style="color: #fff " >Mobile Number<font color="red">*</font></h6>                           
                           
                  		 <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space;">
                               <i   class="fa fa-mobile"></i></span>
                                 </div>
                        <input type="text" placeholder="Enter Mobile Number" class="form-control border" maxlength="10"
                               style="background-color:white" name="mobileNumber" value="<%=DataUtility.getStringData(dto.getMobileno())%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("mobileNumber",request) %> </font>

                  <%if(dto.getId()>0 && dto != null) { %> 
                          
                        
                      
                        <input type="hidden" placeholder="Enter Password" class="form-control border" style="height:31px ; background-color:white" name="password" value="<%=DataUtility.getStringData(dto.getPassword())%>">
                               
                        <input type="hidden" placeholder="Enter Confirm Password" class="form-control border" style="height:31px ; background-color:white" name="confirmPassword" value="<%=((id==0)? DataUtility.getStringData(dto.getConfirmpassword()):DataUtility.getStringData(dto.getPassword()))%>">
                             
                          
                          <% } else { %>
                           
                           <h6 class="paddingclass" style="color: #fff ">Password<font color="red">*</font></h6>
                          
                  		 <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space;">
                               <i class="fa fa-lock"></i></span>
                                 </div>
                        <input type="password" placeholder="Enter Password" class="form-control border" 
                               style="background-color:white" name="password" value="<%=DataUtility.getStringData(dto.getPassword())%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("password",request) %> </font>

                         
                           
                           
                           <h6 class="paddingclass" style="color: #fff " >Confirm Password <font color="red">*</font></h6>
                           
                     
                  		 <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space;">
                               <i class="fa fa-lock"></i></span>
                                 </div>
                        <input type="password" placeholder="Enter Confirm Password" class="form-control border" 
                               style="background-color:white" name="confirmPassword" value="<%=((id==0)? DataUtility.getStringData(dto.getConfirmpassword()):DataUtility.getStringData(dto.getPassword()))%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("confirmPassword",request) %> </font>
                          
                           
                        <% } %>
                           
                           <h6 class="paddingclass" style="color: #fff;">Gender<font color="red">*</font></h6>
                           <%HashMap<String,String> map=new HashMap<String,String>();
                           map.put("Male", "Male");
                           map.put("Female","Female");
                           String gender=HTMLUtility.getList("gender",dto.getGender(), map);
                           %>
                          
								<div class="input-group">
                             <div class="input-group-prepend">
                             <span class="input-group-text"><i style="width: 17px" class="fa fa-venus-mars"></i></span>
                                      </div>
                           	<%=gender%>
                                      </div>
								<font color="red"> <%=ServletUtility.getErrorMessage("gender", request) %></font>

                       

                     <% String role=HTMLUtility.getList("role",String.valueOf(dto.getRoleid()),list); %>
                           
                      <h6 class="paddingclass" style="color: #fff " >Role<font color="red">*</font></h6>
                          
                        	<div class="input-group">
                             <div class="input-group-prepend">
                             <span class="input-group-text"><i style="width: 17px" class="fa fa-user"></i></span>
                                      </div>
                           	<%=role%>
                                      </div>
								<font color="red"> <%=ServletUtility.getErrorMessage("role", request) %></font>

                          
                           
                           <h6 class="paddingclass" style="color: #fff">Date Of Birth<font color="red">*</font></h6>
                          <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space;"><i   class="fa fa-calendar"></i></span>
                                 </div>
                        <input type="text" id="date" readonly="readonly" placeholder="Enter dob" class="form-control border" 
                               style="background-color:white" name="dob" value="<%=DataUtility.getDateString(dto.getDob())%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("dob",request) %> </font>

                                                       
                          <%if (id>0){ %>
                          
                          <div class="text-center paddingclass">
							
								 <button type="submit" class="btn btn-success" name="operation"
									style="font-size: 13px"	value="<%=UserCtl.OP_UPDATE%>">
										<span class="fa fa-check-square"></span> Update
									</button>
									 
									
							 <button type="submit" class="btn btn-warning" name="operation"
									style="font-size: 13px"	value="<%=UserCtl.OP_CANCEL%>">
										<span class="fa fa-refresh"></span> Cancel
									</button>
							
							</div>
                          
                          <%}else{ %> 
                         
                            
                            <div class="text-center paddingclass">
							
								 <button type="submit" class="btn btn-success" name="operation"
									style="font-size: 13px"	value="<%=UserCtl.OP_SAVE%>">
										<span class="fa fa-check-square"></span> Save
									</button>
									 
									
							 <button type="submit" class="btn btn-warning" name="operation"
									style="font-size: 13px"	value="<%=UserCtl.OP_RESET%>">
										<span class="fa fa-refresh"></span> Reset
									</button>
							
							</div>
                          
                            
                             <%} %>
                        </div>
                    </div>
                </div>
                <div class="col-md-4"></div>
               </div>
             </main>

</form>


<div>

</div>
<br><br>
</body>
<%@include file="Footer.jsp" %>
</html> 

