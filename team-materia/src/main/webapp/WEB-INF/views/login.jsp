<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="partial/head.jsp" %>
<script src="/js/loginpage.js"></script>
<title>Login</title>
</head>
<body>
<%@ include file="partial/logout.jsp" %>
<div class="jumbotron">
<h1 >Login</h1>
</div>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<form action="/login" method="POST" class="form-horizontal" id="form-login">
				<div class="form-group">
					<label for="username">Username</label>
					<input name="username" type="text" class="form-control" />
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<input name="password" type="password" class="form-control" />
				</div>
				<div class="form-group">
			       <div class="col-md-6">
					<input type="button" value="Submit" class="btn btn-primary" id="button-login" />
				</div>
			       <div class="col-md-6 text-right">
			        <a href="register" class="btn btn-primary">New? Register here</a>
			       </div>
			    </div>
			</form>
		</div>
	</div>
	<br/>
	<div class="row">
		<div id="errorPlaceholder" class="col-md-6 col-md-offset-3 text-center text-danger"></div>
	</div>
</div>
</body>
</html>