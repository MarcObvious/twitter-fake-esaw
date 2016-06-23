<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="false"%>

<script type="text/javascript">
    $(document).ready(function() {
        $(".menu").click(function(event) {
            $('#content').load('Content',{content: $(this).attr('id')});
        });
    });
</script>


<%HttpSession session = request.getSession(false);
    System.out.println("cargamos menu.jsp");
  /*  System.out.println("        (menu.jsp)Sesion:"+session);*/
//System.out.println("Sesion(user):"+session.getAttribute("user"));
    if ((session != null) && (session.getAttribute("user")!=null)) {

%>
<table class="table text-center">
    <tr>
        <td> <a class="menu" id="/index.jsp" href=#> Home <i class="fa fa-home" aria-hidden="true"></i></a> </td>
        <td> <a class="menu" id="logout.jsp" href=#> Logout <i class="fa fa-sign-out" aria-hidden="true"></i></a> </td>
        <td> <a class="menu" id="tweet.jsp" href=#> New Tweet <i class="fa fa-pencil-square-o" aria-hidden="true"></i></a> </td>
        <td> <a class="menu" id="userarea.jsp" href=#> User Area <i class="fa fa-user" aria-hidden="true"></i> </a> </td>
    </tr>
</table>
<% }
else {%>
<table class="table text-center">
    <tr>
        <td> <a class="menu" id="index.jsp" href=#> Home <i class="fa fa-home" aria-hidden="true"></i></a> </td>
        <td> <a class="menu" id="register.jsp" href=#> Registration <i class="fa fa-user" aria-hidden="true"></i></a> </td>
        <td> <a class="menu" id="login.jsp" href=#> Login <i class="fa fa-sign-in" aria-hidden="true"></i></a> </td>
    </tr>
</table>
<%}; %>
