<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Wings</title>
<link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css">
<script src="./custom/js/jquery-3.1.1.min.js"></script>
<script src="./bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="./custom/css/custom.css">

</head>

<body>
	<% if (session.getAttribute("wings-logged-logstate") == null || session.getAttribute("wings-logged-logstate") != "loggedin") {
	response.sendRedirect("login.jsp");
	} %>
	<jsp:directive.include file="./menu.jsp" />

	<div class="container main-container">
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-3">
				<jsp:directive.include file="./left-panel.jsp" />
			</div>
			<div class="col-lg-10 col-md-10 col-sm-9">

				<div class="panel panel-primary">
					<div class="panel-heading">Ranger : HDFS</div>
					<div class="panel-body">
						<div class="ranger-policy">
							<h3>Fetch Policy for HDFS Resource</h3>
							<form method="post" action="fetchRanger">
								<table>
									<tr>
										<td>HDFS Resource</td>
										<td width=80%><input type=text name="resource" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><input type="submit" name="login-submit"
											id="login-submit" class="form-control btn btn-login"
											value="Fetch Policies" required></td>
									</tr>
								</table>
							</form>
							<hr />
							<h3>Policy creation for HDFS</h3>
							<hr />
							<br />

							<form method="post" action="">
								<table>
									<tr>
										<td>HDFS Resource</td>
										<td width=80%><input type=text name="resource" /></td>
									</tr>
									<tr>
										<td>User</td>
										<td width=80%><input type=text name="user" /></td>
									</tr>
									<tr>
										<td>Group</td>
										<td width=80%><input type=text name="group" /></td>
									</tr>
									<tr>
										<td>Access Type</td>
										<td width=80%><label class="checkbox-inline"> <input
												type="checkbox" value="">Read
										</label> <label class="checkbox-inline"> <input
												type="checkbox" value="">Write
										</label> <label class="checkbox-inline"> <input
												type="checkbox" value="">Execute
										</label></td>
									</tr>
									<tr>
										<td>Recursive</td>
										<td width=80%><label class="radio-inline"> <input
												type="radio" name="optradio">Yes
										</label> <label class="radio-inline"> <input type="radio"
												name="optradio">No
										</label></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><input type="submit" name="login-submit"
											id="login-submit" class="form-control btn btn-login"
											value="Create Policy"></td>
									</tr>
								</table>
							</form>
						</div>
						<br />Note - <br />1. All the entries should start with "/org" <br />2.
						Admin access is not provided for the policies
						<hr />
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:directive.include file="./footer.jsp" />
	<jsp:directive.include file="./about.jsp" />
</body>
</html>