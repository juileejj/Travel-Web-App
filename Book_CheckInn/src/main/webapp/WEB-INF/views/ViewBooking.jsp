<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<jsp:include page="menu.jsp" />
<br>
<center><h1><b>List of your bookings</b></h1></center>
<br>
<div class="container">
<form action ="" method="">
<ul class="list-group">
 
 <c:forEach var="booking" items="${sessionScope.bookinglist}">
<li class="list-group-item">
<div class="row">
<div class="col-sm-8">
<c:if test="${booking.bookingStatus eq 'booked' }">
<b><c:out value="${booking.hotel.hotelName}"></c:out></b><br>
Room type:<c:out value="${booking.roomType}"></c:out><br>
Price:<c:out value="${booking.price}"></c:out><br>
Booked from:<c:out value="${booking.bookedFrom}"></c:out><br>
Booked to:<c:out value="${booking.bookedTo}"></c:out><br>

</div>
<div class="col-sm-2">
 <a href="booking.pdf?bookingid=${booking.roomId}" class="btn btn-success" role="button">View Booking</a>
</div>
<div class="col-sm-2">
 <a href="cancelbooking.htm?bookingid=${booking.booking.bookingId}" class="btn btn-danger" role="button">Cancel Booking</a>
</div></c:if>
</div>
 </li> 
</c:forEach>
</ul>
</form>
</div>

</body>
</html>