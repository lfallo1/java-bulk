/**
 * Date helper utility
 */
var DateHelper = {
	SECOND: 1000,
	MINUTE: 60000,
	HOUR: 3600000,
	DAY: 86400000,
	WEEK: 604800000
};

/**
 * Report Index Constants
 */
var ReportIndexHelper = {
	TOTAL_HOURS_REPORT : 0,
	OVERTIME_REPORT : 1,
	ABSENT_REPORT : 2
};

/**
 * UserHelper object that will store all users,
 * after the initial getAllUsers ajax call
 */
var UserHelper = {
	users : []
};

/**
 * global vars:
 * beginDate- used to mark the beginning of the week
 * endDate- used to mark the end of the week
 * weekdays- an array storing five date objects (Mon-Fri)
 */
var beginDate;
var endDate;
var weekdays = [];

/**
 * When page loads call init and begin page initiazliation
 */
$(document).ready(function(){
	init();
});

/**
 * populate user and date dropdowns
 */
function init(){
	populateDateDropdown();
	populateUserDropdown();
	$('#filter-title-placeholder').empty().append("<h2><small>Filter</small></h2>");
}

/**
 * helper method that sets the beginWeek and endWeek global vars
 * to Date objects, representing the beginning and end of week.
 * @param date
 */
function generateWeekConstraints(date){
	var original = new Date(date.year, (date.monthOfYear-1), date.dayOfMonth);
	beginWeek = new Date(original.setDate(original.getDate()-original.getDay()));
	endWeek = new Date(original.setDate(original.getDate()+6));	
}

/**
 * given any date, this will populate the weekdays array with
 * five dates, Mon - Fri, for the week in which the passed in date falls
 * @param date
 */
function generateWeekdayConstraints(date){
	var original = new Date(date.year, (date.monthOfYear-1), date.dayOfMonth);
	for(var i = 0; i < 5; i++){
		weekdays[i] = new Date(original.setDate(original.getDate()-original.getDay()+ i + 1));
	}
}

/**
 * Convert a joda date passed in from java to a javascript date object
 * @param date
 * @returns {Date}
 */
function jodaToJs(date){
	return new Date(date.year, (date.monthOfYear-1), date.dayOfMonth);
}

/**
 * Format javascript date object in MM/DD/YYYY format
 * @param date
 * @returns {String}
 */
function formatJavascriptDate(date){
	return (date.getUTCMonth() + 1) + "/" + date.getUTCDate() + "/" + date.getUTCFullYear();
}

/**
 * Format JodaTime date in MM/DD/YYYY format
 * @param date
 * @returns {String}
 */
function formatJodaDate(date){
	var dayOfMonth = date.dayOfMonth < 10 ? "0" + date.dayOfMonth : date.dayOfMonth;
	return date.monthOfYear + "/" + dayOfMonth + "/" + date.year;
}

/**
 * Generate the html to be placed inside the user details popover item
 * @param user
 * @returns {String}
 */
function generatePopoverData(user){
	var popoverData = '';
	popoverData += '<h3>' + user.firstName +' '+ user.lastName + '<small> ' + user.username + '</small></h3>';
	popoverData += '<hr>';
	popoverData += '<span class=&quot;glyphicon glyphicon-time&quot;></span><h5>Created <small>'+ formatJodaDate(user.created) + '</small></h5></br>';
	popoverData += '<span class=&quot;glyphicon glyphicon-envelope&quot;></span>'+ user.email + '</br>';
	popoverData += user.active ? '<span class=&quot;glyphicon glyphicon-ok-sign&quot;></span>Active: '+ user.active : '<span class=&quot;glyphicon glyphicon-remove-sign&quot;></span>Active: '+ user.active;
	return popoverData;
}

/**
 * Toggle the active class for the three report links
 * @param index
 */
function toggleActive(index){
	var thumbnails = $('#reportList a');
	for(var i = 0; i < thumbnails.length; i++){
		if(i == index){
			if(!$(thumbnails[i]).hasClass('active')){
				$(thumbnails[i]).addClass('active');
			}
		}
		else{
			if($(thumbnails[i]).hasClass('active')){
				$(thumbnails[i]).removeClass('active');
			}
		}
	}
}

/**
 * On completion of each report generating, this
 * should run. It will the bind the correct event handler
 * to the onchange event for both select-lists.
 * It will goto the specified anchor tag, and will
 * set the popover
 * @param index
 */
function finalizeReportView(index){
	toggleActive(index);
	$('#select-list-dates').unbind('change');
	$('#select-list-users').unbind('change');
	//Total Hours Week Rpt
	if(index == 0){
		$('#select-list-dates').change(totalHoursRpt);
		$('#select-list-users').change(totalHoursRpt);		
	}
	//Overtime Report
	else if(index == 1){
		$('#select-list-dates').change(overtimeRpt);
		$('#select-list-users').change(overtimeRpt);		
	}
	//Absent Report
	else if(index == 2){
		$('#select-list-dates').change(absentRpt);
		$('#select-list-users').change(absentRpt);			
	}
	
	$('.userPopoverData').popover();
	location.href="#reportList";
	//Clear hash from url
	history.pushState('', document.title, window.location.pathname);
}

