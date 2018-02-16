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
						<h3>Ranger Policy creation or HDFS</h3>
						<hr />
						<br />

						<div class="ranger-policy">
							<table>
								<tr>
									<td>Policy Name</td>
									<td width=80%><input type=text name="policyname" /></td>
								</tr>
								<tr>
									<td>HDFS Resource</td>
									<td width=80%><input type=text name="policyname" /></td>
								</tr>
								<tr>
									<td>User</td>
									<td width=80%><input type=text name="policyname" /></td>
								</tr>
								<tr>
									<td>Group</td>
									<td width=80%><input type=text name="policyname" /></td>
								</tr>
								<tr>
									<td>Access Type</td>
									<td width=80%><input type=text name="policyname" /></td>
								</tr>
								<tr>
									<td>Recursive</td>
									<td width=80%><input type=text name="policyname" /></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td><input type="submit" name="login-submit"
										id="login-submit" class="form-control btn btn-login"
										value="Create Policy"></td>
								</tr>
							</table>
						</div>
						<br />Note - <br />1. All the entries should start with "/org" <br />2.
						Admin access is not provided for the policies
						<hr />
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer class="footer"> &nbsp;&nbsp;| &nbsp;Designed and
		Developed by &copy;&nbsp;ITI/GS &nbsp;| &nbsp;Contact&nbsp;|
		&nbsp;Submit a bug&nbsp;| &nbsp; </footer>
	<jsp:directive.include file="./about.jsp" />
</body>
</html>