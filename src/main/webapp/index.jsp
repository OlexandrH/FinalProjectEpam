<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="userinterface"/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome</title>
    <link rel="stylesheet" href="styles.css">
</head>

<body>
<jsp:include page="welcome-header.jsp"/>

<div class="header-bar">
    <h2><fmt:message key="label.welcome"/> :)</h2>
</div>
<br>
<div class="header-bar">
    <h4><fmt:message key="label.please"/>, <a href = "signin"><fmt:message key="label.signin"/></a> <fmt:message key="label.or"/> <a href="register.jsp"><fmt:message key="label.register"/></a> </h4>
</div>

</body>

