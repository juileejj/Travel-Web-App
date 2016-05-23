<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta charset="utf-8">

<meta name="description" content="">
<meta name="viewport" content="width=device-width">
<meta name="author" content="templatemo">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,800,700,600,300'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="resources/CSS/bootstrap.min.css">
<link rel="stylesheet" href="resources/CSS/font-awesome.css">
<link rel="stylesheet" href="resources/CSS/animate.css">
<link rel="stylesheet" href="resources/CSS/templatemo_misc.css">
<link rel="stylesheet" href="resources/CSS/templatemo_style.css">

<script src="resources/JS/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
</head>
<body>
	<!--[if lt IE 7]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->

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
								<li><a href="index.htm">Home</a></li>
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
							<li><a href="index.htm">Home</a></li>
								<c:choose>
									<c:when test="${sessionScope.customer!=null}">
										<li>Welcome ${sessionScope.customer.firstName}</li>
										<li><a href="logout.htm">Logout</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="login.htm"></a></li>
									</c:otherwise>
								</c:choose>
								<li><a href="about.html">About Us</a></li>
								<li><a href="contact.html">Contact</a></li>
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
	  <div class="page-top" id="templatemo_contact">
        </div> <!-- /.page-header -->
</body>