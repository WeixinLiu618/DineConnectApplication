<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Update Business</title>
</head>
<body>
    <h1>Update Business</h1>
    <form action="userupdate" method="post">
        <p>
            <label for="businessname">Business name:</label>
            <input id="businessname" name="businessname" value="${fn:escapeXml(param.businessname)}">
        </p>
        <p>
            <label for="address">Address:</label>
            <input id="address" name="address" value="">
        </p>
        <p>
            <label for="mondayhours">MondayListedHours:</label>
            <input id="mondayhours" name="mondayhours" value="">
        </p>
        <p>
            <label for="tuesdayhours">TuesdayListedHours:</label>
            <input id="tuesdayhours" name="tuesdayhours" value="">
        </p>
        <p>
            <label for="wednesdayhours">WednesdayListedHours:</label>
            <input id="wednesdayhours" name="wednesdayhours" value="">
        </p>
        <p>
            <label for="thursdayhours">ThursdayListedHours:</label>
            <input id="thursdayhours" name="thursdayhours" value="">
        </p>
        <p>
            <label for="fridayhours">FridayListedHours:</label>
            <input id="fridayhours" name="fridayhours" value="">
        </p>
        <p>
            <label for="saturdayhours">SaturdayListedHours:</label>
            <input id="saturdayhours" name="saturdayhours" value="">
        </p>
        <p>
            <label for="sundayhours">SundayListedHours:</label>
            <input id="sundayhours" name="sundayhours" value="">
        </p>
        <p>
            <input type="submit">
        </p>
    </form>
    <br/><br/>
    <p>
</body>
</html>
