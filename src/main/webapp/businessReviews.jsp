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

</div>
</body>
</html>
