<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ page import="models.BeanLogin" %>

<head>

	<script type="text/javascript" src="jquery/jquery.validate.js"></script>
	<script>
		$(document).ready(function(){
					$("#loginForm").validate({
						submitHandler: function(form) {
							$('#content').load('logincontroller',$("#loginForm").serialize());
						},
						rules: {
							user: {
								required: true,
								minlength: 6
							},
							password: {
								required: true,
								minlength: 8,
								maxlength: 20
							}
						}
					});
				}
		);
	</script>

</head>
<body>

	<%
BeanLogin login = null;
if (request.getAttribute("login")!=null) {
	login = (BeanLogin)request.getAttribute("login");
}
else {
	login = new BeanLogin();
}
%>
<div class="col-xs-4"></div>
<div class="col-xs-4">
	<form class="form-group" id=loginForm action="" method="POST">

		<label for="user"> User id </label> <input class="form-control input-md" type="text" name="user" value="<%=login.getUser() %>" id="user" />
		<%
			if ( login.getError()[0] == 1) {
		%>
		<span class="error"> The username doesn't exists in our DB! </span>
		<%
			}
		%>

		<label for="password"> User passwd </label>
		<input class="form-control input-md" type="password" name="password" value="<%=login.getPassword() %>" id="password"/>
		<%
			if ( login.getError()[1] == 1) {
		%>
		<span class="error"> The password is incorrect! </span>
		<%
			}
		%>
		<input name="sumbit" type="submit" value="Enviar" class="btn btn-default">
	</form>
</div>
<div class="col-xs-4"></div>
