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
					<div class="panel-heading">Ticket Details</div>
					<div class="panel-body">
						<div class="ranger-policy">
						<c:forEach items="${ticketDetails}" var="ticket">
							<table>
								<tr>	<td width=20%>Ticket ID 	</td>	<td>:&nbsp;<c:out value="${ticket.ticket_id}" />	</td>	</tr>
								<tr>	<td>Requestor 	</td>	<td>:&nbsp;<c:out value="${ticket.requestor}" />	</td>	</tr>
								<tr>	<td>Type		</td>	<td>:&nbsp;<c:out value="${ticket.ticket_type}" />	</td>	</tr>
								<tr>	<td>Start Time	</td>	<td>:&nbsp;<c:out value="${ticket.req_start}" />	</td>	</tr>
								<tr>	<td>End Time	</td>	<td>:&nbsp;<c:out value="${ticket.req_end}" />		</td>	</tr>
								<tr>	<td>Status		</td>	<td>:&nbsp;<c:out value="${ticket.ticket_status}" /></td>	</tr>
								<tr>	<td>Details		</td>	<td>&nbsp;</td>	</tr>
								<tr>	<td colspan=2> <c:out value="${ticket.ticket_text}" /> </td>	</tr>
							</table>
						</c:forEach>
						
						</div>
					</div>
				</div>
				<br />
			</div>
		</div>
	</div>

	<jsp:directive.include file="./footer.jsp" />
	<jsp:directive.include file="./about.jsp" />
</body>
</html>