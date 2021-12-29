 <%-- <%@page import="java.util.TimerTask"%> --%>
<%@page import="in.co.rays.ctl.TimeTableCtl"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Time Table</title>
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
.paddingclass{
padding-top: 10px;
}
</style>
<style type="text/css">
/* .setForm{
padding-top: 5%;
padding-left: 25%;
width: 130%
}
 */
 .button{
border-radius:10px;padding:10px;color:white;font-size:20px;background-color:#00cc88
}

.textfield{
border: 1px solid #8080803b;height: 38px; padding-left: 6px;
}

</style>
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
		$("#datepicker").datepicker({
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
</script>
</head>
<body>
<div>
<%@include file="Header.jsp"%>
</div>

<jsp:useBean id="dto"  class="in.co.rays.dto.TimeTableDTO" scope="request" />

<form action="<%=ORSView.TIMETABLE_CTL%>" method="post">
<!-- getting role list for preload -->

<%
 List courseList= (List)request.getAttribute("courseList"); 
 List sujectList= (List)request.getAttribute("sujectList");
%> 

    
    
    <main>
        
        <!--MDB Forms-->
        <div class="container mt-4">

            
            <!-- Grid row -->
            <div class="row">
                <!-- Grid column -->
               <div class="col-md-4"></div>
               
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body">
                        
                        <%
                          long id=DataUtility.getLong(request.getParameter("id"));
                          System.out.println("PASS---->"+id);
                        %>
                       
                           <h3 class="text-center default-text py-3"><u><%=(id==0)? "Add TimeTable": "Update TimeTable" %></u></h3>
                            <hr color="black"> 
                          
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
                            
                           <h6  style="color: #fff ">Course<font color="red">*</font></h6>
                             <div class="input-group">
                             <div class="input-group-prepend">
                             <span class="input-group-text"><i style="width: 17px" class="fa fa-book"></i></span>
                              </div>
                           	<%=HTMLUtility.getList("course", String.valueOf(dto.getCourseId()), courseList)%>
                               </div>
								<font color="red"> <%=ServletUtility.getErrorMessage("course", request) %></font>

                         
                         <h6 class=" paddingclass" style="color: #fff ">Subject<font color="red">*</font></h6>
                           <div class="input-group">
                             <div class="input-group-prepend">
                             <span class="input-group-text"><i style="width: 17px" class="fa fa-book"></i></span>
                                      </div>
                           	<%=HTMLUtility.getList("subject", String.valueOf(dto.getSubId()), sujectList)%>
                                      </div>
								<font color="red"> <%=ServletUtility.getErrorMessage("subject", request) %></font>

                         
                         <h6 class=" paddingclass" style="color: #fff " >Semester<font color="red">*</font></h6>
                         
                         <%LinkedHashMap<String,String> map=new LinkedHashMap<String,String>();
                            map.put("1st", "1st");
							map.put("2nd", "2nd");
							map.put("3rd", "3rd");
							map.put("4th", "4th");
							map.put("5th", "5th");
							map.put("6th", "6th");
							map.put("7th", "7th");
							map.put("8th", "8th");
							
                           String semester=HTMLUtility.getList("semester",dto.getSemester(), map);
                           %>
                          
                    		<div class="input-group">
                             <div class="input-group-prepend">
                             <span class="input-group-text"><i style="width: 17px" class="fa fa-hourglass"></i></span>
                                      </div>
                           	<%=semester%>
                                      </div>
								<font color="red"> <%=ServletUtility.getErrorMessage("semester", request) %></font>

                
                           <h6 class=" paddingclass" style="color: #fff " >Exam Date<font color="red">*</font></h6>
                            <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space;"><i class="fa fa-calendar"></i></span>
                                 </div>
                        <input readonly="readonly" type="text" id="datepicker1" placeholder="Enter examdate" class="form-control border" style="background-color:white;"
                         name="examdate" value="<%=DataUtility.getDateString(dto.getExamDate())%>">
                               
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("examdate",request) %> </font>
                           
                           
                                              
                          
                          
                          
                          
                          
                          
                           <h6 class=" paddingclass" style="color: #fff " >Exam Time<font color="red">*</font></h6>

          <% LinkedHashMap<String,String> map1=new LinkedHashMap<String,String>();
            map1.put("08:00AM to 11:00AM", "08:00AM to 11:00AM");
			map1.put("12:00PM to 3:00PM", "12:00PM to 3:00PM");
			map1.put("3:00PM to 6:00PM", "3:00PM to 6:00PM");
                           %>
                         
                    
                    
                    		<div class="input-group">
                             <div class="input-group-prepend">
                             <span class="input-group-text"  ><i class="fa fa-clock"></i></span>
                                      </div>
                           	<%=HTMLUtility.getList("examTime", dto.getExamTime(), map1)%>
                                      </div>
								<font color="red"> <%=ServletUtility.getErrorMessage("examTime", request) %></font>
                    
                    
                     
                           
                           
                   
                   
                           <h6 class=" paddingclass" style="color: #fff " >Description<font color="red">*</font></h6>                           
                          
                        	<div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" style="background-color:white-space;"><i class="fa fa-book"></i></span>
                                 </div>
                             <input type="text" placeholder="Enter description" class="form-control border" 
                               style="background-color:white;" name="description" value="<%=DataUtility.getStringData(dto.getDescription())%>">
                                </div>
							<font color="red"><%=ServletUtility.getErrorMessage("description",request) %> </font>
                           
                            
                         
                          <%if (id>0){ %>
                          
                           <div class="text-center paddingclass">
							
								 <button type="submit" class="btn btn-success" name="operation"
									style="font-size: 13px"	value="<%=TimeTableCtl.OP_UPDATE%>">
										<span class="fa fa-check-square"></span> Update
									</button>
									 
									
							 <button type="submit" class="btn btn-warning" name="operation"
									style="font-size: 13px"	value="<%=TimeTableCtl.OP_CANCEL%>">
										<span class="fa fa-refresh"></span> Cancel
									</button>
							
							</div>
                          
                          
                          <%}else{ %> 
                            <div class="text-center paddingclass">
							
								 <button type="submit" class="btn btn-success" name="operation"
									style="font-size: 13px"	value="<%=TimeTableCtl.OP_SAVE%>">
										<span class="fa fa-check-square"></span> Save
									</button>
									 
									
							  <button type="submit" class="btn btn-warning" name="operation"
									style="font-size: 13px"	value="<%=TimeTableCtl.OP_RESET%>">
										<span class="fa fa-refresh"></span> Reset
									</button>
							
							</div>
                          
                             <%} %>
                             </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4"></div>
               </div>
             



<div>
<br><br>
</div>
</form>
</body>
<%@include file="Footer.jsp" %>
</html> 


