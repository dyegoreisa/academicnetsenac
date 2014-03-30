<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="form-group">
  <div class="col-md-4">
    <a class="btn btn-default" href="${pageContext.request.contextPath}/AlunoServlet?acao=6">
    	&nbsp;<span class="glyphicon glyphicon-plus"></span>&nbsp;Novo
    </a>
  </div>
</div>

<form class="form-inline" action="AlunoServlet">
<fieldset>

<!-- Search input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="conteudo"></label>
  <div class="col-md-4">
    <input id="conteudo" name="conteudo" type="search" placeholder="Nome" class="form-control input-md">
    
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for=""></label>
  <div class="col-md-4">
    <button id="" name="" class="btn btn-primary">Filtrar</button>
  </div>
</div>

<input type="hidden" name="acao" value="5">

</fieldset>
</form>
<hr>
<table class="table table-striped table-hover">
	<thead>
	<tr>
		<th>Nome</th>
		<th>Sobrenome</th>
		<th>E-mail</th>
		<th>Nascimento</th>
		<th colspan="2">Aç&otilde;es</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="item" items="${listaAlunos}">
		<tr>
			<td>${item.nome}</td>
			<td>${item.sobrenome}</td>
			<td>${item.email}</td>
			<td><fmt:formatDate value="${item.dataNascimento}" pattern="dd/MM/yyyy"/></td>
			<td>
				<a href="AlunoServlet?id=${item.id}&acao=4">
					<span class="glyphicon glyphicon-edit"></span>
				</a>
			</td>
			<td>
				<a href="AlunoServlet?id=${item.id}&acao=3" onclick="return excluir();">
					<span class="glyphicon glyphicon-remove-sign"></span>
				</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
	
</table>