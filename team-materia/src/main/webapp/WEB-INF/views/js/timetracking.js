/**
 * This number will go negative over time as we click the Previous Button
 */
var page=0;
/** 
 * This number will get configured in the date object
 */ 
var oldest;
/**
 * Useful numbers for Date object in Milliseconds
 */
var DateHelper = {
	SECOND: 1000,
	MINUTE: 60000,
	HOUR: 3600000,
	DAY: 86400000,
	WEEK: 604800000,
}
/**
 * Days of the week and their hours
 * 
 * Access day names using weekdays[0-6].weekday
 *            hours using weekdays[0-6].hours
 */
var weekdays = [
    {"weekday" : "sunday", "hours" : 0},
    {"weekday" : "monday", "hours" : 0},
    {"weekday" : "tuesday", "hours" : 0},
    {"weekday" : "wednesday", "hours" : 0},
    {"weekday" : "thursday", "hours" : 0},
    {"weekday" : "friday", "hours" : 0},
    {"weekday" : "saturday", "hours" : 0}
];
/**
 * Begin and End date is initialized in the init() method
 * beginDate is the Sunday of the week
 * endDate is the Saturday of the week
 */
var beginDate;
var endDate;
/**
 * This method is called from $("#clockBtn").click(toggleButton()); When
 * clicked, the object makes an AJAX call (either clockIn() or clockOut()) and
 * then switches it's display text
 */
function toggleButton(){
	if($("#clock").html() == "Clock In"){
		clockIn();
		$("#clock").empty().append("Clock Out");
	} else {
		clockOut();
		$("#clock").empty().append("Clock In");
	}
}
/**
 * A crude - semi-unimportant - method to get when we are on the page in
 * relation to now. We subtract a week per page back from today
 * 
 * @returns the relevant time for getWeek();
 */
function when(){
	return Date.now()+(page*DateHelper.WEEK);
}
/**
 * This method simply sends a POST request to the page (indication for the
 * server-side code to do the clocking in)
 */
function clockIn(){
	$.ajax("/timetracking",{
		type:"POST",
	});
}
/**
 * This method simply sends a PUT request to the page (indication for the
 * server-side code to do the clocking out)
 */
function clockOut(){
	$.ajax("/timetracking",{
		type:"PUT",
	}).done(getWeek);
}
/**
 * This method sends the when() method's <strong>long</strong> to the server
 * and returns a list of UserTime objects that exist within the timeframe listed
 * on screen <br> <br> In the .done() method we initialize the weekdays object back to numbers (0),
 * add all the UserTime objects to them and then truncates the numbers to two
 * decimal places
 */
function getWeek(){
	$.ajax("/timetracking/date",{
		type:"GET",
		data:{date:when()}
	}).done(function(data){
		for(var i = 0; i < weekdays.length; i++){
			weekdays[i].hours = 0;
		}
		//returns a list of UserTime objects for the user in session for the current week.
		for(var i = 0; i < data.length; i++){
			weekdays[data[i].clockDate.dayOfWeek%7].hours += Number((Math.abs(data[i].clockOut.millisOfDay - data[i].clockIn.millisOfDay))/DateHelper.HOUR)
		}
		//Truncate values
		for(var j = 0; j < weekdays.length; j++){
			weekdays[j].hours = weekdays[j].hours.toFixed(2);
		}
		//Update screen
		cosmeticUpdate();
	});
}
/**
 * This method is only called in the init() method and returns the User's oldest
 * record on file (This sets a breaking point for where we should stop
 * displaying older pages)
 */
function getOldest(){
	$.ajax("/timetracking/old",{
		type:"GET"
		
	}).done(function(data){
		if(data!=""){
			oldest = Date.UTC(data.year, data.monthOfYear-1, data.dayOfMonth);
			if(beginDate <= oldest){
				$("#previous").removeClass("btn-primary").addClass("btn-default");
			}
		} else {
			$("#previous").removeClass("btn-primary").addClass("btn-default");
		}
	});
}
/**
 * This method is called in the init() method only, and simply sets the Clock In /
 * Clock Out button to the correct one
 */
function getClockStatus(){
	$.ajax("/timetracking/clockstatus",{
		type:"GET"
	}).done(function(data){
		if(data){
			$("#clock").append("Clock Out");
		} else {
			$("#clock").append("Clock In");
		}
	});
}
/**
 * This method is called any time the Previous button is clicked on the page and
 * is responsible for updating the page number, updating the begin and end
 * dates, turning on and off buttons (itself and the next button) and then
 * calling getWeek();
 */
function previous(){
	if(endDate - DateHelper.WEEK >= oldest){
		page-=1;
		beginDate.setUTCDate(beginDate.getUTCDate()-7);
		endDate.setUTCDate(endDate.getUTCDate()-7);
		if(beginDate <= oldest){
			$("#previous").removeClass("btn-primary").addClass("btn-default");
		}
		if($("#next").hasClass("btn-default")){
			$("#next").removeClass("btn-default").addClass("btn-primary");
		}
		getWeek();
	}
}
/**
 * This method is called any time the Next button is clicked on the page and is
 * responsible for updating the page number, updating the begin and end dates,
 * turning on and off buttons (itself and the previous button) and then calling
 * getWeek();
 */
function next(){
	if(page<0){
		page+=1;
		beginDate.setUTCDate(beginDate.getUTCDate()+7);
		endDate.setUTCDate(endDate.getUTCDate()+7);
		if(page==0){
			$("#next").removeClass("btn-primary").addClass("btn-default");
		}
		if($("#previous").hasClass("btn-default")){
			$("#previous").removeClass("btn-default").addClass("btn-primary");
		}
		getWeek();
	}
}
/**
 * Called only in the init() method, this gets the starting points for the date
 * object embedded in and displayed on the page
 */
function findWeekBegin(){
	var date = new Date();
	beginDate = new Date(date - (date.getDay()*DateHelper.DAY));
	endDate = new Date(beginDate.valueOf() + DateHelper.WEEK - DateHelper.DAY);
}
/**
 * This is where we do our display updates.
 */
function cosmeticUpdate(){
	$("#sunday").empty().append(weekdays[0].hours);
	$("#monday").empty().append(weekdays[1].hours);
	$("#tuesday").empty().append(weekdays[2].hours);
	$("#wednesday").empty().append(weekdays[3].hours);
	$("#thursday").empty().append(weekdays[4].hours);
	$("#friday").empty().append(weekdays[5].hours);
	$("#saturday").empty().append(weekdays[6].hours);
	$("#beginDate").empty().append((beginDate.getMonth()+1) + "/" + beginDate.getDate() + "/" + beginDate.getFullYear());
	$("#endDate").empty().append((endDate.getMonth()+1) + "/" + endDate.getDate() + "/" + endDate.getFullYear());
}
/**
 * This is called when the page starts and should put into motion all the
 * necessary code to make the page viewable
 */
function init(){
	findWeekBegin();
	getOldest();
	getClockStatus();
	getWeek();
}
$(document).ready(function(){
	//show loading gif during ajax calls 
	$('#loading-image').hide();
	$(document).ajaxStart(function() {
		$('#loading-image').show();
	});
	$(document).ajaxStop(function() {
		$('#loading-image').hide();
	});
	init();
	$("#clockBtn").click(toggleButton);
	$("#previous").click(previous);
	$("#next").click(next);
});