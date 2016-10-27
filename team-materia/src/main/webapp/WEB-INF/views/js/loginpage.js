/**
 * loginpage javascript
 */
$(document).ready(function(){
	
	$("input").keypress(function(event) {
	    if (event.which == 13) {
	        event.preventDefault();
	        $("#button-login").trigger("click");
	    }
	});
	
	//When user clicks submit on login form, send post
	//request to loginPost web service, passing the form-login as the paramater
	$('#button-login').on('click', function(){
		var accountLocked = 'This account is currently locked due to multiple unsuccessful login attempts. Please contact the administrator';
		var userPassNotFound = 'Username/Password could not be found';
		 $.ajax({
	         type: "POST",
	         url: "/login",
	         data: $("#form-login").serialize()
		 }).done(function(data){
			 //if the object returned states the login was valid, redirect to loginsuccess controller
			  if(data.valid===true){
				  $('#errorPlaceholder').html('');
				  window.location.href="/loginsuccess";
			  }
			  //If login was not successful, display appropriate error
			  else{				  
				  if(data.accountLocked){
					  $('#errorPlaceholder').html(accountLocked);
				  }
				  else{
					  $('#errorPlaceholder').html(userPassNotFound);
				  }
				  $('input[name="password"]').val("");
			  }
		 });
	});
});