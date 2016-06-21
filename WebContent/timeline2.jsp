<script>
    $(document).ready(function(){
        $('#content').load('timelinecontroller');
    });
</script>


<h2>patata</h2>
<%--<div id="cosa" >

</div>&ndash;%&gt;--%>

<%=session.getAttribute("timeline")%>
<%
    if (session.getAttribute("timeline") != null) {
        System.out.print("PUTAAAAAAAAAAAAAAAAa\n");
        System.out.print(session.getAttribute("timeline"));
%>
<%=session.getAttribute("timeline")%>
<%
    }
%>


<h2>patata</h2>


