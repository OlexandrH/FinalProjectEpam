<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${param.sessionLocale}"/>
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
    </form>
</div>
<br>
 <b>
 <fmt:message key="label.mydata" /> &nbsp;&nbsp; <button type="button" onclick="toggleEdit()"><fmt:message key="label.edit" /> </button>
 <br>
 <br>
 </b>
 
<fmt:message key="label.name" />: ${sessionScope.user.name} &nbsp;&nbsp; 
<br>
<br>
<div id="edit" style="display: none;">
 <form  action="user-page" method="post" >
 	<label class="fixed-width"><fmt:message key="label.name" /><label>
 	<input type="text" name="name" value="${sessionScope.user.name}" minlength="2" maxlength="30" pattern="[.a-zA-ZА-Яа-яҐґЇїІіЄє0-9_\s]{2,30}" required/>
 	<br>
 	<label class="fixed-width"><fmt:message key="label.password" /></label>
 	<input type="password" name="password" value="${sessionScope.user.password}" minlength="3" maxlength="30" pattern="[a-zA-Z0-9_]{3,30}" required />
 	<input type="hidden" name="id" value="${sessionScope.user.id}"/>
 	<br>
 	<button type=submit><fmt:message key="label.save" /></button>
 	<button type=reset><fmt:message key="label.restore" /></button>
 </form>
 </div>
 <hr>
 <br>
 <b>
 <fmt:message key="label.myactivities" />
 </b>
 <br>
 <br>
 <div>

 <table >
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
 			<a href="user-page?usersActSortBy=cagegory&amp;usersActOrder=asc">+</a>
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
       
       <td >${s.activity.name}</td>
      
       <td>${s.activity.category.name}</td>

       <c:set value="${s.amountTime/60}" var="hours"/>
       <fmt:formatNumber var="hours" value="${hours}" maxFractionDigits="0" />
       <td>${hours}&nbsp;<fmt:message key="label.hour"/>&nbsp;${s.amountTime%60}&nbsp;<fmt:message key="label.min"/></td>
       <td>${s.status}</td>
      
       <td>
      
         <form action="user-act-add" method="get">
          <c:if test="${s.status == 'BOOKED'}">
         <fmt:message key="label.unbook" var="buttonText"/>
         <input type="submit" value="${buttonText}">
         <input type="hidden" name="id" value="${s.id}">
          </c:if>
       </form>
     
      </td>

       </tr>
    </c:forEach>
 </table>
</div>

<fmt:message key="label.page" />:&nbsp;
<c:forEach begin="1" end="${numPages}" varStatus="loop">
    <a href="user-page?usersActPage=${loop.index}">${loop.index}</a>
</c:forEach>

<hr>
<br>
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

<div class="message-bar">
    <h4>Something else, something else, something else</h4>
</div>
<p id="demo"></p>
</body>
</html>

