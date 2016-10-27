/**
 * Regex helper: nameFail & alphaNumFail are test inversions; <br>
 * password uses .replace() <br>
 * email is junk, and should not be relied upon heavily (the java handles the
 * errors)
 */
var regex = {
	nameFail : /[^a-z]+/i,
	password : /[^a-z]/,
	alphaNumFail : /[^a-z0-9]/i,
	email : /.+@.+\..+(\..+)*/
}
/**
 * When this is called, it removes the error box, and switches the color to success
 * @param obj the object to call that on (the valid object)
 */
function success(obj) {
	$("." + obj.attr("id")).remove();
	obj.parent().parent().removeClass("has-error").addClass("has-success");
}
/**
 * When this is called, it removes the error box, and switches the color to
 * error, and then adds the new error box
 * @param obj the object to call that on (the invalid object)
 * @param data a string of text to put in the box
 */
function specError(obj, data) {
	$("." + obj.attr("id")).remove();
	obj.parent().parent().removeClass("has-success").addClass("has-error");
	$("#errors")
			.prepend(
					'<div class="alert alert-dismissable alert-danger '
							+ obj.attr("id")
							+ '"><button type="button" class="close" data-dismiss="alert">X</button>'
							+ data + '</div>');
}
/**
 * The live validation call
 * @param obj the object being validated
 */
function inLineVal(obj) {
	// if the input is null don't bother
	if ($(obj).val() != "") {
		if (obj.name == "user.firstName") {
			if (regex.nameFail.test($("#firstName").val())) {
				specError($(obj), "Name must be letters only");
			} else {
				success($(obj));
			}
		}
		if (obj.name == "user.lastName") {
			if (regex.nameFail.test($("#lastName").val())) {
				specError($(obj), "Name must be letters only");
			} else {
				success($(obj));
			}
		}
		if (obj.name == "user.username") {
			if (regex.alphaNumFail.test($("#username").val())) {
				specError($(obj), "Username must be alphanumeric");
			} else {
				ajaxUsername();
			}
		}
		if (obj.name == "user.email") {
			if (!regex.email.test($("#email").val())) {
				specError($(obj), "Invalid email");
			} else {
				ajaxEmail();
			}
		}
		if (obj.name == "password") {
			if (!regex.password.test($("#password").val())
					|| $("#password").val().length < 8) {
				specError(
						$(obj),
						"Your password must be at least 8 characters long and contain an uppercase letter, number or special character");
			} else {
				success($(obj));
			}
		}
	}
}
/**
 * Quick one line validation to determine whether the Add button can be pressed
 * @returns true or false
 */
function validate() {
	return !regex.nameFail.test($("#firstName").val())
			&& !regex.nameFail.test($("#lastName").val())
			&& !regex.alphaNumFail.test($("#username").val())
			&& regex.email.test($("#email").val())
			&& regex.password.test($("#password").val());
}
/**
 * Quick one line validation to determine whether the Update button can be pressed
 * @returns true or false
 */
function validUpdate() {
	return !regex.nameFail.test($("#firstName").val())
			&& !regex.nameFail.test($("#lastName").val())
			&& !regex.alphaNumFail.test($("#username").val())
			&& regex.email.test($("#email").val())
			&& (regex.password.test($("#password").val()) || $("#password")
					.val() == "")
			&& $("#user").val()!="";
}
/**
 * This is the email availability ajax call
 */
function ajaxEmail() {
	//if email is null skip this
	if ($("#email").val() != "") {
		// if there is a pre existing user (update)
		if ($("#user").val() != "" && $("#user").val() != null) {
			// use this call that will return true if the email is new
			// or if it is the same one from before
			$.ajax("/accountconfig/email/", {
				data : {
					userId : $("#user").val(),
					email : $("#email").val()
				},
				type : "GET",
				dataType : "text",
				success : function(data) {
					if (data == "true") {
						success($("#email"));
					} else {
						specError($("#email"), "Email in use");
					}
				}
			});
		} else {
			// Otherwise do the old call from register (add)
			$.ajax("/register/email/", {
				data : {
					email : $("#email").val()
				},
				type : "GET",
				dataType : "text",
				success : function(data) {
					if (data == "true") {
						success($("#email"));
					} else {
						specError($("#email"), "Email in use");
					}
				}
			});
		}
	}
}
/**
 * This is the username availability ajax call
 */
