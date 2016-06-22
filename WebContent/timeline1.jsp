<%@ page import="models.BeanNewtweet" %>
<%@ page import="java.util.ArrayList" %>
<script type="text/javascript">
    $(document).ready(function() {
        $('#navigation').load('menu.jsp');
    });
</script>

<%
    if (request.getAttribute("timeline") != null) {

        ArrayList<BeanNewtweet> list = (ArrayList<BeanNewtweet>) request.getAttribute("timeline");
        for(BeanNewtweet b : list) {

%>

<div class="col-xs-3">
    <div class="panel panel-default">
        <div class="panel-heading"><%=b.getTitle() %></div>
        <div class="panel-body">
            <strong><%=b.getUser() %></strong>  wrote at <%=b.getDatetweet() %>:
            <p> <%=b.getTweet()%> </p>

            <button type="button" title="Retweet" class="btn btn-success" > <i class="fa fa-retweet" aria-hidden="true"></i></button>

            <%
                if (b.getUser().equals(session.getAttribute("username"))) {
            %>

            <button id="btne<%=b.getIdtweet()%>" type="button" title="Edit" class="btn btn-success" > <i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>

            <script>
                $( "#btne<%=b.getIdtweet()%>" ).click(function() {
                    $('#content').load('newtweetcontroller', { method: "edittweet", id_tweet:  <%=b.getIdtweet()%>, title: "<%=b.getTitle() %>", tweet: "<%=b.getTweet()%>" });
                });
            </script>

            <button id="btnd<%=b.getIdtweet()%>" type="button" title="Delete tweet" class="btn btn-danger" > <i class="fa fa-trash" aria-hidden="true"></i></button>

            <script>
                $( "#btnd<%=b.getIdtweet()%>" ).click(function() {
                    $('#content').load('timelinecontroller', { method: "deletetweet", id_tweet:  <%=b.getIdtweet()%> });
                });
            </script>
            <%
                }
            %>
        </div>
    </div>
</div>

<%
    }
}
else {
%>

No tweets to show.
<%
    }
%>





