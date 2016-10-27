<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update User</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/main.css" />
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		updateUserList();
		$("select#users").on("change", function() {
			var userID = this.value;
			$.ajax({
				type : "GET",
				url : "/users/" + userID
			}).done(function(user) {
				$("#userID").val(user.userID);
				$("#firstName").val(user.firstName);
				$("#lastName").val(user.lastName);
				var managerID;
				if (user.manager !== undefined && user.manager !== null) {
					managerID = user.manager.userID;
				}
				updateManagerList(managerID);
				updateStaffList(user.userID);

			}).fail(function() {
				//Called when there is an error code returned. (400's and 500's)
				// http://en.wikipedia.org/wiki/List_of_HTTP_status_codes 
				$("#errorMsg").html("User not found.");
			});
		});

		$(".update-user").on("click", function() {
			$.ajax({
				type : "PUT",
				url : "/users",
				data : $("#form-update-user").serialize()
			}).done(function() {
				//Success
				$("#userID").val("");
				$("#firstName").val("");
				$("#lastName").val("");

			}).fail(function(err) {

				$("#update-user-error").html(err.statusText);

			}).always(function() {
				updateUserList();
			});

		});
	});

	function updateStaffList(managerID) {
		$.ajax({
			type : "GET",
			url : "/users/" + managerID + "/staff"
		}).done(function(data) {
			var userList = ''
			for (var i = 0; i < data.length; i++) {
				var item = data[i];
				//Add entries to select list so it contains the current users in the database.
				userList += '<li>' + item.fullName + '</li>';
			}
			$("#staff").html(userList);
		});
	}

	function updateUserList() {
		$.ajax({
			type : "GET",
			url : "/users"
		}).done(function(data) {
			var userList = '<option value="0">Select User to Update</option>';
			for (var i = 0; i < data.length; i++) {
				var item = data[i];
				//Add entries to select list so it contains the current users in the database.
				userList += '<option value="'+item.userID+'">' + item.fullName + '</option>';

			}
			$("#users").html(userList);
		});

	}
	function updateManagerList(selected) {

		$.ajax({
			type : "GET",
			url : "/users"
		}).done(function(data) {
			var managersOptions = '<option value="0">Select Manager</option>';
			for (var i = 0; i < data.length; i++) {
				var item = data[i];
				var selectedText = "";
				if (selected !== undefined && selected === item.userID) {
					selectedText = "selected";
				}
				//Add entries to select list so it contains the current users in the database.
				managersOptions += '<option '+selectedText+' value="'+item.userID+'">' + item.fullName + '</option>';

			}
			$("#managers").html(managersOptions);

		});

	}
</script>
</head>
<body>



	<div>
		<select id="users">
			<option selected="selected" value="">Select User to Update</option>

		</select>
	</div>
	<form id="form-update-user">
		<input type="hidden" name="userID" id="userID" />

		<div>
			<p id="update-user-error" class="error"></p>
		</div>

		<div>
			<label for="firstName">First Name:</label> <input id="firstName"
				type="text" name="firstName" />
		</div>
		<div>
			<label for="lastName">Last Name:</label> <input id="lastName"
				type="text" name="lastName" />
		</div>

		<div>
			<select id="managers" name="manager.userID">
				<option selected="selected" value="">Select Manager</option>
			</select>
		</div>
		<div>
			<button type="button" class="update-user">Update User</button>
		</div>
	</form>

	<div>
		<h3>Staff they Manage:</h3>
		<ul id="staff">

		</ul>
	</div>



</body>
</html>