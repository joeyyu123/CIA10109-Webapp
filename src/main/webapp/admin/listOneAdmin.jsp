
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.joey.*"%>
<%@ page import="entity.Admin" %>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
    Admin admin = (Admin) request.getAttribute("admin");
%>

<html>
<head>
    <title>管理員資料 - listOneAdmin.jsp</title>

    <style>
        table#table-1 {
            background-color: #CCCCFF;
            border: 2px solid black;
            text-align: center;
        }
        table#table-1 h4 {
            color: red;
            display: block;
            margin-bottom: 1px;
        }
        h4 {
            color: blue;
            display: inline;
        }
    </style>

    <style>
        table {
            width: 1000px;
            background-color: white;
            margin-top: 5px;
            margin-bottom: 5px;
        }
        table, th, td {
            border: 1px solid #CCCCFF;
        }
        th, td {
            padding: 5px;
            text-align: center;
        }
    </style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
    <tr><td>
        <h3>管理員資料 - listOneAdmin.jsp</h3>
        <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
    </td></tr>
</table>

<table>
    <tr>
        <th>管理員編號</th>
        <th>管理員帳號</th>
        <th>管理員信箱</th>
        <th>管理員電話</th>
        <th>管理員狀態</th>

    </tr>
    <tr>
        <td><%= admin.getAdminNo() %></td>
        <td><%= admin.getAdminAccount() %></td>
        <td><%= admin.getAdminMail() %></td>
        <td><%= admin.getAdminPhone() %></td>
        <td><%= admin.getAdminStatus() %></td>
    </tr>
</table>

</body>
</html>
