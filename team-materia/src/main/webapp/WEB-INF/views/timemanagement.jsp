<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="partial/head.jsp"%>
<link type="text/css" rel="stylesheet" href="/css/jquery.timepicker.css" />
<script src="/js/jquery.timepicker.js"></script>
<script src="/js/timemanagement.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Time Management</title>
</head>
<body>
<%@include file="partial/logout.jsp" %>
	<div class="jumbotron">
		<h1>Time Management</h1>
	</div>
	<div class="row">
	<div class="col-lg-2"></div>
	<div class="col-lg-7" id="error"></div>
	</div>
	<div id=main class="row">
		
		<div class="col-lg-1"></div>
		<div class="col-lg-7 center">
			<div class="form-horizontal">
				<fieldset>
					<legend>Time Management</legend>
					<div class="form-group">
						<label for="user" class="col-lg-2 control-label">User</label>
						<div class="col-lg-5">
							<select class="form-control" id="user" name="userId">
								<option value="">Last, First</option>
							</select>
						</div>
						<div class="col-lg-3">
							<div id="inactiveButton" class="btn btn-block btn-danger">Inactive Hidden</div>
						</div>
					</div>
					<div class="form-group">
						<label for="week" class="col-lg-2 control-label">Week</label>
						<div class="col-lg-5">
							<select class="form-control" id="week" name="week">
								<option value="">Select Week</option>
							</select>
						</div>
					</div>
					<div class="row">
					<div class="col-lg-2"></div>
					<div class="form-group col-lg-10">
						<div style="display: inline-block;" class="col-lg-4">
							<div id="forClockIn">
								<label for="clockIn" class="control-label text-left">Clock
									In Time</label>
							</div>
						</div>
						<div style="display: inline-block;" class="col-lg-4">
							<div id="forClockOut">
								<label for="clockOut" class="control-label">Clock
									Out Time</label> 
							</div>
						</div>
						<div style="display: inline-block;" class="col-lg-4">
							<div id="forClockDate">
								<label for="clockDate" class="control-label">Date</label>
							</div>
						</div>
					</div>
					</div>
					<form id="appendRows">
					
					</form>
				</fieldset>
			</div>
			<div class="col-lg-4"></div>
			<div class="col-lg-2">
			<div id="submit" class="btn btn-primary btn-block">Update</div>
			</div>
			<div class="col-lg-2">
			<a href="/adminsplash" class="btn btn-danger btn-block">Cancel</a>
			</div>
		</div>
	</div>
</body>
</html>