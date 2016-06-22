<%@ page import="models.BeanNewtweet" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>

<script type="text/javascript" src="jquery/jquery.validate.js"></script>
<script>
	$(document).ready(function(){
				$("#newTweetForm").validate({
					submitHandler: function(form) {
						$('#content').load('newtweetcontroller',$("#newTweetForm").serialize());
					},
					rules: {
						title: {
							required: true,
							minlength: 6
						},
						tweet: {
							required: true,
							minlength: 8,
							maxlength: 140
						}
					}
				});
			}
	);
</script>

<%
	BeanNewtweet newtweet = null;
	if (request.getAttribute("newtweet")!=null) {
		newtweet = (BeanNewtweet)request.getAttribute("newtweet");
	}
	else {
		newtweet = new BeanNewtweet();
	}
%>


<div class="col-xs-4"></div>
<div class="col-xs-4">
	<form class="form-group" id=newTweetForm action="" method="POST">

		<input  placeholder="Tweet Title" class="form-control input-md" type="text" name="title" id="title" value="<%=newtweet.getTitle()%>" />

		<textarea  placeholder="Tweet" class="form-control input-md" name="tweet" id="tweet" rows="4" cols="50"><%=newtweet.getTweet()%></textarea>

		<hr>
		<h3>Privacity:</h3>

		<label for="privacity_0">Public </label><input type="radio" name="privacity" id="privacity_0" value=0 <% if (newtweet.getPrivacity() == 0){%> checked <% } %> />
		<label for="privacity_1">Private </label><input type="radio" name="privacity" id="privacity_1" value=1 <% if (newtweet.getPrivacity() == 1){%> checked <% } %> /><br>

		<input  class="form-control input-sm" name="idtweet"
			   value="<%=newtweet.getIdtweet()%>" id="idtweet" type="hidden"/>

		<input name="sumbit" type="submit" value="Enviar" class="btn btn-default">
	</form>
</div>
<div class="col-xs-4"></div>
