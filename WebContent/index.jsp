<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <title> Practica Final </title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"  crossorigin="anonymous">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

    <link rel="stylesheet" type="text/css" href="css/main.css" />

    <script type="text/javascript">
        $(document).ready(function() {
            $(".menu").click(function(event) {
                $('#content').load('Content',{content: $(this).attr('id')});
            });
        });
    </script>
</head>

<body>
<div id="wrapper" class="wrapper">
    <div class="container row-fluid">
        <div class="col-xs-12">
            <div class="col-xs-12">
                <div class="panel panel-default">
                    <div class="panel-heading text-center">
                        <h1 class="h1-logo">Twitter Fake ESAW <i class="fa fa-twitter" aria-hidden="true"></i></h1>
                    </div>
                </div>
            </div>
        </div>
        <%--NAVIGATOR--%>
        <div class="col-xs-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div id="navigation" class="navigation">
                        <%
                            if ((String) session.getAttribute("user") != null) {
                        %>

                        <jsp:include page="menu2.jsp" />
                        <%
                        }
                        else { %>
                        <jsp:include page="menu3.jsp" />
                        <%
                            }
                        %>
                    </>
                </div>
            </div>
        </div>

        <div class="col-xs-12">
            <div class="panel panel-default">
                <div class="panel-heading"><i class="fa fa-twitter" aria-hidden="true"></i></div>
                <div class="panel-body">
                    <div id="content">
                        <jsp:include page="timeline.jsp" />
                    </div>
                </div>

            </div>
        </div>

        <div class="col-xs-12">
            <div class="panel panel-default">
                <div class="panel-heading">Made with love from:</div>
                <div class="panel-body">
                    <div class="text-center">
                        <h2>Marc Mateu: 146756</h2>
                        <h2>Ignasi Larroca: 158633</h2>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</div>
</body>
</html>

<%--

<!-- Begin Wrapper -->
<div id="wrapper" class="container">

    <!-- Begin Header -->
    <div id="header" class="col-xs-12 panel panel-default">

        <h1>Twitter Fake ESAW</h1>

    </div>
    <!-- End Header -->

    <!-- Begin Navigation -->
    <div id="navigation">

        <%
            if ((String) session.getAttribute("user") != null) {
        %>

        <jsp:include page="menu2.jsp" />
        <%
        }
        else { %>
        <jsp:include page="menu3.jsp" />
        <%
            }
        %>

    </div>
    <!-- End Navigation -->

    <!-- Begin Faux Columns -->
    <div class="col-xs-12">

        <!-- Begin Left Column -->
        <div id="col1" class="col-xs-1">

      &lt;%&ndash;      <%
                if ((String) session.getAttribute("user") != null) {
            %>

            <jsp:include page="timeline.jsp" />
            <%
            }
            else { %>
            <jsp:include page="login.jsp" />
            <%
                }
            %>&ndash;%&gt;

        </div>
        <!-- End Left Column -->

        <div id="content" class="col-xs-10">

            <jsp:include page="timeline.jsp" />
        </div>

        <!-- Begin Content Column -->
        <div id="timeline">
            &lt;%&ndash; <a id="timeline.jsp" href=#>  timeline???????''''' </a>
             <h1>Timeline</h1>
             <br>
             <jsp:include page="timeline.jsp" />&ndash;%&gt;

        </div>
        <!-- End Content Column -->



        <!-- Begin Right Column -->
        <div class="col-xs-1">
            col2
        </div>
        <!-- End Right Column -->

    </div>
    <!-- End Faux Columns -->

    <!-- Begin Footer -->
    <div id="footer" class="panel panel-default">
        <div>
            <div class="panel-heading">Footer</div>

            <p>Made with love from:</p>
            <h2>Marc Mateu: 146756</h2>
            <h2>Ignasi Larroca: 158633</h2>

        </div>
    </div>
    <!-- End Footer -->

</div>
<!-- End Wrapper -->
</body>
</html>--%>
