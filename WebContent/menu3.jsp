<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="false"%>

<script type="text/javascript">
    $(document).ready(function() {
        $(".menu").click(function(event) {
            $('#content').load('Content',{content: $(this).attr('id')});
        });
    });
</script>

<table class="table text-center">
    <tr>
        <td> <a class="menu" id="index.jsp" href=#> Home <i class="fa fa-home" aria-hidden="true"></i></a> </td>
        <td> <a class="menu" id="register.jsp" href=#> Registration <i class="fa fa-user" aria-hidden="true"></i></a> </td>
        <td> <a class="menu" id="login.jsp" href=#> Login <i class="fa fa-sign-in" aria-hidden="true"></i></a> </td>
    </tr>
</table>