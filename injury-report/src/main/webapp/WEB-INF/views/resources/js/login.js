(function(){
/**
 * 
 */
function checkUsernameEmpty(){
	var isValid = false;
	if($('#inputUsername').val().length > 0 && $('#inputPassword').val().length > 0){
		isValid = true;
	}
	else{
		$('#errorMessage').empty().append('<div id="emptyFieldErrors" class="alert alert-danger">Username and password fields are required</div>');
	}
	return isValid;
}
})();