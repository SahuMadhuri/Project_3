<%@page import="in.co.rays.ctl.ChangePasswordCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
<!-- font-awesome library -->
<style type="text/css">
@import url(https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css);

@import url(https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.3/css/mdb.min.css);

/* .hm-gradient {
    background-image: linear-gradient(to top, #f3e7e9 0%, #e3eeff 99%, #e3eeff 100%);
} */

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
.form-white .md-form label {
  color: #fff; 
}
.form-white input[type=text]:focus:not([readonly]) {
    border-bottom: 1px solid #fff;
    -webkit-box-shadow: 0 1px 0 0 #fff;
    box-shadow: 0 1px 0 0 #fff; 
}
.form-white input[type=text]:focus:not([readonly]) + label {
    color: #fff; 
}
.form-white input[type=password]:focus:not([readonly]) {
    border-bottom: 1px solid #fff;
    -webkit-box-shadow: 0 1px 0 0 #fff;
    box-shadow: 0 1px 0 0 #fff; 
}
.form-white input[type=password]:focus:not([readonly]) + label {
    color: #fff; 
}
.form-white input[type=password], .form-white input[type=text] {
    border-bottom: 1px solid #fff; 
}

.form-white .form-control:focus {
    color: #fff;
}
.form-white .form-control {
    color: #fff;
}
.form-white textarea.md-textarea:focus:not([readonly]) {
    border-bottom: 1px solid #fff;
    box-shadow: 0 1px 0 0 #fff;
    color: #fff; 
}
.form-white textarea.md-textarea  {
    border-bottom: 1px solid #fff;
    color: #fff;
}
.form-white textarea.md-textarea:focus:not([readonly])+label {
    color: #fff;
}
.ripe-malinka-gradient {
    background-image: linear-gradient(120deg, #f093fb 0%, #f5576c 100%);
}
.near-moon-gradient {
    background-image: linear-gradient(to bottom, #5ee7df 0%, #b490ca 100%);
}
.paddingclass{
padding-top: 10px;
}

</style>
<style type="text/css">
.setForm{
padding-top: 5%;
padding-left: 25%;
width: 130%
}
.textfield{
border: 1px solid #8080803b;height: 38px; padding-left: 6px;
}


.button{
border-radius:10px;padding:10px;color:white;font-size:20px;background-color:#00cc88
}
</style>
</head>

<body  class="hm-gradient">
<jsp:useBean id="dto" scope="request" class="in.co.rays.dto.UserDTO" />
<div>
<%@include file="Header.jsp" %>
</div>
<form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="post">



    
    
    <main>
        
        <!--MDB Forms-->
        <div class="container mt-2">

            
            <!-- Grid row -->
            <div class="row">
            <div class="col-md-4"></div>
                <!-- Grid column -->
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body">
                        
                            <h3 class="text-center default-text py-2"> <u>Change Password:</u></h3>
                           
                            <hr color="black"> 
                             <!--Body-->

                            		<%if(!ServletUtility.getSuccessMessage(request).equals("")){ %>
    <div class="alert alert-success alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <%=ServletUtility.getSuccessMessage(request) %>
    </div>
    <%} %>                            
	
	<%if(!ServletUtility.getErrorMessage(request).equals("")){ %>
    <div class="alert alert-danger alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <%=ServletUtility.getErrorMessage(request) %>
    </div>
    <%} %>                            
		
            <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
   
                    <h6 style="color: #fff ">Old Password<font color="red">*</font></h6>
                         <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space;">
                               <i   class="fa fa-lock"></i></span>
                                 </div>
                        <input type="password" placeholder="Enter Old Password" class="form-control border" 
                               style="background-color:white" name="oldPassword"
                               value="<%=DataUtility.getString(request.getParameter("oldPassword") == null ? "" : DataUtility.getString(request.getParameter("oldPassword")))%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("oldPassword",request) %> </font>

                            
                            
                      <h6 class="paddingclass" style="color: #fff ">New Password<font color="red">*</font></h6>
                          <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space;">
                               <i   class="fa fa-key"></i></span>
                                 </div>
                        <input type="password" placeholder="Enter New Password" class="form-control border" 
                               style="background-color:white" name="newPassword"
                               value="<%=DataUtility.getString(request.getParameter("newPassword") == null ? "" : DataUtility.getString(request.getParameter("newPassword")))%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("newPassword",request) %> </font>

                     
                            
                        <h6 class="paddingclass" style="color: #fff ">Confirm Password<font color="red">*</font></h6>
                           <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space;">
                               <i   class="fa fa-key"></i></span>
                                 </div>
                        <input type="password" placeholder="Enter New Password" class="form-control border" 
                               style="background-color:white" name="confirmPassword"
                               value="<%=DataUtility.getString(request.getParameter("confirmPassword") == null ? "" : DataUtility.getString(request.getParameter("confirmPassword")))%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("confirmPassword",request) %> </font>

                          
                           <div class="text-center paddingclass">
				            <button type="submit" class="btn btn-success" name="operation"
									style="font-size: 13px"	value="<%=ChangePasswordCtl.OP_SAVE%>">
										<span class="fa fa-check-square"></span> Save
									</button>
									 
									
						    <button type="submit" class="btn btn-warning" name="operation"
									style="font-size: 13px"	value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%>">
										<span class="fa fa-eraser"></span> Change My Profile
									</button>
							
							</div>
                          
                        </div>
                    
                    </div>
                     </div>

<div class="col-md-3"></div>
</div>               
</div>
</form>
<br><br><br>
</body>
<%@include file="Footer.jsp" %>
</html>