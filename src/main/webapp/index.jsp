<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <title>Redirect</title>
        <link rel="stylesheet" href="resources/css/style.css">
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Comfortaa:wght@500&display=swap');
            h1 {
                font-family: 'Comfortaa', serif;
            }
            body {
                background-image: url('resources/img/background-image.jpg');
                background-size: cover;
            }
        </style>
    </head>
    <body>
    <div>
        <%
            // перенаправляем на главную страницу с новостями
            response.sendRedirect("/controller?command=MAIN_PAGE");
        %>
    </div>
    </body>
</html>