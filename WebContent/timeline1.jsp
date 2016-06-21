<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<script type="text/javascript">
    $(document).ready(function() {
        $('#navigation').load('menu.jsp');
    });
</script>


<%
    if (request.getAttribute("timeline") != null) {
        ResultSet  result = (ResultSet) request.getAttribute("timeline");

        try {
            while (result.next()) {

%>
<div class="col-xs-3">
    <div class="panel panel-default">
        <div class="panel-heading"><%=result.getString("title") %></div>
        <div class="panel-body">
            <strong><%=result.getString("user") %></strong>  wrote at <%=result.getString("date_tweet") %>:
            <p> <%=result.getString("tweet") %> </p>

            <button type="button" title="Retweet" class="btn btn-success" > <i class="fa fa-retweet" aria-hidden="true"></i></button>

            <%
                if (result.getString("id_user").equals(session.getAttribute("user_id"))) {
            %>
            <button type="button" title="Edit" class="btn btn-success" > <i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>
            <button type="button" title="Delete tweet" class="btn btn-danger" > <i class="fa fa-trash" aria-hidden="true"></i></button>
            <%
                }
            %>


        </div>

    </div>
</div>

<%
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    else {
%>
    No tweets to show.
<%
            }
%>





