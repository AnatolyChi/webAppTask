<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <fmt:setLocale value="${sessionScope.local}"/>
        <fmt:setBundle basename="localization.local" var="loc"/>

        <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button"/>
        <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button"/>
        <fmt:message bundle="${loc}" key="local.locbutton.submit" var="submit"/>
    </head>
    <body>
        <header>
            <div style="float: left; width: 91%">
                <img style="margin-top: 20px; margin-left: 20px" width="350" src="../../resources/img/News.svg.png" alt="news">
            </div>
            <div style="float: left; margin-top: 20px">
                <form action="/controller" method="POST">
                    <label>
                        <select name="local">
                            <option value="ru">${ru_button}</option>
                            <option value="en">${en_button}</option>
                        </select>
                    </label><br>
                    <input type="hidden" name="command" value="CHANGE_LOCALE">
                    <input type="submit" value="${submit}">
                </form>
            </div>
        </header>
    </body>
</html>
