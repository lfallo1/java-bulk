<!DOCTYPE html>
<html>
<head>
<%@include file="partial/head.jsp" %>
<script src="/js/accountconfig.js"></script>
<title>Account Config</title>
</head>
<body>
<%@include file="partial/logout.jsp" %>
<div class="jumbotron"><h1>Account Config</h1></div>
<div id=main class="row">
<div class="col-lg-1"></div>
<div class="col-lg-5 center">
  <form id="config" class="form-horizontal">
    <fieldset>
    <legend>Account Config</legend>
    <div class="form-group">
      <label for="user" class="col-lg-3 control-label">User</label>
      <div class="col-lg-5">
        <select class="form-control" id="user" name="user.userId">
        	<option value="">Last, First</option>
        </select>
      </div>
      <div class="col-lg-3">
      	<div id="inactiveButton" class="btn btn-block btn-danger">Inactive Hidden</div>
      </div>
    </div>
    <div class="form-group">
      <label for="firstName" class="col-lg-3 control-label">First Name</label>
      <div class="col-lg-5">
        <input type="text" class="form-control" id="firstName" name="user.firstName" placeholder="First Name" />
      </div>
      <div class="col-lg-3">
      	<div id="formSwapButton" class="btn btn-block btn-primary">Add User</div>
      </div>
    </div>
    <div class="form-group">
      <label for="lastName" class="col-lg-3 control-label">Last Name</label>
      <div class="col-lg-5">
        <input type="text" class="form-control" id="lastName" name="user.lastName" placeholder="Last Name" />
      </div>
    </div>
    <div class="form-group">
      <label for="username" class="col-lg-3 control-label">Username</label>
      <div class="col-lg-5">
        <input type="text" class="form-control" id="username" name="user.username" placeholder="Username" />
      </div>
    </div>
    <div class="form-group">
      <label for="email" class="col-lg-3 control-label">Email</label>
      <div class="col-lg-5">
        <input type="text" class="form-control" id="email" name="user.email" placeholder="Email" />
      </div>
    </div>
    <div class="form-group">
      <label for="password" class="col-lg-3 control-label">Password</label>
      <div class="col-lg-5">
        <input type="password" class="form-control" id="password" name="password" placeholder="Password" />
      </div>
    </div>
    <div class="form-group" id="activeHide">
      <label class="col-lg-3 control-label">Active</label>
      <div class="col-lg-5">
        <div class="radio">
          <label>
            <input type="radio" name="user.active" id="active" value=true>
            Active
          </label>
        </div>
        <div class="radio">
          <label>
            <input type="radio" name="user.active" id="inactive" value=false>
            Inactive
          </label>
        </div>
      </div>
    </div>
    <div class="form-group">
      <label for="select" class="col-lg-3 control-label">Role</label>
      <div class="col-lg-5">
        <select class="form-control" id="role" name="user.role.roleId">
        	<option value="1">User</option>
        	<option value="2">Admin</option>
        </select>
      </div>
    </div>
   	</fieldset> 
  </form>
  <div class="col-lg-4"></div>
  <div id="submit" class="btn btn-primary">Update</div>
  <a href="/adminsplash" class="btn btn-danger">Cancel</a>
  </div>
  <div class="col-lg-5 center">
  <div id="errors">
  
  </div>
  </div>
</div>
</body>
</html>