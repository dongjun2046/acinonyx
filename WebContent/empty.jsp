<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wings</title>
<link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="./bootstrap/css/bootstrap-theme.min.css">
<script src="./bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="./custom/css/custom.css">
</head>

<body>
	<div class="navbar navbar-inverse navbar-fixed-top shadow">
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
		<%session.invalidate(); %>
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<br /> Server Database issue. Immediately reach out to Support. <br />
						Email - wings.iti@daimler.com <br />
						<br /> &lt;&lt; <a href="login.jsp"> Home</a>
					</div>
					<div class="panel-body"></div>
				</div>
			</div>
		</div>
	</div>

	<jsp:directive.include file="./footer.jsp" />
	<jsp:directive.include file="./about.jsp" />

</body>
</html>