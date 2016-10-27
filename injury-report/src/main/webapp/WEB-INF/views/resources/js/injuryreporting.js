(function(){
var timeRegex = /^([01]\d|2[0-3]):?([0-5]\d)$/;

$(document).ready(function() {
	initalizeDateTimePicker();
	var currDate = $("#dateOfInjury").val();
	getWeatherList();
	getInjuryTypeList();
	getBodyPartList();
	$("select").change(function(){inLineVal(this)});
	$("#timepicker").change(function(){inLineVal(this)});
	$("#injuryReport").hide();
	
	$("#next").click(function(){		
		if(validateInjuryReport(currDate)){
			getReportData(currDate);
			$("#main").hide();
			$("#injuryReport").show();
			successfulReport($("#errorIncompleteForm"));
			successfulReport($("#errorInvalidDates"));
		}else{
			validateDates(currDate);
			weatherSubmitValidation($("#weatherSelect").val());
			injurySubmitValidation($("#injuryTypeSelect").val());
			bodyPartSubmitValidation($("#bodyPartSelect").val());
			timeSubmitValidation($("#timepicker").val());
			errorIncompleteForm("Please complete the form accurately before submitting!");
		}
	})
	
	$("#back").click(function(){
		clearReportData();
		$("#main").show();
		$("#injuryReport").hide();
	})
	
	$("#submit").click(function(){
		ajaxPost(currDate);
	})
})

/**
 * Initializes the datepicker and timepicker objects
 */
function initalizeDateTimePicker(){
	$(".datepicker").datepicker();
	$("#timepicker").timepicker();
}

/**
 * Ajax call to handle grab the list of weather conditions from the database
 */
function getWeatherList() {
	//Grab the weatherId for later
	var weatherId = $("#weatherSelect").val();
	$.ajax("/injuryreporting/weather", {
		type : "GET",
	}).success(function(data) {
		//Empty the weather select box
		$("#weatherSelect").empty().append("<option value=\"\">SelectOne</option>")
		for (var i = 0; i < data.length; i++) {
			//Create an option using the weatherID as the value for this select
			$("#weatherSelect").append("<option value=" + data[i].weatherId + ">" 
					//Display the weather type
					+ data[i].weatherCondition + "</option>"); 
		}
		//Return the previous weather to the select box
		$("#weatherSelect").val(weatherId);
	});
}

/**
 * Ajax call to handle grab the list of injury types from the database
 */
function getInjuryTypeList() {
	//Grab the injuryTypeId for later
	var injuryTypeId = $("#injuryTypeSelect").val();
	$.ajax("/injuryreporting/injurytype", {
		type : "GET",
	}).success(function(data) {
		//Empty the injury type select box and set the first option to no value
		$("#injuryTypeSelect").empty().append("<option value=\"\">SelectOne</option>")
		for (var i = 0; i < data.length; i++) {
			//Create an option using the injuryTypeID as the value for this select
			$("#injuryTypeSelect").append("<option value=" + data[i].typeId + ">" 
					// display the injury type
					+ data[i].typeName + "</option>"); 
		}
		//Return the previous injury type to the select box
		$("#injuryTypeSelect").val(injuryTypeId);
	});
}

/**
 * Ajax call to handle grab the list of body parts from the database
 */
function getBodyPartList() {
	//Grab the bodyPartId for later
	var bodyPartId = $("#bodyPartSelect").val();
	$.ajax("/injuryreporting/bodypart", {
		type : "GET",
	}).success(function(data) {
		//Empty the body part select box and set the first option to no value
		$("#bodyPartSelect").empty().append("<option value=\"\">SelectOne</option>")
		for (var i = 0; i < data.length; i++) {
			//Create an option using the bodyPartID as the value for this select
			$("#bodyPartSelect").append("<option value=" + data[i].bodyPartId + ">" 
					//Display the body part
					+ data[i].bodyPartName + "</option>"); 
		}
		//Return the previous body part to the select box
		$("#bodyPartSelect").val(bodyPartId);
	});
}

/**
 * Displays validation underneath the html object passed to inLineVal function
 * @param obj - html object
 */
function inLineVal(obj){
	if(obj.name == "weatherSelect"){
		if($("#weatherSelect").val()==""){
			specError($(obj),"Please select a weather type");
		}else{
			success($(obj),"Good");
		}
	}
	if(obj.name == "injuryTypeSelect"){
		if($("#injuryTypeSelect").val()==""){
			specError($(obj),"Please select an injury type");
		}else{
			success($(obj),"Good");
		}
	}
	if(obj.name == "bodyPartSelect"){
		if($("#bodyPartSelect").val()==""){
			specError($(obj),"Please select a body part");
		}else{
			success($(obj),"Good");
		}
	}
	if(obj.name == "timeInjuryOccurred"){
		if(timeRegex.test($("#timepicker").val())){
			success($(obj),"Good");	
		}else{
			specError($(obj),"Please enter a valid time");
		}
	}
		
}

/**
 * Appends error message to html element
 * @param where - the html element to append the message to
 * @param data - the content of the error message
 */
function specError(where, data){
	// due to design this parent().parent() is required
	x = where.parent().parent();
	// change the color to error
	x.addClass("has-error");
	// remove the box and append a new one
	$(x.children()[1]).remove();
	x.append('<div class="alert alert-dismissable alert-danger"><button type="button" class="close" data-dismiss="alert">X</button>'+data+'</div>');
}

/**
 * Appends success message to form element
 */
function success(where,data){
	//due to design this parent().parent() thing is required
	x=where.parent().parent();
	// clear the error color and make it success
	x.removeClass("has-error");
	//remove the error box and append a new one
	$(x.children()[1]).remove();
	x.append('<div class="alert alert-dismissable alert-success"><button type="button" class="close" data-dismiss="alert">X</button>'+data+'</div>');
}

/**
 * Check to make sure all inputs are valid
 * @param currDate - The date the injury report is being created
 * @returns {Boolean}
 */
function validateInjuryReport(currDate){
	return $("#weatherSelect").val() != "" 
		&&  $("#injuryTypeSelect").val() != "" 
		&& $("#bodyPartSelect").val() != ""
		&& timeRegex.test($("#timepicker").val())
		&& convertDate($("#dateOfInjury").val()) <= convertDate($("#dateReported").val())
		&& convertDate($("#dateOfInjury").val()) <= convertDate(currDate)
		&& convertDate($("#dateReported").val()) <= convertDate(currDate);
}

/**
 * Appends the data entered into the form to the appropriate container
 * @param currDate - The date the injury report is being created
 */
function getReportData(currDate){	
	$("#injuredEmployee").append(getUrlParameter("firstName")+" "+getUrlParameter("lastName"));
	$("#employeeId").append(getUrlParameter("employeeId"));
	getReporter();
	$("#injuryDate").append($("#dateOfInjury").val());
	$("#reportedDate").append($("#dateReported").val());
	$("#filedDate").append(currDate);
	$("#weatherCondition").append($(getSelectValue("weatherSelect")).html());
	$("#typeOfInjury").append($(getSelectValue("injuryTypeSelect")).html());
	$("#bodyPartAffected").append($(getSelectValue("bodyPartSelect")).html());
	$("#injuryTime").append($("#timepicker").val());
	$("#reportDescription").append($("#description").val());
}

/**
 * Gets the values of the parameters in the URL
 * 
 * @param sParam
 * @returns
 */
function getUrlParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) 
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) 
        {
            return sParameterName[1];
        }
    }
}

