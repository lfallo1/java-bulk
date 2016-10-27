<nav role="navigation" class="navbar navbar-inverse">
  <div class="navbar-header">
	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#toggle-items">
		<span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
	</button>  
    <a class="navbar-brand" href="/">Home</a>
  </div>
  <div id="toggle-items" class="navbar-collapse collapse navbar-responsive-collapse">
   	<% if(request.getUserPrincipal()!=null) { %>
	   	<% if((request.isUserInRole("ROLE_ADMIN"))){ %>
	  		<ul class="nav navbar-nav navbar-left">
	  			<li><a href="/adminreview">Review</a></li>
	  			<li><a href="/adminreport">Reporting</a></li>
	  			<li><a href="/admintools">Tools</a></li>
	  		</ul>
	  	<%}%>
   		<ul class="nav navbar-nav navbar-right">
   			<li><a href="/logout">Logout</a></li>
   	    </ul>	    
   	<% } %>
  </div>
</nav>