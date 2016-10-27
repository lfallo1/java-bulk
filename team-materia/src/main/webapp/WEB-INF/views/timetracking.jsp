<!DOCTYPE html>
<html>
<head>
<%@ include file="partial/head.jsp"%>
<script src="/js/timetracking.js"></script>
<link href="/css/timetracking.css" rel="stylesheet" type="text/css"/>
<title>Time Tracking</title>
</head>
<body>
	<%@ include file="partial/logout.jsp"%>
	<div class="jumbotron">
		<h1>Time Tracking</h1>
	</div>
	<div id="loading-image">
		<img src="/gif/ajax-loader-squares.gif" alt="loading.gif" />
	</div>
	<div class="row">
		<div class="col-lg-1"></div>
		<div class="col-lg-1">
			<div id="previous" class="btn btn-primary btn-block">Previous</div>
		</div>
		<div class="col-lg-7">
			<h2 class="text-center">
				Hours for the week of&nbsp; <span id="beginDate"></span>
				&nbsp;until&nbsp; <span id="endDate"></span>
			</h2>
		</div>
		<div class="col-lg-1">
			<div id="next" class="btn btn-default btn-block">Next</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-2"></div>
		<div class="col-lg-1">
			<div class="row">
				<h3 class="text-center">Sunday</h3>
			</div>
			<div class="row">
				<h3 id="sunday" class="text-center"></h3>
			</div>
		</div>
		<div class="col-lg-1">
			<div class="row">
				<h3 class="text-center">Monday</h3>
			</div>
			<div class="row">
				<h3 id="monday" class="text-center"></h3>
			</div>
		</div>
		<div class="col-lg-1">
			<div class="row">
				<h3 class="text-center">Tuesday</h3>
			</div>
			<div class="row">
				<h3 id="tuesday" class="text-center"></h3>
			</div>
		</div>
		<div class="col-lg-1">
			<div class="row">
				<h3 class="text-center">Wednesday</h3>
			</div>
			<div class="row">
				<h3 id="wednesday" class="text-center"></h3>
			</div>
		</div>
		<div class="col-lg-1">
			<div class="row">
				<h3 class="text-center">Thursday</h3>
			</div>
			<div class="row">
				<h3 id="thursday" class="text-center"></h3>
			</div>
		</div>
		<div class="col-lg-1">
			<div class="row">
				<h3 class="text-center">Friday</h3>
			</div>
			<div class="row">
				<h3 id="friday" class="text-center"></h3>
			</div>
		</div>
		<div class="col-lg-1">
			<div class="row">
				<h3 class="text-center">Saturday</h3>
			</div>
			<div class="row">
				<h3 id="saturday" class="text-center"></h3>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-5"></div>
		<div class="col-lg-1">
			<div id="clockBtn" class="btn btn-primary btn-block">
				<span id="clock"></span>
			</div>
		</div>
	</div>
</body>
</html>
