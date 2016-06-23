<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ page import="models.BeanUser" %>

<script type="text/javascript" src="jquery/jquery.validate.js"></script>
<script>
	$(document).ready(function(){
		$("#registerForm").validate({
			submitHandler: function(form) {
				$('#content').load('registercontroller',$("#registerForm").serialize());
			},
			rules: {
				user: {
					required: true,
					minlength: 6
				},
				name: {
					lettersonly: true,
					required: true,
					minlength: 2
				},
				surname: {
					lettersonly: true,
					required: true,
					minlength: 2
				},
				bday: {
					date: true,
					required: true
				},
				passwd: {
					required: true,
					minlength: 8,
					maxlength: 20
				},
				passwd2: {
					required: true,
					equalTo: "#passwd"
				},
				mail: {
					required: true,
					email: true
				},
				surname2: {
					lettersonly: true,
					minlength: 2
				},
				description: {
					minlength: 300,
					maxlength: 600
				},
				likes: {
					minlength: 150,
					maxlength: 600
				},
			}
		});
	});
</script>

<%
	BeanUser user = null;
	if (request.getAttribute("user")!=null) {
		user = (BeanUser)request.getAttribute("user");
	}
	else {
		user = new BeanUser();
	}
%>

<div class="col-xs-12">
	<div class="register">

		<form class="form-group" id=registerForm action="" method="POST">


			<div class="col-xs-12 col-lg-6">

				<h2 class="hd2-register">Camps Obligatoris:</h2>

				<input placeholder="User id" class="form-control input-sm" type="text" name="user"
					   value="<%=user.getUser()%>" id="user"/>
				<%
					if (user.getError()[0] == 1) {
				%>
				<span>The username already exists in our DB!</span>
				<%
					}
				%>
				<br>
				<input placeholder="User name" class="form-control input-md" type="text" name="name"
					   value="<%=user.getName()%>" id="name"/>

				<br>
				<input placeholder="User surname" class="form-control input-md" type="text" name="surname"
					   value="<%=user.getSurname()%>" id="surname"/>

				<br>
				<input placeholder="BirthDay" class="form-control input-md" type="date" name="bday" value="<%=user.getBday()%>" id="bday">

				<br>
				<input  placeholder="Password" class="form-control input-md" type="password" name="passwd" value="<%=user.getPasswd()%>" id="passwd"/>

				<br>
				<input placeholder="Repeat Password"  class="form-control input-md" type="password" name="passwd2" value="<%=user.getPasswd2()%>" id="passwd2"/>

				<br>
				<input  placeholder="Email" class="form-control input-md" type="text" name="mail" value="<%=user.getMail()%>" id="mail"/>
				<%
					if (user.getError()[1] == 1) {
				%>
				<span class="error">This email already exists in our DB!</span>
				<%
					}
				%>

			</div>

			<div class="col-xs-12 col-lg-6">

				<h2 class="hd2-register">Camps Opcionals: </h2>

				<input  placeholder="User Second Surname" class="form-control input-md" type="text" name="surname2" value="<%=user.getSurname2()%>" id="surname2"/>
				<br>

				<textarea  placeholder="Description" class="form-control input-md" name="description" id="description" rows="4" cols="50"><%=user.getDescription()%></textarea>

				<br>
				<textarea  placeholder="Likes and Interests"  class="form-control input-md" name="likes" id="likes" rows="4" cols="50"><%=user.getLikes()%></textarea>

				<br>
				<strong>Gender:</strong>
				<br>

				<label for="radio_0" class="radio-inline"><input  type="radio" name="gender" id="radio_0" value=0 <% if (user.getGender() == 0){%> checked <% } %> > Other</label>
				<label for="radio_1" class="radio-inline"><input  type="radio" name="gender" id="radio_1" value=1 <% if (user.getGender() == 1){%> checked <% } %>> Male</label>
				<label for="radio_2" class="radio-inline"><input  type="radio" name="gender" id="radio_2" value=2 <% if (user.getGender() == 2){%> checked <% } %>> Female</label>


			</div>
			<input class="btn btn-default btn-register btn-md" name="submit" type="submit" value="Enviar">
		</form>
	</div>

</div>

