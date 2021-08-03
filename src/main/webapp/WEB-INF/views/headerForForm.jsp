<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <fmt:setLocale value="${sessionScope.local}"/>
        <fmt:setBundle basename="localization.local" var="loc"/>

        <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button"/>
        <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button"/>
    </head>
    <body>
        <header>
            <div style="float: left; width: 95%">
                <img style="margin-top: 20px; margin-left: 20px" width="350" src="../../resources/img/News.svg.png" alt="news">
            </div>
            <div style="float: left; margin-top: 20px">
                <form action="/controller" method="POST">
                    <input type="hidden" name="local" value="ru">
                    <input type="hidden" name="command" value="CHANGE_LOCALE">
                    <input type="submit" value="${ru_button}" >
                </form>
                <form action="/controller" method="POST">
                    <input type="hidden" name="local" value="en">
                    <input type="hidden" name="command" value="CHANGE_LOCALE">
                    <input type="submit" value="${en_button}">
                </form>
            </div>
        </header>
    </body>
</html>
