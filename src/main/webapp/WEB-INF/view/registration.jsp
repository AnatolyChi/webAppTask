<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="valid_message" type="java.lang.String"--%>
<%--@elvariable id="reg_message" type="java.lang.String"--%>
<%--@elvariable id="user_login" type="java.lang.String"--%>
<html>
    <head>
        <title>Registration</title>
        <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
        <style>
            input:invalid { border: 2px solid #75e7d2; }
        </style>
        <fmt:setLocale value="${sessionScope.local}"/>
        <fmt:setBundle basename="localization.local" var="loc"/>

        <fmt:message bundle="${loc}" key="local.registration" var="registration"/>
        <fmt:message bundle="${loc}" key="local.login" var="login"/>
        <fmt:message bundle="${loc}" key="local.password" var="password"/>
        <fmt:message bundle="${loc}" key="local.locbutton.back" var="back"/>
        <fmt:message bundle="${loc}" key="local.valid.registration" var="not_reg"/>
        <fmt:message bundle="${loc}" key="local.valid.check.registration" var="not_valid"/>
    </head>
    <body>
        <jsp:include page="header_single.jsp"/>
        <h1 style="margin: 0 17%">${registration}</h1>
        <div style="margin-left: 43%">
            <p>
                <c:if test="${reg_message != null}">
                    <c:out value="${not_reg}"/>
                </c:if>
                <c:if test="${valid_message != null}">
                    <c:out value="${not_valid}"/>
                </c:if>
            </p>
            <form action="/controller" method="POST">
                <label for="login">
                    ${login}:
                        <input id="login" class="login" type="text" name="login"
                               required minlength="3" autocomplete="on">
                </label>
                <br>
                <label for="password">
                    ${password}:
                        <input id="password" class="pass" type="password" name="password"
                               required minlength="5">
                </label>
                <br>
                <input type="hidden" name="command" value="REGISTRATION_NEW_USER">
                <input class="submit" style="margin-top: 15px; margin-left: 50px;" type="submit" value="Submit">
            </form>
            <p>
                <button onclick="location.href='..'">${back}</button>
            </p>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
