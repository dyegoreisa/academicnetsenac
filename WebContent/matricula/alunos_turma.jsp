<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<table class="table table-striped table-hover">
	<caption>Alunos da turma <b>${turma.nome}</b> com previs&atilde;o de t&eacute;rmino em 
	<b><fmt:formatDate value="${turma.previsaoTermino}" pattern="dd/MM/yyyy"/></b></caption>
	<thead>
	<tr>
		<th>Nome</th>
		<th>Sobrenome</th>
		<th>E-mail</th>
		<th>Nascimento</th>
		<th>Cancelar Matr&iacute;cula</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="item" items="${turma.matriculas}">
		<tr>
			<td>${item.aluno.nome}</td>
			<td>${item.aluno.sobrenome}</td>
			<td>${item.aluno.email}</td>
			<td><fmt:formatDate value="${item.aluno.dataNascimento}" pattern="dd/MM/yyyy"/></td>
			<td>
				<a href="MatriculaServlet?id_turma=${turma.id}&id_aluno=${item.aluno.id}&acao=3&destino=turma" onclick="return excluir('Deseja cancelar a matricula?');" >
					<span class="glyphicon glyphicon-remove-sign"></span>
				</a>
			</td>
			
		</tr>
	</c:forEach>
	</tbody>
	
</table>