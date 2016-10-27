<!DOCTYPE html>
<html>
<head>
<title>Admin Tools</title>
<%@ include file="resources/partial/head.jsp"%>
<script src="resources/js/admintools.js"></script>
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
		<h1>Admin Tools</h1>
	</div>
	<div class="row">
		<div class="col-lg-3"></div>
		<div id="choiceList" class="list-group col-lg-6">
			<div id="editPeople" class="col-lg-6 list-group-item btn btn-default">
				<span class="glyphicon glyphicon-user"></span>
				<h3 class="list-group-item-heading text-center">Edit People</h3>
			</div>
			<div id="editTypes" class="col-lg-6 list-group-item btn btn-default">
				<span class="glyphicon glyphicon-th-list"></span>
				<h3 class="list-group-item-heading text-center">Edit Types</h3>
			</div>
		</div>
	</div>
	<div id="error" class="col-lg-6 col-lg-offset-3"></div>
	<div id="tools" class="row">
		<div class="col-lg-6 col-lg-offset-3">
			<!-- BEGIN PEOPLE DROP DOWN -->
			<div id="people" style="display: none">
				<div class="col-lg-4">
					<div id="employee" class="btn btn-default btn-block">New
						Employee</div>
				</div>
				<div class="col-lg-4">
					<div id="editemployee" class="btn btn-default btn-block">Edit
						Employee</div>
				</div>
				<div class="col-lg-4">
					<div id="user" class="btn btn-default btn-block">User</div>
				</div>
				<br>
				<br>
				<div class="row" id="empSelect" style="display: none">
					<form id="employeesearch" class="form-horizontal">
						<fieldset>
							<legend>Search for Employee</legend>
							<div class="col-lg-3">
								<label for="filtFirstName" class="control-label">First Name</label><br>
								<input class="form-control" name="filtFirstName" id="FirstName" />
							</div>
							<div class="col-lg-3">
								<label for="filtLastName" class="control-label">Last Name</label><br>
								<input class="form-control" name="filtLastName" id="filtLastName" />
							</div>
							<div class="col-lg-3">
								<label for="filtEmployeeId" class="control-label">Employee
									ID#</label><br> <input class="form-control" name="filtEmployeeId"
									id="filtEmployeeId" />
							</div>
							<div class="col-lg-3">
								<label for="filtPositionId" class="control-label">Position</label><br>
								<select class="form-control" name="filtPositionId" id="filtPositionId"></select>
							</div>
						</fieldset>
					</form>
					<div class="col-lg-10">
					<label for="employeeSelect" class="control-label">Employee</label>
					<select name="employeeSelect" id="employeeSelect"
						class="form-control"></select>
						</div>
						
						<div class="col-lg-2">
								<label for="filter" class="control-label">Filter</label>
								<div id="filter" class="btn btn-primary btn-block"><span style="font-size: 1em;" class="glyphicon glyphicon-download"></span>
								</div>
							</div>
				</div>
				<div class="row" id="userForm" style="display: none">
					<form id="usr">
						<fieldset>
							<legend>User Info</legend>
							<div id="userUsernameContainer" class="form-group"
								style="display: none;"></div>
							<div class="form-group">
								<label for="userUsername" class="col-lg-3 control-label">Username</label>
								<div class="col-lg-6">
									<input type="text" class="form-control" id="userUsername"
										name="userUsername" placeholder="Username" />
								</div>
								<div class="col-lg-3">
									<div id="checkUsername" class="btn btn-primary btn-block">Check
										Availability</div>
								</div>
							</div>
							<br>
							<br>
							<div class="form-group">
								<label for="userPassword" class="col-lg-3 control-label">Password</label>
								<div class="col-lg-9">
									<input type="password" class="form-control" id="userPassword"
										name="userPassword" placeholder="Password" />
								</div>
							</div>
							<br>
							<br>
							<div class="form-group">
								<label for="userRole" class="col-lg-3 control-label">Role</label>
								<div class="col-lg-9">
									<select name="userRole" id="userRole" class="form-control">
										<option value="2">User</option>
										<option value="1">Admin</option>
									</select>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
				<div class="row" id="employeeForm" style="display: none;">
					<form id="emp">
						<fieldset>
							<legend>Employee Info</legend>
							<div class="form-group">
								<label for="empFirstName" class="col-lg-3 control-label">First
									Name</label>
								<div class="col-lg-9">
									<input type="text" class="form-control" id="empFirstName"
										name="empFirstName" placeholder="First Name" />
								</div>
							</div>
							<br>
							<br>
							<div class="form-group">
								<label for="empLastName" class="col-lg-3 control-label">Last
									Name</label>
								<div class="col-lg-9">
									<input type="text" class="form-control" id="empLastName"
										name="empLastName" placeholder="Last Name" />
								</div>
							</div>
							<br>
							<br>
							<div class="form-group">
								<label for="empPosition" class="col-lg-3 control-label">Position</label>
								<div class="col-lg-9">
									<select name="empPosition" id="empPosition"
										class="form-control"></select>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
				<br>
				<div class="col-lg-offset-4 col-lg-4">
					<div id="peopleSubmit" class="btn btn-primary btn-block">Submit</div>
				</div>
			</div>
			<!-- END PEOPLE DROP DOWN -->
			<br>
			<!-- BEGIN TYPE DROP DOWN -->
			<div id="types" style="display: none">
				<div id="choiceList">
					<div class="col-lg-3">
						<div id="weather" class="btn btn-default btn-block">Weather</div>
					</div>
					<div class="col-lg-3">
						<div id="position" class="btn btn-default btn-block">Positions</div>
					</div>
					<div class="col-lg-3">
						<div id="body" class="btn btn-default btn-block">Body Parts</div>
					</div>
					<div class="col-lg-3">
						<div id="injury" class="btn btn-default btn-block">Injury
							Types</div>
					</div>
				</div>
				<br> <br>
				<h3 id="typeDisplay" class="text-center">Select One</h3>
				<br> <br>
				<div>
					<div class="col-lg-9">
						<select id="typeSelect" class="form-control">
							<option value="">Select a tab to begin</option>
						</select>
					</div>
					<div class="col-lg-3">
						<div id="typeDelete" class="btn btn-danger btn-block">Delete</div>
					</div>
				</div>
				<br> <br>
				<div>
					<div class="col-lg-9">
						<input id="typeName" class="form-control" name="typeName"
							placeholder="Create a new type" />
					</div>
					<div class="col-lg-3">
						<div id="typeSubmit" class="btn btn-primary btn-block">Submit</div>
					</div>
				</div>
				<br> <br>
			</div>
			<!-- END TYPE DROP DOWN -->
		</div>
	</div>
</body>
</html>