<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="UPDATE_PERSONAL_PAGE" type="java.lang.String"--%>
<%--@elvariable id="PERSONAL_PAGE" type="java.lang.String"--%>
<%--@elvariable id="command" type="java.lang.String"--%>
<html>
    <head>
        <title>Personal Page</title>
        <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
        <fmt:setLocale value="${sessionScope.local}"/>
        <fmt:setBundle basename="localization.local" var="loc"/>

        <fmt:message bundle="${loc}" key="local.personal_page" var="personal_page"/>
        <fmt:message bundle="${loc}" key="local.firstname" var="firstname"/>
        <fmt:message bundle="${loc}" key="local.lastname" var="lastname"/>
        <fmt:message bundle="${loc}" key="local.email" var="email"/>
        <fmt:message bundle="${loc}" key="local.locbutton.back" var="back"/>
        <fmt:message bundle="${loc}" key="local.date.reg" var="date"/>
        <fmt:message bundle="${loc}" key="local.age" var="age"/>
        <fmt:message bundle="${loc}" key="local.personal_page.edit" var="edit"/>
    </head>
    <body>
        <jsp:include page="header_for_form.jsp"/>
        <h1 style="margin: 0 17%">${personal_page}</h1>
        <div style="float: left; margin: 30px 200px 0 17%;">
            <div>
                <p>${firstname}: <span>${sessionScope.user.firstName}</span></p>
                <p>${lastname}: <span>${sessionScope.user.lastName}</span></p>
                <p>${age}: <span>${sessionScope.user.age}</span></p>
                <p>${email}: <span>${sessionScope.user.email}</span></p>
                <br>
                <p>${date}: <span>${sessionScope.user.dateRegistered}</span></p>
            </div>
            <form action="/controller" method="GET">
                <input type="hidden" name="command" value="UPDATE_PERSONAL_PAGE">
                <input class="submit" type="submit" value="${edit}">
            </form>
            <p style="clear: both; margin-left: 240px">
                <button onclick="location.href='..'">${back}</button>
            </p>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
