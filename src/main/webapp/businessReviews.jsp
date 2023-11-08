<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <title>Business Reviews</title>
</head>
<body>

<div class="container theme-showcase" role="main">
    <div class="text-right">
        <p>
            <i class="fas fa-user"></i> <!-- User icon from Font Awesome -->
            <c:out value="${user.getUserName()}"/>
        </p>
    </div>
    <div class="jumbotron">
        <h1>Reviews of <c:out value="${business.getBusinessName()}"/></h1>
    </div>

    <div id="businessListedHours">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>No.</th>
                <th>Comment</th>
                <th>Comment Stars</th>
                <th>Username</th>
                <th>Created Time</th>
            </tr>
            </thead>
            <c:forEach items="${reviewList}" var="review" varStatus="status">
                <tbody>
                <tr>
                    <td>${status.count}</td>
                    <td><c:out value="${review.getComment()}"/></td>
                    <fmt:formatNumber value="${review.getCommentStars()}" pattern="#.00" var="formattedStars" />
                    <td><c:out value="${formattedStars}"/></td>
                    <td><c:out value="${review.getUser().getUserName()}"/></td>
                    <fmt:parseDate value="${review. getCreatedTime()}" pattern="EEE MMM dd HH:mm:ss z yyyy" var="parsedEndTime"/>
                    <td><fmt:formatDate value="${parsedEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>
    <br><br>

    <div>
        <form action="addreview" method="post">
            <input type="hidden" name="businessId" value="${business.getBusinessId()}">
            <input type="hidden" name="userId" value="${user.getUserId()}">
            Comment:
            <br>
            <textarea name="comment" rows="4" cols="50"></textarea>
            <br><br>
            Comment Stars: <input type="number" name="commentStars" min="0" max="5" style="width: 5%">
            <br><br>
            <input type="submit" value="Add Review"/>
        </form>
    </div>

</div>
</body>
</html>
