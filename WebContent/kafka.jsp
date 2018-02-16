<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>acinonyx</title>
<link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css">
<script src="./custom/js/jquery-3.1.1.min.js"></script>
<script src="./bootstrap/js/bootstrap.min.js"></script>
<script language="Javascript" type="text/javascript">
 
        function onlyNos(e, t) {
            try {
                if (window.event) {
                    var charCode = window.event.keyCode;
                }
                else if (e) {
                    var charCode = e.which;
                }
                else { return true; }
                if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                    return false;
                }
                return true;
            }
            catch (err) {
                alert(err.Description);
            }
        }
 
        function validate_form()
        {
        valid = true;

        if($('input[type=checkbox]:checked').length == 0)
        {
            alert ( "ERROR! Please select at least one checkbox" );
            valid = false;
        }

        return valid;
        }
        
    </script>
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
					<div class="panel-heading">Kafka Topic Creation</div>
					<div class="panel-body">
						<div class="ranger-policy">
							<form method="post" action="./kafkatopicacl"
								onsubmit="return validate_form();">
								<table>
									<tr>
										<td width=10%>Topic Name</td>
										<td><input type=text name="topicname" size="20"
											maxlength="20" required /></td>
									</tr>
									<tr>
										<td>Partitions</td>
										<td><input type=text name="partition" size="20"
											maxlength="1" value=1 required
											onkeypress="return onlyNos(event,this);" /></td>
									</tr>
									<tr>
										<td>Replication</td>
										<td><input type=text name="repl" size="20" maxlength="1"
											value=1 required onkeypress="return onlyNos(event,this);" /></td>
									</tr>
									<tr>
										<td>Allow User</td>
										<td><input type=text name="princ" size="20" required /></td>
									</tr>
									<tr>
										<td>Permissions</td>
										<td>&nbsp; <label><input type="checkbox"
												name="perms" value="read">&nbsp; Read</label> <label><input
												type="checkbox" name="perms" value="write">&nbsp;
												Write </label>
										</td>
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