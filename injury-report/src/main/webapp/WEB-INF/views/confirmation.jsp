<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="resources/partial/head.jsp"%>
<link href="resources/css/confirmation.css" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmation</title>
</head>
<body>
<%@include file="resources/partial/navbar.jsp"%>
<div class="jumbotron">
	<h1 id="submit-success"><span class="glyphicon glyphicon-ok"></span> Injury report submitted successfully</h1>	
	<h3>Please write down the ID number for your records</h3>
	<p class="text-danger">Injury Report ID#<%= request.getAttribute("CONFIRMATION_NUMBER") %></p>
	<br/>
	<h3><small>You have been logged out. Click <a href="/">here</a> to return to the login page</small></h3>
</div>
</body>
</html>