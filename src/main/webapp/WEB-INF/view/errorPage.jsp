<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<html>
    <head>
        <title>Title</title>
        <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
        <fmt:setLocale value="${sessionScope.local}"/>
        <fmt:setBundle basename="localization.local" var="loc"/>

        <fmt:message bundle="${loc}" key="local.locbutton.back" var="back"/>
        <fmt:message bundle="${loc}" key="local.error.exception" var="error"/>
    </head>
    <body>
        <jsp:include page="headerForForm.jsp"/>
        <div>
            <h1 style="text-align: center">
                ${error}
            </h1>
            <p style="text-align: center">
                <button onclick="location.href='..'">${back}</button>
            </p>
        </div>
    </body>
</html>
