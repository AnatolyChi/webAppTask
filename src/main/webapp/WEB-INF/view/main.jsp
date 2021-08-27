<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--@elvariable id="newsList" type="java.util.List"--%>
<%--@elvariable id="nOfPages" type="java.lang.String"--%>
<%--@elvariable id="currentPage" type="java.lang.String"--%>
<%--@elvariable id="recordsPerPage" type="java.lang.String"--%>
<html>
    <head>
        <title>Main Page</title>
        <link rel="stylesheet" href="../../resources/css/style.css">
        <fmt:setLocale value="${sessionScope.local}" />
        <fmt:setBundle basename="localization.local" var="loc" />

        <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
        <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
        <fmt:message bundle="${loc}" key="local.news.author" var="author"/>
        <fmt:message bundle="${loc}" key="local.news.page.current" var="current"/>
        <fmt:message bundle="${loc}" key="local.news.page.next" var="next"/>
        <fmt:message bundle="${loc}" key="local.news.page.previous" var="previous"/>
        <fmt:message bundle="${loc}" key="local.news.page.records" var="records"/>
        <fmt:message bundle="${loc}" key="local.news.update" var="update"/>
        <fmt:message bundle="${loc}" key="local.news.delete" var="delete"/>
        <fmt:message bundle="${loc}" key="local.news.search" var="search"/>
        <fmt:message bundle="${loc}" key="local.locbutton.submit" var="submit"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <c:if test="${newsList != null and newsList.size() > 0}">
                    <div style="margin-top: 50px">

                        <!-- Поиск по тегу -->
                        <div style="margin-left: 20px">
                            <form action="/controller" method="GET">
                                <p>
                                    <input type="search" name="tegNews" placeholder="${search}">
                                    <input type="hidden" name="command" value="MAIN_PAGE">
                                    <input type="submit" value="${submit}">
                                </p>
                            </form>
                        </div>

                        <c:forEach var="news" items="${newsList}">
                            <div class="newsMain">
                                <form action="/controller" method="GET">
                                    <div style="float: left">
                                        <p style="font-size: xx-small; text-align: left">${author}: ${news.author}</p>
                                        <p style="font-size: xx-small; text-align: left">${news.date}</p>
                                    </div>
                                    <c:if test="${sessionScope.user.role.equals('Admin')}">

                                        <!-- Возможно стоить удалять и обновлять по id -->
                                        <div style="float: right">
                                            <a href="controller?command=UPDATE_NEWS_PAGE&title=${news.title}&content=${news.content}"
                                               style="font-size: small">${update}</a><br>
                                            <a href="controller?command=DELETE_NEWS&title=${news.title}"
                                               style="font-size: small">${delete}</a>
                                        </div>
                                    </c:if>
                                    <input type="hidden" name="command" value="NEWS_PAGE">
                                    <input type="hidden" name="title" value="${news.title}">
                                    <input type="hidden" name="content" value="${news.content}">
                                    <input type="submit" class="input_news" value="${news.title}"
                                           style="margin-top: 40px; font-family: 'Comfortaa', serif; text-align: center">
                                </form>
                            </div>
                        </c:forEach>
                    </div>

                    <!-- Навигация по новостям -->
                    <nav>
                        <ul>
                            <c:if test="${currentPage != 1}">
                                <li><a href="controller?command=MAIN_PAGE&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">
                                        ${previous}
                                </a></li>
                            </c:if>

                            <c:forEach begin="1" end="${nOfPages}" var="i">
                                <c:choose>
                                    <c:when test="${currentPage eq i}">
                                        <li><a>${i}<span>(${current})</span></a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="controller?command=MAIN_PAGE&recordsPerPage=${recordsPerPage}&currentPage=${i}">
                                                ${i}
                                        </a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <c:if test="${currentPage lt nOfPages}">
                                <li><a href="controller?command=MAIN_PAGE&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">
                                        ${next}
                                </a></li>
                            </c:if>
                        </ul>
                    </nav>

                    <!-- Выборка, сколько новостей показывать на странице -->
                    <div style="text-align: center; margin-top: 50px">
                        <form action="controller?command=MAIN_PAGE" method="POST">
                            <label for="records">${records}:</label>
                            <select id="records" name="recordsPerPage">
                                <option value="4">4</option>
                                <option value="8">8</option>
                                <option value="12">12</option>
                            </select>
                            <input type="hidden" name="currentPage" value="1">
                            <input type="submit" value="${submit}">
                        </form>
                    </div>
                </c:if>
            </c:when>
            <c:when test="${sessionScope.user == null}">
                <c:if test="${newsList != null and newsList.size() > 0}">
                    <div style="margin-top: 50px; clear: both">
                        <c:forEach var="news" items="${newsList}">
                            <div class="newsMain">
                                <div style="text-align: left">
                                    <p style="font-size: xx-small">${author}: ${news.author}</p>
                                    <p style="font-size: xx-small">${news.date}</p>
                                </div>
                                <p style="text-align: center; margin-top: 0">${news.title}</p>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </c:when>
        </c:choose>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
