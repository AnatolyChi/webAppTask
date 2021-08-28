<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="content" type="java.lang.String"--%>
<%--@elvariable id="title" type="java.lang.String"--%>
<html>
    <head>
        <title>News</title>
        <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
        <fmt:setLocale value="${sessionScope.local}"/>
        <fmt:setBundle basename="localization.local" var="loc"/>

        <fmt:message bundle="${loc}" key="local.locbutton.back" var="back"/>
        <fmt:message bundle="${loc}" key="local.news.add.favourite" var="add"/>
        <fmt:message bundle="${loc}" key="local.news.delete.favourite" var="delete"/>
    </head>
    <body>
        <jsp:include page="header_for_form.jsp"/>

        <!-- Здесь добавить форму на добавление новости в избранные -->
        <div style="margin-left: 100px; margin-right: 100px; margin-top: 50px">
            <c:if test="${err_add != null}">

            </c:if>
            <form action="/controller" method="POST">
                <input type="hidden" name="command" value="ADD_TO_FAVOURITE">
                <input type="hidden" name="user_login" value="${sessionScope.user.login}">
                <input type="hidden" name="title" value="${title}">
                <input type="submit" value="${add}">
            </form>
            <c:if test="${err_delete != null}">

            </c:if>
            <form action="/controller" method="POST">
                <input type="hidden" name="command" value="DELETE_FROM_FAVOURITE">
                <input type="hidden" name="user_login" value="${sessionScope.user.login}">
                <input type="hidden" name="title" value="${title}">
                <input type="submit" value="${delete}">
            </form>
            <p style="text-align: center">${title}</p>
            <hr>
            <p style="text-align: center">${content}</p>
        </div>
        <p style="margin-top: 50px; text-align: center">
            <button onclick="location.href='..'">${back}</button>
        </p>

        <!-- Здесь добавить отображение списка избранных новостей -->

        <jsp:include page="footer.jsp"/>
    </body>
</html>
