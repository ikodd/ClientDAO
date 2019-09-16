<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: ik
  Date: 09.09.2019
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client database management</title>
</head>
<body>

<c:set var="name" value="${client.getName()}"/>
<c:set var="age" value="${client.getPhone()}"/>
<c:set var="message1" value=""/>

<c:if test="${command eq 'add' and success == true}">
    <c:set var="message1">Info been successfully added to the database</c:set>
</c:if>

<c:if test="${command eq 'delete'}">
    <c:if test="${success eq true}">
        <c:set var="message1">The deletion procedure has been completed successfully</c:set>
    </c:if>
    <c:if test="${success eq false}">
        <c:set var="message1">The deletion is not completed. Reason: an absence of such a client in the database</c:set>
    </c:if>
</c:if>
<p><font color="#cd5c5c"><c:if test="${name ne null}"><c:out value="Client ${name}: "/></c:if><c:out value="${message1}"/></font></p>

<h1>Client database management system</h1>
<c:if test="?{message1 ne ''}">
   <b><c:out value="?{message1}"/></b>
</c:if>
<table>
    <tr>
        <td colspan="3"><h2>Commands</h2></td>
    </tr>
    <tr><h3>
        <td><a href="/manage?command=get_all">Client list</a></td>
        <td><a href="/add.jsp">Add client</a></td>
        <td><a href="/manage?command=list_selection">Delete</a></td>
    </h3>
    </tr>
</table>
</body>
</html>
