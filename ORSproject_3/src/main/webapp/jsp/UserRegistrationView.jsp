<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.ctl.UserRegistrationCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
<!-- date picker library -->


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
  background-color:#3751a1!important;
  }
.paddingclass {
	padding-top:10px;
}

#textfield {
	border: 2px solid #8080803b;
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
	<jsp:useBean id="dto" scope="request" class="in.co.rays.dto.UserDTO" />

	<div>
		<%@include file="Header.jsp"%>
	</div>



	<script>
 /*  $( function() {
    $( "#datepicker" ).datepicker({ 
    	changeMonth :true,
		  changeYear :true,
		  yearRange :'-40:-18',
		  dateFormat:'dd/mm/yy',
	  endDate: '-18y'	 
		
    	
		 }) ;
  } ); */
  </script>

	<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">



		<main> <!--MDB Forms-->
		<div class="container mt-3">
			<span class="rounded"></span>

			<!-- Grid row -->
			<div class="row">

				<div class="col-md-4"></div>
				<!-- Grid column -->
				<div class="col-md-4">
					<div class="card">
						<div class="card-body">
							<h3 class="text-center default-text py-3"><u>User Registration</u>
							</h3>
							<hr color="black">
							<!--Body-->
							<%
		
		long id=DataUtility.getLong(request.getParameter("id"));
		
		if(!ServletUtility.getSuccessMessage(request).equals("")){ %>
							<div class="alert alert-success alert-dismissible fade show">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<%=ServletUtility.getSuccessMessage(request) %>
							</div>
							<%} %>

							<%if(!ServletUtility.getErrorMessage(request).equals("")){ %>
							<div class="alert alert-success alert-dismissible fade show">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
						     <font color="red"> <%=ServletUtility.getErrorMessage(request) %></font>
							</div>
							<%} %>

							<input type="hidden" name="id" value="<%=dto.getId()%>">
							<input type="hidden" name="createdBy"
								value="<%=dto.getCreatedBy()%>"> <input type="hidden"
								name="modifiedBy" value="<%=dto.getModifiedBy()%>"> <input
								type="hidden" name="createdDatetime"
								value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
							<input type="hidden" name="modifiedDatetime"
								value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">







							<h6 style="color: #fff">
								First Name<font color="red">*</font>
							</h6>
		                    	<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" style="background-color:white-space;"><i 
											class="fa fa-user"></i></span>
									</div>
									<input type="text" name="firstName"
										value="<%=DataUtility.getStringData(dto.getFirstname())%>"
										class="form-control border" style="background-color:white" placeholder="Enter First Name">
								</div>
								<font color="red"> <%=ServletUtility.getErrorMessage("firstName", request) %></font>
					
							<h6 style="color: #fff" class="paddingclass">
								Last Name<font color="red">*</font>
							</h6>
						        <div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" style="background-color:white-space;" ><i style="width: 17px"
											class="fa fa-user"></i></span>
									</div>
									<input type="text" name="lastName"
										value="<%=DataUtility.getStringData(dto.getLastname())%>"
										class="form-control border" style="background-color:white" placeholder="Enter Last Name">
								</div>
								<font color="red"> <%=ServletUtility.getErrorMessage("lastName", request) %></font>
						



							<h6 style="color: #fff" class="paddingclass">
								Email<font color="red">*</font>
							</h6>
							<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" style="background-color:white-space;"><i 
											class="fa fa-envelope"></i></span>
									</div>
									<input type="text" name="email"
										value="<%=DataUtility.getStringData(dto.getLoginid())%>"
										class="form-control border" style="background-color:white" placeholder="Enter Email">
								</div>
								<font color="red"> <%=ServletUtility.getErrorMessage("email", request) %></font>
						
							<h6 style="color: #fff" class="paddingclass">
								Mobile Number<font color="red">*</font>
							</h6>
									<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" style="background-color:white-space;"><i
											class="fa fa-mobile"></i></span>
									</div>
									<input type="text" maxlength="10" name="mobileNumber"
										value="<%=DataUtility.getStringData(dto.getMobileno())%>"
										class="form-control border" style="background-color:white" placeholder="Enter Mobile Number">
								</div>
								<font color="red"> <%=ServletUtility.getErrorMessage("mobileNumber", request) %></font>
							


							<h6 style="color: #fff" class="paddingclass">
								Password<font color="red">*</font>
							</h6>
							<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" style="background-color:white-space;"><i
											class="fa fa-lock"></i></span>
									</div>
									<input type="password" name="password"
										value="<%=DataUtility.getStringData(dto.getPassword())%>"
										class="form-control border" style="background-color:white" placeholder="Enter Password">
								</div>
								<font color="red"> <%=ServletUtility.getErrorMessage("password", request) %></font>
						

							<h6 style="color: #fff" class="paddingclass">
								Confirm Password<font color="red">*</font>
							</h6>
						         <div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" style="background-color:white-space;"><i
											class="fa fa-lock"></i></span>
									</div>
									<input type="password" name="confirmPassword"
										value="<%=((id==0)?DataUtility.getStringData(dto.getConfirmpassword()):DataUtility.getStringData(dto.getPassword()))%>"
										class="form-control border" style="background-color:white"
										placeholder="Enter Confirm Password">
								</div>
								<font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request) %></font>
							
                        <%HashMap<String,String> map=new HashMap<String,String>();
                           map.put("Male", "Male");
                           map.put("Female","Female");
                           String gender=HTMLUtility.getList("gender",dto.getGender(), map);
                        %>

							<h6 style="color: #fff" class="paddingclass">
								Gender<font color="red">*</font>
							</h6>
							
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i 
											class="fa fa-venus-mars"></i></span>
									</div>
									<%=gender%>
								</div>
								<font color="red"> <%=ServletUtility.getErrorMessage("gender", request) %></font>
						



							<h6 style="color: #fff" class="paddingclass">
								Date of Birth<font color="red">*</font>
							</h6>
						     <div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" style="background-color:white-space;"><i
											class="fa fa-calendar"></i></span>
									</div>
									<input type="text" id="datepicker" readonly="readonly"
										name="dob"
										value="<%=DataUtility.getDateString(dto.getDob())%>"
										class="form-control border" style="background-color:white" placeholder="Enter dob">

                                </div>
								<span> <font color="red"> <%=ServletUtility.getErrorMessage("dob", request) %></font></span>
							




							
								<div class="text-center paddingclass">
							
								 <button type="submit" class="btn btn-success" name="operation"
									style="font-size: 13px"	value="<%=LoginCtl.OP_SIGN_UP%>">
										<span class="fa fa-check-square"></span> Save
									</button>
									 
									
							 <button type="submit" class="btn btn-warning" name="operation"
									style="font-size: 13px"	value="<%=LoginCtl.OP_RESET%>">
										<span class="fa fa-refresh"></span> Reset
									</button>
							
							</div>
							
							
						</div>
					</div>
				</div>

				<div class="col-md-4"></div>
			</div>
		</div>
		<br>
		<br>
	</form>

	<%@include file="Footer.jsp"%>


</body>
</html>