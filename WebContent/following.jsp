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
<div class="col-xs-12 col-md-6">
    <div class="panel panel-default">
        <div class="panel-body">
            <div class="col-xs-12">
                <div class="col-xs-9">
                    User <strong><%=b.getName()%></strong>  since: <cite><%=b.getSince() %></cite>
                </div>
                <div class="col-xs-3">
                    <button type="button" title="Unfollow" class="btn btn-danger" id="btnu<%=b.getIdfollowed()%>" > <i class="fa fa-trash" aria-hidden="true"></i></button>

                    <script>
                        $( "#btnu<%=b.getIdfollowed()%>" ).click(function() {
                            $('#following').load('followcontroller', { method: "unfollow", id_followed:  <%=b.getIdfollowed()%> });
                        });
                    </script>

                </div>
            </div>
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


