<!DOCTYPE html>
<html>
<head>
<%@include file="partial/head.jsp" %>
<title>Admin Splash</title>
<style>
.glyphicon{
	font-size:4em;
}
</style>
</head>
<body>
<%@ include file="partial/logout.jsp" %>
<div class="jumbotron">
<h1>Admin Splash</h1>
</div>
<div id="container" class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
		<a href="/accountconfig" class="btn btn-default col-lg-4 list-group-item" id="account-config-link"><span class="glyphicon glyphicon-cog"></span><h1>Account Config</h1></a>
		<a href="/timemanagement" class="text-center btn btn-default col-lg-4 list-group-item" id="time-management-link"><span class="glyphicon glyphicon-time"></span><h1>Time Management</h1></a>
		<a href="/reporting" class="text-center btn btn-default col-lg-4 list-group-item" id="reporting-link"><span class="glyphicon glyphicon-stats"></span><h1>Reporting</h1></a>
	</div>
</div>
</body>
</html>
