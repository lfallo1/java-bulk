(function(){
/**
 * The one and only real function that exists on this page
 */
function lookUp() {
	// validate
	if (yellAtTheUser()) {
		$.ajax("/injuredemployee/list", {
			type : "GET",
			data : {
				"firstName" : $("#firstName").val(),
				"lastName" : $("#lastName").val(),
				"id" : $("#employeeId").val()
			}
		}).done(function(data) {
			// if no employees are found display a no results found message
			if (data.length == 0) {
				$("#employeeList").empty()
					.append('<div class="col-lg-12 jumbotron text-center"><h1>No Results</h1></div>')
			} else {
				$("#employeeList").empty();
				for (var i = 0; i < data.length; i++) {
					// add a series of clickable buttons for the found employees
					$("#employeeList").append("<a class='btn btn-default' href='/injuryreporting?employeeId="
							+ data[i].employeeId + "&firstName="
							+ data[i].firstName + "&lastName="
							+ data[i].lastName + "'><h1>"
							+ data[i].lastName + ", "
							+ data[i].firstName + "</h1><p>"
							+ data[i].employeeId + "<br>"
							+ data[i].position.positionName
							+ "</p></a>");
				}
			}
		});
	} else {
		// if invalid fix it
		getRidOfThatJunk();
	}
}
/**
 * Validation - this will take care of throwing error messages
 * 
 * @returns true if valid
 */
function yellAtTheUser() {
	var error = "";
	if (/[^A-Za-z]/.test($("#firstName").val())
		|| /[^A-Za-z]/.test($("#lastName").val())) {
		error += "Names should consist of letters.\n";
	}
	if (/[^0-9]/.test($("#employeeId").val())) {
		error += "Id numbers should consist of numbers.";
	}
	if (error != "") {
		alert(error);
		return false;
	}
	return true;
}
function getRidOfThatJunk() {
	$("#firstName").val($("#firstName").val().replace(/[^A-Za-z]/g, ""));
	$("#lastName").val($("#lastName").val().replace(/[^A-Za-z]/g, ""));
	$("#employeeId").val($("#employeeId").val().replace(/[^0-9]/g, ""));
}
$(document).ready(function() {
	$("#lookUp").click(lookUp);
});
})();