<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="userinterface"/>

<body>
       <div class="abc">
            &nbsp;
            <a href="?sessionLocale=en">ENG</a>&nbsp;
            <a href="?sessionLocale=ua">УКР</a>&nbsp;
        </div>
</body>
