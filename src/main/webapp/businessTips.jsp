<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Business Tips</title>
</head>
<body>

<div class="container theme-showcase" role="main">
    <div class="text-right">
        <p>Your Username: <c:out value="${user.getUserName()}"/></p>
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
                    <fmt:parseDate value="${tip.getCreatedTime()}" pattern="EEE MMM dd HH:mm:ss z yyyy" var="parsedCreatedTime"/>
                    <td><fmt:formatDate value="${parsedCreatedTime}" pattern="MMMM dd, yyyy HH:mm:ss z"/></td>
                    <td><c:out value="${tip.getUser().getUserName()}"/></td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>
    <br><br>

    <div>
        <form action="add tip" method="post">
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
