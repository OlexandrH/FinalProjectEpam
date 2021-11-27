<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="userinterface"/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error</title>
    <link rel="stylesheet" href="styles.css">

    <style>
        table {
          border-collapse: collapse;
          width: 50%;
           margin-left: auto;
          margin-right: auto;
        }

        th, td {
          text-align: left;
          padding: 8px;
        }

        tr:nth-child(odd) {
            background-color: #f2f2f2;
        }

        tr {
        	height: 55px;
        }

    </style>

</head>
<body>
    <jsp:include page="welcome-header.jsp"/>
    <div class="header-bar">
        <h2><fmt:message key="label.error"/> :)</h2>
    </div>
</body>