function ajaxUsername() {
	//if username is null skip this
	if ($("#username").val() != "") {
		// if there is a pre-existing user (update)
		if ($("#user").val() != "" && $("#user").val() != null) {
			// use this call that will return true if the email is new
			// or if it is the same one from before
			$.ajax("/accountconfig/username/", {
				data : {
					userId : $("#user").val(),
					username : $("#username").val()
				},
				type : "GET",
				dataType : "text",
				success : function(data) {
					if (data == "true") {
						success($("#username"));
					} else {
						specError($("#username"), "Username in use");
					}
				}
			});
		} else {
			// otherwise use the old register call
			$.ajax("/register/user/", {
				data : {
					username : $("#username").val()
				},
				type : "GET",
				dataType : "text",
				success : function(data) {
					if (data == "true") {
						success($("#username"));
					} else {
						specError($("#username"), "username in use");
					}
				}
			});
		}
	}
}
/**
 * Use this to clear the page
 */
function clearBoxes() {
	$("#firstName").val("").removeClass("has-success").removeClass("has-error");
	$("#lastName").val("").removeClass("has-success").removeClass("has-error");
	$("#username").val("").removeClass("has-success").removeClass("has-error");
	$("#password").val("").removeClass("has-success").removeClass("has-error");
	$("#active").removeAttr("checked");
	$("#inactive").removeAttr("checked");
	$("#email").val("").removeClass("has-success").removeClass("has-error");
	$("#error").empty();
}
/**
 * Ajax call to handle getting a specific user and populate the fields with their data
 */
function getUser() {
	$.ajax("/accountconfig/user/" + $("#user").val(), {
		type : "GET"
	}).success(function(data) {
		// dole out the data to where it belongs
		$("#firstName").val(data.firstName);
		$("#lastName").val(data.lastName);
		$("#username").val(data.username);
		$("#role").val(data.role.roleId);
		if (data.active) {
			// USE CLICK TO AVOID FIREFOX BEING STUPID WITH .attr("checked", "checked")
			$("#active").click();
		} else {
			// USE CLICK TO AVOID FIREFOX BEING STUPID WITH .attr("checked", "checked")
			$("#inactive").click();
		}
		$("#password").val("");
		$("#email").val(data.email);
	});
}
/**
 * the call to get all users (same ajax call)
 */
function showInactiveUsers() {
	// snag the userId for later
	var user = $("#user").val();
	$.ajax("/accountconfig/users", {
		type : "GET",
		data : {
			// this is the bit that allows or disallows config
			withInactive : true
		}
	}).success(function(data) {
		// empty the users and set the first option to no value
		$("#user").empty().append("<option value=\"\">SelectOne</option>")
		for (var i = 0; i < data.length; i++) {
			// create an option using the userId as the value for this select
			$("#user").append("<option value=" + data[i].userId + ">" 
					// display the name in Last, First order
					+ data[i].lastName + ", " + data[i].firstName +
					"</option>"); 
		}
		// return the previous user to the select box
		$("#user").val(user);
	});
}
/**
 * Gets back only those users who are set as active in the database
 */
function hideInactiveUsers() {
	// snag the userId for later
	var user = $("#user").val();
	$.ajax("/accountconfig/users", {
		type : "GET",
		data : {
			// this is the bit that makes this different from showInactiveUsers
			withInactive : false
		}
	}).success(function(data) {
		// clear the select box and set the first option to an empty
		$("#user").empty().append("<option value=\"\">SelectOne</option>")
		for (var i = 0; i < data.length; i++) {
			// create an option using the userId as the value for this select
			$("#user").append("<option value=" + data[i].userId + ">" 
					// display the name in Last, First order
					+ data[i].lastName + ", " + data[i].firstName + 
					"</option>");
		}
		// check to see if we can return the previous user to the select box
		checkForUser(user);
	});
}
/**
 * This is the PUT ajax call to update the user
 */
