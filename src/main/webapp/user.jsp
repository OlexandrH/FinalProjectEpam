<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="userinterface"/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Personal page</title>
    <link rel="stylesheet" href="styles.css">
    <script type="text/javascript">
    function toggleEdit() {
 		var edit = document.getElementById("edit");
  		if (edit.style.display === "none") {
    		edit.style.display = "block";
  		} else {
    	edit.style.display = "none";
  		}
	} 
	</script>

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

<jsp:include page="user-header.jsp"/>

<div class="header-bar">
        <h2>
            <fmt:message key="label.hello" />, ${sessionScope.user.name}
        </h2>

</div>
<br>

<div style = "display: table; width: 100%" >
    <div style="display: table-row;">

        <div style="display: table-cell; width: 20%;">
            <div class="message-bar"><p><b><fmt:message key="label.mydata"/></b></p></div>
            <div style="background-color: lightblue">

            <form  action="user-edit" method="POST" style="display: inline;">
                &nbsp;&nbsp;<label class="fixed-width"><fmt:message key="label.name" /></label>
                <input type="text" name="name" value="${sessionScope.user.name}" minlength="2" maxlength="30" pattern="[.a-zA-ZА-Яа-яҐґЇїІіЄє0-9_\s]{2,30}" required/>
                <br>
                <br>
                &nbsp;&nbsp;<label class="fixed-width"><fmt:message key="label.password" /></label>
                <input type="text" name="password" value="${sessionScope.user.password}" minlength="3" maxlength="30" pattern="[a-zA-Z0-9_]{3,30}" required />
                <input type="hidden" name="id" value="${sessionScope.user.id}"/>
                <br>
                <br>
                &nbsp;&nbsp;<button type= "submit" style="display: inline"><fmt:message key="label.save" /></button>
                <button type="reset" style="display: inline"><fmt:message key="label.restore" /></button>
            </form>
            <p>
                &nbsp;
            </p>
        </div>
        </div>
        <div style="display: table-cell; width: 10px;"></div>
        <div style="display: table-cell;">


            <div style= "font-family: 'Segoe UI';background-color: lightblue; display:flex; justify-content: center;">
                <p>
                    <b><fmt:message key="label.myactivities" /></b>
                </p>
            </div>

                <br>
            <div>
                <table style="width: 100%">

                    <tr>
                        <th width="50"><fmt:message key="label.id"/>
                            <a href="user-page?usersActSortBy=id&amp;usersActOrder=desc">-</a>
                            <a href="user-page?usersActSortBy=id&amp;usersActOrder=asc">+</a>
                        </th>

                        <th width="120"><fmt:message key="label.activity"/>
                            <a href="user-page?usersActSortBy=activity&amp;usersActOrder=desc">-</a>
                            <a href="user-page?usersActSortBy=activity&amp;usersActOrder=asc">+</a>
                        </th>

                        <th width="120">
                            <fmt:message key="label.category"/>
                            <a href="user-page?usersActSortBy=category&amp;usersActOrder=desc">-</a>
                            <a href="user-page?usersActSortBy=category&amp;usersActOrder=asc">+</a>
                        </th>

                        <th width="120">
                            <fmt:message key="label.time"/>
                            <a href="user-page?usersActSortBy=amount_time&amp;usersActOrder=desc">-</a>
                            <a href="user-page?usersActSortBy=amount_time&amp;usersActOrder=asc">+</a>
                        </th>

                        <th width="120">
                            <fmt:message key="label.status"/>
                            <a href="user-page?usersActSortBy=status&amp;usersActOrder=desc">-</a>
                            <a href="user-page?usersActSortBy=status&amp;usersActOrder=asc">+</a>
                        </th>

                        <th width="80"></th>
                    </tr>

                    <c:forEach items="${usersActivities}" var="s">

                        <tr>

                            <td>${s.id}</td>

                            <td>${s.activity.name}</td>

                            <td>${s.activity.category.name}</td>

                                <c:set value="${s.amountTime/60}" var="hours"/>
                                <fmt:formatNumber var="hours" value="${hours}" maxFractionDigits="0" />
                            <td>${hours}&nbsp;<fmt:message key="label.hour"/>&nbsp;${s.amountTime%60}&nbsp;<fmt:message key="label.min"/></td>

                            <td>${s.status}</td>

                            <td>
                                <form action="user-act-add" method="get">
                                    <c:if test="${s.status == 'BOOKED'}">
                                    <fmt:message key="label.unbook" var="buttonText"/>
                                    <input type="submit" value="${buttonText}"/>
                                    <input type="hidden" name="id" value="${s.id}"/>
                                    <input type ="hidden" name="status" value="1"/>
                                    </c:if>
                                </form>
                            </td>
                        </tr>

                    </c:forEach>
                </table>
            </div>

            <div class="message-bar">
                <p>
                    <fmt:message key="label.page" />:&nbsp;
                    <c:forEach begin="1" end="${numPages}" varStatus="loop">
                    <c:if test="${loop.index == sessionScope.usersActPage}">
                    <a href="user-page?usersActPage=${loop.index}"><b>${loop.index}</b></a>&nbsp;
                    </c:if>
                    <c:if test="${loop.index != sessionScope.usersActPage}">
                    <a href="user-page?usersActPage=${loop.index}">${loop.index}</a>&nbsp;
                    </c:if>
                    </c:forEach>
                </p>
            </div>
                <br>
            <div style="font-family: 'Segoe UI';background-color: white; display:flex; justify-content: center;">
                <form action="user-act-add" method="POST">
                    <b>
                        <fmt:message key="label.add"/>:&nbsp;&nbsp;&nbsp;
                    </b>
                    <select name=activityId>
                        <c:forEach items="${activities}" var="s">
                            <option value="${s.id}">${s.name} (${s.category.name})</option>
                        </c:forEach>
                    </select>
                    <fmt:message key="label.time" />:&nbsp;
                    <input name="hour" type="number" min="0" max="23" value="0" size="2">
                    <fmt:message key="label.hour"/>&nbsp;
                    <input name="min" type="number" min="0" max="55" value="5" step="5" size="2">
                    <fmt:message key="label.min" />&nbsp;&nbsp;
                    <input type="hidden" name="userId" value="${sessionScope.user.id}">
                    <button type="submit"> <fmt:message key="label.add" /> </button>
                </form>
            </div>
        </div>
    </div>
</div>



<!-- </div> -->
</body>
</html>

