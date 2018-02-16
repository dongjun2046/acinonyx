<div class="navbar-fixed-top navbar shadow">
	<div class="container nav-container">
		<div class="navbar-header">
			<div class="navbar-brand">
				<span class='logo'>acinonyx :: <%= session.getAttribute("wings-logged-cluster-name") %></span>
			</div>
			<button class="navbar-toggle" type="button" data-toggle="collapse"
				data-target="#navbar-main">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-right navbar-nav">
				<li><a href="" data-toggle="dropdown"> <span
						class="glyphicon glyphicon-user"></span> <%= session.getAttribute("wings-logged-username") %>
						<span class="caret"></span>
				</a>

					<ul class="dropdown-menu">
						<li><a href="#" id="myBtn"><span
								class="glyphicon glyphicon-cloud-upload"></span> About Acinonyx</a>
						</li>
						<li class="divider"></li>
						<li><a href="login.jsp"><span
								class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</div>