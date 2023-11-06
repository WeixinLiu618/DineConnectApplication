<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Business Reviews</title>
</head>
<body>

<div class="container theme-showcase" role="main">
    <div class="text-right">
        <p>Your Username: <c:out value="${user.getUserName()}"/></p>
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
        </table>
    </div>
    <br><br>

    <div>
        <form action="add review" method="post">
            Comment:
            <br>
            <textarea name="comment" rows="4" cols="50"></textarea>
            <br><br>
            Comment Stars: <input type="text" name="commentStars" size="10">
            <br><br>
            <input type="submit" value="Add Review"/>
        </form>
    </div>

</div>
</body>
</html>
