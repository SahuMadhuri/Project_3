<%@page import="in.co.rays.dto.RoleDTO"%>
<%@page import="in.co.rays.dto.UserDTO"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

  <meta charset="utf-8">
  
  <meta name="viewport" content="width=device-width, initial-scale=1">
 
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
 
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
 
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  
  <script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
  <script type="text/javascript" src="<%=ORSView.APP_CONTEXT%>/js/CheckBox11.js"></script>
  
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com//jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
  
function disableSunday(d){
	  var day = d.getDay();
	  if(day==0)
	  {
	   return [false];
	  }else
	  {
		  return [true];
	  }
}
	$(function() {
		$("#datepicker1").datepicker({
			dateFormat : 'mm/dd/yy',
			//defaultDate : "06/06/2001",
			changeMonth : true,
			changeYear : true,
			//yearRange: "c-20:c+0",
			yearRange : "2021:2023",
			 beforeShowDay : disableSunday,	
			 
		//maxDate:'0',
		// minDate:0
		//yearRange : "-40:-18"
		});
	}); 
  
  $( function() {
	  $( "#date" ).datepicker({
		  changeMonth :true,
		  changeYear :true,
		  yearRange :'1985:2021',
		  dateFormat:'dd/mm/yy',

// Disable for Sunday
		  /* beforeShowDay : disableSunday, */		  
// Disable for back date
		 // minDate : 0   
	  });
  } );
  </script>


</head>
<body>
<%@page import="in.co.rays.ctl.LoginCtl"%>

<%@page import="in.co.rays.ctl.ORSView"%>

<%
    UserDTO userDto = (UserDTO) session.getAttribute("user");

	boolean userLoggedIn = userDto != null; 

	String welcomeMsg = "Hi, ";

	if (userLoggedIn) 
	{
	    String role = (String) session.getAttribute("role");
  
	    welcomeMsg += userDto.getFirstname() + " (" + role + ")";

	} 
	else 
	{
  	 	welcomeMsg += "Guest";
	}
%>
<style>
 .navbar-dark .navbar-nav .nav-link {
    color: #f8f9fa; 
  
}

.dropdown-menu{
background-color: #fa00ffab !importent;
}

.dropdown-item:hover 
{
 background-color:#fa00ffab !importent; 
background :#3751a1;
}

/* .nav{
 background-color:#ffc1077d!important;
} */
 </style>

