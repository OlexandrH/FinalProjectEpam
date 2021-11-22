<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="userinterface"/>


<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Category List</title>
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
        <fmt:message key="label.categories"/>
        </h2>
</div>
<br>
<table class="center">

    <tr>
           <td><b><fmt:message key="label.id"/></b></td>
           <td><b><fmt:message key="label.nameobj"/><b></td>
           <td><b><fmt:message key="label.activities"/><b></td>
           <td> </td>
           <td> </td>
           </tr>
    <c:forEach items="${categories}" var="s">
       <tr>
       <td>${s.id}</td>
       <td>${s.name}</td>

        <td>
        <a href="activity-list?cat-id=${s.id}"><fmt:message key="label.show"/></a>
        </td>

       <td>
       <a href="category-edit?id=${s.id}"><fmt:message key="label.edit"/></a>
       </td>

      <td>
        <form action="category-list" method="post">
        <fmt:message key="label.delete" var="buttonText"/>
        <input type="submit" value="${buttonText}">
        <input type="hidden" name="id" value="${s.id}">
       </form>
      </td>

    </c:forEach>
</table>
    <br>
    <div class="message-bar">
        <h3>

        <h3>
    </div>
    <br>

    <div style="font-family: 'Segoe UI';background-color: white; display:flex; justify-content: center;">
        <fmt:message key="label.add"/><br>
        <fmt:message key="label.add" var="buttonText"/>
        <form action="category-add" method="post">:
            <input name="name" type="text" required>
            <input type="submit" value="${buttonText}">
        </form>
    </div>
</body>