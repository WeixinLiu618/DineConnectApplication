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
        <form action="userpage" method="post">
            <input type="hidden" name="userId" value="${user.getUserId()}">
            longitude(-180 to 180): <input type="text" name="longitude"/>
            &nbsp
            latitude(-90 to 90): <input type="text" name="latitude">
            &nbsp
            <input type="submit" value="Search Nearby Restaurants"/>
        </form>
    </div>

    <div id="restaurantsNearby">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>No.</th>
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

            <c:forEach items="${businessList}" var="business" varStatus="status">
                <tbody>
                <tr>
                    <td>${status.count}</td>
                    <td><c:out value="${business.getBusinessName()}"/></td>
                    <fmt:formatNumber value="${business.getBusinessStars()}" pattern="#.00" var="formattedRating"/>
                    <td><c:out value="${formattedRating}"/></td>
                    <td><c:out value="${business.getAddress()}"/></td>
                    <td><a href="businesslistedhours?businessId=<c:out value="${business.getBusinessId()}"/>">Listed
                        Hours</a></td>
                    <td>
                        <a href="businesspromotions?businessId=<c:out value="${business.getBusinessId()}"/>">promotions</a>
                    </td>
                    <td>
                        <a href="businessreviews?businessId=<c:out value="${business.getBusinessId()}"/>&userId=${user.getUserId()}">reviews</a>
                    </td>
                    <td>
                        <a href="businesstips?businessId=<c:out value="${business.getBusinessId()}"/>&userId=${user.getUserId()}">tips</a>
                    </td>
                    <td>
                        <a href="businesscheckins?businessId=<c:out value="${business.getBusinessId()}"/>&userId=${user.getUserId()}">checkins</a>
                    </td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>


</div>
</body>
</html>
