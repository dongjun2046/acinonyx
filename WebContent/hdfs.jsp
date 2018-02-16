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
	<div class="container main-container container-fixed">
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-3">
				<jsp:directive.include file="./left-panel.jsp" />
			</div>
			<div class="col-lg-10 col-md-10 col-sm-9">

				<div class="panel panel-primary">
					<div class="panel-heading">HDFS Layout</div>
					<div class="panel-body">
						<table class="table">
							<tr>
								<td><img src="./custom/images/hadoop-hdfs-post.jpg" /></td>
								<td>Please Specify the list of directories that has to be
									created. <br />Note - <br />1. All the entries should start
									with as per - "&nbsp; <u> hdfs.restriction.beginswith </u>&nbsp;
									" parameter <br />2. Avoid using platform keywords like <b>hadoop,
										yarn, hive, hbase, oozie, spark-submit</b> etc. to avoid
									complications.
									<hr />
									<div>
										<h3>Specify folders in multiple lines</h3>
										<form action="processhdfs" method="post">
											<textarea name="hdfs_layout" class=" code" required> </textarea>
											<br /> <br /> <input type="submit" value="Process"
												class="form-control btn btn-login" required />
										</form>
									</div>
								</td>
							</tr>
						</table>
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