<!DOCTYPE html>
<html>
<head>
<title>Reporting</title>
<%@ include file="resources/partial/head.jsp"%>
<script src="resources/js/d3.min.js"></script>
<script src="resources/js/reporting.js"></script>
<link href="resources/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="resources/css/bootstrap.icon-large.css" rel="stylesheet" type="text/css"/>
<link href="resources/css/reporting.css" type="text/css" rel="stylesheet" />
</head>
<body>
<%@ include file="resources/partial/navbar.jsp"%>
<div class="jumbotron">
	<h1>Reporting</h1>
</div>
<div class="container">
	<div class="row">
		<div class="col-md-5 col-md-push-4">
		    <div id="chart">
		    </div>	
			<div id="legend">
				<div><h3><i class="icon-large icon-keys"></i><small>Legend</small></h3></div>
				<div id="legend-categories-lg" class="hidden-xs hidden-sm">
					<div id="legend-categories-selection"><span class="glyphicon glyphicon-ok"></span> Approved injury reports</div>
					<div id="legend-categories-others"><span class="glyphicon glyphicon-remove"></span> Injury reports pending approval</div>
				</div>
				<div id="legend-categories-sm" class="visible-xs-block visible-sm-block">
					<div id="legend-categories-selection"><span class="glyphicon glyphicon-ok"></span> Approved injury reports</div>
					<div id="legend-categories-others"><span class="glyphicon glyphicon-remove"></span> Injury reports pending approval</div>
				</div>				
			</div>		    		
		</div>
		<div class="col-md-4 col-md-pull-5">
			<div id="subchart-panel" class="panel panel-default">
				 <div class="panel-heading"><h2 class="text-default text-center">Sub Charts</h2></div>
				 <div id="subchart-panel-body" class="panel-body">
				 	<div id="subchart-message" class="alert alert-info"><span class="glyphicon glyphicon-question-sign"></span> Hover over chart to see sub reports grouped by <strong>BODY PART</strong> and <strong>INJURY TYPE</strong></div>		
					<div id="subcharts">
						<div id="sub-chart-bodypart" class="row"></div>
						<div id="sub-chart-injurytype" class="row"></div>
					</div>
				</div>
			</div>
		</div>		
		<div id="filter-div" class="col-md-3">
			<div class="row">	  
				<div id="chart-type-panel" class="panel panel-default">
				  <div class="panel-heading"><h2 class="text-default text-center">Chart Type</h2></div>
				  <div class="panel-body">
					<div id="chart-type-list" class="list-group-horizontal">
					  <a id="pie-chart" href="#reportList" class="col-md-6 list-group-item active" onclick="toggleChartType('pie')">
						  <span class="chart-type-glyph glyphicon fa fa-pie-chart"></span>
					  </a>					
					  <a id="bar-graph" href="#reportList" class="col-md-6 list-group-item" onclick="toggleChartType('bar')">
						  <span class="chart-type-glyph glyphicon glyphicon-signal"></span>
					  </a>
					</div>				    
				  </div>
				</div>			
			</div>		
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading"><h2 class="text-default text-center"><span class="glyphicon glyphicon-filter"></span> Filters</h2></div>
					<div class="panel-body">
						<div id="filters">
							<label for="body-part">Body Part</label><select id="body-part" class="form-control" onchange="updateChart()"></select>
							<label for="employee-position">Employee Position</label><select id="employee-position" class="form-control" onchange="updateChart()"></select>
							<label for="injury-type">Injury type</label><select id="injury-type" class="form-control" onchange="updateChart()"></select>
							<label for="employee">Employee</label><select id="employee" class="form-control" onchange="updateChart()"></select>
							<label for="weather-type" class="control-label">Weather</label><select id="weather-type" class="form-control" onchange="updateChart()"></select>
							<label for="report-date" class="control-label">Report Date</label><input type="text" id="report-date" class="form-control datepicker" onchange="updateChart()" />
						</div>					
					</div>
				</div>
			</div>
		</div>
	</div>
</div>	
</body>
</html>