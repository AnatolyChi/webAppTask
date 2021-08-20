<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="content" type="java.lang.String"--%>
<%--@elvariable id="title" type="java.lang.String"--%>
<html>
    <head>
        <title>News</title>
        <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
    </head>
    <body>
        <jsp:include page="headerForForm.jsp"/>
        <div style="margin-left: 100px; margin-right: 100px; margin-top: 50px">
            <p style="text-align: center">${title}</p>
            <hr>
            <p style="text-align: center">${content}</p>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
