<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="userinterface"/>

<head>
<script type="text/javascript">

function setEng()
{

    '<%session.setAttribute("language", "en"); %>';

    window.location.reload();
}

function setUkr()
{
    '<%session.setAttribute("language", "ua"); %>';
    window.location.reload();
}

</script>
</head>

<!-- Admin header -->
       <div class="abc">
            <a href="admin.jsp"> <fmt:message key="label.adminpage"/> </a> &nbsp;
            <a href="signout"> <fmt:message key="label.signout"/> </a> &nbsp;&nbsp;&nbsp;
            <a href="?sessionLocale=en">ENG</a>&nbsp;
                        <a href="?sessionLocale=ua">УКР</a>&nbsp;
        </div>
<!-- -->

