<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin splash</title>
<%@ include file="resources/partial/head.jsp"%>
<style>
.glyphicon {
	display: block;
	text-align: center;
	font-size: 4em;
}
</style>
</head>
<body>
<%@include file="resources/partial/navbar.jsp"%>
<div class="jumbotron">
	<h1>Admin Splash</h1>
</div>
<div class="row">
	<div id="choiceList" class="list-group col-lg-10 col-lg-offset-1">
		<a href="/adminreview" id="review" class="col-lg-4 list-group-item btn btn-default">
			<span class="glyphicon glyphicon-pencil"></span>
			<br>
			<span class="list-group-item-heading text-center h1">Review</span>
		</a>
		<a href="/adminreport" id="reports" class="col-lg-4 list-group-item btn btn-default">
			<span class="glyphicon glyphicon-list-alt"></span>
			<br>
			<span class="list-group-item-heading text-center h1">Reports</span>
		</a>
		<a href="/admintools" id="tools" class="col-lg-4 list-group-item btn btn-default">
			<span class="glyphicon glyphicon-wrench"></span>
			<br>
			<span class="list-group-item-heading text-center h1">Tools</span>
		</a>
	</div>
</div>
</body>
</html>