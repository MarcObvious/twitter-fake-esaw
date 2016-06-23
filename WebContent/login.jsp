<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ page import="models.BeanLogin" %>

<head>

	<%--	<script type="text/javascript" src="jquery/jquery-1.7.1.js"></script>--%>
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
<div class="col-md-3"></div>
<div class="col-md-6">
	<form class="form-group" id=loginForm action="" method="POST">
		<div class="col-xs-12">
			<div class="col-xs-12 col-md-3">
				<label id="userlabel"> User id </label>
			</div>
			<div class="col-xs-12  col-md-9">
				<input class="form-control input-md userlogin" type="text" name="user" value="<%=login.getUser() %>" id="user" />
				<%
					if ( login.getError()[0] == 1) {
				%>
				<span class="error"> The username doesn't exists in our DB! </span>
				<%
					}
				%>
			</div>
			<div class="col-xs-12  col-md-3">
				<label id="passwordlabel"> User passwd </label>
			</div>
			<div class=" col-xs-12 col-md-9">
				<input class="form-control input-md" type="password" name="password" value="<%=login.getPassword() %>" id="password"/>
				<%
					if ( login.getError()[1] == 1) {
				%>
				<span class="error"> The password is incorrect! </span>
				<%
					}
				%>
			</div>
			<div class="col-xs-12">
				<input name="sumbit" type="submit" value="Enviar" class="btn btn-login btn-default">
			</div>
		</div>
	</form>
</div>
<div class="col-md-3"></div>
