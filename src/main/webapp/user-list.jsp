<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="userinterface"/>


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User List</title>
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
        <fmt:message key="label.users"/>
        </h2>
</div>
<br>
<table class="center">

    <tr>
           <th><fmt:message key="label.id"/></th>
           <th><fmt:message key="label.login"/></th>
           <th><fmt:message key="label.name"/></th>
           <th><fmt:message key="label.role"/></th>
           <th> </th>
           <th> </th>
           </tr>
    <c:forEach items="${userList}" var="s">
       <tr>
       <td>${s.id}</td>
       <td>${s.login}</td>
       <td>${s.name}</td>
       <td>${s.role}</td>
       <td>
       <a href="user-edit?id=${s.id}"><fmt:message key="label.edit"/></a>
        </td>
      <td>
        <form action="user-delete" method="post">
        <fmt:message key="label.delete" var="buttonText"/>
        <input type="submit" value="${buttonText}">
        <input type="hidden" name="id" value="${s.id}">
       </form>
      </td>
       </tr>
    </c:forEach>
</table>
<div class="message-bar">
<p>
<a href="user-list?page=first"><fmt:message key="label.first"/></a>
<a href="user-list?page=prev"><fmt:message key="label.prev"/></a>
<a href="user-list?page=next"><fmt:message key="label.next"/></a>
</p>
</div>
</body>