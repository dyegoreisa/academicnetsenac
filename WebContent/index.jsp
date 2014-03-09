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
<body>
<p><font size="<%=Math.random() *5 %>"><%=new Date() %></font></p>
<p>Seja bem vindo ao meu projeto!<br>
<br>
</p>
<ul>
	<li>Aluno
		<ul>
			<li><a href="aluno_cadastrar.jsp">Cadastrar</a></li>
			<li><a href="aluno_buscar.jsp">Buscar</a></li>
			<li><a href="aluno_listar.jsp">Listar</a></li>
		</ul>
	</li>
</ul>
<script src="bootstrap/js/jquery.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
</body>
</html>