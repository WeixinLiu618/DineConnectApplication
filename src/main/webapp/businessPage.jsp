<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Business Page</title>
</head>
<body>

<div class="container theme-showcase" role="main">
    <div class="jumbotron">
        <h1>Welcome, <c:out value="${business.getBusinessName()}"/></h1>
    </div>

    <div>
        <h3>Business Information</h3>
    </div>

    <div id="businessInfo">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Business Name</th>
                <th>Address</th>
                <th>Postal Code</th>
                <th>ByAppointmentOnly</th>
                <th>Attire</th>
                <th>Alcohol</th>
                <th>UpdateBusiness</th>
            </tr>
            </thead>
        </table>
    </div>
    <br><br>

    <div>
        <h3>Promotions List</h3>
    </div>

    <div id="promotionsList">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Event</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Delete</th>
            </tr>
            </thead>
        </table>
    </div>
    <br><br>

    <div>
        <form action="add promotion" method="post">
            Start Time: <input type="text" name="startTime"/>
            <br><br>
            End Time: <input type="text" name="endTime">
            <br><br>
            Event: <input type="text" name="event">
            <br><br>
            <input type="submit" value="Add Promotion"/>
        </form>
    </div>

</div>
</body>
</html>
