/**
 * Regex helper: nameFail & alphaNumFail are test inversions; password is there
 * for a .replace and test email is just-barely good enough, and should not be
 * relied upon heavily (the back-end catches a lot of the errors on just this
 * one)
 */
var regex = {
	nameFail: /[^a-z]+/i,
	password: /[^a-z]/,
	alphaNumFail: /[^a-z0-9]/i,
	email: /.+@.+\..+(\..+)*/
}
/**
 * This method is a location-lacking error (defaults to the top of the page);
 * @param data - the error message
 */
function error(data) {
	specError($("#error"),data);
}
/**
 * Error message with a specific purpose in mind
 * 
 * @param where the jQuery object to append to
 * @param data the message
 */
function specError(where, data){
	// due to design this parent().parent() is required
	x = where.parent().parent();
	// change the color to error
	x.removeClass("has-success");
	x.addClass("has-error");
	// remove the box and append a new one
	$(x.children()[2]).remove();
	x.append('<div class="alert alert-dismissable alert-danger"><button type="button" class="close" data-dismiss="alert">X</button>'+data+'</div>');
}
/**
 * Success message returning "Available" (used only by the ajax calls)
 * 
 * @param where the jQuery object to append to
 */
function successMsg(where){
	success(where,"Available!");
}
/**
 * Success message returning to a specific location and with a specific message
 * @param where the jQuery object to append to
 * @param data the message
 */
function success(where, data){
	//due to design this parent().parent() thing is required
	x=where.parent().parent();
	// clear the error color and make it success
	x.removeClass("has-error");
	x.addClass("has-success");
	//remove the error box and append a new one
	$(x.children()[2]).remove();
	x.append('<div class="alert alert-dismissable alert-success"><button type="button" class="close" data-dismiss="alert">X</button>'+data+'</div>');
}
/**
 * This POST method is to instantiate a new user
 */
function ajaxPost() {
	// Check the validation to see if the call should be made
	if(validate()){
		// AJAX call begin
		$.ajax("/register",{
			// serialize the data
			data:$("#register").serializeArray(),
			type:"POST",
			dataType:"text",
			// on error (which with validation there never should be)
			// inform the user
			error: function(data){alert("A server error has occurred." + data.toString());},
			// on success
			success: function(data){
				// if there are no error messages to return
				if(data=="true"){
					// go back to the login page
					window.location.assign("/login")
				} else {
					// otherwise print the error message box
					error(data);
				}
			}
		});
	} else {
		// the validation is checking only that the form was filled out (as the
		// rest of the validation is handled live and won't let us click the
		// submit button if it doesn't pass
		error("Please complete the form before submitting!");
	}
}
/**
 * This is the inline validation checking every important bit possible
 * (including the AJAX checks for email and username availability)
 * 
 * @param obj the input line to validate 
 */
function inLineVal(obj){
	// if this line is null skip out and check nothing (thus the validation() method)
	if($(obj).val()!=""){
		// FIRST NAME VALIDATION
		if(obj.name == "firstName"){
			// checking if first name contains anything other than letters
			if(regex.nameFail.test($("#firstName").val())){
				specError($(obj),"Name must be letters only");
			} else {
				success($(obj),"Good");
			}
		}
		// LAST NAME VALIDATION
		if(obj.name == "lastName"){
			// checking if last name contains anything other than letters
			if(regex.nameFail.test($("#lastName").val())){
				specError($(obj),"Name must be letters only");
			} else {
				success($(obj),"Good");
			}
		}
		// USERNAME VALIDATION
		if(obj.name == "username"){
			// checking if username contains anything other than numbers and letters
			if(regex.alphaNumFail.test($("#username").val())){
				specError($(obj),"username must be alphanumeric");
			} else {
				// if the name is valid check it against the database
				ajaxUsername();
			}
		}
		// EMAIL VALIDATION
		if(obj.name == "email"){
			// checking to see if email clears the half-assed validation we do
			// here with a crap excuse for a regex
			if(!regex.email.test($("#email").val())){
				specError($(obj),"Invalid email");
			} else {
				// if the address is "valid" check it against the database
				ajaxEmail();
			}
		}
		// PASSWORD VALIDATION
		if(obj.name == "password"){
			// make sure the is at least one digit, capital or special char 
			if(!regex.password.test($("#password").val())){
				// make sure that the password is atleast 8 chars long
				if($("#password").val().length<8){
					specError($(obj),"your password must at least 8 characters long");
				} else {
					specError($(obj),"your password must contain an uppercase letter, number or special character");
				}
			} else {
				success($(obj),"Good");
			}
			// if the password confirmation box isn't empty validate it too
			if($("#passwordConfirm").val()!=""){
				obj=$("#passwordConfirm")[0];
			}
		}
		// PASSWORD CONFIRMATION VALIDATION
		if(obj.name == "passwordConfirm"){
			// if this does not match the password box
			if($("#password").val()!=$("#passwordConfirm").val()){
				specError($(obj),"passwords must match");
			} else {
				success($(obj),"Matches");
			}
		}	
	}
}
// short validation
function validate(){
	return !regex.nameFail.test($("#firstName").val()) 
	&& !regex.nameFail.test($("#lastName").val()) 
	&& !regex.alphaNumFail.test($("#username").val()) 
	&& regex.email.test($("#email").val()) 
	&& regex.password.test($("#password").val())
	&& $("#password").val()==$("#passwordConfirm").val();
}
/**
 * AJAX call to check email availability
 * <br><br>
 * The data will only ever be true or false
 */
function ajaxEmail(){
	if($("#email").val()!=""){
		$.ajax("/register/email/",{
			data:{email:$("#email").val()},
			type:"GET",
			dataType:"text",
			success: function(data){
				if(data=="true"){
					successMsg($("#email"));
				} else {
					specError($("#email"),"email in use");
				}
			}
		});
	}
}
/**
 * AJAX call to check username availability
 * <br><br>
 * The data will only ever be true or false
 */
function ajaxUsername(){
	if($("#username").val()!=""){
		$.ajax("/register/user/",{
			data:{username:$("#username").val()},
			type:"GET",
			dataType:"text",
			success: function(data){
				if(data=="true"){
					successMsg($("#username"));
				} else {
					specError($("#username"),"username in use");
				}
			}
		});
	}
} 
$(document).ready(function(){
	$("#submit").click(ajaxPost);
	// Any time an input filed loses focus attempt to validate it
	$("input").blur(function(){inLineVal(this)});
	// if enter is pressed on the keyboard attempt to send
	$("form").keypress(function(event) {
		  if (event.which == 13) {
		     ajaxPost();
		  }
		});
});