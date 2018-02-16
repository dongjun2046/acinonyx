
<% if (session.getAttribute("wings-logged-logstate") == null || session.getAttribute("wings-logged-logstate") != "loggedin") {
	response.sendRedirect("login.jsp");
	} %>