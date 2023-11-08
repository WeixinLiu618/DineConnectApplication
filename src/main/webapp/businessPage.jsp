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

    <div style="display: flex; justify-content: space-between; align-items: baseline; width: 100%;">
        <h3>Business Information</h3>
        <a style="font-size: x-large;margin-right: 40px;"
           href="updatebusiness?businessId=<c:out value="${business.getBusinessId()}"/>">update</a>
    </div>

    <div id="businessInfo">
        <table class="table table-striped">
            <tr>
                <th>Business Name</th>
                <td><c:out value="${business.getBusinessName()}"/></td>
            </tr>
            <tr>
                <th>Address</th>
                <td><c:out value="${business.getAddress()}"/></td>
            </tr>
            <tr>
                <th>Postal Code</th>
                <td><c:out value="${business.getCity().getPostalCode()}"/></td>
            </tr>

            <tr>
                <th>Monday</th>
                <td><c:out value="${business.getMondayListedHours()}"/></td>
            </tr>
            <tr>
                <th>Tuesday</th>
                <td><c:out value="${business.getTuesdayListedHours()}"/></td>
            </tr>
            <tr>
                <th>Wednesday</th>
                <td><c:out value="${business.getWednesdayListedHours()}"/></td>

            </tr>
            <tr>
                <th>Thursday</th>
                <td><c:out value="${business.getThursdayListedHours()}"/></td>
            </tr>
            <tr>
                <th>Friday</th>
                <td><c:out value="${business.getFridayListedHours()}"/></td>
            </tr>
            <tr>
                <th>Saturday</th>
                <td><c:out value="${business.getSaturdayListedHours()}"/></td>
            </tr>
            <tr>
                <th>Sunday</th>
                <td><c:out value="${business.getSundayListedHours()}"/></td>
            </tr>
            <tr>
                <th>By Appointment Only</th>
                <td><c:out value="${byAppointmentOnly}"/></td>
            </tr>
            <tr>
                <th>Attire</th>
                <td><c:out value="${attire}"/></td>
            </tr>
            <tr>
                <th>Alcohol</th>
                <td><c:out value="${alcohol}"/></td>
            </tr>
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
                    <fmt:parseDate value="${promotion.getStartTime()}" pattern="EEE MMM dd HH:mm:ss z yyyy"
                                   var="parsedStartTime"/>
                    <td><fmt:formatDate value="${parsedStartTime}" pattern="MMMM dd, yyyy"/></td>
                    <fmt:parseDate value="${promotion.getEndTime()}" pattern="EEE MMM dd HH:mm:ss z yyyy"
                                   var="parsedEndTime"/>
                    <td><fmt:formatDate value="${parsedEndTime}" pattern="MMMM dd, yyyy"/></td>
                    <td>
                        <a href="promotiondelete?promotionId=<c:out value="${promotion.getPromotionId()}"/>&businessId=${business.getBusinessId()}">delete</a>
                    </td>
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
