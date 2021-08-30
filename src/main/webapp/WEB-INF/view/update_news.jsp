<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="news_id" type="java.lang.Integer"--%>
<%--@elvariable id="content" type="java.lang.String"--%>
<%--@elvariable id="title" type="java.lang.String"--%>
<html>
    <head>
        <title>Update News</title>
        <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
        <fmt:setLocale value="${sessionScope.local}"/>
        <fmt:setBundle basename="localization.local" var="loc"/>

        <fmt:message bundle="${loc}" key="local.news.add" var="add_news"/>
        <fmt:message bundle="${loc}" key="local.news.title" var="title_news"/>
        <fmt:message bundle="${loc}" key="local.news.content" var="content_news"/>
        <fmt:message bundle="${loc}" key="local.locbutton.back" var="back"/>
        <fmt:message bundle="${loc}" key="local.locbutton.submit" var="submit"/>
    </head>
    <body>
        <jsp:include page="header_for_form.jsp"/>
        <h1 style="margin: 0 17%">${add_news}</h1>
        <div style="margin-left: 28%">
            <form action="/controller" method="POST">
                <p><b>${title_news}</b></p>
                <p>
                    <label>
                        <textarea name="title" rows="1" cols="90" required autofocus>${title}</textarea>
                    </label>
                </p>
                <p><b>${content_news}</b></p>
                <p>
                    <label>
                        <textarea name="content" rows="30" cols="90" required>${content}</textarea>
                    </label>
                </p>
                <input type="hidden" name="command" value="UPDATE_NEWS">
                <input type="hidden" name="news_id" value="${news_id}">
                <input class="submit" type="submit" value="${submit}">
            </form>
            <p>
                <button onclick="location.href='..'">${back}</button>
            </p>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
