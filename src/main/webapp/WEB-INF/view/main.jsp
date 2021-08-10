<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--@elvariable id="newsList" type="java.util.List"--%>
<html>
    <head>
        <title>Main Page</title>
        <link rel="stylesheet" href="../../resources/css/style.css">
        <fmt:setLocale value="${sessionScope.local}" />
        <fmt:setBundle basename="localization.local" var="loc" />

        <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
        <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
        <fmt:message bundle="${loc}" key="local.news.author" var="author"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <c:if test="${newsList != null}">
                    <div style="margin-top: 50px">
                        <c:forEach var="news" items="${newsList}">
                            <div class="newsMain">
                                <p style="font-size: xx-small; text-align: left">${author}: ${news.author}</p>
                                <p style="font-size: xx-small; text-align: left">${news.date}</p>
                                    ${news.title}
                                <hr>
                                    ${news.content}
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </c:when>
            <c:when test="${sessionScope.user == null}">
                <c:if test="${newsList != null}">
                    <div style="margin-top: 50px">
                        <c:forEach var="news" items="${newsList}">
                            <div class="newsMain">
                                <p style="font-size: xx-small; text-align: left">${author}: ${news.author}</p>
                                <p style="font-size: xx-small; text-align: left">${news.date}</p>
                                    ${news.title}
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </c:when>
        </c:choose>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
