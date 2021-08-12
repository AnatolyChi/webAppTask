<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>News</title>
        <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
        <fmt:setLocale value="${sessionScope.local}"/>
        <fmt:setBundle basename="localization.local" var="loc"/>

        <fmt:message bundle="${loc}" key="local.news.add" var="add_news"/>
        <fmt:message bundle="${loc}" key="local.news.title" var="title"/>
        <fmt:message bundle="${loc}" key="local.news.info" var="info"/>
        <fmt:message bundle="${loc}" key="local.locbutton.back" var="back"/>
    </head>
    <body>
        <jsp:include page="headerForForm.jsp"/>
        <h1 style="margin: 0 17%">${add_news}</h1>
        <div style="margin-left: 28%">
            <form action="/controller" method="POST">
                <p><b>${title}</b></p>
                <p>
                    <label>
                        <textarea name="title" rows="1" cols="90" required autofocus></textarea>
                    </label>
                </p>
                <p><b>${info}</b></p>
                <p>
                    <label>
                        <textarea name="content" rows="30" cols="90" required></textarea>
                    </label>
                </p>
                <input type="hidden" name="author" value="${sessionScope.user.login}">
                <input type="hidden" name="command" value="ADD_NEWS">
                <input class="submit" type="submit" value="Submit">
            </form>
            <p>
                <button onclick="location.href='..'">${back}</button>
            </p>
        </div>
    </body>
</html>