/**
 * Populate the date dropdown a list of date/week spans. There will be an
 * option for each week in which there is atleast one valid UserTime entry
 */
function populateDateDropdown(){
	$.ajax("/reporting/weeks").done(function(dates){
		var datesHtml = '';
		datesHtml += '<div class="input-group">';
		datesHtml += '<span class="input-group-addon glyphicon glyphicon-calendar"></span>';		
		datesHtml += '<select id="select-list-dates" class="form-control">';
		for(var i = 0; i < dates.length; i++){
			generateWeekConstraints(dates[i]);
			datesHtml += '<option value="'+ Date.UTC(dates[i].year, dates[i].monthOfYear-1, dates[i].dayOfMonth+1) + '">'+ formatJavascriptDate(beginWeek) + " to "  + formatJavascriptDate(endWeek) +'</option>';
		}
		datesHtml += '</select>';
		datesHtml += '</div>';
		$('#select-week-dropdown-placeholder').empty().append(datesHtml);
		$('#select-list-dates').prop("disabled", true);
	});	
}

/**
 * Populate user select list with all users from database
 */
function populateUserDropdown(){
	$.ajax("/reporting/users", { type : "GET"
	}).done(function(users){
		UserHelper.users = users;
		var usersHtml = '';
		usersHtml += '<div class="input-group">';
		usersHtml += '<span class="input-group-addon glyphicon glyphicon-user"></span>';
		usersHtml += '<select id="select-list-users" class="form-control">';
		usersHtml += '<option value="-1">All Users</option>';
		for(var i = 0; i < users.length; i++){
			usersHtml += '<option value="'+ users[i].username +'">'+ users[i].lastName + ', ' + users[i].firstName +'</option>';
		}
		usersHtml += '</select>';
		usersHtml += '</div>';
		$('#select-user-dropdown-placeholder').empty().append(usersHtml);
		$('#select-list-users').prop("disabled", true);
	});	
}

/**
 * Total Hours report
 */
function totalHoursRpt(){
	$('#select-list-users').prop("disabled", false);
	$('#select-list-dates').prop("disabled", false);
	var user = $('#select-list-users').val() == -1 ? null : $('#select-list-users').val(); 
	$.ajax("/reporting/totalhours", {
		type : "GET",
		data : {
			date : Number($('#select-list-dates').val()),
			username : user
		}
	}).done(function(data){
		//data returned is a list of UserTimeMillis objects
		var totalHoursHtml = '';
		var allUsers = user == null ? $('#select-list-users option') : $('#select-list-users').val();
		//Create a tempArray to store each user that is in the result list.
		var usersWithResults = [];
		totalHoursHtml += '<table id="totalHoursRptTable" class="table table-hover table-bordered table-striped text-center"><thead><tr>';
		totalHoursHtml += '<th>User</th>';
		totalHoursHtml += '<th>Total hours</th>';
		totalHoursHtml += '</tr></thead><tbody>';
		for(var i = 0; i < data.length; i++){
			usersWithResults.push(data[i].userTime.user.username);
			totalHoursHtml += '<tr>';
			totalHoursHtml += '<td class="td-user"><span class="userPopoverData user" tabindex="0" data-html="true" data-content="'+ generatePopoverData(data[i].userTime.user) + '" rel="popover" data-placement="top" data-original-title="<span class=&quot;glyphicon glyphicon-user&quot;></span>User Information" data-trigger="focus">'+ data[i].userTime.user.firstName + ' ' + data[i].userTime.user.lastName +'</span></td>';
			totalHoursHtml += '<td>'+ (data[i].millis/DateHelper.HOUR).toFixed(2) +'</td>';
			totalHoursHtml += '</tr>';
		}
		
		//NOTE: The global userlist MINUS the tempArray list above, will be used to
		//get remaining users & populate the remaining rows with 0.00 hours
		//--------------------------------------------------------------------------
		//If the user == null, this means that no user filter was specified. And all
		//users should be displayed.
		if(user == null){
			for(var j = 0; j < UserHelper.users.length; j++){
				inList = false;
				for(var k = 0; k < usersWithResults.length; k++){
					if(usersWithResults[k] == UserHelper.users[j].username){
						inList = true;
					}
				}
				
				if(!inList){
					totalHoursHtml += '<tr>';
					totalHoursHtml += '<td class="td-user"><span class="userPopoverData" tabindex="0" data-html="true" data-content="'+ generatePopoverData(UserHelper.users[j]) + '" rel="popover" data-placement="top" data-original-title="<span class=&quot;glyphicon glyphicon-user&quot;></span>User Information" data-trigger="focus">'+ UserHelper.users[j].firstName + ' ' + UserHelper.users[j].lastName +'</span></td>';
					totalHoursHtml += '<td>0.00</td>';
					totalHoursHtml += '</tr>';
				}
			}
		}
		//Else, if there was a user specified and no results were returned,
		//find the user in the UserHelper.users list, and display the information
		else{
			if(data.length == 0){
				var i = 0;
				var found = false;
				while(!found && i < UserHelper.users.length){
					if(UserHelper.users[i].username == allUsers){
						totalHoursHtml += '<tr>';
						totalHoursHtml += '<td class="td-user"><span class="userPopoverData" tabindex="0" data-html="true" data-content="'+ generatePopoverData(UserHelper.users[i]) + '" rel="popover" data-placement="top" data-original-title="<span class=&quot;glyphicon glyphicon-user&quot;></span>User Information" data-trigger="focus">'+ UserHelper.users[i].firstName + ' ' + UserHelper.users[i].lastName +'</span></td>';
						totalHoursHtml += '<td>0.00</td>';
						totalHoursHtml += '</tr>';
						found = true;
					}						
					i++;
				}				
			}
		}
		totalHoursHtml += '</tbody></table>';
		$('#report-placeholder').empty().append(totalHoursHtml);
		finalizeReportView(ReportIndexHelper.TOTAL_HOURS_REPORT);
	});		
}

