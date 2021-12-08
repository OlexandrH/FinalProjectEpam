<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="userinterface"/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sign-in</title>
    <link rel="stylesheet" href="styles.css">
</head>

<jsp:include page="welcome-header.jsp"/>
<body>
<div class="header-bar">
    <h2><fmt:message key="label.registercap"/></h2>
</div>
<br>
<div class="register-form">
    <form action="user-add" method="post">
        <h3></h3>
        <label><fmt:message key="label.login"/>:</label>
        <br>
        <input name="login" type="text" minlength="3" maxlength="30" pattern="[a-zA-Z0-9_]{3,30}" required/>
        <br> <br>
        <label><fmt:message key="label.password"/>:</label>
        <br>
        <input name="password" type="password" minlength="3" maxlength="30" pattern="[a-zA-Z0-9_]{3,30}" required/>
        <br> <br>
        <!--
        <label>Repeat password:</label>
        <br>
        <input name="user_password" type="password" required>
        <br> <br>
        -->
        <label><fmt:message key="label.name"/>:</label>
        <br>
        <input name="name" type="text" minlength="2" maxlength="30" pattern="[.a-zA-ZА-Яа-яҐґЇїІіЄє0-9_\s]{2,30}" required/>
        <br> <br>
        <fmt:message key="label.registercap" var="buttonText"/>
        <input name="submit" type="submit" value="${buttonText}">
        <br>
        <br>
        <c:if test="${requestScope.errorMsg != null}">
            <label style="color: red;"><fmt:message key="${requestScope.errorMsg}"/></label>
        </c:if>
    </form>

</div>
<br>
<div class="message-bar">
    <h4><fmt:message key="label.haveaccount"/><a href="signin.jsp"> <fmt:message key="label.signin"/></a></h4>
    <!-- <h4>If you already have an account, please <a href="signin.jsp">sign-in</a></h4> -->
</div>

</body>
</html>