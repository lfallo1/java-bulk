<%@ page import="com.catalyst.teammateria.timeclock.businesslayer.model.User" %>
<nav role="navigation" class="navbar navbar-inverse">
  <div class="navbar-header">
	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#toggle-items">
		<span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
	</button>  
    <a class="navbar-brand" href="/">Time Tracking</a>
  </div>
  <div id="toggle-items" class="navbar-collapse collapse navbar-responsive-collapse">
   	<% if(session.getAttribute("currentUser")!=null) { %>
	   	<% if(((User)session.getAttribute("currentUser")).getRole().getRoleId()==2){ %>
	  		<ul class="nav navbar-nav navbar-left">
	  			<li><a href="/accountconfig" id="accountconfig-nav">Account Config</a></li>
	  			<li><a href="/timemanagement" id="timemanagement-nav">Time Management</a></li>
	  			<li><a href="/reporting" id="reporting-nav">Reporting</a></li>
	  		</ul>
	  	<%}%>
   		<ul class="nav navbar-nav navbar-right">
   			<li><a href="/logout" id="logout-nav">Logout</a></li>
   	    </ul>	    
   	<% } %>
  </div>
</nav>