/**
 * Overtime report
 */
function overtimeRpt(){
	//enable date select-list & disable user select-list
	$('#select-list-dates').prop("disabled", false);
	$('#select-list-users').prop("disabled", true);
	$.ajax("/reporting/overtime", {
		type : "GET",
		data : {
			date : Number($('#select-list-dates').val())
		}
	}).done(function(data){
		//returns a list of UserTimeMillis objects.
		//Create a table to display the user and hours
		//for each record
		var totalHoursHtml = '';
		if(data.length == 0){
			totalHoursHtml += '<div class="alert alert-warning">There were no results for the selected week</div>'; 
		}
		else{
			totalHoursHtml += '<table class="table table-hover table-bordered table-striped text-center"><thead><tr>';
			totalHoursHtml += '<th>Username</th>';
			totalHoursHtml += '<th>Date</th>';
			totalHoursHtml += '<th>Total hours</th>';
			totalHoursHtml += '</tr></thead><tbody>';
			for(var i = 0; i < data.length; i++){
				totalHoursHtml += '<tr>';
				totalHoursHtml += '<td class="td-user"><span class="userPopoverData" tabindex="0" data-html="true" data-content="'+ generatePopoverData(data[i].userTime.user) + '" rel="popover" data-placement="top" data-original-title="<span class=&quot;glyphicon glyphicon-user&quot;></span>User Information" data-trigger="focus">'+ data[i].userTime.user.firstName + ' ' + data[i].userTime.user.lastName +'</span></td>';
				totalHoursHtml += '<td>'+ formatJodaDate(data[i].userTime.clockDate) +'</td>';
				totalHoursHtml += '<td>'+ (data[i].millis/3600000).toFixed(2) +'</td>';
				totalHoursHtml += '</tr>';
			}
			totalHoursHtml += '</tbody></table>';
		}
		$('#report-placeholder').empty().append(totalHoursHtml);
		finalizeReportView(ReportIndexHelper.OVERTIME_REPORT);
	});
}

/**
 * Absent Report
 */
