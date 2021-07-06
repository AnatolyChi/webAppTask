<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>News</title>
        <link rel="stylesheet" href="../../resources/css/style.css">
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Comfortaa:wght@500&display=swap');
            h1, p {
                font-family: 'Comfortaa', serif;
            }
            body {
                background-image: url("../../resources/img/background-image.jpg");
                background-size: cover;
            }
        </style>
    </head>
    <body>
        <header>
            <h1 style="text-align: center">Add News</h1>
        </header>
        <div style="margin-left: 28%">
            <form action="/controller" method="POST">
                <p><b>Title</b></p>
                <p><label>
                    <textarea name="title" rows="1" cols="90" required></textarea>
                </label></p>
                <p><b>Info</b></p>
                <p><label>
                    <textarea name="info" rows="30" cols="90" required></textarea>
                </label></p>
                <input type="hidden" name="command" value="ADD_NEWS">
                <input class="submit" type="submit" value="Submit">
            </form>
            <p>
                <button onclick="location.href='..'">Back to main</button>
            </p>
        </div>
    </body>
</html>
