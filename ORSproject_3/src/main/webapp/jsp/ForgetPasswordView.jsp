<%@page import="in.co.rays.ctl.ForgetPasswordCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forget Password</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- font-awesome library -->
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
   background-color:#0080ff82!important;
  }

.paddingclass {
	padding-top: 10px;
}

.textfield {
	border: 1px solid #8080803b;
	height: 38px;
	padding-left: 6px;
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
</style>
</head>

<body class="hm-gradient">
	<jsp:useBean id="dto" scope="request"
		class="in.co.rays.dto.UserDTO" />
	<div>
		<%@include file="Header.jsp"%>
	</div>
	<form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="post">





<br>
<br>
<br>

		<main> <!--MDB Forms-->
		<div class="container mt-3">


			<!-- Grid row -->
			<div class="row">
				<div class="col-md-4"></div>
				<!-- Grid column -->
				<div class="col-md-4">
					<div class="card">
						<div class="card-body">

							<h3 class="text-center default-text py-2"><u>Forget Password</u></h3>
							<hr color="black">
							<!--Body-->
							<%if(!ServletUtility.getSuccessMessage(request).equals("")){ %>
							<div class="alert alert-success alert-dismissible fade show">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<font color="green" ><%=ServletUtility.getSuccessMessage(request) %></font>
							</div>
							<%} %>

							<%if(!ServletUtility.getErrorMessage(request).equals("")){ %>
							<div class="alert alert-danger alert-dismissible fade show">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
							   <font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
							  </div>
							<%} %>



							<h6 style="color: #fff">
								<b>Please Enter Your Email:</b>
							</h6>
						
							
							 <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space;height: 40px; width: 45px;"><i  class="fa fa-envelope"></i></span>
                                 </div>
                        <input type="text" placeholder="Enter Email" class="form-control border" style="height:40px ;background-color:white" name="login" value="<%=DataUtility.getStringData(dto.getLoginid())%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("login",request) %> </font>
							
                        					
							
				<div class="text-center paddingclass">
							
					<button type="submit" class="btn btn-success" name="operation"
							style="font-size: 13px"	value="<%=ForgetPasswordCtl.OP_GO%>">
					<span class="fa fa-check-square"></span> Go
					</button>
									 
									
					<button type="submit" class="btn btn-warning" name="operation"
							style="font-size: 13px"	value="<%=ForgetPasswordCtl.OP_RESET%>">
					<span class="fa fa-refresh"></span> Reset
					</button>
							
				</div>
							
						</div>
					</div>
				</div>


				<div class="col-md-4"></div>
			</div>
		</div>
	</form>
</body>
<%@ include file="Footer.jsp" %>
</html>