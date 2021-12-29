<%@page import="in.co.rays.ctl.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Title</title>
<!-- Select All Library -->
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/CheckBox11.js"></script>	

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
    color: #ff3547; }
.default-text {
    color: #2BBBAD; 
}
.info-text {
    color: #33b5e5; 
}
.form-white .md-form label {
  color: #fff; 
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
.button{
border-radius:5px;width:100px; padding:5px;color:white;font-size:20px;background-color:#00cc88
}
.fonta{
font-size: 20px;
}
</style>
<script>
$(document).ready(function(){
  $('[data-toggle="tooltip"]').tooltip();
});
</script>
<style type="text/css">
.header{
padding-top: -3%
}


</style>

</head>
<body>


<form action="<%=ORSView.WELCOME_CTL%>">
<div>
<%@include file="Header.jsp" %>
</div>
<div class="container-fluid">
<h1 style="padding: 90px">
    <h1 align="Center" >
    
        <img src="img/logo1.png" width="318" height="127" border="0">
<center><h1  style="color: red" class="header">Welcome To ORS</h1></center>
    </h1>

</div>
</form>
<%@include file="Footer.jsp" %>
</body>