<nav class="navbar navbar-expand-xl  navbar-dark  " style="background-color: rgba(28, 35, 49, 0.6);">
  <a class="navbar-brand" href="<%=ORSView.WELCOME_CTL%>">
  <img src="<%=ORSView.APP_CONTEXT%>/img/rays.png" width="95" height="40"></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
   <div class="collapse navbar-collapse" id="collapsibleNavbar" style="font-size:15.5px">
    <ul class="navbar-nav ml-auto mr-5">
   
   
   
   
            <%
            if (userLoggedIn) {
          %>
         
         <%
          if (userDto.getRoleid() == RoleDTO.STUDENT) {
        %>

   <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        &nbsp;User
      </a>
      <div class="dropdown-menu" >
        <a class="dropdown-item"  href="<%=ORSView.MY_PROFILE_CTL%>"><i class='fas fa-graduation-cap'></i>&nbsp;<strong>My Profile</strong> </a>
       <a class="dropdown-item"  href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><i class='fas fa-edit'></i>&nbsp;<strong>Change Password </strong></a>
      </div>
    </li>

<!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle"  href="#" id="navbardrop" data-toggle="dropdown">
       &nbsp;Marksheet
      </a>
      <div class="dropdown-menu" >
        <a class="dropdown-item"  href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><i class='fa fa-list-alt' ></i>&nbsp;<strong>Marksheet Merit List</strong></a>
        <a class="dropdown-item"  href="<%=ORSView.GET_MARKSHEET_CTL%>"><i class='fas fa-plus-square' ></i>&nbsp;<strong>Get Marksheet</strong></a>
     
      </div>
    </li>


<%
}else if(userDto.getRoleid()==RoleDTO.ADMIN){
%>
   
       
          <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
       <i class='fas fa-user'></i>&nbsp;User
      </a>
      <div class="dropdown-menu" >
        <a class="dropdown-item"  href="<%=ORSView.USER_CTL%>"><i class='fas fa-plus-square'></i>&nbsp;<strong>Add User</strong></a>
        <a class="dropdown-item"  href="<%=ORSView.USER_LIST_CTL%>"><i class='fa fa-list-alt'></i>&nbsp;<strong>User List</strong></a>
     
      </div>
    </li>
   
       <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown" class="fonta">
        <i class='fas fa-user-circle'></i>&nbsp;Role
      </a>
      <div class="dropdown-menu" >
        <a class="dropdown-item"  href="<%=ORSView.ROLE_CTL%>"><i class='fas fa-plus-square'></i>&nbsp;<strong>Add Role</strong></a>
        <a class="dropdown-item"  href="<%=ORSView.ROLE_LIST_CTL%>"><i class='fa fa-list-alt'></i>&nbsp;<strong>Role List</strong></a>
     
      </div>
    </li>
   
   
     <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        <i class='fas fa-university'></i>&nbsp;College
      </a>
      <div class="dropdown-menu" >
        <a class="dropdown-item"  href="<%=ORSView.COLLEGE_CTL%>"> <i class='fas fa-plus-square'></i>&nbsp;<strong>Add College</strong></a>
        <a class="dropdown-item"  href="<%=ORSView.COLLEGE_LIST_CTL%>"> <i class='fa fa-list-alt'></i>&nbsp;<strong>College List</strong></a>
     
      </div>
    </li>
   
   
      <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
         <i class='fas fa-user-graduate'></i>&nbsp;Student
      </a>
      <div class="dropdown-menu" >
        <a class="dropdown-item"  href="<%=ORSView.STUDENT_CTL%>"><i class='fas fa-plus-square'></i>&nbsp;<strong>Add Student</strong></a>
        <a class="dropdown-item"  href="<%=ORSView.STUDENT_LIST_CTL%>"> <i class='fa fa-list-alt'></i>&nbsp;<strong>Student List</strong></a>
     
      </div>
    </li>
   
   
      <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle"  href="#" id="navbardrop" data-toggle="dropdown">
       <i class='fas fa-file-alt' ></i>&nbsp;Marksheet
      </a>
      <div class="dropdown-menu" >
        <a class="dropdown-item"  href="<%=ORSView.MARKSHEET_CTL%>"><i class='fas fa-plus-square' ></i>&nbsp;<strong>Add Marksheet</strong></a>
        <a class="dropdown-item"  href="<%=ORSView.MARKSHEET_LIST_CTL%>"><i class='fa fa-list-alt' ></i>&nbsp;<strong>Marksheet List</strong></a>
        <a class="dropdown-item"  href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><i class='fa fa-list-alt' ></i>&nbsp;<strong>Marksheet Merit List</strong></a>
        <a class="dropdown-item"  href="<%=ORSView.GET_MARKSHEET_CTL%>"><i class='fas fa-plus-square' ></i>&nbsp;<strong>Get Marksheet</strong></a>
     
      </div>
    </li>
   
     <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        <i class='fas fa-user-tie'></i>&nbsp;Faculty
      </a>
      <div class="dropdown-menu" >
        <a class="dropdown-item"  href="<%=ORSView.FACULTY_CTL%>"><i class='fas fa-plus-square'></i>&nbsp;<strong> Add Faculty</strong></a>
        <a class="dropdown-item"  href="<%=ORSView.FACULTY_LIST_CTL%>"><i class='fa fa-list-alt'></i>&nbsp;<strong> Faculty List</strong></a>
     
      </div>
    </li>
   
    <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
         <i class='fa fa-book'></i>&nbsp;Course
      </a>
      <div class="dropdown-menu" >
        <a class="dropdown-item"  href="<%=ORSView.COURSE_CTL%>"><i class='fas fa-plus-square'></i>&nbsp;<strong>Add Course</strong></a>
        <a class="dropdown-item"  href="<%=ORSView.COURSE_LIST_CTL%>"><i class='fa fa-list-alt'></i>&nbsp;<strong>Course List</strong></a>
     
      </div>
    </li>
   
    <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
       <i class='fas fa-book'></i>&nbsp;Subject
      </a>
      <div class="dropdown-menu" >
        <a class="dropdown-item"  href="<%=ORSView.SUBJECT_CTL%>"><i class='fas fa-plus-square'></i>&nbsp;<strong>Add Subject</strong></a>
        <a class="dropdown-item"  href="<%=ORSView.SUBJECT_LIST_CTL%>"><i class='fa fa-list-alt'></i>&nbsp;<strong>Subject List</strong></a>
     
      </div>
    </li>
   
   
     <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        <i class='fas fa-clock'></i>&nbsp;TimeTable
      </a>
      <div class="dropdown-menu" >
        <a class="dropdown-item"  href="<%=ORSView.TIMETABLE_CTL%>"><i class='fas fa-plus-square'></i>&nbsp;<strong>Add TimeTable</strong></a>
        <a class="dropdown-item"  href="<%=ORSView.TIMETABLE_LIST_CTL%>"><i class='fa fa-list-alt'></i>&nbsp;<strong>TimeTable List</strong></a>
     
      </div>
    </li>
   
      <%
            }
            }
        %>
   
   
 &emsp;&nbsp;
       
         <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
     <i class='fas fa-user'></i>&nbsp;<b><%=welcomeMsg %></b></a>
      <div class="dropdown-menu" left:-10px;">
     
      <%
if(userLoggedIn){
      %>
     
      <a class="dropdown-item"  href="<%=ORSView.MY_PROFILE_CTL%>"><i class='fas fa-graduation-cap'></i>&nbsp;<strong>My Profile</strong> </a>
       <a class="dropdown-item" href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><i class='fas fa-edit'></i>&nbsp;<strong>Change Password </strong></a>
       <a class="dropdown-item" href="<%=ORSView.JAVA_DOC_VIEW %>" target="blank"><i class='fas fa-clone'></i>&nbsp;<strong>Java Doc</strong></a>
        <a class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><i class='fas fa-sign-out-alt'></i>&nbsp;<strong>Logout</strong></a>
       
       
      </div>
    </li>  
                   
                   
             <%
                } else {
            %>
            <a class="dropdown-item"  href="<%=ORSView.LOGIN_CTL%>"><i class='fas fa-sign-in-alt'></i>&nbsp;<strong>Login</strong></a>
             <a class="dropdown-item"  href="<%=ORSView.USER_REGISTRATION_CTL%>"><i class='fas fa-user-plus'></i>&nbsp;<strong>User Registration</strong></a>
           <%--   <a class="dropdown-item" style="background-color: #ffeb3bab" href="<%=ORSView.FORGET_PASSWORD_CTL%>"><i class='fa fa-lock'></i>&nbsp;<strong>Forget Password</strong></a>
            --%>
             <%
     }
 %></ul>
</div>
</nav>

</body>
</html>
