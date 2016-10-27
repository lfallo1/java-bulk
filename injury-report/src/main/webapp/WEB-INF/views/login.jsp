<!DOCTYPE html>
<html>
<head>
<script src="/resources/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="/resources/js/jquery-ui.min.js" type="text/javascript"></script>
<script src="/resources/js/bootstrap.min.js" type="text/javascript" ></script>
<script src="/resources/js/login.js" type="text/javascript"></script>
<link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="/resources/css/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
<link href="/resources/css/login.css" rel="stylesheet" type="text/css"/>
<link type="text/css" rel="stylesheet" href="/resources/css/navbar.css"/>
<title>Login Page</title>
</head>
<body onload='document.f.j_username.focus();'>
<%@ include file="resources/partial/navbar.jsp"%>
<%
String last_username = "";
if(session.getAttribute("LAST_USERNAME")!=null){
	last_username = (String)session.getAttribute("LAST_USERNAME");
}
%>
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3 text-center">
				<h1>Login</h1>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row"><!-- Start form -->
			<div class="col-lg-6 col-lg-offset-3">
				<form class="form-horizontal" role="form" name='f' action="j_spring_security_check" method='POST' onsubmit="return checkUsernameEmpty();">
					<div class="form-group">
						<label for="inputUsername" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-10">
							<input id="inputUsername" class="form-control" type='text' name='j_username' value='<%=last_username %>' placeholder="Username">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-10">
							<input id='inputPassword' class="form-control" type='password' name='j_password' placeholder="password" />
						</div>					
					</div>				
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input name="submit" class="btn btn-default" type="submit" value="Login" />
				            <div class="form-group">
				                <label for="_spring_security_remember_me">Remember Me?</label>
				                <input type="checkbox" name="_spring_security_remember_me" />
				            </div>									
						</div>
					</div>
				</form>
			</div>
		</div><!-- End form -->
		<div class="row"><!-- Start errors -->
			<div id="errorMessage" class="col-lg-6 col-lg-offset-3 text-center">
				<% if(session.getAttribute("error_type")!=null) {
					String message = (String)session.getAttribute("error_type");
					if ("User is disabled".equals(message) || session.getAttribute("accountLocked")!=null) { %>
						<div class="alert alert-danger">This account has been temporarily locked</div>
					<% } else { %>
						<div class="alert alert-danger">The username and password you entered was invalid</div>
					<% }
					session.invalidate();
				} %>	
			</div>
		</div><!-- End errors -->	
	</div>
</body>
</html>