/**
 * Gets the reporting users name and id for the generated report
 */
function getReporter() {

	$.ajax("/injuryreporting/getUser", {
		type : "GET",
	}).success(function(data) {
			$("#injuryReporter").append(data.employee.firstName + " " + data.employee.lastName);
			$("#reporterId").append(data.userId);
	});
}

/**
 * Gets the text value of a select box
 * 
 * @param select - the id of the select box to get the value from
 * @returns {@G}
 */
function getSelectValue(select){
	var option;
	$("#"+select).children().each(function(){
		if($(this).val()==$("#"+select).val()){
			option=this
		}
	});
	return option;
}

/**
 * Resets the report data to empty divs
 */
function clearReportData(){
	$("#injuredEmployee").empty();
	$("#employeeId").empty();
	$("#injuryReporter").empty();
	$("#reporterId").empty();
	$("#injuryDate").empty();
	$("#reportedDate").empty();
	$("#filedDate").empty();
	$("#weatherCondition").empty();
	$("#typeOfInjury").empty();
	$("#bodyPartAffected").empty();
	$("#injuryTime").empty();
	$("#reportDescription").empty();
}

/**
 * Clears error messages from form after a valid report is entered
 * @param where - the html element to remove the error from
 */
function successfulReport(where){
	//due to design this parent().parent() thing is required
	x=where.parent().parent();
	// clear the error color and make it success
	x.removeClass("has-error");
	//remove the error boxes
	$(x.children()[1]).remove();
}

