<script type="text/javascript">
	$(document).ready(function() {
		$('#navigation').load('menu.jsp');
	});
</script>

Logged in!
<%if ((String) session.getAttribute("user") != null) {
				System.out.println("User: " + (String) session.getAttribute("user") + " is logged in.");
%>

<%
	if (session.getAttribute("username") != null) {
%>
<p>
	Username: <strong><%=session.getAttribute("username")%></strong>
</p>
<%
	}
%>

<%
	if (session.getAttribute("date") != null) {
%>
<p>
	Session date:
	<%=session.getAttribute("date")%></p>
<%
	}
%>

<%
	if (session.getAttribute("result") != null) {
%>
<p>User info:</p><%=session.getAttribute("result")%>
<%
	}
}
%>
