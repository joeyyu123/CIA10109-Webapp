<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.joey.*"%>
<%@ page import="entity.Admin" %>

<% //��com.emp.controller.EmpServlet.java��163��s�Jreq��empVO���� (�����q��Ʈw���X��empVO, �]�i�H�O��J�榡�����~�ɪ�empVO����)
   Admin admin = (Admin) request.getAttribute("admin");
%>
--<%= admin==null %>--${admin.adminno}--  <!-- line 100 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���u��ƭק� - update_admin_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>���u��ƭק� - update_admin_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="admin.do" name="form1">
<table>
	<tr>
		<td>���u�s��:<font color=red><b>*</b></font></td>
		<td><%=admin.getAdminNo()%></td>
	</tr>

	<tr>
		<td>���u�b��:</td>
		<td><input type="TEXT" name="adminAccount" value="<%=admin.getAdminAccount()%>" size="45"/></td>
	</tr>

	<tr>
		<td>���u�K�X:</td>
		<td><input type="TEXT" name="adminPwd"  size="45"/></td>
	</tr>

	<tr>
		<td>�H�c:</td>
		<td><input type="TEXT" name="adminMail"   value="<%=admin.getAdminMail()%>" size="45"/></td>
	</tr>
	<tr>
		<td>�q��:</td>
		<td><input type="text" name="adminPhone" value="<%=admin.getAdminPhone()%>"  ></td>
	</tr>
	<tr>
		<td>�b�����A:</td>
		<td><input type="TEXT" name="adminStatus"   value="<%=admin.getAdminStatus()%>" size="45"/></td>
	</tr>




</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="adminno" value="<%=admin.getAdminNo()%>">
<input type="hidden" name="adminAccount" value="<%=admin.getAdminAccount()%>">
<input type="hidden" name="adminPwd" value="<%=admin.getAdminPwd()%>">
<input type="hidden" name="adminMail" value="<%=admin.getAdminMail()%>">
<input type="hidden" name="adminPhone" value="<%=admin.getAdminPhone()%>">
<input type="hidden" name="adminStatus" value="<%=admin.getAdminStatus()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>

</html>