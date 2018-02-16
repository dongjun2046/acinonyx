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
					<div class="panel-heading">Message :</div>
					<div class="panel-body">
						<div id="err-msg" style="overflow-y: scroll; height: 400px;">
							<c:forEach var="msgs" items="${msg}">
								<c:out value="${msgs}" />
								<br />
							</c:forEach>
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