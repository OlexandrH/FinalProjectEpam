<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="userinterface"/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Administrator Page</title>
    <link rel="stylesheet" href="styles.css">
</head>

<body>

<jsp:include page="admin-header.jsp"/>

<div class="header-bar">
        <h2>
            <fmt:message key="label.hello" />, admin!
        </h2>
</div>

<div style="font-family: 'Segoe UI';background-color: white; display:flex; justify-content: center;">

       <ul>
       <li><a href="user-list?userPage=1"><fmt:message key="label.users"/></a></li> <br>
       <li><a href="category-list"><fmt:message key="label.categories"/></a></li> <br>
       <li><a href="activity-all"><fmt:message key="label.activities"/></a></li> <br>
       <li><a href="users-activities?page=first"><fmt:message key="label.usersactivities"/></a></li>
       <ul>

</div>

</body>
