<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/main.css" />
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<script type="text/javascript"
	src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="//cdn.datatables.net/1.10.2/js/jquery.dataTables.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		getUsers();
		$(".add-user").on("click", function() {
			$("#new-user-error").html("");
			$.post("/users", $("form#newUser").serialize(), function() {
				getUsers();
			}).fail(function(err) {
				$("#new-user-error").html(err.statusText);
			});

		});

		$("#deleteBtn").on("click", function() {

			$.ajax({
				type : "DELETE",
				url : "/users"
			}).done(function(msg) {
				//Success
				$("#deleteMsg").html(msg);
			}).fail(function() {
				//Called when there is an error code returned. (400's and 500's)
				// http://en.wikipedia.org/wiki/List_of_HTTP_status_codes 
				$("#deleteMsg").html("Delete Failed.");
			}).always(function() {
				//Called no matter what
				getUsers();
			});

		});
	});

	/**
	 *Custom object for interacting with a list of users. Encapsulates
	 *functionality to reduce method complexity.
	 */
	var Users = function() {
		var userList = [];

		return {
			setUserList : function(newUserList) {
				if (newUserList !== null && newUserList !== undefined) {
					userList = newUserList;
				}
			},
			findUserByID : function(id) {
				for (var i = 0; i < userList.length; i++) {
					var user = userList[i];
					if (user.userID === id) {
						return user;
					}
				}
				return null;
			}
		}

	}();

	
	//Updates the user table and the drop down list for manager options.
	function getUsers() {
		$.get("/users", function(data) {

			Users.setUserList(data);
			var managersOptions = '<option value="0">Select Manager</option>';
			var tableBody = '';
			for (var i = 0; i < data.length; i++) {
				var item = data[i];

				var managerID = 0;
				if (item.manager !== null && item.manager !== undefined) {

					if (typeof item.manager === "number") {
						managerID = item.manager;
					} else {
						managerID = item.manager.userID
					}
				}
				var manager = Users.findUserByID(managerID)
				tableBody += '<tr>' + '<td>' + item.userID + '</td>' + '<td>' + item.firstName + '</td>' + '<td>' + item.lastName + '</td>' + '<td>' + (manager === null ? "" : manager.firstName + ' ' + manager.lastName) + '</td>' + '</tr>';

				//Add entries to select list so it contains the current users in the database.
				managersOptions += '<option value="'+item.userID+'">' + item.lastName + ', ' + item.firstName + '</option>';

			}
			$('#user-table tbody').html(tableBody);
			$("#managers").html(managersOptions).removeAttr("disabled");

			$('#user-table').dataTable();

		});

	}
</script>
</head>
<body>


	<fieldset>
		<form id="newUser" action="">

			<div>
				<p id="new-user-error" class="error"></p>
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
				<select id="managers" name="manager.userID" disabled>
				</select>
			</div>
			<div>
				<button type="button" id="add-user-btn" class="add-user">Add
					New User</button>
			</div>
		</form>
	</fieldset>
	<br />
	<div class="delete">
		<button id="deleteBtn" type="button">Remove All</button>
		<span id="deleteMsg"></span>
	</div>
	<div>

		<table id="user-table" class="display">
			<thead>
				<tr>
					<th>UserID</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>Manager</th>
				</tr>
			</thead>
			<tbody>
			</tbody>


		</table>


	</div>

</body>
</html>