<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>acinonyx</title>
<link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css">
<script src="./custom/js/jquery-3.1.1.min.js"></script>
<script src="./bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="./custom/css/custom.css">
</head>

<body>
	<jsp:directive.include file="./checkifloggedin.jsp" />
	<jsp:directive.include file="./menu.jsp" />
	<div class="container main-container container-fixed">
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-3">
				<jsp:directive.include file="./left-panel.jsp" />
			</div>
			<div class="col-lg-10 col-md-10 col-sm-9">

				<div class="panel panel-primary">
					<div class="panel-heading">Read Only access to Ambari UI</div>
					<div class="panel-body">
						<div class="ranger-policy">
							<form method="post" action="AmbariLDAPSync">
								<table>
									<tr>
										<td width=10%>Username</td>
										<td><input type=text name="unames" size="20"
											maxlength="110" required /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><br />
										<input type="submit" name="login-submit" id="login-submit"
											class="form-control btn btn-login" value="Proceed" required></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
				<br />
				<div class="panel panel-primary">
					<div class="panel-heading">Access to Ambari Views</div>
					<div class="panel-body">
						<div class="ranger-policy">
							<form method="post" action="./processviews">
								<table>
									<tr>
										<td width=10%>Username</td>
										<td><input type=text name="unameview" size="20"
											maxlength="110" required /></td>
									</tr>
									<tr>
										<td>Views</td>
										<td><br /> <label><input type="checkbox"
												name="views" value="file">&nbsp; File</label> <label><input
												type="checkbox" name="views" value="hive1">&nbsp;
												Hive </label> <label><input type="checkbox" name="views"
												value="hive2">&nbsp; Hive 2.0</label> <br />
										<br /> <label><input type="checkbox" name="views"
												value="tez">&nbsp; Tez</label> <label><input
												type="checkbox" name="views" value="pig">&nbsp; Pig</label>
											<label><input type="checkbox" name="views"
												value="oozie">&nbsp; Oozie</label><br />
										<br /> <label><input type="checkbox" name="views"
												value="storm">&nbsp; Storm</label> <label><input
												type="checkbox" name="views" value="yarn">&nbsp;
												Capacity Scheduler</label></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><br />
										<input type="submit" name="login-submit" id="login-submit"
											value="PROCEED"></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<jsp:directive.include file="./footer.jsp" />
	<jsp:directive.include file="./about.jsp" />
</body>
</html>