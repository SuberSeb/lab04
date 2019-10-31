<%--
  Created by IntelliJ IDEA.
  User: SuberSeb
  Date: 30.10.2019
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Главная</title>
</head>
<body>
<h2>Операции с БД на сервлетах</h2>
<p><a href='<c:url value="/showUsers" />'>Показать пользователей</a></p>
<p><a href='<c:url value="/showGroups" />'>Показать группы</a></p>
</body>
</html>
