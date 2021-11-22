<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="userinterface"/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit User</title>
    <link rel="stylesheet" href="styles.css">
</head>

<body>
<jsp:include page="admin-header-nolang.jsp"/>
<div class="header-bar">
        <h2>
            <fmt:message key="label.edituser"/>
        </h2>
</div>
<br>
<div class="register-form">
     <form action="user-edit" method="post">
         <label><fmt:message key="label.login"/></label>
                <br>
                <input name="login" type="text" value="${user.login}" disabled>
                <br> <br>
                <label><fmt:message key="label.changepassword"/>:</label>
                <br>
                <input name="password" type="password" value="${user.password}" required>
                <br> <br>
                <input name="id" type="hidden" value="${user.id}"/>
                    <!--
                    <label><fmt:message key="label.repeatpassword"/>:</label>
                    <br>
                    <input name="user_password" type="password" required>
                    <br> <br>
                    -->
                <label><fmt:message key="label.changename"/>:</label>
                <br>
                <input name="name" type="text" value="${user.name}" minlength="2" pattern="[.a-zA-ZА-Яа-яҐґЇїІіЄє0-9_]{2,}" required/>
                <br> <br>
        <fmt:message key="label.save" var="buttonText"/>
        <input type = "submit" value = "${buttonText}">
        <fmt:message key="label.back" var="buttonText"/>
        <a href="user-list"><button type="button"><fmt:message key="label.back"/></button></a>
     </form>
     </div>
</body>