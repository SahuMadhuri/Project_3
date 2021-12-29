<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.dto.*"%>
<%@page import="in.co.rays.ctl.MarksheetListCtl"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Marksheet List</title>
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
</head>
<body >
<jsp:useBean id="dto" scope="request" class="in.co.rays.dto.MarksheetDTO" />
<div>
<%@include file="Header.jsp" %>
</div>




<form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="post">

<div class="container-fluid " style="margin-bottom: 50px; margin-top:30px">
       
      	
        
   
	
 <div align="center">    
         <H2 style="color: white">  <span class="fa fa-list-alt"></span><b > <u>Marksheet List</u></b> </H2>
         <hr color="white">
       </div>
   
          <div class="text-center" >
       
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
			   </div>
			
			<input type="hidden" name="id" value="<%=dto.getId()%>"> 
			<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>"> 
			<input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">

   
   

	<% 
	List rollList=(List)request.getAttribute("rollList");
	List list = ServletUtility.getList(request);
	System.out.println(list);
        if (list.size()==0){ %>
			<table align="center">
				<tr>
					<td>
					 
					  <button type="submit" name="operation" class=" form-control btn btn-warning" 
			     value="<%=MarksheetListCtl.OP_BACK%>" style=" width: 150px; height: 47px; font-size: 16px; background-color: gray;">
                 <span style="margin-right: 7px;" class="	fa fa-folder-open"></span>  Back </button>
                 
					</td>
			
				</tr>
			</table>
						
			<%}else{ %>
	 
	            
  
  <div class="row no-gutters">
 
     <div class="col-sm-1"></div>
      &emsp;&emsp;
	 <!--  <label class="form-check-label" for="check2" style = "max-width:72px"> -->
	 <strong style="color: white">RollNo :~</strong>&nbsp;
	 <% List roleList=(List)request.getAttribute("roleList");%>
	 <div class="col-sm-2"> <%=HTMLUtility.getList("roll", String.valueOf(dto.getId()), rollList)%>
	 </div>
	&emsp;&emsp;
	
	<strong style="color: white">Student Name :~</strong>&nbsp;
    <div class="col-sm-2">		 
    <input placeholder="Enter Student Name" style="background-color: white;max-width:270px"  type="text"   name="name" class="border" 
     value="<%=ServletUtility.getParameter("name", request)%>"> 
	</div>
	
	
	&emsp;
	   
	  <div class="col-sm-2 pl-4" style="max-width: 140px">
	  <button type="submit" name="operation" class=" form-control btn btn-info" style="font-size: 13px"
				 value="<%=MarksheetListCtl.OP_SEARCH%>">
             <span class="fa fa-search"></span> Search </button>
        </div>
        &nbsp;
      <div class="col-sm-2 pl-1" style="max-width: 100px">     
      <button type="submit" name="operation" class=" form-control btn btn-warning"  style="font-size: 13px"
			     value="<%=MarksheetListCtl.OP_RESET%>" >
                 <span class="fa fa-refresh"></span>Reset</button>
      </div>
	 <div class="col-sm-1"></div>
	 <div class="col-sm-2"></div>
	  </div>
  
  
  
  
        <hr color="white">
       
 

<br>
     <table width="100%">
				<tr>
		
		
	
					
	  <td align="right">
      <button type="submit" style="font-size: 13px" name="operation" value="<%=MarksheetListCtl.OP_DELETE%>"
	  class="btn btn-danger"><span class="fa fa-trash"></span> 
	  <%=MarksheetListCtl.OP_DELETE%> </button>
	  </td>	
			
				
			</tr>
			</table>
                
 
 
 

  <div class="box-body table-responsive" >
      <!-- <table class="table table-striped" width="100%"> -->
      
		<table   
		 border="3" width="100%"  cellpadding=12px cellspacing=".6">
				
      <thead>
      <tr align="center" style="background-color:#ffaaff">
      <th class="text-center"><input type="checkbox" id="select_all" name="select"><strong>Select All</strong></th>
                    <th><strong>S.NO</strong></th>
					<th><strong>Roll No</strong></th>
					<th><strong>Name</strong></th>
					<th><strong>Physics</strong></th>
					<th><strong>Chemistry</strong></th>
					<th><strong>Maths</strong></th>
					<th><strong>Total</strong></th>
					<th><strong>Percentage (%)</strong></th>
					<th><strong>Grade</strong></th>
					
					<th><strong>Result</strong></th>
					
					<th><strong>Edit</strong></th>
      </tr>
      </thead>
      <%
	                int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                	int next=DataUtility.getInt(request.getAttribute("nextlist").toString());

                    Iterator<MarksheetDTO> it = list.iterator();
                    while (it.hasNext()) {
                    	
                    	   dto = it.next();
                    	   String grade = null;
   						String result = "<font color='green'>Pass</font>";
   						String div="";
   						long p = DataUtility.getLong(dto.getPhysics()+"");
   						long c = DataUtility.getLong(dto.getChemistry()+"");
   						long m = DataUtility.getLong(dto.getMaths()+"");
   						long totalMarks = (p + c + m);
   						
   						float percentage=(float)totalMarks/3;
                           percentage=percentage*100;
                           percentage=Math.round(percentage);
                           percentage=percentage/100;
                          int avg=(int)(p+c+m)/3;
                           if(avg>=80&&!(p<=32||c<=32||m<=32))
                           {
                               grade="A+";
                           }
                           else if(avg>60 && avg<=80&&!(p<=32||c<=32||m<=32))
                           {
                           	grade="A";
                           }
                           else if(avg>40 && avg<=60&&!(p<=32||c<=32||m<=32))
                           {
                           	grade="B";
                           }
                           else if(avg<40&&!(p<=32||c<=32||m<=32))
                           {
                           		
                           	grade="C";
                           }else{
                           	grade="D";
                           	result = "<font color='red'>Fail</font>";
                           }
   					
   				
               %>
               <tbody>
				<%-- <tr class="<%=((index)%2==0)?"table-info":"table-danger"%>"> --%>
				<tr align="center" style="background:white">
					<td class="text-center"><input type="checkbox" class="checkbox" 
					name="ids" value="<%=dto.getId()%>"></td>
					<td class=""><strong><%=index++%></strong></td>
					<td><strong><%=dto.getRollNo()%></strong></td>
					<td><strong><%=dto.getName()%></strong></td>
					<td><strong><%=dto.getPhysics()<33?dto.getPhysics()+"<font color='red'>*</font>":dto.getPhysics()%></strong></td>
					<td><strong><%=dto.getChemistry()<33?dto.getChemistry()+"<font color='red'>*</font>":dto.getChemistry()%></strong></td>
					<td><strong><%=dto.getMaths()<33?dto.getMaths()+"<font color='red'>*</font>":dto.getMaths()%></strong></td>
					<td><strong><%=totalMarks%></td>
					<td><strong><%=percentage%></td>
					<td><strong><%=grade%></td>
					
					<td><strong><%=result %></td>
					
					
					<%-- <td><a href="MarksheetCtl?id=<%=dto.getId()%>">Edit</a></td> --%>
					
					 <td style="size: 20%; text-align: center;">
					
					<a href="MarksheetCtl?id=<%=dto.getId()%>">
					<span class="fa fa-edit"></span></a>
					
					
				</tr>
					
						<input type="hidden" name="pageNo" value="<%=pageNo%>"> 
			<input type="hidden" name="pageSize" value="<%=pageSize%>">
				
				</tbody>
				<%
                    }
                %>
			</table>
</div>	

         	<table width="100%">
				<tr>
				
					 <td align="left">
  <button type="submit" name="operation" style="font-size: 13px"
	value="<%=MarksheetListCtl.OP_PREVIOUS%>"<%=(pageNo == 1) ? "disabled" : ""%>   class="btn btn-primary">
	<span class="fa fa-chevron-left"></span> <%=MarksheetListCtl.OP_PREVIOUS%> </button> 

			</td>
					<%-- <td>
				
      <button type="submit" style="font-size: 13px" name="operation" value="<%=MarksheetListCtl.OP_DELETE%>"
	  class="btn btn-danger"><span class="fa fa-trash"></span> 
	  <%=MarksheetListCtl.OP_DELETE%> </button>
			</td>	
			
				
							<td>
    <button type="submit" style="font-size: 13px" name="operation" value="<%=MarksheetListCtl.OP_NEW%>"
	class="btn btn-primary"> <span class="fas fa-plus-square"></span> 
	<%=MarksheetListCtl.OP_NEW%> </button>
			</td>
			 --%>
			<td align="center"> <button type="submit" style="font-size: 13px" name="operation" value="<%=MarksheetListCtl.OP_NEW%>"
	class="btn btn-primary"> <span class="fas fa-plus-square"></span> 
	<%=MarksheetListCtl.OP_NEW%> </button></td>
				
                 <td align="right">
        <button type="submit" style="font-size: 13px" name="operation" value="<%=MarksheetListCtl.OP_NEXT%>"<%=(list.size()<pageSize||next==0) ? "disabled" : ""%>  class="btn btn-primary" 
        ><%=MarksheetListCtl.OP_NEXT%> <span class="fa fa-chevron-right"></span> 
        </button></div>

			</td>

			</table>


			
			
			<%} %>
				</div>
</form>

<%@include file="Footer.jsp" %>
</body>
</html>