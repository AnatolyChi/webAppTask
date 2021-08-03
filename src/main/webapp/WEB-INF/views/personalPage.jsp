<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    </head>
    <body>
        <jsp:include page="headerForForm.jsp"/>
        <h1 style="margin: 0 17%">${personal_page}</h1>
        <section style="margin: 30px 17% 0; float: left">
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
                    <label class="field" for="email">${email}:</label>
                    <input class="per_input" id="email" type="email" name="email">
                </div>
                <div class="field">
                    <input type="hidden" name="command" value="UPDATE_USER">
                    <input class="submit" type="submit" value="Submit">
                </div>
            </form>
        </section>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
