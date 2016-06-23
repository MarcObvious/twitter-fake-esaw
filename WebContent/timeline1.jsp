<%@ page import="models.BeanTweet" %>
<%@ page import="java.util.ArrayList" %>
<script type="text/javascript">
    $(document).ready(function() {
        $('#navigation').load('menu.jsp');
    });
</script>

<%
    if (request.getAttribute("timeline") != null) {

        ArrayList<BeanTweet> list = (ArrayList<BeanTweet>) request.getAttribute("timeline");
        for(BeanTweet b : list) {

%>

<div class="col-xs-3">
    <div class="panel panel-default">
        <div class="panel-heading"><%=b.getTitle() %></div>
        <div class="panel-body">
            <strong><%=b.getUser() %></strong>  wrote at <%=b.getDatetweet() %>:
            <p> <%=b.getTweet()%> </p>

            <%
                if (session.getAttribute("user") != null) {
            %>

            <button id="btnr<%=b.getIdtweet()%>" type="button" title="Retweet" class="btn btn-success" > <i class="fa fa-retweet" aria-hidden="true"></i></button>

            <script>
                $( "#btnr<%=b.getIdtweet()%>" ).click(function() {
                    $('#content').load('tweetcontroller', { method: "retweet", id_tweet:  <%=b.getIdtweet()%>, username: "<%=b.getUser()%>" });
                });
            </script>

            <%
                }

                if (b.getUser().equals(session.getAttribute("user"))) {
            %>


            <button id="btne<%=b.getIdtweet()%>" type="button" title="Edit" class="btn btn-success" > <i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>

            <script>
                $( "#btne<%=b.getIdtweet()%>" ).click(function() {
                    $('#content').load('tweetcontroller', { method: "edittweet", id_tweet:  <%=b.getIdtweet()%> });
                });
            </script>

            <%
                }

                if (b.getUser().equals(session.getAttribute("user")) || "1".equals(session.getAttribute("is_admin"))) {
            %>

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