<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="userinterface"/>


<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Activity List</title>
    <link rel="stylesheet" href="styles.css">
  
    <style>
        table {
          border-collapse: collapse;
          width: 50%;
        }

        th, td {
          text-align: left;
          padding: 8px;
        }

        tr:nth-child(odd) {
            background-color: #f2f2f2;
        }

        table.center {
          margin-left: auto;
          margin-right: auto;
        }
    </style>

</head>

<body>

<jsp:include page="admin-header.jsp"/>

<div class="header-bar">
        <h2>
        <fmt:message key="label.activities"/>
        </h2>
</div>
<br>

<table class="center">

    <tr>
           <td><b><fmt:message key="label.id"/></b></td>
           <td><b><fmt:message key="label.nameobj"/></b></td>
           <td><b><fmt:message key="label.category"/></b></td>
           <td> </td>
           <td> </td>
           </tr>
    <c:forEach items="${activities}" var="s">
       <tr>
       <td>${s.id}</td>
       <td>${s.name}</td>
       <td>${s.category.name}</td>

       <td>
       <a href="activity-edit?id=${s.id}"><fmt:message key="label.edit"/></a>
       </td>

      <td>
        <form action="activity-list" method="post">
        <fmt:message key="label.delete" var="buttonText"/>
        <input type="submit" value="${buttonText}">
        <input type="hidden" name="id" value="${s.id}">
       </form>
      </td>
       </tr>
    </c:forEach>

</table>

    <div class="message-bar">
        <h3>

        <h3>
    </div>
    <br>

    <div style="font-family: 'Segoe UI';background-color: white; display:flex; justify-content: center;">
        <fmt:message key="label.add"/><br>
        <form action="activity-add" method="post">:
            <input name="name" type="text" required>
            <fmt:message key="label.add" var="buttonText"/>
             <c:set var="cat" value="${activities[0].category.id}"/>
             <c:set var="cat" value="${param[\"cat-id\"]}"/>
             <input type="hidden" name="cat-id" value="${cat}">
            <input type="submit" value="${buttonText}">
        </form>
    </div>




</body>