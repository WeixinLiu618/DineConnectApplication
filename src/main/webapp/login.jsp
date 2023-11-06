<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Login</title>
</head>
<body>
<div class="container theme-showcase">

    <div class="jumbotron">
        <h1>Welcome to DineConnect</h1>
    </div>
    <%--Form for user or business login--%>
    <form action="userpage" method="post">
        Input your UserID: <input type="text" name="userId"/>
        <input type="submit" name="userLogin" value="Login as User" style="margin-left: 10px;"/>
        <div style="clear: both;"></div>
    </form>
    <br><br>
    <form action="businesspage" method="post">
        Input your BusinessID: <input type="text" name="businessId"/>
        <input type="submit" name="businessLogin" value="Login as Business" style="margin-left: 10px;"/>
        <div style="clear: both;"></div>
    </form>


</div>


<!-- Bootstrap -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>
