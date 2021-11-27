<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="userinterface"/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>All Activities</title>
    <link rel="stylesheet" href="styles.css">
</head>

<body>
<jsp:include page="admin-header.jsp"/>
<div class="header-bar">
        <h3>
            <a href="user-list"><fmt:message key="label.users"/></a>&nbsp;
            <a href="category-list"><fmt:message key="label.categories"/></a>&nbsp;
            <fmt:message key="label.activities"/>
        </h3>
</div>
<br>
<div>
    <table style="margin-left: auto; margin-right: auto;">
        <tr>
            <th>
                ID
                <a href="activity-all?activitySortBy=id&activityOrder=desc">-</a>
                <a href="activity-all?activitySortBy=id&activityOrder=asc">+</a>
            </th>
            <th>
                <fmt:message key="label.nameobj"/>
                <a href="activity-all?activitySortBy=name&activityOrder=desc">-</a>
                <a href="activity-all?activitySortBy=name&activityOrder=asc">+</a>
            </th>
            <th>
                <fmt:message key="label.category"/>
                <a href="activity-all?activitySortBy=category&activityOrder=desc">-</a>
                <a href="activity-all?activitySortBy=category&activityOrder=asc">+</a>
            </th>
            <th>
                <fmt:message key="label.users"/>
                <a href="activity-all?activitySortBy=userCount&activityOrder=desc">-</a>
                <a href="activity-all?activitySortBy=userCount&activityOrder=asc">+</a>
            </th>
            <th>
                <fmt:message key="label.time"/>
                <a href="activity-all?activitySortBy=totalTime&activityOrder=desc">-</a>
                <a href="activity-all?activitySortBy=totalTime&activityOrder=asc">+</a>
            </th>
        </tr>
        <c:forEach items="${activities}" var="s">
        <tr>
            <td>
                ${s.id}
            </td>
            <td>
                ${s.name}
            </td>
            <td>
                ${s.category.name}
            </td>
            <td>
                ${s.userCount}
            </td>
            <td>
                <c:set value="${s.totalTime/60}" var="hours" />
                <fmt:formatNumber var="hours" value="${hours}" maxFractionDigits="0" />

                ${hours}&nbsp;<fmt:message key="label.hour"/>&nbsp;${s.totalTime%60}&nbsp;<fmt:message key="label.min"/>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
<br>
<div class="message-bar">
    <p>
    <fmt:message key="label.page"/>: &nbsp;
    <c:forEach begin="1" end="${requestScope.numPages}" varStatus="loop">
        <c:if test="${loop.index == requestScope.page}">
            <a href="activity-all?activityPage=${loop.index}"><b>${loop.index}</b></a>
        </c:if>
        <c:if test="${loop.index != requestScope.page}">
            <a href="activity-all?activityPage=${loop.index}">${loop.index}</a>
        </c:if>

    </c:forEach>
    </p>
</div>

</body>