/**
 * Validation for the dates submitted
 */
function validateDates(currDate){
	if(convertDate($("#dateOfInjury").val()) > convertDate($("#dateReported").val())){
		errorInvalidDates($("#errorInvalidDates3"),"Date Reported to management must be the same or a later date than Date of Injury.");
	}else{
		successfulReport($("#errorInvalidDates3"));
	}
	
	if(convertDate($("#dateOfInjury").val()) > convertDate(currDate)){
		errorInvalidDates($("#errorInvalidDates1"),"Date of Injury must  be today's date or any date previous");
	}else{
		successfulReport($("#errorInvalidDates1"));
	}
	
	if(convertDate($("#dateReported").val()) > convertDate(currDate)){
		errorInvalidDates($("#errorInvalidDates2"),"Date Reported to management must be today's date or any date previous");
	}else{
		successfulReport($("#errorInvalidDates2"));
	}
}

/**
 * Converts the date to a number for comparison
 * @param date
 * @returns
 */
function convertDate(date){
	var splitDate = date.split("/");
	var dateInt = Date.UTC(splitDate[2],splitDate[0],splitDate[1]);
	return dateInt;
}

/**
 * Displays error message for Invalid Dates
 * 
 * @param where - the html element to append the error message to
 * @param data - the content of the error message
 */
function errorInvalidDates(where, data) {
	specError(where,data);
}

/**
 * Validates the selected weather
 * @param value - the value of the weatherSelect drop down list
 */
function weatherSubmitValidation(value){
	if(value=="" || null==value){
		specError($("#weatherSelect"),"Please select a weather type");
	}else{
		success($("#weatherSelect"),"Good");
	}
}

/**
 * Validates the selected injury type 
 * @param value - the value of the injuryTypeSelect drop down list
 */
function injurySubmitValidation(value){
	if(value=="" || null==value){
		specError($("#injuryTypeSelect"),"Please select an injury type");
	}else{
		success($("#injuryTypeSelect"),"Good");
	}
}

/**
 * Validates the selected body part 
 * @param value - the value of the bodyPartSelect drop down list
 */
function bodyPartSubmitValidation(value){
	if(value=="" || null==value){
		specError($("#bodyPartSelect"),"Please select a body part");
	}else{
		success($("#bodyPartSelect"),"Good");
	}
}

/**
 * Validates the time entered from the timepicker object
 * @param value - the value of the timepicker object
 */
function timeSubmitValidation(value){
	if(timeRegex.test(value)){
		success($("#timepicker"),"Good");	
	}else{
		specError($("#timepicker"),"Please enter a valid time");
	}
}

/**
 * Displays error message for Incomplete form
 * @param data - the content of the error message
 */
function errorIncompleteForm(data) {
	specError($("#errorIncompleteForm"),data);
}

/**
 * This POST method is used to instantiate a new injury report
 */
function ajaxPost(currDate) {
	$.ajax("/injuryreporting",{
		data:{
			employeeId: getUrlParameter("employeeId"),
			reporterId: $("#reporterId").html(),
			weatherId: $("#weatherSelect").val(),
			bodyPartId: $("#bodyPartSelect").val(),
			injuryTypeId: $("#injuryTypeSelect").val(),
			reportDate: currDate,
			reportManagementDate: $("#dateReported").val(),
			timeOfInjury: $("#timepicker").val(),
			description: $("#description").val()
		},
		type:"POST",
		dataType:"text",
		success: function(data){
			window.location.href = "/confirmation/" + data;
		}
	});
}
})();