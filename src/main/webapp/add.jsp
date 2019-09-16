<%--
  Created by IntelliJ IDEA.
  User: ik
  Date: 12.09.2019
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add client</title>
</head>
    <body>
    <h1>Add client to the database</h1>
    <form method ="POST" action="/manage">
        <input type="hidden" name="command" value="add">
        <table>
            <tr><input type="text" name="fName" required>* Full name<br></tr>
            <tr> <input type="tel" name="phone" pattern="[0-9]{2}[0-9]{3}[0-9]{7}" required>*Phone number. Format: 380000000000<br></tr>
            <tr><input type="submit"></tr>
        </table><br>
    </form>

<a href="/index.jsp">Back to main page</a>
</body>
</html>
