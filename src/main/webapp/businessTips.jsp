<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <title>Business Tips</title>
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
        <h1>Tips of <c:out value="${business.getBusinessName()}"/></h1>
    </div>

    <div id="businessTips">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>No.</th>
                <th>Text</th>
                <th>Created Time</th>
                <th>Username</th>
            </tr>
            </thead>

            <c:forEach items="${tipList}" var="tip" varStatus="status">
                <tbody>
                <tr>
                    <td>${status.count}</td>
                    <td><c:out value="${tip.getText()}"/></td>
                    <fmt:parseDate value="${tip.getCreatedTime()}" pattern="EEE MMM dd HH:mm:ss z yyyy"
                                   var="parsedCreatedTime"/>
                    <td><fmt:formatDate value="${parsedCreatedTime}" pattern="MMMM dd, yyyy HH:mm:ss z"/></td>
                    <td><c:out value="${tip.getUser().getUserName()}"/></td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>
    <br><br>

    <div>
        <form action="addtip" method="post">
            <input type="hidden" name="businessId" value="${business.getBusinessId()}">
            <input type="hidden" name="userId" value="${user.getUserId()}">
            Text:
            <br>
            <textarea name="text" rows="4" cols="50"></textarea>
            <br><br>
            <input type="submit" value="Add Tip"/>
        </form>
    </div>

</div>
</body>
</html>
