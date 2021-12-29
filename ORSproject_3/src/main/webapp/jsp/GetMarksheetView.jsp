<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<%@page import="in.co.rays.ctl.GetMarksheetCtl"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.model.ModelFactory"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>


<head>
<title>Get Maksheet</title>

<script type="text/javascript" src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>

<style type="text/css">

@import
	url(https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css);

@import
	url(https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.3/css/mdb.min.css);

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

.button{
border-radius:10px;padding:10px;color:white;font-size:20px;background-color:#00cc88
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
padding-top: 10px;
}
</style>
</head>

<body>
  
    <jsp:useBean id="dto" class="in.co.rays.dto.MarksheetDTO" scope="request"></jsp:useBean>
	
    <%@include file="Header.jsp"%>
	
	<form action="<%=ORSView.GET_MARKSHEET_CTL%>" method="post">
	
		<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=dto.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
	
	 

       <% 		
        if (dto.getName() == null ) {
       %>
        
     
     <main> <!--MDB Forms-->
		<div class="container mt-4">


			<!-- Grid row -->
			<div class="row">
				<div class="col-md-4"></div>
				<!-- Grid column -->
				<div class="col-md-4">
					<div class="card">
						<div class="card-body">

						 <H3 class="text-center py-3" style="color: white">  <span class="fa fa-file-alt"></span><b > <u>Get Marksheet</u></b> </H3>
							<hr color="white">
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
							<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>  
							  </div>
							<%} %>



							<h6 style="color: #fff">
								<b>Please Enter Your RollNo :</b>
							</h6>
							
							
							 <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space;height: 40px; width: 45px;"><i  class="fa fa-envelope"></i></span>
                                 </div>
                          <input type="text" placeholder="Enter RollNo" class="form-control border" 
                                 style="height:40px; background-color:white" name="rollNo" value="<%=DataUtility.getStringData(dto.getRollNo())%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("rollNo",request) %> </font>
							
                
				<div class="text-center paddingclass">
							
					<button type="submit" class="btn btn-success" name="operation"
							style="font-size: 13px"	value="<%=GetMarksheetCtl.OP_GO%>">
					<span class="fa fa-check-square"></span> Go
					</button>
									 
									
					<button type="submit" class="btn btn-warning" name="operation"
							style="font-size: 13px"	value="<%=GetMarksheetCtl.OP_RESET%>">
					<span class="fa fa-refresh"></span> Reset
					</button>
							
				</div>
				 	
						</div>
					</div>
				</div>


				<div class="col-md-4"></div>
			</div>
		</div>
     
	    <% } if (dto.getName() != null && dto.getName().trim().length() > 0) {

        	String grade = null;
			String result = null;
			long phyMarks = DataUtility.getLong(dto.getPhysics()+"");
			long chemMarks = DataUtility.getLong(dto.getChemistry()+"");
			long MathMarks = DataUtility.getLong(dto.getMaths()+"");
			long totalMarks = (phyMarks + chemMarks + MathMarks);
		
			float percentage=(float)totalMarks/3;
        	percentage=percentage*100;
        	percentage=Math.round(percentage);
        	percentage=percentage/100; 
        	
        	if (phyMarks >= 35 && chemMarks >= 35 && MathMarks >= 35) {
				if (totalMarks >= 195) {
					grade = "A";
					result = "Pass";
				} else if (totalMarks < 195 && totalMarks >= 150) {
					grade = "B";
					result = "Pass";
				} else {
					grade = "c";
					result = "Pass";
				}

			} else {
				grade = "D";
				result = "Fail";
			}
        	
        	String div = null;
			if (percentage >= 60) {
				div = "First";
			} else if (percentage >= 45 && percentage < 60) {
				div = "Second";
			} else if (percentage >= 33 && percentage < 45) {
				div = "Third";
			}else if (percentage <33){
				div="Fail";
			}
		    	
        %>
        <div class="container-fluid">
       	  <div class="card" style="background-color:#0080ff82; margin-bottom: 50px; margin-top:20px "  >
        <div class="card-body" >
        
          <H3 class="text-center py-1" style="color: white">  <span class="fa fa-file-alt"></span><b > <u>Get Marksheet</u></b> </H3>
							<hr color="white">
        				
        				
		<div class="box-body table-responsive">
			<!-- <table  class="table  table-bordered table-striped"> -->
			<table border="3" width="100%"  cellpadding=12px cellspacing=".6">
			 <tbody style="background:white" >
				<tr align="center">
					<th colspan="4" style="background:#ffaaff" align="center">
					   <b><i style="font-size: x-large">Provisional Marksheet</i></b></th>
				</tr>
				<tr>
					<th style="text-align: center;"><strong>Roll No. :-</strong></th>
					<th colspan="3"><strong><%=DataUtility.getStringData(dto.getRollNo())%>
					</strong></th>
				</tr>
				<tr>
					<th style="text-align: center;"><strong>Name :-</strong></th>
					<th colspan="3" class="space"><strong>
					<%=DataUtility.getStringData(dto.getName())%></strong></th>
				</tr>
				
				<tr>
					<th style="text-align: center;"><strong>CollegeName :-</strong></th>
					<th colspan="3" class="space">
					<strong><%=DataUtility.getStringData(request.getAttribute("CollegeName"))%>
					</strong></th>
				</tr>
				<tr style="background-color: #ffaaff">
					<th style="text-align: center;"><strong>Subject :-</strong></th>
					<th style="text-align: center;"><strong>Maximum Marks :-</strong></th>
					<th style="text-align: center;"><strong>Minimum Marks :-</strong></th>
					<th style="text-align: center;"><strong>Obtained Marks :-</strong></th>
				</tr>
				<tr>
					<th style="text-align: center;"><strong>Physics</strong></th>
					<td style="text-align: center;"><strong>100</strong></td>
					<td style="text-align: center;"><strong>35</strong></td>
					 <%if(dto.getPhysics()<35){ %> 
					<td style="text-align: center;"><strong><font color="red">
					<%=DataUtility.getStringData(dto.getPhysics())%></font></strong></td>
					<%} else{%>
					<td style="text-align: center;"><strong>
					<%=DataUtility.getStringData(dto.getPhysics())%></strong></td><%} %>
				</tr>
				<tr>
					<th style="text-align: center;"><strong>Chemistry</strong></th>
					<td style="text-align: center;"><strong>100</strong></td>
					<td style="text-align: center;"><strong>35</strong></td>
					<%if(dto.getChemistry()<35){ %>
					
					<td style="text-align: center;"><strong><font color="red">
					<%=DataUtility.getStringData(dto.getChemistry())%></font></strong></td>
					<%} else{%>
					<td style="text-align: center;"><strong>
					<%=DataUtility.getStringData(dto.getChemistry())%></strong></td><%} %>
				<tr>
					<th style="text-align: center;"><strong>Math</strong></th>
					<td style="text-align: center;"><strong>100</strong></td>
					<td style="text-align: center;"><strong>35</strong></td>
					<%if(dto.getMaths()<35){ %>
					
					<td style="text-align: center;"><strong><font color="red">
					<%=DataUtility.getStringData(dto.getMaths())%></font></strong></td>
					<%} else{%>
					<td style="text-align: center;"><strong>
					<%=DataUtility.getStringData(dto.getMaths())%></strong></td><%} %>
				<tr>
					<th style="text-align: center;"><strong>Total</strong></th>
					<td style="text-align: center;"><strong>300</strong></td>
					<td style="text-align: center;"></td>
					<td style="text-align: center;"><strong><%=totalMarks%></strong></td>
				</tr>
		
				
				</tbody>
			    </table>
			    
			    <table border="3" width="100%"  cellpadding=12px cellspacing=".6">
			   <tbody>
				<tr style="background-color: #ffaaff;">
					<th style="text-align: center;"><strong>Result :-</strong></th>
					<%
						if ("PASS".equalsIgnoreCase(result)) {
					%>
					<td align="center"><font color="green" style="font-weight: bold;">
					    <strong><%=result%></strong></font></td>
					<th style="text-align: center;"><strong>Grade :-</strong></th>
					<td align="center"><font color="green" style="font-weight: bold;">
					    <%=grade%></font></td>
					    
					<th style="text-align: center;"><strong>Division :-</strong></th>
					<td style="text-align: center;"><font color="green" style="font-weight: bold;">
					     <%=div%></font></td>
					
					<th style="text-align: center;"><strong>Percentage :-</strong></th>
					<td align="center"><font color="green" style="font-weight: bold;">
					    <%=percentage%>%</font></td>
					    
					<%
						} else {
					%>

					<td align="center"><font color="red" style="font-weight: bold;"><%=result%></font></td>
					<th style="text-align: center;"><strong>Grade :-</strong></th>
					<td align="center"><font color="red" style="font-weight: bold;"><%=grade%></font></td>
					
					<th style="text-align: center;"><strong>Division :-</strong></th>
					<td style="text-align: center;"><font color="red" style="font-weight: bold;">
					     <%=div%></font></td>
					
					<th style="text-align: center;"><strong>Percentage :-</strong></th>
					<td align="center"><font color="red" style="font-weight: bold;">
					    <%=percentage%>%</font></td>
					
				
					<%
						}
					%>
					
					
				</tr>
				</tbody>
				</table>
				</div>
				
					<table align="center">
					<tr>
					<td>
					 
					  <button type="submit" name="operation" class=" form-control btn btn-info" 
			     value="<%=GetMarksheetCtl.OP_RESET%>" style=" width: 150px; height: 47px; font-size: 16px; ">
                 <span style="margin-right: 7px;" class="fa fa-folder-open"></span>  Back </button>
                 
					</td>
				 </tr>	
				</table>
				
			</div></div></div>
			<%
				}
			%>
	
		
		<hr>
	</form>
    <br><br>
   
    <%@include file="Footer.jsp"%>
</body>
</html>