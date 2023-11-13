<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Update Business</title>
</head>
<body>
<div class="container theme-showcase" role="main">
    <div class="jumbotron">
        <h1>Update Business</h1>
    </div>
    <form action="updatebusiness" method="post">
        <input type="hidden" name="businessId" value="${business.getBusinessId()}">

        Business name:
        <input id="businessName" name="businessName" value="${business.getBusinessName()}">
        <br>
        <br>
        Address:
        <input id="address" name="address" value="${business.getAddress()}" size="30">
        <br>
        <br>
        MondayListedHours:
        <input id="mondayListedHours" name="mondayListedHours" value="${business.getMondayListedHours()}">
        <br>
        <br>
        TuesdayListedHours:
        <input id="tuesdayListedHours" name="tuesdayListedHours" value="${business.getTuesdayListedHours()}">
        <br>
        <br>
        WednesdayListedHours:
        <input id="wednesdayhours" name="wednesdayListedHours" value="${business.getWednesdayListedHours()}">
        <br>
        <br>
        ThursdayListedHours:
        <input id="thursdayListedHours" name="thursdayListedHours" value="${business.getThursdayListedHours()}">
        <br>
        <br>
        FridayListedHours:
        <input id="fridayListedHours" name="fridayListedHours" value="${business.getFridayListedHours()}">
        <br>
        <br>
        SaturdayListedHours:
        <input id="saturdayListedHour" name="saturdayListedHour" value="${business.getSaturdayListedHours()}">
        <br>
        <br>
        SundayListedHours:
        <input id="sundayListedHours" name="sundayListedHours" value="${business.getSundayListedHours()}">
        <br>
        <br>
        <input type="submit" value="update">

    </form>
    <br/><br/>
</div>
</body>
</html>
