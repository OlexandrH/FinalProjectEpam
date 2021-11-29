<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="userinterface"/>


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User List</title>
    <link rel="stylesheet" href="styles.css">

    <style>
        table {
          border-collapse: collapse;
         // width: 50%;
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
            <fmt:message key="label.users"/>&nbsp;
            <a href="category-list"><fmt:message key="label.categories"/></a>&nbsp;
            <a href="activity-all"><fmt:message key="label.activities"/></a>
        </h3>
</div>
<br>
<div style="display: table; width: 100%;">
    <div style="display: table-row;">
        <div style="display: table-cell; width: 50%;">
            <div class="message-bar">
            <p>
            <label style><b><fmt:message key="label.userlist"/></b></label>
            </p>
            </div>
            <table style="border-collapse: collapse; width: 100%;">
                <title></title>
                <tr>
                    <th>
                        <fmt:message key="label.id"/>
                        <a href="user-list?userSortBy=id&amp;userOrder=desc">-</a>
                        <a href="user-list?userSortBy=id&amp;userOrder=asc">+</a>
                    </th>
                    <th>
                        <fmt:message key="label.login"/>
                        <a href="user-list?userSortBy=login&amp;userOrder=desc">-</a>
                        <a href="user-list?userSortBy=login&amp;userOrder=asc">+</a>
                    </th>
                    <th>
                        <fmt:message key="label.name"/>
                        <a href="user-list?userSortBy=name&amp;userOrder=desc">-</a>
                        <a href="user-list?userSortBy=name&amp;userOrder=asc">+</a>
                    </th>
                    <th>
                        <fmt:message key="label.role"/>
                    </th>
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
                        <a href="user-list?editUserId=${s.id}"><fmt:message key="label.edit"/></a>
                    </td>
                    <td>
                        <div>
                        <form action="user-delete" method="post" style="display: inline;">

                            <fmt:message key="label.delete" var="buttonText"/>
                            <input type="submit" value="${buttonText}"/>
                            <input type="hidden" name="id" value="${s.id}"/>

                        </form>
                        </div>
                    </td>
                </tr>
                </c:forEach>
            </table>

            <div class="message-bar">
                <p>
                    <fmt:message key="label.page" />:&nbsp;
                    <c:forEach begin="1" end="${numPages}" varStatus="loop">

                        <c:if test="${loop.index == requestScope.userPage}">
                            <a href="user-list?userPage=${loop.index}"><b>${loop.index}</b></a>&nbsp;
                        </c:if>
                        <c:if test="${loop.index != requestScope.userPage}">
                            <a href="user-list?userPage=${loop.index}">${loop.index}</a>&nbsp;
                        </c:if>
                    </c:forEach>
                </p>
            </div>
        </div>
        <div style="display: table-cell; width: 10px;">
            
        </div>

         <div style="display: table-cell;">
            <div class="message-bar">
               <p>
               <b>${sessionScope.editUser.login} (id = ${sessionScope.editUser.id})</b>
                </p>
            </div>
            <form action="user-edit" method="POST">


                <br>
                <b>
                &nbsp;&nbsp;<label ><fmt:message key="label.edit"/></label>:<br>
                </b>
                <br>
                &nbsp;&nbsp;<label><fmt:message key="label.name"/>:</label>
                <input name="name" type="text" value="${sessionScope.editUser.name}" minlength="2" maxlength="30" pattern="[.a-zA-ZА-Яа-яҐґЇїІіЄє0-9_\s]{2,30}" required/>
                &nbsp;&nbsp;<label><fmt:message key="label.password"/>:</label>
                <input name="password" type="password" value="${sessionScope.editUser.password}" minlength="3" maxlength="30" pattern="[a-zA-Z0-9_]{3,30}" required/>
                <input type="hidden" name="id" value="${sessionScope.editUser.id}">
                &nbsp;&nbsp;<button type="submit"><fmt:message key="label.save"/></button>&nbsp;
                <button type="reset"><fmt:message key="label.restore"/></button>&nbsp;
            </form>
            <hr>


            <div class="header-bar">
                <fmt:message key="label.activities"/>
            </div>
                <table style="width: 100%;">
                    <tr>
                    <th>
                        <fmt:message key="label.id"/>
                        <a href="user-list?usersActSortBy=id&usersActOrder=desc">-</a>
                        <a href="user-list?usersActSortBy=id&usersActOrder=asc">+</a>
                    </th>
                    <th>
                        <fmt:message key="label.activity"/>
                        <a href="user-list?usersActSortBy=activity&usersActOrder=desc">-</a>
                        <a href="user-list?usersActSortBy=activity&usersActOrder=asc">+</a>
                    </th>
                    <th>
                        <fmt:message key="label.category"/>
                        <a href="user-list?usersActSortBy=category&usersActOrder=desc">-</a>
                        <a href="user-list?usersActSortBy=category&usersActOrder=asc">+</a>
                    </th>
                    <th>
                        <fmt:message key="label.time"/>
                         <a href="user-list?usersActSortBy=amount_time&usersActOrder=desc">-</a>
                         <a href="user-list?usersActSortBy=amount_time&usersActOrder=asc">+</a>
                    </th>
                    <th>
                        <fmt:message key="label.status"/>
                         <a href="user-list?usersActSortBy=status&usersActOrder=desc">-</a>
                         <a href="user-list?usersActSortBy=status&usersActOrder=asc">+</a>
                    </th>
                    <th>
                    </th>
                    <th>
                    </th>
                    </tr>
                    <c:forEach items = "${usersActList}" var="s">
                        <tr>
                            <td>
                                ${s.id}
                            </td>
                            <td>
                                ${s.activity.name}
                            </td>
                            <td>
                                ${s.activity.category.name}
                            </td>
                            <td>
                                <c:set value="${s.amountTime/60}" var="hour"/>
                                <fmt:formatNumber var="hour" value="${hour}" maxFractionDigits="0"/>
                                ${hour}&nbsp;<fmt:message key="label.hour"/>&nbsp;${s.amountTime%60}&nbsp;<fmt:message key="label.min" />
                            </td>
                            <td>
                                ${s.status}
                            </td>
                            <td>
                                <c:if test="${s.status != 'UNBOOKED'}">
                                <form action="user-act-add" method="get" style="display: inline;">
                                    <input type="hidden" name="status" value="2"/>
                                    <input type="hidden" name="id" value="${s.id}"/>

                                    <button type="submit"> <fmt:message key="label.accept"/> </button>

                                </form>
                                </c:if>
                            </td>
                            <td>
                                <form action="user-act-add" method="post" style="display: inline;">
                                    <input type="hidden" name="id" value="${s.id}"/>
                                    <input type="hidden" name="delete" value="true"/>
                                    <button type="submit"> <fmt:message key="label.delete"/> </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="message-bar">
                    <fmt:message key="label.page"/>:&nbsp;
                    <c:forEach begin="1" end="${numUsersActPages}" varStatus="loop">
                        <c:if test="${loop.index == sessionScope.usersActPage}">
                        <a href="user-list?usersActPage=${loop.index}&editUserId=${sessionScope.editUser.id}"><b>${loop.index}</b></a>
                        </c:if>
                        <c:if test="${loop.index != sessionScope.usersActPage}">
                        <a href="user-list?editUserId=${sessionScope.editUser.id}&usersActPage=${loop.index}">${loop.index}</a>
                        </c:if> &nbsp;
                    </c:forEach>
                </div>
        </div>   
    </div>
</div>
</body>