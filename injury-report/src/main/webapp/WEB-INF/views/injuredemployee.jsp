<!DOCTYPE html>
<html>
<head>
<title>Employee Search</title>
<%@ include file="resources/partial/head.jsp"%>
<script src="resources/js/injuredemployee.js"></script>
</head>
<body>
<%@include file="resources/partial/navbar.jsp"%>
	<div class="jumbotron">
		<h1>Employee Search</h1>
	</div>
	<div class="row">
		<div class="col-lg-1"></div>
		<div class="col-lg-10">
			<form id="employeesearch" class="form-horizontal">
				<fieldset>
					<legend>Search for Employee</legend>
					<div class="col-lg-3">
						<label for="firstName" class="control-label">Employee
							First Name</label><br> <input name="firstName" id="firstName" />
					</div>
					<div class="col-lg-3">
						<label for="lastName" class="control-label">Employee Last
							Name</label><br> <input name="lastName" id="lastName" />
					</div>
					<div class="col-lg-3">
						<label for="employeeId" class="control-label">Employee ID#</label><br>
						<input name="employeeId" id="employeeId" />
					</div>
					<div class="col-lg-3">
						<div id="lookUp" class="btn btn-primary btn-block">Look Up</div>
					</div>
				</fieldset>
			</form>
			<br>
			<div id="employeeList" class="row">
			</div>
		</div>
	</div>
</body>
</html>