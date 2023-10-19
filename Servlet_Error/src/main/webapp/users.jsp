<%--
  Created by IntelliJ IDEA.
  User: Ruslan
  Date: 13.10.2023
  Time: 9:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h1>From JSP Users</h1>
<div>
    <table>
        <tr>
            <th>NAME</th>
            <th>EMAIL</th>
            <th>PASSWORD</th>
        </tr>
            <%
                List<User> users = (List<User>) request.getAttribute("usersForJsp");
                for ( int i = 0; i < users.size(); i++) {
            %>
        <tr>
            <td> <%=users.get(i).getName()%></td>
            <td> <%=users.get(i).getEmail()%></td>
            <td> <%=users.get(i).getPassword()%></td>
        </tr>
        <%}%>
    </table>
</div>
</body>
</html>
