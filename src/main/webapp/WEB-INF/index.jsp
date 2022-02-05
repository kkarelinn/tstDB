<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>APP test</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<h2>App</h2>
<form:form action="/launch" method="get">
    <% Object s = request.getAttribute("catch"); %>
    <label>
        <input type="text" name="name" placeholder="Введите ваше имя"/>
        <c:if test="<%=(s!=null)%>">
            <p style="color: red"> поле не должно быть пустым </p>
        </c:if>

    </label>


    <div>
        <input type="submit" value="Ok"/>
    </div>

</form:form>
<br>
</body>
</html>