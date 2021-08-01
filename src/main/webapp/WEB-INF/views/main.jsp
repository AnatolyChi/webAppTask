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
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div>
            <c:if test="${newsList != null}">
                <c:forEach var="news" items="${newsList}">
                    <div class="divNews">
                        ${news.title} <hr> ${news.content}
                    </div>
                </c:forEach>
            </c:if>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
