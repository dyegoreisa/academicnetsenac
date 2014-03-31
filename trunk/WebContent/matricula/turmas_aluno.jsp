<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<table class="table table-striped table-hover">
	<thead>
	<tr>
		<th>Turma</th>
		<th>Data Inicio</th>
		<th>Data Fim</th>
		<th>Previsão Termino</th>
		<th>Curso</th>
		<th>A&ccedil;&atilde;o</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="item" items="${aluno.matriculas}">
		<tr>
			<td>${item.turma.nome}</td>
			<td><fmt:formatDate value="${item.turma.dataInicio}" pattern="dd/MM/yyyy"/></td>
			<td><fmt:formatDate value="${item.turma.dataFim}" pattern="dd/MM/yyyy"/></td>
			<td><fmt:formatDate value="${item.turma.previsaoTermino}" pattern="dd/MM/yyyy"/></td>
			<td>${item.turma.curso.nome}</td>
			<td>
				<a href="MatriculaServlet?id_turma=${item.turma.id}&id_aluno=${aluno.id}&acao=3&destino=aluno" onclick="return excluir('Deseja cancelar a matricula?');" >
					<span class="glyphicon glyphicon-remove-sign"></span>
				</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
	
</table>