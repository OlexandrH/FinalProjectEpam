<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="ua"/>
<fmt:setBundle basename="userinterface"/>


<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Test Page</title>
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

<fmt:message key="label.hello" />

<div class="header-bar">
        <h2>Hello, admin!
        </h2>
</div>
<br>


<div style="display: table; width: 100%;">
    <div style="display: table-row;">

        <div name "left" style="width: 50%; display: table-cell;">
            left side
        </div>

        <div name "right" style"display: table-cell;">
            right side
        </div>
    </div>
</div>

</body>