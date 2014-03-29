<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<c:if test="${sessionScope.usuarioLogado == null }">
	<jsp:forward page="login.jsp"/>
</c:if>

<body>
<%// <jsp:include page="${pageContext.request.contextPath}/paginas/menu.jsp"/> %>
<br>
<p><font size="<%=Math.random() *5 %>"><%=new Date() %></font></p>
