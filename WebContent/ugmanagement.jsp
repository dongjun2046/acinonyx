<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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

	<div class="container main-container">
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-3">
				<jsp:directive.include file="./left-panel.jsp" />
			</div>
			<div class="col-lg-10 col-md-10 col-sm-9">

				<div class="panel panel-primary">
					<div class="panel-heading">User & Group Creation</div>
					<div class="panel-body">
						NOTE -<br /> Please provide the users in a comma separated format
						and the group name(only One)
						<div id="err-msg">
							e.g. user1,user2 <br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;group
						</div>
						<div class="ranger-policy">
							<form method="post" action="usermgmnt">
								<table>
									<tr>
										<td width=10%>Username(s)</td>
										<td><input type=text name="unames" size="20"
											maxlength="110" required /></td>
									</tr>
									<tr>
										<td>Group</td>
										<td><input type=text name="gnames" size="20"
											maxlength="20" required /></td>
									</tr>
									<tr>
										<td>Folders</td>
										<td><textarea name="os_layout" class="code" required> </textarea></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><br />
										<input type="submit" name="login-submit" id="login-submit"
											class="form-control btn btn-login" value="Create" required></td>
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