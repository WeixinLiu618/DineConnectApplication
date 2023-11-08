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
            <tbody>
            <tr>
                <td><c:out value="${business.getBusinessName()}"/></td>
                <td><c:out value="${business.getAddress()}"/></td>
                <td><c:out value="${business.getCity().getPostalCode()}"/></td>

                <td><c:out value="${byAppointmentOnly}"/></td>
                <td><c:out value="${attire}"/></td>
                <td><c:out value="${alcohol}"/></td>
                <td><a href="updatebusiness?businessId=<c:out value="${business.getBusinessId()}"/>">update</a></td>
            </tr>
            </tbody>
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
                <th>No.</th>
                <th>Event</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Delete</th>
            </tr>
            </thead>
            <c:forEach items="${promotionList}" var="promotion" varStatus="status">
                <tbody>
                <tr>
                    <td>${status.count}</td>
                    <td><c:out value="${promotion.getEvent()}"/></td>
                    <fmt:parseDate value="${promotion.getStartTime()}" pattern="EEE MMM dd HH:mm:ss z yyyy" var="parsedStartTime"/>
                    <td><fmt:formatDate value="${parsedStartTime}" pattern="MMMM dd, yyyy"/></td>
                    <fmt:parseDate value="${promotion.getEndTime()}" pattern="EEE MMM dd HH:mm:ss z yyyy" var="parsedEndTime"/>
                    <td><fmt:formatDate value="${parsedEndTime}" pattern="MMMM dd, yyyy"/></td>
                    <td><a href="promotiondelete?promotionId=<c:out value="${promotion.getPromotionId()}"/>&businessId=${business.getBusinessId()}">delete</a></td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>
    <br><br>

    <div>
        <form action="addpromotion" method="post">
            <input type="hidden" name="businessId" value="${business.getBusinessId()}">
            Start Time: <input type="date" name="startTime"/>
            <br><br>
            End Time: <input type="date" name="endTime">
            <br><br>
            Event: <input type="text" name="event">
            <br><br>
            <input type="submit" value="Add Promotion"/>
        </form>
    </div>

</div>
</body>
</html>
