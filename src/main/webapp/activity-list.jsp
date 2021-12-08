<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="userinterface"/>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Activity List</title>
    <link rel="stylesheet" href="styles.css">
  
    <style>
        table {
          border-collapse: collapse;
          width: 60%;
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
        <h3>
            <fmt:message key="label.activities"/>
            <a href="category-list"><fmt:message key="label.categories"/></a>
        </h3>
</div>
<br>

<table class="center">

    <tr>
           <th><fmt:message key="label.id"/></th>
           <th><fmt:message key="label.nameobj"/></th>
           <th><fmt:message key="label.category"/></th>
           <th><fmt:message key="label.edit"/></th>
           <th></th>
    </tr>

    <c:forEach items="${activities}" var="s">
       <tr>
       <td>${s.id}</td>
       <td>${s.name}</td>
       <td>${s.category.name}</td>
       <td>
            <form action="activity-list" method="post" style="display: inline" >
                <input type="text" name="name" value="${s.name}" pattern=".0-9A-Za-zА-Яа-яҐґІіЇіЄє\s]{3,30}" minlength="2" maxlength="30" required/>

                <select name="cat-id" value="${s.category.id}">
                    <c:forEach items="${categories}" var="n">
                        <c:if test="${n.name == s.category.name}">
                            <option value="${s.category.id}"selected>${n.name}</option>
                        </c:if>
                        <c:if test="${n.name != s.category.name}">
                        <option value="${n.id}">${n.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <input type="hidden" name="param" value="update"/>
                <input type="hidden" name="id" value="${s.id}">
                <button type="submit"><fmt:message key="label.save"/></button>
                <button type="reset"><fmt:message key="label.restore"/></button>

            </form>
       </td>

      <td>
        <form action="activity-list" method="post" style="display: inline">
        <fmt:message key="label.delete" var="buttonText"/>
        <input type="submit" value="${buttonText}"/>
        <input type="hidden" name="id" value="${s.id}"/>
        <input type="hidden" name="param" value="delete"/>
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
        <form action="activity-list" method="post">:
            <input name="name" type="text" required>
            <fmt:message key="label.add" var="buttonText"/>
             <c:set var="cat" value="${activities[0].category.id}"/>
             <c:set var="cat" value="${param[\"cat-id\"]}"/>
             <input type="hidden" name="cat-id" value="${cat}"/>
             <input type="hidden" name="param" value="add"/>
            <input type="submit" value="${buttonText}">
        </form>
    </div>

</body>