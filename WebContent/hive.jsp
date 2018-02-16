<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>acinonyx</title>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
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
					<div class="panel-heading">Hive - Table Creation</div>
					<div class="panel-body">
						<form action="processhivequery" method="post"
							accept-charset="ANSI">
							<table class="table">
								<tr>
									<td width="30px"><img src="./custom/images/hive.png" /></td>
									<td align="left"><label for="sel1">Choose the
											Database</label> <select class="form-control" name="dbname">
											<c:forEach items="${databaseList}" var="db_name">
												<option>
													<c:out value="${db_name}" />
												</option>
											</c:forEach>
									</select> <br />
									<br /> <label>Table Create Statement</label> <textarea
											class="code" rows="10" name="query"></textarea></td>
								</tr>
								<tr>
									<td></td>
									<td><input type="submit" value="Execute"
										class="form-control btn btn-login" /></td>
								</tr>
							</table>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:directive.include file="./footer.jsp" />
	<jsp:directive.include file="./about.jsp" />
</body>
</html>