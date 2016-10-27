<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Injury Reporting</title>
<%@ include file="resources/partial/head.jsp"%>
<script src="resources/js/injuryreporting.js"></script>
<link href="resources/css/jquery.ui.timepicker.css" rel="stylesheet" type="text/css"/>
<script src="resources/js/jquery.ui.timepicker.js"></script>
<script type="text/javascript" src="resources/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.ui.position.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.ui.tabs.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.ui.widget.min.js"></script>
</head>
<body>
<%@include file="resources/partial/navbar.jsp"%>
	<div class="jumbotron">
		<h1>Injury Reporting</h1>
	</div>
	<div id="main" class="row">
		<div class="col-lg-2"></div>
		<div class="col-lg-7 center">
			<form id="injuryreporting" class="form-horizontal">
				<fieldset>
					<legend>Injury Reporting Form</legend>
					<div class="form-group col-lg-12">
						<div>
							<div id="dateOfInjury" class="col-lg-5 datepicker" name="dateOfInjury">
								<label for="dateOfInjury" class="control-label">Date Of Injury</label><br>
							</div>
							<div id="dateReported" class="col-lg-5 datepicker" name="dateReported" >
								<label for="dateReported" class="control-label">Date Reported To Manager</label><br>
							</div>
						</div>
					</div>
					<div class="col-lg-12"><div><div id="errorInvalidDates1"></div></div></div>
					<div class="col-lg-12"><div><div id="errorInvalidDates2"></div></div></div>
					<div class="col-lg-12"><div><div id="errorInvalidDates3"></div></div></div>
					<div class="form-group col-lg-12">
						<div class="col-lg-3">
							<div id="weather">
								<label for="weather" class="control-label">Weather</label><br>
								<select id="weatherSelect" name="weatherSelect">
								</select>
							</div>
						</div>
						<div class="col-lg-3">
							<div id="injuryType">
								<label for="injuryType" class="control-label">Injury Type</label><br>
								<select id="injuryTypeSelect" name="injuryTypeSelect">
								</select> 
							</div>
						</div>
						<div class="col-lg-3">
							<div id="bodyPart">
								<label for="bodyPart" class="control-label">Body Part</label><br>
								<select id="bodyPartSelect" name="bodyPartSelect">
								</select>
							</div>
						</div>
						<div class="col-lg-3">
							<div id="timeInjuryOccurred" class="timepicker">
								<label for="timeInjuryOccurred" class="control-label">Time Injury Occurred</label><br>
								<input type="text" id="timepicker" name="timeInjuryOccurred" />
							</div>
						</div>
					</div>
					<div class="form-group col-lg-12">
						<div class="col-lg-12">
							<label for="description" class="control-label">Description</label>						
							<textarea rows="4" cols="50" class="form-control" id="description" name="description" ></textarea>
						</div>
					</div>
					
				</fieldset>
			</form>
			
			<div class="col-lg-12"><div><div id="errorIncompleteForm"></div></div></div>
			<div class="form-group col-lg-12">
				<div class="col-lg-10"></div>
				<div class="col-lg-2">
					<div id="next" class="btn btn-primary btn-block">Next</div>
				</div>
			</div>
		</div>
	</div>
	<div id="injuryReport" class="row">
		<div class="col-lg-2"></div>
		<div class="col-lg-3 center">
			Injured Employee: <div id="injuredEmployee" class="form-group"></div>
			Injured Employee's ID#: <div id="employeeId" class="form-group"></div>
			Reporting User: <div id="injuryReporter" class="form-group"></div>
			Reporting User's ID#: <div id="reporterId" class="form-group"></div>
			Date Of Injury: <div id="injuryDate" class="form-group"></div>
			Date Reported To Management: <div id="reportedDate" class="form-group"> </div>
			Date Filed: <div id="filedDate" class="form-group"></div>
			Weather Conditions: <div id="weatherCondition" class="form-group"></div>
			Type Of Injury: <div id="typeOfInjury" class="form-group"></div>
			Body Part Affected: <div id="bodyPartAffected" class="form-group"></div>
			Time Injury Occurred: <div id="injuryTime" class="form-group"></div>
			Description: <div id="reportDescription" class="form-group"></div>
			<div id="correctInfo" class="form-group">Is this information correct? </div>
			<div class="col-lg-4">
				<div id="back" class="btn btn-primary btn-block">Back</div>
			</div>
			<div class="col-lg-4">
				<div id="submit" class="btn btn-primary btn-block">Submit</div>
			</div>
			
		</div>
	</div>
	
</body>
</html>