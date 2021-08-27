<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="message_err" type="java.lang.String"--%>
<html>
    <head>
        <title>Adding News</title>
        <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
        <fmt:setLocale value="${sessionScope.local}"/>
        <fmt:setBundle basename="localization.local" var="loc"/>

        <fmt:message bundle="${loc}" key="local.news.add" var="add_news"/>
        <fmt:message bundle="${loc}" key="local.news.title" var="title_news"/>
        <fmt:message bundle="${loc}" key="local.news.content" var="content_news"/>
        <fmt:message bundle="${loc}" key="local.locbutton.back" var="back"/>
        <fmt:message bundle="${loc}" key="local.news.add.err" var="not_valid"/>
    </head>
    <body>
        <jsp:include page="header_for_form.jsp"/>
        <h1 style="margin: 0 17%">${add_news}</h1>
        <div style="margin-left: 28%">
            <p style="font-size: small; margin-left: 70px">
                <c:if test="${message_err != null}">
                    ${not_valid}
                </c:if>
            </p>
            <form action="/controller" method="POST">
                <p><b>${title_news}</b></p>
                <p>
                    <label>
                        <textarea name="title" rows="1" cols="90" required autofocus></textarea>
                    </label>
                </p>
                <p><b>${content_news}</b></p>
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
        <jsp:include page="footer.jsp"/>
    </body>
</html>
