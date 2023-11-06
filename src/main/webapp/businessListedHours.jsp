<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Business Listed Hours</title>
</head>
<body>

<div class="container theme-showcase" role="main">
    <div class="jumbotron">
        <h1>Listed Hours of <c:out value="${business.getBusinessName()}"/></h1>
    </div>

    <div id="businessListedHours">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Monday</th>
                <th>Tuesday</th>
                <th>Wednesday</th>
                <th>Thursday</th>
                <th>Friday</th>
                <th>Saturday</th>
                <th>Sunday</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><c:out value="${business.getMondayListedHours()}"/></td>
                <td><c:out value="${business.getTuesdayListedHours()}"/></td>
                <td><c:out value="${business.getWednesdayListedHours()}"/></td>
                <td><c:out value="${business.getThursdayListedHours()}"/></td>
                <td><c:out value="${business.getFridayListedHours()}"/></td>
                <td><c:out value="${business.getSaturdayListedHours()}"/></td>
                <td><c:out value="${business.getSundayListedHours()}"/></td>
            </tr>
            </tbody>

        </table>
    </div>
</div>
</body>
</html>
