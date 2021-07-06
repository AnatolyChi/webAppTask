<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%
    List<String> userList = (List<String>) request.getAttribute("userList");
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
                    if (userList != null) {
                        for (String users : userList) {
                            out.println("<li>" + users + "</li>");
                        }
                        out.println("Total users: " + userList.size());
                    } else {
                        out.print("Users not found!");
                    }
                %>
            </ol>
            <p>
                <button onclick="location.href='..'">Back to main</button>
            </p>
        </div>
    </body>
</html>
