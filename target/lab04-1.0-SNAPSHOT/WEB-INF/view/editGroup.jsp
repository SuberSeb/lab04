<%--
  Created by IntelliJ IDEA.
  User: SuberSeb
  Date: 30.10.2019
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование группы</title>
</head>
<body>
    <h3>Редактировать группу</h3>
    <form method="post">
        <input type="hidden" value="${requestScope.group.id}" name="id" />
        <table>
            <tr>
                <td><label>Имя:</label></td>
                <td><input name="name"/></td>
            </tr>
            <tr>
                <td><label>Описание:</label></td>
                <td><input name="description"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Отправить" style="width: 100%;"/></td>
                <td><input type="reset" value="Очистить" style="width: 100%;"/></td>
            </tr>
        </table>
    </form>
</body>
</html>
