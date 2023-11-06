<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>User Page</title>
</head>
<body>

<div class="container theme-showcase" role="main">
    <div class="jumbotron">
        <h1>Hi, <c:out value="${user.getUserName()}"/></h1>
        <fmt:parseDate value="${user.getYelpingSince()}" pattern="EEE MMM dd HH:mm:ss z yyyy" var="parsedDate"/>
        <h2>You have yelped since <fmt:formatDate value="${parsedDate}" pattern="MMMM dd, yyyy"/></h2>
        <br>
        <h4><a href="userhistory">History</a></h4>
    </div>

    <div>
        <form action="userlogin" method="post">
            longitude: <input type="text" name="longitude"/>
            latitude: <input type="text" name="latitude">
            <input type="submit" value="Search Nearby Restaurants"/>
        </form>
    </div>

    <div id="restaurantsNearby">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Restaurant nearby</th>
                <th>Ratings</th>
                <th>Address</th>
                <th>ListedHours</th>
                <th>Promotions</th>
                <th>Reviews</th>
                <th>Tips</th>
                <th>Checkins</th>
            </tr>
            </thead>
            <%--TODO: forEach of nearyby retaurants showing for user--%>
        </table>
    </div>


</div>
</body>
</html>
