<%@ page import="com.example.webAppTask.model.Model" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<String> userList = Model.getInstance().sourceUserList();
%>
<html>
    <head>
        <title>First JSP</title>
        <link rel="stylesheet" href="../../resources/css/style.css">
        <style>
            body {
                background-image: url("../../resources/img/background-image.jpg");
                background-size: cover;
            }
        </style>
    </head>
    <body>
        <div>
            <ol>
                <%
                    for (String users : userList) {
                        out.println("<li>" + users + "</li>");
                    }
                    out.println("Total users: " + userList.size());
                %>
            </ol>
        </div>
    </body>
</html>
