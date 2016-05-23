<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="resources/CSS/style.css">
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <!-- <script src="resources/JS/index.js"></script> -->
</head>
<body>

    <div class="wrapper">
	<div class="container">
		
		
			<form action="login.htm" method="POST">
		<input path="emailId" name="emailId" type="text" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" placeholder="Email Id"/>
		<input path="password" name="password" type="password" placeholder="Password" maxlength="10"/>
			
			<input type="submit" id="login-button" value="Login"></input>
			<a href="signup.htm">Create account here</a>
			</form>
		
		
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
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<!-- 
        <script src="resources/JS/index.js"></script>

        -->
</body>
</html>