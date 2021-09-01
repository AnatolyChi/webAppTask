<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="err_delete" type="java.lang.String"--%>
<%--@elvariable id="suc_delete" type="java.lang.String"--%>
<%--@elvariable id="news_id" type="java.lang.Integer"--%>
<%--@elvariable id="suc_add" type="java.lang.String"--%>
<%--@elvariable id="content" type="java.lang.String"--%>
<%--@elvariable id="err_add" type="java.lang.String"--%>
<%--@elvariable id="title" type="java.lang.String"--%>
<html>
    <head>
        <title>News</title>
        <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
        <fmt:setLocale value="${sessionScope.local}"/>
        <fmt:setBundle basename="localization.local" var="loc"/>

        <fmt:message bundle="${loc}" key="local.locbutton.back" var="back"/>
        <fmt:message bundle="${loc}" key="local.news.favourite.add" var="add"/>
        <fmt:message bundle="${loc}" key="local.news.favourite.delete" var="delete"/>
        <fmt:message bundle="${loc}" key="local.news.favourite.add.suc" var="add_suc_loc"/>
        <fmt:message bundle="${loc}" key="local.news.favourite.add.err" var="add_err_loc"/>
        <fmt:message bundle="${loc}" key="local.news.favourite.delete.suc" var="delete_suc_loc"/>
        <fmt:message bundle="${loc}" key="local.news.favourite.delete.err" var="delete_err_loc"/>
    </head>
    <body>
        <jsp:include page="header_single.jsp"/>
        <div style="margin-left: 100px; margin-right: 100px; margin-top: 50px">
            <form action="/controller" method="POST">
                <input type="hidden" name="command" value="ADD_TO_FAVOURITE">
                <input type="hidden" name="user_id" value="${sessionScope.user.id}">
                <input type="hidden" name="news_id" value="${news_id}">
                <input type="hidden" name="title" value="${title}">
                <input type="hidden" name="content" value="${content}">
                <input type="submit" value="${add}">
                <p class="small_font">
                    <c:if test="${suc_add != null}">${add_suc_loc}</c:if>
                </p>
                <p class="small_font">
                    <c:if test="${err_add != null}">${add_err_loc}</c:if>
                </p>
            </form>

            <form action="controller?command=DELETE_FROM_FAVOURITE&title=${title}&content=${content}" method="POST">
                <input type="hidden" name="command" value="DELETE_FROM_FAVOURITE">
                <input type="hidden" name="user_id" value="${sessionScope.user.id}">
                <input type="hidden" name="news_id" value="${news_id}">
                <input type="hidden" name="title" value="${title}">
                <input type="hidden" name="content" value="${content}">
                <input type="submit" value="${delete}">
                <p class="small_font">
                    <c:if test="${suc_delete != null}">${delete_suc_loc}</c:if>
                </p>
                <p class="small_font">
                    <c:if test="${err_delete != null}">${delete_err_loc}</c:if>
                </p>
            </form>
            <p style="text-align: center">${title}</p>
            <hr>
            <p style="text-align: center">${content}</p>
        </div>
        <p style="margin-top: 50px; text-align: center">
            <button onclick="location.href='..'">${back}</button>
        </p>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
