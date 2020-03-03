<%@ page import="models.view.HomeViewModel" %>
<%@ page import="models.CourseServiceModel" %><%--
  Created by IntelliJ IDEA.
  User: Vasil
  Date: 18/10/2019
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HomePage</title>
</head>
<body>
    It works! JSP IS ALIVE
    <br/>
    <%= request.getAttribute("name")%>
    <br/>
    <% for(CourseServiceModel course : ((HomeViewModel)request.getAttribute("viewModel")).getCourses()) {%>
        <li>
            <%= course.getName() %>
        </li>
    <% } %>
</body>
</html>
