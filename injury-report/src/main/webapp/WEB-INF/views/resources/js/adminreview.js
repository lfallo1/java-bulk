(function(){
var reportId;
/**
 * Method to do the main serach function for this page.
 */
function doSearch() {
	/* This is the header that we create below in #searchResults
	 * except here we show formatting:
	 * 
	 * 	<div class="col-lg-12">
	 * 		<div class="col-lg-3 text-center">
	 * 			<span class="h2">Report ID#</span>
	 * 		</div>
	 * 		<div class="col-lg-3 text-center">
	 * 			<span class="h2">Employee Name</span>
	 * 		</div>
	 * 		<div class="col-lg-3 text-center">
	 * 			<span class="h2">Date of Injury</span>
	 * 		</div>
	 * 		<div class="col-lg-3 text-center">
	 * 			<span class="h2">Enable/Disable</span>
	 * 		</div>
	 *	</div>
	 */
	$("#searchResults").empty()
		.append('<div class="col-lg-12"><div class="col-lg-3 text-center"><span class="h2">Report ID#</span></div><div class="col-lg-3 text-center"><span class="h2">Employee Name</span></div><div class="col-lg-3 text-center"><span class="h2">Date of Injury</span></div><div class="col-lg-3 text-center"><span class="h2">Enable/Disable</span></div></div>');
	// Then the ajax call
	$.ajax("/adminreview/reports", {
			type : "GET",
			data : {
				reportId : $("#reportId").val(),
				firstName : $("#firstName").val(),
				lastName : $("#lastName").val(),
				date : $("#date").val(),
				status : $("#active").val()
			}
		}).done(function(data) {
				for (var i = 0; i < data.length; i++) {
					// This is where we create the buttons
					$("#searchResults").append(
							/* Object nesting should be as follows
							 * 	<div col-lg-12> --- holds everything
							 * 		<div col-lg-9> --- link button holder
							 * 			<div btn-block id="link#"> (# == reportId)
							 * 				<div col-lg-4>
							 * 					<h4> reportId </h4>
							 * 				</div>
							 *				<div col-lg-4>
							 *					<h4> lastName, firstName </h4>
							 *				</div>
							 *				<div col-lg-4>
							 *					<h4> dateOfReport </h4>
							 *				</div>
							 *			</div>
							 *		</div>
							 *		<div col-lg-3>
							 *			<div btn-block id="enable#"> (# == reportId)
							 *				<h4> Enable/Disable </h4> (only displays one word)
							 *			</div>
							 *		</div>
							 *	</div>
							 */
							'<div class="col-lg-12"><div class="col-lg-9"><div id="link'
								+ data[i].reportId
								+ '" class="btn btn-default btn-block form-control link"><div class="col-lg-4 text-center"><span class="h4">'
								+ data[i].reportId
								+ '</span></div><div class="col-lg-4 text-center"><span class="h4">'
								+ data[i].employee.lastName
								+ ', '
								+ data[i].employee.firstName
								+ '</span></div><div class="col-lg-4 text-center"><span class="h4">'
								+ data[i].dateOfReport
								+ '</span></div></div></div><div class="col-lg-3 text-center"><span class="h4">'
								+ '<div id="enable'
								+ data[i].reportId
								+ '" class="btn btn-primary btn-block enabler">'
								+ (data[i].enabled ? 'Disable' : 'Enable')
								+ '</div>' + '</span></div></div>');
					// set this object's link button to an action on click
					$("#link" + data[i].reportId)
						.click(function() {
							// do an ajax call to get the specific report data
							$.ajax("/adminreview/"+ $(this).attr("id").replace("link", ""), {
								type : "GET"
							}).done(function(data) {
								reportId = data.reportId;
								var a = data.timeOfInjury.split(":");
								var b = data.dateOfReport.split("-");
								var c = data.dateReportedToManagement.split("-");
								$("#emp").append(data.employee.lastName + ", " 
									+ data.employee.firstName);
								$("#empId").append(data.employee.employeeId);
								$("#user").append(data.reporter.employee.lastName + ", " 
									+ data.reporter.employee.firstName);
								$("#userId").append(data.reporter.userId);
								$("#dateToMgmt").val(c[1] + "/" + c[2] + "/" + c[0]);
								$("#dateFile").val(b[1] + "/" + b[2] + "/" + b[0]);
								$("#weather").val(data.weather.weatherId);
								$("#injury").val(data.injuryType.typeId);
								$("#bodyPart").val(data.bodyPart.bodyPartId);
								$("#time").val(a[0] + ":" + a[1]);
								$("#description").val(data.description);
								$("#comments").val(data.approverComments);
								// switch which page we're looking at
								$("#mainPage").hide("blind");
								$("#overlay").show("blind");
							});
						});
					// Set a function for this button's enable/disable
					$("#enable" + data[i].reportId).click(function() {
						// this put call will switch the status to the opposite of what it is now
						// this.setEnabled(!this.getEnabled);
						$.ajax("/adminreview/enable/" + $(this).attr("id").replace("enable", ""),{
							type : "PUT"
						}).done(function() {
							$("#search").click();
						});
					});
				}
		});
}
function init() {
	// AJAX all injury types (borrowed from the admin tools webservice)
	$.ajax("/admintools/injury", {
		type : "GET"
	}).done(function(data) {
		for (var i = 0; i < data.length; i++) {
			$("#injury").append(
				'<option value="' + data[i].typeId + '">'
					+ data[i].typeName + '</option>');
		}
	});
	// AJAX all weather types (borrowed from the admin tools webservice)
	$.ajax("/admintools/weather", {
		type : "GET"
	}).done(function(data) {
		for (var i = 0; i < data.length; i++) {
			$("#weather").append(
				'<option value="' + data[i].weatherId + '">'
					+ data[i].weatherCondition + '</option>');
		}
	});
	// AJAX all body parts (borrowed from the admin tools webservice)
	$.ajax("/admintools/body", {
		type : "GET"
	}).done(function(data) {
		for (var i = 0; i < data.length; i++) {
			$("#bodyPart").append(
				'<option value="' + data[i].bodyPartId + '">'
					+ data[i].bodyPartName + '</option>');
		}
	});
}
/**
 * Submits the form on the proofing part of the page
 */
function submitForm() {
	var str = validForm();
	if (str == "") {
		$.ajax("/adminreview/report/" + reportId, {
			type : "PUT",
			data : {
				dateReportedToManagement : $("#dateToMgmt").val(),
				dateOfReport : $("#dateFile").val(),
				weatherId : $("#weather").val(),
				typeId : $("#injury").val(),
				bodyPartId : $("#bodyPart").val(),
				timeOfInjury : $("#time").val(),
				description : $("#description").val(),
				approverComments : $("#comments").val()
			}
		}).done(function() {
			alert("submitted successfully");
		});
	} else {
		alert(str);
	}
}
/**
 * Basic validation, the return string will already have line breaks ("\n").
 * 
 * @returns Error messages or an empty string
 */
function validForm() {
	var str = "";
	if (!dateCheck($("#dateToMgmt").val()) || !dateCheck($("#dateFile").val())) {
		str += "You've entered a date in the future, are you magic or wrong?\n";
	}
	if ($("#dateToMgmt").val().length != 10
		|| $("#dateToMgmt").val().replace(/[0-9]{2}\/[0-9]{2}\/[0-9]{4}/, "").length > 0) {
		str += "Date reported to management is incorrectly formatted (just use the date-picker).\n";
	} 
	if ($("#dateFile").val().length != 10
		|| $("#dateFile").val().replace(/[0-9]{2}\/[0-9]{2}\/[0-9]{4}/, "").length > 0) {
		str += "Date Filed is incorrectly formatted (just use the date-picker).\n";
	}
	if ($("#time").val().length != 5
		|| $("#time").val().replace(/[0-9]{2}:[0-9]{2}/, "").length > 0) {
		str += "Time of Injury is incorrectly formatted (just use the time-picker).\n";
	}
	if ($("#description").val().length > 0 
		&& /[^ A-Za-z0-9/(),.:]/.test($("#description").val())) {
		str += "Description may only consist of alphanumeric characters, parenthesis, commas, periods, backslashes and colons.\n";
	}
	if ($("#comments").val().length == 0
		|| /[^ A-Za-z0-9/(),.:]/.test($("#comments").val())) {
		str += "Approval comments must be entered and consist of alphanumeric characters, parenthesis, commas, periods, backslashes and colons.\n";
	}
	return str;
}
/**
 * Converts the date string into a date, checks it against now. 
 * @param {String} Date formatted "mm/dd/yyyy"
 * @returns {Boolean} true if it's valid
 */
function dateCheck(date) {
	var arr = date.split("/");
	if (Date.now() > Date.UTC(arr[2], parseInt(arr[0], 10) - 1, arr[1])) {
		return true;
	}
	return false;
}
/**
 * Clearing out all the data from the proofing page when returning
 * to the main page
 */
function back() {
	reportId = null;
	$("#emp").empty();
	$("#empId").empty();
	$("#user").empty();
	$("#userId").empty();
	$("#dateToMgmt").val("");
	$("#dateFile").val("");
	$("#weather").val("");
	$("#injury").val("");
	$("#bodyPart").val("");
	$("#time").val("");
	$("#description").val("");
	$("#comments").val("");
	$("#mainPage").show("blind");
	$("#overlay").hide("blind");
}
/**
 * The stuff to do/bind on document load.
 */
$(document).ready(function() {
	init();
	$("#date").datepicker();
	$("#dateToMgmt").datepicker();
	$("#dateFile").datepicker();
	$("#time").timepicker();
	$("#search").click(doSearch);
	$("#formSubmit").click(submitForm);
	$("#back").click(back);
});
})();