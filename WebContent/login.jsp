<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>acinonyx</title>
<link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="./bootstrap/css/bootstrap-theme.min.css">
<script src="./bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="./custom/css/custom.css">
<script>
$(function() {

    $('#login-form-link').click(function(e) {
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
});
</script>
</head>

<body>
	<div class="navbar-fixed-top navbar shadow">
		<div class="container nav-container">
			<div class="navbar-header">

				<button class="navbar-toggle" type="button" data-toggle="collapse"
					data-target="#navbar-main">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
		</div>
	</div>
	<div class="container main-container">
		<%session.invalidate();
%>
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<img src="./custom/images/hadoop_fast.png" style="height: 100px;">
						<hr>
						<br />Login
						<hr>
					</div>
					<div class="panel-body">
						<form id="login-form" action="login_validate" method="post"
							role="form" style="display: block;">
							<div class="form-group">
								<input type="text" name="username" id="username" tabindex="1"
									class="form-control" placeholder="Username" value="" required
									autofocus>
							</div>
							<div class="form-group">
								<input type="password" name="password" id="password"
									tabindex="2" class="form-control" placeholder="Password"
									required>
							</div>
							<div class="form-group errmsg">
								<%
								String message = request.getParameter("message");
								if(message!=null && message.equals("failure")){
									out.print("Login Failed..!");
								} else if(message!=null && message.equals("noaccess")){
									out.print("UnAuthorized Access..!");
								} else if(message!=null && message.equals("error")){
									out.print("Unknown error. Please contact support team...!");
								}
								%>
							</div>
							<div class="form-group">
								<center>
									<input type="submit" name="login-submit" id="login-submit"
										tabindex="3" class="form-control btn btn-login" value="Log In">
								</center>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:directive.include file="./footer.jsp" />

</body>
</html>