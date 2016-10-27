<!DOCTYPE html>
<html>
<head>
<title>Admin Review</title>
<%@include file="resources/partial/head.jsp"%>
<script src="/resources/js/adminreview.js"></script>
<link href="resources/css/jquery.ui.timepicker.css" rel="stylesheet" type="text/css"/>
<script src="resources/js/jquery.ui.timepicker.js"></script>
<script type="text/javascript" src="resources/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.ui.position.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.ui.tabs.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.ui.widget.min.js"></script>
</head>
<body>
	<%@include file="resources/partial/navbar.jsp"%>
	<div id="mainPage">
		<div class="jumbotron">
			<h1>Admin Review</h1>
		</div>
		<div class="row">
			<div id="searchContainer" class="col-lg-10 col-lg-offset-1">
				<form id="employeesearch" class="form-horizontal">
					<fieldset>
						<legend>Search for Employee</legend>
						<div class="col-lg-4">
							<label for="reportId" class="control-label">Report ID#</label><br>
							<input name="reportId" id="reportId" class="form-control" />
						</div>
						<div class="col-lg-4">
							<label for="firstName" class="control-label">Employee
								First Name</label><br> <input name="firstName" id="firstName"
								class="form-control" />
						</div>
						<div class="col-lg-4">
							<label for="lastName" class="control-label">Employee
								LastName</label><br> <input name="lastName" id="lastName"
								class="form-control" />
						</div>
						<div class="col-lg-4">
							<label for="date" class="control-label">Date of Injury</label> <input
								id="date" name="date" class="form-control" />
						</div>
						<div class="col-lg-4">
							<label for="active" class="control-label">Status</label> <select
								name="active" id="active" class="form-control">
								<option value="true">Enabled</option>
								<option value="false">Disabled</option>
							</select>
						</div>
						<div class="col-lg-4">
							<label for="search" class="control-label">Search</label>
							<div id="search"
								class="btn btn-primary btn-block vertical-align-center">
								<span class="glyphicon glyphicon-search"></span>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="btn-block" style="border-bottom: 1px solid #ddd;">&nbsp;</div>
				<br>
			</div>
		</div>
		<div class="row">
			<div id="searchResults" class="col-lg-10 col-lg-offset-1"></div>
		</div>
	</div>
	<!-- HIDDEN OVERLAY LAYER! -->
	<div id="overlay" style="display: none;">
		<div class="jumbotron">
			<h1>Proofing</h1>
		</div>
		<div class="row">
			<div class="col-lg-10 col-lg-offset-1">
				<form><fieldset>
				<div class="form-group">
					<div class="col-lg-3">
						<label for="emp" class="control-label">Injured Employee:</label>
					</div>
					<div class="col-lg-9">
						<div id="emp" class="form-control"></div>
					</div>
				</div>

				<div class="form-group">
					<div class="col-lg-3">
						<label for="empId" class="control-label">Injured
							Employee's ID#: </label>
					</div>
					<div class="col-lg-9">
						<div id="empId" class="form-control"></div>
					</div>
				</div>

				<div class="form-group">
					<div class="col-lg-3">
						<label for="user" class="control-label">Reporting User: </label>
					</div>
					<div class="col-lg-9">
						<div id="user" class="form-control"></div>

					</div>
				</div>

				<div class="form-group">
					<div class="col-lg-3">
						<label for="userId" class="control-label">Reporting User's
							ID#: </label>
					</div>
					<div class="col-lg-9">
						<div id="userId" class="form-control"></div>
					</div>
				</div>

				<div class="form-group">
					<div class="col-lg-3">
						<label for="dateToMgmt" class="control-label">Date
							Reported To Management: </label>
					</div>
					<div class="col-lg-9">
						<input id="dateToMgmt" class="datepicker form-control" />

					</div>
				</div>

				<div class="form-group">
					<div class="col-lg-3">
						<label for="dateFile" class="control-label">Date Filed: </label>
					</div>
					<div class="col-lg-9">
						<input id="dateFile" class="datepicker form-control" />

					</div>
				</div>

				<div class="form-group">
					<div class="col-lg-3">
						<label for="weather" class="control-label">Weather
							Conditions: </label>
					</div>
					<div class="col-lg-9">
						<select id="weather" class="form-control"></select>

					</div>
				</div>

				<div class="form-group">
					<div class="col-lg-3">
						<label for="injury" class="control-label">Type Of Injury:
						</label>
					</div>
					<div class="col-lg-9">
						<select id="injury" class="form-control"></select>
					</div>
				</div>

				<div class="form-group">
					<div class="col-lg-3">
						<label for="bodyPart" class="control-label">Body Part
							Affected: </label>
					</div>
					<div class="col-lg-9">
						<select id="bodyPart" class="form-control"></select>

					</div>
				</div>

				<div class="form-group">
					<div class="col-lg-3">
						<label for="time" class="control-label">Time Injury
							Occurred: </label>
					</div>
					<div class="col-lg-9">
						<input id="time" class="timepicker form-control" />

					</div>
				</div>

				<div class="form-group">
					<div class="col-lg-3">
						<label for="description" class="control-label">Description: </label>
					</div>
					<div class="col-lg-9">
						<textarea rows="4" cols="50" id="description" class="form-control"></textarea>

					</div>
				</div>

				<div class="form-group">
					<div class="col-lg-3">
						<label for="comments" class="control-label">Approver
							Comments:</label>
					</div>
					<div class="col-lg-9">
						<textarea rows="4" cols="50" id="comments" class="form-control"></textarea>
					</div>
				</div>
				</fieldset></form>
				<div class="form-group">
					<div class="col-lg-2 col-lg-offset-8">
						<div id="formSubmit" class="btn btn-primary btn-block">Update</div>
					</div>
					<div class="col-lg-2">
						<div id="back" class="btn btn-danger btn-block">Cancel</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>