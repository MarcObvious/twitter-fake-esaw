<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="false"%>

<script type="text/javascript">
    $(document).ready(function() {
        $(".menu").click(function(event) {
            $('#content').load('Content',{content: $(this).attr('id')});
        });
    });
</script>


<table width="100%">
    <tr>
        <td> <a class="menu" id="index.jsp" href=#> Home </a> </td>
        <td> <a class="menu" id="logout.jsp" href=#> Logout </a> </td>
        <td> <a class="menu" id="tweet.jsp" href=#> New Tweet </a> </td>
        <td> <a class="menu" id="userarea.jsp" href=#> User Area</a> </td>
    </tr>
</table>	
