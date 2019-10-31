<%--
  Created by IntelliJ IDEA.
  User: SuberSeb
  Date: 30.10.2019
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Группы</title>
</head>
<body>
<h2>Список групп</h2>
<p><a href='<c:url value="/addGroup" />'>Добавить группу</a></p>
<table border="1" width="600px">
    <tr>
        <th>Название</th>
        <th>Описание</th>
        <th>Редактирование</th>
        <th>Удаление</th>
    </tr>
    <c:forEach var="group" items="${requestScope.groups}">
        <tr align="center">
            <td>${group.name}</td>
            <td>${group.description}</td>
            <td><a href='<c:url value="/editGroup?id=${group.id}" />'>Редактировать</a></td>
            <td>
                <form method="post" action='<c:url value="/deleteGroup" />' style="display:inline;">
                    <input type="hidden" name="id" value="${group.id}">
                    <input type="submit" value="Удалить">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
