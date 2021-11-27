<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="userinterface"/>

<head>



</head>


       <div class="abc">
            <a href="admin.jsp"> <fmt:message key="label.adminpage"/> </a> &nbsp;
            <a href="signout"> <fmt:message key="label.signout"/> </a> &nbsp;&nbsp;&nbsp;
        </div>


