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
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <c:if test="${newsList != null}">
                    <div style="margin-top: 50px">
                        <c:forEach var="news" items="${newsList}">
                            <div class="newsMain">
                                <form action="/controller" method="POST">
                                    <p style="font-size: xx-small; text-align: left">${author}: ${news.author}</p>
                                    <p style="font-size: xx-small; text-align: left">${news.date}</p>
                                    <input type="hidden" name="title" value="${news.title}">
                                    <input type="hidden" name="content" value="${news.content}">
                                    <input type="hidden" name="command" value="NEWS_PAGE">
                                    <input type="submit" class="input_news" value="${news.title}">
                                </form>
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
                            <li><a>${i}<span>${current}</span></a></li>
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
            <form action="/controller" method="POST">
                <label for="records">${records}:</label>
                <select id="records" name="recordsPerPage">
                    <option value="4">4</option>
                    <option value="8">8</option>
                    <option value="12">12</option>
                </select>
                <input type="hidden" name="currentPage" value="1">
                <input type="hidden" name="command" value="MAIN_PAGE">
                <input type="submit" value="Submit">
            </form>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
