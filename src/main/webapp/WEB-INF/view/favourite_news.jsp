<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="newsList" type="java.util.List"--%>
<html>
    <head>
        <title>Favourite news</title>
        <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
        <fmt:setLocale value="${sessionScope.local}"/>
        <fmt:setBundle basename="localization.local" var="loc"/>

        <fmt:message bundle="${loc}" key="local.news.favourite.empty.list" var="empty_list"/>
        <fmt:message bundle="${loc}" key="local.locbutton.back" var="back"/>
        <fmt:message bundle="${loc}" key="local.news.author" var="author"/>
        <fmt:message bundle="${loc}" key="local.news.update" var="update"/>
        <fmt:message bundle="${loc}" key="local.news.delete" var="delete"/>
    </head>
    <body>
        <jsp:include page="header_single.jsp"/>

        <div style="margin-top: 50px">
            <c:if test="${newsList == null or newsList.size() == 0}">
                <p style="text-align: center">${empty_list}</p>
            </c:if>

            <c:if test="${newsList != null and newsList.size() > 0}">
                <c:forEach var="news" items="${newsList}">
                    <div class="newsMain">
                        <form action="/controller" method="GET">
                            <div style="float: left">
                                <p style="font-size: xx-small; text-align: left">${author}: ${news.author}</p>
                                <p style="font-size: xx-small; text-align: left">${news.date}</p>
                            </div>
                            <c:if test="${sessionScope.user.role.equals('Admin')}">

                                <div style="float: right">
                                    <a href="controller?command=GO_TO_UPDATE_NEWS_PAGE&news_id=${news.newsId}&title=${news.title}&content=${news.content}"
                                       style="font-size: small">${update}</a><br>
                                    <a href="controller?command=DELETE_NEWS&news_id=${news.newsId}"
                                       style="font-size: small">${delete}</a>
                                </div>
                            </c:if>
                            <input type="hidden" name="command" value="GO_TO_READ_NEWS_PAGE">
                            <input type="hidden" name="news_id" value="${news.newsId}">
                            <input type="hidden" name="title" value="${news.title}">
                            <input type="hidden" name="content" value="${news.content}">
                            <input type="submit" class="input_news" value="${news.title}"
                                   style="margin-top: 40px; font-family: 'Comfortaa', serif; text-align: center">
                        </form>
                    </div>
                </c:forEach>
            </c:if>
        </div>

        <p style="text-align: center">
            <button onclick="location.href='..'">${back}</button>
        </p>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
