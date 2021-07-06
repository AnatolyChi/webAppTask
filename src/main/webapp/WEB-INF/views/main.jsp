<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.webAppTask.bean.News" %>
<%@ page import="com.example.webAppTask.bean.User" %>
<%@ page import="java.util.List" %>
<%--<jsp:useBean id="user" scope="session" class="com.example.webAppTask.bean.User"/>--%>
<%
    List<News> newsList = (List<News>) request.getAttribute("newsList");
    String greeting = (String) request.getAttribute("greeting");
    User user = (User) request.getSession().getAttribute("user");
%>
<html>
<head>
    <title>Main Page</title>
    <link rel="stylesheet" href="../../resources/css/style.css">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Comfortaa:wght@500&display=swap');
        h1 {
            font-family: 'Comfortaa', serif;
        }
        body {
            background-image: url('../../resources/img/background-image.jpg');
            background-size: cover;
        }
    </style>
</head>
<body>
<header>
    <div>
        <img style="margin-top: 20px; margin-left: 20px" width="350" src="../../resources/img/News.svg.png" alt="news">
    </div>
    <div style="margin-top: 7px">

        <% if (user == null) { %>
        <form action="/controller" method="GET">
            <input type="hidden" name="command" value="REGISTRATION">
            <input class="reg" type="submit" value="Registration">
        </form>
        <form action="/controller" method="GET">
            <input type="hidden" name="command" value="AUTHORIZATION">
            <input class="auth" type="submit" value="Authorization">
        </form>
        <form action="/controller" method="GET">
            <input type="hidden" name="command" value="REGISTERED_USERS">
            <input class="auth" type="submit" value="Registered users">
        </form>
        <% } else { %>
        <form action="/controller" method="POST">
            <input type="hidden" name="command" value="OUT_USER">
            <input class="reg" type="submit" value="Out user">
        </form>
        <form action="/controller" method="POST">
            <input type="hidden" name="command" value="NEWS">
            <input class="auth" type="submit" value="News">
        </form>
        <% } %>
    </div>
    </header>
    <div style="text-align: center">
        <h1>
            <%
                if (greeting != null)
                    out.print(greeting);
            %>
        </h1>
    </div>
    <div>
        <%
            if (newsList != null)
                for (News news : newsList)
                    out.println("<div>" + news.getTitle() + " " + news.getBrief() + "</div>");
        %>
    </div>
</body>
</html>
