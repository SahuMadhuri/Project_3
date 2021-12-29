<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.dto.*"%>

<%@page import="in.co.rays.ctl.MarksheetMeritListCtl"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Marksheet Merit List</title>
<!-- Select All Library -->
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/CheckBox11.js"></script>	

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
    color: #fff; 
}
.info-text {
    color: #33b5e5; 
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
<form action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>" method="post">


 <div class="container-fluid" style="margin-bottom: 50px; margin-top:30px">
       
      
        
        
       
       
       
       <%
	List list = ServletUtility.getList(request);
	System.out.println(list);
        if (list.size()==0){ %>
			
			 <table align="center">
				<tr>
					<td>
			
			<button type="submit" name="operation" class=" form-control btn btn-warning" 
			     value="<%=MarksheetMeritListCtl.OP_BACK%>" style=" width: 150px; height: 47px; font-size: 16px; background-color: gray;">
                 <span style="margin-right: 7px;" class="	fa fa-folder-open"></span>  Back </button>
                 
                 </td>
			
				</tr>
			</table>
                 
			<%}else{ %>
	 
	   <div align="center">    
         <H2 style="color: white">  <span class="fa fa-list-alt"></span><b > <u>Marksheet Merit List</u></b> </H2>
         <hr color="white">
       </div>
	  
	 <div class="col-12 d-flex justify-content-center">
	 
	     <a class="btn btn-success" href="<%=ORSView.JASPER_CTL%>" target="blank">Click Here to
						Print MeritList</a>
	 </div>
	 
	  <br> 
	  
	  <div class="box-body table-responsive" >
       <table  border="3" width="100%"  cellpadding=12px cellspacing=".6" > 
      <thead>
      <tr align="center" style="background-color:#ffaaff">
     <!--  <th class="text-center"><input type="checkbox" id="select_all" name="select">Select All</th>
      -->           <th><strong>S.NO</strong></th>
					<th><strong>Roll No</strong></th>
					<th><strong>Name</strong></th>
					<th><strong>Maths</strong></th>
					<th><strong>Physics</strong></th>
					<th><strong>Chemistry</strong></th>
					<th><strong>Total</strong></th>
					<th><strong>Percentage%</strong></th>
				   <!--  <th>Edit</th> -->
      </tr>
      </thead>
      <%
	                int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;
                   
                    Iterator<MarksheetDTO> it = list.iterator();
                    
                    while (it.hasNext()) {
                    	
                       dto = it.next();
               %>
               <tbody>
				<tr align="center" style="background:white">
					<%--  <td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="<%=dto.getId()%>"></td>
					 --%><td class=""><strong><%=index++%></strong></td>
					<td><strong><%=dto.getRollNo()%></strong></td>
					<td><strong><%=dto.getName()%></strong></td>
					<td><strong><%=dto.getMaths()%></strong></td>
					<td><strong><%=dto.getPhysics()%></strong></td>
					<td><strong><%=dto.getChemistry()%></strong></td>
	                <td><strong><%=(dto.getChemistry()+dto.getMaths()+dto.getPhysics())%></strong></td>
                    <td><strong><%=(((dto.getChemistry()+dto.getMaths()+dto.getPhysics())/3) +"%")%></strong></td>
                    					
                   <%-- <td><a href="MarksheetCtl?id=<%=dto.getId()%>">Edit</a></td> --%>
				</tr>
				</tbody>
				<%
                    }
                %>
			</table>
			
			</div>
			
			
			
			<%-- <table width="100%">
			<tr>
			<td><center><input class="button" type="submit"  name="operation" value="<%=MarksheetMeritListCtl.OP_BACK%>"></center></td>
			
			</td></tr>
			</table> --%>
			
			<table align="center">
				<tr>
					<td>
					 
					  <button type="submit" name="operation" class=" form-control btn btn-info" 
			     value="<%=MarksheetMeritListCtl.OP_BACK%>" style=" width: 150px; height: 47px; font-size: 16px; ">
                 <span style="margin-right: 7px;" class="fa fa-folder-open"></span>  Back </button>
                 
					</td>
			
				</tr>
			</table>
			
				
			<%} %>
			</div>
</form>


<%@include file="Footer.jsp" %>
</body>
</html>