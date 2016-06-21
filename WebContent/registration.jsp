<script type="text/javascript">
$(document).ready(function() {
        $('#navigation').load('menu.jsp');   
});
</script>

Registration done!

<% if (request.getAttribute("result") != null) {%>
	<p>Users info: </p><%= request.getAttribute("result") %>
<%} %>