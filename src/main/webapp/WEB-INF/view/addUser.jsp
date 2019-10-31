<%--
  Created by IntelliJ IDEA.
  User: SuberSeb
  Date: 30.10.2019
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добавить пользователя</title>
</head>
<body>
    <h3>Добавить пользователя</h3>
    <form method="post">
        <table>
            <tr>
                <td><label>Имя:</label></td>
                <td><input name="name"/></td>
            </tr>
            <tr>
                <td><label>Дата рождения:</label></td>
                <td><input name="birthDate"/></td>
            </tr>
            <tr>
                <td><label>Электронная почта:</label></td>
                <td><input name="email"/></td>
            </tr>
            <tr>
                <td><label>Город:</label></td>
                <td><input name="city"/></td>
            </tr>
            <tr>
                <td><label>Группа:</label></td>
                <td><input name="group" type="number" min="1"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Добавить" style="width: 100%;"/></td>
                <td><input type="reset" value="Очистить" style="width: 100%;"/></td>
            </tr>
        </table>
    </form>
</body>
</html>
