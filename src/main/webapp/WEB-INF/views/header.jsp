<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Header</title>
        <fmt:setLocale value="${sessionScope.local}" />
        <fmt:setBundle basename="localization.local" var="loc" />

        <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
        <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
        <fmt:message bundle="${loc}" key="local.registration" var="Registration"/>
        <fmt:message bundle="${loc}" key="local.authorization" var="Authorization"/>
        <fmt:message bundle="${loc}" key="local.user.out" var="out_user"/>
        <fmt:message bundle="${loc}" key="local.news.add" var="add_news"/>
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
            <div style="clear: both">
                <!-- Работающая, но неправильная проверка, придумать как исправить... -->
                <c:if test="${requestScope.reg == null and requestScope.auth == null and requestScope.news == null}">
                    <c:choose>
                        <c:when test="${sessionScope.user == null}">
                            <form action="/controller" method="GET">
                                <input type="hidden" name="command" value="REGISTRATION">
                                <input class="reg" type="submit" value="${Registration}">
                            </form>
                            <form action="/controller" method="GET">
                                <input type="hidden" name="command" value="AUTHORIZATION">
                                <input class="auth" type="submit" value="${Authorization}">
                            </form>
                        </c:when>
                        <c:when test="${sessionScope.user != null}">
                            <p style="font-weight: bold; margin-left: 20px">
                                <c:out value="Welcome ${sessionScope.user.login}"/>
                            </p>
                            <form action="/controller" method="POST">
                                <input type="hidden" name="command" value="OUT_USER">
                                <input class="reg" type="submit" value="${out_user}">
                            </form>
                            <form action="/controller" method="POST">
                                <input type="hidden" name="command" value="NEWS">
                                <input class="auth" type="submit" value="${add_news}">
                            </form>
                        </c:when>
                    </c:choose>
                </c:if>
            </div>
        </header>
    </body>
</html>
