<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<div class="panel-heading">Ranger : Hive</div>
					<div class="panel-body ranger-list">
						<c:forEach items="${policyDetailsList}" var="policyDetailsList">
							<table border=1px>
								<thead>
									<tr>
										<th>ID</th>
										<th>Policy Name</th>
										<th>Resource</th>
										<th>Recursive</th>
									</tr>
								</thead>
								<tr>
									<td width="10%"><c:out
											value="${policyDetailsList.policyId}" /></td>
									<td width="30%"><c:out
											value="${policyDetailsList.policyName}" /></td>
									<td width="30%"><c:out
											value="${policyDetailsList.resourceName}" /></td>
									<td width="30%"><c:out
											value="${policyDetailsList.isRecursive}" /></td>
								</tr>
								<tr>

									<td colspan=4><span><a data-toggle="collapse"
											data-target="#policy-id-<c:out value="${policyDetailsList.policyId}" />">Permission
												List</a></span> <span>
											<div
												id="policy-id-<c:out value="${policyDetailsList.policyId}" />"
												class="collapse">
												<table border=1px cellspacing=0 cellpadding=0 class="shadow">
													<thead>
														<tr>
															<th>User List</th>
															<th>Group List</th>
															<th>Permissions</th>
														</tr>
													</thead>
													<c:forEach items="${policyDetailsList.permList}"
														var="permList">
														<tr>
															<td width=30%><c:out value="${permList.uList}" /></td>
															<td width=30%><c:out value="${permList.gList}" /></td>
															<td width=30%><c:out value="${permList.pList}" /></td>
														</tr>
													</c:forEach>
												</table>
											</div>
									</span></td>

								</tr>
							</table>
							<br />
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:directive.include file="./footer.jsp" />
	<jsp:directive.include file="./about.jsp" />
</body>
</html>