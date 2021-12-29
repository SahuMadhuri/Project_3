<%@page import="in.co.rays.ctl.LoginCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

<!-- font-awesome library -->
<style type="text/css">
@import
	url(https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css)
	;

 @import
	url(https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.3/css/mdb.min.css)
	;
 

 
 body {
	background-image:url('img/abc.jpg');
	background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
}
 

 
.darken-grey-text {
	color: #2E2E2E;
}

.danger-text {
	color: #ff3547;
}

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

.paddingclass {
	padding-top: 10px;
}
</style>
<style type="text/css">
.setForm {
	padding-top: 5%;
	padding-left: 25%;
	width: 130%
}

.button {
	border-radius: 10px;
	padding: 10px;
	color: white;
	font-size: 20px;
	background-color: #00cc88
}

.textfield {
	border: 1px solid #8080803b;
	height: 38px;
	padding-left: 6px;
}
</style>
</head>

<body>

<% 	String uri = (String)request.getAttribute("uri");%>
	<jsp:useBean id="dto" scope="request"
		class="in.co.rays.dto.UserDTO" />
	<div>
		<%@include file="Header.jsp"%>
	</div>
	<form action="<%=ORSView.LOGIN_CTL%>" method="post">



		<main> <!--MDB Forms-->
		<div class="container mt-4 ">


			<!-- Grid row -->
			<div class="row">

				<div class="col-md-4"></div>

				<!-- Grid column -->
				<div class="col-md-4">
					<div class="card">
						<div class="card-body">

							<h3 class="text-center default-text py-1" style="color: #fff"><b><u>Login</u></b></h3>
							<!--Body-->
							<hr color="white">
							<%if(!ServletUtility.getSuccessMessage(request).equals("")){ %>
							<div class="alert alert-success alert-dismissible fade show">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<%=ServletUtility.getSuccessMessage(request) %>
							</div>
							<%} %>
							<%if(!ServletUtility.getErrorMessage(request).equals("")){ %>
							<div class="alert alert-danger alert-dismissible fade show">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<font color="red"><%=ServletUtility.getErrorMessage(request) %></font>
							</div>
							<%} %>

						 	 <h6 style="color: #fff">
								<b>Username:</b><font color="red">*</font>
							</h6>
   
							


     					 <div class="input-group">
                            <div class="input-group-prepend ">
                               <span class="input-group-text" style="background-color:white-space; height:40px; width: 45px;"><i  class="fa fa-envelope"></i></span>
                                 </div>
                        <input type="text" placeholder="Enter Email" class="form-control border" style="height:40px ; background-color:white" name="login" value="<%=DataUtility.getStringData(dto.getLoginid())%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("login",request) %> </font>

						
							<h6 class="paddingclass" style="color: #fff">
								<b>Password:</b><font color="red">*</font>
							</h6>


                             <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space; height: 40px; width: 45px;"><i  class="fa fa-lock"></i></span>
                                 </div>
                        <input type="password" placeholder="Enter Password" class="form-control border" style="height:40px ; background-color:white" name="password" value="<%=DataUtility.getStringData(dto.getPassword())%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("password",request) %> </font>



                         <div class="text-center paddingclass">
							
								 <button type="submit" class="btn btn-success" name="operation"
									style="font-size: 13px"	value="<%=LoginCtl.OP_SIGN_IN%>">
										<span class="fa fa-check-square"></span> Signin
									</button>
									 
									
							 <button type="submit" class="btn btn-info" name="operation"
									style="font-size: 13px"	value="<%=LoginCtl.OP_SIGN_UP%>">
										<span class="fa fa-user"></span> Signup
									</button>
							
							</div>
							<div class="text-center" style="color: #20B2AA">
								<b> <font size="4px"> <a style="color:white;"
										href="<%=ORSView.FORGET_PASSWORD_CTL%>"><u>Forget password ?</u></a></font></b>
							</div>



						</div>
					</div>
				</div>
				
				
			
             <input type="hidden" name="uri" value="<%=uri%>">
				<div class="col-md-4"></div>
			</div>
		</div>
	</form>
	
</body>
<br><br>

<%@include file="Footer.jsp" %>

</html>

