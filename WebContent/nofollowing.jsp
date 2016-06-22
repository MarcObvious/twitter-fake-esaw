<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="models.BeanFollow" %>
<%@ page import="java.util.ArrayList" %>

<h2>Who are you not following?</h2>

<%
    if (request.getAttribute("nofollowing") != null) {

        ArrayList<BeanFollow> list = (ArrayList<BeanFollow>) request.getAttribute("nofollowing");

        for(BeanFollow b : list) {
%>
<div class="col-xs-6">
    <div class="panel panel-default">

        <div class="panel-body">
            <strong><%=b.getName()%></strong>  since <%=b.getSince() %>

            <button type="button" title="follow" class="btn btn-success" id="btnf<%=b.getIduser()%>"> <i class="fa fa-plus" aria-hidden="true"></i></button>

            <script>
                $( "#btnf<%=b.getIduser()%>" ).click(function() {
                    $('#following').load('followcontroller', { method: "follow", id_followed:  <%=b.getIduser()%> });
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
No users not following.
<%
    }
%>