<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="false"%>

<script type="text/javascript">
    $(document).ready(function() {
        $(".menu").click(function(event) {
            $('#content').load('Content',{content: $(this).attr('id')});
        });
    });
</script>

<table>
    <tr>
        <td> <a class="menu" id="index.jsp" href=#> Home </a> </td>
        <td> <a class="menu" id="form.jsp" href=#> Registration </a> </td>
        <td> <a class="menu" id="login.jsp" href=#> Login </a> </td>
    </tr>
</table>