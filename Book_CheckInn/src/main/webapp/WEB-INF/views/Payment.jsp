<%@page import="com.neu.JSONPojo.JSONDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
function getrooms()
{
	var singleprice= document.getElementById("price").value;
	var adults= document.getElementById("adults").value;
	var rooms =document.getElementById("rooms").value;
	var tprice =document.getElementById("totalprice").value;
	//var price =document.getElementById("price").value;
	if(adults>0&&adults<=2)
		{
		document.getElementById("rooms").value= 1;
		document.getElementById("totalprice").value=1*singleprice;
		}
	else if(adults>2&&adults<=4)
	{
		document.getElementById("rooms").value= 2;
		document.getElementById("totalprice").value=2*singleprice;
		}
	else if(adults>4&&adults<=6)
	{
		document.getElementById("rooms").value= 3;
		document.getElementById("totalprice").value=3*singleprice;
		}
	else 
		{
		return false;
		}
	}



</script>
<script>
	$(function($) {
		$(".datepicker").datepicker({
			dateFormat : 'yy-mm-dd',

		});
		$("#anim").change(function($) {
			$(".datepicker").datepicker("fadeIn", "showAnim", $(this).val());
		});
	});
</script>
</head>
<body>
<jsp:include page="menu.jsp" />
<form action="payment.htm" method="POST" class="form-group">
<br>
<div class ="col-sm-2"></div>
	<div class ="col-sm-8">
<div class ="jumbotron">
<h2><b>Booking details:</b></h2>
Select room type:
 <select class="form-control" name="roomtype" id="roomtype">
    <option value="Deluxe">Deluxe</option>
    <option value="Suite">Suite</option>
   No of adults:
   
  </select>
Adults:<select class="form-control" name="adult" id="adults" onchange="getrooms()">
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
									</select>
									<% 
									JSONDetails json=(JSONDetails)session.getAttribute("hoteljson");
									String checkin = json.getStayDates().getCheckInDate();
									String checkout=json.getStayDates().getCheckOutDate();
									%>
	Check-in Date:<input type="text" class="datepicker" name="checkindate" id="checkindate" value="<%=checkin %>"  disabled/><br/>
	Check-out Date: <input type="text" class="datepicker" name="checkoutdate" id="checkoutdate" value="<%=checkout %>"  disabled /><br/>								

</div>

<div class="jumbotron">
<h2><b>Total Price:</b></h2>

<input type="text" id="totalprice" value="<%= request.getAttribute("price") %>" name="totalprice" disabled/>
<h2><b>No of Rooms:</b></h2>
<input type="text" id="rooms" name="noofrooms" value="1" disabled/>

<input type="hidden" name="price" id="price" value="<%= request.getAttribute("price") %>"/>
</div>
<script src="resources/travelsite_js/creditCardValidation.js"></script>
<div class="jumbotron">
<h2><b>Payment details:</b></h2>
Card Holder Name:
<input type ="text" class="form-control" style="text-transform:uppercase" maxlength="30" name="cardholdername" id="cardholdername"/><br>
Card type:<img src="http://www.credit-card-logos.com/images/visa_mastercard_credit-card-logos/mc_vs_accpt_h_023_gif.gif"/>
<select name="cardtype" id="cardtype" class="form-control">
<option value="Credit">Credit Card</option>
<option value="Debit">Debit Card</option>
</select>
<br>
Card Number:
<input type="text" id="cardnumber" name="cardnumber" pattern="[0-9]{13,16}" maxlength="16" class="form-control">
Card Expiration:
<select name='expireMM' class="form-control" id='expireMM'>
    <option value='01'>Janaury</option>
    <option value='02'>February</option>
    <option value='03'>March</option>
    <option value='04'>April</option>
    <option value='05'>May</option>
    <option value='06'>June</option>
    <option value='07'>July</option>
    <option value='08'>August</option>
    <option value='09'>September</option>
    <option value='10'>October</option>
    <option value='11'>November</option>
    <option value='12'>December</option>
</select> 
<select name='expireYY' class="form-control" id='expireYY'>
    <option value='16'>2016</option>
    <option value='17'>2017</option>
    <option value='18'>2018</option>
    <option value='19'>2019</option>
    <option value='20'>2020</option>
    <option value='21'>2021</option>
    <option value='22'>2022</option>
</select>
CCV:
<input type="text" name="ccv" id="ccv" pattern="[0-9]{3}" maxlength="3" class="form-control">
<center><input type="submit" class="btn btn-success" value="Confirm Booking" onclick="return validate()"/></center>
 </div>
</div>
</form>

</body>
</html>