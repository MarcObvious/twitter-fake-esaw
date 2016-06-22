<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="models.BeanUser" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.ResultSet" %>

<script type="text/javascript" src="jquery/jquery.validate.js"></script>
<script>
    $(document).ready(function(){
        $("#userareaForm").validate({

            submitHandler: function(form) {
                $('#content').load('userareacontroller',$("#userareaForm").serialize());
            },
            rules: {
                user: {
                    required: true,
                    minlength: 6
                },
                name: {
                    required: true,
                    lettersonly: true,
                    minlength: 2
                },
                surname: {
                    required: true,
                    lettersonly: true,
                    minlength: 2
                },
                bday: {
                    date: true
                },
                mail: {
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
                }
            }
        });
    });
</script>

<%
    BeanUser user = null;
    if (request.getAttribute("user")!=null) {
        user = (BeanUser)request.getAttribute("user");
        System.out.print("Si user bean\n");
    }
    else {
        user = new BeanUser();
        System.out.print("No user bean\n");
    }
%>

<div class="col-xs-12">

    <form class="form-group" id=userareaForm action="" method="POST">


        <div class="col-xs-12 col-lg-6">

            <h2>Camps Obligatoris:</h2>

            <input placeholder="User id" class="form-control input-sm" type="text" name="user"
                   value="<%=user.getUser()%>" id="user" readonly/>

            <input placeholder="User name" class="form-control input-md" type="text" name="name"
                   value="<%=user.getName()%>" id="name"/>

            <input placeholder="User surname" class="form-control input-md" type="text" name="surname"
                   value="<%=user.getSurname()%>" id="surname"/>

            <input placeholder="BirthDay" class="form-control input-md" type="date" name="bday" value="<%=user.getBday()%>" id="bday">

            <input  placeholder="Email" class="form-control input-md" type="text" name="mail" value="<%=user.getMail()%>" id="mail" readonly/>

        </div>

        <div class="col-xs-12 col-lg-6">

            <h2>Camps Opcionals: </h2>

            <input  placeholder="User Second Surname" class="form-control input-md" type="text" name="surname2" value="<%=user.getSurname2()%>" id="surname2"/>

            <textarea  placeholder="Description" class="form-control input-md" name="description" id="description" rows="4" cols="50"><%=user.getDescription()%></textarea>

            <textarea  placeholder="Likes and Interests"  class="form-control input-md" name="likes" id="likes" rows="4" cols="50"><%=user.getLikes()%></textarea>

            <h4>Gender:</h4>

            <div class="input-group">
            <input  type="radio" name="gender" id="radio_0" value=0 <% if (user.getGender() == 0){%> checked <% } %>/><label
                for="radio_0"> Other </label>
            <input  type="radio" name="gender" id="radio_1" value=1 <% if (user.getGender() == 1){%> checked <% } %>/><label
                for="radio_1"> Male </label>
            <input  type="radio" name="gender" id="radio_2" value=2 <% if (user.getGender() == 2){%> checked <% } %>/><label
                for="radio_2"> Female </label>
            </div>

        </div>

        <input class="btn btn-default btn-lg" name="submit" type="submit" value="Guardar">
    </form>

    <script type="text/javascript">
        $(document).ready(function() {
            $('#following').load('followcontroller', { method: "following" });
        });
    </script>

    <script type="text/javascript">
        $(document).ready(function() {
            $('#nofollowing').load('followcontroller', { method: "nofollowing" });
        });
    </script>

    <div id="following">

    </div>
    <div id="nofollowing">

    </div>
<%--    <div class="col-xs-12 col-lg-6">
        <h2>Who are you following?</h2>

        <%
            if (request.getAttribute("following") != null) {
                ResultSet result = (ResultSet) request.getAttribute("following");

                try {
                    while (result.next()) {

        %>
        <div class="col-xs-6">
            <div class="panel panel-default">
                &lt;%&ndash;<div class="panel-heading"><%=result.getString("title") %></div>&ndash;%&gt;
                <div class="panel-body">
                  &lt;%&ndash;  <strong><%=result.getString("user") %></strong>  since <%=result.getString("since") %>:&ndash;%&gt;


                    <button type="button" title="Unfollow" class="btn btn-danger" > <i class="fa fa-trash" aria-hidden="true"></i></button>

                </div>

            </div>
        </div>

        <%
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        else {
        %>
            No users following.
        <%
            }
        %>
    </div>--%>

  <%--  <div class="col-xs-12 col-lg-6">
        <h2>Who can you follow?</h2>

        <%
            if (request.getAttribute("following") != null) {
                ResultSet result2 = (ResultSet) request.getAttribute("following");

                try {
                    while (result2.next()) {

        %>
        <div class="col-xs-6">
            <div class="panel panel-default">
                &lt;%&ndash;<div class="panel-heading"><%=result.getString("title") %></div>&ndash;%&gt;
                <div class="panel-body">
                    <strong><%=result2.getString("user") %></strong>  since <%=result2.getString("since") %>:


                    &lt;%&ndash; <button type="button" title="Retweet" class="btn btn-success" > <i class="fa fa-retweet" aria-hidden="true"></i></button>
                     <button type="button" title="Edit" class="btn btn-success" > <i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>&ndash;%&gt;
                    <button type="button" title="Unfollow" class="btn btn-success" > <i class="fa fa-plus" aria-hidden="true"></i></button>

                </div>

            </div>
        </div>

        <%
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        else {
        %>
            No users following.
        <%
            }
        %>
    </div>--%>

</div>

