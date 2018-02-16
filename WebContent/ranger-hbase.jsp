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
					<div class="panel-heading">Ranger : HBase</div>
					<div class="panel-body"></div>
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