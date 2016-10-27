/**
 * Date helper holds numbers relevant to milliseconds of date and time
 */
var DateHelper = {
	SECOND: 1000,
	MINUTE: 60000,
	HOUR: 3600000,
	DAY: 86400000,
	WEEK: 604800000,
};
// used to hold a date for the sole purpose of knowing the timezone offset
var iHateJavascriptDates = new Date();
/**
 * Check to see if the user still exists in the select box
 * @param option the original option (representing a user)
 */
function checkForUser(option) {
	var opt = $("option");
	for (var i = 0; i < opt.length; i++) {
		if ($(opt[i]).val() == option) {
			$("#user").val(option);
			continue;
		}
	}
	if ($("#user").val() != option) {
		clearBoxes();
	}
}
/**
 * AJAX call to get all users
 */
function getAllUsers() {
	var user = $("#user").val();
	$.ajax("/accountconfig/users", {
		type : "GET",
		data : {
			withInactive : true
		}
	}).success(function(data) {
		$("#user").empty().append("<option value=\"\">SelectOne</option>")
		for (var i = 0; i < data.length; i++) {
			$("#user").append("<option value=" + data[i].userId + ">" 
					+ data[i].lastName + ", " + data[i].firstName +
					"</option>"); 
		}
		$("#user").val(user);
	});
}
/**
 * AJAX call to get active users
 */
function getActiveUsers() {
	var user = $("#user").val();
	$.ajax("/accountconfig/users", {
		type : "GET",
		data : {
			withInactive : false
		}
	}).success(function(data) {
		$("#user").empty().append("<option value=\"\">SelectOne</option>");
		for (var i = 0; i < data.length; i++) {
			$("#user").append("<option value=" + data[i].userId + ">" 
					+ data[i].lastName + ", " + data[i].firstName + 
					"</option>");
		}
		checkForUser(user);
	});
}
/**
 * Function to handle the toggling nature of the inactive button
 */
function toggleInactiveButton(){
	if($("#inactiveButton").html() == "Inactive Hidden"){
		$("#inactiveButton").empty().append("Inactive Shown").removeClass("btn-danger").addClass("btn-success");
		getAllUsers();
	} else {
		$("#inactiveButton").empty().append("Inactive Hidden").removeClass("btn-success").addClass("btn-danger");
		getActiveUsers();
	}
}
/**
 * AJAX call to return all the weeks that a user has worked 
 */
function getUserWeeks(){
	$("#appendRows").empty();
	$.ajax("/timemanagement/weeks/"+$("#user").val(),{
		type:"GET"
	}).done(function(data){
		$("#week").empty().append("<option value=\"\">Select One</option>");
		for(var i=0;i<data.length;i++){
			// added twelve hours to this date object to avoid issues with daylight savings time
			var nextWeek = new Date(data[i].year, data[i].monthOfYear-1, data[i].dayOfMonth, 12);
			nextWeek.setUTCDate(nextWeek.getDate()+6);
			// Object's value is set to the number of milliseconds that translates into the date
			$("#week").append("<option value="+Date.UTC(data[i].year, data[i].monthOfYear-1, data[i].dayOfMonth)+">"
				+ "Week of "
				+ data[i].monthOfYear + "/" + data[i].dayOfMonth + "/" + data[i].year
				+ " Until "
				+ (nextWeek.getMonth()+1) + "/" + nextWeek.getDate() + "/" + nextWeek.getFullYear()
				+"</option>");
		}
	});
}
/**
 * AJAX call to return all user time object to the page for a given week
 */
