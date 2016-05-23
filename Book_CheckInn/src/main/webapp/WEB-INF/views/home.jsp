<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
<meta name="author" content="templatemo">

<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,800,700,600,300'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
<link rel="stylesheet"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.min.css" />
<!--   <script src= "https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> -->
<script src="resources/travelsite_js/homeValidation.js"></script>
<link rel="stylesheet" href="resources/CSS/bootstrap.min.css">
<link rel="stylesheet" href="resources/CSS/font-awesome.css">
<link rel="stylesheet" href="resources/CSS/animate.css">
<link rel="stylesheet" href="resources/CSS/templatemo_misc.css">
<link rel="stylesheet" href="resources/CSS/templatemo_style.css">
<script src="resources/travelsite_js/ajax.js"></script>
<script src="resources/travelsite_js/script.js"></script>

<script>
	$(function($) {
		$(".datepicker").datepicker({
			dateFormat : 'yy-mm-dd',
			minDate : 0
		});
		$("#anim").change(function($) {
			$(".datepicker").datepicker("fadeIn", "showAnim", $(this).val());
		});
	});
</script>
<script src="resources/JS/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
</head>
<body>

	<div class="site-header">
		<div class="container">
			<div class="main-header">
				<div class="row">
					<div class="col-md-4 col-sm-6 col-xs-10">
						<div class="logo">
							<a href="#"> <img src="resources/images/logo.png"
								alt="travel html5 template" title="travel html5 template">
							</a>
						</div>
						<!-- /.logo -->
					</div>
					<!-- /.col-md-4 -->
					<div class="col-md-8 col-sm-6 col-xs-2">
						<div class="main-menu">
							<ul class="visible-lg visible-md">
								<li class="active"><a href="index.htm">Home</a></li>

								<c:choose>
									<c:when test="${sessionScope.customer!=null}">
										<li><a>Welcome ${sessionScope.customer.firstName}</a></li>
										<li><a href="getBookings.htm">Your Bookings</a></li>
										<li><a href="logout.htm">Logout</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="login.htm">Login</a></li>
									</c:otherwise>
								</c:choose>

							</ul>
							<a href="#" class="toggle-menu visible-sm visible-xs"> <i
								class="fa fa-bars"></i>
							</a>
						</div>
						<!-- /.main-menu -->
					</div>
					<!-- /.col-md-8 -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.main-header -->
			<div class="row">
				<div class="col-md-12 visible-sm visible-xs">
					<div class="menu-responsive">
						<ul>
							<li class="active"><a href="index.htm">Home</a></li>
							<c:choose>
								<c:when test="${sessionScope.customer!=null}">
									<li><a>Welcome ${sessionScope.customer.firstName}</a></li>
									<li><a href="getBookings.htm">Your Bookings</a></li>
									<li><a href="logout.htm">Logout</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="login.htm"></a></li>
								</c:otherwise>
							</c:choose>

						</ul>
					</div>
					<!-- /.menu-responsive -->
				</div>
				<!-- /.col-md-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container -->
	</div>
	<!-- /.site-header -->


	<div class="flexslider">
		<ul class="slides">
			<li>
				<div class="overlay"></div> <img
				src="resources/images/templatemo_slide_1.jpg" alt="Special 1">
				<div class="container">
					<div class="row">
						<div class="col-md-5 col-lg-4">
							<div class="flex-caption visible-lg">
							

	<form name="form1" onsubmit="return getResults()" method="POST"
		enctype="multipart/form-data">
		City: <input type="text" name="address" id="address"
			placeholder="Enter city" />  <input type="button" value="Get" class="btn btn-success" onclick="codeAddress()"><br> Check In date: <input
			type="text" class="datepicker" name="checkindate"
			placeholder="Check In date" id="checkindate" maxlength="10" /><br>
		Check Out date: <input type="text" class="datepicker"
			name="checkoutdate" placeholder="Check Out Date" id="checkoutdate"
			maxlength="10" /><br> Adults:<select name="adult" id="adults">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
		</select><br> <input type="hidden" id="latlong" /> 
		<input type="submit" name="search" class="btn btn-success" value="Search" />
		<b><div id="errorMessage" style="color: red;"></div></b>
	</form>

							</div>
						</div>
					</div>
				</div>
			</li>
			<!-- <li>
				<div class="overlay"></div> <img
				src="resources/images/templatemo_slide_2.jpg" alt="Special 2">
				<div class="container">
					<div class="row">
						<div class="col-md-5 col-lg-4">
							<div class="flex-caption visible-lg">
								
							</div>
						</div>
					</div>
				</div>
			</li>
			<li>
				<div class="overlay"></div> <img
				src="resources/images/templatemo_slide_3.jpg" alt="Special 3">
				<div class="container">
					<div class="row">
						<div class="col-md-5 col-lg-4">
							<div class="flex-caption visible-lg">
								
							</div>
						</div>
					</div>
				</div>
			</li> -->
		</ul>
	</div>
	<!-- /.flexslider -->

	<div class="middle-content" style="visibility: hidden">

		<div class="container">

			<div id="hoteldiv"></div>

		</div>
	</div>

	<div class="partner-list">
		<div class="container">
			<div class="row">
				<div class="col-md-2 col-sm-4 col-xs-6">
					<div class="partner-item">
						<img src="resources/images/partners/partner1.png" alt="">
					</div>
					<!-- /.partner-item -->
				</div>
				<!-- /.col-md-2 -->
				<div class="col-md-2 col-sm-4 col-xs-6">
					<div class="partner-item">
						<img src="resources/images/partners/partner2.png" alt="">
					</div>
					<!-- /.partner-item -->
				</div>
				<!-- /.col-md-2 -->
				<div class="col-md-2 col-sm-4 col-xs-6">
					<div class="partner-item">
						<img src="resources/images/partners/partner3.png" alt="">
					</div>
					<!-- /.partner-item -->
				</div>
				<!-- /.col-md-2 -->
				<div class="col-md-2 col-sm-4 col-xs-6">
					<div class="partner-item">
						<img src="resources/images/partners/partner1.png" alt="">
					</div>
					<!-- /.partner-item -->
				</div>
				<!-- /.col-md-2 -->
				<div class="col-md-2 col-sm-4 col-xs-6">
					<div class="partner-item">
						<img src="resources/images/partners/partner2.png" alt="">
					</div>
					<!-- /.partner-item -->
				</div>
				<!-- /.col-md-2 -->
				<div class="col-md-2 col-sm-4 col-xs-6">
					<div class="partner-item last">
						<img src="resources/images/partners/partner3.png" alt="">
					</div>
					<!-- /.partner-item -->
				</div>
				<!-- /.col-md-2 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container -->
	</div>
	<!-- /.partner-list -->



	<div class="site-footer">
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-sm-4">
					<div class="footer-logo">
						<a href="index.html"> <img src="resources/images/logo.png"
							alt="">
						</a>
					</div>
				</div>
				<!-- /.col-md-4 -->
				<div class="col-md-4 col-sm-4">
					<div class="copyright">
						<span> Copyright &copy; Web Tools
								Travel Site<a href="admin.htm">Admin Login
								</a>
								
								<!--
                            | Design: <a rel="nofollow" href="http://www.templatemo.com" target="_parent">templatemo</a>
                            -->

						</span>
					</div>
				</div>
				<!-- /.col-md-4 -->

				<!-- /.col-md-4 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container -->
	</div>
	<!-- /.site-footer -->


	<script>
		window.jQuery
				|| document
						.write('<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"><\/script>')
	</script>
	<script src="resources/JS/bootstrap.js"></script>
	<script src="resources/JS/plugins.js"></script>
	<script src="resources/JS/main.js"></script>
	<!-- templatemo 409 travel -->
</body>
</html>