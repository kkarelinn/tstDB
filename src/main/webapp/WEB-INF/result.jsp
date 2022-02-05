<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>results</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>

<h3>current user is ${user.name} have a ${user.maxTotalPoints} points</h3>

<h4>All Users</h4>
<table border="2" cellpadding="2" width="60%">
    <tr>
        <th>name</th>
        <th>total_points</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.name}</td>
            <td>${user.maxTotalPoints}</td>
        </tr>
    </c:forEach>
</table>

<br>
<form action="/launch/final">
    <button>Start again</button>
</form>
</body>
</html>