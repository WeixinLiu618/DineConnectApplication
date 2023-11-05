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
            <%--TODO: forEach of reviews showing for user--%>
        </table>
    </div>
</div>

</body>
</html>