function updateUser() {
	// base validation for this type
	if (validUpdate()) {
		$.ajax("/accountconfig", {
			type : "PUT",
			data : $("form").serializeArray()
		}).done(function(data) {
			if (data == "true") {
				alert("Update Successful");
			} else {
				specError($("input"), data);
			}
		});
	}
}
/**
 * POST request for adding a user
 */
function addUser() {
	if (validate()) {
		$.ajax(
				"/register",
				{
					type : "POST",
					// easier to hard code this and use the Registration page POST call
					data : "firstName=" + $("#firstName").val() + "&lastName="
							+ $("#lastName").val() + "&username="
							+ $("#username").val() + "&email="
							+ $("#email").val() + "&password="
							+ $("#password").val() + "&passwordConfirm="
							+ $("#password").val()
				}).done(function(data) {
			if (data == "true") {
				alert("Addition Successful");
			} else {
				specError($("input"), data);
			}
		});
	}
}
/**
 * Checking to see if the previous option is in the select now and if it is it
 * is selected
 * 
 * @param option -
 *            the original option value
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
 * Handling the clicking of the inactive button
 */
function toggleInactive() {
	// Checking if the button is able to be clicked (should be hidden, but if
	// not this check is useful) - remember never trust *`~ should ~`*
	if ($("#inactiveButton").html() != "N/A") {
		if ($("#inactiveButton").html() == "Inactive Hidden") {
			$("#inactiveButton").empty().append("Inactive Shown").removeClass(
					"btn-danger").addClass("btn-success");
			showInactiveUsers();
		} else { // if currently showing
			$("#inactiveButton").empty().append("Inactive Hidden").removeClass(
					"btn-success").addClass("btn-danger");
			hideInactiveUsers();
		}
	}
}
/**
 * Used to toggle between the Update and Add form
 */
function toggleForm() {
	// Clear the boxes
	clearBoxes();
	// IF IT IS ADD USER => UPDATE USER
	if ($("#formSwapButton").html() == "Add User") {
		// change the header
		$("legend").empty().append("Add New User");
		// Swap the toggle button
		$("#formSwapButton").empty().append("Modify Users");
		// Clear the user (inactive) button
		$("#inactiveButton").empty().append("N/A").removeClass("btn-danger")
				.removeClass("btn-success").addClass("btn-default");
		// remove the users from the user list and hide the entire selection
		// including ^^ The inactive button ^^
		$("#user").empty().parent().parent().hide("blind");
		// Set active 
		$("#active").click();
		// Hide active
		$("#activeHide").hide("blind");
		// turn the Update button to an add button
		$("#submit").empty().append("Add");
		// set role to User and hide this box
		$("#role").val(1).parent().parent().hide("blind");
	} else { // IF IT IS UPDATE USER => ADD USER
		// change the form header
		$("legend").empty().append("Account Config");
		// Switch the button
		$("#formSwapButton").empty().append("Add User");
		// Show the inactive button
		$("#inactiveButton").empty().append("Inactive Hidden").removeClass(
				"btn-default").addClass("btn-danger");
		// fill the user box with users
		hideInactiveUsers();
		// show the user box and inactive button
		$("#user").parent().parent().show("blind");
		// show the active box
		$("#activeHide").show("blind");
		// change the add button to an update button
		$("#submit").empty().append("Update");
		// show the role box
		$("#role").parent().parent().show("blind");
	}
}
/**
 * Submit button defers here (so as to allow the swapping of text)
 */
function submitButton() {
	if ($("#submit").html() == "Update") {
		updateUser();
	} else {
		addUser();
	}
}
/**
 * Things that need to be called to start
 */
function init() {
	hideInactiveUsers();
	clearBoxes();
}
$(document).ready(function() {
	init();
	$("#inactiveButton").click(toggleInactive);
	$("#user").change(getUser);
	$("#submit").click(submitButton);
	$("input").blur(function() {
		inLineVal(this)
	});
	$("#formSwapButton").click(toggleForm);
});