<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Meu Projeto Web</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
</head>

<c:if test="${sessionScope.usuarioLogado == null }">
	<jsp:forward page="login.jsp"/>
</c:if>

<body>
<%// <jsp:include page="${pageContext.request.contextPath}/paginas/menu.jsp"/> %>
<br>
<p><font size="<%=Math.random() *5 %>"><%=new Date() %></font></p>
<script src="bootstrap/js/jquery.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
</body>
</html>