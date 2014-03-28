<%@ page import="br.com.senac.model.Aluno"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buscar Aluno</title>
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<c:if test="${sessionScope.usuarioLogado ne null }">
<ul>
	<li>Aluno
		<ul>
			<li><a href="${pageContext.request.contextPath}/AlunoServlet">Aluno</a></li>
			<li><a href="${pageContext.request.contextPath}/CursoServlet">Curso</a></li>
			<li><a href="${pageContext.request.contextPath}/TurmaServlet">Turma</a></li>
			<li><a href="${pageContext.request.contextPath}/LogoutServlet">Sair</a></li>
		</ul>
	</li>
</ul>
</c:if>