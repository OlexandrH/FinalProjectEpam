<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="userinterface"/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Administrator Page</title>
    <link rel="stylesheet" href="styles.css">
</head>

<body>
        <a href="?sessionLocale=en">ENG</a>
        <a href="?sessionLocale=ua">UKR</a>
<div class="header-bar">
        <h2>
            <fmt:message key="label.hello" />, admin!
        </h2>
</div>

<form action="test" method="post">
    <input type="text" name="test">
    <input type="submit">
</form>

</body>