function getUserClockedTime(){
	$.ajax("/timemanagement/hours/"+$("#user").val(),{
		type:"GET",
		data:{week:$("#week").val()}
	}).done(function(data){
		// clearing out existing date objects
		$("#appendRows").empty();
		// used to circumvent the timepicker Date() dependencies
		var quick = [];
		// sorts them by date and then by clock in time
		data.sort(function(a,b){return dateSort(a,b)});
		for(var i=0;i<data.length;i++){
			// setting these allows us to use time-picker appropriately - touch of a workaround, but better than trying to handle all the parsing myself
			quick[i*2] = data[i].clockIn.millisOfDay + (iHateJavascriptDates.getTimezoneOffset()*DateHelper.MINUTE);
			// making sure that the clock out is not null before setting this one
			quick[i*2+1] = data[i].clockOut==null? null : data[i].clockOut.millisOfDay + (iHateJavascriptDates.getTimezoneOffset()*DateHelper.MINUTE);
			/* creates a new row-----------------------------------------------------------------------------------------sets the clock in wrapper----------------------sets the clock out wrapper-----------------------sets the clock date wrapper------------------------places the currently hidden and empty error message---------*/
			$("#appendRows").append("<div id='rower"+i+"' class='row'><div class='col-lg-2'></div><div class='col-lg-10'><div id='rowerIn"+i+"' class='col-lg-4'></div><div id='rowerOut"+i+"' class='col-lg-4'></div><div id='rowerDate"+i+"' class='col-lg-4'></div></div><div id='error"+i+"' class=\"alert alert-dismissable alert-danger\" style=\"display:none;\"></div></div>");
			/* places an input for this id-------------------READONLY----------HIDDEN */
			$("#rower"+i).append('<input type="number" readonly="readonly" hidden="hidden" name="userTimeList['+i+'].id" value="'+ data[i].id +'"/>');
			/*creates the clockIn input------------------------TIMEPICKER------------ID used below after the for loop */
			$("#rowerIn"+i).append('<input class="form-control timepicker list'+i+'" id="'+(i*2)+'" name="userTimeList['+i+'].clockIn" />');
			/*creates the clockOut input-----------------------TIMEPICKER------------ID used below after the for loop */
			$("#rowerOut"+i).append('<input class="form-control timepicker list'+i+'" id="'+(i*2+1)+'" name="userTimeList['+i+'].clockOut" />');
			/*creates the clockDate input---------READONLY-------------------------------------------------------------------------------------- check to see if the month has two numbers--------? print ----------------------- : add a zero to the front ----------------------check to see if the date has two digits ---------? print ---------------------- : add a zero to the front --------------------print the year*/
			$("#rowerDate"+i).append('<input readonly="readonly" class="form-control list'+i+'" name="userTimeList['+i+'].clockDate" value="'+ (String(data[i].clockDate.monthOfYear).length == 2 ? data[i].clockDate.monthOfYear : ('0'+data[i].clockDate.monthOfYear)) + "-" + (String(data[i].clockDate.dayOfMonth).length == 2 ? data[i].clockDate.dayOfMonth : ('0'+data[i].clockDate.dayOfMonth)) + "-" + data[i].clockDate.year +'"/>');
		}
		/* set the time pickers ------------------FORMAT----------------------for each picker-------------NULL check ------------  ? empty string  :  the date set above (quick[]) using the id*/
		$(".timepicker").timepicker({timeformat: "hh:mm p"}).each(function(){$(this).timepicker('setTime',quick[$(this).attr("id")]==null? "" : new Date(quick[$(this).attr("id")]))});
	});
}
/**
 * method used to sort
 * @param a {JodaTime} the first date
 * @param b {JodaTime} the second date
 * @returns {Number} sort variable
 */
function dateSort(a,b){
	var c = Date.UTC(a.clockDate.year,a.clockDate.monthOfYear,a.clockDate.dayOfMonth,a.clockIn.hourOfDay,a.clockIn.minuteOfHour) - Date.UTC(b.clockDate.year,b.clockDate.monthOfYear,b.clockDate.dayOfMonth,b.clockIn.hourOfDay,b.clockIn.minuteOfHour);
	  if (c>0) {
	    return 1;
	  }
	  if (c<0) {
	    return -1;
	  }
	  return 0;
}
/**
 * AJAX PUT for this page
 */
function update(){
	if(validate()){
		$.ajax("/timemanagement/"+$("#user").val(), {
			type:"PUT",
			data:$("form").serialize()
		}).done(function(data){
			if(data!="true"){
				// return the errors and print them
			$("#error").prepend('<div class="alert alert-dismissable alert-danger"><button type="button" class="close" data-dismiss="alert">X</button>'+ data + '</div>');
			}
		});
	}
}
/**
 * simple clear all boxes function
 */
function clearBoxes(){
	$("#user").val("");
	$("#week").val("");
	$("#clockInTime").val("");
	$("#clockOutTime").val("");
	$("#clockDate").val("");
	$("#appendRows").empty();
}
/**
 * things to do to start the page
 */
function init(){
	clearBoxes();
	getActiveUsers();
}
/**
 * put errors in the box
 * @param i - the line
 * @param str - the string to append
 */
function lineError(i,str){
	$("#error"+i).append(str);
	$("#error"+i).show();
}
/**
 * remove all errors and hide the error box
 * @param i - the line
 */
