<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Business Promotions</title>
</head>
<body>

<div class="container theme-showcase" role="main">
    <div class="jumbotron">
        <h1>Promotions of <c:out value="${business.getBusinessName()}"/></h1>
    </div>

    <div id="businessPromotions">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>No.</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Event</th>
            </tr>
            </thead>

            <c:forEach items="${promotionList}" var="promotion" varStatus="status">
                <tbody>
                <tr>
                    <td>${status.count}</td>
                    <fmt:parseDate value="${promotion.getStartTime()}" pattern="EEE MMM dd HH:mm:ss z yyyy" var="parsedStartTime"/>
                    <td><fmt:formatDate value="${parsedStartTime}" pattern="MMMM dd, yyyy"/></td>
                    <fmt:parseDate value="${promotion.getEndTime()}" pattern="EEE MMM dd HH:mm:ss z yyyy" var="parsedEndTime"/>
                    <td><fmt:formatDate value="${parsedEndTime}" pattern="MMMM dd, yyyy"/></td>
                    <td><c:out value="${promotion.getEvent()}"/></td>
                </tr>
                </tbody>
            </c:forEach>

        </table>
    </div>
</div>
</body>
</html>
