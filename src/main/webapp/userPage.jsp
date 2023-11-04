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
    </div>
</div>
</body>
</html>
