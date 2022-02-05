
<%--
  Created by IntelliJ IDEA.
  User: HomePC
  Date: 29.12.2021
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>TEST</title>
</head>
<body>
<p> Testing </p>
<br>
<h3>user ${user.name}  набрал  ${user.maxTotalPoints} очков</h3>

    <div>
        <p> question id: ${question.id}</p>
    </div>
    <br>
    <div>
        <h5 > ${question.question}</h5>
    </div>
    <br>
<form:form action="/launch/action" method="POST">

    <label>
        <input type="text" value="" name="answer" />
        <input type="hidden" value="${question.id}" name="idQ" />
        <input type="hidden" value="${name}" name="name" />
    </label>

    <div>
        <input type="submit" value="next question"/>
    </div>
</form:form>

<form action="/launch/result" >
    <button>Результаты</button>
</form>
<br>

<br>
<form action="/launch/final" >
    <button>Окончить тест </button>
</form>
</body>
</html>