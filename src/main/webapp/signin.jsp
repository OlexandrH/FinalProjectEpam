<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="userinterface"/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sign-in</title>
    <link rel="stylesheet" href="styles.css">
</head>

<body>

<jsp:include page="welcome-header.jsp"/>

<div class="header-bar">
    <h2><fmt:message key="label.sign"/></h2>
</div>

<br>

<div class="register-form">
    <form action="signin" method="post">
        <h3></h3>
        <label><fmt:message key="label.login"/>:</label>
        <br>
        <input type="text" name="userLogin" required>
        <br> <br>
        <label><fmt:message key="label.password"/>:</label>
        <br>
        <input name="userPassword" type="password" required>
        <br> <br>
           <fmt:message key="label.sign" var="buttonText"/>
        <input name="submit" type="submit" value="${buttonText}">
        <br> <br>
        <c:if test="${requestScope.errorMsg != null}">
            <label style="color: red;"><fmt:message key="${requestScope.errorMsg}"/></label>
        </c:if>
    </form>
</div>
<br>
<div class="message-bar">
<h4><fmt:message key="label.noaccount"/> <a href="register.jsp"><fmt:message key="label.register"/></a></h4>
</div>

</body>

</html>

