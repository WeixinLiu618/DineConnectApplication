<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>User History</title>
</head>
<body>
<div class="container theme-showcase" role="main">
    <div class="jumbotron">
<%--        <h2>${user.getUserId()}</h2>--%>
        <h2>User History for ${user.getUserName()}</h2>
    </div>

    <h2>Reviews</h2>
    <div id="review-for-user">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Business Name</th>
                <th>Comment</th>
                <th>Comment Star</th>
                <th>Created Time</th>
                <th>Delete</th>
            </tr>
            </thead>
            <c:forEach items="${reviewList}" var="review">
                <tbody>
                <tr>
                    <td><c:out value="${businessNameMap.get(review.getBusiness().getBusinessId())}"/></td>
                    <td><c:out value="${review.getComment()}"/></td>
                    <td><c:out value="${review.getCommentStars()}"/></td>
                    <fmt:parseDate value="${review.getCreatedTime()}" pattern="EEE MMM dd HH:mm:ss z yyyy" var="parsedTime"/>
                    <td><fmt:formatDate value="${parsedTime}" pattern="MMMM dd, yyyy HH:mm:ss"/></td>
                    <td><a href="reviewdelete?reviewId=<c:out value="${review.getReviewId()}"/>">Delete</a></td>

                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>
    <br><br>

    <h3>Tips</h3>
    <div id="tip-for-user">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Business Name</th>
                <th>Text</th>
                <th>Created Time</th>
                <th>Delete</th>
            </tr>
            </thead>
            <c:forEach items="${tipList}" var="tip">
                <tbody>
                <tr>
                    <td><c:out value="${businessNameMap.get(tip.getBusiness().getBusinessId())}"/></td>
                    <td><c:out value="${tip.getText()}"/></td>
                    <fmt:parseDate value="${tip.getCreatedTime()}" pattern="EEE MMM dd HH:mm:ss z yyyy" var="parsedTime"/>
                    <td><fmt:formatDate value="${parsedTime}" pattern="MMMM dd, yyyy HH:mm:ss"/></td>
                    <td><a href="tipdelete?tipId=<c:out value="${tip.getTipId()}"/>">Delete</a></td>

                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>
    <br><br>

    <h3>Check-Ins</h3>
    <div id="checkin-for-user">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Business Name</th>
                <th>Check-In Time</th>
            </tr>
            </thead>
            <c:forEach items="${checkinList}" var="checkin">
                <tbody>
                <tr>
                    <td><c:out value="${businessNameMap.get(checkin.getBusiness().getBusinessId())}"/></td>
                    <fmt:parseDate value="${checkin.getCheckInTime()}" pattern="EEE MMM dd HH:mm:ss z yyyy" var="parsedTime"/>
                    <td><fmt:formatDate value="${parsedTime}" pattern="MMMM dd, yyyy HH:mm:ss"/></td>

                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
