<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
 <link rel="stylesheet" href="resources/CSS/style.css">
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src="resources/JS/index.js"></script>
 <script src="resources/travelsite_js/homeValidation.js"></script>
</head>
<body>
    <div class="wrapper">
	<div class="container">
		<h1>Welcome</h1>
		
		<form:form class="form" commandName="customer" method="post">
		<form:input path="firstName" type="text" id="firstName" placeholder="First Name" maxlength="20"/><font color="white"><form:errors path="firstName"/></font>
		<form:input path="lastName" type="text" id="lastName" placeholder="Last Name" maxlength="20" /><font color="white"><form:errors path="lastName"/></font>
		<form:input path="emailId" type="text" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" placeholder="Email Id"/><font color="white"><form:errors path="emailId"/></font>
		<form:input path="password" type="password" placeholder="Password" maxlength="10"/>
			<font color="white"><form:errors path="password"/></font>
			<input type="submit" id="signup-button" value="Sign Up"></input>
			<b><div id="error" style="color: red;"></div></b>
		</form:form>
	</div>
	
	<ul class="bg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
</div>
</body>
</html>