<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>acinonyx</title>
<script src="./custom/js/jquery-3.1.1.min.js"></script>
<script src="./bootstrap/js/bootstrap.min.js"></script>
<script src="./charts/Chart.min.js"></script>
<link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css"
	media="all" type="text/css">
<link rel="stylesheet" href="./bootstrap/css/bootstrap-table.css"
	media="all" type="text/css">
<link rel="stylesheet" href="./bootstrap/css/jquery.dataTables.min.css"
	media="all" type="text/css">
<link rel="stylesheet"
	href="./bootstrap/css/dataTables.bootstrap.min.css" media="all"
	type="text/css">
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

					<div class="panel-body">
						<div id="ticket-container" class="container">
							<ul class="nav nav-tabs">
								<!-- <li class="active"> <a  href="#1" data-toggle="tab">Chart View</a></li> -->
								<li class="active"><a href="#2" data-toggle="tab">Standard
										View</a></li>
							</ul>

							<div class="tab-content ">
								<!-- 
							<div class="tab-pane active" id="1">
			          			<canvas id="bar-chart" width="100" height="40"></canvas>
			          			<script>
			          			new Chart(document.getElementById("bar-chart"), {
			          			    type: 'bar',
			          			    data: {
			          			      labels: ["Views", "Kafka", "EdgeNode", "HDFS", "Hive", "Ranger", "AD", "HBase"],
			          			      datasets: [
			          			        {
			          			          label: "Tickets Count : ",
			          			          backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850", "#8e9fa2","#d7d3b9"],
			          			          data: [24,57,34,84,33,20,14,22],
			          			          borderWidth: 1
			          			        }
			          			      ]
			          			    },
			          			    options: {
			          			      legend: { display: false },
			          			      title: {
			          			        display: true,
			          			        text: 'Tickets counts - Last 10 Days'
			          			      },
			          			    scales: {
			          		            xAxes: [{
			          		                // Change here
			          		            	barPercentage: 0.2,
			          		            	categorySpacing: 0.1
			          		            }]
			          		        }
			          			    }
			          			});
			          			</script>
							</div> --->

								<div class="tab-pane active" id="2">
									<br />
									<div id="ticket-data" class="container">
										<table id="tickets" class="display stripe table table-striped"
											width="100%">
											<thead>
												<tr>
													<th>Ticket ID</th>
													<th>Component</th>
													<th>Requestor</th>
													<th>Creation Date</th>
													<th>Completion Date</th>
													<th>Status</th>
												</tr>
											</thead>
											<tfoot>
												<tr></tr>
											</tfoot>
											<tbody>
												<c:forEach items="${ticketDetails}" var="ticket">
													<tr>
														<td><a
															href="loadticketdetails?ticketid=<c:out value="${ticket.ticket_id}" />">
																<c:out value="${ticket.ticket_id}" />
														</a></td>
														<td><c:out value="${ticket.ticket_type}" /></td>
														<td><c:out value="${ticket.requestor}" /></td>
														<td><c:out value="${ticket.req_start}" /></td>
														<td><c:out value="${ticket.req_end}" /></td>
														<td><c:out value="${ticket.ticket_status}" /></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>


				</div>
			</div>
		</div>




	</div>
	<jsp:directive.include file="./footer.jsp" />
	<jsp:directive.include file="./about.jsp" />
	<script src="./bootstrap/js/jquery.dataTables.min.js"></script>
	<script>
$(document).ready(function(){
    $('[data-toggle="popover"]').popover({
    	container: 'table' });   
});
</script>
	<script>
$(document).ready(function() {
    $('#tickets').DataTable( {
        "order": [[ 3, "desc" ]],
        "pageLength": 10
    } );
} );
</script>
</body>
</html>