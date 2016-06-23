<%@ page import="models.BeanTweet" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>

<script type="text/javascript" src="jquery/jquery.validate.js"></script>
<script>
	$(document).ready(function(){
				$("#tweetForm").validate({
					submitHandler: function(form) {
						$('#content').load('tweetcontroller',$("#tweetForm").serialize());
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
	BeanTweet tweet = null;
	if (request.getAttribute("tweet")!=null) {
		tweet = (BeanTweet)request.getAttribute("tweet");
	}
	else {
		tweet = new BeanTweet();
	}
%>


<div class="col-xs-4"></div>
<div class="col-xs-4">
	<form class="form-group" id=tweetForm action="" method="POST">

		<input  placeholder="Tweet Title" class="form-control input-md" type="text" name="title" id="title" value="<%=tweet.getTitle()%>" />

		<textarea  placeholder="Tweet" class="form-control input-md" name="tweet" id="tweet" rows="4" cols="50"><%=tweet.getTweet()%></textarea>

		<hr>
		<h3>Privacity:</h3>

		<label for="privacity_0">Public </label><input type="radio" name="privacity" id="privacity_0" value=0 <% if (tweet.getPrivacity() == 0){%> checked <% } %> />
		<label for="privacity_1">Private </label><input type="radio" name="privacity" id="privacity_1" value=1 <% if (tweet.getPrivacity() == 1){%> checked <% } %> /><br>

		<input  class="form-control input-sm" name="idtweet"
			   value="<%=tweet.getIdtweet()%>" id="idtweet" type="hidden"/>

		<input name="sumbit" type="submit" value="Enviar" class="btn btn-default">
	</form>
</div>
<div class="col-xs-4"></div>
