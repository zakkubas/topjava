<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<table border="1">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>

<c:forEach var="mealsTo" items="${mealsTo}">
    <tr style="color:${mealsTo.excess ? 'red' : 'green'}">
        <td>
            <fmt:parseDate value="${ mealsTo.dateTime }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
            <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/>
        </td>
            <td>${mealsTo.description}</td>
            <td>${mealsTo.calories}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>