<%--
  Created by IntelliJ IDEA.
  User: SuberSeb
  Date: 30.10.2019
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Пользователи</title>
</head>
<body>
<h2>Список пользователей</h2>
<p><a href='<c:url value="/addUser" />'>Добавить пользователя</a></p>
<table border="1" width="800px">
    <tr>
        <th>Имя</th>
        <th>Дата рождения</th>
        <th>Почта</th>
        <th>Город</th>
        <th>Группа</th>
        <th>Редактирование</th>
        <th>Удаление</th>
    </tr>
    <c:forEach var="user" items="${requestScope.users}">
        <tr align="center">
            <td>${user.name}</td>
            <td>${user.birthDate}</td>
            <td>${user.email}</td>
            <td>${user.city}</td>
            <td>${user.group}</td>
            <td><a href='<c:url value="/editUser?id=${user.id}" />'>Редактировать</a></td>
            <td>
                <form method="post" action='<c:url value="/deleteUser" />' style="display:inline;">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" value="Удалить">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
