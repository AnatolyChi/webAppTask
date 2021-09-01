<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="valid_err" type="java.lang.String"--%>
<html>
    <head>
        <title>Update personal page</title>
        <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
        <fmt:setLocale value="${sessionScope.local}"/>
        <fmt:setBundle basename="localization.local" var="loc"/>

        <fmt:message bundle="${loc}" key="local.personal_page" var="personal_page"/>
        <fmt:message bundle="${loc}" key="local.firstname" var="firstname"/>
        <fmt:message bundle="${loc}" key="local.lastname" var="lastname"/>
        <fmt:message bundle="${loc}" key="local.email" var="email"/>
        <fmt:message bundle="${loc}" key="local.age" var="age"/>
        <fmt:message bundle="${loc}" key="local.locbutton.back" var="back"/>
        <fmt:message bundle="${loc}" key="local.valid.update.user" var="check_update"/>
        <fmt:message bundle="${loc}" key="local.locbutton.submit" var="submit"/>
    </head>
    <body>
        <jsp:include page="header_single.jsp"/>
        <h1 style="margin: 0 17%">${personal_page}</h1>
        <div style="margin: 30px 17% 0; float: left">
            <c:if test="${valid_err != null}">
                <p style="text-align: center">${check_update}</p>
            </c:if>
            <form action="/controller" method="POST" class="form_person">
                <div class="div_form_person">
                    <label class="label_form_person" for="firstname">
                        ${firstname}:
                    </label>
                    <input class="input_form_person" id="firstname" type="text"
                           name="firstname" value="${sessionScope.user.firstName}" autofocus>
                </div>
                <div class="div_form_person">
                    <label class="label_form_person" for="lastname">
                        ${lastname}:
                    </label>
                    <input class="input_form_person" id="lastname" type="text"
                           name="lastname" value="${sessionScope.user.lastName}">
                </div>
                <div class="div_form_person">
                    <label class="label_form_person" for="age">
                        ${age}:
                    </label>
                    <input class="input_form_person" id="age" type="number"
                           name="age" value="${sessionScope.user.age}">
                </div>
                <div class="div_form_person">
                    <label class="label_form_person" for="email">
                        ${email}:
                    </label>
                    <input class="input_form_person" id="email" type="email"
                           name="email" value="${sessionScope.user.email}">
                </div>
                <div class="div_form_person">
                    <input type="hidden" name="command" value="UPDATE_USER">
                    <input type="hidden" name="user_id" value="${sessionScope.user.id}">
                    <input style="margin-left: 100px" class="submit" type="submit" value="${submit}">
                </div>
            </form>
            <p style="clear: both; margin-left: 240px">
                <button onclick="location.href='..'">${back}</button>
            </p>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
