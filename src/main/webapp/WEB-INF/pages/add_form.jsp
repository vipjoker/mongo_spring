<%--
  Created by IntelliJ IDEA.
  User: oleh
  Date: 30.05.16
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add contact</title>
</head>
<body>
    <form:form method="POST" action="/add" modelAttribute="contact">
        <form:hidden path="id"></form:hidden>
        <table>
            <tr>
                <td>Name:</td>
                <td><form:input path="name"/> </td>
            </tr>
            <tr>
                <td>Number:</td>
                <td><form:input path="number"/> </td>
            </tr>
            <tr>

                <td colspan="2">
                    <input type="submit"/>
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