function absentRpt(){
	$('#select-list-dates').prop("disabled", false);
	$('#select-list-users').prop("disabled", false);
	var user = $('#select-list-users').val() == -1 ? null : $('#select-list-users').val(); 
	$.ajax("/reporting/absent", {
		type : "GET",
		data : {
			date : Number($('#select-list-dates').val()),
			username : user
		}
	}).done(function(data){
		//html to be inserted into the report placeholder
		var absentHtml = '';
		//current username of the user being processed
		var currentUsername = '';
		//main counter for the data that was returned from the ajax call
		var counter = 0;
		//weekday counter
		var weekdayCounter = 0;
		//array to store a distinct list of users that had results from the query
		var usersWithResults = [];
		 
		//Table Header
		absentHtml += '<table id="absent-report-table" class="table table-bordered text-center"><thead><tr>';
		absentHtml += '<th class="text-center"></th>';
		absentHtml += '<th class="text-center">Monday</th>';
		absentHtml += '<th class="text-center">Tuesday</th>';
		absentHtml += '<th class="text-center">Wednesday</th>';
		absentHtml += '<th class="text-center">Thursday</th>';
		absentHtml += '<th class="text-center">Friday</th>';
		absentHtml += '</tr></thead><tbody>';
		//If There were any results from the query
		if(data.length != 0){
			//get the first entry from the result list, and send its date as a parmater
			//to the generateWeekdayConstraints method, to set the Mon-Fri array 
			generateWeekdayConstraints(data[0].userTime.clockDate);
			//add rows while the results counter is less than the number of results returned
			while(counter < data.length){
				//set the currentUsername to the username of the current result
				currentUsername = data[counter].userTime.user.username;
				//add that username to the usersWithResults array
				usersWithResults.push(currentUsername);
				absentHtml += '<tr>';
				absentHtml += '<td class="td-user"><span class="userPopoverData" tabindex="0" data-html="true" data-content="'+ generatePopoverData(data[counter].userTime.user) + '" rel="popover" data-placement="top" data-original-title="<span class=&quot;glyphicon glyphicon-user&quot;></span>User Information" data-trigger="focus">'+ data[counter].userTime.user.firstName + ' ' + data[counter].userTime.user.lastName +'</span></td>';
				//Set the weekday counter to 0, before iterating over the weekdays
				weekdayCounter = 0;
				//to avoid null pointer, add the counter<data.length qualification
				while(weekdayCounter < 5 && counter < data.length){
					//if the day object in the next record is the matching weekday, mark as NOT absent,
					//and move results counter to next record
					if(jodaToJs(data[counter].userTime.clockDate).getTime() == weekdays[weekdayCounter].getTime() && data[counter].userTime.user.username == currentUsername){
						absentHtml += '<td class="alert alert-success"><span class="absentHours">'+ (data[counter].millis/DateHelper.HOUR).toFixed(2) +'</span></td>';
						counter++;
					}
					//else if the weekday does NOT match, mark as absent
					else{
						absentHtml += '<td class="alert alert-danger"><span class="glyphicon glyphicon-remove"></span></td>';
					}
					//increment weekday counter
					weekdayCounter++;
				}
				//if the loop has exited due to no more results in the query,
				//check to see if there were any days remaining in the weekday counter,
				//and add to the grid if necessary
				if(counter == data.length){
					while(weekdayCounter < 5){
						absentHtml += '<td class="alert alert-danger"><span class="glyphicon glyphicon-remove"></span></td>';
						weekdayCounter++;
					}					
				}
				//End the row
				absentHtml += '</tr>';
			}
		}
		
		//Add additional rows for employees that had no results
		//for the week, and populate rows to show as absent.
		//If there was no user filter...
		if(user == null){
			//for each user in the master users list
			for(var j = 0; j < UserHelper.users.length; j++){
				inList = false;
				//Loop through the usersWithResults list and check to see
				//if that user was in the results list. if it was, set inList to true
				for(var k = 0; k < usersWithResults.length; k++){
					if(usersWithResults[k] == UserHelper.users[j].username){
						inList = true;
					}
				}
				//if inList was not set to true, add an entry for that user
				if(!inList){
					absentHtml += '<tr>';
					absentHtml += '<td class="td-user"><span class="userPopoverData" tabindex="0" data-html="true" data-content="'+ generatePopoverData(UserHelper.users[j]) + '" rel="popover" data-placement="top" data-original-title="<span class=&quot;glyphicon glyphicon-user&quot;></span>User Information" data-trigger="focus">'+ UserHelper.users[j].firstName + ' ' + UserHelper.users[j].lastName +'</span></td>';
					for(var l = 0; l < 5; l++){
						absentHtml += '<td class="alert alert-danger"><span class="glyphicon glyphicon-remove"></span></td>';
					}
					absentHtml += '</tr>';
				}
			}
		}
		//If there was a user filter and the result set returned 0 results,
		//find the user object in the master users list and add the entry
		//to the table
		else{
			if(data.length == 0){			
				var i = 0;
				var found = false;
				while(!found && i < UserHelper.users.length){
					if(UserHelper.users[i].username == $('#select-list-users').val()){
						absentHtml += '<tr>';
						absentHtml += '<td class="td-user"><span class="userPopoverData" tabindex="0" data-html="true" data-content="'+ generatePopoverData(UserHelper.users[i]) + '" rel="popover" data-placement="top" data-original-title="<span class=&quot;glyphicon glyphicon-user&quot;></span>User Information" data-trigger="focus">'+ UserHelper.users[i].firstName + ' ' + UserHelper.users[i].lastName +'</span></td>';
						for(var l = 0; l < 5; l++){
							absentHtml += '<td class="alert alert-danger"><span class="glyphicon glyphicon-remove"></span></td>';
						}
						absentHtml += '</tr>';							
						found = true;
					}						
					i++;
				}	
			}
		}
		absentHtml += '</tbody></table>';
		$('#report-placeholder').empty().append(absentHtml);
		finalizeReportView(ReportIndexHelper.ABSENT_REPORT);
	});		
}
