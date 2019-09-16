<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.company.clientdb.client.*" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ik
  Date: 09.09.2019
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client list</title>
</head>
<body>
<%List<Client> clients = (List<Client>)request.getAttribute("clients");%>
<%if(clients == null )response.sendRedirect("/index.jsp");%>
<%Boolean isDelete = (Boolean) request.getAttribute("isDelete");%>
<%isDelete = (isDelete != null) ? isDelete : false;%>
<%String command;%>
<%if((command = request.getParameter("command")) != null){
    switch (command){
        case "delete":
            isDelete = true;
            break;
        default:
            break;
    }
}%>
<c:if test="${isDelete ne true}">
    <c:set var="disabled">disabled</c:set>
</c:if>
<c:if test="${isDelete eq true}">
    <c:set var="title">: deletion command</c:set>
    <c:set var="instruction">To delete client info, check box at the right column "Selected" and hit "Submit" button below the table</c:set>
</c:if>
<h1>Client list</h1>
<table>
    <form action="/manage" method="post">
    <tr><td>Client ID</td><td>Full name</td><td>Phone number</td><td>Selected</td></tr>
    <c:if test="${clients ne null}">
    <c:forEach var="client" items="${clients}" varStatus="counter">
        <tr>
            <td><c:out value="${client.id}"/></td>
            <td><c:out value="${client.name}"/> </td>
            <td><c:out value="${client.phone}"/> </td>
            <td><input type="radio" name="command" value=<c:out value="delete${client.id}"/> ${disabled}>
                    </td>
        </tr>
    </c:forEach>
    </c:if>
</table><br>
<input type="submit" ${disabled}>
</form>
<a href="index.jsp">Back to main page</a>

</body>
</html>
