<!DOCTYPE html>
<html>
<head>
<title>Register</title>
<%@ include file="partial/head.jsp" %>
<script src="/js/register.js"></script>
</head>
<body>
<%@ include file="partial/logout.jsp" %>
<div class="jumbotron">
<h1>Register</h1>
</div>
<div class=""><div class=""></div><div class=""><div id="error"></div></div></div>
<div id=main class="row">
<div class="col-lg-2"></div>
<div class="col-lg-6 center">
  <form id="register" class="form-horizontal">
    <fieldset>
    <legend>Registration Form</legend>
    <div class="form-group">
      <label for="firstName" class="col-lg-3 control-label">First Name</label>
      <div class="col-lg-3">
        <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name" />
      </div>
    </div>
    <div class="form-group">
      <label for="lastName" class="col-lg-3 control-label">Last Name</label>
      <div class="col-lg-3">
        <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" />
      </div>
    </div>
    <div class="form-group">
      <label for="username" class="col-lg-3 control-label">User Name</label>
      <div class="col-lg-3">
        <input type="text" class="form-control" id="username" name="username" placeholder="User Name" />
      </div>
    </div>
    <div class="form-group">
      <label for="email" class="col-lg-3 control-label">Email</label>
      <div class="col-lg-3">
        <input type="text" class="form-control" id="email" name="email" placeholder="Email" />
      </div>
    </div>
    <div class="form-group">
      <label for="password" class="col-lg-3 control-label">Password</label>
      <div class="col-lg-3">
        <input type="password" class="form-control" id="password" name="password" placeholder="Password" />
      </div>
    </div>
     <div class="form-group">
      <label for="passwordConfirm" class="col-lg-3 control-label">Confirm Password</label>
      <div class="col-lg-3">
        <input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm" placeholder="Confirm Password" />
      </div>
    </div>
    </fieldset>
  </form>
  <div class="col-lg-4"></div>
  <div id="submit" class="btn btn-primary">Submit</div>
  <a href="/" class="btn btn-danger">Cancel</a>
  </div>
</div>
</body>
</html>