function clearError(i){
	$("#error"+i).empty();
	$("#error"+i).hide();
}
/**
 * validation method to be called before making any updates to the database
 * @returns {Boolean} failure or success of the validation
 */
function validate(){
	var pass = true;
	var listLength = $("input").length/4;
	for(var i=0;i<listLength;i++){
		clearError(i);
		// the class set for each of the lines that we need to validate
		// should return an array[3] in the order clockIn, clockOut, clockDate
		var x=$(".list"+i);
		// NULL CHECK
		if($(x[0]).val() == "" || $(x[1]).val() == ""){
			// throw a main line error
			$("#error").prepend('<div class="alert alert-dismissable alert-danger"><button type="button" class="close" data-dismiss="alert">X</button> Please fill out all clock in and clock out times</div>');
			return false;
		}
		// FORMAT CHECK
		if($(x[0]).val().replace(/[0-9]{1,3}:[0-9]{2}[ap]m/,"") != "" || $(x[1]).val().replace(/[0-9]{1,3}:[0-9]{2}[ap]m/,"") != ""){
			// throw a main line error
			lineError(i,"&bull;Formatting error <br>");
			return false;
		}
		// name the clock in and allow a parse
		var clockIn = timeParse($(x[0]).val());
		// name the clock out and allow a parse
		var clockOut = timeParse($(x[1]).val());
		// NEGATIVE TIME CHECK
		if(clockIn.hours > clockOut.hours || clockIn.hours == clockOut.hours && clockIn.minutes >= clockOut.minutes){
			lineError(i,"&bull;Clock in must be before clock out<br>");
			pass = false;
		}
		// FULL LIST LOOP
		for(var j=0; j<listLength; j++){
			// second time object pulled in like the first
			// (array[3] - in, out, date)
			var y = $(".list"+j);
			// makes sure it isn't checking against itself and that
			// the Dates are the same
			if(i!=j && $(x[2]).val()==$(y[2]).val()){
				// parsing
				var yIn = timeParse($(y[0]).val());
				var yOut = timeParse($(y[1]).val());
				// CHECK FOR B.clockIn < A.clockIn < B.clockOut
				if((yIn.hours<clockIn.hours || yIn.hours == clockIn.hours && yIn.minutes <= clockIn.minutes)
						&& (clockIn.hours < yOut.hours || clockIn.hours == yOut.hours && clockIn.minutes <= yOut.minutes)){
					lineError(i,"&bull;Clock in time nested in entry "+(j+1)+"<br>");
					pass = false;
				}
				// CHECK FOR B.clockIn < A.clockOut < B.clockOut
				if((yIn.hours<clockOut.hours || yIn.hours == clockOut.hours && yIn.minutes <= clockOut.minutes)
						&& (clockOut.hours < yOut.hours || clockOut.hours == yOut.hours && clockOut.minutes <= yOut.minutes)){
					lineError(i,"&bull;Clock out time nested in entry "+(j+1)+"<br>");
					pass = false;
				}
				// CHECK FOR A.clockIn < B < A.clockOut
				if((clockIn.hours<yIn.hours || clockIn.hours == yIn.hours && clockIn.minutes <= yIn.minutes)
						&& (clockOut.hours > yOut.hours || clockOut.hours == yOut.hours && clockOut.minutes >= yOut.minutes)){
					lineError(i,"&bull;Entry "+(j+1)+" nested in this one<br>");
					pass = false;
				}
			}
		}
	}
	return pass;
}
/**
 * A method to create a very specific type of time object
 * @param a date string to parse
 * @returns object containing hours & minutes as numbers
 */
function timeParse(a){
	var z = {
		// test for am ----------------------- ? take the hour number ----- : add 12 to the hour number
		hours: a.substring(a.length-2) == "am" ? Number(a.split(':')[0],10) : Number(a.split(':')[0],10)+12,
				// get the minutes bit
		minutes: Number(a.split(':')[1].replace(/[^0-9]+/,""),10)
	}
	// if noon or midnight fix the calculatory error created above
	if( z.hours==12 || z.hours==24){
		z.hours-=12;
	}
	return z;
}
$(document).ready(function(){
	init();
	$("#inactiveButton").click(toggleInactiveButton);
	$("#user").change(getUserWeeks);
	$("#week").change(getUserClockedTime);
	$("#submit").click(update);
});