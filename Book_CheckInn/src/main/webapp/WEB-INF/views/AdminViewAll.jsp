<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<jsp:include page="AdminMenu.jsp" />
<br>
<center><h1><b>List of your bookings</b></h1></center>
<br>
<div class="container">
<form action ="" method="">
<ul class="list-group">
 
 <c:forEach var="user" items="${sessionScope.alluserlist}">
<li class="list-group-item">
<div class="row">
<div class="col-sm-8">
<b>First Name:<c:out value="${user.firstName}"></c:out></b><br>
Last Name:<c:out value="${user.lastName}"></c:out><br>
Email Id:<c:out value="${user.emailId}"></c:out><br>
Password:<c:out value="${user.password}"></c:out><br>
</div>
<div class="col-sm-2">
Registered
</div>
<div class="col-sm-2">
</div>
</div>
 </li> 
</c:forEach>
</ul>
</form>
</div>

</body>
</html>