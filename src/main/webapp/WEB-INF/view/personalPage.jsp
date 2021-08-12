<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="edit" type="java.lang.String"--%>
<html>
    <head>
        <title>PersonalPage</title>
        <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
        <fmt:setLocale value="${sessionScope.local}"/>
        <fmt:setBundle basename="localization.local" var="loc"/>

        <fmt:message bundle="${loc}" key="local.personalpage" var="personal_page"/>
        <fmt:message bundle="${loc}" key="local.firstname" var="firstname"/>
        <fmt:message bundle="${loc}" key="local.lastname" var="lastname"/>
        <fmt:message bundle="${loc}" key="local.email" var="email"/>
        <fmt:message bundle="${loc}" key="local.locbutton.back" var="back"/>
        <fmt:message bundle="${loc}" key="local.date.reg" var="date"/>
        <fmt:message bundle="${loc}" key="local.age" var="age"/>
    </head>
    <body>
        <jsp:include page="headerForForm.jsp"/>
        <h1 style="margin: 0 17%">${personal_page}</h1>
        <c:choose>
            <c:when test="${edit == null}">
                <div style="float: left; margin: 30px 200px 0 17%;">
                    <div>
                        <p>${firstname}: <span>${sessionScope.user.firstName}</span></p>
                        <p>${lastname}: <span>${sessionScope.user.lastName}</span></p>
                        <p>${age}: <span>${sessionScope.user.age}</span></p>
                        <p>${email}: <span>${sessionScope.user.email}</span></p>
                        <hr>
                        <p>${date}: <span>${sessionScope.user.dateRegistered}</span></p>
                    </div>
                    <form action="/controller" method="POST">
                        <input type="hidden" name="command" value="PERSONAL_PAGE">
                        <input type="hidden" name="edit" value="edit">
                        <input class="submit" type="submit" value="Edit">
                    </form>
                </div>
            </c:when>
            <c:when test="${edit != null}">
                <div style="margin: 30px 17% 0; float: left">
                    <form action="/controller" method="POST">
                        <div class="field">
                            <label class="field" for="firstname">${firstname}:</label>
                            <input class="per_input" id="firstname" type="text" name="firstname" autofocus>
                        </div>
                        <div class="field">
                            <label class="field" for="lastname">${lastname}:</label>
                            <input class="per_input" id="lastname" type="text" name="lastname">
                        </div>
                        <div class="field">
                            <label class="field" for="age">${age}:</label>
                            <input class="per_input" id="age" type="number" name="age">
                        </div>
                        <div class="field">
                            <label class="field" for="email">${email}:</label>
                            <input class="per_input" id="email" type="email" name="email" aria-required="false">
                        </div>
                        <div class="field">
                            <input type="hidden" name="command" value="UPDATE_USER">
                            <input class="submit" type="submit" value="Submit">
                        </div>
                    </form>
                    <p style="clear: both; margin-left: 240px">
                        <button onclick="location.href='..'">${back}</button>
                    </p>
                </div>
            </c:when>
        </c:choose>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
