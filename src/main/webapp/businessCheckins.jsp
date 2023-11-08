<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <title>Business Check-Ins</title>
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
        <h1>Check-Ins of <c:out value="${business.getBusinessName()}"/></h1>
    </div>

    <div id="businessCheckins">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>No.</th>
                <th>Check-In Time</th>
                <th>UserName</th>
            </tr>
            </thead>

            <c:forEach items="${checkinList}" var="checkin" varStatus="status">
                <tbody>
                <tr>
                    <td>${status.count}</td>
                    <fmt:parseDate value="${checkin.getCheckInTime()}" pattern="EEE MMM dd HH:mm:ss z yyyy"
                                   var="parsedCheckInTime"/>
                    <td><fmt:formatDate value="${parsedCheckInTime}" pattern="MMMM dd, yyyy HH:mm:ss z"/></td>
                    <td><c:out value="${checkin.getUser().getUserName()}"/></td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>
    <br><br>

    <div>
        <form action="addcheckin" method="post">
            <input type="hidden" name="businessId" value="${business.getBusinessId()}">
            <input type="hidden" name="userId" value="${user.getUserId()}">
            <input type="submit" value="Add Check-In"/>
        </form>
    </div>

</div>
</body>
</html>
