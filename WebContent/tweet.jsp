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


<div class="col-md-3 col-lg-4"></div>
<div class="col-xs-12 col-md-6 col-lg-4">
	<form class="form-group" id=tweetForm action="" method="POST">
		<h2 class="hd2-login">New Tweet: </h2>
		<input  placeholder="Tweet Title" class="form-control input-md" type="text" name="title" id="title" value="<%=tweet.getTitle()%>" />
		<br>
		<textarea  placeholder="Tweet" class="form-control input-md" name="tweet" id="tweet" rows="4" cols="50"><%=tweet.getTweet()%></textarea>
		<hr>

		<strong>Privacity:</strong>
		<br>

		<label for="privacity_0" class="radio-inline"><input  type="radio" name="privacity" id="privacity_0" value=0 <% if (tweet.getPrivacity() == 0){%> checked <% } %> > Public</label>
		<label for="privacity_1" class="radio-inline"><input  type="radio" name="privacity" id="privacity_1" value=1 <% if (tweet.getPrivacity() == 1){%> checked <% } %>> Private</label>

		<input  class="form-control input-sm" name="idtweet"
			   value="<%=tweet.getIdtweet()%>" id="idtweet" type="hidden"/>
		<hr>
		<input name="sumbit" type="submit" value="Enviar" class="btn btn-tweet btn-default">
	</form>
</div>
<div class="col-md-3 col-lg-4"></div>
