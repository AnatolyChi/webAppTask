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
        <fmt:message bundle="${loc}" key="local.personal_page" var="personal_page"/>
        <fmt:message bundle="${loc}" key="local.greeting" var="greeting"/>
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
            <div style="clear: both">
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
                    <c:when test="${sessionScope.user != null && sessionScope.user.role.equals('Admin')}">
                        <p style="font-weight: bold; margin-left: 20px">
                            <c:out value="${greeting} ${sessionScope.user.login}"/>
                        </p>
                        <form action="/controller" method="GET">
                            <input type="hidden" name="command" value="NEWS">
                            <input class="reg" type="submit" value="${add_news}">
                        </form>
                        <form action="/controller" method="GET">
                            <input type="hidden" name="command" value="PERSONAL_PAGE">
                            <input class="auth" type="submit" value="${personal_page}">
                        </form>
                        <form action="/controller" method="GET">
                            <input type="hidden" name="command" value="OUT_USER">
                            <input class="auth" type="submit" value="${out_user}">
                        </form>
                    </c:when>
                    <c:when test="${sessionScope.user != null && sessionScope.user.role.equals('User')}">
                        <p style="font-weight: bold; margin-left: 20px">
                            <c:out value="${greeting} ${sessionScope.user.login}"/>
                        </p>
                        <form action="/controller" method="GET">
                            <input type="hidden" name="command" value="PERSONAL_PAGE">
                            <input class="reg" type="submit" value="${personal_page}">
                        </form>
                        <form action="/controller" method="GET">
                            <input type="hidden" name="command" value="OUT_USER">
                            <input class="auth" type="submit" value="${out_user}">
                        </form>
                    </c:when>
                </c:choose>
            </div>
        </header>
    </body>
</html>
