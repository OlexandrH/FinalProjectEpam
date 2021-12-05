<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}" />
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
        <h3>
            <a href="user-list"><fmt:message key="label.users"/></a>&nbsp;
            <fmt:message key="label.categories"/>&nbsp;
            <a href="activity-all"><fmt:message key="label.activities"/></a>
        </h3>
</div>
<br>
<table class="center">

    <tr>
           <th><fmt:message key="label.id"/>
                <a href="category-list?categorySortBy=id&categoryOrder=desc">-</a>
                <a href="category-list?categorySortBy=id&categoryOrder=asc">+</a>
           </th>
           <th><fmt:message key="label.nameobj"/>
                <a href="category-list?categorySortBy=name&categoryOrder=desc">-</a>
                <a href="category-list?categorySortBy=name&categoryOrder=asc">+</a>
           </th>
           <th><fmt:message key="label.activities"/></th>
           <th><fmt:message key="label.edit"/></th>
           <th> </th>
           </tr>
    <c:forEach items="${categories}" var="s">
       <tr>
       <td>${s.id}</td>
       <td>${s.name}</td>

        <td>
        <a href="activity-list?cat-id=${s.id}"><fmt:message key="label.show"/></a>
        </td>

       <td>
       <form action="category-list" method="post" style="display: inline">
       <input type="text" value="${s.name}" name="name" pattern="[.0-9A-Za-zА-Яа-яҐґІіЇіЄє\s]{3,30}" required/>
       <input type="hidden" value="${s.id}" name="id"/>
       <input type="hidden" value="update" name="param"/>
       <button type="submit" style="display: inline" ><fmt:message key="label.save"/> </button>
       <button type="reset" style="display: inline"><fmt:message key="label.restore"/></button>
       </form>
       </td>

      <td>
        <form action="category-list" method="post" style="display: inline">
        <input type="hidden" name="id" value="${s.id}"/>
        <input type="hidden" name="param" value="delete"/>
        <button type="submit" style="display: inline"> <fmt:message key="label.delete"/> </button>
       </form>
      </td>

    </c:forEach>

</table>

    <br>
    <div class="message-bar">

            <fmt:message key="label.page"/>:&nbsp;
            <c:forEach begin="1" end="${requestScope.numPages}" varStatus="loop">

                <c:if test="${loop.index == requestScope.page}">
                    <a href="category-list?categoryPage=${loop.index}"><b>${loop.index} </b></a>
                </c:if>

                <c:if test="${loop.index != requestScope.page}">
                    <a href="category-list?categoryPage=${loop.index}">${loop.index} </a>
                </c:if>
                &nbsp;
            </c:forEach>

    </div>
    <br>

    <div style="font-family: 'Segoe UI';background-color: white; display:flex; justify-content: center;">
        <fmt:message key="label.add"/><br>
        <form action="category-list" method="post">:
            <input name="name" type="text" pattern="[.0-9A-Za-zА-Яа-яҐґІіЇіЄє\s]{3,30}" required/>
            <input type="hidden" name="param" value="add"/>
            <button type="submit"><fmt:message key="label.add"/></button>
        </form>
    </div>

</body>