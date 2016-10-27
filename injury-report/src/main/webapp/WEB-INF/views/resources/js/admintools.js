(function(){
var typeForSend = null;
var current = null;
/**
 * What to do when clicking the edit people button
 */
function showPeople() {
	if (current != "people") {
		current = "people";
		$("#types").hide("blind");
		clearEverything();
		peopleData();
		$("#people").show("blind");
	}
}
/**
 * What to do when clicking the edit types button
 */
function showType() {
	if (current != "type") {
		current = "type";
		$("#people").hide("blind");
		clearEverything();
		$("#types").show("blind");
	}
}
/**
 * What to do when clicking the submit button on the edit people page
 */
function peopleSubmit() {
	// setting current this way forces us to make selections
	// before submitting empty nothingness
	if (current == "employee") {
		if (typeForSend == "POST") {
			employeePost();
		} else if (typeForSend == "PUT") {
			employeePut();
		}
	} else if (current == "user") {
		userPost();
	}
}
/**
 * To submit a new employee
 */
function employeePost() {
	// Check validation or shoot off an error
	if (validateEmployee()) {
		$.ajax("/admintools/employee/", {
			type : "POST",
			data : {
				firstName : $("#empFirstName").val(),
				lastName : $("#empLastName").val(),
				positionId : $("#empPosition").val()
			}
		}).done(function() {
			// do the base actions to get here again
			$("#editPeople").click();
			$("#employee").click();
		});
	} else {
		// Only thing that gets validated is what is submitted in the name
		$("#error").empty()
			.append('<div class="alert alert-dismissable alert-danger"><button type="button" class="close" data-dismiss="alert">X</button>Please only use letters in names</div>');
	}
}
/**
 * To update an existing employee
 */
function employeePut() {
	// Check validation or shoot off an error
	if (validateEmployee()) {
		$.ajax("/admintools/employee/" + $("#employeeSelect").val(), {
			type : "PUT",
			data : {
				firstName : $("#empFirstName").val(),
				lastName : $("#empLastName").val(),
				positionId : $("#empPosition").val()
			}
		}).done(function() {
			// do the base actions to get here again
			$("#editPeople").click();
			$("#editemployee").click();
		});
	} else {
		// Only thing that gets validated is what is submitted in the name
		$("#error").empty()
			.append('<div class="alert alert-dismissable alert-danger"><button type="button" class="close" data-dismiss="alert">X</button>Please only use letters in names</div>');
	}
}
/**
 * AJAX employees using filters
 */
function filterEmployeeBox() {
	$.ajax("/admintools/employee/filter", {
		type : "GET",
		data : $("#employeesearch").serialize()
	}).done(function(data) {
		// if there are no employees returned say so.
		if (data.length == 0) {
			$("#employeeSelect").empty().append(
				"<option val=''>No Results</option>");
		} else {
			// add a select one (with no value)
			$("#employeeSelect").empty().append(
				"<option value=''>Select One</option>");
			// Set employeeId values to a drop down with their names
			for (var i = 0; i < data.length; i++) {
				$("#employeeSelect").append(
					"<option value='" + data[i].employeeId + "'>"
						+ data[i].lastName + ", " + data[i].firstName
						+ "</option>");
			}
		}
	});
}
/**
 * Create or update a user the act is determined serverside depending on whether or not
 * the employee object already has a user associated or not.
 */
function userPost() {
	if (validateUser()) {
		$.ajax("/admintools/user/" + $("#employeeSelect").val(), {
			type : "POST",
			data : {
				username : $("#userUsername").val(),
				password : $("#userPassword").val(),
				roleId : $("#userRole").val()
			}
		}).done(function() {
			$("#editPeople").click();
			$("#user").click();
		});
	} else {
		$("#error").empty()
			.append('<div class="alert alert-dismissable alert-danger"><button type="button" class="close" data-dismiss="alert">X</button>Please only use letters and numbers in username</div>');
	}
}
/**
 * Grab the data required for the edit people section
 */
function peopleData() {
	$.ajax("/admintools/position", {
		type : "GET"
	}).done(
		function(data) {
			$("#filtPositionId").append("<option value=''>All</option>");
			for (var i = 0; i < data.length; i++) {
				// Fill both boxes all at once
				$("#empPosition").append(
					"<option value='" + data[i].positionId + "'>"
						+ data[i].positionName + "</option>");
				$("#filtPositionId").append(
					"<option value='" + data[i].positionId + "'>"
						+ data[i].positionName + "</option>");
			}
		});
	// when this is completed grab the employees
	ajaxEmployee();
}
/**
 * The ajax call to grab the employees for the box
 */
function ajaxEmployee() {
	$.ajax("/admintools/employee", {
		type : "GET"
	}).done(
		function(data) {
			$("#employeeSelect").empty().append(
				"<option value=''>Select One</option>");
			for (var i = 0; i < data.length; i++) {
				$("#employeeSelect").append(
					"<option value='" + data[i].employeeId + "'>"
						+ data[i].lastName + ", " + data[i].firstName
						+ "</option>");
			}
		});
}
/**
 * To be called when selecting an employee under the user side
 * this will gather the data from an employee's user record.
 */
function ajaxUser() {
	if ($("#employeeSelect").val() != "") {
		$.ajax("/admintools/user/" + $("#employeeSelect").val(), {
			type : "GET"
		}).done(function(data) {
			// You will be given empty dummy data to fill the fields with if there is no user.
			$("#userUsername").val(data.userName);
			if (data.role != null) {
				$("#userRole").val(data.role.roleId);
			}
		});
	}
}
/**
 * Used to grab the employee information for the edit employee bit of this page
 */
function ajaxOneEmployee() {
	if ($("#employeeSelect").val() != "") {
		$.ajax("/admintools/employee/" + $("#employeeSelect").val(), {
			type : "GET"
		}).done(function(data) {
			$("#empFirstName").val(data.firstName);
			$("#empLastName").val(data.lastName);
			$("#empPosition").val(data.position.positionId);
		});
	}
}
/**
 * What to do when changing the selected employee in the box
 */
function doEmpChange() {
	if ($("employeeSelect").val() != "" || null) {
		if (current == "user") {
			ajaxUser();
		} else if (current == "employee") {
			ajaxOneEmployee();
		}
	}
}
/**
 * Used to switch which display name shows up under edit types
 * @param string
 */
function typeDisplay(string) {
	$("#typeDisplay").empty().append(string);
}
/**
 * Click new employee button
 */
function showEmployee() {
	current = "employee";
	typeForSend = "POST";
	$("#employeeForm").show("blind");
	$("#empSelect").hide("blind");
	$("#employeeSelect").val("");
	$("#userForm").hide("blind");
}
/**
 * Click edit employee button
 */
function showEditEmployee() {
	current = "employee";
	typeForSend = "PUT";
	$("#employeeForm").show("blind");
	$("#empSelect").show("blind");
	$("#employeeSelect").val("");
	$("#userForm").hide("blind");
}
/**
 * Click user button
 */
function showUser() {
	current = "user";
	$("#employeeForm").hide("blind");
	$("#empSelect").show("blind");
	$("#employeeSelect").val("");
	$("#userForm").show("blind");
}
/**
 * Validation for use with Edit types
 * @returns true if valid
 */
function validate() {
	return !/[^ A-Za-z0-9/:]/.test($("#typeName").val());
}
/**
 * Validation for use with employee
 * @returns true if valid
 */
function validateEmployee() {
	return !/[^A-Za-z]/.test($("empFirstName").val())
		&& !/[^A-Za-z]/.test($("empLastName").val());
}
/**
 * Validation for use with User
 * @returns true if valid
 */
function validateUser() {
	return !/[^A-Za-z0-9]/.test($("userUsername").val());
}
/**
 * Check to see if the username requested is available
 */
function userNameAvailable() {
	if ($("#userUsername").val() != "" && validateUser()) {
		$.ajax("/admintools/username/"+ ($("#employeeSelect").val() == "" ? "0" : $("#employeeSelect").val()), {
			type : "GET",
			data : {
				username : $("#userUsername").val()
			}
		}).done(function(data) {
			
		/* This section may seem long tedious and confusing, but it's very simple
		 * A series of callbacks run the animation so that everything appears and
		 * disappears smoothly
		 * 
		 * We add an alert, .show() it,
		 * in .show() we add a callback with a timeout
		 * when that second has gone by we .hide()
		 * in .hide we have a callback to remove the alert
		 */
			
			// data == boolean (true if available)
			if (data) {
				$("#userUsernameContainer").empty()
					// alert pops up "Available"
					.append('<div class="alert alert-dismissable alert-success"><button type="button" class="close" data-dismiss="alert">X</button>Available</div>')
					// show the box
					.show({
						effect : "blind",
						// When finished showing
						complete : function() {
							// Set a timeout
							setTimeout(function() {
								// to hide it
								$("#userUsernameContainer").hide({
									effect : "blind",
									complete : function() {
										// and then empty it after it's been hidden
										$("#userUsernameContainer").empty();
									}
								});
							// end of timeout line below
							}, 1000);
						}
					});
			} else {
				$("#userUsernameContainer").empty()
					// alert pops up "Not available"
					.append('<div class="alert alert-dismissable alert-danger"><button type="button" class="close" data-dismiss="alert">X</button>Not Available</div>')
					.show({
						effect : "blind",
						// When finished showing
						complete : function() {
							// Set a timeout
							setTimeout(function() {
								// to hide it
								$("#userUsernameContainer").hide({
									effect : "blind",
									complete : function() {
										// and then empty it after it's been hidden
										$("#userUsernameContainer").empty();
									}
								});
							// end of timeout line below
							}, 1000);
						}
					});
			}
		});
	}
}
/**
 * If weather button is pushed on edit types
 */
function showWeather() {
	typeForSend = "weather";
	$.ajax("/admintools/weather", {
		type : "GET"
	}).done(
		function(data) {
			$("#typeSelect").empty();
			for (var i = 0; i < data.length; i++) {
				$("#typeSelect").append(
					"<option value='" + data[i].weatherId + "'>"
						+ data[i].weatherCondition + "</option>");
			}
			typeDisplay("Weather");
		});
}
/**
 * If position button is pushed on edit types
 */
function showPosition() {
	typeForSend = "position";
	$.ajax("/admintools/position", {
		type : "GET"
	}).done(
		function(data) {
			$("#typeSelect").empty();
			for (var i = 0; i < data.length; i++) {
				$("#typeSelect").append(
					"<option value='" + data[i].positionId + "'>"
						+ data[i].positionName + "</option>");
			}
			typeDisplay("Position");
		});
}
/**
 * If body part button is pushed on edit types
 */
function showBodyPart() {
	typeForSend = "body";
	$.ajax("/admintools/body", {
		type : "GET"
	}).done(
		function(data) {
			$("#typeSelect").empty();
			for (var i = 0; i < data.length; i++) {
				$("#typeSelect").append(
					"<option value='" + data[i].bodyPartId + "'>"
						+ data[i].bodyPartName + "</option>");
			}
			typeDisplay("Body Part");
		});
}
/**
 * If injury type button is pushed on edit types
 */
function showInjuryType() {
	typeForSend = "injury";
	$.ajax("/admintools/injury", {
		type : "GET"
	}).done(
		function(data) {
			$("#typeSelect").empty();
			for (var i = 0; i < data.length; i++) {
				$("#typeSelect").append(
					"<option value='" + data[i].typeId + "'>"
						+ data[i].typeName + "</option>");
			}
			typeDisplay("Injury Type");
		});
}
/**
 * If the delete button is clicked
 */
function typeDelete() {
	if (typeForSend != null || "") {
		if ($("#typeSelect") != "") {
			$.ajax("/admintools/" + typeForSend + "/" + $("#typeSelect").val(),
				{
					type : "DELETE"
				}).done(function() {
				$("#" + typeForSend).click();
			});
		}
	}
}
/**
 * If the submit button is clicked
 */
function typeSubmit() {
	if (typeForSend != null || "") {
		if (validate()) {
			$.ajax("/admintools/" + typeForSend, {
				type : "POST",
				data : {
					name : $("#typeName").val()
				}
			}).done(function() {
				$("#" + typeForSend).click();
			});
		} else {
			$("#error").empty()
				.append('<div class="alert alert-dismissable alert-danger"><button type="button" class="close" data-dismiss="alert">X</button>You may only use the letters, numbers, backslash "/" and colon ":"</div>');
		}
	}
}
/**
 * Clears out everything that can be cleared out
 * 
 * Useful because of the amount of stuff being updated on this page
 */
function clearEverything() {
	typeForSend = null;
	current = null;
	typeDisplay("Select One");
	$("#error").empty();
	var input = $("input");
	var select = $("select");
	for (var i = 0; i < input.length; i++) {
		$(input[i]).val("");
	}
	for (var i = 0; i < select.length; i++) {
		$(select[i]).empty();
	}
	// Roles are hardcoded because there are no other types planned ever
	$("#userRole").append(
		"<option value='2'>User</option><option value='1'>Admin</option>")
}
$(document).ready(function() {
	// Main Buttons
	$("#editPeople").click(showPeople);
	$("#editTypes").click(showType);
	// People Page (choice) Buttons
	$("#employee").click(showEmployee);
	$("#editemployee").click(showEditEmployee);
	$("#user").click(showUser);
	// People Page Submit and Change
	$("#employeeSelect").change(doEmpChange);
	$("#peopleSubmit").click(peopleSubmit);
	// Type Page (choice) Buttons
	$("#weather").click(showWeather);
	$("#position").click(showPosition);
	$("#body").click(showBodyPart);
	$("#injury").click(showInjuryType);
	// Type Page Submit and delete buttons
	$("#typeDelete").click(typeDelete);
	$("#typeSubmit").click(typeSubmit);
	// Check username button on people page
	$("#checkUsername").click(userNameAvailable);
	// Filter button in the employee selection portion of the people page
	$("#filter").click(filterEmployeeBox);
});
})();