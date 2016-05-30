<%--
  Created by IntelliJ IDEA.
  User: oleh
  Date: 30.05.16
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>All contacts</title>
</head>
<body>

    <table width="600px">
        <td>
            <td><b>ID</b></td>
            <td><b>Name</b></td>
            <td><b>Number</b></td>
            <td><b>E-mail</b></td>
            <td><b>Action</b></td>
        </tr>


        <c:forEach var="contact" items="${contacts}">
            <tr>
                <td>${contact.id}</td>
                <td>${contact.name}</td>
                <td>${contact.number}</td>
                <td>${contact.email}</td>
                <td><a href="/edit?id=${contact.id}">Edit</a> | <a href="/delete?id=${contact.id}">Delete</a></td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="5">
                <a href="/add">Add contact</a>
            </td>

        </tr>
    </table>

</body>
</html>
