<%@page import="br.com.senac.model.Aluno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrado com sucesso</title>
</head>
<body>
<jsp:useBean id="aluno" scope="request" class="br.com.senac.model.Aluno"/>
<H1>Cadastrado com sucesso!</H1>
<p>
	Dados do aluno cadatrado
	Nome: <jsp:getProperty property="nome" name="aluno"/>
	<br>
	Sobrenome: <jsp:getProperty property="sobrenome" name="aluno"/>
	<br>
	Sexo: ${aluno.sexo}
	<br>
	E-mail: ${aluno.email}
	<br>
	<br>
	Informações resumidas do aluno:<br>
	<%=aluno %>
</p>
</body>
</html>