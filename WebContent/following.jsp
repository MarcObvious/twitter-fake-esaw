<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="models.BeanFollow" %>
<%@ page import="java.util.ArrayList" %>

<h2>Who are you following?</h2>
<%
    if (request.getAttribute("following") != null) {

        ArrayList<BeanFollow> list = (ArrayList<BeanFollow>) request.getAttribute("following");

        for(BeanFollow b : list) {
%>
<div class="col-xs-6">
    <div class="panel panel-default">

        <div class="panel-body">
            <strong><%=b.getName()%></strong>  since <%=b.getSince() %>

            <button type="button" title="Unfollow" class="btn btn-danger" id="btnu<%=b.getIdfollowed()%>" > <i class="fa fa-trash" aria-hidden="true"></i></button>

            <script>
                $( "#btnu<%=b.getIdfollowed()%>" ).click(function() {
                    $('#following').load('followcontroller', { method: "unfollow", id_followed:  <%=b.getIdfollowed()%> });
                });
            </script>

        </div>

    </div>
</div>

<%
    }
}
else {
%>
No users following.
<%
    }
%>


