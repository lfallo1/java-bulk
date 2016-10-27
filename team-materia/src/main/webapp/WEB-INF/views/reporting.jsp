<!DOCTYPE html>
<html>
<head>
<title>Reporting</title>
<%@ include file="partial/head.jsp" %>
<script src="/js/reporting.js"></script>
<link href="/css/reporting.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@ include file="partial/logout.jsp" %>
<div class="jumbotron">
<h1>Reporting</h1>
</div>
<div class="container">
	<div class="row">	  
		<div id="reportList" class="list-group-horizontal">
		  <a id="TotalHrsThumbnail" href="#reportList" class="col-md-4 list-group-item" onclick="totalHoursRpt()">
			  <span class="glyphicon glyphicon-user"></span>
		      <h3 class="list-group-item-heading text-center">Total hours by week</h3>
		      <p class="list-group-item-text">Total hours worked in a given week. Can optionally filter results by employee.</p>
		  </a>
		  <a id="OvertimeThumbnail" href="#reportList" class="col-md-4 list-group-item" onclick="overtimeRpt()">
			  <span class="glyphicon glyphicon-time"></span>
		      <h3 class="list-group-item-heading text-center">Overtime</h3>
		      <p class="list-group-item-text">All employees who worked for 8 or more hours in a single day.</p>
		  </a>
		  <a id="AbsentThumbnail" href="#reportList" class="col-md-4 list-group-item" onclick="absentRpt()">
			  <span class="glyphicon glyphicon-ban-circle"></span>
		      <h3 class="list-group-item-heading text-center">Absent</h3>
		      <p class="list-group-item-text">All employees, absent from work on a weekday for the selected week.</p>
		  </a> 		    
		</div>
	</div>
</div>
<br/>
<hr/>
<div class="container">
	<div id="select-lists" class="row">
		<div class="col-md-8 col-md-offset-2">
			<div class="panel panel-primary">
				<div id="panel-heading-div" class="panel-heading">
					<h3 class="panel-title">Filters <span class="glyphicon glyphicon-filter"></span></h3>
				</div>
				<div class="panel-body">	
					<div id="select-week-dropdown-placeholder" class="col-md-5 col-md-offset-1">
					</div>
					<div id="select-user-dropdown-placeholder" class="col-md-5">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<br/>
<div class="container">
	<div class="row">
		<div id="report-placeholder" class="col-md-8 col-md-offset-2">
		</div>
	</div>
</div>
</body>
</html>
